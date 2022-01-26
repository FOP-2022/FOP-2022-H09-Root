package h09.utils.rd;

/**
 * An identifier Matcher.
 *
 * @author Ruben Deisenroth
 */
public class IdentifierMatcher {

    /**
     * The name to match.
     */
    public String identifierName;
    /**
     * The package name.
     */
    public String packageName;
    /**
     * The minimum required similarity.
     */
    public double similarity;

    /**
     * Constructs and initializes an identifier matcher to match the specified identifier.
     *
     * @param identifierName the identifier to match
     * @param packageName    the package name
     * @param similarity     the minimum required similarity
     */
    public IdentifierMatcher(String identifierName, String packageName, double similarity) {
        this.identifierName = identifierName;
        this.packageName = packageName;
        this.similarity = similarity;
    }

    /**
     * Constructs and initializes an identifier matcher to match the specified identifier.
     *
     * @param identifierName the identifier to match
     * @param similarity     the minimum required similarity
     */
    public IdentifierMatcher(String identifierName, double similarity) {
        this(identifierName, null, similarity);
    }
}
