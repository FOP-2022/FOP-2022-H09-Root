package h09.h1;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class MyFunctionWithAdjacent<X, Y, Z> extends FunctionWithFilterMapAndFold<X, Y, Z> {

  public MyFunctionWithAdjacent(final Traits<X, Y, Z> traits) {
    super(traits);
  }

  @Override
  public Z apply(final X[] elements) {
    final Predicate<X> pred = traits.getPred();
    final Function<X, Y> fct = traits.getFct();
    final BiFunction<Y, Z, Z> op = traits.getOp();
    final BiFunction<Y, Y, Y> combine = traits.getCombine();
    Z accumulator = traits.getInit();

    Y previous = null;

    for (final var element : elements) {
      if (pred.test(element)) {
        final var mapped = fct.apply(element);
        if (previous!=null) {
          accumulator = op.apply( combine.apply(previous, mapped), accumulator);
        }
        previous = mapped;
      }
    }
    return accumulator;
  }
}
