package h09.h2;

import h09.TutorUtils;
import h09.TutorUtils.Modifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * Defines the JUnit test cases for the class defined in H2.2.
 *
 * @author Nhan Huynh, Darya Nikitina
 */
@DisplayName("Criterion: Class BiologicalHierarchy")
final class TutorTest_H2_2 {

  /**
   * The name of the package where the class for the test cases should exist.
   */
  private static final String PACKAGE_NAME = "h09.h2";

  /**
   * The name of the class which should be tested.
   */
  private static final String CLASS_NAME = "BiologicalHierarchy";

  /**
   * The name of the class {@code Animal} which should be tested.
   */
  private static final String CLASS_NAME_ANIMAL = "Animal";

  /**
   * The name of the class {@code Vertebrate} which should be tested.
   */
  private static final String CLASS_NAME_VERTEBRATE = "Vertebrate";

  /**
   * The name of the class {@code Mammal} which should be tested.
   */
  private static final String CLASS_NAME_MAMMAL = "Mammal";

  /**
   * The name of the class {@code Bird} which should be tested.
   */
  private static final String CLASS_NAME_BIRD = "Bird";

  /**
   * The name of the class {@code Placental} which should be tested.
   */
  private static final String CLASS_NAME_PLACENTAL = "Placental";

  /**
   * The name of the class {@code Monotreme} which should be tested.
   */
  private static final String CLASS_NAME_MONOTREME = "Monotreme";

  /**
   * The name of the class {@code Lagomorpha} which should be tested.
   */
  private static final String CLASS_NAME_LAGOMORPHA = "Lagomorpha";

  /**
   * The name of the class {@code Rodent} which should be tested.
   */
  private static final String CLASS_NAME_RODENT = "Rodent";

  /**
   * The name of the class {@code Leporidae} which should be tested.
   */
  private static final String CLASS_NAME_LEPORIDAE = "Leporidae";

  /**
   * The name of the method specified in H2.2 (1).
   */
  private static final String METHOD_NAME_1 = "typeOfVertebrate";

  /**
   * The name of the method specified in H2.2 (2).
   */
  private static final String METHOD_NAME_2 = "returnAsLagomorphs";

  /**
   * The name of the method specified in H2.2 (3).
   */
  private static final String METHOD_NAME_3 = "typeOfMammals";

  /**
   * Returns the class instance of the class {@value CLASS_NAME} which should be tested.
   *
   * @return the class instance of the class {@value CLASS_NAME} which should be tested.
   */
  private static Class<?> getTestClass() {
    return TutorUtils.getClass(PACKAGE_NAME, CLASS_NAME);
  }

  /**
   * Returns the class instance of the specified animal.
   *
   * @param name the class name of the animal
   *
   * @return the class instance of the specified animal
   */
  private static Class<?> getTestAnimalClass(final String name) {
    return TutorUtils.getClass(PACKAGE_NAME, String.format("TutorTest%s", name));
  }

  /**
   * Returns an instance of the specified animal.
   *
   * @param name the class name of the animal
   *
   * @return an instance of the specified animal
   */
  private static Object getTestAnimal(final String name) {
    final var clazz = getTestAnimalClass(name);
    final var constructor = TutorUtils.getConstructor(clazz);
    return TutorUtils.invokeConstructor(constructor);
  }

  /**
   * Defines the JUnit tests cases related to the class header.
   */
  @Nested
  @DisplayName("Criterion: Class Header")
  final class TestClassHeader {

    @Test
    @DisplayName("Criterion: Only modifiers public")
    void testModifiers() {
      TutorUtils.assertModifiers(getTestClass(), List.of(Modifier.PUBLIC, Modifier.FINAL),
        List.of(Modifier.STATIC, Modifier.ABSTRACT));
    }

    @Test
    @DisplayName("Criterion: No type parameter")
    void testTypeParameter() {
      final var types = getTestClass().getTypeParameters();
      Assertions.assertEquals(0, types.length,
        String.format("The class should not contain a generic type parameter, given: %s",
          types.length));
    }
  }

  /**
   * Defines the JUnit test cases related for the method {@code typeOfVertebrate}.
   */
  @Nested
  @DisplayName("Criterion: Method typeOfVertebrate")
  final class TestMethod1 {

    /**
     * Returns the method that should be tested.
     *
     * @return the method that should be tested.
     */
    private Method getMethod() {
      return TutorUtils.getMethod(getTestClass(), METHOD_NAME_1, getClassParameter());
    }

    /**
     * Returns the class instance of the parameter.
     *
     * @return the class instance of the parameter.
     */
    private Class<?> getClassParameter() {
      return TutorUtils.getClass(PACKAGE_NAME, CLASS_NAME_VERTEBRATE);
    }

    @Test
    @DisplayName("Criterion: Only modifiers public")
    void testModifiers() {
      final var method = getMethod();
      TutorUtils.assertModifiers(method, List.of(Modifier.PUBLIC),
        List.of(Modifier.FINAL, Modifier.ABSTRACT, Modifier.STATIC));
    }


    @Test
    @DisplayName("Criterion: Generic method <T extends Vertebrate>")
    void testGeneric() {
      final var method = getMethod();
      final var types = method.getTypeParameters();

      // Check generic type name
      final var expectedType = "T";
      final var actualType = types[0].getTypeName();
      Assertions.assertEquals(expectedType, actualType);

      // Check bound
      final var actualBound = types[0].getBounds();
      final var expectedLength = 1;
      final var actualLength = actualBound.length;

      Assertions.assertEquals(expectedLength, actualLength,
        String.format("Expected only %s bound, given %s.", expectedLength, actualLength));

      // Check bound type
      final var expectedBoundType = getClassParameter().getName();
      final var actualBoundType = actualBound[0].getTypeName();
      Assertions.assertEquals(expectedBoundType, actualBoundType,
        String.format("Expected bound of %s, given %s.", expectedBoundType, actualBoundType));
    }

    @Test
    @DisplayName("Criterion: Parameter T")
    void testParameters() {
      final var method = getMethod();
      final var parameters = method.getGenericParameterTypes();

      // Check parameter
      final var expectedParameters = 1;
      final var actualParameters = parameters.length;
      Assertions.assertEquals(expectedParameters, actualParameters,
        String.format("The method should have %s parameter(s), given %s.", expectedParameters,
          actualParameters));

      // Check type
      final var expectedType = "T";
      final var actualType = parameters[0].getTypeName();
      Assertions.assertEquals(expectedType, actualType,
        String.format("The type of the parameter should be %s, given %s.", expectedType,
          actualType));
    }

    @Test
    @DisplayName("Criterion: Result")
    void testResult() {
      final var clazz = getTestClass();
      final var constructor = TutorUtils.getConstructor(clazz);
      final var instance = TutorUtils.invokeConstructor(constructor);
      final var method = getMethod();

      Assertions.assertNull(
        TutorUtils.invokeMethod(method, instance, getTestAnimal(CLASS_NAME_VERTEBRATE)));
      Assertions.assertEquals(CLASS_NAME_MAMMAL,
        TutorUtils.invokeMethod(method, instance, getTestAnimal(CLASS_NAME_MAMMAL)));
      Assertions.assertEquals(CLASS_NAME_BIRD,
        TutorUtils.invokeMethod(method, instance, getTestAnimal(CLASS_NAME_BIRD)));
      Assertions.assertEquals(CLASS_NAME_MAMMAL,
        TutorUtils.invokeMethod(method, instance, getTestAnimal(CLASS_NAME_PLACENTAL)));
      Assertions.assertEquals(CLASS_NAME_MAMMAL,
        TutorUtils.invokeMethod(method, instance, getTestAnimal(CLASS_NAME_MONOTREME)));
      Assertions.assertEquals(CLASS_NAME_MAMMAL,
        TutorUtils.invokeMethod(method, instance, getTestAnimal(CLASS_NAME_LAGOMORPHA)));
    }
  }

  /**
   * Defines the JUnit test cases related for the method {@code returnAsLagomorphs:}.
   */
  @Nested
  @DisplayName("Criterion: Method returnAsLagomorphs:")
  final class TestMethod2 {

    /**
     * Returns the method that should be tested.
     *
     * @return the method that should be tested.
     */
    private Method getMethod() {
      return TutorUtils.getMethod(getTestClass(), METHOD_NAME_2, List.class);
    }

    @Test
    @DisplayName("Criterion: Only modifiers public")
    void testModifiers() {
      final var method = getMethod();
      TutorUtils.assertModifiers(method, List.of(Modifier.PUBLIC),
        List.of(Modifier.FINAL, Modifier.ABSTRACT, Modifier.STATIC));
    }

    @Test
    @DisplayName("Criterion: Parameter List<? super Lagomorph>")
    void testParameters() {
      final var method = getMethod();
      final var parameters = method.getGenericParameterTypes();

      // Check parameters
      final var expectedParameters = 1;
      final var actualParameters = parameters.length;
      Assertions.assertEquals(expectedParameters, actualParameters,
        String.format("The method should have %s parameter(s), given %s.", expectedParameters,
          actualParameters));

      // Check type
      final var actualType = parameters[0];
      TutorUtils.assertGenericType(List.class,
        String.format("? super %s.%s", PACKAGE_NAME, CLASS_NAME_LAGOMORPHA), actualType);
    }

    @Test
    @DisplayName("Criterion: Result List<Placental>")
    void testResultListOfPlacental() {
      final var clazz = getTestClass();
      final var constructor = TutorUtils.getConstructor(clazz);
      final var instance = TutorUtils.invokeConstructor(constructor);
      final var method = getMethod();

      final var placental = getTestAnimal(CLASS_NAME_PLACENTAL);
      final var rodent = getTestAnimal(CLASS_NAME_RODENT);
      final var lagomorpha = getTestAnimal(CLASS_NAME_LAGOMORPHA);
      final var leporidae = getTestAnimal(CLASS_NAME_LEPORIDAE);

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
    void testResultListOfMammal() {
      final var clazz = getTestClass();
      final var constructor = TutorUtils.getConstructor(clazz);
      final var instance = TutorUtils.invokeConstructor(constructor);
      final var method = getMethod();

      final var mammal = getTestAnimal(CLASS_NAME_MAMMAL);
      final var placental = getTestAnimal(CLASS_NAME_PLACENTAL);
      final var monotreme = getTestAnimal(CLASS_NAME_MONOTREME);
      final var rodent = getTestAnimal(CLASS_NAME_RODENT);
      final var lagomorpha = getTestAnimal(CLASS_NAME_LAGOMORPHA);
      final var leporidae = getTestAnimal(CLASS_NAME_LEPORIDAE);

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
    void testResultListOfVertebrate() {
      final var clazz = getTestClass();
      final var constructor = TutorUtils.getConstructor(clazz);
      final var instance = TutorUtils.invokeConstructor(constructor);
      final var method = getMethod();

      final var vertebrate = getTestAnimal(CLASS_NAME_VERTEBRATE);
      final var mammal = getTestAnimal(CLASS_NAME_MAMMAL);
      final var bird = getTestAnimal(CLASS_NAME_BIRD);
      final var placental = getTestAnimal(CLASS_NAME_PLACENTAL);
      final var monotreme = getTestAnimal(CLASS_NAME_MONOTREME);
      final var rodent = getTestAnimal(CLASS_NAME_RODENT);
      final var lagomorpha = getTestAnimal(CLASS_NAME_LAGOMORPHA);
      final var leporidae = getTestAnimal(CLASS_NAME_LEPORIDAE);

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
    void testResultListOfAnimal() {
      final var clazz = getTestClass();
      final var constructor = TutorUtils.getConstructor(clazz);
      final var instance = TutorUtils.invokeConstructor(constructor);
      final var method = getMethod();

      final var animal = getTestAnimal(CLASS_NAME_ANIMAL);
      final var vertebrate = getTestAnimal(CLASS_NAME_VERTEBRATE);
      final var mammal = getTestAnimal(CLASS_NAME_MAMMAL);
      final var bird = getTestAnimal(CLASS_NAME_BIRD);
      final var placental = getTestAnimal(CLASS_NAME_PLACENTAL);
      final var monotreme = getTestAnimal(CLASS_NAME_MONOTREME);
      final var rodent = getTestAnimal(CLASS_NAME_RODENT);
      final var lagomorpha = getTestAnimal(CLASS_NAME_LAGOMORPHA);
      final var leporidae = getTestAnimal(CLASS_NAME_LEPORIDAE);

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
    void testResultListOfObject() {
      final var clazz = getTestClass();
      final var constructor = TutorUtils.getConstructor(clazz);
      final var instance = TutorUtils.invokeConstructor(constructor);
      final var method = getMethod();

      final var animal = getTestAnimal(CLASS_NAME_ANIMAL);
      final var vertebrate = getTestAnimal(CLASS_NAME_VERTEBRATE);
      final var mammal = getTestAnimal(CLASS_NAME_MAMMAL);
      final var bird = getTestAnimal(CLASS_NAME_BIRD);
      final var placental = getTestAnimal(CLASS_NAME_PLACENTAL);
      final var monotreme = getTestAnimal(CLASS_NAME_MONOTREME);
      final var rodent = getTestAnimal(CLASS_NAME_RODENT);
      final var lagomorpha = getTestAnimal(CLASS_NAME_LAGOMORPHA);
      final var leporidae = getTestAnimal(CLASS_NAME_LEPORIDAE);

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

  /**
   * Defines the JUnit test cases related for the method {@code typeOfMammals}.
   */
  @Nested
  @DisplayName("Criterion: Method typeOfMammals")
  final class TestMethod3 {

    /**
     * Returns the method that should be tested.
     *
     * @return the method that should be tested.
     */
    private Method getMethod() {
      return TutorUtils.getMethod(getTestClass(), METHOD_NAME_3, List.class);
    }

    @Test
    @DisplayName("Criterion: Only modifiers public")
    void testModifiers() {
      final var method = getMethod();
      TutorUtils.assertModifiers(method, List.of(Modifier.PUBLIC),
        List.of(Modifier.FINAL, Modifier.ABSTRACT, Modifier.STATIC));
    }

    @Test
    @DisplayName("Criterion: Parameter List<? extends Mammal>")
    void testParameters() {
      final var method = getMethod();
      final var parameters = method.getGenericParameterTypes();

      // Check parameters
      final var expectedParameters = 1;
      final var actualParameters = parameters.length;
      Assertions.assertEquals(expectedParameters, actualParameters,
        String.format("The method should have %s parameter(s), given %s.", expectedParameters,
          actualParameters));

      // Check type
      final var actualType = parameters[0];
      TutorUtils.assertGenericType(List.class,
        String.format("? extends %s.%s", PACKAGE_NAME, CLASS_NAME_MAMMAL), actualType);
    }

    @Test
    @DisplayName("Criterion: Result List<Mammal>")
    void testResultListOfMammal() {
      final var clazz = getTestClass();
      final var constructor = TutorUtils.getConstructor(clazz);
      final var instance = TutorUtils.invokeConstructor(constructor);
      final var method = getMethod();

      final var mammal = getTestAnimal(CLASS_NAME_MAMMAL);
      final var placental = getTestAnimal(CLASS_NAME_PLACENTAL);
      final var monotreme = getTestAnimal(CLASS_NAME_MONOTREME);
      final var rodent = getTestAnimal(CLASS_NAME_RODENT);
      final var lagomorpha = getTestAnimal(CLASS_NAME_LAGOMORPHA);
      final var leporidae = getTestAnimal(CLASS_NAME_LEPORIDAE);

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
    void testResultListOfPlacental() {
      final var clazz = getTestClass();
      final var constructor = TutorUtils.getConstructor(clazz);
      final var instance = TutorUtils.invokeConstructor(constructor);
      final var method = getMethod();

      final var placental = getTestAnimal(CLASS_NAME_PLACENTAL);
      final var rodent = getTestAnimal(CLASS_NAME_RODENT);
      final var lagomorpha = getTestAnimal(CLASS_NAME_LAGOMORPHA);
      final var leporidae = getTestAnimal(CLASS_NAME_LEPORIDAE);

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
    void testResultListOfMonotreme() {
      final var clazz = getTestClass();
      final var constructor = TutorUtils.getConstructor(clazz);
      final var instance = TutorUtils.invokeConstructor(constructor);
      final var method = getMethod();

      final var monotreme = getTestAnimal(CLASS_NAME_MONOTREME);

      final var animals = List.of(
        monotreme
      );
      final var expected = List.of("Monotreme");

      Assertions.assertEquals(expected,
        TutorUtils.invokeMethod(method, instance, animals));
    }

    @Test
    @DisplayName("Criterion: Result List<Lagomorpha>")
    void testResultListOfLagomorph() {
      final var clazz = getTestClass();
      final var constructor = TutorUtils.getConstructor(clazz);
      final var instance = TutorUtils.invokeConstructor(constructor);
      final var method = getMethod();

      final var lagomorpha = getTestAnimal(CLASS_NAME_LAGOMORPHA);
      final var leporidae = getTestAnimal(CLASS_NAME_LEPORIDAE);

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
    void testResultListOfRodent() {
      final var clazz = getTestClass();
      final var constructor = TutorUtils.getConstructor(clazz);
      final var instance = TutorUtils.invokeConstructor(constructor);
      final var method = getMethod();

      final var rodent = getTestAnimal(CLASS_NAME_RODENT);

      final var animals = List.of(
        rodent
      );
      final var expected = List.of("Placental");

      Assertions.assertEquals(expected,
        TutorUtils.invokeMethod(method, instance, animals));
    }

    @Test
    @DisplayName("Criterion: Result List<Leporidae>")
    void testResultListOfLeporidae() {
      final var clazz = getTestClass();
      final var constructor = TutorUtils.getConstructor(clazz);
      final var instance = TutorUtils.invokeConstructor(constructor);
      final var method = getMethod();

      final var leporidae = getTestAnimal(CLASS_NAME_LEPORIDAE);

      final var animals = List.of(
        leporidae
      );
      final var expected = List.of("Placental");

      Assertions.assertEquals(expected,
        TutorUtils.invokeMethod(method, instance, animals));
    }
  }
}
