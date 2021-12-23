package h09.utils.spoon;

import org.junit.jupiter.api.Assertions;
import org.sourcegrade.jagr.api.testing.TestCycle;
import spoon.Launcher;
import spoon.processing.Processor;
import spoon.support.QueueProcessingManager;
import spoon.support.compiler.VirtualFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public final class SpoonUtils {

  private final static Map<String, QueueProcessingManager> CACHE_PROCESSING_MANAGER = new HashMap<>();

  private SpoonUtils() {
  }

  public static VirtualFile getSourceCode(final TestCycle testCycle, final String path) {
    try {
      // TODO Jagr API
//      final var source = testCycle.getSubmission().getSourceFile(path);
//      if (source!=null) {
//        return Assertions.fail(String.format("The specified source code %s could not be accessed.",
//          path));
//      }
//      return new VirtualFile(source.getContent());
      return new VirtualFile(Files.lines(Path.of(path)).collect(Collectors.joining("\n")));
    } catch (IOException e) {
      return Assertions.fail(String.format("The source code %s could not accessed.", path), e);
    }
  }

  public static <P extends Processor<?>> P process(final TestCycle testCycle, final String path,
                                                   final P processor) {
    QueueProcessingManager manager;
    if (!CACHE_PROCESSING_MANAGER.containsKey(path)) {
      final var source = getSourceCode(testCycle, path);
      final var launcher = new Launcher();

      launcher.addInputResource(source);
      launcher.run();

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
