package h09.h1;

import h09.TutorUtils;
import h09.TutorUtils.Modifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Defines the JUnit test cases for the class defined in H1.1.
 *
 * @author Nhan Huynh, Darya Nikitina
 */
@DisplayName("Criterion: Class Traits")
final class TutorTest_H1_1 {

  /**
   * The name of the package where the class for the test cases should exist.
   */
  private static final String PACKAGE_NAME = "h09.h1";

  /**
   * The name of the class which should be tested.
   */
  private static final String CLASS_NAME = "Traits";

  /**
   * The name of the field which defines the filter operation.
   */
  private static final String FIELD_NAME_FILTER = "pred";

  /**
   * The name of the field which defines the map operation.
   */
  private static final String FIELD_NAME_MAP = "fct";

  /**
   * The name of the field which defines the fold operation.
   */
  private static final String FIELD_NAME_FOLD = "op";

  /**
   * The name of the method which defines the initial value for the fold operation.
   */
  private static final String FIELD_NAME_INITIAL = "init";

  /**
   * The name of the method which defines the filter operation.
   */
  private static final String METHOD_NAME_FILTER = String.format("get%s%s",
    FIELD_NAME_FILTER.substring(0, 1).toUpperCase(), FIELD_NAME_FILTER.substring(1));

  /**
   * The name of the method which defines the map operation.
   */
  private static final String METHOD_NAME_MAP = String.format("get%s%s",
    FIELD_NAME_MAP.substring(0, 1).toUpperCase(), FIELD_NAME_MAP.substring(1));

  /**
   * The name of the method which defines the fold operation.
   */
  private static final String METHOD_NAME_FOLD = String.format("get%s%s",
    FIELD_NAME_FOLD.substring(0, 1).toUpperCase(), FIELD_NAME_FOLD.substring(1));

  /**
   * The name of the method which defines the initial value for the fold operation.
   */
  private static final String METHOD_NAME_INITIAL = String.format("get%s%s",
    FIELD_NAME_INITIAL.substring(0, 1).toUpperCase(), FIELD_NAME_INITIAL.substring(1));

  /**
   * Returns the class instance of the class {@value CLASS_NAME} which should be tested.
   *
   * @return the class instance of the class {@value CLASS_NAME} which should be tested.
   */
  private static Class<?> getTestClass() {
    return TutorUtils.getClass(PACKAGE_NAME, CLASS_NAME);
  }

  /**
   * Tests whether the type parameters X, Y and Z are correctly defined.
   */
  private static void assertTypeParameters() {
    final var typeVariables = getTestClass().getTypeParameters();

    // Check if type parameters exists
    Assertions.assertNotNull(typeVariables,
      String.format("The class %s should contain the type parameter X, Y and Z, given %s.",
        CLASS_NAME, Arrays.toString(typeVariables)));

    // Check number of type parameters
    Assertions.assertEquals(3, typeVariables.length,
      String.format("The class %s should contain the type parameter X, Y and Z, given %s.",
        CLASS_NAME, Arrays.toString(typeVariables)));

    // Check name of the type parameters
    final String[] types = {"X", "Y", "Z"};
    for (int i = 0; i < types.length; i++) {
      final var expected = types[i];
      final var actual = typeVariables[i].getTypeName();
      Assertions.assertEquals(expected, actual,
        String.format("The class %s expected the type parameter %s, given %s.",
          CLASS_NAME, expected, actual));
    }
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
      TutorUtils.assertModifiers(getTestClass(), List.of(Modifier.PUBLIC),
        List.of(Modifier.ABSTRACT, Modifier.FINAL, Modifier.STATIC));
    }

    @Test
    @DisplayName("Criterion: No extension")
    void testExtension() {
      final var superClass = getTestClass().getSuperclass();
      Assertions.assertEquals(Object.class, superClass,
        String.format("The class should not extend another class, given %s.", superClass));
    }

    @Test
    @DisplayName("Criterion: Type parameter X, Y, Z")
    void testTypeParameters() {
      assertTypeParameters();
    }
  }

  /**
   * Defines the JUnit tests cases related to the field {@value FIELD_NAME_FILTER}.
   */
  @Nested
  @DisplayName("Criterion: Field for filter operation")
  final class TestFieldPred {

    @Test
    @DisplayName("Criterion: Only modifiers private final")
    void testModifiers() {
      final var field = TutorUtils.getField(getTestClass(), FIELD_NAME_FILTER);
      TutorUtils.assertModifiers(field, List.of(Modifier.PRIVATE, Modifier.FINAL),
        List.of(Modifier.STATIC));
    }

    @Test
    @DisplayName("Criterion: Type Predicate<X>")
    void testType() {
      final var field = TutorUtils.getField(getTestClass(), FIELD_NAME_FILTER);
      final var type = field.getGenericType();
      TutorUtils.assertGenericType(Predicate.class, "? super X", type);
    }

    @Test
    @DisplayName("Criterion: Getter method")
    void testGetter() {
      final var method = TutorUtils.getMethod(getTestClass(), METHOD_NAME_FILTER);

      // Check modifier
      TutorUtils.assertModifiers(method, List.of(Modifier.PUBLIC), List.of(Modifier.STATIC,
        Modifier.FINAL));

      // Check return type
      final var expected = Predicate.class;
      final var actual = method.getReturnType();
      Assertions.assertEquals(expected, actual,
        String.format("Expected return type %s, given %s.", expected, actual));

      final var name = method.getGenericReturnType().getTypeName();
      Assertions.assertEquals(
        String.format("%s<? super X>", Predicate.class.getCanonicalName()), name,
        String.format("Expected return type name ? super X, given %s.", name));
    }
  }

  /**
   * Defines the JUnit tests cases related to the field {@value FIELD_NAME_MAP}.
   */
  @Nested
  @DisplayName("Criterion: Field for map operation")
  final class TestFieldFct {

    @Test
    @DisplayName("Criterion: Only modifiers private final")
    void testModifiers() {
      final var field = TutorUtils.getField(getTestClass(), FIELD_NAME_MAP);
      TutorUtils.assertModifiers(field, List.of(Modifier.PRIVATE, Modifier.FINAL),
        List.of(Modifier.STATIC));
    }

    @Test
    @DisplayName("Criterion: Type Predicate<X>")
    void testType() {
      final var field = TutorUtils.getField(getTestClass(), FIELD_NAME_MAP);
      final var type = field.getGenericType();
      TutorUtils.assertGenericType(Function.class, "? super X, ? extends Y", type);
    }

    @Test
    @DisplayName("Criterion: Getter method")
    void testGetter() {
      final var method = TutorUtils.getMethod(getTestClass(), METHOD_NAME_MAP);

      // Check modifier
      TutorUtils.assertModifiers(method, List.of(Modifier.PUBLIC), List.of(Modifier.STATIC,
        Modifier.FINAL));

      // Check return type
      final var expected = Function.class;
      final var actual = method.getReturnType();
      Assertions.assertEquals(expected, actual,
        String.format("Expected return type %s, given %s.", expected, actual));

      final var name = method.getGenericReturnType().getTypeName();
      Assertions.assertEquals(
        String.format("%s<? super X, ? extends Y>", Function.class.getCanonicalName()), name,
        String.format("Expected return type name ? super X, given %s.", name));
    }
  }

  /**
   * Defines the JUnit tests cases related to the field {@value FIELD_NAME_FOLD}
   */
  @Nested
  @DisplayName("Criterion: Field for fold operation")
  final class TestFieldOp {

    @Test
    @DisplayName("Criterion: Only modifiers private final")
    void testModifiers() {
      final var field = TutorUtils.getField(getTestClass(), FIELD_NAME_FOLD);
      TutorUtils.assertModifiers(field, List.of(Modifier.PRIVATE, Modifier.FINAL),
        List.of(Modifier.STATIC));
    }

    @Test
    @DisplayName("Criterion: Type BiFunction<Y, Z, Z>")
    void testType() {
      final var field = TutorUtils.getField(getTestClass(), FIELD_NAME_FOLD);
      final var type = field.getGenericType();
      TutorUtils.assertGenericType(BiFunction.class, "Z, ? super Y, Z", type);
    }

    @Test
    @DisplayName("Criterion: Getter method")
    void testGetter() {
      final var method = TutorUtils.getMethod(getTestClass(), METHOD_NAME_FOLD);

      // Check modifier
      TutorUtils.assertModifiers(method, List.of(Modifier.PUBLIC), List.of(Modifier.STATIC,
        Modifier.FINAL));

      // Check return type
      final var expected = BiFunction.class;
      final var actual = method.getReturnType();
      Assertions.assertEquals(expected, actual,
        String.format("Expected return type %s, given %s.", expected, actual));

      final var name = method.getGenericReturnType().getTypeName();
      Assertions.assertEquals(
        String.format("%s<Z, ? super Y, Z>", BiFunction.class.getCanonicalName()), name,
        String.format("Expected return type name ? super X, given %s.", name));
    }
  }

  /**
   * Defines the JUnit tests cases related to the field {@value FIELD_NAME_INITIAL}.
   */
  @Nested
  @DisplayName("Criterion: Field for the initial value of the fold operation")
  final class TestFieldInit {

    @Test
    @DisplayName("Criterion: Only modifiers private final")
    void testModifiers() {
      final var field = TutorUtils.getField(getTestClass(), FIELD_NAME_INITIAL);
      TutorUtils.assertModifiers(field, List.of(Modifier.PRIVATE, Modifier.FINAL),
        List.of(Modifier.STATIC));
    }

    @Test
    @DisplayName("Criterion: Type BiFunction<Y, Z, Z>")
    void testType() {
      final var field = TutorUtils.getField(getTestClass(), FIELD_NAME_INITIAL);
      final var type = field.getGenericType();
      final var expected = "Z";
      final var actual = type.getTypeName();
      Assertions.assertEquals(expected, actual,
        String.format("Expected type %s, given %s.", expected, actual));
    }

    @Test
    @DisplayName("Criterion: Getter method")
    void testGetter() {
      final var method = TutorUtils.getMethod(getTestClass(), METHOD_NAME_INITIAL);

      // Check modifier
      TutorUtils.assertModifiers(method, List.of(Modifier.PUBLIC), List.of(Modifier.STATIC,
        Modifier.FINAL));

      // Check return type
      final var expected = Object.class;
      final var actual = method.getReturnType();
      Assertions.assertEquals(expected, actual,
        String.format("Expected return type %s, given %s.", expected, actual));

      final var name = method.getGenericReturnType().getTypeName();
      Assertions.assertEquals("Z", name,
        String.format("Expected return type name ? super X, given %s.", name));
    }
  }

  /**
   * Defines the JUnit tests cases related to the constructor.
   */
  @Nested
  @DisplayName("Criterion: Constructor")
  final class TestConstructor {

    @Test
    @DisplayName("Criterion: Only modifier public")
    void testModifiers() {
      final var constructor = TutorUtils.getConstructor(
        getTestClass(), Predicate.class, Function.class, BiFunction.class, Object.class
      );
      TutorUtils.assertModifiers(constructor, List.of(Modifier.PUBLIC));
    }

    @Test
    @DisplayName("Criterion: Parameter Predicate<X>, Function<X, Y>, BiFunction<Y, Z, Z>, Z")
    void testParameterTypes() {
      // Get constructor
      final var constructor = TutorUtils.getConstructor(
        getTestClass(), Predicate.class, Function.class, BiFunction.class, Object.class
      );

      // Check number of parameters
      final var types = constructor.getGenericParameterTypes();
      Assertions.assertEquals(4, types.length,
        String.format("The constructor must have 4 parameters, given: %s", Arrays.toString(types)));

      // Check parameter types
      @SuppressWarnings("unchecked") final Entry<Class<?>, String>[] expected =
        (Entry<Class<?>, String>[]) Array.newInstance(Entry.class, 3);
      expected[0] = new SimpleEntry<>(Predicate.class, "? super X");
      expected[1] = new SimpleEntry<>(Function.class, "? super X, ? extends Y");
      expected[2] = new SimpleEntry<>(BiFunction.class, "Z, ? super Y, Z");

      for (int i = 0; i < expected.length; i++) {
        final var entry = expected[i];
        TutorUtils.assertGenericType(entry.getKey(), entry.getValue(), types[i]);
      }

      final var z = types[3].getTypeName();
      Assertions.assertEquals("Z", z,
        String.format("The type of the init must be Z, given: %s", z));
    }

    @Test
    @DisplayName("Criterion: Initialization of fields")
    void testFields() {
      final Predicate<Integer> expectedFilter = x -> x < 10;
      final Function<Integer, Integer> expectedMap = x -> x;
      final BiFunction<Integer, Integer, Integer> expectedFold = (a, b) -> a * b;
      final Integer expectedInit = 1;

      final var traitsConstructor = TutorUtils.getConstructor(getTestClass(), Predicate.class,
        Function.class, BiFunction.class, Object.class);

      final var instance = TutorUtils.invokeConstructor(traitsConstructor, expectedFilter,
        expectedMap, expectedFold, expectedInit);

      // Check if fields are initialized
      final var actualFilter = TutorUtils.getField(instance, FIELD_NAME_FILTER);
      final var actualMap = TutorUtils.getField(instance, FIELD_NAME_MAP);
      final var actualFold = TutorUtils.getField(instance, FIELD_NAME_FOLD);
      final var actualInit = TutorUtils.getField(instance, FIELD_NAME_INITIAL);

      Assertions.assertEquals(expectedFilter, TutorUtils.getContent(actualFilter, instance),
        String.format("Expected the type %s for the filter operation, given %s",
          expectedFilter, actualFilter));
      Assertions.assertEquals(expectedMap, TutorUtils.getContent(actualMap, instance),
        String.format("Expected the type %s for the map operation, given %s",
          expectedMap, actualMap));
      Assertions.assertEquals(expectedFold, TutorUtils.getContent(actualFold, instance),
        String.format("Expected the type %s for the fold operation, given %s",
          expectedFold, actualFold));
      Assertions.assertEquals(expectedInit, TutorUtils.getContent(actualInit, instance),
        String.format("Expected the type %s for the initial value for the fold operation, given %s",
          expectedInit, actualInit));
    }
  }
}
