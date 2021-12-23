package h09.utils.spoon;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtExecutableReferenceExpression;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.reference.CtTypeReference;

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
   * Contains the type of the method references.
   */
  private final List<CtTypeReference<?>> types;

  /**
   * Constructs and initializes a processor which scans all method references in the specified
   * method.
   *
   * @param methodName the name of the method that should be processed
   */
  public MethodReferencesMethodBodyProcessor(final String methodName) {
    this.methodName = methodName;
    this.methodReferences = new ArrayList<>();
    this.types = new ArrayList<>();
  }


  @Override
  public void process(final CtMethod<?> method) {
    final var found = method.getElements(
      (CtExecutableReferenceExpression<?, ?> lambda) -> method.getSimpleName().equals(methodName)
    );

    for (final var reference : found) {
      methodReferences.add(reference);
      types.add(reference.getType());
    }
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

  /**
   * Returns the type of the scanned method references.
   *
   * @return the types of the scanned method references
   */
  public List<CtTypeReference<?>> getTypes() {
    return types;
  }
}
