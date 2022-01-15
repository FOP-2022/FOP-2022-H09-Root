package h09.utils;

import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

/**
 * Represents Java modifiers that can be checked on objects to see if they have the specified
 * modifiers.
 *
 * @author Nhan Huynh, Darya Nikitina
 */
public final class Modifier {

    /**
     * The modifier {@code public} which can be applied on classes, fields, constructors and methods.
     *
     * @see Class
     * @see Member
     * @see Method
     */
    public static final Modifier PUBLIC = new Modifier(
        "public",
        getPredicateClassAndMember(java.lang.reflect.Modifier::isPublic),
        Class.class, Member.class, Method.class
    );
    /**
     * The modifier {@code protected} which can be applied on classes, fields, constructors and
     * methods.
     *
     * @see Class
     * @see Member
     * @see Method
     */
    public static final Modifier PROTECTED = new Modifier(
        "protected",
        getPredicateClassAndMember(java.lang.reflect.Modifier::isProtected),
        Class.class, Member.class, Method.class
    );
    /**
     * The modifier {@code private} which can be applied on classes, fields, constructors and methods.
     *
     * @see Class
     * @see Member
     * @see Method
     */
    public static final Modifier PRIVATE = new Modifier(
        "private",
        getPredicateClassAndMember(java.lang.reflect.Modifier::isPrivate),
        Class.class, Member.class, Method.class
    );
    /**
     * The modifier {@code package-private} which can be applied on classes, fields, constructors and
     * methods.
     *
     * @see Class
     * @see Member
     * @see Method
     */
    public static final Modifier PACKAGE_PRIVATE = new Modifier(
        "package-private",
        PUBLIC.nand(PROTECTED, PRIVATE).predicate,
        Class.class, Member.class, Method.class
    );
    /**
     * The modifier {@code static} which can be applied on classes, fields, constructors and methods.
     *
     * @see Class
     * @see Member
     * @see Method
     */
    public static final Modifier STATIC = new Modifier(
        "static",
        getPredicateClassAndMember(java.lang.reflect.Modifier::isStatic),
        Class.class, Member.class, Method.class
    );
    /**
     * The modifier {@code final} which can be applied on classes, fields, constructors and methods.
     *
     * @see Class
     * @see Member
     * @see Method
     */
    public static final Modifier FINAL = new Modifier(
        "final",
        getPredicateClassAndMember(java.lang.reflect.Modifier::isFinal),
        Class.class, Member.class, Method.class
    );

    /**
     * The modifier {@code abstract} which can be applied on classes and methods.
     *
     * @see Class
     * @see Member
     * @see Method
     */
    public static final Modifier ABSTRACT = new Modifier(
        "abstract",
        getPredicateClassAndMember(java.lang.reflect.Modifier::isAbstract),
        Class.class, Member.class, Method.class
    );

    /**
     * The modifier {@code interface} which can be applied on classes (interfaces).
     *
     * @see Class
     */
    public static final Modifier INTERFACE = new Modifier(
        "private",
        getPredicateClassAndMember(java.lang.reflect.Modifier::isInterface),
        Class.class
    );

    /**
     * The modifier {@code default} which can be applied on methods.
     *
     * @see Method
     */
    public static final Modifier DEFAULT = new Modifier(
        "default",
        object -> object instanceof Method && ((Method) object).isDefault(),
        Method.class
    );

    /**
     * The name of the modifier(s).
     */
    private final String name;

    /**
     * The types on which the modifier(s)  can be applied.
     */
    private final Set<Class<?>> types;

    /**
     * The predicate which is used to check, if the specified object contains these modifiers.
     */
    private final Predicate<Object> predicate;

    /**
     * Constructs and initialized a modifier with the specified name and predicate, which is used
     * to determine if an object contains the specified modifiers.
     *
     * @param name      the name of the modifier(s)
     * @param predicate the predicate which is used to check of the presence of the modifier(s)
     * @param types     the types the modifier(s) can be applied
     */
    private Modifier(final String name, final Predicate<Object> predicate, final Class<?>... types) {
        this.name = name;
        this.types = new LinkedHashSet<>(Arrays.asList(types));
        this.predicate = predicate;
    }

    /**
     * Constructs and initialized a modifier with the specified name and predicate, which is used
     * to determine if an object contains the specified modifiers.
     *
     * @param name      the name of the modifier(s)
     * @param predicate the predicate which is used to check of the presence of the modifier(s)
     * @param types     the types the modifier(s) can be applied
     */
    private Modifier(final String name, final Predicate<Object> predicate, final Set<Class<?>> types) {
        this.name = name;
        this.types = new LinkedHashSet<>(types);
        this.predicate = predicate;
    }

    /**
     * Returns a predicate for a {@link Class} instance which used the specified predicate to
     * determine the presence of modifier(s).
     *
     * @param predicate the predicate which will be wrapped for a {@link Class} instance
     *
     * @return a predicate for a {@link Class} instance
     */
    private static Predicate<Object> getPredicateClass(final IntPredicate predicate) {
        return object -> object instanceof Class<?> && predicate.test(((Class<?>) object).getModifiers());
    }

    /**
     * Returns a predicate for a {@link Member} instance which used the specified predicate to
     * determine the presence of modifier(s).
     *
     * @param predicate the predicate which will be wrapped for a {@link Member} instance
     *
     * @return a predicate for a {@link Member} instance
     */
    private static Predicate<Object> getPredicateMember(final IntPredicate predicate) {
        return object -> object instanceof Member && predicate.test(((Member) object).getModifiers());
    }

    /**
     * Returns a predicate for a {@link Class} or {@link Member} instance which used the specified
     * predicate to
     * determine the presence of modifier(s).
     *
     * @param predicate the predicate which will be wrapped for a {@link Class} or {@link Member}
     *                  instance
     *
     * @return a predicate for a {@link Class} or {@link Member} instance
     */
    private static Predicate<Object> getPredicateClassAndMember(final IntPredicate predicate) {
        return getPredicateClass(predicate).or(getPredicateMember(predicate));
    }

    /**
     * Negates this modifier.
     *
     * @return the negated modifier
     */
    public Modifier negate() {
        return new Modifier(String.format("not(%s)", name), predicate.negate(), types);
    }

    /**
     * Uses the specified binary operation on each modifier which will reduce the modifiers to a
     * single modifier.
     *
     * @param op        the binary operation which will be used to combine the modifiers
     * @param format    the naming format of the resulting modifier
     * @param modifiers the modifiers which should be combined with this modifier
     *
     * @return the combined modifiers with this modifier using the specified binary operation
     */
    private Modifier op(final BiFunction<Predicate<Object>, Predicate<Object>, Predicate<Object>> op,
                        final String format,
                        final Modifier... modifiers) {
        var ops = predicate;
        final List<String> names = new ArrayList<>(modifiers.length);

        for (final var other : modifiers) {
            if (other.types.containsAll(types)) {
                names.add(other.name);
                ops = op.apply(ops, other.predicate);
            }
        }
        return new Modifier(
            String.format(format, name, String.join(", ", names)),
            ops,
            types
        );
    }

    /**
     * Combines the modifiers where the predicate will be combined using the binary operation
     * {@link Predicate#and(Predicate)}.
     *
     * @param modifiers the modifiers which should be combined with this modifier
     *
     * @return the combined modifiers using the logical AND operation
     */
    public Modifier and(final Modifier... modifiers) {
        return op(Predicate::and, "and(%s, %s)", modifiers);
    }

    /**
     * Combines the modifiers where the predicate will be combined using the binary operation
     * {@link Predicate#and(Predicate)} and negates the result of it.
     *
     * @param modifiers the modifiers which should be combined with this modifier
     *
     * @return the combined modifiers using the logical AND operation and negation afterwards
     */
    public Modifier nand(final Modifier... modifiers) {
        return and(modifiers).negate();
    }

    /**
     * Combines the modifiers where the predicate will be combined using the binary operation.
     * {@link Predicate#or(Predicate)}
     *
     * @param modifiers the modifiers which should be combined with this modifier
     *
     * @return the combined modifiers using the logical OR operation
     */
    public Modifier or(final Modifier... modifiers) {
        return op(Predicate::and, "or(%s, %s)", modifiers);
    }

    /**
     * Combines the modifiers where the predicate will be combined using the binary operation
     * {@link Predicate#or(Predicate)} and negates the result of it.
     *
     * @param modifiers the modifiers which should be combined with this modifier
     *
     * @return the combined modifiers using the logical OR operation and negation afterwards
     */
    public Modifier nor(final Modifier... modifiers) {
        return or(modifiers).negate();
    }

    /**
     * Tests the presence of the object modifiers.
     *
     * @param object the object for whose presence modifiers are to be tested
     *
     * @return {@code true} if the specified object contains these  modifiers
     */
    public boolean test(final Object object) {
        return predicate.test(object);
    }

    /**
     * Returns the name of the  modifier(s).
     *
     * @return the name of the  modifier(s
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the types on which these modifiers can be used.
     *
     * @return the types on which these modifiers can be used
     */
    public Set<Class<?>> getTypes() {
        return types;
    }

    @Override
    public String toString() {
        return name;
    }
}
