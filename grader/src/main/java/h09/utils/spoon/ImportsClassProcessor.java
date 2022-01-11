package h09.utils.spoon;

import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtImport;
import spoon.reflect.visitor.DefaultJavaPrettyPrinter;
import spoon.reflect.visitor.ImportScanner;
import spoon.reflect.visitor.ImportScannerImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * Defines a processor that scans the imported classes of a class.
 *
 * @author Nhan Huynh, Darya Nikitina
 */
public final class ImportsClassProcessor extends AbstractProcessor<CtClass<?>> {

    /**
     * The scanner used to scan the imports of a {@link CtElement},
     */
    private final ImportScanner scanner;

    /**
     * Contains all scanned imports.
     */
    private final Map<String, CtImport> imports;

    /**
     * Constructs and initializes a processor which scans all imports of classes.
     */
    public ImportsClassProcessor() {
        this.scanner = new ImportScannerImpl();
        this.imports = new HashMap<>();
    }

    @Override
    public void process(final CtClass<?> clazz) {
        imports.clear();

        // Scan imports of a class
        scanner.computeImports(clazz.getPackage());

        // Retrieve scanned value and store them in key-value pair: import - spoon import
        for (final var ctImport : scanner.getAllImports()) {
            final var reference = ctImport.getReference();
            final var printer = new DefaultJavaPrettyPrinter(reference.getFactory().getEnvironment());
            printer.scan(reference);
            imports.put(printer.toString(), ctImport);
        }
    }

    /**
     * Returns the scanned imports so far. If this processor does not process any class yet, the
     * content will be empty.
     *
     * @return the scanned imports so far
     */
    public Map<String, CtImport> getImports() {
        return imports;
    }
}
