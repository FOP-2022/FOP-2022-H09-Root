package h09.h1;

import h09.utils.Modifier;
import h09.utils.TutorClassTesters;
import h09.utils.TutorConstants;
import h09.utils.TutorMessage;
import h09.utils.TutorUtils;
import h09.utils.spoon.ArraysInstantiationMethodBodyProcessor;
import h09.utils.spoon.SpoonUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.sourcegrade.jagr.api.testing.TestCycle;
import org.sourcegrade.jagr.api.testing.extension.JagrExecutionCondition;
import org.sourcegrade.jagr.api.testing.extension.TestCycleResolver;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.stream.IntStream;

/**
 * Defines the JUnit test cases related to the class defined in the task H1.3.
 *
 * @author Nhan Huynh, Darya Nikitina
 */
@TestForSubmission("h09")
@DisplayName("Criterion: Class MyFunctionWithFilterMapAndFold")
public final class TutorTest_H1_3 {

    /* *********************************************************************
     *                            Utilities                                *
     **********************************************************************/

    /**
     * Returns the class instance of the class that should be tested.
     *
     * @return the class instance of the test class
     */
    private static Class<?> getTestClass() {
        return TutorClassTesters.H1_3.assureClassResolved().getTheClass();
    }

    /**
     * Returns the field class instance that should be tested.
     *
     * @return the field class instance that should be tested
     */
    private static Class<?> getTestFieldClass() {
        return TutorClassTesters.H1_1.assureClassResolved().getTheClass();
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
        @DisplayName("Criterion: Only modifier public")
        public void testModifiers() {
            final var actual = getTestClass();
            final var expected = Modifier.STATIC.nand(Modifier.ABSTRACT, Modifier.FINAL)
                .and(Modifier.PUBLIC);
            TutorUtils.assertModifiers(expected, actual);
        }

        @Test
        @DisplayName("Criterion: Extension of FunctionWithFilterMapAndFold")
        public void testExtension() {
            final var expected = TutorClassTesters.H1_2.assureClassResolved().getTheClass();
            final var actual = getTestClass().getSuperclass();
            Assertions.assertEquals(
                expected, actual, TutorMessage.CLASS_EXTENSION_MISMATCH.format(expected, actual)
            );
        }

        @Test
        @DisplayName("Criterion: Type parameter X, Y, Z")
        public void testTypeParameters() {
            final var clazz = getTestClass();
            TutorUtils_H1.assertClassTypeParameters(clazz);
        }

        @Test
        @ExtendWith({TestCycleResolver.class, JagrExecutionCondition.class})
        @DisplayName("Criterion: Check imports")
        public void testImports(final TestCycle testCycle) {
            final var path = TutorUtils.getPathToSource(getTestClass());
            TutorUtils_H1.assertImports(testCycle, path,
                TutorConstants.H1_IMPORT_BLACK_LIST);
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

        /**
         * Returns the constructor that should be tested.
         *
         * @return the constructor that should be tested
         */
        private Constructor<?> getTestConstructor() {
            final var clazz = getTestClass();
            final var parameter = getTestFieldClass();
            return TutorUtils.assertConstructor(clazz, parameter);
        }

        @Test
        @DisplayName("Criterion: Only modifier public")
        public void testModifiers() {
            final var actual = getTestConstructor();
            final var expected = Modifier.PUBLIC.or(Modifier.PACKAGE_PRIVATE);
            TutorUtils.assertModifiers(expected, actual);
        }

        @Test
        @DisplayName("Criterion: Parameter Traits<X, Y, Z>")
        public void testParameter() {
            final var constructor = getTestConstructor();
            final var parameterClass = getTestFieldClass();
            TutorUtils_H1.assertConstructorParameterH1(constructor, parameterClass);
        }
    }

    /* *********************************************************************
     *                               Method                                *
     **********************************************************************/

    /**
     * Defines the JUnit test cases related to the method {@value TutorConstants#H1_2_METHOD_NAME}.
     */
    @Nested
    @DisplayName("Criterion: Method apply")
    public final class TestMethod {

        /**
         * Returns the method that should be tested.
         *
         * @return the method that should be tested
         */
        private Method getTestMethod() {
            final var clazz = getTestClass();
            return TutorUtils.assertMethod(
                clazz, TutorConstants.H1_2_METHOD_NAME, TutorConstants.H1_2_METHOD_CLASS_PARAMETER
            );
        }

        @Test
        @DisplayName("Criterion: Only modifiers public")
        public void testModifiers() {
            final var actual = getTestMethod();
            final var expected = Modifier.STATIC.nand(Modifier.ABSTRACT).and(Modifier.PUBLIC);
            TutorUtils.assertModifiers(expected, actual);
        }

        @Test
        @DisplayName("Criterion: Parameter X[]")
        public void testParameters() {
            final var method = getTestMethod();
            TutorUtils_H1.assertParametersH1_2(method);
        }

        @Test
        @DisplayName("Criterion: Return type Z")
        public void testReturnType() {
            final var method = getTestMethod();
            TutorUtils_H1.assertReturnTypeH1_2(method);
        }

        @Test
        @DisplayName("Criterion: Return value only operation 3")
        public void testReturnValueOperation3() {
            // Traits object
            final var expectedField1 = TutorConstants.H1_1_FIELD_EXAMPLE_DEFAULT_1;
            final var expectedField2 = TutorConstants.H1_1_FIELD_EXAMPLE_DEFAULT_2;
            final var expectedField3 = TutorConstants.H1_1_FIELD_EXAMPLE_DEFAULT_3;
            final var expectedField4 = TutorConstants.H1_1_FIELD_EXAMPLE_DEFAULT_4;

            final var fieldClass = getTestFieldClass();
            final var fieldConstructor = TutorUtils.assertConstructor(
                fieldClass,
                TutorConstants.H1_1_FIELD_TYPE_1, TutorConstants.H1_1_FIELD_TYPE_2,
                TutorConstants.H1_1_FIELD_TYPE_3, TutorConstants.H1_1_FIELD_TYPE_4
            );

            final var fieldObject = TutorUtils.invokeConstructor(
                fieldConstructor, expectedField1, expectedField2, expectedField3, expectedField4
            );

            // Main object
            final var clazz = getTestClass();
            final var constructor = TutorUtils.assertConstructor(clazz, fieldClass);
            final var instance = TutorUtils.invokeConstructor(constructor, fieldObject);

            // Method call
            final var elements = new Integer[TutorConstants.H1_1_FIELD_EXAMPLE_2_ARRAY_SIZE];
            IntStream.range(0, TutorConstants.H1_1_FIELD_EXAMPLE_2_ARRAY_SIZE)
                .forEach(i -> TutorConstants.H1_1_FIELD_EXAMPLE_2_ARRAY_FILL.accept(i, elements));

            final var method = getTestMethod();
            final var result = TutorUtils.invokeMethod(method, instance, new Object[]{elements});
            Assertions.assertEquals(TutorConstants.H1_1_FIELD_EXAMPLE_DEFAULT_RESULT, result);
        }

        @Test
        @DisplayName("Criterion: Return value")
        public void testReturnValue() {
            // Traits object
            final var expectedField1 = TutorConstants.H1_1_FIELD_EXAMPLE_2_1;
            final var expectedField2 = TutorConstants.H1_1_FIELD_EXAMPLE_2_2;
            final var expectedField3 = TutorConstants.H1_1_FIELD_EXAMPLE_2_3;
            final var expectedField4 = TutorConstants.H1_1_FIELD_EXAMPLE_2_4;

            final var fieldClass = getTestFieldClass();
            final var fieldConstructor = TutorUtils.assertConstructor(
                fieldClass,
                TutorConstants.H1_1_FIELD_TYPE_1, TutorConstants.H1_1_FIELD_TYPE_2,
                TutorConstants.H1_1_FIELD_TYPE_3, TutorConstants.H1_1_FIELD_TYPE_4
            );

            final var fieldObject = TutorUtils.invokeConstructor(
                fieldConstructor, expectedField1, expectedField2, expectedField3, expectedField4
            );

            // Main object
            final var clazz = getTestClass();
            final var constructor = TutorUtils.assertConstructor(clazz, fieldClass);
            final var instance = TutorUtils.invokeConstructor(constructor, fieldObject);

            // Method call
            final var elements = new Integer[TutorConstants.H1_1_FIELD_EXAMPLE_2_ARRAY_SIZE];
            IntStream.range(0, TutorConstants.H1_1_FIELD_EXAMPLE_2_ARRAY_SIZE)
                .forEach(i -> TutorConstants.H1_1_FIELD_EXAMPLE_2_ARRAY_FILL.accept(i, elements));

            final var method = getTestMethod();
            final var result = TutorUtils.invokeMethod(method, instance, new Object[]{elements});
            Assertions.assertEquals(TutorConstants.H1_1_FIELD_EXAMPLE_2_RESULT, result);
        }

        @Test
        @ExtendWith({TestCycleResolver.class, JagrExecutionCondition.class})
        @DisplayName("Criterion: Requirement - Arrays as intermediate storage")
        public void testRequirementArrays(final TestCycle testCycle) {
            final var path = TutorUtils.getPathToSource(getTestClass());
            final var processor = SpoonUtils.process(testCycle, path,
                new ArraysInstantiationMethodBodyProcessor(TutorConstants.H1_3_CLASS_NAME));

            final var expected = 2;
            final var actual = processor.getArrays().size();

            Assertions.assertTrue(
                expected <= actual,
                TutorMessage.REQUIREMENT_INTERMEDIATE_ARRAY_MISMATCH.format(expected, actual)
            );
        }
    }
}
