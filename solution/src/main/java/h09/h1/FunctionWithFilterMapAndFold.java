package h09.h1;

/**
 * A function that provides an operation that filter a set of elements, which will be then mapped
 * and optionally combined and then reduced (folded) to a single value.
 *
 * @param <X> the type of the element to be filtered
 * @param <Y> the type of the element to be mapped and optionally combined
 * @param <Z> the type of the element to be reduced
 * @author Nhan Huynh, Darya Nikitina
 */
public abstract class FunctionWithFilterMapAndFold<X, Y, Z> {

    /**
     * Provides filter, map, fold and optionally combine operation.
     */
    protected final Traits<X, Y, Z> traits;

    /**
     * Constructs and initialized a function that enables filter, map, fold and optionally
     * combine operation.
     *
     * @param traits the traits used to access filter, map, fold and optionally combine operation
     */
    public FunctionWithFilterMapAndFold(final Traits<X, Y, Z> traits) {
        this.traits = traits;
    }

    /**
     * Transforms the specified elements using a filter which are then mapped. The mapped values
     * can optionally be combined and then reduced to a single value using fold.
     *
     * @param elements the elements to be transformed
     * @return the transformed value
     */
    public abstract Z apply(final X[] elements);
}
