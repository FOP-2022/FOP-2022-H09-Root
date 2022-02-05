package h09.utils.spoon;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtConstructorCall;
import spoon.reflect.code.CtVariableAccess;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.reference.CtTypeReference;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Defines a processor that scans the constructor calls and variable access in a method.
 *
 * @author Nhan Huynh, Darya Nikitina
 */
public class ObjectsUsageMethodProcessor extends AbstractProcessor<CtClass<?>> {

    /**
     * The method name to look for constructor calls instantiation.
     */
    private final String methodName;

    /**
     * Contains the types of the constructor calls and variable access of the specified method.
     */
    private final List<CtTypeReference<?>> types;

    /**
     * Constructs and initializes a processor which scans all constructor calls and variable access
     * in the specified method.
     *
     * @param methodName the name of the method that should be processed
     */
    public ObjectsUsageMethodProcessor(final String methodName) {
        this.methodName = methodName;
        this.types = new ArrayList<>();
    }

    @Override
    public void process(final CtClass<?> clazz) {
        final var methods = clazz.getElements((CtMethod<?> method) -> method.getSimpleName().equals(methodName));
        if (methods.isEmpty()) {
            return;
        }
        final var method = methods.get(0);

        // Variable access
        types.addAll(
            method.getElements((CtVariableAccess<?> variable) -> true)
                .stream()
                .map(CtVariableAccess::getType)
                .distinct()
                .collect(Collectors.toList())
        );

        // Constructor call
        types.addAll(
            method.getElements((CtConstructorCall<?> constructor) -> true)
                .stream()
                .map(CtConstructorCall::getType)
                .distinct()
                .collect(Collectors.toList())
        );
    }

    /**
     * Returns the scanned types of the constructor calls or variable access so far. If this
     * processor does not process any method yet, the content will be empty.
     *
     * @return the scanned constructor calls  so far
     */
    public List<CtTypeReference<?>> getTypes() {
        return types;
    }
}
