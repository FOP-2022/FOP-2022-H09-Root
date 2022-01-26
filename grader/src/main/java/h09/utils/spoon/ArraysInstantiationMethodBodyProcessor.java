package h09.utils.spoon;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtNewArray;
import spoon.reflect.declaration.CtClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Defines a processor that scans the arrays' instantiation in a class.
 *
 * @author Nhan Huynh, Darya Nikitina
 */
public final class ArraysInstantiationMethodBodyProcessor extends AbstractProcessor<CtClass<?>> {

    /**
     * The class name to look for arrays instantiation.
     */
    private final String className;

    /**
     * Contains the arrays' instantiation of the specified method.
     */
    private final List<CtNewArray<?>> arrays;

    /**
     * Constructs and initializes a processor which scans all array instantiation in the specified
     * class.
     *
     * @param className the name of the method that should be processed
     */
    public ArraysInstantiationMethodBodyProcessor(final String className) {
        this.className = className;
        this.arrays = new ArrayList<>();
    }

    @Override
    public void process(final CtClass<?> clazz) {
        if (!clazz.getSimpleName().equals(className)) {
            return;
        }
        arrays.addAll(clazz.getElements((CtNewArray<?> array) -> true));
    }

    /**
     * Returns the scanned array instantiation so far. If this processor does not process any method
     * yet, the content will be empty.
     *
     * @return the scanned array instantiation so far
     */
    public List<CtNewArray<?>> getArrays() {
        return arrays;
    }
}
