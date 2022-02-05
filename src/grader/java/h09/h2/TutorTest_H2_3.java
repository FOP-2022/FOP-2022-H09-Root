package h09.h2;

import h09.utils.Modifier;
import h09.utils.TutorClassTesters;
import h09.utils.TutorConstants;
import h09.utils.TutorMessage;
import h09.utils.TutorUtils;
import h09.utils.spoon.ObjectsUsageMethodProcessor;
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

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Defines the JUnit test cases related to the class defined in the task H2.3.
 *
 * @author Nhan Huynh, Darya Nikitina
 */
@TestForSubmission("h09")
@DisplayName("Criterion: Class TestBiologicalHierachy")
public final class TutorTest_H2_3 {

    /* *********************************************************************
     *                            Utilities                                *
     **********************************************************************/

    /**
     * Returns the class instance of the class that should be tested.
     *
     * @return the class instance of the test class
     */
    private static Class<?> getTestClass() {
        return TutorClassTesters.H2_3.assureClassResolved().getTheClass();
    }

    /**
     * Tests whether the specified animals are used in the specified test method.
     *
     * @param testCycle       the test cycle to retrieve the method source code
     * @param methodName      the name of the method to check
     * @param expectedSize    the expected number of animals
     * @param expectedAnimals the expected animals
     */
    private static void assertRequirements(final TestCycle testCycle, final String methodName,
                                           final int expectedSize, final String... expectedAnimals) {
        final var processor = new ObjectsUsageMethodProcessor(methodName);
        final var path = TutorUtils.getPathToSource(getTestClass());
        SpoonUtils.process(testCycle, path, processor);

        final var testAnimals = Stream.of(expectedAnimals)
            .map(animal -> animal.startsWith("Rabbit") ? animal : String.format("Test%s", animal))
            .collect(Collectors.toSet());


        final var actualAnimals = processor.getTypes().stream()
            .map(Object::toString).map(clazz -> clazz.replaceAll(".*\\.", ""))
            .collect(Collectors.toSet());

        int actualSize = 0;

        // Check found animals
        for (final var expected : actualAnimals) {
            if (testAnimals.remove(expected)) {
                actualSize++;
            }
        }

        // Check number of animals
        Assertions.assertTrue(expectedSize <= actualSize,
            TutorMessage.H2_3_TEST_ANIMAL_SIZE_MISMATCH.format(expectedSize, actualSize));
    }

    /**
     * Tests whether the JUnit test method is correctly defined.
     *
     * @param methodName the name of the JUnit test method to check
     */
    private static void assertJunitMethod(final String methodName) {
        final var clazz = getTestClass();
        final var method = TutorUtils.assertMethod(clazz, methodName);

        // Modifier
        final var expected = Modifier.PRIVATE.negate();
        TutorUtils.assertModifiers(expected, method);
        Assertions.assertNotNull(method.getAnnotation(Test.class));

        // Invoke method
        try {
            final var constructor = TutorUtils.assertConstructor(clazz);
            final var instance = TutorUtils.invokeConstructor(constructor);
            method.invoke(instance);
        } catch (Exception e) {
            Assertions.fail(TutorConstants.ASSERTION_FAILED, e);
        }
    }

    /* *********************************************************************
     *                            Class Header                             *
     **********************************************************************/

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

    /* *********************************************************************
     *                              Method 1                               *
     **********************************************************************/

    /**
     * Defines the JUnit test cases related to the method {@link TutorConstants#H2_3_CLASS_NAME_1}.
     */
    @Nested
    @DisplayName("Criterion: Method testGetTypeOfVertebrate")
    public final class TestMethod1 {

        /**
         * The expected animals used to check the test method.
         */
        private final String[] expectedAnimals = {
            TutorConstants.H2_1_CLASS_NAME_2, TutorConstants.H2_1_CLASS_NAME_3,
            TutorConstants.H2_1_CLASS_NAME_4, TutorConstants.H2_1_CLASS_NAME_5,
            TutorConstants.H2_1_CLASS_NAME_6, TutorConstants.H2_1_CLASS_NAME_7,
            TutorConstants.H2_1_CLASS_NAME_8
        };

        @Test

        @ExtendWith({TestCycleResolver.class, JagrExecutionCondition.class})
        @DisplayName("Criterion: Requirements - At least 3 possible animals")
        public void testRequirements3(final TestCycle testCycle) {
            assertRequirements(testCycle, TutorConstants.H2_3_METHOD_NAME_1, 3, expectedAnimals);
        }

        @Test
        @ExtendWith({TestCycleResolver.class, JagrExecutionCondition.class})
        @DisplayName("Criterion: Requirements - At least 6 possible animals")
        public void testRequirements6(final TestCycle testCycle) {
            assertRequirements(testCycle, TutorConstants.H2_3_METHOD_NAME_1, 6, expectedAnimals);
        }

        @Test
        @DisplayName("Criterion: JUnit method")
        public void testMethod() {
            assertJunitMethod(TutorConstants.H2_3_METHOD_NAME_1);
        }
    }

    /* *********************************************************************
     *                              Method 2                               *
     **********************************************************************/

    /**
     * Defines the JUnit test cases related to the method {@link TutorConstants#H2_3_CLASS_NAME_2}.
     */
    @Nested
    @DisplayName("Criterion: Method testFilterLagomorphs")
    public final class TestMethod2 {

        /**
         * The expected animals used to check the test method.
         */
        private final String[] expectedAnimals = {
            TutorConstants.H2_1_CLASS_NAME_1, TutorConstants.H2_1_CLASS_NAME_2,
            TutorConstants.H2_1_CLASS_NAME_3, TutorConstants.H2_1_CLASS_NAME_4,
            TutorConstants.H2_1_CLASS_NAME_5, TutorConstants.H2_1_CLASS_NAME_6,
            TutorConstants.H2_1_CLASS_NAME_7, TutorConstants.H2_1_CLASS_NAME_8,
            TutorConstants.H2_1_CLASS_NAME_10
        };

        @Test
        @ExtendWith({TestCycleResolver.class, JagrExecutionCondition.class})
        @DisplayName("Criterion: Requirements - At least 4 possible animals")
        public void testRequirements4(final TestCycle testCycle) {
            assertRequirements(testCycle, TutorConstants.H2_3_METHOD_NAME_2, 4, expectedAnimals);
        }

        @Test
        @ExtendWith({TestCycleResolver.class, JagrExecutionCondition.class})
        @DisplayName("Criterion: Requirements - At least 7 possible animals")
        public void testRequirements7(final TestCycle testCycle) {
            assertRequirements(testCycle, TutorConstants.H2_3_METHOD_NAME_2, 7, expectedAnimals);
        }

        @Test
        @DisplayName("Criterion: JUnit method")
        public void testMethod() {
            assertJunitMethod(TutorConstants.H2_3_METHOD_NAME_2);
        }
    }

    /* *********************************************************************
     *                              Method 3                               *
     **********************************************************************/

    /**
     * Defines the JUnit test cases related to the method {@link TutorConstants#H2_3_CLASS_NAME_3}.
     */
    @Nested
    @DisplayName("Criterion: Method testGetTypesOfMammals")
    public final class TestMethod3 {

        /**
         * The expected animals used to check the test method.
         */
        private final String[] expectedAnimals = {
            TutorConstants.H2_1_CLASS_NAME_3, TutorConstants.H2_1_CLASS_NAME_5,
            TutorConstants.H2_1_CLASS_NAME_6, TutorConstants.H2_1_CLASS_NAME_7,
            TutorConstants.H2_1_CLASS_NAME_8, TutorConstants.H2_1_CLASS_NAME_10
        };

        @Test
        @ExtendWith({TestCycleResolver.class, JagrExecutionCondition.class})
        @DisplayName("Criterion: Requirements - At least 3 possible animals")
        public void testRequirements3(final TestCycle testCycle) {
            assertRequirements(testCycle, TutorConstants.H2_3_METHOD_NAME_3, 3, expectedAnimals);
        }

        @Test
        @ExtendWith({TestCycleResolver.class, JagrExecutionCondition.class})
        @DisplayName("Criterion: Requirements - At least 5 possible animals")
        public void testRequirements5(final TestCycle testCycle) {
            assertRequirements(testCycle, TutorConstants.H2_3_METHOD_NAME_3, 5, expectedAnimals);
        }

        @Test
        @DisplayName("Criterion: JUnit method")
        public void testMethod() {
            assertJunitMethod(TutorConstants.H2_3_METHOD_NAME_3);
        }
    }
}
