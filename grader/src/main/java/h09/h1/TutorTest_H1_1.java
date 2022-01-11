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

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * Defines the JUnit test cases related to the class defined in the task H1.1.
 *
 * @author Nhan Huynh, Darya Nikitina
 */
@TestForSubmission("h09")
@DisplayName("Criterion: Class Traits")
public final class TutorTest_H1_1 {

    /* *********************************************************************
     *                            Utilities                                *
     **********************************************************************/

    private static Class<?> getTestClass() {
        return TutorUtils.assertClass(TutorConstants.H1_PACKAGE_NAME, TutorConstants.H1_1_CLASS_NAME);
    }

    /* *********************************************************************
     *                            Class Header                             *
     **********************************************************************/

    @Nested
    @DisplayName("Criterion: Class Header")
    public final class TestClassHeader {

        @Test
        @DisplayName("Criterion: Only modifiers public")
        public void testModifiers() {
            final var actual = getTestClass();
            final var expected = Modifier.ABSTRACT
                .nand(Modifier.FINAL, Modifier.STATIC)
                .and(Modifier.PUBLIC);
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
     *                              Field 1                                *
     **********************************************************************/

    @Nested
    @DisplayName("Criterion: Field for filter operation")
    public final class TestField1 {

        private Field getTestField() {
            final var clazz = getTestClass();
            return TutorUtils.assertField(clazz, TutorConstants.H1_1_FIELD_NAME_1);
        }

        @Test
        @DisplayName("Criterion: Only modifiers private final")
        public void testModifiers() {
            final var actual = getTestField();
            final var expected = Modifier.STATIC.negate().and(Modifier.PRIVATE, Modifier.FINAL);
            TutorUtils.assertModifiers(expected, actual);
        }

        @Test
        @DisplayName("Criterion: Type Predicate<? super X>")
        public void testType() {
            final var actual = getTestField();
            final var type = actual.getGenericType();
            TutorUtils.assertGenericType(
                TutorConstants.H1_1_FIELD_TYPE_1, TutorConstants.H1_1_FIELD_TYPE_PARAMETER_1, type
            );
        }

        @Test
        @DisplayName("Criterion: Getter method")
        public void testGetter() {
            final var method = TutorUtils.assertMethod(getTestClass(), TutorConstants.H1_1_METHOD_NAME_1);

            // Check modifier
            TutorUtils.assertModifiers(Modifier.STATIC.nand(Modifier.FINAL).and(Modifier.PUBLIC), method);

            // Check return type
            final var expected = TutorConstants.H1_1_FIELD_TYPE_1;
            final var actual = method.getReturnType();
            Assertions.assertEquals(
                expected, actual, TutorMessage.RETURN_TYPE_MISMATCH.format(expected, actual)
            );

            final var name = method.getGenericReturnType().getTypeName();
            Assertions.assertEquals(
                String.format(
                    "%s<%s>", expected.getCanonicalName(), TutorConstants.H1_1_FIELD_TYPE_PARAMETER_1
                ), name, TutorMessage.RETURN_TYPE_MISMATCH.format(expected, actual)
            );
        }
    }

    /* *********************************************************************
     *                              Field 2                                *
     **********************************************************************/

    @Nested
    @DisplayName("Criterion: Field for map operation")
    public final class TestField2 {

        private Field getTestField() {
            final var clazz = getTestClass();
            return TutorUtils.assertField(clazz, TutorConstants.H1_1_FIELD_NAME_2);
        }

        @Test
        @DisplayName("Criterion: Only modifiers private final")
        public void testModifiers() {
            final var actual = getTestField();
            final var expected = Modifier.STATIC.negate().and(Modifier.PRIVATE, Modifier.FINAL);
            TutorUtils.assertModifiers(expected, actual);
        }

        @Test
        @DisplayName("Criterion: Type Function<? super X, ? extends Y>")
        public void testType() {
            final var actual = getTestField();
            final var type = actual.getGenericType();
            TutorUtils.assertGenericType(
                TutorConstants.H1_1_FIELD_TYPE_2, TutorConstants.H1_1_FIELD_TYPE_PARAMETER_2, type
            );
        }

        @Test
        @DisplayName("Criterion: Getter method")
        public void testGetter() {
            final var method = TutorUtils.assertMethod(getTestClass(), TutorConstants.H1_1_METHOD_NAME_2);

            // Check modifier
            TutorUtils.assertModifiers(Modifier.STATIC.nand(Modifier.FINAL).and(Modifier.PUBLIC), method);

            // Check return type
            final var expected = TutorConstants.H1_1_FIELD_TYPE_2;
            final var actual = method.getReturnType();
            Assertions.assertEquals(
                expected, actual, TutorMessage.RETURN_TYPE_MISMATCH.format(expected, actual)
            );

            final var name = method.getGenericReturnType().getTypeName();
            Assertions.assertEquals(
                String.format(
                    "%s<%s>", expected.getCanonicalName(), TutorConstants.H1_1_FIELD_TYPE_PARAMETER_2
                ), name, TutorMessage.RETURN_TYPE_MISMATCH.format(expected, actual)
            );
        }
    }

    /* *********************************************************************
     *                              Field 3                                *
     **********************************************************************/

    @Nested
    @DisplayName("Criterion: Field for fold operation")
    public final class TestField3 {

        private Field getTestField() {
            final var clazz = getTestClass();
            return TutorUtils.assertField(clazz, TutorConstants.H1_1_FIELD_NAME_3);
        }

        @Test
        @DisplayName("Criterion: Only modifiers private final")
        public void testModifiers() {
            final var actual = getTestField();
            final var expected = Modifier.STATIC.negate().and(Modifier.PRIVATE, Modifier.FINAL);
            TutorUtils.assertModifiers(expected, actual);
        }

        @Test
        @DisplayName("Criterion: Type BiFunction<Z, ? super Y, Z")
        public void testType() {
            final var actual = getTestField();
            final var type = actual.getGenericType();
            TutorUtils.assertGenericType(
                TutorConstants.H1_1_FIELD_TYPE_3, TutorConstants.H1_1_FIELD_TYPE_PARAMETER_3, type
            );
        }

        @Test
        @DisplayName("Criterion: Getter method")
        public void testGetter() {
            final var method = TutorUtils.assertMethod(getTestClass(), TutorConstants.H1_1_METHOD_NAME_3);

            // Check modifier
            TutorUtils.assertModifiers(Modifier.STATIC.nand(Modifier.FINAL).and(Modifier.PUBLIC), method);

            // Check return type
            final var expected = TutorConstants.H1_1_FIELD_TYPE_3;
            final var actual = method.getReturnType();
            Assertions.assertEquals(
                expected, actual, TutorMessage.RETURN_TYPE_MISMATCH.format(expected, actual)
            );

            final var name = method.getGenericReturnType().getTypeName();
            Assertions.assertEquals(
                String.format(
                    "%s<%s>", expected.getCanonicalName(), TutorConstants.H1_1_FIELD_TYPE_PARAMETER_3
                ), name,
                TutorMessage.RETURN_TYPE_MISMATCH.format(expected, actual)
            );
        }
    }

    /* *********************************************************************
     *                              Field 4                                *
     **********************************************************************/

    @Nested
    @DisplayName("Criterion: Field for the initial value of the fold operation")
    public final class TestField4 {

        private Field getTestField() {
            final var clazz = getTestClass();
            return TutorUtils.assertField(clazz, TutorConstants.H1_1_FIELD_NAME_4);
        }

        @Test
        @DisplayName("Criterion: Only modifiers private final")
        public void testModifiers() {
            final var actual = getTestField();
            final var expected = Modifier.STATIC.negate().and(Modifier.PRIVATE, Modifier.FINAL);
            TutorUtils.assertModifiers(expected, actual);
        }

        @Test
        @DisplayName("Criterion: Type Z")
        public void testType() {
            final var field = getTestField();
            final var type = field.getGenericType();
            final var expected = TutorConstants.H1_1_FIELD_TYPE_PARAMETER_4;
            final var actual = type.getTypeName();
            Assertions.assertEquals(
                expected, actual, TutorMessage.TYPE_PARAMETER_MISMATCH.format(expected, actual)
            );
        }

        @Test
        @DisplayName("Criterion: Getter method")
        public void testGetter() {
            final var method = TutorUtils.assertMethod(getTestClass(), TutorConstants.H1_1_METHOD_NAME_4);

            // Check modifier
            TutorUtils.assertModifiers(Modifier.STATIC.nand(Modifier.FINAL).and(Modifier.PUBLIC), method);

            // Check return type
            final var expected = TutorConstants.H1_1_FIELD_TYPE_4;
            final var actual = method.getReturnType();
            Assertions.assertEquals(
                expected, actual, TutorMessage.RETURN_TYPE_MISMATCH.format(expected, actual)
            );

            final var expectedName = TutorConstants.H1_1_FIELD_TYPE_PARAMETER_4;
            final var actualName = method.getGenericReturnType().getTypeName();
            Assertions.assertEquals(
                expectedName, actualName, TutorMessage.RETURN_TYPE_MISMATCH.format(expectedName, actualName)
            );
        }
    }

    /* *********************************************************************
     *                            Constructor                              *
     **********************************************************************/

    @Nested
    @DisplayName("Criterion: Constructor")
    public final class TestConstructor {

        private Constructor<?> getTestConstructor() {
            final var clazz = getTestClass();
            return TutorUtils.assertConstructor(
                clazz,
                TutorConstants.H1_1_FIELD_TYPE_1,
                TutorConstants.H1_1_FIELD_TYPE_2,
                TutorConstants.H1_1_FIELD_TYPE_3,
                TutorConstants.H1_1_FIELD_TYPE_4
            );
        }

        @Test
        @DisplayName("Criterion: Only modifier public")
        public void testModifiers() {
            final var actual = getTestClass();
            final var expected = Modifier.PUBLIC;
            TutorUtils.assertModifiers(expected, actual);
        }

        @Test
        @DisplayName("Criterion: Parameter Predicate<? super X>, Function<? super X, ? extends Y>, " +
            "BiFunction<Z, ? super Y, Z>, Z")
        public void testParameterTypes() {
            final var constructor = getTestConstructor();
            TutorUtils_H1.assertConstructorParameterTypesH1_1(constructor, false);
        }

        @Test
        @DisplayName("Criterion: Initialization of fields")
        public void testFields() {
            final var constructor = getTestConstructor();
            TutorUtils_H1.assertConstructorFieldsH1_1(constructor, false);
        }
    }
}
