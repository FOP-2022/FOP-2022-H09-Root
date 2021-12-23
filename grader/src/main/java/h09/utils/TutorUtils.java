package h09.utils;

import org.junit.jupiter.api.Assertions;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

/**
 * A utility class used for JUnit tests which provides reflective access to some properties and
 * assertions.
 *
 * @author Nhan Huynh, Darya Nikitina
 */
public final class TutorUtils {

  /**
   * Don't let anyone instantiate this class.
   */
  private TutorUtils() {
  }

  /**
   * Contains cached class instances for faster access.
   */
  private static final Map<String, Class<?>> CACHE_CLASSES = new HashMap<>();

  /* *********************************************************************
   *                               Class                                 *
   **********************************************************************/

  /**
   * Checks if the specified class exists.
   *
   * @param packageName the name of the package where the class belong
   * @param className   the name of the class
   *
   * @return the specified class instance
   */
  public static Class<?> assertClass(final String packageName, final String className) {
    final var name = packageName==null ? className:String.format("%s.%s", packageName, className);
    if (CACHE_CLASSES.containsKey(name)) {
      return CACHE_CLASSES.get(name);
    }
    try {
      final var clazz = Class.forName(name);
      CACHE_CLASSES.put(name, clazz);
      return clazz;
    } catch (ClassNotFoundException e) {
      return Assertions.fail(TutorMessage.CLASS_NOT_FOUND.format(name), e);
    }
  }

  /**
   * Checks if the specified nested class exists.
   *
   * @param packageName     the name of the package where the class belong
   * @param className       the name of the class
   * @param nestedClassName the name of the nested class
   *
   * @return the specified nested class instance
   */
  public static Class<?> assertNestedClass(final String packageName, final String className,
                                           final String nestedClassName) {
    final var clazz = assertClass(packageName, className);
    final var classes = clazz.getDeclaredClasses();
    for (final var c : classes) {
      if (c.getSimpleName().equals(nestedClassName)) {
        return c;
      }
    }
    final var message = String.format("%s.%s", clazz.getCanonicalName(), nestedClassName);
    return Assertions.fail(TutorMessage.CLASS_NOT_FOUND.format(message));
  }

  /* *********************************************************************
   *                               Field                                 *
   **********************************************************************/

  /**
   * Checks if the specified field exists.
   *
   * @param clazz     the class instance to retrieve the field
   * @param fieldName the name of the field to retrieve
   *
   * @return the specified class instance
   */
  public static Field assertField(final Class<?> clazz, final String fieldName) {
    try {
      return clazz.getDeclaredField(fieldName);
    } catch (NoSuchFieldException e) {
      return Assertions.fail(
        TutorMessage.FIELD_NOT_FOUND.format(fieldName, clazz.getSimpleName()), e
      );
    }
  }

  /**
   * Checks if the specified field exists.
   *
   * @param instance  the object instance to retrieve the field
   * @param fieldName the name of the field to retrieve
   *
   * @return the specified class instance
   */
  public static Field assertField(final Object instance, final String fieldName) {
    return assertField(instance.getClass(), fieldName);
  }

  /**
   * Returns the accessed field content. If the field is static, the instance can be {@code null}.
   *
   * @param field    the field to access
   * @param instance the instance to retrieve its field
   *
   * @return the accessed field object
   */
  public static Object getFieldContent(final Field field, final Object instance) {
    try {
      final var tmp = field.canAccess(instance);
      field.setAccessible(!tmp);
      var object = field.get(instance);
      field.setAccessible(tmp);
      return object;
    } catch (IllegalAccessException e) {
      return Assertions.fail(
        TutorMessage.FIELD_NO_ACCESS.format(field.getName(), field.getDeclaringClass()), e
      );
    }
  }

  /**
   * Returns the accessed static field content.
   *
   * @param field the field to access
   *
   * @return the accessed field object
   */
  public static Object getFieldContent(final Field field) {
    return getFieldContent(field, null);
  }

  /**
   * Sets the accessed field content to the specified one. If the field is static, the instance can
   * be {@code null}.
   *
   * @param field    the field to access
   * @param instance the instance to retrieve its field
   * @param value    the new value of the field
   */
  public static void setFieldContent(final Field field, final Object instance, final Object value) {
    try {
      final var tmp = field.canAccess(instance);
      field.setAccessible(!tmp);
      field.set(instance, value);
      field.setAccessible(tmp);
    } catch (IllegalAccessException e) {
      Assertions.fail(
        TutorMessage.FIELD_NO_ACCESS.format(
          field.getName(), field.getDeclaringClass()
        ), e
      );
    }
  }

  /**
   * Sets the accessed static field content to the specified one.
   *
   * @param field the field to access
   * @param value the new value of the field
   */
  public static void setFieldContent(final Field field, final Object value) {
    setFieldContent(field, null, value);
  }

  /* *********************************************************************
   *                            Constructor                              *
   **********************************************************************/

  /**
   * Checks if the specified constructor exists.
   *
   * @param clazz      the class where the constructor belong
   * @param parameters the parameters of the constructor
   *
   * @return the specified constructor instance
   */
  public static Constructor<?> assertConstructor(final Class<?> clazz,
                                                 final Class<?>... parameters) {
    try {
      return clazz.getDeclaredConstructor(parameters);
    } catch (NoSuchMethodException e) {
      return Assertions.fail(
        TutorMessage.CONSTRUCTOR_NOT_FOUND.format(
          clazz.getSimpleName(),
          Arrays.stream(parameters).map(Class::getSimpleName).collect(Collectors.joining(", "))
        ), e
      );
    }
  }

  /**
   * Returns an instance of the object that is constructed by invoking the specified constructor.
   *
   * @param constructor the constructor that should be invoked
   * @param parameters  the parameters of the constructor
   *
   * @return an instance of the object that is constructed by invoking the specified constructor
   */
  public static Object invokeConstructor(final Constructor<?> constructor,
                                         final Object... parameters) {
    try {
      final var tmp = constructor.canAccess(null);
      constructor.setAccessible(!tmp);
      final var instance = constructor.newInstance(parameters);
      constructor.setAccessible(tmp);
      return instance;
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
      return Assertions.fail(
        TutorMessage.CONSTRUCTOR_NO_INVOKE.format(
          constructor.getName(),
          Arrays.stream(parameters)
            .map(Object::getClass)
            .map(Class::getSimpleName)
            .collect(Collectors.joining(", "))
        ), e
      );
    }
  }

  /**
   * Checks if the constructor parameters match with the expected parameter types.
   *
   * @param actual   the actual constructor with its parameter
   * @param expected the expected constructor parameters
   */
  public static void assertConstructorParameters(final Constructor<?> actual,
                                                 final List<Entry<Class<?>, String>> expected) {
    // Check number of parameters
    final var types = actual.getGenericParameterTypes();
    final var expectedLength = expected.size();
    final var actualLength = types.length;
    Assertions.assertEquals(expectedLength, actualLength,
      TutorMessage.CONSTRUCTOR_PARAMETER_MISMATCH_SIZE.format(
        actual.getName(), expectedLength, actualLength
      )
    );

    final Map<Integer, String> typesObject = new HashMap<>();

    // Wildcards and restricted type parameters
    for (int i = 0; i < expected.size(); i++) {
      final var entry = expected.get(i);
      final var key = entry.getKey();
      final var value = entry.getValue();
      if (key==Object.class) {
        typesObject.put(i, value);
      } else {
        TutorUtils.assertGenericType(key, value, types[i]);
      }
    }

    // Type parameters
    for (final Entry<Integer, String> entry : typesObject.entrySet()) {
      final var actualType = types[entry.getKey()].getTypeName();
      final var expectedType = entry.getValue();
      Assertions.assertEquals(expectedType, actualType,
        TutorMessage.TYPE_PARAMETER_MISMATCH.format(expectedType, actualType)
      );
    }
  }

  /* *********************************************************************
   *                               Method                                *
   **********************************************************************/

  /**
   * Checks if the method exists
   *
   * @param clazz      the class instance to retrieve its method
   * @param name       the name of the method
   * @param parameters the parameters class type of the class
   *
   * @return the specified method
   */
  public static Method assertMethod(final Class<?> clazz, final String name,
                                    final Class<?>... parameters) {
    try {
      return clazz.getDeclaredMethod(name, parameters);
    } catch (NoSuchMethodException e) {
      return Assertions.fail(
        TutorMessage.METHOD_NOT_FOUND.format(
          name,
          parameters==null ? "(no parameters)":
            Arrays.stream(parameters)
              .map(Object::getClass)
              .map(Class::getSimpleName)
              .collect(Collectors.joining(", "))
          , clazz.getSimpleName()
        ), e
      );
    }
  }

  /**
   * Checks if the method exists
   *
   * @param instance   the object instance to retrieve its method
   * @param name       the name of the method
   * @param parameters the parameters class type of the class
   *
   * @return the specified method
   */
  public static Method assertMethod(final Object instance, final String name,
                                    final Class<?>... parameters) {
    return assertMethod(instance.getClass(), name, parameters);
  }

  /**
   * Invokes the specified method by the caller.
   *
   * @param method     the method to invoke
   * @param caller     the caller that invokes the method
   * @param parameters the parameters of the method to invoke.
   *
   * @return the return value of the invoked method
   */
  public static Object invokeMethod(final Method method, final Object caller,
                                    final Object... parameters) {
    try {
      return method.invoke(caller, parameters);
    } catch (IllegalAccessException | InvocationTargetException e) {
      return Assertions.fail(
        TutorMessage.METHOD_NO_INVOKE.format(
          method.getName(),
          Arrays.stream(parameters)
            .map(Object::getClass)
            .map(Class::getSimpleName)
            .collect(Collectors.joining(", ")),
          method.toGenericString()
        ), e
      );
    }
  }

  /* *********************************************************************
   *                              Modifier                               *
   **********************************************************************/

  /**
   * Tests if the specified modifiers of an object only contains the specified modifiers and do not
   * contain no more.
   *
   * @param expected      the expected modifiers of an object to check
   * @param actual        the actual object with its modifiers
   * @param name          the name of the object used for the message format
   * @param messageFormat the message if the assertions fail
   */
  private static void assertModifiers(final Modifier expected, final Object actual, final String name,
                                      final String messageFormat) {
    if (!expected.test(actual)) {
      Assertions.fail(String.format(messageFormat, name, expected.getName()));
    }
  }

  /**
   * Tests if the specified modifiers of an object only contains the specified modifiers and do not
   * contain no more.
   *
   * @param expected the expected modifiers of an object to check
   * @param actual   the actual object with its modifiers
   */
  public static void assertModifiers(final Modifier expected, final Class<?> actual) {
    assertModifiers(expected, actual, actual.getSimpleName(),
      TutorMessage.MODIFIERS_CLASS_MISMATCH.format(actual.getName(), expected));
  }

  /**
   * Tests if the specified modifiers of an object only contains the specified modifiers and do not
   * contain no more.
   *
   * @param expected the expected modifiers of an object to check
   * @param actual   the actual object with its modifiers
   */
  public static void assertModifiers(final Modifier expected, final Member actual) {
    assertModifiers(expected, actual, actual.getName(),
      TutorMessage.MODIFIERS_MEMBER_MISMATCH.format(actual.getName(), expected));
  }

  /* *********************************************************************
   *                              Generic                                *
   **********************************************************************/

  /**
   * Checks if the expected generic type is equal the actual one.
   *
   * @param expected    the expected class type
   * @param genericType the expected generic type
   * @param actual      the type to check
   */
  public static void assertGenericType(final Class<?> expectedClass,
                                       final String expectedGenericType,
                                       final Type actual) {
    final var ex = String.format("%s<%s>", expectedClass.getCanonicalName(), expectedGenericType);
    final var ac = actual.getTypeName();
    Assertions.assertEquals(ex, ac,
      TutorMessage.TYPE_PARAMETER_MISMATCH.format(ex, ac));
  }
}
