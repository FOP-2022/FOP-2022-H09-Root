package h09.utils.spoon;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtInvocation;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Defines a processor that scans the callees in a methods.
 *
 * @author Nhan Huynh, Darya Nikitina
 */
public class MethodCallsProcessor extends AbstractProcessor<CtClass<?>> {


    /**
     * The method name to look for callees.
     */
    private final String methodName;

    /**
     * Contains the callees of the specified method.
     */
    private final List<CtInvocation<?>> callees;

    /**
     * Constructs and initializes a processor which scans all calees in the specified method. If the method name is {@code null},
     * the whole source code will be scanned.
     *
     * @param methodName the name of the method that should be processed
     */
    public MethodCallsProcessor(final String methodName) {
        this.methodName = methodName;
        this.callees = new ArrayList<>();
    }

    @Override
    public void process(final CtClass<?> clazz) {
        // Retrieve method
        final var methods = clazz.getElements((CtMethod<?> method) -> method.getSimpleName().equals(methodName));
        if (methods.isEmpty()) {
            return;
        }
        // Retrieve callees
        callees.addAll(
            methods.get(0).getElements(
                (CtInvocation<?> invocation) -> {
                    final var parent = invocation.getParent();
                    if (parent instanceof CtMethod<?>) {
                        final var m = (CtMethod<?>) parent;
                        return m.getSimpleName().equals(methodName);
                    }
                    return false;
                }
            )
        );

    }

    /**
     * Returns the scanned callees so far. If this processor does not process any method yet, the content will be empty.
     *
     * @return the scanned callees so far
     */
    public List<CtInvocation<?>> getCallees() {
        return callees;
    }
}
