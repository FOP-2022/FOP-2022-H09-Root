package h09.h1;

import h09.utils.Modifier;
import h09.utils.TutorConstants;
import h09.utils.TutorMessage;
import h09.utils.TutorUtils;
import h09.utils.spoon.ArraysInstantiationMethodBodyProcessor;
import h09.utils.spoon.LoopsMethodBodyProcessor;
import h09.utils.spoon.MethodCallsProcessor;
import h09.utils.spoon.SpoonUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.sourcegrade.jagr.api.testing.TestCycle;
import org.sourcegrade.jagr.api.testing.extension.TestCycleResolver;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.stream.IntStream;

/**
 * Defines the JUnit test cases related to the classes defined in the task H1.4.
 *
 * @author Nhan Huynh, Darya Nikitina
 */
@TestForSubmission("h09")
@DisplayName("Criterion: Class MyFunctionWithAdjacent")
public final class TutorTest_H1_4 {

    /* *********************************************************************
     *                            Utilities                                *
     **********************************************************************/

    /**
     * Returns the class instance of the class that should be tested.
     *
     * @return the class instance of the test class
     */
    private static Class<?> getTestClass() {
        return TutorUtils.assertClass(TutorConstants.H1_PACKAGE_NAME, TutorConstants.H1_4_CLASS_NAME);
    }

    /**
     * Returns the field class instance that should be tested.
     *
     * @return the field class instance that should be tested
     */
    private static Class<?> getTestFieldClass() {
        return TutorUtils.assertClass(TutorConstants.H1_PACKAGE_NAME, TutorConstants.H1_1_CLASS_NAME);
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
            final var expected = TutorUtils.assertClass(TutorConstants.H1_PACKAGE_NAME,
                TutorConstants.H1_2_CLASS_NAME);
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
        @ExtendWith(TestCycleResolver.class)
        @DisplayName("Criterion: Check imports")
        public void testImports(final TestCycle testCycle) {
            TutorUtils_H1.assertImports(testCycle, TutorConstants.H1_4_PATH_TO_SOURCE,
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
        public Constructor<?> getTestConstructor() {
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
     * Defines the JUnit test cases related to the method {@value  TutorConstants#H1_2_METHOD_NAME}.
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
        @DisplayName("Criterion: Return value")
        public void testReturnValue() {
            // Traits object
            final var expectedField1 = TutorConstants.H1_1_FIELD_EXAMPLE_3_1;
            final var expectedField2 = TutorConstants.H1_1_FIELD_EXAMPLE_3_2;
            final var expectedField3 = TutorConstants.H1_1_FIELD_EXAMPLE_3_3;
            final var expectedField4 = TutorConstants.H1_1_FIELD_EXAMPLE_3_4;
            final var expectedField5 = TutorConstants.H1_1_FIELD_EXAMPLE_3_5;

            final var fieldClass = getTestFieldClass();
            final var fieldConstructor = TutorUtils.assertConstructor(fieldClass,
                TutorConstants.H1_1_FIELD_TYPE_1, TutorConstants.H1_1_FIELD_TYPE_2,
                TutorConstants.H1_1_FIELD_TYPE_3, TutorConstants.H1_1_FIELD_TYPE_4,
                TutorConstants.H1_4_FIELD_TYPE);

            final var fieldObject = TutorUtils.invokeConstructor(fieldConstructor, expectedField1,
                expectedField2, expectedField3, expectedField4, expectedField5);

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
            Assertions.assertEquals(TutorConstants.H1_1_FIELD_EXAMPLE_3_RESULT, result);
        }

        @Test
        @ExtendWith(TestCycleResolver.class)
        @DisplayName("Criterion: Requirement - No arrays")
        public void testRequirementArrays(final TestCycle testCycle) {
            final var arrayProcessor = SpoonUtils.process(testCycle, TutorConstants.H1_4_PATH_TO_SOURCE,
                new ArraysInstantiationMethodBodyProcessor(TutorConstants.H1_2_METHOD_NAME));

            final var expected = 0;
            final var actual = arrayProcessor.getArrays().size();
            Assertions.assertEquals(
                expected, actual,
                TutorMessage.REQUIREMENT_NO_ARRAY.format(expected, actual)
            );

            final var calleeProcessor = SpoonUtils.process(testCycle, TutorConstants.H1_4_PATH_TO_SOURCE,
                new MethodCallsProcessor(TutorConstants.H1_2_METHOD_NAME));

            for (final var callee : calleeProcessor.getCallees()) {
                final var name = callee.getExecutable().getSimpleName();
                Assertions.assertTrue(TutorConstants.H1_4_REQUIREMENTS_CALLEES.contains(name),
                    TutorMessage.REQUIREMENT_FOREACH_NO_CALLEE.format(1, name)
                );
            }
        }

        @Test
        @ExtendWith(TestCycleResolver.class)
        @DisplayName("Criterion: Requirement - Only one foreach")
        public void testRequirementForeachLoop(final TestCycle testCycle) {
            final var processor = SpoonUtils.process(testCycle, TutorConstants.H1_4_PATH_TO_SOURCE,
                new LoopsMethodBodyProcessor(null));

            final var expectedForEach = 1;
            final var actualForEach = processor.getForeachLoops().size();
            Assertions.assertEquals(expectedForEach, actualForEach,
                TutorMessage.REQUIREMENT_FOREACH_LOOP.format(expectedForEach, actualForEach)
            );

            final var actualFor = processor.getForLoops().size();
            Assertions.assertTrue(processor.getForLoops().isEmpty(),
                TutorMessage.REQUIREMENT_FOREACH_LOOP.format(
                    expectedForEach, String.format("%d for loop(s)", actualFor))
            );

            final var actualWhile = processor.getWhileLoops().size();
            Assertions.assertTrue(processor.getWhileLoops().isEmpty(),
                TutorMessage.REQUIREMENT_FOREACH_LOOP.format(
                    expectedForEach, String.format("%d while loop(s)", actualWhile))
            );

            final var actualDoWhile = processor.getDoWhileLoops().size();
            Assertions.assertTrue(processor.getDoWhileLoops().isEmpty(),
                TutorMessage.REQUIREMENT_FOREACH_LOOP.format(
                    expectedForEach, String.format("%d do-while loop(s)", actualDoWhile))
            );
        }
    }

    /* *********************************************************************
     *                               Field                                 *
     **********************************************************************/

    /**
     * Defines the JUnit test cases related to the field {@value TutorConstants#H1_4_FIELD_NAME}.
     */
    @Nested
    @DisplayName("Criterion: Traits - Field combine")
    public final class TestH1ExtraField {

        /**
         * Defines the JUnit test cases related to the field {@value TutorConstants#H1_4_FIELD_NAME}.
         */
        @Nested
        @DisplayName("Criterion: Field for combining")
        public final class TestField {

            /**
             * Returns the field that should be tested.
             *
             * @return the field that should be tested
             */
            private Field getTestField() {
                final var clazz = getTestFieldClass();
                return TutorUtils.assertField(clazz, TutorConstants.H1_4_FIELD_NAME);
            }

            @Test
            @DisplayName("Criterion: Only modifiers private final")
            public void testModifiers() {
                final var actual = getTestField();
                final var expected = Modifier.STATIC.negate().and(Modifier.PRIVATE, Modifier.FINAL);
                TutorUtils.assertModifiers(expected, actual);
            }

            @Test
            @DisplayName("Criterion: Type BiFunction<Y/? super Y, ? super Y, ? extends Y>")
            public void testType() {
                final var actual = getTestField();
                final var type = actual.getGenericType();
                TutorUtils.assertGenericType(
                    type, TutorConstants.H1_4_FIELD_TYPE, TutorConstants.H1_4_FIELD_TYPE_PARAMETER
                );
            }

            @Test
            @DisplayName("Criterion: Getter method")
            public void testGetter() {
                final var method = TutorUtils.assertMethod(getTestFieldClass(), TutorConstants.H1_4_METHOD_NAME);

                // Check modifier
                TutorUtils.assertModifiers(Modifier.STATIC.nand(Modifier.FINAL).and(Modifier.PUBLIC), method);

                // Check return type
                final var expected = TutorConstants.H1_4_FIELD_TYPE;
                final var actual = method.getReturnType();
                Assertions.assertEquals(
                    expected, actual, TutorMessage.RETURN_TYPE_MISMATCH.format(expected, actual)
                );

                final var name = method.getGenericReturnType().getTypeName();
                for (final var acceptedTypes : TutorConstants.H1_4_FIELD_TYPE_PARAMETER) {
                    final var expectedName = String.format("%s<%s>", expected.getCanonicalName(), acceptedTypes);
                    try {
                        Assertions.assertEquals(expectedName, name);
                        return;
                    } catch (AssertionError e) {
                        continue;
                    }
                }
                Assertions.fail(TutorMessage.RETURN_TYPE_MISMATCH.format(
                    String.format("%s<%s>", expected, String.join("/", TutorConstants.H1_4_FIELD_TYPE_PARAMETER)),
                    name
                ));
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
             * Defines the JUnit test cases related to the constructor with 4 arguments.
             */
            @Nested
            @DisplayName("Criterion: 4 arguments Constructor")
            public final class Test4Args {

                /**
                 * Returns the constructor that should be tested.
                 *
                 * @return the constructor that should be tested
                 */
                private Constructor<?> getTestConstructor() {
                    final var clazz = getTestFieldClass();
                    final Class<?>[] parameters = {TutorConstants.H1_1_FIELD_TYPE_1,
                        TutorConstants.H1_1_FIELD_TYPE_2, TutorConstants.H1_1_FIELD_TYPE_3,
                        TutorConstants.H1_1_FIELD_TYPE_4};
                    return TutorUtils.assertConstructor(clazz, parameters);
                }

                @Test
                @DisplayName("Criterion: Only modifier public")
                public void testModifiers() {
                    final var actual = getTestConstructor();
                    final var expected = Modifier.PUBLIC;
                    TutorUtils.assertModifiers(expected, actual);
                }

                @Test
                @DisplayName("Criterion: Parameter Predicate<? super X>, Function<? super X, ? extends "
                    + "Y>, BiFunction<Y, Z, Z>, Z")
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

            /**
             * Defines the JUnit test cases related to the constructor with 5 arguments.
             */
            @Nested
            @DisplayName("Criterion: 5 arguments Constructor")
            public final class Test5Args {

                /**
                 * Returns the constructor that should be tested.
                 *
                 * @return the constructor that should be tested
                 */
                private Constructor<?> getTestConstructor() {
                    final var clazz = getTestFieldClass();
                    final Class<?>[] parameters = {TutorConstants.H1_1_FIELD_TYPE_1,
                        TutorConstants.H1_1_FIELD_TYPE_2, TutorConstants.H1_1_FIELD_TYPE_3,
                        TutorConstants.H1_1_FIELD_TYPE_4, TutorConstants.H1_4_FIELD_TYPE};
                    return TutorUtils.assertConstructor(clazz, parameters);
                }

                @Test
                @DisplayName("Criterion: Only modifier public")
                public void testModifiers() {
                    final var actual = getTestConstructor();
                    final var expected = Modifier.PUBLIC;
                    TutorUtils.assertModifiers(expected, actual);
                }

                @Test
                @DisplayName("Criterion: Parameter Predicate<? super X>, Function<? super X, ? extends "
                    + "Y>, BiFunction<Y, Z, Z>, Z, BiFunction<Y, ? super Y, Y>")
                public void testParameterTypes() {
                    final var constructor = getTestConstructor();
                    TutorUtils_H1.assertConstructorParameterTypesH1_1(constructor, true);
                }

                @Test
                @DisplayName("Criterion: Initialization of fields")
                public void testFields() {
                    final var constructor = getTestConstructor();
                    TutorUtils_H1.assertConstructorFieldsH1_1(constructor, true);
                }
            }
        }
    }
}
