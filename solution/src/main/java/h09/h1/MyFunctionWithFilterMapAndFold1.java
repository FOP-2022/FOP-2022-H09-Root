package h09.h1;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class MyFunctionWithFilterMapAndFold1<X, Y, Z> extends
  FunctionWithFilterMapAndFold<X, Y, Z> {

  public MyFunctionWithFilterMapAndFold1(final Traits<X, Y, Z> traits) {
    super(traits);
  }

  @Override
  public Z apply(final X[] elements) {
    final Predicate<X> pred = traits.getPred();
    int size = 0;
    for (final var element : elements) {
      if (pred.test(element)) {
        size++;
      }
    }
    @SuppressWarnings("unchecked") final X[] filtered = (X[]) new Object[size];
    int index = 0;
    for (final var element : elements) {
      if (pred.test(element)) {
        filtered[index++] = element;
      }
    }

    final Function<X, Y> fct = traits.getFct();
    @SuppressWarnings("unchecked") final Y[] mapped = (Y[]) new Object[size];
    for (int i = 0; i < mapped.length; i++) {
      mapped[i] = fct.apply(filtered[i]);
    }

    final BiFunction<Y, Z, Z> op = traits.getOp();
    Z accumulator = traits.getInit();

    for (final var element : mapped) {
      accumulator = op.apply(element, accumulator);
    }
    return accumulator;
  }
}
