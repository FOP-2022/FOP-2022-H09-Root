package h09.h2;

import h09.TutorUtils;
import h09.TutorUtils.Modifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.function.Predicate;

/**
 * Defines the JUnit test cases for the classes defined in H2.1.
 *
 * @author Nhan Huynh, Darya Nikitina
 */
@DisplayName("Criterion: Animal Hierarchy")
final class TutorTest_H2_1 {

  /**
   * The name of the package where the class for the test cases should exist.
   */
  private static final String PACKAGE_NAME = "h09.h2";

  /**
   * The name of the class {@code Animal} which should be tested.
   */
  private static final String CLASS_NAME_ANIMAL = "Animal";

  /**
   * The name of the class {@code Vertebrate} which should be tested.
   */
  private static final String CLASS_NAME_VERTEBRATE = "Vertebrate";

  /**
   * The name of the class {@code Mammal} which should be tested.
   */
  private static final String CLASS_NAME_MAMMAL = "Mammal";

  /**
   * The name of the class {@code Bird} which should be tested.
   */
  private static final String CLASS_NAME_BIRD = "Bird";

  /**
   * The name of the class {@code Placental} which should be tested.
   */
  private static final String CLASS_NAME_PLACENTAL = "Placental";

  /**
   * The name of the class {@code Monotreme} which should be tested.
   */
  private static final String CLASS_NAME_MONOTREME = "Monotreme";

  /**
   * The name of the class {@code Lagomorpha} which should be tested.
   */
  private static final String CLASS_NAME_LAGOMORPHA = "Lagomorpha";

  /**
   * The name of the class {@code Rodent} which should be tested.
   */
  private static final String CLASS_NAME_RODENT = "Rodent";

  /**
   * The name of the class {@code Leporidae} which should be tested.
   */
  private static final String CLASS_NAME_LEPORIDAE = "Leporidae";

  /**
   * The name of the class {@code Rabbit} which should be tested.
   */
  private static final String CLASS_NAME_RABBIT = "Rabbit";

  /**
   * Tests if the specified interface contains the modifiers {@code public} and {@code interface}.
   *
   * @param name the class name to check
   */
  private static void assertInterfaceModifiers(final String name) {
    TutorUtils.assertModifiers(TutorUtils.getClass(PACKAGE_NAME, name), List.of(Modifier.PUBLIC,
      Modifier.INTERFACE));
  }

  /**
   * Tests if the (interface) header is correctly defined and if it extends the specified
   * interfaces. If the interface does not extend any other interfaces, the {@code extensions} can
   * be {@code null}.
   *
   * @param extensions the possible interfaces that the interface extends
   */
  private static void assertInterfaceExtensions(final String name, final String... extensions) {
    final var clazz = TutorUtils.getClass(PACKAGE_NAME, name);
    final var interfaces = clazz.getInterfaces();

    if (extensions==null) {
      Assertions.assertEquals(0, interfaces.length,
        String.format("The interface %s should not extends any other interfaces.",
          clazz.getSimpleName()));
    } else {
      Assertions.assertEquals(extensions.length, interfaces.length,
        String.format("The interface %s should extends %s interface(s), given %s",
          clazz.getSimpleName(), extensions.length, interfaces.length));
    }
  }

  /**
   * Returns the method typeOfX where X is the name of the interface. The method name starts with
   * the prefix typeOf followed by its interface name.
   *
   * @param name the class name of the method
   * @param type if it's a method typeOf*
   *
   * @return the method typeOfX where X is the name of the interface
   */
  private static Method getMethodTypeOfX(final String name, boolean type) {
    final var clazz = TutorUtils.getClass(PACKAGE_NAME, name);
    final var methodName = !type ? String.format("typeOf%s", clazz.getSimpleName()):
      "nameOfIndividual";
    return TutorUtils.getMethod(clazz, methodName);
  }

  /**
   * Tests if the method in the interface is correctly defined with its only modifier public and no
   * parameters.
   *
   * @param name the class name pf the method to check
   * @param type if it's a method typeOf*
   */
  private static void assertMethodModifiers(final String name, boolean type) {
    final var method = getMethodTypeOfX(name, type);
    TutorUtils.assertModifiers(method, null, List.of(Modifier.STATIC));
    Assertions.assertFalse(method.isDefault(),
      String.format("The method %s should not be a default method", method.getName()));
  }

  /**
   * Tests if the method return type is correctly defined. The return type is a {@link String}.
   *
   * @param name the class name pf the method to check
   * @param type if it's a method typeOf*
   */
  private static void assertMethodReturnType(final String name, boolean type) {
    final var method = getMethodTypeOfX(name, type);
    final var expected = String.class;
    final var actual = method.getReturnType();
    Assertions.assertEquals(expected, actual,
      String.format("The return type of the method %s must be %s, given %s.", method.getName(),
        expected, actual));
  }

  /**
   * Defines the JUnit test cases for the interface {@code Animal}.
   *
   * @author Nhan Huynh, Darya Nikitina
   */
  @DisplayName("Criterion: Interface Animal")
  @Nested
  final class TestAnimal {

    /**
     * Defines the JUnit tests cases related to the class header.
     */
    @Nested
    @DisplayName("Criterion: Class header")
    final class TestClassHeader {

      @Test
      @DisplayName("Criterion: Only modifiers public and interface")
      void testModifiers() {
        assertInterfaceModifiers(CLASS_NAME_ANIMAL);
      }

      @Test
      @DisplayName("Criterion: No extension")
      void testSuperClass() {
        assertInterfaceExtensions(CLASS_NAME_ANIMAL);
      }
    }

    /**
     * Defines the JUnit tests cases related to method {@code typeOfAnimal}.
     */
    @Nested
    @DisplayName("Criterion: Method typeOfAnimal")
    final class TestMethodTypeOfAnimal {

      @Test
      @DisplayName("Criterion: Only modifier public and no parameters")
      void testModifiers() {
        assertMethodModifiers(CLASS_NAME_ANIMAL, false);
      }

      @Test
      @DisplayName("Criterion: Return type is String")
      void testReturnType() {
        assertMethodReturnType(CLASS_NAME_ANIMAL, false);
      }
    }
  }

  /**
   * Defines the JUnit test cases for the interface {@code Vertebrate}.
   */
  @DisplayName("Criterion: Interface Vertebrate")
  @Nested
  final class TestVertebrate {
    /**
     * Defines the JUnit tests cases related to the class header.
     */
    @Nested
    @DisplayName("Criterion: Class header")
    final class TestClassHeader {

      @Test
      @DisplayName("Criterion: Only modifiers public and interface")
      void testModifiers() {
        assertInterfaceModifiers(CLASS_NAME_VERTEBRATE);
      }

      @Test
      @DisplayName("Criterion: Extend Animal")
      void testSuperClass() {
        assertInterfaceExtensions(CLASS_NAME_VERTEBRATE, CLASS_NAME_ANIMAL);
      }
    }

    /**
     * Defines the JUnit tests cases related to method {@code typeOfVertebrate}.
     */
    @Nested
    @DisplayName("Criterion: Method typeOfVertebrate")
    final class TestMethodTypeOfAnimal {

      @Test
      @DisplayName("Criterion: Only modifier public and no parameters")
      void testModifiers() {
        assertMethodModifiers(CLASS_NAME_VERTEBRATE, false);
      }

      @Test
      @DisplayName("Criterion: Return type is String")
      void testReturnType() {
        assertMethodReturnType(CLASS_NAME_VERTEBRATE, false);
      }
    }
  }

  /**
   * Defines the JUnit test cases for the interface {@code Mammal}.
   */
  @Nested
  @DisplayName("Criterion: Interface Mammal")
  final class TestMammal {

    /**
     * Defines the JUnit tests cases related to the class header.
     */
    @Nested
    @DisplayName("Criterion: Class header")
    final class TestClassHeader {

      @Test
      @DisplayName("Criterion: Only modifiers public and interface")
      void testModifiers() {
        assertInterfaceModifiers(CLASS_NAME_MAMMAL);
      }

      @Test
      @DisplayName("Criterion: Extend Vertebrate")
      void testSuperClass() {
        assertInterfaceExtensions(CLASS_NAME_MAMMAL, CLASS_NAME_VERTEBRATE);
      }
    }

    /**
     * Defines the JUnit tests cases related to method {@code typeOfMammal}.
     */
    @Nested
    @DisplayName("Criterion: Method typeOfMammal")
    class TestMethodTypeOfAnimal {

      @Test
      @DisplayName("Criterion: Only modifier public and no parameters")
      void testModifiers() {
        assertMethodModifiers(CLASS_NAME_MAMMAL, false);
      }

      @Test
      @DisplayName("Criterion: Return type is String")
      void testReturnType() {
        assertMethodReturnType(CLASS_NAME_MAMMAL, false);
      }
    }
  }

  /**
   * Defines the JUnit test cases for the interface {@code Bird}.
   */
  @Nested
  @DisplayName("Criterion: Interface Bird")
  final class TestBird {

    /**
     * Defines the JUnit tests cases related to the class header.
     */
    @Nested
    @DisplayName("Criterion: Class header")
    final class TestClassHeader {

      @Test
      @DisplayName("Criterion: Only modifiers public and interface")
      void testModifiers() {
        assertInterfaceModifiers(CLASS_NAME_BIRD);
      }

      @Test
      @DisplayName("Criterion: Extend Vertebrate")
      void testSuperClass() {
        assertInterfaceExtensions(CLASS_NAME_BIRD, CLASS_NAME_VERTEBRATE);
      }
    }

    /**
     * Defines the JUnit tests cases related to method {@code typeOfBird}.
     */
    @Nested
    @DisplayName("Criterion: Method typeOfBird")
    final class TestMethodTypeOfAnimal {

      @Test
      @DisplayName("Criterion: Only modifier public and no parameters")
      void testModifiers() {
        assertMethodModifiers(CLASS_NAME_BIRD, false);
      }

      @Test
      @DisplayName("Criterion: Return type is String")
      void testReturnType() {
        assertMethodReturnType(CLASS_NAME_BIRD, false);
      }
    }
  }

  /**
   * Defines the JUnit test cases for the interface {@code Placental}.
   */
  @Nested
  @DisplayName("Criterion: Interface Placental")
  final class TestPlacental {

    /**
     * Defines the JUnit tests cases related to the class header.
     */
    @Nested
    @DisplayName("Criterion: Class header")
    final class TestClassHeader {

      @Test
      @DisplayName("Criterion: Only modifiers public and interface")
      void testModifiers() {
        assertInterfaceModifiers(CLASS_NAME_PLACENTAL);
      }

      @Test
      @DisplayName("Criterion: Extend Mammal")
      void testSuperClass() {
        assertInterfaceExtensions(CLASS_NAME_PLACENTAL, CLASS_NAME_MAMMAL);
      }
    }

    /**
     * Defines the JUnit tests cases related to method {@code typeOfPlacental}.
     */
    @Nested
    @DisplayName("Criterion: Method typeOfPlacental")
    class TestMethodTypeOfAnimal {

      @Test
      @DisplayName("Criterion: Only modifier public and no parameters")
      void testModifiers() {
        assertMethodModifiers(CLASS_NAME_PLACENTAL, false);
      }

      @Test
      @DisplayName("Criterion: Return type is String")
      void testReturnType() {
        assertMethodReturnType(CLASS_NAME_PLACENTAL, false);
      }
    }
  }

  /**
   * Defines the JUnit test cases for the interface {@code Monotreme}.
   */
  @Nested
  @DisplayName("Criterion: Interface Monotreme")
  final class TestMonotreme {


    /**
     * Defines the JUnit tests cases related to the class header.
     */
    @Nested
    @DisplayName("Criterion: Class header")
    final class TestClassHeader {

      @Test
      @DisplayName("Criterion: Only modifiers public and interface")
      void testModifiers() {
        assertInterfaceModifiers(CLASS_NAME_MONOTREME);
      }

      @Test
      @DisplayName("Criterion: Extend Mammal")
      void testSuperClass() {
        assertInterfaceExtensions(CLASS_NAME_MONOTREME, CLASS_NAME_MAMMAL);
      }
    }

    /**
     * Defines the JUnit tests cases related to method {@code typeOfMonotreme}.
     */
    @Nested
    @DisplayName("Criterion: Method typeOfMonotreme")
    class TestMethodTypeOfAnimal {

      @Test
      @DisplayName("Criterion: Only modifier public and no parameters")
      void testModifiers() {
        assertMethodModifiers(CLASS_NAME_MONOTREME, false);
      }

      @Test
      @DisplayName("Criterion: Return type is String")
      void testReturnType() {
        assertMethodReturnType(CLASS_NAME_MONOTREME, false);
      }
    }
  }

  /**
   * Defines the JUnit test cases for the interface {@code Lagomorpha}.
   */
  @Nested
  @DisplayName("Criterion: Interface Lagomorpha")
  final class TestLagomorpha {

    /**
     * Defines the JUnit tests cases related to the class header.
     */
    @Nested
    @DisplayName("Criterion: Class header")
    final class TestClassHeader {

      @Test
      @DisplayName("Criterion: Only modifiers public and interface")
      void testModifiers() {
        assertInterfaceModifiers(CLASS_NAME_LAGOMORPHA);
      }

      @Test
      @DisplayName("Criterion: Extend Placental")
      void testSuperClass() {
        assertInterfaceExtensions(CLASS_NAME_LAGOMORPHA, CLASS_NAME_PLACENTAL);
      }
    }

    /**
     * Defines the JUnit tests cases related to method {@code typeOfLagomorph}.
     */
    @Nested
    @DisplayName("Criterion: Method typeOfLagomorpha")
    class TestMethodTypeOfAnimal {

      @Test
      @DisplayName("Criterion: Only modifier public and no parameters")
      void testModifiers() {
        assertMethodModifiers(CLASS_NAME_LAGOMORPHA, false);
      }

      @Test
      @DisplayName("Criterion: Return type is String")
      void testReturnType() {
        assertMethodReturnType(CLASS_NAME_LAGOMORPHA, false);
      }
    }
  }

  /**
   * Defines the JUnit test cases for the interface {@code Rodent}.
   */
  @Nested
  @DisplayName("Criterion: Interface Rodent")
  final class TestRodent {

    /**
     * Defines the JUnit tests cases related to the class header.
     */
    @Nested
    @DisplayName("Criterion: Class header")
    final class TestClassHeader {

      @Test
      @DisplayName("Criterion: Only modifiers public and interface")
      void testModifiers() {
        assertInterfaceModifiers(CLASS_NAME_RODENT);
      }

      @Test
      @DisplayName("Criterion: Extend Lagomorpha")
      void testSuperClass() {
        assertInterfaceExtensions(CLASS_NAME_RODENT, CLASS_NAME_LAGOMORPHA);
      }
    }

    /**
     * Defines the JUnit tests cases related to method {@code typeOfRodent}.
     */
    @Nested
    @DisplayName("Criterion: Method typeOfRodent")
    class TestMethodTypeOfAnimal {

      @Test
      @DisplayName("Criterion: Only modifier public and no parameters")
      void testModifiers() {
        assertMethodModifiers(CLASS_NAME_RODENT, false);
      }

      @Test
      @DisplayName("Criterion: Return type is String")
      void testReturnType() {
        assertMethodReturnType(CLASS_NAME_RODENT, false);
      }
    }
  }

  /**
   * Defines the JUnit test cases for the interface {@code Leporidae}.
   */
  @Nested
  @DisplayName("Criterion: Interface Leporidae")
  final class TestLeporidae {

    /**
     * Defines the JUnit tests cases related to the class header.
     */
    @Nested
    @DisplayName("Criterion: Class header")
    final class TestClassHeader {

      @Test
      @DisplayName("Criterion: Only modifiers public and interface")
      void testModifiers() {
        assertInterfaceModifiers(CLASS_NAME_LEPORIDAE);
      }

      @Test
      @DisplayName("Criterion: Extend Lagomorpha")
      void testSuperClass() {
        assertInterfaceExtensions(CLASS_NAME_LEPORIDAE, CLASS_NAME_LAGOMORPHA);
      }
    }

    /**
     * Defines the JUnit tests cases related to method {@code nameOfIndividual}.
     */
    @Nested
    @DisplayName("Criterion: Method nameOfIndividual")
    class TestMethodTypeOfAnimal {

      @Test
      @DisplayName("Criterion: Only modifier public and no parameters")
      void testModifiers() {
        assertMethodModifiers(CLASS_NAME_LEPORIDAE, true);
      }

      @Test
      @DisplayName("Criterion: Return type is String")
      void testReturnType() {
        assertMethodReturnType(CLASS_NAME_LEPORIDAE, true);
      }
    }
  }

  /**
   * Defines the JUnit test cases for the class {@code Rabbit}.
   */
  @Nested
  @DisplayName("Criterion: Class Rabbit")
  final class TestRabbit {

    /**
     * Returns the class instance of the class {@value #CLASS_NAME_RABBIT} which should be tested.
     *
     * @return the class instance of the class {@value #CLASS_NAME_RABBIT} which should be tested.
     */
    private Class<?> getTestClass() {
      return TutorUtils.getClass(PACKAGE_NAME, CLASS_NAME_RABBIT);
    }

    /**
     * Returns the searched field in the class {@code Rabbit} which fulfills the specified predicate.
     *
     * @param criterion the predicate that determines which field should be accepted
     * @param message   the message if the field could not be found
     *
     * @return the searched field in the specified class which fulfills the specified predicate
     *
     * @throws RuntimeException if the field could not be found
     */
    private Field getStaticField(final Predicate<Field> criterion,
                                 final String message) {
      final var clazz = getTestClass();
      for (final var field : clazz.getDeclaredFields()) {
        if (criterion.test(field)) {
          return field;
        }
      }
      Assertions.fail(message);
      throw new RuntimeException();
    }

    /**
     * Returns the static field that will be checked in the test cases. The field represents the next
     * available name in integer of a {@code Rabbit}.
     *
     * @return the static field that will be checked in the test cases
     */
    private Field getStaticField() {
      return getStaticField(f -> {
          final var modifiers = f.getModifiers();
          return java.lang.reflect.Modifier.isPrivate(modifiers) &&
            java.lang.reflect.Modifier.isStatic(modifiers) && f.getType().equals(Integer.TYPE);
        },
        "The private static int field used to determine the next available name of the "
          + "a rabbit could not be found.");
    }

    /**
     * Returns the non-static field that will be checked in the test cases. The field represents the
     * individual name of the {@code Rabbit}.
     *
     * @return the non-static field that will be checked in the test cases
     */
    private Field getNonStaticField() {
      return getStaticField(f -> {
          final var modifiers = f.getModifiers();
          return java.lang.reflect.Modifier.isPrivate(modifiers) &&
            !java.lang.reflect.Modifier.isStatic(modifiers) && f.getType().equals(Integer.TYPE);
        },
        "The private int field used to determine the name of the rabbit "
          + "could not be found.");
    }

    /**
     * Resets the counter after the executions of the specified statements.
     *
     * @param statements the statements that should be executed
     */
    private void resetCounter(final Runnable statements) {
      // Reset counter at the end
      final var counter = getStaticField();

      try {
        statements.run();
      } finally {
        TutorUtils.setContent(counter, 1);
      }
    }

    /**
     * Defines the JUnit tests cases related to the class header.
     */
    @Nested
    @DisplayName("Criterion: Class header")
    final class TestClassHeader {

      @Test
      @DisplayName("Criterion: Only modifiers public")
      void testModifiers() {
        TutorUtils.assertModifiers(getTestClass(), List.of(Modifier.PUBLIC),
          List.of(Modifier.STATIC, Modifier.ABSTRACT, Modifier.FINAL));
      }

      @Test
      @DisplayName("Criterion: Extend Leporidae")
      void testSuperClass() {
        assertInterfaceExtensions(CLASS_NAME_RABBIT, CLASS_NAME_LEPORIDAE);
      }
    }

    /**
     * Defines the JUnit tests cases related to the static int field.
     */
    @Nested
    @DisplayName("Criterion: Static field int - Name counter")
    final class TestFieldStatic {

      @Test
      @DisplayName("Criterion: Only modifiers private static")
      void testModifiers() {
        final var field = getStaticField();
        TutorUtils.assertModifiers(field,
          List.of(Modifier.PRIVATE, Modifier.STATIC),
          List.of(Modifier.FINAL));
      }

      @Test
      @DisplayName("Criterion: Initialization value is 1")
      void testValue() {
        final var field = getStaticField();
        Assertions.assertEquals(1, TutorUtils.getContent(field));
      }
    }

    /**
     * Defines the JUnit tests cases related to the int field.
     */
    @Nested
    @DisplayName("Criterion: Field int - Name of the individual")
    @TestInstance(Lifecycle.PER_CLASS)
    final class TestField {

      @Test
      @DisplayName("Criterion: Only modifiers private final")
      void testModifiers() {
        final var field = getNonStaticField();
        TutorUtils.assertModifiers(field, List.of(Modifier.PRIVATE, Modifier.FINAL),
          List.of(Modifier.STATIC));
      }
    }

    /**
     * Defines the JUnit tests cases related to the constructor.
     */
    @Nested
    @DisplayName("Criterion: Constructor")
    @TestInstance(Lifecycle.PER_CLASS)
    final class TestConstructor {

      @Test
      @DisplayName("Criterion: public")
      void testModifiers() {
        final var constructor = TutorUtils.getConstructor(getTestClass());
        TutorUtils.assertModifiers(constructor, List.of(Modifier.PUBLIC));
      }

      @Test
      @DisplayName("Criterion: Instantiation of objects increase static field")
      void testFieldStatic() {
        resetCounter(() -> {
          for (int i = 0; i < 5; i++) {
            new Rabbit();
            final var field = getStaticField();

            final var expected = i + 2;
            final var actual = TutorUtils.getContent(field);

            Assertions.assertEquals(expected, actual,
              String.format("Field %s should have a value of %s after %s instantiation, given: %s",
                field.getName(), expected, i + 1, actual));
          }
        });
      }

      @Test
      @DisplayName("Criterion: Instantiation of objects increase non-static field")
      void testField() {
        resetCounter(() -> {
          for (int i = 0; i < 5; i++) {
            final var instance = new Rabbit();
            final var field = getNonStaticField();

            final var expected = i + 1;
            final var actual = TutorUtils.getContent(field, instance);

            Assertions.assertEquals(expected, actual,
              String.format("Field %s should have a value of %s after %s instantiation, given: %s",
                field.getName(), expected, i + 1, actual));
          }
        });
      }

      @Test
      @DisplayName("Criterion: Method nameOfIndividual return value")
      void testMethodNameOfIndividual() {
        resetCounter(() -> {
          // Check fields
          for (int i = 0; i < 5; i++) {
            final var instance = new Rabbit();
            final var expected = String.valueOf(i + 1);
            final var actual = instance.nameOfIndividual();

            Assertions.assertEquals(expected, actual,
              String.format("Method %s should return the value of %s, given: %s",
                "nameOfIndividual", expected, actual));
          }
        });
      }
    }

    /**
     * Defines the JUnit tests cases related to the methods typeOfX where X should be replaced by
     * their respective name.
     */
    @Nested
    @DisplayName("Criterion: Method typeofX")
    class TestMethodsTypeOfX {

      /**
       * Tests if the method typeOfX where X is replaced by its respective name returns the correct
       * type hierarchy of the animal.
       *
       * @param name     the name of the method without the prefix
       * @param expected the expected value
       */
      private void assertTypeOfXReturnValue(final String name, final String expected) {
        resetCounter(() -> {
          final var clazz = getTestClass();
          final var constructor = TutorUtils.getConstructor(clazz);
          final var instance = TutorUtils.invokeConstructor(constructor);
          final var method = getMethodTypeOfX(name, false);
          final var actual = TutorUtils.invokeMethod(method, instance);

          Assertions.assertEquals(expected, actual,
            String.format("The method typeOf%s, should return %s, given %s.", name, expected,
              actual));
        });
      }

      @Test
      @DisplayName("Criterion: Method typeOfAnimal return value")
      void testTypeOfAnimal() {
        assertTypeOfXReturnValue(CLASS_NAME_ANIMAL, CLASS_NAME_VERTEBRATE);
      }

      @Test
      @DisplayName("Criterion: Method typeOfVertebrate return value")
      void testTypeOfVertebrate() {
        assertTypeOfXReturnValue(CLASS_NAME_VERTEBRATE, CLASS_NAME_MAMMAL);
      }

      @Test
      @DisplayName("Criterion: Method typeOfMammal return value")
      void testTypeOfMammal() {
        assertTypeOfXReturnValue(CLASS_NAME_MAMMAL, CLASS_NAME_PLACENTAL);
      }

      @Test
      @DisplayName("Criterion: Method typeOfPlacental return value")
      void testTypeOfPlacental() {
        assertTypeOfXReturnValue(CLASS_NAME_PLACENTAL, CLASS_NAME_LAGOMORPHA);
      }


      @Test
      @DisplayName("Criterion: Method typeOfLagomorpha return value")
      void testTypeOfLagomorph() {
        assertTypeOfXReturnValue(CLASS_NAME_LAGOMORPHA, CLASS_NAME_LEPORIDAE);
      }
    }
  }
}
