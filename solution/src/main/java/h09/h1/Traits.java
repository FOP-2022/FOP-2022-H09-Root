package h09.h1;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * A traits which provides filter, map, fold combine operations.
 *
 * @param <X> the type of element that will be used for the filter operation
 * @param <Y> the type of element that will be used for the mapping operation and optionally combine
 * @param <Z> the type of element that will be used for the folding operation
 *
 * @author Nhan Huynh, Darya Nikitina
 */
public class Traits<X, Y, Z> {

  /**
   * The predicate used to filter elements (filtering). This is equivalent to H07 {@code
   * PersonFilter}.
   */
  private final Predicate<? super X> pred;

  /**
   * The function used to map elements (mapping). This is equivalent to H07 {@code
   * PersonToIntFunction}.
   */
  private final Function<? super X, ? extends Y> fct;

  /**
   * The operator to use an operation on two given arguments and produce a result from it (folding).
   * This is equivalent to H07 {@code IntBinaryOperator}.
   */
  private final BiFunction<Z, ? super Y, Z> op;

  /**
   * The combine operator to combine wo given arguments from the same type. This is equivalent to
   * H07 {@code IntBinaryOperator}.
   */
  private final BiFunction<Y, ? super Y, Y> combine;

  /**
   * The initial value of the fold operation.
   */
  private final Z init;

  /**
   * Constructs and initialized a traits which provides a filter, map, fold and combine operation.
   *
   * @param pred    the predicate to filter elements
   * @param fct     the function to map the elements
   * @param op      the operator to produce a result from two given arguments
   * @param combine the operator to combine two given arguments
   * @param init    the initial value of the traits
   */
  public Traits(final Predicate<? super X> pred, final Function<? super X, ? extends Y> fct,
                final BiFunction<Z, ? super Y, Z> op,
                final BiFunction<Y, ? super Y, Y> combine, final Z init) {
    this.pred = pred;
    this.fct = fct;
    this.op = op;
    this.init = init;
    this.combine = combine;
  }

  /**
   * Constructs and initialized a traits which provides a filter, map and fold operation.
   *
   * @param pred the predicate to filter elements
   * @param fct  the function to map the elements
   * @param op   the operator to produce a result from two given arguments
   * @param init the initial value of the traits
   */
  public Traits(final Predicate<? super X> pred, final Function<? super X, ? extends Y> fct,
                final BiFunction<Z, ? super Y, Z> op,
                final Z init) {
    this(pred, fct, op, null, init);
  }

  /**
   * Returns the predicate to filter elements.
   *
   * @return the predicate to filter elements
   */
  public Predicate<? super X> getPred() {
    return pred;
  }

  /**
   * Returns the function to map the elements.
   *
   * @return the function to map the elements
   */
  public Function<? super X, ? extends Y> getFct() {
    return fct;
  }

  /**
   * Returns the operator to produce a result from two given arguments.
   *
   * @return the operator to produce a result from two given arguments
   */
  public BiFunction<Z, ? super Y, Z> getOp() {
    return op;
  }

  /**
   * Returns the initial value of the fold operation.
   *
   * @return the initial value of the fold operation
   */
  public Z getInit() {
    return init;
  }

  /**
   * Returns the operator which combines two given arguments from the same type.
   *
   * @return two given arguments from the same type
   */
  public BiFunction<Y, ? super Y, Y> getCombine() {
    return combine;
  }
}
