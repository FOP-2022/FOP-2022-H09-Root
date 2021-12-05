package h09.h1;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class Traits<X, Y, Z> {

  private final Predicate<X> pred;
  private final Function<X, Y> fct;
  private final BiFunction<Y, Z, Z> op;
  private final BiFunction<Y, Y, Y> combine;
  private final Z init;

  public Traits(final Predicate<X> pred, final Function<X, Y> fct, final BiFunction<Y, Z, Z> op,
                final BiFunction<Y, Y, Y> combine, final Z init) {
    this.pred = pred;
    this.fct = fct;
    this.op = op;
    this.init = init;
    this.combine = combine;
  }

  public Traits(final Predicate<X> pred, final Function<X, Y> fct, final BiFunction<Y, Z, Z> op,
                final Z init) {
    this(pred, fct, op, null, init);
  }

  public Predicate<X> getPred() {
    return pred;
  }

  public Function<X, Y> getFct() {
    return fct;
  }

  public BiFunction<Y, Z, Z> getOp() {
    return op;
  }

  public Z getInit() {
    return init;
  }

  public BiFunction<Y, Y, Y> getCombine() {
    return combine;
  }
}
