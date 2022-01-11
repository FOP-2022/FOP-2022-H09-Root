package h09.utils.spoon;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtConstructorCall;
import spoon.reflect.declaration.CtMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Defines a processor that scans the constructor calls in a method.
 *
 * @author Nhan Huynh, Darya Nikitina
 */
public final class ConstructorsCallMethodBodyProcessor extends AbstractProcessor<CtMethod<?>> {

    /**
     * The method name to look for constructor calls instantiation.
     */
    private final String methodName;

    /**
     * Contains the constructor calls of the specified method.
     */
    private final List<CtConstructorCall<?>> constructors;

    /**
     * Constructs and initializes a processor which scans all constructor calls in the specified
     * method.
     *
     * @param methodName the name of the method that should be processed
     */
    public ConstructorsCallMethodBodyProcessor(final String methodName) {
        this.methodName = methodName;
        this.constructors = new ArrayList<>();
    }

    @Override
    public void process(final CtMethod<?> method) {
        constructors.addAll(method.getElements(constructor -> method.getSimpleName().equals(methodName)));
    }

    /**
     * Returns the scanned constructor calls  so far. If this processor does not process any method
     * yet, the content will be empty.
     *
     * @return the scanned constructor calls  so far
     */
    public List<CtConstructorCall<?>> getConstructors() {
        return constructors;
    }
}
