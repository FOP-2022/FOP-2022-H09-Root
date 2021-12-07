package h09.h1;

import h09.TutorUtils;
import h09.TutorUtils.Modifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;

/**
 * Defines the JUnit test cases for the class defined in H1.2.
 *
 * @author Nhan Huynh, Darya Nikitina
 */
@DisplayName("Criterion: Class FunctionWithFilterMapAndFold")
final class TutorTest_H1_2 {

  /**
   * The name of the package where the class for the test cases should exist.
   */
  private static final String PACKAGE_NAME = "h09.h1";

  /**
   * The name of the class which should be tested.
   */
  private static final String CLASS_NAME = "FunctionWithFilterMapAndFold";

  /**
   * The name of the class of the field {@value FIELD_NAME}
   */
  private static final String CLASS_NAME_FIELD = "Traits";

  /**
   * The name of the field which provides filter, map and fold operation.
   */
  private static final String FIELD_NAME = "traits";

  /**
   * The name of the abstract that transforms a set using filter, map and fold operation.
   */
  private static final String METHOD_NAME = "apply";

  /**
   * Returns the class instance of the class {@value CLASS_NAME} which should be tested.
   *
   * @return the class instance of the class {@value CLASS_NAME} which should be tested.
   */
  private static Class<?> getTestClass() {
    return TutorUtils.getClass(PACKAGE_NAME, CLASS_NAME);
  }

  /**
   * Returns the class instance of the field {@value FIELD_NAME} which should be tested.
   *
   * @return the class instance of the field {@value FIELD_NAME} which should be tested.
   */
  private static Class<?> getFieldClass() {
    return TutorUtils.getClass(PACKAGE_NAME, CLASS_NAME_FIELD);
  }

  /**
   * Returns the field instance {@value FIELD_NAME} which should be tested.
   *
   * @return the field instance {@value FIELD_NAME} which should be tested
   */
  private static Field getField() {
    return TutorUtils.getField(getTestClass(), FIELD_NAME);
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
   * Defines the JUnit tests cases related to the class header.
   */
  @Nested
  @DisplayName("Criterion: Class Header")
  final class TestClassHeader {

    @Test
    @DisplayName("Criterion: Only modifiers public abstract")
    void testModifiers() {
      TutorUtils.assertModifiers(getTestClass(), List.of(Modifier.PUBLIC, Modifier.ABSTRACT),
        List.of(Modifier.STATIC));
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
   * Defines the JUnit tests cases related to the field {@value FIELD_NAME}
   */
  @Nested
  @DisplayName("Criterion: Field traits")
  final class TestField {
    @Test
    @DisplayName("Criterion: Only modifiers protected final")
    void testModifiers() {
      TutorUtils.assertModifiers(getField(), List.of(Modifier.PROTECTED, Modifier.FINAL),
        List.of(Modifier.STATIC));
    }

    @Test
    @DisplayName("Criterion: Type Traits<X, Y, Z>")
    void testType() {
      final var field = getField();
      TutorUtils.assertGenericType(
        getFieldClass(), "X, Y, Z", field.getGenericType()
      );
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
   * Defines the JUnit tests cases related to the method {@code apply}.
   */
  @Nested
  @DisplayName("Criterion: Method apply")
  final class TestMethodApply {

    @Test
    @DisplayName("Criterion: Only modifiers public abstract")
    void testModifiers() {
      TutorUtils.assertModifiers(getMethod(), List.of(Modifier.ABSTRACT, Modifier.PACKAGEPRIVATE),
        List.of(Modifier.STATIC));
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
        String.format("Expected one parameter with the type X[] (Object[]), given %s.", types[0]));
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
  }
}
