package h09.utils.spoon;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtNewArray;
import spoon.reflect.declaration.CtMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Defines a processor that scans the arrays' instantiation in a method.
 *
 * @author Nhan Huynh, Darya Nikitina
 */
public final class ArraysInstantiationMethodBodyProcessor extends AbstractProcessor<CtMethod<?>> {

  /**
   * The method name to look for arrays instantiation.
   */
  private final String methodName;

  /**
   * Contains the arrays' instantiation of the specified method.
   */
  private final List<CtNewArray<?>> arrays;

  /**
   * Constructs and initializes a processor which scans all array instantiation in the specified
   * method.
   *
   * @param methodName the name of the method that should be processed
   */
  public ArraysInstantiationMethodBodyProcessor(final String methodName) {
    this.methodName = methodName;
    this.arrays = new ArrayList<>();
  }

  @Override
  public void process(final CtMethod<?> method) {
    arrays.addAll(method.getElements(array -> method.getSimpleName().equals(methodName)));
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
