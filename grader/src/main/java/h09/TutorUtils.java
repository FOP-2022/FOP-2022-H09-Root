package h09;

import org.junit.jupiter.api.Assertions;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
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

  /* *********************************************************************
   *                          Reflective access                          *
   *                                                                     *
   *  Functions related to accessing properties of a class in a          *
   *  reflective way.                                                    *
   *                                                                     *
   **********************************************************************/

  /**
   * Retrieves the {@link Class} instance with the specified name. The retrieved class name must
   * exist in the specified package.
   * <p>
   * If the class could not be found, the assertions will fail.
   *
   * @param packageName the name of the package to retrieve the specified class
   * @param className   the name of the class to retrieve
   *
   * @return the retrieved {@link Class} instance with the specified name
   */
  public static Class<?> getClass(final String packageName, final String className) {
    final var name = packageName==null ? className:String.format("%s.%s", packageName, className);
    try {
      return Class.forName(name);
    } catch (ClassNotFoundException e) {
      return Assertions.fail(String.format("The class %s could not be found.", name), e);
    }
  }

  /**
   * Retrieves the {@link Field} with the specified name of the specified class.
   * <p>
   * If the field could not be found, the assertions will fail.
   *
   * @param clazz     the class to retrieve the field
   * @param fieldName the name of the field
   *
   * @return the retrieved {@link Field} with the specified name
   */
  public static Field getField(final Class<?> clazz, final String fieldName) {
    try {
      return clazz.getDeclaredField(fieldName);
    } catch (NoSuchFieldException e) {
      return Assertions.fail(
        String.format("The field %s could not be found in the class %s.",
          fieldName, clazz.getSimpleName()), e);
    }
  }

  /**
   * Retrieves the {@link Field} instance with the specified name of the specified object.
   * <p>
   * If the field could not be found, the assertions will fail.
   *
   * @param instance  the object instance to retrieve its field
   * @param fieldName the name of the field
   *
   * @return the retrieved {@link Field} instance with the specified name
   */
  public static Field getField(final Object instance, final String fieldName) {
    return getField(instance.getClass(), fieldName);
  }

  /**
   * Returns the accessed field content. If the field is static, the instance can be {@code null}.
   * <p>
   * If the content of the field could not be accessed, the assertions will fail.
   *
   * @param field    the field to access
   * @param instance the instance to retrieve its field
   *
   * @return the accessed field object
   */
  public static Object getContent(final Field field, final Object instance) {
    try {
      final var tmp = field.canAccess(instance);
      field.setAccessible(!tmp);
      var object = field.get(instance);
      field.setAccessible(tmp);
      return object;
    } catch (IllegalAccessException e) {
      return Assertions.fail(
        String.format("The field %s could not be accessed.", field.getName()), e
      );
    }
  }

  /**
   * Returns the accessed static field content.
   * <p>
   * If the content of the field could not be accessed, the assertions will fail.
   *
   * @param field the field to access
   *
   * @return the accessed field object
   */
  public static Object getContent(final Field field) {
    return getContent(field, null);
  }

  /**
   * Sets the accessed field content to the specified one. If the field is static, the instance can
   * be {@code null}.
   * <p>
   * If the content of the field could not be accessed, the assertions will fail.
   *
   * @param field    the field to access
   * @param instance the instance to retrieve its field
   * @param value    the new value of the field
   */
  public static void setContent(final Field field, final Object instance, final Object value) {
    try {
      final var tmp = field.canAccess(instance);
      field.setAccessible(!tmp);
      field.set(instance, value);
      field.setAccessible(tmp);
    } catch (IllegalAccessException e) {
      Assertions.fail(String.format("The field %s could not be accessed.", field.getName()));
    }
  }

  /**
   * Sets the accessed static field content to the specified one.
   * <p>
   * If the content of the field could not be accessed, the assertions will fail.
   *
   * @param field the field to access
   * @param value the new value of the field
   */
  public static void setContent(final Field field, final Object value) {
    setContent(field, null, value);
  }

  /**
   * Retrieves the {@link Constructor} instance with the specified parameters.
   * <p>
   * If the constructor could not be found, the assertions will fail.
   *
   * @param clazz      the class to retrieve its constructor
   * @param parameters the parameters of the constructor
   *
   * @return the retrieved {@link Constructor} instance with the specified parameters.
   */
  public static Constructor<?> getConstructor(final Class<?> clazz, final Class<?>... parameters) {
    try {
      return clazz.getDeclaredConstructor(parameters);
    } catch (NoSuchMethodException e) {
      return Assertions.fail(String.format(
        "The constructor for the class %s with the type parameters %s could not be found.",
        clazz.getSimpleName(), Arrays.toString(parameters)), e);
    }
  }

  /**
   * Returns an instance of the object that is constructed by invoking the specified constructor.
   * <p>
   * If the constructor could not be invoked, the assertions will fail.
   *
   * @param constructor the constructor that should be invoked
   * @param parameters  the parameters of the constructor
   *
   * @return an instance of the object that is constructed by invoking the specified constructor
   */
  public static Object invokeConstructor(final Constructor<?> constructor,
                                         final Object... parameters) {
    try {
      return constructor.newInstance(parameters);
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
      return Assertions.fail(String.format("The constructor of the class %s could not be invoked",
        constructor.getName()), e);
    }
  }

  /**
   * Retrieves the {@link Method} with the specified name a parameters of the specified object.
   * <p>
   * If the method could not be found, the assertions will fail.
   *
   * @param clazz      the class instance to retrieve its method
   * @param name       the name of the method
   * @param parameters the parameters class type of the class
   *
   * @return the retrieved {@link Field} instance with the specified name
   */
  public static Method getMethod(final Class<?> clazz, final String name,
                                 final Class<?>... parameters) {
    try {
      return clazz.getDeclaredMethod(name, parameters);
    } catch (NoSuchMethodException e) {
      return Assertions.fail(
        String.format(
          "The method %s with the parameters %s could not be found in the class %s.",
          name, Arrays.toString(parameters), clazz.getSimpleName()
        ),
        e
      );
    }
  }

  /**
   * Retrieves the {@link Method} with the specified name a parameters of the specified object.
   * <p>
   * If the method could not be found, the assertions will fail.
   *
   * @param instance   the object instance to retrieve its method
   * @param name       the name of the method
   * @param parameters the parameters class type of the class
   *
   * @return the retrieved {@link Field} instance with the specified name
   */
  public static Method getMethod(final Object instance, final String name,
                                 final Class<?>... parameters) {
    return getMethod(instance.getClass(), name, parameters);
  }

  /**
   * Invokes the specified method by the caller.
   *
   * <p>
   * If the method could not be accessed or invoked, the assertion will fail.
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
        String.format("The method %s could not be invoked.", method.toGenericString()),
        e
      );
    }
  }

  /* *********************************************************************
   *                       Presence of modifiers                         *
   *                                                                     *
   *  Functions related to checking the presence of modifiers.           *
   *                                                                     *
   **********************************************************************/

  /**
   * An enumerations of java modifiers which allows checking if a modifier is presence.
   */
  public enum Modifier {
    PUBLIC("public", java.lang.reflect.Modifier::isPublic),
    PROTECTED("protected", java.lang.reflect.Modifier::isProtected),
    PRIVATE("private", java.lang.reflect.Modifier::isPrivate),
    PACKAGEPRIVATE("package-private",
      Predicate.not(PUBLIC.presence.or(PROTECTED.presence.or(PRIVATE.presence)))),
    STATIC("static", java.lang.reflect.Modifier::isStatic),
    FINAL("final", java.lang.reflect.Modifier::isFinal),
    ABSTRACT("abstract", java.lang.reflect.Modifier::isAbstract),
    INTERFACE("interface", java.lang.reflect.Modifier::isInterface),
    ;

    /**
     * The value of the modifier that is used to indicate the modifier as string.
     */
    private final String value;
    /**
     * The predicate that is used to check if the modifier is presence
     */
    private final Predicate<Integer> presence;

    /**
     * Constructs a modifier with a value and a predicate that is used to indicate its presence.
     *
     * @param value    the value of the modifier that is used to indicate the modifier as string
     * @param presence the predicate that is used to check if the modifier is presence
     */
    Modifier(final String value, final Predicate<Integer> presence) {
      this.value = value;
      this.presence = presence;
    }

    /**
     * Returns the {@link #value} of the specified modifiers.
     *
     * @param modifiers the modifiers to extract the values.
     *
     * @return the {@link #value} of the specified modifiers
     */
    public static Set<String> values(final List<Modifier> modifiers) {
      final Set<String> values = new LinkedHashSet<>();
      if (modifiers==null) {
        return values;
      }
      for (final var modifier : modifiers) {
        values.add(modifier.value);
      }
      return values;
    }

    /**
     * Returns the value of the modifier that is used to indicate the modifier as string.
     *
     * @return the value of the modifier that is used to indicate the modifier as string
     */
    public String getValue() {
      return value;
    }

    /**
     * Returns the predicate that is used to check if the modifier is presence.
     *
     * @return the predicate that is used to check if the modifier is presence
     */
    public Predicate<Integer> checkPresence() {
      return presence;
    }
  }

  /**
   * Tests if the specified modifiers of an object only contains the specified modifiers and do not
   * contain no more.
   *
   * @param modifiers the modifier of an object to check
   * @param presence  the modifiers that should be presence
   * @param absence   the modifiers that should be absence
   * @param message   the message if the assertions fail
   */
  public static void assertModifiersByPredicate(final Integer modifiers,
                                                final List<Predicate<Integer>> presence,
                                                final List<Predicate<Integer>> absence,
                                                final String message) {
    if (presence!=null) {
      for (final var predicate : presence) {
        if (!predicate.test(modifiers)) {
          Assertions.fail(message);
          return;
        }
      }
    }
    if (absence!=null) {
      for (final var predicate : absence) {
        if (predicate.test(modifiers)) {
          Assertions.fail(message);
          return;
        }
      }
    }
  }

  /**
   * Tests if the specified modifiers of an object only contains the specified modifiers and do not
   * contain no more.
   *
   * @param modifiers the modifier of an object to check
   * @param presence  the modifiers that should be presence
   * @param absence   the modifiers that should be absence
   * @param message   the message if the assertions fail
   */
  private static void assertModifiers(final Integer modifiers,
                                      final List<Modifier> presence, final List<Modifier> absence,
                                      final String message) {
    assertModifiersByPredicate(modifiers,
      presence==null ? null:presence.stream().map(Modifier::checkPresence).collect(
        Collectors.toList()),
      absence==null ? null:absence.stream().map(Modifier::checkPresence).collect(
        Collectors.toList()), message);
  }

  /**
   * Tests if a {@link Member} contains only the specified modifiers and no more.
   *
   * @param member   the member object to check its modifiers presence
   * @param presence the modifiers that should be presence
   * @param absence  the modifiers that should be absence
   */
  public static void assertModifiers(final Member member, final List<Modifier> presence,
                                     final List<Modifier> absence) {
    assertModifiers(member.getModifiers(), presence, absence,
      String.format("The member %s should only contains the following modifiers: %s",
        member.getName(), Modifier.values(presence)));
  }

  /**
   * Tests if a {@link Member} contains only the specified modifiers and no more.
   *
   * @param member   the member object to check its modifiers presence
   * @param presence the modifiers that should be presence
   */
  public static void assertModifiers(final Member member, final List<Modifier> presence) {
    assertModifiers(member, presence, null);
  }

  /**
   * Tests if a {@link Class} contains only the specified modifiers and no more.
   *
   * @param clazz    the class object to check its modifiers presence
   * @param presence the modifiers that should be presence
   * @param absence  the modifiers that should be absence
   */
  public static void assertModifiers(final Class<?> clazz, final List<Modifier> presence,
                                     final List<Modifier> absence) {
    assertModifiers(clazz.getModifiers(), presence, absence,
      String.format("The class %s should only contains the following modifiers: %s",
        clazz.getName(), Modifier.values(presence)));
  }

  /**
   * Tests if a {@link Class} contains only the specified modifiers and no more.
   *
   * @param clazz    the class object to check its modifiers presence
   * @param presence the modifiers that should be presence
   */
  public static void assertModifiers(final Class<?> clazz, final List<Modifier> presence) {
    assertModifiers(clazz, presence, null);
  }

  /* *********************************************************************
   *                          Type parameters                            *
   *                                                                     *
   *  Functions related to type parameters.                              *
   *                                                                     *
   **********************************************************************/

  /**
   * Checks if the expected generic type is equal the actual one.
   *
   * @param expected    the expected class type
   * @param genericType the expected generic type
   * @param actual      the type to check
   */
  public static void assertGenericType(final Class<?> expected, final String genericType,
                                       final Type actual) {
    final var ex = String.format("%s<%s>", expected.getCanonicalName(), genericType);
    final var ac = actual.getTypeName();
    Assertions.assertEquals(ex, ac, String.format("Expected the type parameter %s, given %s.",
      ex, ac));
  }
}
