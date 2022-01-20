package h09.utils.spoon;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtExecutableReferenceExpression;
import spoon.reflect.declaration.CtMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Defines a processor that method references (lambda) in a method.
 *
 * @author Nhan Huynh, Darya Nikitina
 */
public class MethodReferencesMethodBodyProcessor extends AbstractProcessor<CtMethod<?>> {

    /**
     * The method name to look for constructor calls instantiation.
     */
    private final String methodName;

    /**
     * Contains all method references.
     */
    private final List<CtExecutableReferenceExpression<?, ?>> methodReferences;

    /**
     * Constructs and initializes a processor which scans all method references in the specified
     * method.
     *
     * @param methodName the name of the method that should be processed
     */
    public MethodReferencesMethodBodyProcessor(final String methodName) {
        this.methodName = methodName;
        this.methodReferences = new ArrayList<>();
    }

    @Override
    public void process(final CtMethod<?> method) {
        methodReferences.addAll(
            method.getElements(
                (CtExecutableReferenceExpression<?, ?> lambda) -> method.getSimpleName().equals(methodName)
            )
        );
    }

    /**
     * Returns the scanned method references so far. If this processor does not process any method
     * yet, the content will be empty.
     *
     * @return the scanned constructor calls  so far
     */
    public List<CtExecutableReferenceExpression<?, ?>> getMethodReferences() {
        return methodReferences;
    }

}
