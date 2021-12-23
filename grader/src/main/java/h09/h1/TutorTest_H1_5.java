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

import java.lang.reflect.Method;

@TestForSubmission("h09")
@DisplayName("Criterion: Class FunctionFactory")
public final class TutorTest_H1_5 {

  private static Class<?> getTestClass() {
    return TutorUtils.assertClass(TutorConstants.H1_PACKAGE_NAME, TutorConstants.H1_5_CLASS_NAME);
  }

  private static Class<?> getTestClassParameter() {
    return TutorUtils.assertClass(TutorConstants.H1_PACKAGE_NAME, TutorConstants.H1_1_CLASS_NAME);
  }

  @Nested
  @DisplayName("Criterion: Class Header")
  public final class TestClassHeader {

    @Test
    @DisplayName("Criterion: Only modifiers public final")
    void testModifiers() {
      final var actual = getTestClass();
      final var expected = Modifier.STATIC.nand(Modifier.ABSTRACT).and(Modifier.PUBLIC,
        Modifier.FINAL);
      TutorUtils.assertModifiers(expected, actual);
    }

    @Test
    @DisplayName("Criterion: No type parameter")
    void testTypeParameter() {
      final var clazz = getTestClass();
      final var types = clazz.getTypeParameters();
      final var expected = 0;
      final var actual = types.length;
      Assertions.assertEquals(
        expected, actual,
        TutorMessage.CLASS_TYPE_PARAMETER_MISMATCH_SIZE.format(clazz.getSimpleName(), expected, actual)
      );
    }
  }

  @Nested
  @DisplayName("Criterion: Constructor")
  public final class TestConstructor {

    @Test
    @DisplayName("Criterion: Only modifier private")
    void testModifiers() {
      final var clazz = getTestClass();
      final var actual = TutorUtils.assertConstructor(clazz);
      final var expected = Modifier.PRIVATE;
      TutorUtils.assertModifiers(expected, actual);
    }
  }

  @Nested
  @DisplayName("Criterion: Method createFunctionWithFilterMapAndFold")
  public final class TestMethod1 {

    private Method getTestMethod() {
      final var clazz = getTestClass();
      final var parameters = getTestClassParameter();
      return TutorUtils.assertMethod(clazz, TutorConstants.H1_5_METHOD_NAME_1, parameters);
    }

    @Test
    @DisplayName("Criterion: Only modifiers public static")
    void testModifiers() {
      final var actual = getTestMethod();
      final var expected = Modifier.PUBLIC.and(Modifier.STATIC);
      TutorUtils.assertModifiers(expected, actual);
    }

    @Test
    @DisplayName("Criterion: Parameter Traits<X, Y, Z>")
    void testParameters() {
      final var classParameter = getTestClassParameter();
      final var method = getTestMethod();
      final var types = method.getParameterTypes();

      // Check number of parameters
      final var expectedLength = 1;
      final var actualLength = types.length;
      Assertions.assertEquals(expectedLength, actualLength,
        TutorMessage.METHOD_PARAMETER_MISMATCH_SIZE.format(method.getName(), expectedLength,
          actualLength));

      // Check type of parameters
      final var actualClass = types[0];
      Assertions.assertEquals(classParameter, actualClass,
        TutorMessage.METHOD_PARAMETER_MISMATCH.format(method.getName(), classParameter,
          actualClass));

      // Check generic types
      final var parametrized = method.getParameters()[0].getParameterizedType();
      TutorUtils.assertGenericType(classParameter, TutorConstants.H1_TYPE_PARAMETERS, parametrized);
    }

    @Test
    @DisplayName("Criterion: Return type FunctionWithFilterMapAndFold<X, Y, Z>")
    void testReturnType() {
      final var method = getTestMethod();
      final var expected = TutorUtils.assertClass(TutorConstants.H1_PACKAGE_NAME,
        TutorConstants.H1_2_CLASS_NAME);

      final var actual = method.getReturnType();
      Assertions.assertEquals(expected, actual, TutorMessage.RETURN_TYPE_MISMATCH.format(expected
        , actual));
      TutorUtils.assertGenericType(expected, TutorConstants.H1_TYPE_PARAMETERS, method.getGenericReturnType());
    }

    @Test
    @DisplayName("Criterion: Return value MyFunctionWithFilterMapAndFold<X, Y, Z>")
    void testReturnValue() {
      final var expectedField1 = TutorConstants.H1_1_FIELD_EXAMPLE_3_1;
      final var expectedField2 = TutorConstants.H1_1_FIELD_EXAMPLE_3_2;
      final var expectedField3 = TutorConstants.H1_1_FIELD_EXAMPLE_3_3;
      final var expectedField4 = TutorConstants.H1_1_FIELD_EXAMPLE_3_4;

      final var parameterClass = getTestClassParameter();
      final var parameterConstructor = TutorUtils.assertConstructor(parameterClass,
        TutorConstants.H1_1_FIELD_TYPE_1, TutorConstants.H1_1_FIELD_TYPE_2,
        TutorConstants.H1_1_FIELD_TYPE_3, TutorConstants.H1_1_FIELD_TYPE_4);

      final var parameterInstance = TutorUtils.invokeConstructor(parameterConstructor, expectedField1,
        expectedField2, expectedField3, expectedField4);

      // Invoke Method
      final var method = getTestMethod();

      final var actual = TutorUtils.invokeMethod(method, null, parameterInstance);
      final var expected = TutorUtils.assertClass(TutorConstants.H1_PACKAGE_NAME,
        TutorConstants.H1_3_CLASS_NAME);
      final var actualClass = actual.getClass();
      Assertions.assertEquals(expected, actualClass,
        TutorMessage.RETURN_VALUE_MISMATCH.format(method.getName(), expected, actualClass));
    }
  }

  @Nested
  @DisplayName("Criterion: Method createFunctionWithFilterMapFoldAndCombine")
  public final class TestMethod2 {

    private Method getTestMethod() {
      final var clazz = getTestClass();
      final var parameters = getTestClassParameter();
      return TutorUtils.assertMethod(clazz, TutorConstants.H1_5_METHOD_NAME_2, parameters);
    }

    @Test
    @DisplayName("Criterion: Only modifiers public static")
    void testModifiers() {
      final var actual = getTestMethod();
      final var expected = Modifier.PUBLIC.and(Modifier.STATIC);
      TutorUtils.assertModifiers(expected, actual);
    }

    @Test
    @DisplayName("Criterion: Parameter Traits<X, Y, Z>")
    void testParameters() {
      final var classParameter = getTestClassParameter();
      final var method = getTestMethod();
      final var types = method.getParameterTypes();

      // Check number of parameters
      final var expectedLength = 1;
      final var actualLength = types.length;
      Assertions.assertEquals(expectedLength, actualLength,
        TutorMessage.METHOD_PARAMETER_MISMATCH_SIZE.format(method.getName(), expectedLength,
          actualLength));

      // Check type of parameters
      final var actualClass = types[0];
      Assertions.assertEquals(classParameter, actualClass,
        TutorMessage.METHOD_PARAMETER_MISMATCH.format(method.getName(), classParameter,
          actualClass));

      // Check generic types
      final var parametrized = method.getParameters()[0].getParameterizedType();
      TutorUtils.assertGenericType(classParameter, TutorConstants.H1_TYPE_PARAMETERS, parametrized);
    }

    @Test
    @DisplayName("Criterion: Return type FunctionWithFilterMapAndFold<X, Y, Z>")
    void testReturnType() {
      final var method = getTestMethod();
      final var expected = TutorUtils.assertClass(TutorConstants.H1_PACKAGE_NAME,
        TutorConstants.H1_2_CLASS_NAME);

      final var actual = method.getReturnType();
      Assertions.assertEquals(expected, actual, TutorMessage.RETURN_TYPE_MISMATCH.format(expected
        , actual));
      TutorUtils.assertGenericType(expected, TutorConstants.H1_TYPE_PARAMETERS, method.getGenericReturnType());
    }

    @Test
    @DisplayName("Criterion: Return value MyFunctionWithAdjacent<X, Y, Z>")
    void testReturnValue() {
      final var expectedField1 = TutorConstants.H1_1_FIELD_EXAMPLE_3_1;
      final var expectedField2 = TutorConstants.H1_1_FIELD_EXAMPLE_3_2;
      final var expectedField3 = TutorConstants.H1_1_FIELD_EXAMPLE_3_3;
      final var expectedField4 = TutorConstants.H1_1_FIELD_EXAMPLE_3_4;
      final var expectedField5 = TutorConstants.H1_1_FIELD_EXAMPLE_3_5;

      final var parameterClass = getTestClassParameter();
      final var parameterConstructor = TutorUtils.assertConstructor(parameterClass,
        TutorConstants.H1_1_FIELD_TYPE_1, TutorConstants.H1_1_FIELD_TYPE_2,
        TutorConstants.H1_1_FIELD_TYPE_3, TutorConstants.H1_1_FIELD_TYPE_4,
        TutorConstants.H1_4_FIELD_TYPE);

      final var parameterInstance = TutorUtils.invokeConstructor(parameterConstructor, expectedField1,
        expectedField2, expectedField3, expectedField4, expectedField5);

      // Invoke Method
      final var method = getTestMethod();

      final var actual = TutorUtils.invokeMethod(method, null, parameterInstance);
      final var expected = TutorUtils.assertClass(TutorConstants.H1_PACKAGE_NAME,
        TutorConstants.H1_4_CLASS_NAME);
      final var actualClass = actual.getClass();
      Assertions.assertEquals(expected, actualClass,
        TutorMessage.RETURN_VALUE_MISMATCH.format(method.getName(), expected, actualClass));
    }
  }
}
