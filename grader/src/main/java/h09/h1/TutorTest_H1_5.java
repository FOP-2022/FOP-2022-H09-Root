package h09.h1;

import h09.utils.Modifier;
import h09.utils.TutorConstants;
import h09.utils.TutorMessage;
import h09.utils.TutorUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.lang.reflect.Method;

/**
 * Defines the JUnit test cases related to the class defined in the task H1.5.
 *
 * @author Nhan Huynh, Darya Nikitina
 */
@TestForSubmission("h09")
@DisplayName("Criterion: Class FunctionFactory")
public final class TutorTest_H1_5 {

    /* *********************************************************************
     *                            Utilities                                *
     **********************************************************************/

    /**
     * Returns the class instance of the class that should be tested.
     *
     * @return the class instance of the test class
     */
    private static Class<?> getTestClass() {
        return TutorUtils.assertClass(TutorConstants.H1_PACKAGE_NAME, TutorConstants.H1_5_CLASS_NAME);
    }

    /**
     * Returns the class instance of the parameter that should be tested.
     *
     * @return the class instance of the parameter
     */
    private static Class<?> getTestClassParameter() {
        return TutorUtils.assertClass(TutorConstants.H1_PACKAGE_NAME, TutorConstants.H1_1_CLASS_NAME);
    }

    /**
     * Checks if the method contains the specified parameter type.
     *
     * @param classParameter the class type of the parameter
     * @param method         the method to test
     */
    private static void assertParameters(final Class<?> classParameter, final Method method) {
        final var types = method.getParameterTypes();

        // Check number of parameters
        final var expectedLength = 1;
        final var actualLength = types.length;
        Assertions.assertEquals(expectedLength, actualLength,
            TutorMessage.METHOD_PARAMETER_MISMATCH_SIZE.format(method.getName(), expectedLength,
                actualLength));

        // Check type of parameters
        final var actualClass = types[0];
        Assertions.assertEquals(classParameter, actualClass,
            TutorMessage.METHOD_PARAMETER_MISMATCH.format(method.getName(), classParameter,
                actualClass));

        // Check generic types
        final var parametrized = method.getParameters()[0].getParameterizedType();
        TutorUtils.assertGenericType(parametrized, classParameter, TutorConstants.H1_TYPE_PARAMETERS);
    }

    /**
     * Tests if the method contains the specified return type.
     *
     * @param method the method to test
     */
    private static void assertReturnType(final Method method) {
        final var expected = TutorUtils.assertClass(TutorConstants.H1_PACKAGE_NAME,
            TutorConstants.H1_2_CLASS_NAME);

        final var actual = method.getReturnType();
        Assertions.assertEquals(expected, actual, TutorMessage.RETURN_TYPE_MISMATCH.format(expected, actual));
        TutorUtils.assertGenericType(method.getGenericReturnType(), expected, TutorConstants.H1_TYPE_PARAMETERS);
    }

    /* *********************************************************************
     *                              General                                *
     **********************************************************************/

    @Test
    @DisplayName("Criterion: Original package name found")
    public void testPackage() {
        final var alternative = TutorUtils.assertPackage(TutorConstants.H1_PACKAGE_NAME, TutorConstants.H1_5_CLASS_NAME);
        Assertions.assertTrue(alternative, TutorMessage.PACKAGE_NAME_ALTERNATIVE.format(TutorConstants.H1_PACKAGE_NAME,
            TutorConstants.H1_5_CLASS_NAME)
        );
    }

    /* *********************************************************************
     *                            Class Header                             *
     **********************************************************************/

    /**
     * Defines the JUnit test cases related to the class header.
     */
    @Nested
    @DisplayName("Criterion: Class Header")
    public final class TestClassHeader {

        @Test
        @DisplayName("Criterion: Only modifiers public final")
        public void testModifiers() {
            final var actual = getTestClass();
            final var expected = Modifier.STATIC.nand(Modifier.ABSTRACT).and(Modifier.PUBLIC,
                Modifier.FINAL);
            TutorUtils.assertModifiers(expected, actual);
        }

        @Test
        @DisplayName("Criterion: No type parameter")
        public void testTypeParameter() {
            final var clazz = getTestClass();
            final var types = clazz.getTypeParameters();
            final var expected = 0;
            final var actual = types.length;
            Assertions.assertEquals(
                expected, actual,
                TutorMessage.CLASS_TYPE_PARAMETER_MISMATCH_SIZE.format(clazz.getSimpleName(), expected, actual)
            );
        }
    }

    /* *********************************************************************
     *                            Constructor                              *
     **********************************************************************/

    /**
     * Defines the JUnit test cases related to the constructor.
     */
    @Nested
    @DisplayName("Criterion: Constructor")
    public final class TestConstructor {

        @Test
        @DisplayName("Criterion: Only modifier private")
        public void testModifiers() {
            final var clazz = getTestClass();
            final var actual = TutorUtils.assertConstructor(clazz);
            final var expected = Modifier.PRIVATE;
            TutorUtils.assertModifiers(expected, actual);
        }
    }

    /* *********************************************************************
     *                            Method 1                                 *
     **********************************************************************/

    /**
     * Defines the JUnit test cases related to the method {@value TutorConstants#H1_5_METHOD_NAME_1}.
     */
    @Nested
    @DisplayName("Criterion: Method createFunctionWithFilterMapAndFold")
    public final class TestMethod1 {

        /**
         * Returns the method that should be tested.
         *
         * @return the method that should be tested
         */
        private Method getTestMethod() {
            final var clazz = getTestClass();
            final var parameters = getTestClassParameter();
            return TutorUtils.assertMethod(clazz, TutorConstants.H1_5_METHOD_NAME_1, parameters);
        }

        @Test
        @DisplayName("Criterion: Only modifiers public static")
        public void testModifiers() {
            final var actual = getTestMethod();
            final var expected = Modifier.PUBLIC.and(Modifier.STATIC);
            TutorUtils.assertModifiers(expected, actual);
        }

        @Test
        @DisplayName("Criterion: Parameter Traits<X, Y, Z>")
        public void testParameters() {
            final var classParameter = getTestClassParameter();
            final var method = getTestMethod();
            assertParameters(classParameter, method);
        }

        @Test
        @DisplayName("Criterion: Return type FunctionWithFilterMapAndFold<X, Y, Z>")
        public void testReturnType() {
            assertReturnType(getTestMethod());
        }

        @Test
        @DisplayName("Criterion: Return value MyFunctionWithFilterMapAndFold<X, Y, Z>")
        public void testReturnValue() {
            final var expectedField1 = TutorConstants.H1_1_FIELD_EXAMPLE_3_1;
            final var expectedField2 = TutorConstants.H1_1_FIELD_EXAMPLE_3_2;
            final var expectedField3 = TutorConstants.H1_1_FIELD_EXAMPLE_3_3;
            final var expectedField4 = TutorConstants.H1_1_FIELD_EXAMPLE_3_4;

            final var parameterClass = getTestClassParameter();
            final var parameterConstructor = TutorUtils.assertConstructor(parameterClass,
                TutorConstants.H1_1_FIELD_TYPE_1, TutorConstants.H1_1_FIELD_TYPE_2,
                TutorConstants.H1_1_FIELD_TYPE_3, TutorConstants.H1_1_FIELD_TYPE_4);

            final var parameterInstance = TutorUtils.invokeConstructor(parameterConstructor, expectedField1,
                expectedField2, expectedField3, expectedField4);

            // Invoke Method
            final var method = getTestMethod();

            final var actual = TutorUtils.invokeMethod(method, null, parameterInstance);
            final var expected = TutorUtils.assertClass(TutorConstants.H1_PACKAGE_NAME,
                TutorConstants.H1_3_CLASS_NAME);
            final var actualClass = actual.getClass();
            Assertions.assertEquals(expected, actualClass,
                TutorMessage.RETURN_VALUE_MISMATCH.format(method.getName(), expected, actualClass));
        }
    }

    /* *********************************************************************
     *                            Method 2                                 *
     **********************************************************************/

    /**
     * Defines the JUnit test cases related to the method {@value  TutorConstants#H1_5_METHOD_NAME_2}.
     */
    @Nested
    @DisplayName("Criterion: Method createFunctionWithFilterMapFoldAndCombine")
    public final class TestMethod2 {

        /**
         * Returns the method that should be tested.
         *
         * @return the method that should be tested
         */
        private Method getTestMethod() {
            final var clazz = getTestClass();
            final var parameters = getTestClassParameter();
            return TutorUtils.assertMethod(clazz, TutorConstants.H1_5_METHOD_NAME_2, parameters);
        }

        @Test
        @DisplayName("Criterion: Only modifiers public static")
        public void testModifiers() {
            final var actual = getTestMethod();
            final var expected = Modifier.PUBLIC.and(Modifier.STATIC);
            TutorUtils.assertModifiers(expected, actual);
        }

        @Test
        @DisplayName("Criterion: Parameter Traits<X, Y, Z>")
        public void testParameters() {
            final var classParameter = getTestClassParameter();
            final var method = getTestMethod();
            assertParameters(classParameter, method);
        }

        @Test
        @DisplayName("Criterion: Return type FunctionWithFilterMapAndFold<X, Y, Z>")
        public void testReturnType() {
            assertReturnType(getTestMethod());
        }

        @Test
        @DisplayName("Criterion: Return value MyFunctionWithAdjacent<X, Y, Z>")
        public void testReturnValue() {
            final var expectedField1 = TutorConstants.H1_1_FIELD_EXAMPLE_3_1;
            final var expectedField2 = TutorConstants.H1_1_FIELD_EXAMPLE_3_2;
            final var expectedField3 = TutorConstants.H1_1_FIELD_EXAMPLE_3_3;
            final var expectedField4 = TutorConstants.H1_1_FIELD_EXAMPLE_3_4;
            final var expectedField5 = TutorConstants.H1_1_FIELD_EXAMPLE_3_5;

            final var parameterClass = getTestClassParameter();
            final var parameterConstructor = TutorUtils.assertConstructor(parameterClass,
                TutorConstants.H1_1_FIELD_TYPE_1, TutorConstants.H1_1_FIELD_TYPE_2,
                TutorConstants.H1_1_FIELD_TYPE_3, TutorConstants.H1_1_FIELD_TYPE_4,
                TutorConstants.H1_4_FIELD_TYPE);

            final var parameterInstance = TutorUtils.invokeConstructor(parameterConstructor, expectedField1,
                expectedField2, expectedField3, expectedField4, expectedField5);

            // Invoke Method
            final var method = getTestMethod();

            final var actual = TutorUtils.invokeMethod(method, null, parameterInstance);
            final var expected = TutorUtils.assertClass(TutorConstants.H1_PACKAGE_NAME,
                TutorConstants.H1_4_CLASS_NAME);
            final var actualClass = actual.getClass();
            Assertions.assertEquals(expected, actualClass,
                TutorMessage.RETURN_VALUE_MISMATCH.format(method.getName(), expected, actualClass));
        }
    }
}
