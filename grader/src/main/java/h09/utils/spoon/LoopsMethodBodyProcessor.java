package h09.utils.spoon;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtDo;
import spoon.reflect.code.CtFor;
import spoon.reflect.code.CtForEach;
import spoon.reflect.code.CtLoop;
import spoon.reflect.code.CtWhile;
import spoon.reflect.declaration.CtMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Defines a processor that scans the for loops in a methods.
 *
 * @author Nhan Huynh, Darya Nikitina
 */
public final class LoopsMethodBodyProcessor extends AbstractProcessor<CtMethod<?>> {

  /**
   * The method name to look for loops.
   */
  private final String methodName;

  /**
   * Contains the {@code for} loops of the specified method.
   */
  private final List<CtFor> forLoops;

  /**
   * Contains the {@code foreach} loops of the specified method.
   */
  private final List<CtForEach> foreachLoops;

  /**
   * Contains the {@code while} loops of the specified method.
   */
  private final List<CtWhile> whileLoops;

  /**
   * Contains the {@code do-while} loops of the specified method.
   */
  private final List<CtDo> doWhileLoops;

  /**
   * Constructs and initializes a processor which scans all loops in the specified method.
   *
   * @param methodName the name of the method that should be processed
   */
  public LoopsMethodBodyProcessor(final String methodName) {
    this.methodName = methodName;
    this.forLoops = new ArrayList<>();
    this.foreachLoops = new ArrayList<>();
    this.whileLoops = new ArrayList<>();
    this.doWhileLoops = new ArrayList<>();
  }

  @Override
  public void process(final CtMethod<?> method) {
    // Retrieve all loops
    final var loops = method.getElements(
      (CtLoop statement) -> method.getSimpleName().equals(methodName)
    );

    // Store the loops in their respective collection
    for (final var loop : loops) {
      if (loop instanceof CtFor) {
        forLoops.add((CtFor) loop);
      } else if (loop instanceof CtForEach) {
        foreachLoops.add((CtForEach) loop);
      } else if (loop instanceof CtWhile) {
        whileLoops.add((CtWhile) loop);
      } else {
        doWhileLoops.add((CtDo) loop);
      }
    }
  }

  /**
   * Returns the scanned {@code for} loop so far. If this processor does not process any method
   * yet, the content will be empty.
   *
   * @return the scanned {@code for} loop so far
   */
  public List<CtFor> getForLoops() {
    return forLoops;
  }

  /**
   * Returns the scanned {@code foreach} loop so far. If this processor does not process any method
   * yet, the content will be empty.
   *
   * @return the scanned {@code foreach} loop so far
   */
  public List<CtForEach> getForeachLoops() {
    return foreachLoops;
  }

  /**
   * Returns the scanned {@code while} loop so far. If this processor does not process any method
   * yet, the content will be empty.
   *
   * @return the scanned {@code while} loop so far
   */
  public List<CtWhile> getWhileLoops() {
    return whileLoops;
  }

  /**
   * Returns the scanned {@code do-while} loop so far. If this processor does not process any method
   * yet, the content will be empty.
   *
   * @return the scanned {@code do-while} loop so far
   */
  public List<CtDo> getDoWhileLoops() {
    return doWhileLoops;
  }
}
