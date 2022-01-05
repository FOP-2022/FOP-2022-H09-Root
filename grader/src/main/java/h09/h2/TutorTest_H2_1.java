package h09.h2;

import h09.utils.Modifier;
import h09.utils.TutorConstants;
import h09.utils.TutorMessage;
import h09.utils.TutorUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Defines the JUnit test cases related to the interfaces and classes defined in the task H2.1.
 *
 * @author Nhan Huynh, Darya Nikitina
 */
@TestForSubmission("h09")
@DisplayName("Criterion: Animal Hierarchy")
public final class TutorTest_H2_1 {

  /* *********************************************************************
   *                            Utilities                                *
   **********************************************************************/

  private static Class<?> getTestClass(final String animal) {
    return TutorUtils.assertClass(TutorConstants.H2_PACKAGE_NAME, animal);
  }

  private static Method getMethod(final String animal) {
    final var clazz = getTestClass(animal);
    final var methodName = TutorConstants.H2_1_ANIMAL_CLASS_AND_METHOD.get(animal);
    return TutorUtils.assertMethod(clazz, methodName);
  }

  private static void assertInterfaceExtensions(final String animal, final String... extensions) {
    final var clazz = getTestClass(animal);
    final var interfaces = clazz.getInterfaces();

    if (extensions.length==0) {
      Assertions.assertEquals(0, interfaces.length,
        TutorMessage.INTERFACE_NO_EXTENSION.format(clazz.getSimpleName()));
    } else {
      final var expected = extensions.length;
      final var actual = interfaces.length;
      Assertions.assertEquals(expected, actual,
        TutorMessage.INTERFACE_EXTENSION_MISMATCH.format(clazz.getSimpleName(), expected,
          actual));
    }
  }

  /* *********************************************************************
   *                            Interfaces                               *
   **********************************************************************/

  @Nested
  @DisplayName("Criterion: Interfaces")
  public final class TestInterfaces {

    private void assertInterfaces(int expected) {
      int passed = 0;
      final List<String> passedAnimals = new ArrayList<>(expected);
      final var animals = TutorConstants.H2_1_ANIMAL_CLASS_AND_METHOD.keySet();
      for (final var animal : animals) {
        try {
          assertModifier(animal);
          final var extension = TutorConstants.H2_1_ANIMAL_CLASS_AND_INHERITANCE.get(animal);
          assertInterfaceExtensions(animal, extension);
          assertMethodModifiers(animal);
          assertMethodReturnType(animal);
          passedAnimals.add(animal);
          passed++;
        } catch (AssertionFailedError e) {
          // Do nothing, just checking how many passed
        }
      }
      Assertions.assertTrue(expected <= passed,
        TutorMessage.H2_1_INTERFACES_MISMATCH.format(expected, passed, String.join(", ", passedAnimals)));
    }

    private void assertModifier(final String animal) {
      final var actual = getTestClass(animal);
      final var expected = Modifier.PUBLIC.and(Modifier.INTERFACE);
      TutorUtils.assertModifiers(expected, actual);
    }

    public void assertMethodModifiers(final String name) {
      final var actual = getMethod(name);
      final var expected = Modifier.DEFAULT.nand(Modifier.STATIC);
      TutorUtils.assertModifiers(expected, actual);
    }

    private void assertMethodReturnType(final String name) {
      final var method = getMethod(name);
      final var expected = String.class;
      final var actual = method.getReturnType();
      Assertions.assertEquals(
        expected, actual,
        TutorMessage.RETURN_TYPE_MISMATCH.format(method.getName(), expected, actual));
    }

    @Test
    @DisplayName("Criterion: At least 4 interfaces are complete and correct")
    public void testInterfaces4() {
      assertInterfaces(4);
    }

    @Test
    @DisplayName("Criterion: At least 9 interfaces are complete and correct")
    public void testInterfaces9() {
      assertInterfaces(9);
    }
  }

  /* *********************************************************************
   *                          Class Rabbit                               *
   **********************************************************************/

  @Nested
  @DisplayName("Criterion: Class Rabbit")
  public final class TestRabbit {

    /* *********************************************************************
     *                            Utilities                                *
     **********************************************************************/

    private Class<?> getTestClass() {
      return TutorUtils.assertClass(TutorConstants.H2_PACKAGE_NAME, TutorConstants.H2_1_CLASS_NAME_10);
    }

    private Constructor<?> getTestConstructor() {
      final var clazz = getTestClass();
      return TutorUtils.assertConstructor(clazz);
    }

    private Field getTestField(final Predicate<Field> criterion, final String message) {
      final var clazz = getTestClass();
      for (final var field : clazz.getDeclaredFields()) {
        if (criterion.test(field)) {
          return field;
        }
      }
      return Assertions.fail(message);
    }

    private Field getStaticField() {
      return getTestField(f -> {
          final var modifiers = f.getModifiers();
          return java.lang.reflect.Modifier.isPrivate(modifiers) &&
            java.lang.reflect.Modifier.isStatic(modifiers) && f.getType().equals(Integer.TYPE);
        }, "The private static int field used to determine the next available name of the "
          + "a rabbit could not be found."
      );
    }

    private Field getNonStaticField() {
      return getTestField(f -> {
          final var modifiers = f.getModifiers();
          return java.lang.reflect.Modifier.isPrivate(modifiers) &&
            !java.lang.reflect.Modifier.isStatic(modifiers) && f.getType().equals(Integer.TYPE);
        },
        "The private int field used to determine the name of the rabbit "
          + "could not be found.");
    }

    private void resetCounter(final Runnable statements) {
      // Reset counter at the end
      final var counter = getStaticField();

      try {
        statements.run();
      } finally {
        TutorUtils.setFieldContent(counter, 1);
      }
    }

    /* *********************************************************************
     *                            Class Header                             *
     **********************************************************************/

    @Nested
    @DisplayName("Criterion: Class header")
    public final class TestClassHeader {

      @Test
      @DisplayName("Criterion: Only modifiers public")
      public void testModifiers() {
        final var actual = getTestClass();
        final var expected = Modifier.STATIC.nand(Modifier.ABSTRACT, Modifier.FINAL)
          .and(Modifier.PUBLIC);
        TutorUtils.assertModifiers(expected, actual);
      }

      @Test
      @DisplayName("Criterion: Extend Leporidae")
      public void testSuperClass() {
        assertInterfaceExtensions(TutorConstants.H2_1_CLASS_NAME_10, TutorConstants.H2_1_CLASS_NAME_9);
      }
    }

    /* *********************************************************************
     *                           Static Field                              *
     **********************************************************************/

    @Nested
    @DisplayName("Criterion: Static field int - Name counter")
    public final class TestFieldStatic {

      @Test
      @DisplayName("Criterion: Only modifiers private static")
      public void testModifiers() {
        final var actual = getStaticField();
        final var expected = Modifier.FINAL.negate().and(Modifier.PRIVATE, Modifier.STATIC);
        TutorUtils.assertModifiers(expected, actual);
      }

      @Test
      @DisplayName("Criterion: Initialization value is 1")
      public void testValue() {
        final var field = getStaticField();
        Assertions.assertEquals(1, TutorUtils.getFieldContent(field));
      }
    }

    /* *********************************************************************
     *                              Field                                  *
     **********************************************************************/

    @Nested
    @DisplayName("Criterion: Field int - Name of the individual")
    public final class TestField {

      @Test
      @DisplayName("Criterion: Only modifiers private final")
      public void testModifiers() {
        final var actual = getNonStaticField();
        final var expected = Modifier.STATIC.negate().and(Modifier.PRIVATE, Modifier.FINAL);
        TutorUtils.assertModifiers(expected, actual);
      }
    }

    /* *********************************************************************
     *                            Constructor                              *
     **********************************************************************/

    @Nested
    @DisplayName("Criterion: Constructor")
    public final class TestConstructor {

      @Test
      @DisplayName("Criterion: public")
      public void testModifiers() {
        final var actual = getTestConstructor();
        final var expected = Modifier.PUBLIC;
        TutorUtils.assertModifiers(expected, actual);
      }

      @Test
      @DisplayName("Criterion: Instantiation of objects increase static field")
      public void testFieldStatic() {
        resetCounter(() -> {
          final var constructor = getTestConstructor();
          for (int i = 0; i < 5; i++) {
            TutorUtils.invokeConstructor(constructor);
            final var field = getStaticField();

            final var expected = i + 2;
            final var actual = TutorUtils.getFieldContent(field);

            Assertions.assertEquals(expected, actual,
              TutorMessage.H2_1_FIELD_MISMATCH.format(field.getName(), expected, i + 1, actual));
          }
        });
      }

      @Test
      @DisplayName("Criterion: Instantiation of objects increase non-static field")
      public void testField() {
        resetCounter(() -> {
          final var constructor = getTestConstructor();
          for (int i = 0; i < 5; i++) {
            final var instance = TutorUtils.invokeConstructor(constructor);
            final var field = getNonStaticField();

            final var expected = i + 1;
            final var actual = TutorUtils.getFieldContent(field, instance);

            Assertions.assertEquals(expected, actual,
              TutorMessage.H2_1_FIELD_MISMATCH.format(field.getName(), expected, i + 1, actual));
          }
        });
      }

      @Test
      @DisplayName("Criterion: Method getNameOfIndividualeOfVertebrate return value")
      public void testMethodNameOfIndividual() {
        resetCounter(() -> {
          final var constructor = getTestConstructor();
          final var clazz = getTestClass();
          final var method = TutorUtils.assertMethod(clazz, TutorConstants.H2_1_METHOD_NAME_9);
          // Check fields
          for (int i = 0; i < 5; i++) {
            final var instance = TutorUtils.invokeConstructor(constructor);
            final var expected = String.valueOf(i + 1);
            final var actual = TutorUtils.invokeMethod(method, instance);

            Assertions.assertEquals(expected, actual,
              TutorMessage.RETURN_VALUE_MISMATCH.format(method.getName(), expected, actual));
          }
        });
      }
    }

    /* *********************************************************************
     *                              Methods                                *
     **********************************************************************/

    @Nested
    @DisplayName("Criterion: Method typeofX")
    public class TestMethodsTypeOfX {

      private void assertTypeOfXReturnValue(final String name, final String expected) {
        resetCounter(() -> {
          final var constructor = getTestConstructor();
          final var instance = TutorUtils.invokeConstructor(constructor);
          final var method = getMethod(name);
          final var actual = TutorUtils.invokeMethod(method, instance);

          Assertions.assertEquals(expected, actual,
            TutorMessage.H2_1_METHOD_TYPE_OF_X_MISMATCH.format(name, expected, actual));
        });
      }

      @Test
      @DisplayName("Criterion: Method getTypeOfAnimal return value")
      public void testTypeOfAnimal() {
        assertTypeOfXReturnValue(TutorConstants.H2_1_CLASS_NAME_1,
          TutorConstants.H2_1_CLASS_NAME_2);
      }

      @Test
      @DisplayName("Criterion: Method getTypeOfVertebrate return value")
      public void testGetTypeOfVertebrate() {
        assertTypeOfXReturnValue(TutorConstants.H2_1_CLASS_NAME_2,
          TutorConstants.H2_1_CLASS_NAME_3);
      }

      @Test
      @DisplayName("Criterion: Method getTypeOfMammal return value")
      public void testTypeOfMammal() {
        assertTypeOfXReturnValue(TutorConstants.H2_1_CLASS_NAME_3,
          TutorConstants.H2_1_CLASS_NAME_5);
      }

      @Test
      @DisplayName("Criterion: Method getTypeOfPlacental return value")
      public void testTypeOfPlacental() {
        assertTypeOfXReturnValue(TutorConstants.H2_1_CLASS_NAME_5,
          TutorConstants.H2_1_CLASS_NAME_7);
      }


      @Test
      @DisplayName("Criterion: Method getTypeOfLagomorpha return value")
      public void testTypeOfLagomorpha() {
        assertTypeOfXReturnValue(TutorConstants.H2_1_CLASS_NAME_7,
          TutorConstants.H2_1_CLASS_NAME_9);
      }
    }
  }
}
