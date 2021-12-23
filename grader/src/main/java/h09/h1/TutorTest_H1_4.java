package h09.h1;

import h09.utils.Modifier;
import h09.utils.TutorConstants;
import h09.utils.TutorMessage;
import h09.utils.TutorUtils;
import h09.utils.spoon.ArraysInstantiationMethodBodyProcessor;
import h09.utils.spoon.LoopsMethodBodyProcessor;
import h09.utils.spoon.SpoonUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.sourcegrade.jagr.api.testing.TestCycle;
import org.sourcegrade.jagr.api.testing.extension.TestCycleResolver;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.stream.IntStream;

@TestForSubmission("h09")
@DisplayName("Criterion: Class MyFunctionWithAdjacent")
public final class TutorTest_H1_4 {

  private static Class<?> getTestClass() {
    return TutorUtils.assertClass(TutorConstants.H1_PACKAGE_NAME, TutorConstants.H1_4_CLASS_NAME);
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
      final var expected = TutorUtils.assertClass(TutorConstants.H1_PACKAGE_NAME,
        TutorConstants.H1_2_CLASS_NAME);
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
    @ExtendWith(TestCycleResolver.class)
    @DisplayName("Criterion: Check imports")
    void testImports(final TestCycle testCycle) {
      TutorUtils_H1.assertImports(testCycle, TutorConstants.H1_4_PATH_TO_SOURCE,
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
      return TutorUtils.assertMethod(clazz, TutorConstants.H1_2_METHOD_NAME,
        TutorConstants.H1_2_METHOD_CLASS_PARAMETER);
    }

    @Test
    @DisplayName("Criterion: Only modifiers public abstract")
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
      final var expectedField1 = TutorConstants.H1_1_FIELD_EXAMPLE_3_1;
      final var expectedField2 = TutorConstants.H1_1_FIELD_EXAMPLE_3_2;
      final var expectedField3 = TutorConstants.H1_1_FIELD_EXAMPLE_3_3;
      final var expectedField4 = TutorConstants.H1_1_FIELD_EXAMPLE_3_4;
      final var expectedField5 = TutorConstants.H1_1_FIELD_EXAMPLE_3_5;

      final var fieldClass = getTestFieldClass();
      final var fieldConstructor = TutorUtils.assertConstructor(fieldClass,
        TutorConstants.H1_1_FIELD_TYPE_1, TutorConstants.H1_1_FIELD_TYPE_2,
        TutorConstants.H1_1_FIELD_TYPE_3, TutorConstants.H1_1_FIELD_TYPE_4,
        TutorConstants.H1_4_FIELD_TYPE);

      final var fieldObject = TutorUtils.invokeConstructor(fieldConstructor, expectedField1,
        expectedField2, expectedField3, expectedField4, expectedField5);

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
      Assertions.assertEquals(TutorConstants.H1_1_FIELD_EXAMPLE_3_RESULT, result);
    }
  }

  @Test
  @ExtendWith(TestCycleResolver.class)
  @DisplayName("Criterion: Requirement - No arrays")
  void testRequirementArrays(final TestCycle testCycle) {
    final var processor = SpoonUtils.process(testCycle, TutorConstants.H1_4_PATH_TO_SOURCE,
      new ArraysInstantiationMethodBodyProcessor(TutorConstants.H1_2_METHOD_NAME));

    final var expected = 0;
    final var actual = processor.getArrays().size();
    Assertions.assertEquals(
      expected, actual,
      TutorMessage.REQUIREMENT_NO_ARRAY.format(expected, actual)
    );
  }

  @Test
  @ExtendWith(TestCycleResolver.class)
  @DisplayName("Criterion: Requirement - Only one foreach")
  void testRequirementForeachLoop(final TestCycle testCycle) {
    final var processor = SpoonUtils.process(testCycle, TutorConstants.H1_4_PATH_TO_SOURCE,
      new LoopsMethodBodyProcessor(TutorConstants.H1_2_METHOD_NAME));

    final var expectedForEach = 1;
    final var actualForEach = processor.getForeachLoops().size();
    Assertions.assertEquals(expectedForEach, actualForEach,
      TutorMessage.REQUIREMENT_FOREACH_LOOP.format(expectedForEach, actualForEach)
    );

    final var actualFor = processor.getForLoops().size();
    Assertions.assertTrue(processor.getForLoops().isEmpty(),
      TutorMessage.REQUIREMENT_FOREACH_LOOP.format(
        expectedForEach, String.format("%d for loop(s)", actualFor))
    );

    final var actualWhile = processor.getWhileLoops().size();
    Assertions.assertTrue(processor.getWhileLoops().isEmpty(),
      TutorMessage.REQUIREMENT_FOREACH_LOOP.format(
        expectedForEach, String.format("%d while loop(s)", actualFor))
    );

    final var actualDoWhile = processor.getDoWhileLoops().size();
    Assertions.assertTrue(processor.getDoWhileLoops().isEmpty(),
      TutorMessage.REQUIREMENT_FOREACH_LOOP.format(
        expectedForEach, String.format("%d do-while loop(s)", actualFor))
    );
  }

  @Nested
  @DisplayName("Criterion: Traits - Field combine")
  public final class TestH1ExtraField {

    @Nested
    @DisplayName("Criterion: Field for combining")
    public final class TestField {

      private Field getTestField() {
        final var clazz = getTestFieldClass();
        return TutorUtils.assertField(clazz, TutorConstants.H1_4_FIELD_NAME);
      }

      @Test
      @DisplayName("Criterion: Only modifiers private final")
      void testModifiers() {
        final var actual = getTestField();
        final var expected = Modifier.STATIC.negate().and(Modifier.PRIVATE, Modifier.FINAL);
        TutorUtils.assertModifiers(expected, actual);
      }

      @Test
      @DisplayName("Criterion: Type BiFunction<Y, ? super Y, Y>")
      void testType() {
        final var actual = getTestField();
        final var type = actual.getGenericType();
        TutorUtils.assertGenericType(
          TutorConstants.H1_4_FIELD_TYPE, TutorConstants.H1_4_FIELD_TYPE_PARAMETER, type
        );
      }

      @Test
      @DisplayName("Criterion: Getter method")
      void testGetter() {
        final var method = TutorUtils.assertMethod(getTestFieldClass(), TutorConstants.H1_4_METHOD_NAME);

        // Check modifier
        TutorUtils.assertModifiers(Modifier.STATIC.nand(Modifier.FINAL).and(Modifier.PUBLIC), method);

        // Check return type
        final var expected = TutorConstants.H1_4_FIELD_TYPE;
        final var actual = method.getReturnType();
        Assertions.assertEquals(
          expected, actual, TutorMessage.RETURN_TYPE_MISMATCH.format(expected, actual)
        );

        final var name = method.getGenericReturnType().getTypeName();
        Assertions.assertEquals(
          String.format(
            "%s<%s>", expected.getCanonicalName(), TutorConstants.H1_4_FIELD_TYPE_PARAMETER
          ), name, TutorMessage.RETURN_TYPE_MISMATCH.format(expected, actual)
        );
      }
    }

    @Nested
    @DisplayName("Criterion: Constructor")
    public final class TestConstructor {

      @Nested
      @DisplayName("Criterion: 3 arguments Constructor")
      public final class Test3Args {

        public Constructor<?> getTestConstructor() {
          final var clazz = getTestFieldClass();
          final Class<?>[] parameters = {TutorConstants.H1_1_FIELD_TYPE_1,
            TutorConstants.H1_1_FIELD_TYPE_2, TutorConstants.H1_1_FIELD_TYPE_3,
            TutorConstants.H1_1_FIELD_TYPE_4};
          return TutorUtils.assertConstructor(clazz, parameters);
        }

        @Test
        @DisplayName("Criterion: Only modifier public")
        void testModifiers() {
          final var actual = getTestConstructor();
          final var expected = Modifier.PUBLIC;
          TutorUtils.assertModifiers(expected, actual);
        }

        @Test
        @DisplayName("Criterion: Parameter Predicate<? super X>, Function<? super X, ? extends " +
          "Y>, BiFunction<Y, Z, Z>, Z")
        void testParameterTypes() {
          final var constructor = getTestConstructor();
          TutorUtils.assertConstructorParameters(constructor, List.of(
            new SimpleEntry<>(TutorConstants.H1_1_FIELD_TYPE_1,
              TutorConstants.H1_1_FIELD_TYPE_PARAMETER_1),
            new SimpleEntry<>(TutorConstants.H1_1_FIELD_TYPE_2,
              TutorConstants.H1_1_FIELD_TYPE_PARAMETER_2),
            new SimpleEntry<>(TutorConstants.H1_1_FIELD_TYPE_3,
              TutorConstants.H1_1_FIELD_TYPE_PARAMETER_3),
            new SimpleEntry<>(TutorConstants.H1_1_FIELD_TYPE_4,
              TutorConstants.H1_1_FIELD_TYPE_PARAMETER_4)
          ));
        }

        @Test
        @DisplayName("Criterion: Initialization of fields")
        void testFields() {
          final var constructor = getTestConstructor();

          final var expectedField1 = TutorConstants.H1_1_FIELD_EXAMPLE_3_1;
          final var expectedField2 = TutorConstants.H1_1_FIELD_EXAMPLE_3_2;
          final var expectedField3 = TutorConstants.H1_1_FIELD_EXAMPLE_3_3;
          final var expectedField4 = TutorConstants.H1_1_FIELD_EXAMPLE_3_4;

          final var instance = TutorUtils.invokeConstructor(constructor,
            expectedField1, expectedField2, expectedField3, expectedField4);

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

      @Nested
      @DisplayName("Criterion: 4 arguments Constructor")
      public final class Test4Args {

        public Constructor<?> getTestConstructor() {
          final var clazz = getTestFieldClass();
          final Class<?>[] parameters = {TutorConstants.H1_1_FIELD_TYPE_1,
            TutorConstants.H1_1_FIELD_TYPE_2, TutorConstants.H1_1_FIELD_TYPE_3,
            TutorConstants.H1_1_FIELD_TYPE_4, TutorConstants.H1_4_FIELD_TYPE};
          return TutorUtils.assertConstructor(clazz, parameters);
        }

        @Test
        @DisplayName("Criterion: Only modifier public")
        void testModifiers() {
          final var actual = getTestConstructor();
          final var expected = Modifier.PUBLIC;
          TutorUtils.assertModifiers(expected, actual);
        }

        @Test
        @DisplayName("Criterion: Parameter Predicate<? super X>, Function<? super X, ? extends " +
          "Y>, BiFunction<Y, Z, Z>, Z, BiFunction<Y, ? super Y, Y>")
        void testParameterTypes() {
          final var constructor = getTestConstructor();
          TutorUtils.assertConstructorParameters(constructor, List.of(
            new SimpleEntry<>(TutorConstants.H1_1_FIELD_TYPE_1,
              TutorConstants.H1_1_FIELD_TYPE_PARAMETER_1),
            new SimpleEntry<>(TutorConstants.H1_1_FIELD_TYPE_2,
              TutorConstants.H1_1_FIELD_TYPE_PARAMETER_2),
            new SimpleEntry<>(TutorConstants.H1_1_FIELD_TYPE_3,
              TutorConstants.H1_1_FIELD_TYPE_PARAMETER_3),
            new SimpleEntry<>(TutorConstants.H1_1_FIELD_TYPE_4,
              TutorConstants.H1_1_FIELD_TYPE_PARAMETER_4),
            new SimpleEntry<>(TutorConstants.H1_4_FIELD_TYPE,
              TutorConstants.H1_4_FIELD_TYPE_PARAMETER)
          ));
        }

        @Test
        @DisplayName("Criterion: Initialization of fields")
        void testFields() {
          final var constructor = getTestConstructor();

          final var expectedField1 = TutorConstants.H1_1_FIELD_EXAMPLE_3_1;
          final var expectedField2 = TutorConstants.H1_1_FIELD_EXAMPLE_3_2;
          final var expectedField3 = TutorConstants.H1_1_FIELD_EXAMPLE_3_3;
          final var expectedField4 = TutorConstants.H1_1_FIELD_EXAMPLE_3_4;
          final var expectedField5 = TutorConstants.H1_1_FIELD_EXAMPLE_3_5;

          final var instance = TutorUtils.invokeConstructor(constructor,
            expectedField1, expectedField2, expectedField3, expectedField4, expectedField5);

          // Check if fields are initialized
          final var actualField1 = TutorUtils.assertField(instance, TutorConstants.H1_1_FIELD_NAME_1);
          final var actualField2 = TutorUtils.assertField(instance, TutorConstants.H1_1_FIELD_NAME_2);
          final var actualField3 = TutorUtils.assertField(instance, TutorConstants.H1_1_FIELD_NAME_3);
          final var actualField4 = TutorUtils.assertField(instance, TutorConstants.H1_1_FIELD_NAME_4);
          final var actualField5 = TutorUtils.assertField(instance,
            TutorConstants.H1_4_FIELD_NAME);

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
          Assertions.assertEquals(
            expectedField5, TutorUtils.getFieldContent(actualField5, instance),
            TutorMessage.FIELD_CONTENT_MISMATCH.format(expectedField5, actualField4)
          );
        }
      }
    }
  }
}
