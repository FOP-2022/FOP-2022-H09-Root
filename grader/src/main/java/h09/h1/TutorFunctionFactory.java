package h09.h1;

/**
 * A factory that allows functions to be created.
 *
 * @author Nhan Huynh, Darya Nikitina
 */
public final class TutorFunctionFactory {

    /**
     * Don't let anyone instantiate this class.
     */
    private TutorFunctionFactory() {
    }

    /**
     * Creates a function which allows using filter, map and fold operation on the elements.
     *
     * @param traits the traits which provides a filter, map and fold operation.
     * @param <X>    the type of the element used in the filter operation
     * @param <Y>    the type of the element to be mapped
     * @param <Z>    the type of the result
     *
     * @return a function which allows using filter, map and fold operation on the elements
     */
    public static <X, Y, Z> FunctionWithFilterMapAndFold<X, Y, Z> createFunctionWithFilterMapAndFold(
        final Traits<X, Y, Z> traits) {
        return new MyFunctionWithFilterMapAndFold<>(traits);
    }

    /**
     * Creates a function which allows using filter, map, fold and combine operation on the elements.
     *
     * @param traits the traits which provides a filter, map, fold and combine operation.
     * @param <X>    the type of the element used in the filter operation
     * @param <Y>    the type of the element to be mapped and combined
     * @param <Z>    the type of the result
     *
     * @return a function which allows using filter, map, fold and combine operation on the elements
     */
    public static <X, Y, Z> FunctionWithFilterMapAndFold<X, Y, Z> createFunctionWithFilterMapFoldAndCombine(
        final Traits<X, Y, Z> traits) {
        return new MyFunctionWithAdjacent<>(traits);
    }
}
