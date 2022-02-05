package h09.utils;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Specifies the tutor constants that are used for the test cases.
 *
 * @author Nhan Huynh, Darya Nikitina
 */
public final class TutorConstants {

    /**
     * Don't let anyone instantiate this class.
     */
    private TutorConstants() {
    }

    /* *********************************************************************
     *                          Helper functions                           *
     **********************************************************************/

    /**
     * Returns the name of a test class.
     *
     * @param className the name of the class from which a test class is formed
     *
     * @return the name of a test class
     */
    private static String getTestClassName(final String className) {
        return String.format("Test%s", className);
    }

    /**
     * Returns the name of a tutor test class.
     *
     * @param className the name of the class from which a tutor test class is formed
     *
     * @return the name of a tutor test class
     */
    private static String getTutorTestClassName(final String className) {
        return String.format("Tutor%s", className);
    }

    /**
     * Returns the name of a getter method.
     *
     * @param fieldName The name of the field which contains a getter method
     *
     * @return the name of a getter method.
     */
    private static String getGetterMethodName(final String fieldName) {
        return String.format("get%s%s", fieldName.substring(0, 1).toUpperCase(), fieldName.substring(1));
    }

    /**
     * Returns the name of a setter method.
     *
     * @param fieldName The name of the field which contains a setter method
     *
     * @return the name of a setter method.
     */
    private static String getSetterMethodName(final String fieldName) {
        return String.format("set%s%s", fieldName.substring(0, 1).toUpperCase(),
            fieldName.substring(1));
    }

    /**
     * Returns the name of a test method.
     *
     * @param methodName the name of the method from which a test method is formed
     *
     * @return the name of a test method.
     */
    private static String getTestMethodName(final String methodName) {
        return String.format("test%s%s", methodName.substring(0, 1).toUpperCase(), methodName.substring(1));
    }

    /**
     * Returns the name of an animal typeOfX method.
     *
     * @param className the class name which is used to farm a typeOfX method
     *
     * @return the name of an animal typeOfX method
     */
    private static String getTypeOfXMethodName(final String className) {
        return String.format("getTypeOf%s", className);
    }

    /* *********************************************************************
     *                                  H1                                 *
     **********************************************************************/

    /**
     * The package name of the task H1.
     */
    public static final String H1_PACKAGE_NAME = "h09.h1";

    /**
     * The excluded imports, which may not be used in H1.3 and H1.4.
     */
    public static final Class<?>[] H1_IMPORT_BLACK_LIST = {
        Array.class, Arrays.class, Collection.class, Collections.class, List.class, Stream.class
    };

    /**
     * The class name of the task H1.1.
     */
    public static final String H1_1_CLASS_NAME = "Traits";
    /**
     * The class name of the task H1.2.
     */
    public static final String H1_2_CLASS_NAME = "FunctionWithFilterMapAndFold";
    /**
     * The class name of the task H1.3.
     */
    public static final String H1_3_CLASS_NAME = "MyFunctionWithFilterMapAndFold";
    /**
     * The class name of the task H1.4.
     */
    public static final String H1_4_CLASS_NAME = "MyFunctionWithAdjacent";
    /**
     * The class name of the task H1.5.
     */
    public static final String H1_5_CLASS_NAME = "FunctionFactory";
    /**
     * The full class name of the task H1.5.
     */
    public static final String H1_5_CLASS_NAME_FULL = String.format("%s.FunctionFactory", H1_PACKAGE_NAME);
    /**
     * The tutor class transformer for the class in task H1.5.
     */
    public static final String H1_5_CLASS_NAME_TRANSFORMER = String.format("%s.%s", H1_PACKAGE_NAME,
        getTutorTestClassName(H1_5_CLASS_NAME)).replaceAll("\\.", "/");
    /**
     * The test class name of the task H1.6 using the name of task H1.5 {@value H1_5_CLASS_NAME}.
     */
    public static final String H1_6_CLASS_NAME = String.format("Test%s", H1_5_CLASS_NAME);
    /**
     * The test class full name of the task H1.6 using the name of task H1.5 {@value H1_5_CLASS_NAME}.
     */
    public static final String H1_6_CLASS_NAME_FULL = String.format("%s.%s", H1_PACKAGE_NAME, getTestClassName(H1_5_CLASS_NAME));
    /**
     * The test class used for testing purposes in task H1.6
     */
    public static final String H1_6_CLASS_NAME_1 = "Person";

    /**
     * The generic type parameters used in the tasks in H1.
     */
    public static final String H1_TYPE_PARAMETERS = "X, Y, Z";

    /**
     * The field name for the filter operation in task H1.1.
     */
    public static final String H1_1_FIELD_NAME_1 = "pred";
    /**
     * The field name for the map operation in task H1.1.
     */
    public static final String H1_1_FIELD_NAME_2 = "fct";
    /**
     * The field name for the fold operation in task H1.1.
     */
    public static final String H1_1_FIELD_NAME_3 = "op";
    /**
     * The field name for the initial value of the fold operation in task H1.1.
     */
    public static final String H1_1_FIELD_NAME_4 = "init";
    /**
     * The field name for accessing the functional operation in task H1.2.
     */
    public static final String H1_2_FIELD_NAME = "traits";
    /**
     * The field name for the combine operation in task H1.4. The field should be added in {@value H1_1_CLASS_NAME}.
     */
    public static final String H1_4_FIELD_NAME = "combine";
    /**
     * The field of the test class {@value H1_6_CLASS_NAME_1}.
     */
    public static final String H1_6_FIELD_NAME_1 = "lastName";
    /**
     * The field of the test class {@value H1_6_CLASS_NAME_1}.
     */
    public static final String H1_6_FIELD_NAME_2 = "firstName";
    /**
     * The field of the test class {@value H1_6_CLASS_NAME_1}.
     */
    public static final String H1_6_FIELD_NAME_3 = "street";
    /**
     * The field of the test class {@value H1_6_CLASS_NAME_1}.
     */
    public static final String H1_6_FIELD_NAME_4 = "houseNumber";
    /**
     * The field of the test class {@value H1_6_CLASS_NAME_1}.
     */
    public static final String H1_6_FIELD_NAME_5 = "postalCode";

    /**
     * The type of the field {@value H1_1_FIELD_NAME_1}.
     */
    public static final Class<?> H1_1_FIELD_TYPE_1 = Predicate.class;
    /**
     * The type of the field {@value H1_1_FIELD_NAME_2}.
     */
    public static final Class<?> H1_1_FIELD_TYPE_2 = Function.class;
    /**
     * The type of the field {@value H1_1_FIELD_NAME_3}.
     */
    public static final Class<?> H1_1_FIELD_TYPE_3 = BiFunction.class;
    /**
     * The type of the field {@value H1_1_FIELD_NAME_4}.
     */
    public static final Class<?> H1_1_FIELD_TYPE_4 = Object.class;
    /**
     * The type of the field {@value H1_4_FIELD_NAME}.
     */
    public static final Class<?> H1_4_FIELD_TYPE = BiFunction.class;
    /**
     * The type of the field {@value H1_6_FIELD_NAME_1}.
     */
    public static final Class<?> H1_6_FIELD_TYPE_1 = String.class;
    /**
     * The type of the field {@value H1_6_FIELD_NAME_2}.
     */
    public static final Class<?> H1_6_FIELD_TYPE_2 = String.class;
    /**
     * The type of the field {@value H1_6_FIELD_NAME_3}.
     */
    public static final Class<?> H1_6_FIELD_TYPE_3 = String.class;
    /**
     * The type of the field {@value H1_6_FIELD_NAME_4}.
     */
    public static final Class<?> H1_6_FIELD_TYPE_4 = int.class;
    /**
     * The type of the field {@value H1_6_FIELD_NAME_5}.
     */
    public static final Class<?> H1_6_FIELD_TYPE_5 = int.class;

    /**
     * The generic boundary of the field {@value H1_1_FIELD_NAME_1}.
     */
    public static final String H1_1_FIELD_TYPE_PARAMETER_1 = "? super X";
    /**
     * The generic boundary of the field {@value H1_1_FIELD_NAME_2}.
     */
    public static final String H1_1_FIELD_TYPE_PARAMETER_2 = "? super X, ? extends Y";
    /**
     * The generic boundary of the field {@value H1_1_FIELD_NAME_3}.
     */
    public static final String[] H1_1_FIELD_TYPE_PARAMETER_3 = {"Z, ? super Y, Z", "? super Z, ? super Y, Z",
        "? super Z, ? super Y, ? extends Z"};
    /**
     * The generic boundary of the field {@value H1_1_FIELD_NAME_4}.
     */
    public static final String H1_1_FIELD_TYPE_PARAMETER_4 = "Z";
    /**
     * The generic boundary of the field {@value H1_4_FIELD_NAME}.
     */
    public static final String[] H1_4_FIELD_TYPE_PARAMETER = {"Y, ? super Y, Y", "? super Y, ? super Y, Y",
        "? super Y, ? super Y, ? extends Y"};

    /**
     * The default example field value of {@value H1_1_FIELD_NAME_1}.
     */
    public static final Object H1_1_FIELD_EXAMPLE_DEFAULT_1 = (Predicate<Integer>) x -> true;
    /**
     * The default example field value of {@value H1_1_FIELD_NAME_2}.
     */
    public static final Object H1_1_FIELD_EXAMPLE_DEFAULT_2 = (Function<Integer, Integer>) x -> x;
    /**
     * The example field value of {@value H1_1_FIELD_NAME_3}.
     */
    public static final Object H1_1_FIELD_EXAMPLE_DEFAULT_3 = (BiFunction<Integer, Integer, Integer>)
        Integer::sum;
    /**
     * The example field value of {@value H1_1_FIELD_NAME_4}.
     */
    public static final Object H1_1_FIELD_EXAMPLE_DEFAULT_4 = 0;
    /**
     * The default result of the method {@value H1_2_METHOD_NAME}.
     */
    public static final Object H1_1_FIELD_EXAMPLE_DEFAULT_RESULT = 500500;

    /**
     * The example field value of {@value H1_1_FIELD_NAME_1}.
     */
    public static final Object H1_1_FIELD_EXAMPLE_2_1 = (Predicate<Integer>) x -> x % 2 == 0;
    /**
     * The example field value of {@value H1_1_FIELD_NAME_2}.
     */
    public static final Object H1_1_FIELD_EXAMPLE_2_2 = (Function<Integer, Integer>) x -> x + 1;
    /**
     * The example field value of {@value H1_1_FIELD_NAME_3}.
     */
    public static final Object H1_1_FIELD_EXAMPLE_2_3 = (BiFunction<Integer, Integer, Integer>)
        (a, b) -> a * b;
    /**
     * The example field value of {@value H1_1_FIELD_NAME_4}.
     */
    public static final Object H1_1_FIELD_EXAMPLE_2_4 = 1;
    /**
     * The example size of the array used for the functional operation used for the method {@value H1_2_METHOD_NAME}.
     */
    public static final int H1_1_FIELD_EXAMPLE_2_ARRAY_SIZE = 1000;

    /**
     * The operation to fill the example array used for the method {@value H1_2_METHOD_NAME}.
     */
    public static final BiConsumer<Integer, Integer[]> H1_1_FIELD_EXAMPLE_2_ARRAY_FILL =
        (i, elements) -> elements[i] = i + 1;
    /**
     * The result of the method {@value H1_2_METHOD_NAME}.
     */
    public static final Object H1_1_FIELD_EXAMPLE_2_RESULT = 603454833;

    /**
     * The example field value of {@value H1_1_FIELD_NAME_1}.
     */
    public static final Object H1_1_FIELD_EXAMPLE_3_1 = (Predicate<Integer>) x -> x % 2 == 0;
    /**
     * The example field value of {@value H1_1_FIELD_NAME_2}.
     */
    public static final Object H1_1_FIELD_EXAMPLE_3_2 = (Function<Integer, String>) String::valueOf;
    /**
     * The example field value of {@value H1_1_FIELD_NAME_3}.
     */
    public static final Object H1_1_FIELD_EXAMPLE_3_3 = (BiFunction<Integer, String, Integer>)
        (i, s) -> s.length() + i;
    /**
     * The example field value of {@value H1_1_FIELD_NAME_4}.
     */
    public static final Object H1_1_FIELD_EXAMPLE_3_4 = 523;
    /**
     * The example field value of {@value H1_4_FIELD_NAME}.
     */
    public static final Object H1_1_FIELD_EXAMPLE_3_5 = (BiFunction<String, String, String>)
        String::concat;

    /**
     * The result of the method {@value H1_2_METHOD_NAME}.
     */
    public static final Object H1_1_FIELD_EXAMPLE_3_RESULT = 3414;

    /**
     * The example field value of {@value H1_6_FIELD_NAME_1}.
     */
    public static final Object H1_6_FIELD_EXAMPLE_1 = "Rodriguez";
    /**
     * The example field value of {@value H1_6_FIELD_NAME_2}.
     */
    public static final Object H1_6_FIELD_EXAMPLE_2 = "Vanessa";
    /**
     * The example field value of {@value H1_6_FIELD_NAME_3}.
     */
    public static final Object H1_6_FIELD_EXAMPLE_3 = "Amselweg";
    /**
     * The example field value of {@value H1_6_FIELD_NAME_4}.
     */
    public static final Object H1_6_FIELD_EXAMPLE_4 = 52;
    /**
     * The example field value of {@value H1_6_FIELD_NAME_5}.
     */
    public static final Object H1_6_FIELD_EXAMPLE_5 = 64289;

    /**
     * The getter method of {@value H1_1_FIELD_NAME_1}.
     */
    public static final String H1_1_METHOD_NAME_1 = getGetterMethodName(H1_1_FIELD_NAME_1);
    /**
     * The getter method of {@value H1_1_FIELD_NAME_2}.
     */
    public static final String H1_1_METHOD_NAME_2 = getGetterMethodName(H1_1_FIELD_NAME_2);
    /**
     * The getter method of {@value H1_1_FIELD_NAME_3}.
     */
    public static final String H1_1_METHOD_NAME_3 = getGetterMethodName(H1_1_FIELD_NAME_3);
    /**
     * The getter method of {@value H1_1_FIELD_NAME_4}.
     */
    public static final String H1_1_METHOD_NAME_4 = getGetterMethodName(H1_1_FIELD_NAME_4);
    /**
     * The getter method of {@value H1_4_FIELD_NAME}.
     */
    public static final String H1_4_METHOD_NAME = getGetterMethodName(H1_4_FIELD_NAME);
    /**
     * The requirements that only one for each is used.
     */
    public static final Set<String> H1_4_REQUIREMENTS_CALLEES = Set.of("getPred", "getFct", "getOp", "getCombine", "getInit",
        "apply}");
    /**
     * The method specified in task H1.2.
     */
    public static final String H1_2_METHOD_NAME = "apply";
    /**
     * The method specified in task H1.5.
     */
    public static final String H1_5_METHOD_NAME_1 = "createFunctionWithFilterMapAndFold";
    /**
     * The method specified in task H1.5.
     */
    public static final String H1_5_METHOD_NAME_2 = "createFunctionWithFilterMapFoldAndCombine";
    /**
     * The test method of {@value H1_5_METHOD_NAME_1}.
     */
    public static final String H1_6_METHOD_NAME_1 = getTestMethodName(H1_5_METHOD_NAME_1) + 1;
    /**
     * The test method of {@value H1_5_METHOD_NAME_1}.
     */
    public static final String H1_6_METHOD_NAME_2 = getTestMethodName(H1_5_METHOD_NAME_1) + 2;
    /**
     * The test method of {@value H1_5_METHOD_NAME_2}.
     */
    public static final String H1_6_METHOD_NAME_3 = getTestMethodName(H1_5_METHOD_NAME_2);
    /**
     * The getter method of {@value H1_6_FIELD_NAME_1}.
     */
    public static final String H1_6_METHOD_NAME_4 = getGetterMethodName(H1_6_FIELD_NAME_1);
    /**
     * The getter method of {@value H1_6_FIELD_NAME_2}.
     */
    public static final String H1_6_METHOD_NAME_5 = getGetterMethodName(H1_6_FIELD_NAME_2);
    /**
     * The getter method of {@value H1_6_FIELD_NAME_3}.
     */
    public static final String H1_6_METHOD_NAME_6 = getGetterMethodName(H1_6_FIELD_NAME_3);
    /**
     * The getter method of {@value H1_6_FIELD_NAME_4}.
     */
    public static final String H1_6_METHOD_NAME_7 = getGetterMethodName(H1_6_FIELD_NAME_4);
    /**
     * The getter method of {@value H1_6_FIELD_NAME_5}.
     */
    public static final String H1_6_METHOD_NAME_8 = getGetterMethodName(H1_6_FIELD_NAME_5);
    /**
     * The setter method of {@value H1_6_FIELD_NAME_1}.
     */
    public static final String H1_6_METHOD_NAME_9 = getSetterMethodName(H1_6_FIELD_NAME_1);
    /**
     * The setter method of {@value H1_6_FIELD_NAME_2}.
     */
    public static final String H1_6_METHOD_NAME_10 = getSetterMethodName(H1_6_FIELD_NAME_2);
    /**
     * The setter method of {@value H1_6_FIELD_NAME_3}.
     */
    public static final String H1_6_METHOD_NAME_11 = getSetterMethodName(H1_6_FIELD_NAME_3);
    /**
     * The setter method of {@value H1_6_FIELD_NAME_4}.
     */
    public static final String H1_6_METHOD_NAME_12 = getSetterMethodName(H1_6_FIELD_NAME_4);
    /**
     * The setter method of {@value H1_6_FIELD_NAME_5}.
     */
    public static final String H1_6_METHOD_NAME_13 = getSetterMethodName(H1_6_FIELD_NAME_5);

    /**
     * The class parameter of {@value H1_2_METHOD_NAME}.
     */
    public static final Class<?> H1_2_METHOD_CLASS_PARAMETER = Object[].class;
    /**
     * The class return type of {@value H1_2_METHOD_NAME}.
     */
    public static final Class<?> H1_2_METHOD_CLASS_RETURN = Object.class;
    /**
     * The generic return type of {@value H1_2_METHOD_NAME}.
     */
    public static final String H1_2_METHOD_RETURN_TYPE_PARAMETER = "Z";

    /**
     * The lambda expression types of the test method 1 specified in H1.6.
     */
    public static final String[] H1_6_METHOD_1_LAMBDAS = {
        "java.util.function.Predicate<? super java.lang.Integer>",
        "java.util.function.Function<? super java.lang.Integer, ? extends java.lang.Integer>",
        "java.util.function.BiFunction<java.lang.Integer, ? super java.lang.Integer, java.lang.Integer>"
    };
    /**
     * The lambda expression types of the test method 2 specified in H1.6.
     */
    public static final String[] H1_6_METHOD_2_LAMBDAS = {
        "java\\.util\\.function\\.Predicate<? super java\\.lang\\.String>",
        "java\\.util\\.function\\.Function<? super java\\.lang\\.String, ? extends java\\.lang\\.Integer>",
        "java\\.util\\.function\\.BiFunction<.*java\\.lang\\.Boolean, ? super java\\.lang\\.Integer, .*java\\.lang\\.Boolean>"
    };
    /**
     * The lambda expression types of the test method 3 specified in H1.6.
     */
    public static final String[] H1_6_METHOD_3_LAMBDAS = {
        "java\\.util\\.function\\.Predicate<? super java\\.lang\\.String>",
        "java\\.util\\.function\\.Function<? super java\\.lang\\.String, ? extends java\\.lang\\.Integer>",
        "java\\.util\\.function\\.BiFunction<.*java\\.lang\\.Boolean, ? super java\\.lang.Integer, .*java\\.lang\\.Boolean>"
    };

    /* *********************************************************************
     *                                  H1                                 *
     **********************************************************************/

    /**
     * The package name of the task H2.
     */
    public static final String H2_PACKAGE_NAME = "h09.h2";

    /**
     * The animal class name of the task H2.1.
     */
    public static final String H2_1_CLASS_NAME_1 = "Animal";
    /**
     * The animal class name of the task H2.1.
     */
    public static final String H2_1_CLASS_NAME_2 = "Vertebrate";
    /**
     * The animal class name of the task H2.1.
     */
    public static final String H2_1_CLASS_NAME_3 = "Mammal";
    /**
     * The animal class name of the task H2.1.
     */
    public static final String H2_1_CLASS_NAME_4 = "Bird";
    /**
     * The animal class name of the task H2.1.
     */
    public static final String H2_1_CLASS_NAME_5 = "Placental";
    /**
     * The animal class name of the task H2.1.
     */
    public static final String H2_1_CLASS_NAME_6 = "Monotreme";
    /**
     * The animal class name of the task H2.1.
     */
    public static final String H2_1_CLASS_NAME_7 = "Lagomorpha";
    /**
     * The animal class name of the task H2.1.
     */
    public static final String H2_1_CLASS_NAME_8 = "Rodent";
    /**
     * The animal class name of the task H2.1.
     */
    public static final String H2_1_CLASS_NAME_9 = "Leporidae";
    /**
     * The animal class name of the task H2.1.
     */
    public static final String H2_1_CLASS_NAME_10 = "Rabbit";
    /**
     * The class names of the task H2.2.
     */
    public static final String[] H2_2_CLASS_NAME = {"BiologyHierarchy", "BiologyHierachy"};
    /**
     * The class names full of the task H2.2.
     */
    public static final String[] H2_2_CLASS_NAME_FULL = {String.format("%s.BiologyHierarchy", H2_PACKAGE_NAME),
        String.format("%s.BiologyHierachy", H2_PACKAGE_NAME)};

    /**
     * The tutor class transformer for the class in task H2.2.
     */
    public static final String H2_2_CLASS_NAME_TRANSFORMER = String.format("%s.%s", H2_PACKAGE_NAME,
        getTutorTestClassName("BiologyHierarchy")).replaceAll("\\.", "/");
    /**
     * The test class name of the task H2.3 for {@link #H2_2_CLASS_NAME}.
     */
    public static final String H2_3_CLASS_NAME = "TestBiologicalHierarchy";
    /**
     * The full class names of the task H2.3.
     */
    public static final String H2_3_CLASS_NAME_FULL = String.format("h09.h2.%s", H2_3_CLASS_NAME);

    /**
     * The animal test class name of the task H2.3 for {@value H2_1_CLASS_NAME_2}.
     */
    public static final String H2_3_CLASS_NAME_1 = getTestClassName(H2_1_CLASS_NAME_1);
    /**
     * The animal test class name of the task H2.3 for {@value H2_1_CLASS_NAME_2}.
     */
    public static final String H2_3_CLASS_NAME_2 = getTestClassName(H2_1_CLASS_NAME_2);
    /**
     * The animal test class name of the task H2.3 for {@value H2_1_CLASS_NAME_3}.
     */
    public static final String H2_3_CLASS_NAME_3 = getTestClassName(H2_1_CLASS_NAME_3);
    /**
     * The animal test class name of the task H2.3 for {@value H2_1_CLASS_NAME_4}.
     */
    public static final String H2_3_CLASS_NAME_4 = getTestClassName(H2_1_CLASS_NAME_4);
    /**
     * The animal test class name of the task H2.3 for {@value H2_1_CLASS_NAME_5}.
     */
    public static final String H2_3_CLASS_NAME_5 = getTestClassName(H2_1_CLASS_NAME_5);
    /**
     * The animal test class name of the task H2.3 for {@value H2_1_CLASS_NAME_6}.
     */
    public static final String H2_3_CLASS_NAME_6 = getTestClassName(H2_1_CLASS_NAME_6);
    /**
     * The animal test class name of the task H2.3 for {@value H2_1_CLASS_NAME_7}.
     */
    public static final String H2_3_CLASS_NAME_7 = getTestClassName(H2_1_CLASS_NAME_7);
    /**
     * The animal test class name of the task H2.3 for {@value H2_1_CLASS_NAME_8}.
     */
    public static final String H2_3_CLASS_NAME_8 = getTestClassName(H2_1_CLASS_NAME_8);
    /**
     * The animal test class name of the task H2.3 for {@value H2_1_CLASS_NAME_9}.
     */
    public static final String H2_3_CLASS_NAME_9 = getTestClassName(H2_1_CLASS_NAME_9);
    /**
     * The animal test class name of the task H2.3 for {@value H2_1_CLASS_NAME_10}.
     */
    public static final String H2_3_CLASS_NAME_10 = getTestClassName(H2_1_CLASS_NAME_10);
    /**
     * The animal test class name of the task H2.3 for {@value H2_1_CLASS_NAME_1}.
     */
    public static final String H2_3_CLASS_NAME_TUTOR_1 = getTutorTestClassName(H2_3_CLASS_NAME_1);
    /**
     * The animal test class name of the task H2.3 for {@value H2_1_CLASS_NAME_2}.
     */
    public static final String H2_3_CLASS_NAME_TUTOR_2 = getTutorTestClassName(H2_3_CLASS_NAME_2);
    /**
     * The animal test class name of the task H2.3 for {@value H2_1_CLASS_NAME_3}.
     */
    public static final String H2_3_CLASS_NAME_TUTOR_3 = getTutorTestClassName(H2_3_CLASS_NAME_3);
    /**
     * The animal test class name of the task H2.3 for {@value H2_1_CLASS_NAME_4}.
     */
    public static final String H2_3_CLASS_NAME_TUTOR_4 = getTutorTestClassName(H2_3_CLASS_NAME_4);
    /**
     * The animal test class name of the task H2.3 for {@value H2_1_CLASS_NAME_5}.
     */
    public static final String H2_3_CLASS_NAME_TUTOR_5 = getTutorTestClassName(H2_3_CLASS_NAME_5);
    /**
     * The animal test class name of the task H2.3 for {@value H2_1_CLASS_NAME_6}.
     */
    public static final String H2_3_CLASS_NAME_TUTOR_6 = getTutorTestClassName(H2_3_CLASS_NAME_6);
    /**
     * The animal test class name of the task H2.3 for {@value H2_1_CLASS_NAME_7}.
     */
    public static final String H2_3_CLASS_NAME_TUTOR_7 = getTutorTestClassName(H2_3_CLASS_NAME_7);
    /**
     * The animal test class name of the task H2.3 for {@value H2_1_CLASS_NAME_8}.
     */
    public static final String H2_3_CLASS_NAME_TUTOR_8 = getTutorTestClassName(H2_3_CLASS_NAME_8);
    /**
     * The animal test class name of the task H2.3 for {@value H2_1_CLASS_NAME_9}.
     */
    public static final String H2_3_CLASS_NAME_TUTOR_9 = getTutorTestClassName(H2_3_CLASS_NAME_9);
    /**
     * The animal test class name of the task H2.3 for {@value H2_1_CLASS_NAME_10}.
     */
    public static final String H2_3_CLASS_NAME_TUTOR_10 = getTutorTestClassName(H2_3_CLASS_NAME_10);

    /**
     * The method name of the class {@value H2_1_CLASS_NAME_1}.
     */
    public static final String H2_1_METHOD_NAME_1 = getTypeOfXMethodName(H2_1_CLASS_NAME_1);
    /**
     * The method name of the class {@value H2_1_CLASS_NAME_2}.
     */
    public static final String H2_1_METHOD_NAME_2 = getTypeOfXMethodName(H2_1_CLASS_NAME_2);
    /**
     * The method name of the class {@value H2_1_CLASS_NAME_3}.
     */
    public static final String H2_1_METHOD_NAME_3 = getTypeOfXMethodName(H2_1_CLASS_NAME_3);
    /**
     * The method name of the class {@value H2_1_CLASS_NAME_4}.
     */
    public static final String H2_1_METHOD_NAME_4 = getTypeOfXMethodName(H2_1_CLASS_NAME_4);
    /**
     * The method name of the class {@value H2_1_CLASS_NAME_5}.
     */
    public static final String H2_1_METHOD_NAME_5 = getTypeOfXMethodName(H2_1_CLASS_NAME_5);
    /**
     * The method name of the class {@value H2_1_CLASS_NAME_6}.
     */
    public static final String H2_1_METHOD_NAME_6 = getTypeOfXMethodName(H2_1_CLASS_NAME_6);
    /**
     * The method name of the class {@value H2_1_CLASS_NAME_7}.
     */
    public static final String H2_1_METHOD_NAME_7 = getTypeOfXMethodName(H2_1_CLASS_NAME_7);
    /**
     * The method name of the class {@value H2_1_CLASS_NAME_8}.
     */
    public static final String H2_1_METHOD_NAME_8 = getTypeOfXMethodName(H2_1_CLASS_NAME_8);
    /**
     * The method name of the class {@value H2_1_CLASS_NAME_9}.
     */
    public static final String H2_1_METHOD_NAME_9 = "getNameOfIndividual";
    /**
     * The method name 1 of the task 2.2.
     */
    public static final String H2_2_METHOD_NAME_1 = "getTypeOfVertebrate";
    /**
     * The method name 2 of the task 2.2.
     */
    public static final String H2_2_METHOD_NAME_2 = "filterLagomorphs";
    /**
     * The method name 3 of the task 2.2.
     */
    public static final String H2_2_METHOD_NAME_3 = "getTypesOfMammals";
    /**
     * The test method name of {@value H2_2_METHOD_NAME_1}.
     */
    public static final String H2_3_METHOD_NAME_1 = getTestMethodName(H2_2_METHOD_NAME_1);
    /**
     * The test method name of {@value H2_2_METHOD_NAME_2}.
     */
    public static final String H2_3_METHOD_NAME_2 = getTestMethodName(H2_2_METHOD_NAME_2);
    /**
     * The test method name of {@value H2_2_METHOD_NAME_3}.
     */
    public static final String H2_3_METHOD_NAME_3 = getTestMethodName(H2_2_METHOD_NAME_3);

    /**
     * The method parameter type of {@value H2_2_METHOD_NAME_2}.
     */
    public static final Class<?> H2_2_METHOD_CLASS_PARAMETER_2 = List.class;
    /**
     * The method parameter type of {@value H2_2_METHOD_NAME_3}.
     */
    public static final Class<?> H2_2_METHOD_CLASS_PARAMETER_3 = List.class;
    /**
     * The method parameter generic boundary of {@value H2_2_METHOD_NAME_1}.
     */
    public static final String H2_2_METHOD_BOUND_1 = "T";
    /**
     * The method parameter generic boundary of {@value H2_2_METHOD_NAME_2}.
     */
    public static final String H2_2_METHOD_BOUND_2 = String.format("? super %s.%s", H2_PACKAGE_NAME,
        H2_1_CLASS_NAME_7);
    /**
     * The method parameter generic boundary of {@value H2_2_METHOD_NAME_3}.
     */
    public static final String H2_2_METHOD_BOUND_3 = String.format("? extends %s.%s", H2_PACKAGE_NAME,
        H2_1_CLASS_NAME_3);

    /**
     * All animals of the task H2.1.
     */
    public static final Map<String, String> H2_1_ANIMAL_CLASS_AND_METHOD = Map.of(
        TutorConstants.H2_1_CLASS_NAME_1, TutorConstants.H2_1_METHOD_NAME_1,
        TutorConstants.H2_1_CLASS_NAME_2, TutorConstants.H2_1_METHOD_NAME_2,
        TutorConstants.H2_1_CLASS_NAME_3, TutorConstants.H2_1_METHOD_NAME_3,
        TutorConstants.H2_1_CLASS_NAME_4, TutorConstants.H2_1_METHOD_NAME_4,
        TutorConstants.H2_1_CLASS_NAME_5, TutorConstants.H2_1_METHOD_NAME_5,
        TutorConstants.H2_1_CLASS_NAME_6, TutorConstants.H2_1_METHOD_NAME_6,
        TutorConstants.H2_1_CLASS_NAME_7, TutorConstants.H2_1_METHOD_NAME_7,
        TutorConstants.H2_1_CLASS_NAME_8, TutorConstants.H2_1_METHOD_NAME_8,
        TutorConstants.H2_1_CLASS_NAME_9, TutorConstants.H2_1_METHOD_NAME_9
    );

    /**
     * The inheritance of the animals in task H2.1.
     */
    public static final Map<String, String[]> H2_1_ANIMAL_CLASS_AND_INHERITANCE = Map.of(
        TutorConstants.H2_1_CLASS_NAME_1, new String[]{},
        TutorConstants.H2_1_CLASS_NAME_2, new String[]{TutorConstants.H2_1_CLASS_NAME_1},
        TutorConstants.H2_1_CLASS_NAME_3, new String[]{TutorConstants.H2_1_CLASS_NAME_2},
        TutorConstants.H2_1_CLASS_NAME_4, new String[]{TutorConstants.H2_1_CLASS_NAME_2},
        TutorConstants.H2_1_CLASS_NAME_5, new String[]{TutorConstants.H2_1_CLASS_NAME_3},
        TutorConstants.H2_1_CLASS_NAME_6, new String[]{TutorConstants.H2_1_CLASS_NAME_3},
        TutorConstants.H2_1_CLASS_NAME_7, new String[]{TutorConstants.H2_1_CLASS_NAME_5},
        TutorConstants.H2_1_CLASS_NAME_8, new String[]{TutorConstants.H2_1_CLASS_NAME_7},
        TutorConstants.H2_1_CLASS_NAME_9, new String[]{TutorConstants.H2_1_CLASS_NAME_8}
    );

    /**
     * The return type of the method {@value H2_2_METHOD_NAME_1}.
     */
    public static final Class<?> H2_2_METHOD_RETURN_TYPE_1 = String.class;
    /**
     * The return type of the method {@value H2_2_METHOD_NAME_2}.
     */
    public static final Class<?> H2_2_METHOD_RETURN_TYPE_2 = List.class;
    /**
     * The return type of the method {@value H2_2_METHOD_NAME_3}.
     */
    public static final Class<?> H2_2_METHOD_RETURN_TYPE_3 = List.class;

    /**
     * Assertion message if a JUnit test fail.
     */
    public static final String ASSERTION_FAILED = "JUnit Assertion failed!";

    /**
     * Byte code flag for constructor calls.
     */
    public static final String BYTE_CODE_FLAG_CONSTRUCTOR_CALL = "<init>";
}
