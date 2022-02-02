package h09.utils.rd;

/**
 * A parameter matcher based on {@link IdentifierMatcher}.
 *
 * @author Ruben Deisenroth
 * @see IdentifierMatcher
 */
public class ParameterMatcher extends IdentifierMatcher {

    /**
     * The expected parameter type.
     */
    public Class<?> parameterType;
    /**
     * The indicator whether to also match subtypes derived from {@link #parameterType}.
     */
    public boolean allowSubTypes;

    /**
     * Constructs and initializes an attribute matcher to match the specified attribute.
     *
     * @param identifierName the identifier name to match
     * @param similarity     the minimum required similarity
     * @param parameterType  the expected parameter type
     * @param allowSubTypes  the indicator whether to also match subtypes derived from {@link #parameterType}
     */
    public ParameterMatcher(String identifierName, double similarity, Class<?> parameterType, boolean allowSubTypes) {
        super(identifierName, similarity);
        this.parameterType = parameterType;
        this.allowSubTypes = allowSubTypes;
    }

    /**
     * Constructs and initializes an attribute matcher to match the specified attribute.
     *
     * @param identifierName the identifier name to match
     * @param similarity     the minimum required similarity
     * @param parameterType  the expected parameter type
     */
    public ParameterMatcher(String identifierName, double similarity, Class<?> parameterType) {
        super(identifierName, similarity);
        this.parameterType = parameterType;
    }

    /**
     * Constructs and initializes an attribute matcher to match the specified attribute.
     *
     * @param parameterType the expected parameter type
     */
    public ParameterMatcher(Class<?> parameterType) {
        this(null, 0, parameterType);
    }

    /**
     * Constructs and initializes an attribute matcher to match the specified attribute.
     *
     * @param parameterType the expected parameter type
     * @param allowSubTypes the indicator whether to also match subtypes derived from {@link #parameterType}
     */
    public ParameterMatcher(Class<?> parameterType, boolean allowSubTypes) {
        this(null, 0, parameterType, allowSubTypes);
    }
}
