package h09.h1;

import h09.utils.Modifier;
import h09.utils.TutorConstants;
import h09.utils.TutorMessage;
import h09.utils.TutorUtils;
import h09.utils.spoon.ArraysInstantiationMethodBodyProcessor;
import h09.utils.spoon.SpoonUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.stream.IntStream;

@TestForSubmission("h09")
@DisplayName("Criterion: Class MyFunctionWithFilterMapAndFold")
public final class TutorTest_H1_3 {

  private static Class<?> getTestClass() {
    return TutorUtils.assertClass(TutorConstants.H1_PACKAGE_NAME, TutorConstants.H1_3_CLASS_NAME);
  }

  private static Class<?> getTestFieldClass() {
    return TutorUtils.assertClass(TutorConstants.H1_PACKAGE_NAME, TutorConstants.H1_1_CLASS_NAME);
  }

  @Nested
  @DisplayName("Criterion: Class Header")
  public final class TestClassHeader {

    @Test
    @DisplayName("Criterion: Only modifier public")
    void testModifiers() {
      final var actual = getTestClass();
      final var expected = Modifier.STATIC.nand(Modifier.ABSTRACT, Modifier.FINAL)
        .and(Modifier.PUBLIC);
      TutorUtils.assertModifiers(expected, actual);
    }

    @Test
    @DisplayName("Criterion: Extension of FunctionWithFilterMapAndFold")
    void testExtension() {
      final var expected = TutorUtils.assertClass(
        TutorConstants.H1_PACKAGE_NAME, TutorConstants.H1_2_CLASS_NAME
      );
      final var actual = getTestClass().getSuperclass();
      Assertions.assertEquals(
        expected, actual, TutorMessage.CLASS_EXTENSION_MISMATCH.format(expected, actual)
      );
    }

    @Test
    @DisplayName("Criterion: Type parameter X, Y, Z")
    void testTypeParameters() {
      final var clazz = getTestClass();
      TutorUtils_H1.assertClassTypeParameters(clazz);
    }

    @Test
    @DisplayName("Criterion: Check imports")
    void testImports() {
      // TODO replace with Jagr API
      TutorUtils_H1.assertImports(null, TutorConstants.H1_3_PATH_TO_SOURCE,
        TutorConstants.H1_IMPORT_BLACK_LIST);
    }
  }

  @Nested
  @DisplayName("Criterion: Constructor")
  public final class TestConstructor {

    public Constructor<?> getTestConstructor() {
      final var clazz = getTestClass();
      final var parameter = getTestFieldClass();
      return TutorUtils.assertConstructor(clazz, parameter);
    }

    @Test
    @DisplayName("Criterion: Only modifier public")
    void testModifiers() {
      final var actual = getTestConstructor();
      final var expected = Modifier.PUBLIC;
      TutorUtils.assertModifiers(expected, actual);
    }

    @Test
    @DisplayName("Criterion: Parameter Traits<X, Y, Z>")
    void testParameter() {
      final var constructor = getTestConstructor();
      final var parameterClass = getTestFieldClass();
      TutorUtils_H1.assertConstructorParameterH1(constructor, parameterClass);
    }
  }

  @Nested
  @DisplayName("Criterion: Method apply")
  public final class TestMethod {

    private Method getTestMethod() {
      final var clazz = getTestClass();
      return TutorUtils.assertMethod(
        clazz, TutorConstants.H1_2_METHOD_NAME, TutorConstants.H1_2_METHOD_CLASS_PARAMETER
      );
    }

    @Test
    @DisplayName("Criterion: Only modifiers public")
    void testModifiers() {
      final var actual = getTestMethod();
      final var expected = Modifier.STATIC.nand(Modifier.ABSTRACT).and(Modifier.PUBLIC);
      TutorUtils.assertModifiers(expected, actual);
    }

    @Test
    @DisplayName("Criterion: Parameter X[]")
    void testParameters() {
      final var method = getTestMethod();
      final var types = method.getParameterTypes();

      // Check number of parameters
      final var expectedNumberParameters = 1;
      final var actualNumberParameters = types.length;
      Assertions.assertEquals(
        expectedNumberParameters, actualNumberParameters,
        TutorMessage.METHOD_PARAMETER_MISMATCH_SIZE.format(
          method.getName(), expectedNumberParameters, actualNumberParameters)
      );

      // Check type of parameters
      final var expectedType = TutorConstants.H1_2_METHOD_CLASS_PARAMETER;
      final var actualType = types[0];
      Assertions.assertEquals(
        expectedType, actualType,
        TutorMessage.METHOD_PARAMETER_MISMATCH.format(method.getName(), expectedType, actualType)
      );
    }

    @Test
    @DisplayName("Criterion: Return type Z")
    void testReturnType() {
      final var method = getTestMethod();
      final var actualType = method.getReturnType();

      // Check type parameter
      final var expectedType = TutorConstants.H1_2_METHOD_CLASS_RETURN;
      Assertions.assertEquals(
        expectedType, actualType, TutorMessage.RETURN_TYPE_MISMATCH.format(expectedType, actualType)
      );

      // Check type parameter name
      final var expectedName = TutorConstants.H1_2_METHOD_RETURN_TYPE_PARAMETER;
      final var actualName = method.getGenericReturnType().getTypeName();
      Assertions.assertEquals(
        expectedName, actualName, TutorMessage.RETURN_TYPE_MISMATCH.format(expectedName, actualName)
      );
    }

    @Test
    @DisplayName("Criterion: Return value")
    void testReturnValue() {
      // Traits object
      final var expectedField1 = TutorConstants.H1_1_FIELD_EXAMPLE_2_1;
      final var expectedField2 = TutorConstants.H1_1_FIELD_EXAMPLE_2_2;
      final var expectedField3 = TutorConstants.H1_1_FIELD_EXAMPLE_2_3;
      final var expectedField4 = TutorConstants.H1_1_FIELD_EXAMPLE_2_4;

      final var fieldClass = getTestFieldClass();
      final var fieldConstructor = TutorUtils.assertConstructor(
        fieldClass,
        TutorConstants.H1_1_FIELD_TYPE_1, TutorConstants.H1_1_FIELD_TYPE_2,
        TutorConstants.H1_1_FIELD_TYPE_3, TutorConstants.H1_1_FIELD_TYPE_4
      );

      final var fieldObject = TutorUtils.invokeConstructor(
        fieldConstructor, expectedField1, expectedField2, expectedField3, expectedField4
      );

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
      Assertions.assertEquals(TutorConstants.H1_1_FIELD_EXAMPLE_2_RESULT, result);
    }

    @Test
    @DisplayName("Criterion: Requirement - Arrays as intermediate storage")
    void testRequirementArrays() {
      // TODO replace with Jagr API
      final var processor = SpoonUtils.process(null, TutorConstants.H1_3_PATH_TO_SOURCE,
        new ArraysInstantiationMethodBodyProcessor(TutorConstants.H1_2_METHOD_NAME));

      final var expected = 2;
      final var actual = processor.getArrays().size();
      Assertions.assertEquals(
        expected, actual,
        TutorMessage.REQUIREMENT_INTERMEDIATE_ARRAY_MISMATCH.format(expected, actual)
      );
    }
  }
}

