package h09.h1;

public final class FunctionFactory {

  private FunctionFactory() {
  }

  public static <X, Y, Z> FunctionWithFilterMapAndFold<X, Y, Z> createFunctionWithFilterMapAndFold(
    final Traits<X, Y, Z> traits) {
    return new MyFunctionWithFilterMapAndFold1<>(traits);
  }

  public static <X, Y, Z> FunctionWithFilterMapAndFold<X, Y, Z>
  createFunctionWithFilterMapFoldAndCombine(final Traits<X, Y, Z> traits) {
    return new MyFunctionWithAdjacent<>(traits);
  }
}
