package h09.utils;

/**
 * Specifies the predefined tutor message format using {@link String#format(String, Object...)}.
 *
 * @author Nhan Huynh, Darya Nikitina
 */
public enum TutorMessage {

    /* *********************************************************************
     *                             Constructor                             *
     **********************************************************************/

    CONSTRUCTOR_NOT_FOUND("The constructor of the class %s with parameters %s could not be found. Message: %s."),
    CONSTRUCTOR_NO_INVOKE("The constructor of the class %s with the parameters %s could not be invoked. Message: %s."),
    CONSTRUCTOR_PARAMETER_MISMATCH_SIZE("The constructor %s must have %s parameters, given %s."),

    /* *********************************************************************
     *                                 Class                               *
     **********************************************************************/

    CLASS_EXTENSION_MISMATCH("The class should extend %s, given %s."),
    CLASS_NOT_FOUND("The class %s could not be found."),
    CLASS_TYPE_PARAMETER_MISMATCH_SIZE("The class %s should contain %s type parameter, given %s."),

    /* *********************************************************************
     *                                 Field                               *
     **********************************************************************/

    FIELD_CONTENT_MISMATCH("Expected the field content %s, given %s."),
    FIELD_NO_ACCESS("The field %s in the class %s could not be accessed. Message %s."),
    FIELD_NOT_FOUND("The field %s could not be found in the class %s. Message %s."),

    /* *********************************************************************
     *                               Import                                *
     **********************************************************************/

    IMPORT_NOT_ALLOWED("The import %s is not allowed."),

    /* *********************************************************************
     *                              Interface                              *
     **********************************************************************/

    INTERFACE_EXTENSION_MISMATCH("The interface %s should extends %s interface(s), given %s."),
    INTERFACE_NO_EXTENSION("The interface %s should not extends any other interfaces."),

    /* *********************************************************************
     *                        Lambda expression                            *
     **********************************************************************/

    LAMBDA_MISMATCH("Expected lambda %s, given %s."),
    LAMBDAS_MISMATCH_SIZE("Expected at least %s lambdas, given %s."),
    LAMBDA_METHOD_REFERENCE_MISMATCH("Expected method reference %s, given %s."),

    /* *********************************************************************
     *                               Method                                *
     **********************************************************************/

    METHOD_NOT_FOUND("The method %s with the parameters %s could not be found in the class %s. Message: %s."),
    METHOD_NO_INVOKE("The method %s with the parameters %s in the class %s could not be invoked. Message: %s."),
    METHOD_PARAMETER_MISMATCH("The method %s expected the parameter types %s, given %s."),
    METHOD_PARAMETER_MISMATCH_SIZE("The method %s must have  %s parameters, given: %s."),

    /* *********************************************************************
     *                              Modifier                               *
     **********************************************************************/

    MODIFIERS_CLASS_MISMATCH("The class %s should contain the following modifier %s."),
    MODIFIERS_MEMBER_MISMATCH("The member %s should contain the following modifier %s."),

    /* *********************************************************************
     *                              Package                                *
     **********************************************************************/

    PACKAGE_NAME_ALTERNATIVE("The package name %s could not be found for the class %s. Using the alternative package name."),

    /* *********************************************************************
     *                                Type                                 *
     **********************************************************************/

    TYPE_MISMATCH("Expected the type %s, given %s."),

    /* *********************************************************************
     *                            Type parameter                           *
     **********************************************************************/

    TYPE_PARAMETER_MISMATCH("Expected the type parameter %s, given %s."),
    TYPE_PARAMETER_MISMATCH_CLASS("The class %s should contain the type parameter %s, given %s."),
    TYPE_PARAMETERS_MISMATCH_CLASS("The class %s should contain the type parameters %s, given %s."),
    TYPE_PARAMETERS_MISMATCH_SIZE("TExpected the %s type parameter(s), given %s."),

    /* *********************************************************************
     *                             Return type                             *
     **********************************************************************/

    RETURN_TYPE_MISMATCH("Expected the return type %s, given %s."),
    RETURN_VALUE_MISMATCH("Expected the return value %s, given %s."),

    /* *********************************************************************
     *                             Requirement                             *
     **********************************************************************/
    REQUIREMENT_FOREACH_LOOP("Only %s for each loop(s) allowed, given %s."),
    REQUIREMENT_FOREACH_NO_CALLEE("Only %s for each loop(s) allowed, unknown callee %s processed."),
    REQUIREMENT_INTERMEDIATE_ARRAY_MISMATCH("An intermediate storage should be required for the each operation. Expected "
        + "at least %s, given %s."),
    REQUIREMENT_NO_ARRAY("Expected %s arrays, given %s."),

    /* *********************************************************************
     *                             Source code                             *
     **********************************************************************/

    SOURCE_CODE_NO_ACCESS("The specified source code %s could not be accessed."),

    /* *********************************************************************
     *                                 H2.1                                *
     **********************************************************************/

    H2_1_FIELD_MISMATCH("Field %s should have a value of %s after %s instantiation, given: %s"),
    H2_1_INTERFACES_MISMATCH("Expected %s interfaces to be complete and correct, given %s. (Correct: %s)"),
    H2_1_METHOD_TYPE_OF_X_MISMATCH("The method typeOf%s, should return %s, given %s."),

    /* *********************************************************************
     *                                 H2.3                                *
     **********************************************************************/

    H2_3_TEST_ANIMAL_MISMATCH("Expected the following test animals %s, given %s."),
    H2_3_TEST_ANIMAL_SIZE_MISMATCH("Expected at least %s test animals, given %s.");

    /**
     * The message format of this message.
     *
     * @see String#format(String, Object...)
     */
    private final String format;

    /**
     * Constructs and initialized a tutor message using the specified format.
     *
     * @param format the message format which will be used to format a message
     */
    TutorMessage(final String format) {
        this.format = format;
    }

    /**
     * Formats this message using the specified arguments.
     *
     * @param args the arguments used for the message format
     *
     * @return the formatted message
     */
    public String format(final Object... args) {
        return String.format(format, args);
    }
}
