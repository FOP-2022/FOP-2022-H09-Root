package h09.h2;

import h09.utils.Modifier;
import h09.utils.TutorConstants;
import h09.utils.TutorMessage;
import h09.utils.TutorUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * Defines the JUnit test cases related to the class defined in the task H2.2.
 *
 * @author Nhan Huynh, Darya Nikitina
 */
@TestForSubmission("h09")
@DisplayName("Criterion: Class BiologyHierarchy")
public final class TutorTest_H2_2 {

    /* *********************************************************************
     *                            Utilities                                *
     **********************************************************************/

    /**
     * Returns the class instance of the class that should be tested.
     *
     * @return the class instance of the test class
     */
    private static Class<?> getTestClass() {
        return TutorUtils.assertClass(TutorConstants.H2_PACKAGE_NAME, TutorConstants.H2_2_CLASS_NAME);
    }

    /**
     * Returns the constructor of the class that should be tested.
     *
     * @return the constructor of the test class
     */
    private static Constructor<?> getTestConstructor() {
        final var clazz = getTestClass();
        return TutorUtils.assertConstructor(clazz);
    }

    /**
     * Returns the test animal to test the methods.
     *
     * @param animal the animal name
     *
     * @return the animal instance
     */
    private static Object getTestAnimal(final String animal) {
        final var clazz = TutorUtils.assertClass(TutorConstants.H2_PACKAGE_NAME, animal);
        final var constructor = TutorUtils.assertConstructor(clazz);
        return TutorUtils.invokeConstructor(constructor);
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
        @DisplayName("Criterion: Only modifiers public")
        public void testModifiers() {
            final var actual = getTestClass();
            final var expected = Modifier.STATIC.nand(Modifier.ABSTRACT, Modifier.FINAL)
                .and(Modifier.PUBLIC);
            TutorUtils.assertModifiers(expected, actual);
        }

        @Test
        @DisplayName("Criterion: No type parameter")
        public void testTypeParameter() {
            final var clazz = getTestClass();
            final var types = clazz.getTypeParameters();
            final var expected = 0;
            final var actual = types.length;
            Assertions.assertEquals(expected, actual,
                TutorMessage.CLASS_TYPE_PARAMETER_MISMATCH_SIZE.format(clazz.getSimpleName(), expected,
                    actual));
        }
    }

    /* *********************************************************************
     *                              Method 1                               *
     **********************************************************************/

    /**
     * Defines the JUnit test cases related to the method {@value TutorConstants#H2_2_METHOD_NAME_1}.
     */
    @Nested
    @DisplayName("Criterion: Method getTypeOfVertebrate")
    public final class TestMethod1 {

        /**
         * Returns the method that should be tested.
         *
         * @return the method that should be tested
         */
        private Method getTestMethod() {
            final var clazz = getTestClass();
            final var parameter = getParameterClass();
            return TutorUtils.assertMethod(clazz, TutorConstants.H2_2_METHOD_NAME_1, parameter);
        }

        /**
         * Returns the class instance of the parameter should be tested.
         *
         * @return the class instance of the parameter that should be tested
         */
        private Class<?> getParameterClass() {
            return TutorUtils.assertClass(TutorConstants.H2_PACKAGE_NAME,
                TutorConstants.H2_2_METHOD_TYPE_PARAMETER_1);
        }

        @Test
        @DisplayName("Criterion: Only modifiers public")
        public void testModifiers() {
            final var actual = getTestMethod();
            final var expected = Modifier.STATIC.nand(Modifier.FINAL, Modifier.ABSTRACT)
                .and(Modifier.PUBLIC);
            TutorUtils.assertModifiers(expected, actual);
        }

        @Test
        @DisplayName("Criterion: Generic method <T extends Vertebrate>")
        public void testGeneric() {
            final var method = getTestMethod();
            final var types = method.getTypeParameters();

            // Check generic type name
            final var expectedType = TutorConstants.H2_2_METHOD_BOUND_1;
            final var actualType = types[0].getTypeName();
            Assertions.assertEquals(expectedType, actualType,
                TutorMessage.TYPE_PARAMETER_MISMATCH.format(expectedType, actualType));

            // Check bound
            final var actualBound = types[0].getBounds();
            final var expectedLength = 1;
            final var actualLength = actualBound.length;

            Assertions.assertEquals(expectedLength, actualLength,
                TutorMessage.TYPE_PARAMETERS_MISMATCH_SIZE.format(expectedLength, actualLength));

            // Check bound type
            final var parameter = getParameterClass();
            final var expectedBoundType = parameter.getName();
            final var actualBoundType = actualBound[0].getTypeName();
            Assertions.assertEquals(expectedBoundType, actualBoundType,
                TutorMessage.TYPE_PARAMETER_MISMATCH.format(expectedBoundType, actualBoundType));
        }

        @Test
        @DisplayName("Criterion: Parameter T")
        public void testParameters() {
            final var method = getTestMethod();
            final var parameters = method.getGenericParameterTypes();

            // Check parameter
            final var expectedParameters = 1;
            final var actualParameters = parameters.length;
            Assertions.assertEquals(expectedParameters, actualParameters,
                TutorMessage.METHOD_PARAMETER_MISMATCH_SIZE.format(method.getName(), expectedParameters,
                    actualParameters));

            // Check type
            final var expectedType = TutorConstants.H2_2_METHOD_BOUND_1;
            final var actualType = parameters[0].getTypeName();
            Assertions.assertEquals(expectedType, actualType,
                TutorMessage.METHOD_PARAMETER_MISMATCH.format(method.getName(), expectedType, actualType));
        }

        @Test
        @DisplayName("Criterion: Result")
        public void testResult() {
            final var constructor = getTestConstructor();
            final var instance = TutorUtils.invokeConstructor(constructor);
            final var method = getTestMethod();
            Assertions.assertNull(
                TutorUtils.invokeMethod(method, instance, getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_2)));
            Assertions.assertEquals(TutorConstants.H2_1_CLASS_NAME_3,
                TutorUtils.invokeMethod(method, instance,
                    getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_3)));
            Assertions.assertEquals(TutorConstants.H2_1_CLASS_NAME_4,
                TutorUtils.invokeMethod(method, instance,
                    getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_4)));
            Assertions.assertEquals(TutorConstants.H2_1_CLASS_NAME_3,
                TutorUtils.invokeMethod(method, instance,
                    getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_5)));
            Assertions.assertEquals(TutorConstants.H2_1_CLASS_NAME_3,
                TutorUtils.invokeMethod(method, instance,
                    getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_6)));
            Assertions.assertEquals(TutorConstants.H2_1_CLASS_NAME_3,
                TutorUtils.invokeMethod(method, instance,
                    getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_7)));
        }
    }

    /* *********************************************************************
     *                              Method 2                               *
     **********************************************************************/

    /**
     * Defines the JUnit test cases related to the method {@value TutorConstants#H2_2_METHOD_NAME_1}.
     */
    @Nested
    @DisplayName("Criterion: Method filterLagomorphs")
    public final class TestMethod2 {

        /**
         * Returns the method that should be tested.
         *
         * @return the method that should be tested
         */
        private Method getTestMethod() {
            final var clazz = getTestClass();
            return TutorUtils.assertMethod(clazz, TutorConstants.H2_2_METHOD_NAME_2,
                TutorConstants.H2_2_METHOD_CLASS_PARAMETER_2);
        }

        @Test
        @DisplayName("Criterion: Only modifiers public")
        public void testModifiers() {
            final var actual = getTestMethod();
            final var expected = Modifier.STATIC.nand(Modifier.FINAL, Modifier.ABSTRACT)
                .and(Modifier.PUBLIC);
            TutorUtils.assertModifiers(expected, actual);
        }

        @Test
        @DisplayName("Criterion: Parameter List<? super Lagomorph>")
        public void testParameters() {
            final var method = getTestMethod();
            final var parameters = method.getGenericParameterTypes();

            // Check parameter
            final var expectedParameters = 1;
            final var actualParameters = parameters.length;
            Assertions.assertEquals(expectedParameters, actualParameters,
                TutorMessage.METHOD_PARAMETER_MISMATCH_SIZE.format(method.getName(), expectedParameters,
                    actualParameters));

            // Check type
            final var expectedType = TutorConstants.H2_2_METHOD_BOUND_2;
            final var actualType = parameters[0];
            TutorUtils.assertGenericType(actualType, TutorConstants.H2_2_METHOD_CLASS_PARAMETER_2, expectedType);
        }

        @Test
        @DisplayName("Criterion: Result List<Placental>")
        public void testResultListOfPlacental() {
            final var constructor = getTestConstructor();
            final var instance = TutorUtils.invokeConstructor(constructor);
            final var method = getTestMethod();

            final var placental = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_5);
            final var rodent = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_8);
            final var lagomorpha = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_7);
            final var leporidae = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_9);

            final var animals = List.of(
                placental,
                rodent,
                lagomorpha,
                leporidae
            );
            final var expected = List.of(
                lagomorpha,
                leporidae
            );

            Assertions.assertEquals(expected,
                TutorUtils.invokeMethod(method, instance, animals));
        }

        @Test
        @DisplayName("Criterion: Result List<Mammal>")
        public void testResultListOfMammal() {
            final var constructor = getTestConstructor();
            final var instance = TutorUtils.invokeConstructor(constructor);
            final var method = getTestMethod();

            final var mammal = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_3);
            final var placental = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_5);
            final var monotreme = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_6);
            final var rodent = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_8);
            final var lagomorpha = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_7);
            final var leporidae = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_9);

            final var animals = List.of(
                mammal,
                placental,
                monotreme,
                rodent,
                lagomorpha,
                leporidae
            );

            final var expected = List.of(
                lagomorpha,
                leporidae
            );

            Assertions.assertEquals(expected,
                TutorUtils.invokeMethod(method, instance, animals));
        }

        @Test
        @DisplayName("Criterion: Result List<Vertebrate>")
        public void testResultListOfVertebrate() {
            final var constructor = getTestConstructor();
            final var instance = TutorUtils.invokeConstructor(constructor);
            final var method = getTestMethod();

            final var vertebrate = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_2);
            final var mammal = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_3);
            final var bird = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_4);
            final var placental = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_5);
            final var monotreme = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_6);
            final var rodent = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_8);
            final var lagomorpha = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_7);
            final var leporidae = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_9);

            final var animals = List.of(
                vertebrate,
                mammal,
                bird,
                placental,
                monotreme,
                rodent,
                lagomorpha,
                leporidae
            );
            final var expected = List.of(
                lagomorpha,
                leporidae
            );

            Assertions.assertEquals(expected,
                TutorUtils.invokeMethod(method, instance, animals));
        }

        @Test
        @DisplayName("Criterion: Result List<Animal>")
        public void testResultListOfAnimal() {
            final var constructor = getTestConstructor();
            final var instance = TutorUtils.invokeConstructor(constructor);
            final var method = getTestMethod();

            final var animal = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_1);
            final var vertebrate = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_2);
            final var mammal = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_3);
            final var bird = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_4);
            final var placental = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_5);
            final var monotreme = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_6);
            final var rodent = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_8);
            final var lagomorpha = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_7);
            final var leporidae = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_9);

            final var animals = List.of(
                animal,
                vertebrate,
                mammal,
                bird,
                placental,
                monotreme,
                rodent,
                lagomorpha,
                leporidae
            );
            final var expected = List.of(
                lagomorpha,
                leporidae
            );

            Assertions.assertEquals(expected,
                TutorUtils.invokeMethod(method, instance, animals));
        }

        @Test
        @DisplayName("Criterion: Result List<Object>")
        public void testResultListOfObject() {
            final var constructor = getTestConstructor();
            final var instance = TutorUtils.invokeConstructor(constructor);
            final var method = getTestMethod();

            final var animal = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_1);
            final var vertebrate = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_2);
            final var mammal = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_3);
            final var bird = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_4);
            final var placental = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_5);
            final var monotreme = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_6);
            final var rodent = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_8);
            final var lagomorpha = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_7);
            final var leporidae = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_9);

            final var animals = List.of(
                animal,
                vertebrate,
                mammal,
                bird,
                placental,
                monotreme,
                rodent,
                lagomorpha,
                leporidae,
                "Lagomorph",
                12345
            );
            final var expected = List.of(
                lagomorpha,
                leporidae
            );

            Assertions.assertEquals(expected,
                TutorUtils.invokeMethod(method, instance, animals));
        }
    }

    /* *********************************************************************
     *                              Method 3                               *
     **********************************************************************/

    /**
     * Defines the JUnit test cases related to the method {@value TutorConstants#H2_2_METHOD_NAME_3}.
     */
    @Nested
    @DisplayName("Criterion: Method testGetTypesOfMammals:")
    public final class TestMethod3 {

        /**
         * Returns the method that should be tested.
         *
         * @return the method that should be tested
         */
        private Method getTestMethod() {
            final var clazz = getTestClass();
            return TutorUtils.assertMethod(clazz, TutorConstants.H2_2_METHOD_NAME_3,
                TutorConstants.H2_2_METHOD_CLASS_PARAMETER_3);
        }

        @Test
        @DisplayName("Criterion: Only modifiers public")
        public void testModifiers() {
            final var actual = getTestMethod();
            final var expected = Modifier.STATIC.nand(Modifier.FINAL, Modifier.ABSTRACT)
                .and(Modifier.PUBLIC);
            TutorUtils.assertModifiers(expected, actual);
        }

        @Test
        @DisplayName("Criterion: Parameter List<? extends Mammal>")
        public void testParameters() {
            final var method = getTestMethod();
            final var parameters = method.getGenericParameterTypes();

            // Check parameter
            final var expectedParameters = 1;
            final var actualParameters = parameters.length;
            Assertions.assertEquals(expectedParameters, actualParameters,
                TutorMessage.METHOD_PARAMETER_MISMATCH_SIZE.format(method.getName(), expectedParameters,
                    actualParameters));

            // Check type
            final var expectedType = TutorConstants.H2_2_METHOD_BOUND_3;
            final var actualType = parameters[0];
            TutorUtils.assertGenericType(actualType, TutorConstants.H2_2_METHOD_CLASS_PARAMETER_3, expectedType);
        }

        @Test
        @DisplayName("Criterion: Result List<Mammal>")
        public void testResultListOfMammal() {
            final var constructor = getTestConstructor();
            final var instance = TutorUtils.invokeConstructor(constructor);
            final var method = getTestMethod();

            final var mammal = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_3);
            final var placental = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_5);
            final var monotreme = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_6);
            final var rodent = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_8);
            final var lagomorpha = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_7);
            final var leporidae = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_9);

            final var animals = List.of(
                mammal,
                placental,
                monotreme,
                rodent,
                lagomorpha,
                leporidae
            );
            final var expected = Arrays.asList(null, "Placental", "Monotreme", "Placental", "Placental",
                "Placental");

            Assertions.assertEquals(expected,
                TutorUtils.invokeMethod(method, instance, animals));
        }

        @Test
        @DisplayName("Criterion: Result List<Placental>")
        public void testResultListOfPlacental() {
            final var constructor = getTestConstructor();
            final var instance = TutorUtils.invokeConstructor(constructor);
            final var method = getTestMethod();

            final var placental = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_5);
            final var rodent = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_8);
            final var lagomorpha = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_7);
            final var leporidae = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_9);

            final var animals = List.of(
                placental,
                rodent,
                lagomorpha,
                leporidae
            );
            final var expected = List.of("Placental", "Placental", "Placental", "Placental");

            Assertions.assertEquals(expected,
                TutorUtils.invokeMethod(method, instance, animals));
        }

        @Test
        @DisplayName("Criterion: Result List<Monotreme>")
        public void testResultListOfMonotreme() {
            final var constructor = getTestConstructor();
            final var instance = TutorUtils.invokeConstructor(constructor);
            final var method = getTestMethod();

            final var monotreme = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_6);

            final var animals = List.of(
                monotreme
            );
            final var expected = List.of("Monotreme");

            Assertions.assertEquals(expected,
                TutorUtils.invokeMethod(method, instance, animals));
        }

        @Test
        @DisplayName("Criterion: Result List<Lagomorpha>")
        public void testResultListOfLagomorpha() {
            final var constructor = getTestConstructor();
            final var instance = TutorUtils.invokeConstructor(constructor);
            final var method = getTestMethod();

            final var lagomorpha = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_7);
            final var leporidae = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_9);

            final var animals = List.of(
                lagomorpha,
                leporidae
            );
            final var expected = List.of("Placental", "Placental");

            Assertions.assertEquals(expected,
                TutorUtils.invokeMethod(method, instance, animals));
        }

        @Test
        @DisplayName("Criterion: Result List<Rodent>")
        public void testResultListOfRodent() {
            final var constructor = getTestConstructor();
            final var instance = TutorUtils.invokeConstructor(constructor);
            final var method = getTestMethod();

            final var rodent = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_8);

            final var animals = List.of(
                rodent
            );
            final var expected = List.of("Placental");

            Assertions.assertEquals(expected,
                TutorUtils.invokeMethod(method, instance, animals));
        }

        @Test
        @DisplayName("Criterion: Result List<Leporidae>")
        public void testResultListOfLeporidae() {
            final var constructor = getTestConstructor();
            final var instance = TutorUtils.invokeConstructor(constructor);
            final var method = getTestMethod();

            final var leporidae = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_9);

            final var animals = List.of(
                leporidae
            );
            final var expected = List.of("Placental");

            Assertions.assertEquals(expected,
                TutorUtils.invokeMethod(method, instance, animals));
        }
    }
}
