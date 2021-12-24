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

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@TestForSubmission("h09")
@DisplayName("Criterion: Class FunctionWithFilterMapAndFold")
public final class TutorTest_H1_2 {

  private static Class<?> getTestClass() {
    return TutorUtils.assertClass(TutorConstants.H1_PACKAGE_NAME, TutorConstants.H1_2_CLASS_NAME);
  }

  private static Class<?> getTestFieldClass() {
    return TutorUtils.assertClass(TutorConstants.H1_PACKAGE_NAME, TutorConstants.H1_1_CLASS_NAME);
  }

  @Nested
  @DisplayName("Criterion: Class Header")
  public final class TestClassHeader {

    @Test
    @DisplayName("Criterion: Only modifiers public abstract")
    void testModifiers() {
      final var actual = getTestClass();
      final var expected = Modifier.STATIC.negate().and(Modifier.PUBLIC, Modifier.ABSTRACT);
      TutorUtils.assertModifiers(expected, actual);
    }

    @Test
    @DisplayName("Criterion: No extension")
    void testExtension() {
      final var expected = Object.class;
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
  }

  @Nested
  @DisplayName("Criterion: Field traits")
  public final class TestField {

    private Field getTestField() {
      return TutorUtils.assertField(getTestClass(), TutorConstants.H1_2_FIELD_NAME);
    }

    @Test
    @DisplayName("Criterion: Only modifiers protected final")
    void testModifiers() {
      final var actual = getTestField();
      final var expected = Modifier.STATIC.negate().and(Modifier.PROTECTED, Modifier.FINAL);
      TutorUtils.assertModifiers(expected, actual);
    }

    @Test
    @DisplayName("Criterion: Type Traits<X, Y, Z>")
    void testType() {
      final var field = getTestField();
      final var clazz = getTestFieldClass();
      TutorUtils.assertGenericType(
        clazz, TutorConstants.H1_TYPE_PARAMETERS, field.getGenericType()
      );
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
      return TutorUtils.assertMethod(clazz, TutorConstants.H1_2_METHOD_NAME,
        TutorConstants.H1_2_METHOD_CLASS_PARAMETER);
    }

    @Test
    @DisplayName("Criterion: Only modifiers public abstract")
    void testModifiers() {
      final var actual = getTestMethod();
      final var expected = Modifier.STATIC.negate().and(Modifier.PUBLIC, Modifier.ABSTRACT);
      TutorUtils.assertModifiers(expected, actual);
    }

    @Test
    @DisplayName("Criterion: Parameter X[]")
    void testParameters() {
      final var method = getTestMethod();
      TutorUtils_H1.assertParametersH1_2(method);
    }

    @Test
    @DisplayName("Criterion: Return type Z")
    void testReturnType() {
      final var method = getTestMethod();
      TutorUtils_H1.assertReturnTypeH1_2(method);
    }
  }
}
