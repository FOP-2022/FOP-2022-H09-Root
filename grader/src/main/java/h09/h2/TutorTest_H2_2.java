package h09.h2;

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
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

@TestForSubmission("h09")
@DisplayName("Criterion: Class BiologyHierarchy")
public final class
TutorTest_H2_2 {

  private static Class<?> getTestClass() {
    return TutorUtils.assertClass(TutorConstants.H2_PACKAGE_NAME, TutorConstants.H2_2_CLASS_NAME);
  }

  private static Constructor<?> getTestConstructor() {
    final var clazz = getTestClass();
    return TutorUtils.assertConstructor(clazz);
  }

  private static Object getTestAnimal(final String animal) {
    final var clazz = TutorUtils.assertClass(TutorConstants.H2_PACKAGE_NAME, animal);
    final var constructor = TutorUtils.assertConstructor(clazz);
    return TutorUtils.invokeConstructor(constructor);
  }

  @Nested
  @DisplayName("Criterion: Class Header")
  public final class TestClassHeader {

    @Test
    @DisplayName("Criterion: Only modifiers public")
    void testModifiers() {
      final var actual = getTestClass();
      final var expected = Modifier.STATIC.nand(Modifier.ABSTRACT, Modifier.FINAL)
        .and(Modifier.PUBLIC);
      TutorUtils.assertModifiers(expected, actual);
    }

    @Test
    @DisplayName("Criterion: No type parameter")
    void testTypeParameter() {
      final var clazz = getTestClass();
      final var types = clazz.getTypeParameters();
      final var expected = 0;
      final var actual = types.length;
      Assertions.assertEquals(expected, actual,
        TutorMessage.CLASS_TYPE_PARAMETER_MISMATCH_SIZE.format(clazz.getSimpleName(), expected,
          actual));
    }
  }

  @Nested
  @DisplayName("Criterion: Method typeOfVertebrate")
  public final class TestMethod1 {

    private Method getTestMethod() {
      final var clazz = getTestClass();
      final var parameter = getParameterClass();
      return TutorUtils.assertMethod(clazz, TutorConstants.H2_2_METHOD_NAME_1, parameter);
    }

    private Class<?> getParameterClass() {
      return TutorUtils.assertClass(TutorConstants.H2_PACKAGE_NAME,
        TutorConstants.H2_2_METHOD_TYPE_PARAMETER_1);
    }

    @Test
    @DisplayName("Criterion: Only modifiers public")
    void testModifiers() {
      final var actual = getTestMethod();
      final var expected = Modifier.STATIC.nand(Modifier.FINAL, Modifier.ABSTRACT)
        .and(Modifier.PUBLIC);
      TutorUtils.assertModifiers(expected, actual);
    }

    @Test
    @DisplayName("Criterion: Generic method <T extends Vertebrate>")
    void testGeneric() {
      final var method = getTestMethod();
      final var types = method.getTypeParameters();

      // Check generic type name
      final var expectedType = TutorConstants.H2_2_METHOD_BOUND_1;
      final var actualType = types[0].getTypeName();
      Assertions.assertEquals(expectedType, actualType,
        TutorMessage.TYPE_PARAMETER_MISMATCH.format(expectedType, actualType));

      // Check bound
      final var actualBound = types[0].getBounds();
      final var expectedLength = 1;
      final var actualLength = actualBound.length;

      Assertions.assertEquals(expectedLength, actualLength,
        TutorMessage.TYPE_PARAMETERS_MISMATCH_SIZE.format(expectedLength, actualLength));

      // Check bound type
      final var parameter = getParameterClass();
      final var expectedBoundType = parameter.getName();
      final var actualBoundType = actualBound[0].getTypeName();
      Assertions.assertEquals(expectedBoundType, actualBoundType,
        TutorMessage.TYPE_PARAMETER_MISMATCH.format(expectedBoundType, actualBoundType));
    }

    @Test
    @DisplayName("Criterion: Parameter T")
    void testParameters() {
      final var method = getTestMethod();
      final var parameters = method.getGenericParameterTypes();

      // Check parameter
      final var expectedParameters = 1;
      final var actualParameters = parameters.length;
      Assertions.assertEquals(expectedParameters, actualParameters,
        TutorMessage.METHOD_PARAMETER_MISMATCH_SIZE.format(method.getName(), expectedParameters,
          actualParameters));

      // Check type
      final var expectedType = TutorConstants.H2_2_METHOD_BOUND_1;
      final var actualType = parameters[0].getTypeName();
      Assertions.assertEquals(expectedType, actualType,
        TutorMessage.METHOD_PARAMETER_MISMATCH.format(method.getName(), expectedType, actualType));
    }

    @Test
    @DisplayName("Criterion: Result")
    void testResult() {
      final var constructor = getTestConstructor();
      final var instance = TutorUtils.invokeConstructor(constructor);
      final var method = getTestMethod();
      Assertions.assertNull(
        TutorUtils.invokeMethod(method, instance, getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_2)));
      Assertions.assertEquals(TutorConstants.H2_1_CLASS_NAME_3,
        TutorUtils.invokeMethod(method, instance,
          getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_3)));
      Assertions.assertEquals(TutorConstants.H2_1_CLASS_NAME_4,
        TutorUtils.invokeMethod(method, instance,
          getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_4)));
      Assertions.assertEquals(TutorConstants.H2_1_CLASS_NAME_3,
        TutorUtils.invokeMethod(method, instance,
          getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_5)));
      Assertions.assertEquals(TutorConstants.H2_1_CLASS_NAME_3,
        TutorUtils.invokeMethod(method, instance,
          getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_6)));
      Assertions.assertEquals(TutorConstants.H2_1_CLASS_NAME_3,
        TutorUtils.invokeMethod(method, instance,
          getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_7)));
    }
  }

  @Nested
  @DisplayName("Criterion: Method returnAsLagomorphs")
  public final class TestMethod2 {

    private Method getTestMethod() {
      final var clazz = getTestClass();
      return TutorUtils.assertMethod(clazz, TutorConstants.H2_2_METHOD_NAME_2,
        TutorConstants.H2_2_METHOD_CLASS_PARAMETER_2);
    }

    @Test
    @DisplayName("Criterion: Only modifiers public")
    void testModifiers() {
      final var actual = getTestMethod();
      final var expected = Modifier.STATIC.nand(Modifier.FINAL, Modifier.ABSTRACT)
        .and(Modifier.PUBLIC);
      TutorUtils.assertModifiers(expected, actual);
    }

    @Test
    @DisplayName("Criterion: Parameter List<? super Lagomorph>")
    void testParameters() {
      final var method = getTestMethod();
      final var parameters = method.getGenericParameterTypes();

      // Check parameter
      final var expectedParameters = 1;
      final var actualParameters = parameters.length;
      Assertions.assertEquals(expectedParameters, actualParameters,
        TutorMessage.METHOD_PARAMETER_MISMATCH_SIZE.format(method.getName(), expectedParameters,
          actualParameters));

      // Check type
      final var expectedType = TutorConstants.H2_2_METHOD_BOUND_2;
      final var actualType = parameters[0];
      TutorUtils.assertGenericType(TutorConstants.H2_2_METHOD_CLASS_PARAMETER_2, expectedType, actualType);
    }

    @Test
    @DisplayName("Criterion: Result List<Placental>")
    void testResultListOfPlacental() {
      final var constructor = getTestConstructor();
      final var instance = TutorUtils.invokeConstructor(constructor);
      final var method = getTestMethod();

      final var placental = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_5);
      final var rodent = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_8);
      final var lagomorpha = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_7);
      final var leporidae = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_9);

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
      final var constructor = getTestConstructor();
      final var instance = TutorUtils.invokeConstructor(constructor);
      final var method = getTestMethod();

      final var mammal = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_3);
      final var placental = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_5);
      final var monotreme = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_6);
      final var rodent = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_8);
      final var lagomorpha = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_7);
      final var leporidae = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_9);

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
      final var constructor = getTestConstructor();
      final var instance = TutorUtils.invokeConstructor(constructor);
      final var method = getTestMethod();

      final var vertebrate = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_2);
      final var mammal = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_3);
      final var bird = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_4);
      final var placental = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_5);
      final var monotreme = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_6);
      final var rodent = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_8);
      final var lagomorpha = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_7);
      final var leporidae = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_9);

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
      final var constructor = getTestConstructor();
      final var instance = TutorUtils.invokeConstructor(constructor);
      final var method = getTestMethod();

      final var animal = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_1);
      final var vertebrate = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_2);
      final var mammal = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_3);
      final var bird = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_4);
      final var placental = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_5);
      final var monotreme = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_6);
      final var rodent = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_8);
      final var lagomorpha = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_7);
      final var leporidae = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_9);

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
      final var constructor = getTestConstructor();
      final var instance = TutorUtils.invokeConstructor(constructor);
      final var method = getTestMethod();

      final var animal = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_1);
      final var vertebrate = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_2);
      final var mammal = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_3);
      final var bird = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_4);
      final var placental = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_5);
      final var monotreme = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_6);
      final var rodent = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_8);
      final var lagomorpha = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_7);
      final var leporidae = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_9);

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

  @Nested
  @DisplayName("Criterion: Method testTypeOfMammals:")
  public final class TestMethod3 {

    private Method getTestMethod() {
      final var clazz = getTestClass();
      return TutorUtils.assertMethod(clazz, TutorConstants.H2_2_METHOD_NAME_3,
        TutorConstants.H2_2_METHOD_CLASS_PARAMETER_3);
    }

    @Test
    @DisplayName("Criterion: Only modifiers public")
    void testModifiers() {
      final var actual = getTestMethod();
      final var expected = Modifier.STATIC.nand(Modifier.FINAL, Modifier.ABSTRACT)
        .and(Modifier.PUBLIC);
      TutorUtils.assertModifiers(expected, actual);
    }

    @Test
    @DisplayName("Criterion: Parameter List<? extends Mammal>")
    void testParameters() {
      final var method = getTestMethod();
      final var parameters = method.getGenericParameterTypes();

      // Check parameter
      final var expectedParameters = 1;
      final var actualParameters = parameters.length;
      Assertions.assertEquals(expectedParameters, actualParameters,
        TutorMessage.METHOD_PARAMETER_MISMATCH_SIZE.format(method.getName(), expectedParameters,
          actualParameters));

      // Check type
      final var expectedType = TutorConstants.H2_2_METHOD_BOUND_3;
      final var actualType = parameters[0];
      TutorUtils.assertGenericType(TutorConstants.H2_2_METHOD_CLASS_PARAMETER_3, expectedType,
        actualType);
    }

    @Test
    @DisplayName("Criterion: Result List<Mammal>")
    void testResultListOfMammal() {
      final var constructor = getTestConstructor();
      final var instance = TutorUtils.invokeConstructor(constructor);
      final var method = getTestMethod();

      final var mammal = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_3);
      final var placental = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_5);
      final var monotreme = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_6);
      final var rodent = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_8);
      final var lagomorpha = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_7);
      final var leporidae = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_9);

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
      final var constructor = getTestConstructor();
      final var instance = TutorUtils.invokeConstructor(constructor);
      final var method = getTestMethod();

      final var placental = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_5);
      final var rodent = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_8);
      final var lagomorpha = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_7);
      final var leporidae = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_9);

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
      final var constructor = getTestConstructor();
      final var instance = TutorUtils.invokeConstructor(constructor);
      final var method = getTestMethod();

      final var monotreme = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_6);

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
      final var constructor = getTestConstructor();
      final var instance = TutorUtils.invokeConstructor(constructor);
      final var method = getTestMethod();

      final var lagomorpha = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_7);
      final var leporidae = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_9);

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
      final var constructor = getTestConstructor();
      final var instance = TutorUtils.invokeConstructor(constructor);
      final var method = getTestMethod();

      final var rodent = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_8);

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
      final var constructor = getTestConstructor();
      final var instance = TutorUtils.invokeConstructor(constructor);
      final var method = getTestMethod();

      final var leporidae = getTestAnimal(TutorConstants.H2_3_CLASS_NAME_TUTOR_9);

      final var animals = List.of(
        leporidae
      );
      final var expected = List.of("Placental");

      Assertions.assertEquals(expected,
        TutorUtils.invokeMethod(method, instance, animals));
    }
  }
}

