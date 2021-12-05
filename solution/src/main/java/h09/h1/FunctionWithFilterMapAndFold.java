package h09.h1;

public abstract class FunctionWithFilterMapAndFold<X, Y, Z> {

  protected final Traits<X, Y, Z> traits;

  public FunctionWithFilterMapAndFold(final Traits<X, Y, Z> traits) {
    this.traits = traits;
  }

  public abstract Z apply(final X[] elements);
}
