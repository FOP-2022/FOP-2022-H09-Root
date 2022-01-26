package h09.utils.spoon;

import h09.utils.TutorMessage;
import org.junit.jupiter.api.Assertions;
import org.sourcegrade.jagr.api.testing.TestCycle;
import spoon.Launcher;
import spoon.processing.Processor;
import spoon.support.QueueProcessingManager;
import spoon.support.compiler.VirtualFile;

import java.util.HashMap;
import java.util.Map;

/**
 * A utility class used for spoon operations e.g. processors.
 *
 * @author Nhan Huynh, Darya Nikitina
 */
public final class SpoonUtils {

    /**
     * A cache to store the already processed source code to enable faster access.
     */
    private static final Map<String, QueueProcessingManager> CACHE_PROCESSING_MANAGER =
        new HashMap<>();

    /**
     * Don't let anyone instantiate this class.
     */
    private SpoonUtils() {
    }

    /**
     * Returns the source code to the specified java source path.
     *
     * @param testCycle the test cycle to retrieve the java source code
     * @param path      the path of the source code
     *
     * @return the retrieved source code
     */
    public static VirtualFile getSourceCode(final TestCycle testCycle, final String path) {
        final var source = testCycle.getSubmission().getSourceFile(path);
        if (source == null) {
            return Assertions.fail(TutorMessage.SOURCE_CODE_NO_ACCESS.format(path));
        }
        return new VirtualFile(source.getContent());
    }

    /**
     * Processes the specified processor on the specified source code.
     *
     * @param testCycle the test cycle to retrieve the source code
     * @param path      the path to the source code
     * @param processor the processor which will process the source code
     * @param <P>       the type of the processor
     *
     * @return the processor which processed the source code
     */
    public static <P extends Processor<?>> P process(final TestCycle testCycle, final String path, final P processor) {
        QueueProcessingManager manager;
        if (!CACHE_PROCESSING_MANAGER.containsKey(path)) {
            final var source = getSourceCode(testCycle, path);
            final var launcher = new Launcher();

            launcher.addInputResource(source);
            launcher.buildModel();
            launcher.process();

            final var factory = launcher.getFactory();
            manager = new QueueProcessingManager(factory);
            CACHE_PROCESSING_MANAGER.put(path, manager);
        } else {
            manager = CACHE_PROCESSING_MANAGER.get(path);
        }
        manager.addProcessor(processor);
        manager.process(manager.getFactory().Class().getAll());
        return processor;
    }
}
