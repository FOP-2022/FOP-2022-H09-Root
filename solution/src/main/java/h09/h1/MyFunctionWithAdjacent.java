package h09.h1;

/**
 * A function that provides an operation that filter a set of elements, which will be then
 * mapped, combines the adjacent mapped elements and then reduced (folded) to a single value.
 *
 * @param <X> the type of the element to be filtered
 * @param <Y> the type of the element to be mapped and optionally combined
 * @param <Z> the type of the element to be reduced
 * @author Nhan Huynh, Darya Nikitina
 */
public class MyFunctionWithAdjacent<X, Y, Z> extends FunctionWithFilterMapAndFold<X, Y, Z> {

    /**
     * Constructs and initialized a function that enables filter, map, fold and combine operation.
     *
     * @param traits the traits used to access filter, map, fold and combine operation
     */
    public MyFunctionWithAdjacent(final Traits<X, Y, Z> traits) {
        super(traits);
    }

    /**
     * Transforms the specified elements using a filter which are then mapped. The mapped values
     * are combined with their respective adjacent element and then reduced to a single value using
     * fold.
     *
     * @param elements the elements to be transformed
     * @return the transformed value
     */
    @Override
    public Z apply(final X[] elements) {
        final var pred = traits.getPred();
        final var fct = traits.getFct();
        final var op = traits.getOp();
        final var combine = traits.getCombine();
        var accumulator = traits.getInit();

        // Combine operation needs the index i and i+1
        Y previous = null;
        for (final var element : elements) {
            if (pred.test(element)) {
                final var mapped = fct.apply(element);
                if (previous != null) {
                    accumulator = op.apply(accumulator, combine.apply(previous, mapped));
                }
                previous = mapped;
            }
        }
        return accumulator;
    }
}
