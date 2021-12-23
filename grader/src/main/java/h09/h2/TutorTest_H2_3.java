package h09.h2;

import h09.utils.Modifier;
import h09.utils.TutorConstants;
import h09.utils.TutorMessage;
import h09.utils.TutorUtils;
import h09.utils.spoon.ConstructorsCallMethodBodyProcessor;
import h09.utils.spoon.SpoonUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.sourcegrade.jagr.api.testing.TestCycle;
import spoon.reflect.code.CtConstructorCall;
import spoon.reflect.reference.CtTypeReference;

import java.lang.reflect.Constructor;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@TestForSubmission("h09")
@DisplayName("Criterion: Class TestBiologyHierarchy")
public final class TutorTest_H2_3 {

  private static Class<?> getTestClass() {
    return TutorUtils.assertClass(TutorConstants.H2_PACKAGE_NAME, TutorConstants.H2_3_CLASS_NAME);
  }

  private static Constructor<?> getTestConstructor() {
    final var clazz = getTestClass();
    return TutorUtils.assertConstructor(clazz);
  }

  private static void assertRequirements(final TestCycle testCycle, final String methodName,
                                         final int expectedSize, final String... expectedAnimals) {
    final var processor = new ConstructorsCallMethodBodyProcessor(methodName);
    SpoonUtils.process(testCycle, TutorConstants.H2_3_PATH_TO_SOURCE, processor);

    final var testAnimals = Stream.of(expectedAnimals)
      .map(animal -> animal.equals("Rabbit") ? animal:String.format("Test%s", animal))
      .collect(Collectors.toList());

    final var actual = processor.getConstructors().stream()
      .map(CtConstructorCall::getType)
      .map(CtTypeReference::getSimpleName)
      .distinct()
      .filter(name -> name.startsWith("Test") || name.equals("Rabbit"))
      .collect(Collectors.toList());

    // Check number of animals
    final var actualSize = actual.size();
    Assertions.assertTrue(expectedSize <= actualSize,
      TutorMessage.H2_3_TEST_ANIMAL_SIZE_MISMATCH.format(expectedSize, actualSize));

    // Check correct animals
    final Set<String> found = new LinkedHashSet<>();
    for (final var animal : testAnimals) {
      if (actual.contains(animal)) {
        found.add(animal);
      }
    }

    final var foundSize = found.size();
    Assertions.assertEquals(actualSize, foundSize,
      TutorMessage.H2_3_TEST_ANIMAL_MISMATCH.format(testAnimals, found));
  }

  @Nested
  @DisplayName("Criterion: Class header")
  public final class TestClassHeader {

    @Test
    @DisplayName("Criterion: Only modifiers package-private")
    void testModifiers() {
      final var actual = getTestClass();
      final var expected = Modifier.FINAL.negate().and(Modifier.PACKAGE_PRIVATE);
      TutorUtils.assertModifiers(expected, actual);
    }

    @Test
    @DisplayName("Criterion: No extension")
    void testExtension() {
      final var actual = getTestClass().getSuperclass();
      final var expected = Object.class;
      Assertions.assertEquals(expected, actual,
        TutorMessage.CLASS_EXTENSION_MISMATCH.format(expected, actual));
    }
  }

  @Nested
  @DisplayName("Criterion: Method testTypeOfVertebrate")
  public final class TestMethod1 {

    private final String[] expectedAnimals = {
      TutorConstants.H2_1_CLASS_NAME_2, TutorConstants.H2_1_CLASS_NAME_3,
      TutorConstants.H2_1_CLASS_NAME_4, TutorConstants.H2_1_CLASS_NAME_5,
      TutorConstants.H2_1_CLASS_NAME_6, TutorConstants.H2_1_CLASS_NAME_7,
      TutorConstants.H2_1_CLASS_NAME_8
    };

    @Test
    @DisplayName("Criterion: Requirements - At least 3 possible animals")
    void testRequirements3() {
      // TODO replace with Jagr API
      assertRequirements(null, TutorConstants.H2_3_METHOD_NAME_1, 3, expectedAnimals);
    }

    @Test
    @DisplayName("Criterion: Requirements - At least 7 possible animals")
    void testRequirements7() {
      // TODO replace with Jagr API
      assertRequirements(null, TutorConstants.H2_3_METHOD_NAME_1, 7, expectedAnimals);
    }
  }

  @Nested
  @DisplayName("Criterion: Method testReturnAsLagomorphs")
  public final class TestMethod2 {

    private final String[] expectedAnimals = {
      TutorConstants.H2_1_CLASS_NAME_1, TutorConstants.H2_1_CLASS_NAME_2,
      TutorConstants.H2_1_CLASS_NAME_3, TutorConstants.H2_1_CLASS_NAME_4,
      TutorConstants.H2_1_CLASS_NAME_5, TutorConstants.H2_1_CLASS_NAME_6,
      TutorConstants.H2_1_CLASS_NAME_7, TutorConstants.H2_1_CLASS_NAME_8,
      TutorConstants.H2_1_CLASS_NAME_10
    };

    @Test
    @DisplayName("Criterion: Requirements - At least 4 possible animals")
    void testRequirements4() {
      // TODO replace with Jagr API
      assertRequirements(null, TutorConstants.H2_3_METHOD_NAME_2, 4, expectedAnimals);
    }

    @Test
    @DisplayName("Criterion: Requirements - At least 9 possible animals")
    void testRequirements9() {
      // TODO replace with Jagr API
      assertRequirements(null, TutorConstants.H2_3_METHOD_NAME_2, 9, expectedAnimals);
    }
  }

  @Nested
  @DisplayName("Criterion: Method testTypeOfMammals")
  public final class TestMethod3 {

    private final String[] expectedAnimals = {
      TutorConstants.H2_1_CLASS_NAME_3, TutorConstants.H2_1_CLASS_NAME_5,
      TutorConstants.H2_1_CLASS_NAME_6, TutorConstants.H2_1_CLASS_NAME_7,
      TutorConstants.H2_1_CLASS_NAME_8, TutorConstants.H2_1_CLASS_NAME_10
    };

    @Test
    @DisplayName("Criterion: Requirements - At least 3 possible animals")
    void testRequirements3() {
      // TODO replace with Jagr API
      assertRequirements(null, TutorConstants.H2_3_METHOD_NAME_3, 3, expectedAnimals);
    }

    @Test
    @DisplayName("Criterion: Requirements - At least 6 possible animals")
    void testRequirements6() {
      // TODO replace with Jagr API
      assertRequirements(null, TutorConstants.H2_3_METHOD_NAME_3, 6, expectedAnimals);
    }
  }
}