package h09.h1;

import h09.utils.Modifier;
import h09.utils.TutorClassTesters;
import h09.utils.TutorConstants;
import h09.utils.TutorMessage;
import h09.utils.TutorUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Defines the JUnit test cases related to the class defined in the task H1.2.
 *
 * @author Nhan Huynh, Darya Nikitina
 */
@TestForSubmission("h09")
@DisplayName("Criterion: Class FunctionWithFilterMapAndFold")
public final class TutorTest_H1_2 {

    /* *********************************************************************
     *                            Utilities                                *
     **********************************************************************/

    /**
     * Returns the class instance of the class that should be tested.
     *
     * @return the class instance of the test class
     */
    private static Class<?> getTestClass() {
        return TutorClassTesters.H1_2.assureClassResolved().getTheClass();
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
        @DisplayName("Criterion: Only modifiers public abstract")
        public void testModifiers() {
            final var actual = getTestClass();
            final var expected = Modifier.STATIC.negate().and(Modifier.PUBLIC, Modifier.ABSTRACT);
            TutorUtils.assertModifiers(expected, actual);
        }

        @Test
        @DisplayName("Criterion: No extension")
        public void testExtension() {
            final var expected = Object.class;
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
    }

    /* *********************************************************************
     *                               Field                                 *
     **********************************************************************/

    /**
     * Defines the JUnit test cases related to the field used to access filter, map and fold
     * operation.
     */
    @Nested
    @DisplayName("Criterion: Field traits")
    public final class TestField {

        /**
         * Returns the field that should be tested.
         *
         * @return the field that should be tested
         */
        private Field getTestField() {
            return TutorUtils.assertField(getTestClass(), TutorConstants.H1_2_FIELD_NAME);
        }

        @Test
        @DisplayName("Criterion: Only modifiers protected final")
        public void testModifiers() {
            final var actual = getTestField();
            final var expected = Modifier.STATIC.negate().and(Modifier.PROTECTED, Modifier.FINAL);
            TutorUtils.assertModifiers(expected, actual);
        }

        @Test
        @DisplayName("Criterion: Type Traits<X, Y, Z>")
        public void testType() {
            final var field = getTestField();
            final var clazz = getTestFieldClass();
            TutorUtils.assertGenericType(
                field.getGenericType(), clazz, TutorConstants.H1_TYPE_PARAMETERS
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
            final var expected = Modifier.PUBLIC;
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
            return TutorUtils.assertMethod(clazz, TutorConstants.H1_2_METHOD_NAME,
                TutorConstants.H1_2_METHOD_CLASS_PARAMETER);
        }

        @Test
        @DisplayName("Criterion: Only modifiers public abstract")
        public void testModifiers() {
            final var actual = getTestMethod();
            final var expected = Modifier.STATIC.negate().and(Modifier.PUBLIC, Modifier.ABSTRACT);
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
    }
}
