package h09.h1;

import h09.utils.Modifier;
import h09.utils.TutorConstants;
import h09.utils.TutorMessage;
import h09.utils.TutorUtils;
import h09.utils.spoon.LambdaExpressionsMethodBodyProcessor;
import h09.utils.spoon.MethodReferencesMethodBodyProcessor;
import h09.utils.spoon.SpoonUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.sourcegrade.jagr.api.testing.TestCycle;
import org.sourcegrade.jagr.api.testing.extension.TestCycleResolver;
import spoon.reflect.code.CtExpression;
import spoon.reflect.declaration.CtTypedElement;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Defines the JUnit test cases related to the class defined in the task H1.6.
 *
 * @author Nhan Huynh, Darya Nikitina
 */
@TestForSubmission("h09")
@DisplayName("Criterion: Class TestFunctionFactory")
public final class TutorTest_H1_6 {

    /**
     * Returns the method that should be tested.
     *
     * @return the method that should be tested
     */
    private static Class<?> getTestClass() {
        return TutorUtils.assertClass(TutorConstants.H1_PACKAGE_NAME, TutorConstants.H1_6_CLASS_NAME);
    }

    /**
     * Tests if the size of the list is correct.
     *
     * @param expected the expected types
     * @param actual   the actual types
     * @param message  the message format to print if the assertions fails
     */
    private static void assertSize(final int expected, final List<?> actual, final TutorMessage message) {
        // Check number of lambdas
        final var size = actual.size();
        Assertions.assertEquals(expected, size, message.format(expected, size));
    }

    /**
     * Tests if the list contains the specified types.
     *
     * @param expected the expected types
     * @param actual   the actual types
     * @param message  the message format to print if the assertions fails
     */
    private static void assertTypes(final String[] expected, final List<?> actual, final TutorMessage message) {
        for (int i = 0; i < expected.length; i++) {
            final var a = actual.get(i).toString();
            final var e = expected[i];
            Assertions.assertEquals(e, a, message.format(e, a));
        }
    }

    /**
     * Tests if the expected lambdas matches the actual lambdas.
     *
     * @param expected the expected class name of the lambdas
     * @param actual   the actual lambdas
     * @param message  the message format to print if the assertions fails
     */
    private static void assertLambdas(final String[] expected,
                                      final List<? extends CtExpression<?>> actual,
                                      final TutorMessage message) {
        assertSize(expected.length, actual, message);
        assertTypes(expected, actual.stream().map(CtTypedElement::getType).collect(Collectors.toList()), message);
    }

    /**
     * Defines the JUnit test cases related to the class header.
     */
    @Nested
    @DisplayName("Criterion: Class header")
    public final class TestClassHeader {

        @Test
        @DisplayName("Criterion: Only modifiers package-private")
        public void testModifiers() {
            final var actual = getTestClass();
            final var expected = Modifier.FINAL.negate().and(Modifier.PACKAGE_PRIVATE);
            TutorUtils.assertModifiers(expected, actual);
        }

        @Test
        @DisplayName("Criterion: No extension")
        public void testExtension() {
            final var actual = getTestClass().getSuperclass();
            final var expected = Object.class;
            Assertions.assertEquals(expected, actual,
                TutorMessage.CLASS_EXTENSION_MISMATCH.format(expected, actual));
        }
    }

    /**
     * Defines the JUnit test cases related to the method {@link  TutorConstants#H1_6_METHOD_NAME_1}.
     */
    @Nested
    @DisplayName("Criterion: Method testCreateFunctionWithFilterMapAndFold1")
    public final class TestMethod1 {

        @Test
        @ExtendWith(TestCycleResolver.class)
        @DisplayName("Criterion: Requirement - Only lambdas")
        public void testRequirement(final TestCycle testCycle) {
            final var processor = SpoonUtils.process(testCycle, TutorConstants.H1_6_PATH_TO_SOURCE,
                new LambdaExpressionsMethodBodyProcessor(TutorConstants.H1_6_METHOD_NAME_1));
            final var actualTypes = processor.getLambdas();
            final var expectedTypes = TutorConstants.H1_6_METHOD_1_LAMBDAS;

            assertLambdas(expectedTypes, actualTypes, TutorMessage.LAMBDA_MISMATCH);
        }
    }

    /**
     * Defines the JUnit test cases related to the method {@link  TutorConstants#H1_6_METHOD_NAME_2}.
     */
    @Nested
    @DisplayName("Criterion: Method testCreateFunctionWithFilterMapAndFold2")
    public final class TestMethod2 {

        @Test
        @ExtendWith(TestCycleResolver.class)
        @DisplayName("Criterion: Requirement - Only lambdas")
        public void testRequirement(final TestCycle testCycle) {
            final var processor = SpoonUtils.process(testCycle, TutorConstants.H1_6_PATH_TO_SOURCE,
                new LambdaExpressionsMethodBodyProcessor(TutorConstants.H1_6_METHOD_NAME_2));
            final var actualTypes = processor.getLambdas();
            final var expectedTypes = TutorConstants.H1_6_METHOD_2_LAMBDAS;

            assertLambdas(expectedTypes, actualTypes, TutorMessage.LAMBDA_MISMATCH);
        }
    }

    /**
     * Defines the JUnit test cases related to the method {@link  TutorConstants#H1_6_METHOD_NAME_3}.
     */
    @Nested
    @DisplayName("Criterion: Method testCreateFunctionWithFilterMapFoldAndCombine")
    public final class TestMethod3 {

        @Test
        @ExtendWith(TestCycleResolver.class)
        @DisplayName("Criterion: Requirement - Only method references")
        public void testRequirement(final TestCycle testCycle) {
            final var processor = SpoonUtils.process(testCycle, TutorConstants.H1_6_PATH_TO_SOURCE,
                new MethodReferencesMethodBodyProcessor(TutorConstants.H1_6_METHOD_NAME_2));
            final var actualTypes = processor.getMethodReferences();
            final var expectedTypes = TutorConstants.H1_6_METHOD_3_LAMBDAS;

            assertLambdas(expectedTypes, actualTypes, TutorMessage.LAMBDA_METHOD_REFERENCE_MISMATCH);
        }

        /**
         * Defines the JUnit test cases related to the class {@value  TutorConstants#H1_6_CLASS_NAME_1}.
         */
        @Nested
        @DisplayName("Criterion: Test class - Person")
        public final class TestPerson {

            /**
             * Returns the class that should be tested.
             *
             * @return the class that should be tested
             */
            private Class<?> getTestClassPerson() {
                return TutorUtils.assertNestedClass(TutorConstants.H1_PACKAGE_NAME,
                    TutorConstants.H1_6_CLASS_NAME, TutorConstants.H1_6_CLASS_NAME_1);
            }

            /**
             * Defines the JUnit test cases related to the class header.
             */
            @Nested
            @DisplayName("Criterion: Class header")
            public final class TestClassHeader {

                @Test
                @DisplayName("Criterion: Only modifiers non-public")
                public void testModifiers() {
                    final var actual = getTestClassPerson();
                    final var expected = Modifier.PUBLIC.nand(Modifier.FINAL);
                    TutorUtils.assertModifiers(expected, actual);
                }

                @Test
                @DisplayName("Criterion: No extension")
                public void testExtension() {
                    final var actual = getTestClassPerson().getSuperclass();
                    final var expected = Object.class;
                    Assertions.assertEquals(expected, actual,
                        TutorMessage.CLASS_EXTENSION_MISMATCH.format(expected, actual));
                }
            }

            /**
             * Defines the JUnit test cases related to the field {@value  TutorConstants#H1_6_FIELD_NAME_1}.
             */
            @Nested
            @DisplayName("Criterion: Field lastName")
            public final class TestField1 {

                /**
                 * Returns the field that should be tested.
                 *
                 * @return the field that should be tested
                 */
                private Field getTestField() {
                    final var clazz = getTestClassPerson();
                    return TutorUtils.assertField(clazz, TutorConstants.H1_6_FIELD_NAME_1);
                }

                /**
                 * Returns the method that should be tested.
                 *
                 * @return the method that should be tested
                 */
                private Method getTestMethod(final String methodName, final Class<?>... parameters) {
                    final var clazz = getTestClassPerson();
                    return TutorUtils.assertMethod(clazz, methodName, parameters);
                }

                @Test
                @DisplayName("Criterion: Only modifiers public")
                public void testModifiers() {
                    final var actual = getTestField();
                    final var expected = Modifier.STATIC.nand(Modifier.FINAL).and(Modifier.PUBLIC);
                    TutorUtils.assertModifiers(expected, actual);
                }

                @Test
                @DisplayName("Criterion: Getter method")
                public void testGetter() {
                    final var method = getTestMethod(TutorConstants.H1_6_METHOD_NAME_4);

                    // Check modifier
                    TutorUtils.assertModifiers(Modifier.STATIC.nand(Modifier.FINAL).and(Modifier.PUBLIC), method);

                    // Check return type
                    final var expected = TutorConstants.H1_6_FIELD_TYPE_1;
                    final var actual = method.getReturnType();
                    Assertions.assertEquals(
                        expected, actual, TutorMessage.RETURN_TYPE_MISMATCH.format(expected, actual)
                    );
                }

                @Test
                @DisplayName("Criterion: Setter method")
                public void testSetter() {
                    final var method = getTestMethod(TutorConstants.H1_6_METHOD_NAME_9,
                        TutorConstants.H1_6_FIELD_TYPE_1);

                    // Check modifier
                    TutorUtils.assertModifiers(Modifier.STATIC.nand(Modifier.FINAL).and(Modifier.PUBLIC), method);

                    // Check return type
                    final var expected = void.class;
                    final var actual = method.getReturnType();
                    Assertions.assertEquals(
                        expected, actual, TutorMessage.RETURN_TYPE_MISMATCH.format(expected, actual)
                    );
                }
            }

            /**
             * Defines the JUnit test cases related to the field {@value  TutorConstants#H1_6_FIELD_NAME_2}.
             */
            @Nested
            @DisplayName("Criterion: Field firstName")
            public final class TestField2 {

                /**
                 * Returns the field that should be tested.
                 *
                 * @return the field that should be tested
                 */
                private Field getTestField() {
                    final var clazz = getTestClassPerson();
                    return TutorUtils.assertField(clazz, TutorConstants.H1_6_FIELD_NAME_2);
                }

                /**
                 * Returns the method that should be tested.
                 *
                 * @return the method that should be tested
                 */
                private Method getTestMethod(final String methodName, final Class<?>... parameters) {
                    final var clazz = getTestClassPerson();
                    return TutorUtils.assertMethod(clazz, methodName, parameters);
                }

                @Test
                @DisplayName("Criterion: Only modifiers public")
                public void testModifiers() {
                    final var actual = getTestField();
                    final var expected = Modifier.STATIC.nand(Modifier.FINAL).and(Modifier.PUBLIC);
                    TutorUtils.assertModifiers(expected, actual);
                }

                @Test
                @DisplayName("Criterion: Getter method")
                public void testGetter() {
                    final var method = getTestMethod(TutorConstants.H1_6_METHOD_NAME_5);

                    // Check modifier
                    TutorUtils.assertModifiers(Modifier.STATIC.nand(Modifier.FINAL).and(Modifier.PUBLIC), method);

                    // Check return type
                    final var expected = TutorConstants.H1_6_FIELD_TYPE_2;
                    final var actual = method.getReturnType();
                    Assertions.assertEquals(
                        expected, actual, TutorMessage.RETURN_TYPE_MISMATCH.format(expected, actual)
                    );
                }

                @Test
                @DisplayName("Criterion: Setter method")
                public void testSetter() {
                    final var method = getTestMethod(TutorConstants.H1_6_METHOD_NAME_10,
                        TutorConstants.H1_6_FIELD_TYPE_2);

                    // Check modifier
                    TutorUtils.assertModifiers(Modifier.STATIC.nand(Modifier.FINAL).and(Modifier.PUBLIC), method);

                    // Check return type
                    final var expected = void.class;
                    final var actual = method.getReturnType();
                    Assertions.assertEquals(
                        expected, actual, TutorMessage.RETURN_TYPE_MISMATCH.format(expected, actual)
                    );
                }
            }

            /**
             * Defines the JUnit test cases related to the field {@value  TutorConstants#H1_6_FIELD_NAME_3}.
             */
            @Nested
            @DisplayName("Criterion: Field street")
            public final class TestField3 {

                /**
                 * Returns the field that should be tested.
                 *
                 * @return the field that should be tested
                 */
                private Field getTestField() {
                    final var clazz = getTestClassPerson();
                    return TutorUtils.assertField(clazz, TutorConstants.H1_6_FIELD_NAME_3);
                }

                /**
                 * Returns the method that should be tested.
                 *
                 * @return the method that should be tested
                 */
                private Method getTestMethod(final String methodName, final Class<?>... parameters) {
                    final var clazz = getTestClassPerson();
                    return TutorUtils.assertMethod(clazz, methodName, parameters);
                }

                @Test
                @DisplayName("Criterion: Only modifiers public")
                public void testModifiers() {
                    final var actual = getTestField();
                    final var expected = Modifier.STATIC.nand(Modifier.FINAL).and(Modifier.PUBLIC);
                    TutorUtils.assertModifiers(expected, actual);
                }

                @Test
                @DisplayName("Criterion: Getter method")
                public void testGetter() {
                    final var method = getTestMethod(TutorConstants.H1_6_METHOD_NAME_6);

                    // Check modifier
                    TutorUtils.assertModifiers(Modifier.STATIC.nand(Modifier.FINAL).and(Modifier.PUBLIC), method);

                    // Check return type
                    final var expected = TutorConstants.H1_6_FIELD_TYPE_3;
                    final var actual = method.getReturnType();
                    Assertions.assertEquals(
                        expected, actual, TutorMessage.RETURN_TYPE_MISMATCH.format(expected, actual)
                    );
                }

                @Test
                @DisplayName("Criterion: Setter method")
                public void testSetter() {
                    final var method = getTestMethod(TutorConstants.H1_6_METHOD_NAME_11,
                        TutorConstants.H1_6_FIELD_TYPE_3);

                    // Check modifier
                    TutorUtils.assertModifiers(Modifier.STATIC.nand(Modifier.FINAL).and(Modifier.PUBLIC), method);

                    // Check return type
                    final var expected = void.class;
                    final var actual = method.getReturnType();
                    Assertions.assertEquals(
                        expected, actual, TutorMessage.RETURN_TYPE_MISMATCH.format(expected, actual)
                    );
                }
            }

            /**
             * Defines the JUnit test cases related to the field {@value  TutorConstants#H1_6_FIELD_NAME_4}.
             */
            @Nested
            @DisplayName("Criterion: Field houseNumber")
            public final class TestField4 {

                /**
                 * Returns the field that should be tested.
                 *
                 * @return the field that should be tested
                 */
                private Field getTestField() {
                    final var clazz = getTestClassPerson();
                    return TutorUtils.assertField(clazz, TutorConstants.H1_6_FIELD_NAME_4);
                }

                /**
                 * Returns the method that should be tested.
                 *
                 * @return the method that should be tested
                 */
                private Method getTestMethod(final String methodName, final Class<?>... parameters) {
                    final var clazz = getTestClassPerson();
                    return TutorUtils.assertMethod(clazz, methodName, parameters);
                }

                @Test
                @DisplayName("Criterion: Only modifiers public")
                public void testModifiers() {
                    final var actual = getTestField();
                    final var expected = Modifier.STATIC.nand(Modifier.FINAL).and(Modifier.PUBLIC);
                    TutorUtils.assertModifiers(expected, actual);
                }

                @Test
                @DisplayName("Criterion: Getter method")
                public void testGetter() {
                    final var method = getTestMethod(TutorConstants.H1_6_METHOD_NAME_7);

                    // Check modifier
                    TutorUtils.assertModifiers(Modifier.STATIC.nand(Modifier.FINAL).and(Modifier.PUBLIC), method);

                    // Check return type
                    final var expected = TutorConstants.H1_6_FIELD_TYPE_4;
                    final var actual = method.getReturnType();
                    Assertions.assertEquals(
                        expected, actual, TutorMessage.RETURN_TYPE_MISMATCH.format(expected, actual)
                    );
                }

                @Test
                @DisplayName("Criterion: Setter method")
                public void testSetter() {
                    final var method = getTestMethod(TutorConstants.H1_6_METHOD_NAME_12,
                        TutorConstants.H1_6_FIELD_TYPE_4);

                    // Check modifier
                    TutorUtils.assertModifiers(Modifier.STATIC.nand(Modifier.FINAL).and(Modifier.PUBLIC), method);

                    // Check return type
                    final var expected = void.class;
                    final var actual = method.getReturnType();
                    Assertions.assertEquals(
                        expected, actual, TutorMessage.RETURN_TYPE_MISMATCH.format(expected, actual)
                    );
                }
            }

            /**
             * Defines the JUnit test cases related to the field {@value  TutorConstants#H1_6_FIELD_NAME_5}.
             */
            @Nested
            @DisplayName("Criterion: Field postalCode")
            public final class TestField5 {

                /**
                 * Returns the field that should be tested.
                 *
                 * @return the field that should be tested
                 */
                private Field getTestField() {
                    final var clazz = getTestClassPerson();
                    return TutorUtils.assertField(clazz, TutorConstants.H1_6_FIELD_NAME_5);
                }

                /**
                 * Returns the method that should be tested.
                 *
                 * @return the method that should be tested
                 */
                private Method getTestMethod(final String methodName, final Class<?>... parameters) {
                    final var clazz = getTestClassPerson();
                    return TutorUtils.assertMethod(clazz, methodName, parameters);
                }

                @Test
                @DisplayName("Criterion: Only modifiers public")
                public void testModifiers() {
                    final var actual = getTestField();
                    final var expected = Modifier.STATIC.nand(Modifier.FINAL).and(Modifier.PUBLIC);
                    TutorUtils.assertModifiers(expected, actual);
                }

                @Test
                @DisplayName("Criterion: Getter method")
                public void testGetter() {
                    final var method = getTestMethod(TutorConstants.H1_6_METHOD_NAME_8);

                    // Check modifier
                    TutorUtils.assertModifiers(Modifier.STATIC.nand(Modifier.FINAL).and(Modifier.PUBLIC), method);

                    // Check return type
                    final var expected = TutorConstants.H1_6_FIELD_TYPE_5;
                    final var actual = method.getReturnType();
                    Assertions.assertEquals(
                        expected, actual, TutorMessage.RETURN_TYPE_MISMATCH.format(expected, actual)
                    );
                }

                @Test
                @DisplayName("Criterion: Setter method")
                public void testSetter() {
                    final var method = getTestMethod(TutorConstants.H1_6_METHOD_NAME_13,
                        TutorConstants.H1_6_FIELD_TYPE_5);

                    // Check modifier
                    TutorUtils.assertModifiers(Modifier.STATIC.nand(Modifier.FINAL).and(Modifier.PUBLIC), method);

                    // Check return type
                    final var expected = void.class;
                    final var actual = method.getReturnType();
                    Assertions.assertEquals(
                        expected, actual, TutorMessage.RETURN_TYPE_MISMATCH.format(expected, actual)
                    );
                }
            }

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
                    final var clazz = getTestClassPerson();
                    return TutorUtils.assertConstructor(
                        clazz,
                        TutorConstants.H1_6_FIELD_TYPE_1,
                        TutorConstants.H1_6_FIELD_TYPE_2,
                        TutorConstants.H1_6_FIELD_TYPE_3,
                        TutorConstants.H1_6_FIELD_TYPE_4,
                        TutorConstants.H1_6_FIELD_TYPE_5
                    );
                }

                @Test
                @DisplayName("Criterion: Only modifier public")
                public void testModifiers() {
                    final var actual = getTestConstructor();
                    final var expected = Modifier.FINAL.negate().and(Modifier.PUBLIC);
                    TutorUtils.assertModifiers(expected, actual);
                }

                @Test
                @DisplayName("Criterion: Initialization of fields")
                public void testFields() {
                    final var constructor = getTestConstructor();

                    final var expectedField1 = TutorConstants.H1_6_FIELD_EXAMPLE_1;
                    final var expectedField2 = TutorConstants.H1_6_FIELD_EXAMPLE_2;
                    final var expectedField3 = TutorConstants.H1_6_FIELD_EXAMPLE_3;
                    final var expectedField4 = TutorConstants.H1_6_FIELD_EXAMPLE_4;
                    final var expectedField5 = TutorConstants.H1_6_FIELD_EXAMPLE_5;

                    final var instance = TutorUtils.invokeConstructor(
                        constructor, expectedField1, expectedField2, expectedField3, expectedField4,
                        expectedField5
                    );

                    // Check if fields are initialized
                    final var actualField1 = TutorUtils.assertField(instance, TutorConstants.H1_6_FIELD_NAME_1);
                    final var actualField2 = TutorUtils.assertField(instance,
                        TutorConstants.H1_6_FIELD_NAME_2);
                    final var actualField3 = TutorUtils.assertField(instance,
                        TutorConstants.H1_6_FIELD_NAME_3);
                    final var actualField4 = TutorUtils.assertField(instance,
                        TutorConstants.H1_6_FIELD_NAME_4);
                    final var actualField5 = TutorUtils.assertField(instance,
                        TutorConstants.H1_6_FIELD_NAME_5);

                    Assertions.assertEquals(
                        expectedField1, TutorUtils.getFieldContent(actualField1, instance),
                        TutorMessage.FIELD_CONTENT_MISMATCH.format(expectedField1, actualField1)
                    );
                    Assertions.assertEquals(
                        expectedField2, TutorUtils.getFieldContent(actualField2, instance),
                        TutorMessage.FIELD_CONTENT_MISMATCH.format(expectedField2, actualField2)
                    );
                    Assertions.assertEquals(
                        expectedField3, TutorUtils.getFieldContent(actualField3, instance),
                        TutorMessage.FIELD_CONTENT_MISMATCH.format(expectedField3, actualField3)
                    );
                    Assertions.assertEquals(
                        expectedField4, TutorUtils.getFieldContent(actualField4, instance),
                        TutorMessage.FIELD_CONTENT_MISMATCH.format(expectedField4, actualField4)
                    );
                    Assertions.assertEquals(
                        expectedField5, TutorUtils.getFieldContent(actualField5, instance),
                        TutorMessage.FIELD_CONTENT_MISMATCH.format(expectedField5, actualField5)
                    );
                }
            }
        }
    }
}
