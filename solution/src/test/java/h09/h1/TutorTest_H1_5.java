package h09.h1;

import h09.TutorUtils;
import h09.TutorUtils.Modifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Defines the JUnit test cases for the class defined in H1.5.
 *
 * @author Nhan Huynh, Darya Nikitina
 */
@DisplayName("Criterion: Class FunctionFactory")
final class TutorTest_H1_5 {

  /**
   * The name of the package where the class for the test cases should exist.
   */
  private static final String PACKAGE_NAME = "h09.h1";

  /**
   * The name of the class which should be tested.
   */
  private static final String CLASS_NAME = "FunctionFactory";

  /**
   * The name of the class parameter of the factory method.
   */
  private static final String CLASS_PARAMETER = "Traits";


  private static final String METHOD_FORMAL_RETURN_TYPE = "FunctionWithFilterMapAndFold";

  /**
   * The name of the first factory method which only supports filter, map and fold operation.
   */
  private static final String METHOD_NAME_1 = "createFunctionWithFilterMapAndFold";

  /**
   * The actual return type of the method {@value METHOD_NAME_1}.
   */
  private static final String METHOD_RETURN_TYPE_1 = "MyFunctionWithFilterMapAndFold1";

  /**
   * The name of the first factory method which only supports filter, map , combine and fold
   * operation.
   */
  private static final String METHOD_NAME_2 = "createFunctionWithFilterMapFoldAndCombine";

  /**
   * The actual return type of the method {@value METHOD_NAME_2}.
   */
  private static final String METHOD_RETURN_TYPE_2 = "MyFunctionWithAdjacent";

  /**
   * Returns the class instance of the class {@value CLASS_NAME} which should be tested.
   *
   * @return the class instance of the class {@value CLASS_NAME} which should be tested.
   */
  private static Class<?> getTestClass() {
    return TutorUtils.getClass(PACKAGE_NAME, CLASS_NAME);
  }

  /**
   * Returns the class instance of the class {@value CLASS_PARAMETER} which should be tested.
   *
   * @return the class instance of the class {@value CLASS_PARAMETER} which should be tested.
   */
  private static Class<?> getTestClassParameter() {
    return TutorUtils.getClass(PACKAGE_NAME, CLASS_PARAMETER);
  }

  /**
   * Defines the JUnit tests cases related to the class header.
   */
  @Nested
  @DisplayName("Criterion: Class Header")
  final class TestClassHeader {

    @Test
    @DisplayName("Criterion: Only modifiers public final")
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
   * Defines the JUnit tests cases related to the constructor.
   */
  @Nested
  @DisplayName("Criterion: Constructor")
  final class TestConstructor {

    @Test
    @DisplayName("Criterion: Only modifier private")
    void testModifiers() {
      final var constructor = TutorUtils.getConstructor(getTestClass());
      TutorUtils.assertModifiers(constructor, List.of(Modifier.PRIVATE));
    }
  }

  /**
   * Defines the JUnit tests cases related to the method {@value METHOD_NAME_1}.
   */
  @Nested
  @DisplayName("Criterion: Method createFunctionWithFilterMapAndFold")
  final class TestMethod1 {

    @Test
    @DisplayName("Criterion: Only modifiers public static")
    void testModifiers() {
      final var method = TutorUtils.getMethod(getTestClass(), METHOD_NAME_1,
        getTestClassParameter());
      TutorUtils.assertModifiers(method, List.of(Modifier.PUBLIC, Modifier.STATIC));
    }

    @Test
    @DisplayName("Criterion: Parameter Traits<X, Y, Z>")
    void testParameters() {
      final var classParameter = getTestClassParameter();
      final var method = TutorUtils.getMethod(getTestClass(), METHOD_NAME_1, classParameter);
      final var types = method.getParameterTypes();

      // Check number of parameters
      final var expectedLength = 1;
      final var actualLength = types.length;
      Assertions.assertEquals(expectedLength, actualLength,
        String.format("The method should contain %s type parameter, given %s.", expectedLength,
          actualLength));

      // Check type of parameters
      final var actualClass = types[0];
      Assertions.assertEquals(classParameter, actualClass,
        String.format("Expected the parameter of type %s, given %s.", classParameter,
          actualClass));

      // Check generic types
      final var parametrized = method.getParameters()[0].getParameterizedType();
      TutorUtils.assertGenericType(classParameter, "X, Y, Z", parametrized);
    }

    @Test
    @DisplayName("Criterion: Return type FunctionWithFilterMapAndFold<X, Y, Z>")
    void testReturnType() {
      final var method = TutorUtils.getMethod(getTestClass(), METHOD_NAME_1,
        getTestClassParameter());
      final var returnType = TutorUtils.getClass(PACKAGE_NAME, METHOD_FORMAL_RETURN_TYPE);

      Assertions.assertEquals(returnType, method.getReturnType());
      TutorUtils.assertGenericType(returnType, "X, Y, Z", method.getGenericReturnType());
    }

    @Test
    @DisplayName("Criterion: Return value FunctionWithFilterMapAndFold2<X, Y, Z>")
    void testReturnValue() {
      // Traits object
      final var classParameter = getTestClassParameter();

      final Predicate<Integer> expectedFilter = x -> x % 2==0;
      final Function<Integer, String> expectedMap = String::valueOf;
      final BiFunction<String, Integer, Integer> expectedFold = (s, i) -> s.length() + i;
      final var expectedInit = 523;

      final var traitsConstructor = TutorUtils.getConstructor(classParameter, Predicate.class,
        Function.class, BiFunction.class, Object.class);

      final var traits = TutorUtils.invokeConstructor(traitsConstructor, expectedFilter,
        expectedMap, expectedFold, expectedInit);

      // Invoke Method
      final var method = TutorUtils.getMethod(getTestClass(), METHOD_NAME_1, classParameter);

      final var actual = TutorUtils.invokeMethod(method, null, traits);
      final var expected = TutorUtils.getClass(PACKAGE_NAME, METHOD_RETURN_TYPE_1);
      Assertions.assertEquals(expected, actual.getClass(),
        String.format("Expected return value %s, given: %s.", expected, actual.getClass()));
    }
  }

  /**
   * Defines the JUnit tests cases related to the method {@value METHOD_NAME_2}.
   */
  @Nested
  @DisplayName("Criterion: Method createFunctionWithFilterMapFoldAndCombine")
  final class TestMethod2 {

    @Test
    @DisplayName("Criterion: Only modifiers public static")
    void testModifiers() {
      final var method = TutorUtils.getMethod(getTestClass(), METHOD_NAME_2,
        getTestClassParameter());
      TutorUtils.assertModifiers(method, List.of(Modifier.PUBLIC, Modifier.STATIC));
    }

    @Test
    @DisplayName("Criterion: Parameter Traits<X, Y, Z>")
    void testParameters() {
      final var classParameter = getTestClassParameter();
      final var method = TutorUtils.getMethod(getTestClass(), METHOD_NAME_2, classParameter);
      final var types = method.getParameterTypes();

      // Check number of parameters
      final var expectedLength = 1;
      final var actualLength = types.length;
      Assertions.assertEquals(expectedLength, actualLength,
        String.format("The method should contain %s type parameter, given %s.", expectedLength,
          actualLength));

      // Check type of parameters
      final var actualClass = types[0];
      Assertions.assertEquals(classParameter, actualClass,
        String.format("Expected the parameter of type %s, given %s.", classParameter,
          actualClass));

      // Check generic types
      final var parametrized = method.getParameters()[0].getParameterizedType();
      TutorUtils.assertGenericType(classParameter, "X, Y, Z", parametrized);
    }

    @Test
    @DisplayName("Criterion: Return type FunctionWithFilterMapAndFold<X, Y, Z>")
    void testReturnType() {
      final var method = TutorUtils.getMethod(getTestClass(), METHOD_NAME_2,
        getTestClassParameter());
      final var returnType = TutorUtils.getClass(PACKAGE_NAME, METHOD_FORMAL_RETURN_TYPE);

      Assertions.assertEquals(returnType, method.getReturnType());
      TutorUtils.assertGenericType(returnType, "X, Y, Z", method.getGenericReturnType());
    }

    @Test
    @DisplayName("Criterion: Return value FunctionWithFilterMapAndFold2<X, Y, Z>")
    void testReturnValue() {
      // Traits object
      final var classParameter = getTestClassParameter();

      final Predicate<Integer> expectedFilter = x -> x % 2==0;
      final Function<Integer, String> expectedMap = String::valueOf;
      final BiFunction<String, Integer, Integer> expectedFold = (s, i) -> s.length() + i;
      final var expectedInit = 523;

      final var traitsConstructor = TutorUtils.getConstructor(classParameter, Predicate.class,
        Function.class, BiFunction.class, Object.class);

      final var traits = TutorUtils.invokeConstructor(traitsConstructor, expectedFilter,
        expectedMap, expectedFold, expectedInit);

      // Invoke Method
      final var method = TutorUtils.getMethod(getTestClass(), METHOD_NAME_2, classParameter);

      final var actual = TutorUtils.invokeMethod(method, null, traits);
      final var expected = TutorUtils.getClass(PACKAGE_NAME, METHOD_RETURN_TYPE_2);
      Assertions.assertEquals(expected, actual.getClass(),
        String.format("Expected return value %s, given: %s.", expected, actual.getClass()));
    }
  }
}
