package h09.h1;

import h09.TutorUtils;
import h09.TutorUtils.Modifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Defines the JUnit test cases for the class and fields defined in H1.4.
 *
 * @author Nhan Huynh, Darya Nikitina
 */
@DisplayName("Criterion: Class MyFunctionWithAdjacent")
final class TutorTest_H1_4 {


  /**
   * The name of the package where the class for the test cases should exist.
   */
  private static final String PACKAGE_NAME = "h09.h1";

  /**
   * The name of the class which should be tested.
   */
  private static final String CLASS_NAME = "MyFunctionWithAdjacent";

  /**
   * The name of the super class of {@value CLASS_NAME} which should be tested.
   */
  private static final String CLASS_NAME_SUPER = "FunctionWithFilterMapAndFold";

  /**
   * The name of the class of the field traits.
   */
  private static final String CLASS_NAME_FIELD = "Traits";

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
   * The name of the field which defines the initial value for the fold operation.
   */
  private static final String FIELD_NAME_INITIAL = "init";

  /**
   * The name of the field that was added in {@value CLASS_NAME}.
   */
  private static final String FIELD_NAME_COMBINE = "combine";

  /**
   * The name of the abstract that transforms a set using filter, map and fold operation.
   */
  private static final String METHOD_NAME = "apply";

  /**
   * Returns the class instance of the class {@value #CLASS_NAME} which should be tested.
   *
   * @return the class instance of the class {@value #CLASS_NAME} which should be tested.
   */
  private static Class<?> getTestClass() {
    return TutorUtils.getClass(PACKAGE_NAME, CLASS_NAME);
  }

  /**
   * Returns the class instance of the field traits which should be tested.
   *
   * @return the class instance of the field traits which should be tested.
   */
  private static Class<?> getFieldClass() {
    return TutorUtils.getClass(PACKAGE_NAME, CLASS_NAME_FIELD);
  }

  /**
   * Returns the method instance {@value METHOD_NAME} which should be tested.
   *
   * @return the method instance {@value METHOD_NAME} which should be tested
   */
  private static Method getMethod() {
    return TutorUtils.getMethod(getTestClass(), METHOD_NAME, Object[].class);
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
   * Tests if the parameter Traits<X, Y, Z> is correctly defined.
   */
  private static void assertTraitsParameter() {
    // Get constructor
    final var parameterClass = TutorUtils.getClass(PACKAGE_NAME, CLASS_NAME_FIELD);
    final var constructor = TutorUtils.getConstructor(getTestClass(), parameterClass);

    final var types = constructor.getGenericParameterTypes();

    // Check number of parameters
    Assertions.assertEquals(1, types.length,
      String.format("The constructor must have one parameters, given: %s",
        Arrays.toString(types)));

    // Check type of parameters
    @SuppressWarnings("unchecked") final var expected =
      (Entry<Class<?>, String>[]) Array.newInstance(Entry.class, 1);
    expected[0] = new SimpleEntry<>(parameterClass, "X, Y, Z");
    for (int i = 0; i < expected.length; i++) {
      final var entry = expected[i];
      TutorUtils.assertGenericType(entry.getKey(), entry.getValue(), types[i]);
    }
  }

  /**
   * Defines the JUnit tests cases related to the class {@value #CLASS_NAME}.
   */
  @Nested
  @DisplayName("Criterion: MyFunctionWithAdjacent")
  final class TestMyFunctionWithAdjacent {

    /**
     * Defines the JUnit tests cases related to the class header.
     */
    @Nested
    @DisplayName("Criterion: Class Header")
    final class TestClassHeader {

      @Test
      @DisplayName("Criterion: Only modifiers public abstract")
      void testModifiers() {
        TutorUtils.assertModifiers(getTestClass(), List.of(Modifier.PUBLIC),
          List.of(Modifier.STATIC, Modifier.ABSTRACT, Modifier.FINAL));
      }

      @Test
      @DisplayName("Criterion: Extension of FunctionWithFilterMapAndFold")
      void testExtension() {
        final var expected = TutorUtils.getClass(PACKAGE_NAME, CLASS_NAME_SUPER);
        final var actual = getTestClass().getSuperclass();
        Assertions.assertEquals(expected, actual,
          String.format("The class should extend class %s, given %s.", expected, actual));
      }

      @Test
      @DisplayName("Criterion: Type parameter X, Y, Z")
      void testTypeParameters() {
        assertTypeParameters();
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
        final var constructor = TutorUtils.getConstructor(getTestClass(), getFieldClass());
        TutorUtils.assertModifiers(constructor, List.of(Modifier.PUBLIC));
      }

      @Test
      @DisplayName("Criterion: Parameter Traits<X, Y, Z>")
      void testParameter() {
        assertTraitsParameter();
      }
    }

    /**
     * Defines the JUnit tests cases related to the method {@value METHOD_NAME}.
     */
    @Nested
    @DisplayName("Criterion: Method apply")
    @TestInstance(Lifecycle.PER_CLASS)
    final class TestMethodApply {

      @Test
      @DisplayName("Criterion: Only modifiers public abstract")
      void testModifiers() {
        TutorUtils.assertModifiers(getMethod(),
          List.of(Modifier.PUBLIC), List.of(Modifier.STATIC, Modifier.FINAL));
      }

      @Test
      @DisplayName("Criterion: Parameter X[]")
      void testParameters() {
        final var method = getMethod();
        final var types = method.getParameterTypes();

        // Check number of parameters
        Assertions.assertEquals(1, types.length,
          String.format("Expected one parameter, given %s.", Arrays.toString(types)));

        // Check type of parameters
        Assertions.assertEquals(Object[].class, types[0],
          String.format("Expected one parameter with the type X[] (Object[]), given %s.",
            types[0]));
      }

      @Test
      @DisplayName("Criterion: Return type Z")
      void testReturnType() {
        final var method = getMethod();
        final var returnType = method.getReturnType();

        // Check type parameter
        Assertions.assertEquals(Object.class, returnType,
          String.format("Expected return type Z (Object), given %s.", returnType));

        // Check type parameter name
        final var name = method.getGenericReturnType().getTypeName();
        Assertions.assertEquals("Z", name,
          String.format("Expected return type name Z, given %s.", name));
      }

      @Test
      @DisplayName("Criterion: Return value")
      void testReturnValue() {
        // Traits object
        final Predicate<Integer> expectedFilter = x -> x % 2==0;
        final Function<Integer, String> expectedMap = String::valueOf;
        final BiFunction<String, String, String> expectedCombine = String::concat;
        final BiFunction<Integer, String, Integer> expectedFold = (i, s) -> s.length() + i;
        final var expectedInit = 523;

        final var traitsConstructor = TutorUtils.getConstructor(getFieldClass(), Predicate.class,
          Function.class, BiFunction.class, BiFunction.class, Object.class);

        final var traits = TutorUtils.invokeConstructor(traitsConstructor, expectedFilter, expectedMap,
          expectedFold, expectedCombine, expectedInit);

        // Main object
        final var constructor = TutorUtils.getConstructor(getTestClass(), getFieldClass());
        final var instance = TutorUtils.invokeConstructor(constructor, traits);

        // Method call
        final var elements = new Integer[1000];
        for (int i = 0; i < elements.length; i++) {
          elements[i] = i + 1;
        }

        final var result = TutorUtils.invokeMethod(getMethod(), instance, new Object[]{elements});
        Assertions.assertEquals(3414, result);
      }
    }
  }

  /**
   * Defines the JUnit tests cases related to the class {@value #CLASS_NAME_FIELD} field
   * {@value FIELD_NAME_COMBINE}.
   */
  @Nested
  @DisplayName("Criterion: Traits - Field combine")
  final class TestTraitsFieldCombine {

    /**
     * Defines the JUnit tests cases related to the field {@value FIELD_NAME_COMBINE}.
     */
    @Nested
    @DisplayName("Criterion: Field for combining")
    final class TestField {
      @Test
      @DisplayName("Criterion: Only modifiers private final")
      void testModifiers() {
        final var field = TutorUtils.getField(getFieldClass(), FIELD_NAME_COMBINE);
        TutorUtils.assertModifiers(field, List.of(Modifier.PRIVATE, Modifier.FINAL),
          List.of(Modifier.STATIC));
      }

      @Test
      @DisplayName("Criterion: Type BiFunction<Y, Y, Y>")
      void testType() {
        final var field = TutorUtils.getField(getFieldClass(), FIELD_NAME_COMBINE);
        final var type = field.getGenericType();
        TutorUtils.assertGenericType(BiFunction.class, "Y, ? super Y, Y", type);
      }
    }

    /**
     * Defines the JUnit tests cases related to the constructor.
     */
    @Nested
    @DisplayName("Criterion: Constructor")
    final class TestConstructor {

      @Nested
      @DisplayName("Criterion: 3 arguments Constructor")
      final class Test3Args {

        @Test
        @DisplayName("Criterion: Only modifier public")
        void testModifiers() {
          final var constructor = TutorUtils.getConstructor(
            getFieldClass(), Predicate.class, Function.class, BiFunction.class, BiFunction.class,
            Object.class
          );
          TutorUtils.assertModifiers(constructor, List.of(Modifier.PUBLIC));
        }

        @Test
        @DisplayName("Criterion: Parameter Predicate<X>, Function<X, Y>, BiFunction<Y, Z, Z>, Z")
        void testParameterTypes() {
          // Get constructor
          final var constructor = TutorUtils.getConstructor(
            getFieldClass(), Predicate.class, Function.class, BiFunction.class, Object.class
          );

          // Check number of parameters
          final var types = constructor.getGenericParameterTypes();
          Assertions.assertEquals(4, types.length,
            String.format("The constructor must have 4 parameters, given: %s",
              Arrays.toString(types)));

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
          final Predicate<Integer> expectedFilter = x -> x % 2==0;
          final Function<Integer, String> expectedMap = String::valueOf;
          final BiFunction<String, Integer, Integer> expectedFold = (s, i) -> s.length() + i;
          final var expectedInit = 523;

          final var traitsConstructor = TutorUtils.getConstructor(getFieldClass(), Predicate.class,
            Function.class, BiFunction.class, Object.class);

          final var instance = TutorUtils.invokeConstructor(traitsConstructor, expectedFilter,
            expectedMap, expectedFold, expectedInit);

          // Check if fields are initialized
          final var actualFilter = TutorUtils.getField(instance, FIELD_NAME_FILTER);
          final var actualMap = TutorUtils.getField(instance, FIELD_NAME_MAP);
          final var actualCombine = TutorUtils.getField(instance, FIELD_NAME_COMBINE);
          final var actualFold = TutorUtils.getField(instance, FIELD_NAME_FOLD);
          final var actualInit = TutorUtils.getField(instance, FIELD_NAME_INITIAL);

          Assertions.assertEquals(expectedFilter, TutorUtils.getContent(actualFilter, instance),
            String.format("Expected the type %s for the filter operation, given %s",
              expectedFilter, actualFilter));
          Assertions.assertEquals(expectedMap, TutorUtils.getContent(actualMap, instance),
            String.format("Expected the type %s for the map operation, given %s",
              expectedMap, actualMap));
          Assertions.assertNull(TutorUtils.getContent(actualCombine, instance),
            String.format("Expected the type %s for the combine operation, given %s",
              null, actualCombine));
          Assertions.assertEquals(expectedFold, TutorUtils.getContent(actualFold, instance),
            String.format("Expected the type %s for the fold operation, given %s",
              expectedFold, actualFold));
          Assertions.assertEquals(expectedInit, TutorUtils.getContent(actualInit, instance),
            String.format("Expected the type %s for the initial value for the fold operation, given %s",
              expectedInit, actualInit));
        }
      }

      @Nested
      @DisplayName("Criterion: 4 arguments Constructor")
      final class Test4Args {

        @Test
        @DisplayName("Criterion: Only modifier public")
        void testModifiers() {
          final var constructor = TutorUtils.getConstructor(
            getFieldClass(), Predicate.class, Function.class, BiFunction.class, BiFunction.class,
            Object.class
          );
          TutorUtils.assertModifiers(constructor, List.of(Modifier.PUBLIC));
        }

        @Test
        @DisplayName("Criterion: Parameter Predicate<X>, Function<X, Y>, BiFunction<Y, Z, Z>, Z")
        void testParameterTypes() {
          // Get constructor
          final var constructor = TutorUtils.getConstructor(
            getFieldClass(), Predicate.class, Function.class, BiFunction.class, Object.class
          );

          // Check number of parameters
          final var types = constructor.getGenericParameterTypes();
          Assertions.assertEquals(4, types.length,
            String.format("The constructor must have 4 parameters, given: %s",
              Arrays.toString(types)));

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
          final Predicate<Integer> expectedFilter = x -> x % 2==0;
          final Function<Integer, String> expectedMap = String::valueOf;
          final BiFunction<String, String, String> expectedCombine = String::concat;
          final BiFunction<String, Integer, Integer> expectedFold = (s, i) -> s.length() + i;
          final var expectedInit = 523;

          final var traitsConstructor = TutorUtils.getConstructor(getFieldClass(), Predicate.class,
            Function.class, BiFunction.class, BiFunction.class, Object.class);

          final var instance = TutorUtils.invokeConstructor(traitsConstructor, expectedFilter,
            expectedMap, expectedFold, expectedCombine, expectedInit);

          // Check if fields are initialized
          final var actualFilter = TutorUtils.getField(instance, FIELD_NAME_FILTER);
          final var actualMap = TutorUtils.getField(instance, FIELD_NAME_MAP);
          final var actualCombine = TutorUtils.getField(instance, FIELD_NAME_COMBINE);
          final var actualFold = TutorUtils.getField(instance, FIELD_NAME_FOLD);
          final var actualInit = TutorUtils.getField(instance, FIELD_NAME_INITIAL);

          Assertions.assertEquals(expectedFilter, TutorUtils.getContent(actualFilter, instance),
            String.format("Expected the type %s for the filter operation, given %s",
              expectedFilter, actualFilter));
          Assertions.assertEquals(expectedMap, TutorUtils.getContent(actualMap, instance),
            String.format("Expected the type %s for the map operation, given %s",
              expectedMap, actualMap));
          Assertions.assertEquals(expectedCombine, TutorUtils.getContent(actualCombine, instance),
            String.format("Expected the type %s for the combine operation, given %s",
              expectedCombine, actualCombine));
          Assertions.assertEquals(expectedFold, TutorUtils.getContent(actualFold, instance),
            String.format("Expected the type %s for the fold operation, given %s",
              expectedFold, actualFold));
          Assertions.assertEquals(expectedInit, TutorUtils.getContent(actualInit, instance),
            String.format("Expected the type %s for the initial value for the fold operation, given %s",
              expectedInit, actualInit));
        }
      }
    }
  }
}
