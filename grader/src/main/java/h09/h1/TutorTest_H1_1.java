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
import java.util.AbstractMap.SimpleEntry;
import java.util.List;

@TestForSubmission("h09")
@DisplayName("Criterion: Class Traits")
public final class TutorTest_H1_1 {

  private static Class<?> getTestClass() {
    return TutorUtils.assertClass(TutorConstants.H1_PACKAGE_NAME, TutorConstants.H1_1_CLASS_NAME);
  }

  @Nested
  @DisplayName("Criterion: Class Header")
  public final class TestClassHeader {

    @Test
    @DisplayName("Criterion: Only modifiers public")
    void testModifiers() {
      final var actual = getTestClass();
      final var expected = Modifier.ABSTRACT
        .nand(Modifier.FINAL, Modifier.STATIC)
        .and(Modifier.PUBLIC);
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
  @DisplayName("Criterion: Field for filter operation")
  public final class TestField1 {

    private Field getTestField() {
      final var clazz = getTestClass();
      return TutorUtils.assertField(clazz, TutorConstants.H1_1_FIELD_NAME_1);
    }

    @Test
    @DisplayName("Criterion: Only modifiers private final")
    void testModifiers() {
      final var actual = getTestField();
      final var expected = Modifier.STATIC.negate().and(Modifier.PRIVATE, Modifier.FINAL);
      TutorUtils.assertModifiers(expected, actual);
    }

    @Test
    @DisplayName("Criterion: Type Predicate<? super X>")
    void testType() {
      final var actual = getTestField();
      final var type = actual.getGenericType();
      TutorUtils.assertGenericType(
        TutorConstants.H1_1_FIELD_TYPE_1, TutorConstants.H1_1_FIELD_TYPE_PARAMETER_1, type
      );
    }

    @Test
    @DisplayName("Criterion: Getter method")
    void testGetter() {
      final var method = TutorUtils.assertMethod(getTestClass(), TutorConstants.H1_1_METHOD_NAME_1);

      // Check modifier
      TutorUtils.assertModifiers(Modifier.STATIC.nand(Modifier.FINAL).and(Modifier.PUBLIC), method);

      // Check return type
      final var expected = TutorConstants.H1_1_FIELD_TYPE_1;
      final var actual = method.getReturnType();
      Assertions.assertEquals(
        expected, actual, TutorMessage.RETURN_TYPE_MISMATCH.format(expected, actual)
      );

      final var name = method.getGenericReturnType().getTypeName();
      Assertions.assertEquals(
        String.format(
          "%s<%s>", expected.getCanonicalName(), TutorConstants.H1_1_FIELD_TYPE_PARAMETER_1
        ), name, TutorMessage.RETURN_TYPE_MISMATCH.format(expected, actual)
      );
    }
  }

  @Nested
  @DisplayName("Criterion: Field for map operation")
  public final class TestField2 {

    private Field getTestField() {
      final var clazz = getTestClass();
      return TutorUtils.assertField(clazz, TutorConstants.H1_1_FIELD_NAME_2);
    }

    @Test
    @DisplayName("Criterion: Only modifiers private final")
    void testModifiers() {
      final var actual = getTestField();
      final var expected = Modifier.STATIC.negate().and(Modifier.PRIVATE, Modifier.FINAL);
      TutorUtils.assertModifiers(expected, actual);
    }

    @Test
    @DisplayName("Criterion: Type Function<? super X, ? extends Y>")
    void testType() {
      final var actual = getTestField();
      final var type = actual.getGenericType();
      TutorUtils.assertGenericType(
        TutorConstants.H1_1_FIELD_TYPE_2, TutorConstants.H1_1_FIELD_TYPE_PARAMETER_2, type
      );
    }

    @Test
    @DisplayName("Criterion: Getter method")
    void testGetter() {
      final var method = TutorUtils.assertMethod(getTestClass(), TutorConstants.H1_1_METHOD_NAME_2);

      // Check modifier
      TutorUtils.assertModifiers(Modifier.STATIC.nand(Modifier.FINAL).and(Modifier.PUBLIC), method);

      // Check return type
      final var expected = TutorConstants.H1_1_FIELD_TYPE_2;
      final var actual = method.getReturnType();
      Assertions.assertEquals(
        expected, actual, TutorMessage.RETURN_TYPE_MISMATCH.format(expected, actual)
      );

      final var name = method.getGenericReturnType().getTypeName();
      Assertions.assertEquals(
        String.format(
          "%s<%s>", expected.getCanonicalName(), TutorConstants.H1_1_FIELD_TYPE_PARAMETER_2
        ), name, TutorMessage.RETURN_TYPE_MISMATCH.format(expected, actual)
      );
    }
  }

  @Nested
  @DisplayName("Criterion: Field for fold operation")
  public final class TestField3 {

    private Field getTestField() {
      final var clazz = getTestClass();
      return TutorUtils.assertField(clazz, TutorConstants.H1_1_FIELD_NAME_3);
    }

    @Test
    @DisplayName("Criterion: Only modifiers private final")
    void testModifiers() {
      final var actual = getTestField();
      final var expected = Modifier.STATIC.negate().and(Modifier.PRIVATE, Modifier.FINAL);
      TutorUtils.assertModifiers(expected, actual);
    }

    @Test
    @DisplayName("Criterion: Type BiFunction<Z, ? super Y, Z")
    void testType() {
      final var actual = getTestField();
      final var type = actual.getGenericType();
      TutorUtils.assertGenericType(
        TutorConstants.H1_1_FIELD_TYPE_3, TutorConstants.H1_1_FIELD_TYPE_PARAMETER_3, type
      );
    }

    @Test
    @DisplayName("Criterion: Getter method")
    void testGetter() {
      final var method = TutorUtils.assertMethod(getTestClass(), TutorConstants.H1_1_METHOD_NAME_3);

      // Check modifier
      TutorUtils.assertModifiers(Modifier.STATIC.nand(Modifier.FINAL).and(Modifier.PUBLIC), method);

      // Check return type
      final var expected = TutorConstants.H1_1_FIELD_TYPE_3;
      final var actual = method.getReturnType();
      Assertions.assertEquals(
        expected, actual, TutorMessage.RETURN_TYPE_MISMATCH.format(expected, actual)
      );

      final var name = method.getGenericReturnType().getTypeName();
      Assertions.assertEquals(
        String.format(
          "%s<%s>", expected.getCanonicalName(), TutorConstants.H1_1_FIELD_TYPE_PARAMETER_3
        ), name,
        TutorMessage.RETURN_TYPE_MISMATCH.format(expected, actual)
      );
    }
  }

  @Nested
  @DisplayName("Criterion: Field for the initial value of the fold operation")
  public final class TestField4 {

    private Field getTestField() {
      final var clazz = getTestClass();
      return TutorUtils.assertField(clazz, TutorConstants.H1_1_FIELD_NAME_4);
    }

    @Test
    @DisplayName("Criterion: Only modifiers private final")
    void testModifiers() {
      final var actual = getTestField();
      final var expected = Modifier.STATIC.negate().and(Modifier.PRIVATE, Modifier.FINAL);
      TutorUtils.assertModifiers(expected, actual);
    }

    @Test
    @DisplayName("Criterion: Type Z")
    void testType() {
      final var field = getTestField();
      final var type = field.getGenericType();
      final var expected = TutorConstants.H1_1_FIELD_TYPE_PARAMETER_4;
      final var actual = type.getTypeName();
      Assertions.assertEquals(
        expected, actual, TutorMessage.TYPE_PARAMETER_MISMATCH.format(expected, actual)
      );
    }

    @Test
    @DisplayName("Criterion: Getter method")
    void testGetter() {
      final var method = TutorUtils.assertMethod(getTestClass(), TutorConstants.H1_1_METHOD_NAME_4);

      // Check modifier
      TutorUtils.assertModifiers(Modifier.STATIC.nand(Modifier.FINAL).and(Modifier.PUBLIC), method);

      // Check return type
      final var expected = TutorConstants.H1_1_FIELD_TYPE_4;
      final var actual = method.getReturnType();
      Assertions.assertEquals(
        expected, actual, TutorMessage.RETURN_TYPE_MISMATCH.format(expected, actual)
      );

      final var expectedName = TutorConstants.H1_1_FIELD_TYPE_PARAMETER_4;
      final var actualName = method.getGenericReturnType().getTypeName();
      Assertions.assertEquals(
        expectedName, actualName, TutorMessage.RETURN_TYPE_MISMATCH.format(expectedName, actualName)
      );
    }
  }

  @Nested
  @DisplayName("Criterion: Constructor")
  public final class TestConstructor {

    private Constructor<?> getTestConstructor() {
      final var clazz = getTestClass();
      return TutorUtils.assertConstructor(
        clazz,
        TutorConstants.H1_1_FIELD_TYPE_1,
        TutorConstants.H1_1_FIELD_TYPE_2,
        TutorConstants.H1_1_FIELD_TYPE_3,
        TutorConstants.H1_1_FIELD_TYPE_4
      );
    }

    @Test
    @DisplayName("Criterion: Only modifier public")
    void testModifiers() {
      final var actual = getTestClass();
      final var expected = Modifier.PUBLIC;
      TutorUtils.assertModifiers(expected, actual);
    }

    @Test
    @DisplayName("Criterion: Parameter Predicate<? super X>, Function<? super X, ? extends Y>, " +
      "BiFunction<Z, ? super Y, Z>, Z")
    void testParameterTypes() {
      final var constructor = getTestConstructor();
      TutorUtils.assertConstructorParameters(
        constructor, List.of(
          new SimpleEntry<>(
            TutorConstants.H1_1_FIELD_TYPE_1, TutorConstants.H1_1_FIELD_TYPE_PARAMETER_1
          ),
          new SimpleEntry<>(
            TutorConstants.H1_1_FIELD_TYPE_2, TutorConstants.H1_1_FIELD_TYPE_PARAMETER_2
          ),
          new SimpleEntry<>(
            TutorConstants.H1_1_FIELD_TYPE_3, TutorConstants.H1_1_FIELD_TYPE_PARAMETER_3
          ),
          new SimpleEntry<>(
            TutorConstants.H1_1_FIELD_TYPE_4, TutorConstants.H1_1_FIELD_TYPE_PARAMETER_4
          )
        )
      );
    }

    @Test
    @DisplayName("Criterion: Initialization of fields")
    void testFields() {
      final var constructor = getTestConstructor();

      final var expectedField1 = TutorConstants.H1_1_FIELD_EXAMPLE_1_1;
      final var expectedField2 = TutorConstants.H1_1_FIELD_EXAMPLE_1_2;
      final var expectedField3 = TutorConstants.H1_1_FIELD_EXAMPLE_1_3;
      final var expectedField4 = TutorConstants.H1_1_FIELD_EXAMPLE_1_4;

      final var instance = TutorUtils.invokeConstructor(
        constructor, expectedField1, expectedField2, expectedField3, expectedField4
      );

      // Check if fields are initialized
      final var actualField1 = TutorUtils.assertField(instance, TutorConstants.H1_1_FIELD_NAME_1);
      final var actualField2 = TutorUtils.assertField(instance, TutorConstants.H1_1_FIELD_NAME_2);
      final var actualField3 = TutorUtils.assertField(instance, TutorConstants.H1_1_FIELD_NAME_3);
      final var actualField4 = TutorUtils.assertField(instance, TutorConstants.H1_1_FIELD_NAME_4);

      Assertions.assertEquals(
        expectedField1, TutorUtils.getFieldContent(actualField1, instance),
        TutorMessage.FIELD_CONTENT_MISMATCH.format(expectedField1, actualField1)
      );
      Assertions.assertEquals(
        expectedField2, TutorUtils.getFieldContent(actualField2, instance),
        TutorMessage.FIELD_CONTENT_MISMATCH.format(expectedField2, actualField2)
      );
      Assertions.assertEquals(
        expectedField3, TutorUtils.getFieldContent(actualField3, instance),
        TutorMessage.FIELD_CONTENT_MISMATCH.format(expectedField3, actualField3)
      );
      Assertions.assertEquals(
        expectedField4, TutorUtils.getFieldContent(actualField4, instance),
        TutorMessage.FIELD_CONTENT_MISMATCH.format(expectedField4, actualField4)
      );
    }
  }
}
