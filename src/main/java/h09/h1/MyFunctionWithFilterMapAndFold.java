package h09.h1;

/**
 * A function that provides an operation that filter a set of elements, which will be then mapped
 * and reduced (folded) to a single value.
 *
 * @param <X> the type of the element to be filtered
 * @param <Y> the type of the element to be mapped
 * @param <Z> the type of the element to be reduced
 *
 * @author Nhan Huynh, Darya Nikitina
 */
public class MyFunctionWithFilterMapAndFold<X, Y, Z> extends FunctionWithFilterMapAndFold<X, Y, Z> {

    /**
     * Constructs and initialized a function that enables filter, map, fold operation.
     *
     * @param traits the traits used to access filter, map, fold
     */
    public MyFunctionWithFilterMapAndFold(final Traits<X, Y, Z> traits) {
        super(traits);
    }

    /**
     * Transforms the specified elements using a filter which are then mapped. The mapped values
     * reduced to a single value using fold.
     *
     * @param elements the elements to be transformed
     *
     * @return the transformed value
     */
    @Override
    public Z apply(final X[] elements) {
        // Filter elements
        final var pred = traits.getPred();
        int size = 0;
        for (final var element : elements) {
            if (pred.test(element)) {
                size++;
            }
        }

        // Map the filtered elements
        // Compute mapped elements array length
        @SuppressWarnings("unchecked") final var filtered = (X[]) new Object[size];
        int index = 0;
        for (final var element : elements) {
            if (pred.test(element)) {
                filtered[index++] = element;
            }
        }

        // Fill array
        final var fct = traits.getFct();
        @SuppressWarnings("unchecked") final var mapped = (Y[]) new Object[size];
        for (int i = 0; i < mapped.length; i++) {
            mapped[i] = fct.apply(filtered[i]);
        }

        // Fold mapped elements
        final var op = traits.getOp();
        Z accumulator = traits.getInit();

        for (final var element : mapped) {
            accumulator = op.apply(accumulator, element);
        }
        return accumulator;
    }
}
