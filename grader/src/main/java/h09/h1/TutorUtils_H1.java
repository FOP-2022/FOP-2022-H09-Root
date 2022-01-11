package h09.h1;

import h09.utils.TutorConstants;
import h09.utils.TutorMessage;
import h09.utils.TutorUtils;
import h09.utils.spoon.ImportsClassProcessor;
import h09.utils.spoon.SpoonUtils;
import org.junit.jupiter.api.Assertions;
import org.sourcegrade.jagr.api.testing.TestCycle;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

/**
 * Defines the utility operations related to the JUnit test cases for the task H1.
 *
 * @author Nhan Huynh, Darya Nikitina
 */
final class TutorUtils_H1 {

    private TutorUtils_H1() {

    }

    public static void assertClassTypeParameters(Class<?> clazz) {
        final var typeVariables = clazz.getTypeParameters();

        // Check if type parameters exists
        Assertions.assertNotNull(typeVariables,
            TutorMessage.TYPE_PARAMETERS_MISMATCH_CLASS.format(
                clazz.getSimpleName(),
                TutorConstants.H1_TYPE_PARAMETERS, "none"));

        // Check number of type parameters
        Assertions.assertEquals(3, typeVariables.length,
            TutorMessage.TYPE_PARAMETER_MISMATCH_CLASS.format(clazz.getSimpleName(),
                TutorConstants.H1_TYPE_PARAMETERS, Arrays.toString(typeVariables)));

        // Check name of the type parameters
        final String[] types = TutorConstants.H1_TYPE_PARAMETERS.split(", ");
        for (int i = 0; i < types.length; i++) {
            final var expected = types[i];
            final var actual = typeVariables[i].getTypeName();
            Assertions.assertEquals(expected, actual,
                TutorMessage.TYPE_PARAMETERS_MISMATCH_CLASS.format(
                    clazz.getSimpleName(),
                    expected, actual));
        }
    }

    public static void assertConstructorParameterH1(final Constructor<?> constructor,
                                                    final Class<?> parameterClass) {
        final var parameters = constructor.getGenericParameterTypes();

        final var expectedLength = 1;
        final var actualLength = parameters.length;
        Assertions.assertEquals(expectedLength, actualLength,
            TutorMessage.CONSTRUCTOR_PARAMETER_MISMATCH_SIZE.format(constructor,
                expectedLength, actualLength));

        final var expectedType = TutorConstants.H1_TYPE_PARAMETERS;
        final var actualType = parameters[0];
        TutorUtils.assertGenericType(parameterClass, expectedType, actualType);
    }

    public static void assertImports(final TestCycle testCycle, final String source,
                                     final Class<?>... blacklist) {
        final var processor = SpoonUtils.process(testCycle, source, new ImportsClassProcessor());

        // Retrieve imports names
        final var names = Arrays.stream(blacklist)
            .map(Class::getCanonicalName)
            .collect(Collectors.toList());

        // Check imports
        for (final var key : processor.getImports().keySet()) {
            Assertions.assertFalse(names.contains(key),
                TutorMessage.IMPORT_NOT_ALLOWED.format(key));
        }
    }

    public static void assertParametersH1_2(final Method method) {
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

    public static void assertReturnTypeH1_2(final Method method) {
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

    public static void assertConstructorParameterTypesH1_1(final Constructor<?> constructor,
                                                           final boolean combine) {
        final List<Entry<Class<?>, String>> parameters = new ArrayList<>(
            List.of(
                new SimpleEntry<>(
                    TutorConstants.H1_1_FIELD_TYPE_1, TutorConstants.H1_1_FIELD_TYPE_PARAMETER_1
                ),
                new SimpleEntry<>(
                    TutorConstants.H1_1_FIELD_TYPE_2, TutorConstants.H1_1_FIELD_TYPE_PARAMETER_2
                ),
                new SimpleEntry<>(
                    TutorConstants.H1_1_FIELD_TYPE_3, TutorConstants.H1_1_FIELD_TYPE_PARAMETER_3
                ),
                new SimpleEntry<>(
                    TutorConstants.H1_1_FIELD_TYPE_4, TutorConstants.H1_1_FIELD_TYPE_PARAMETER_4
                )
            )
        );
        if (combine) {
            parameters.add(
                new SimpleEntry<>(
                    TutorConstants.H1_4_FIELD_TYPE, TutorConstants.H1_4_FIELD_TYPE_PARAMETER
                )
            );
        }
        TutorUtils.assertConstructorParameters(constructor, parameters);
    }

    public static void assertConstructorFieldsH1_1(final Constructor<?> constructor,
                                                   final boolean combine) {
        final var expectedField1 = TutorConstants.H1_1_FIELD_EXAMPLE_3_1;
        final var expectedField2 = TutorConstants.H1_1_FIELD_EXAMPLE_3_2;
        final var expectedField3 = TutorConstants.H1_1_FIELD_EXAMPLE_3_3;
        final var expectedField4 = TutorConstants.H1_1_FIELD_EXAMPLE_3_4;
        final var expectedField5 = !combine ? null : TutorConstants.H1_1_FIELD_EXAMPLE_3_5;

        final var instance = combine ?
            TutorUtils.invokeConstructor(
                constructor, expectedField1, expectedField2, expectedField3, expectedField4, expectedField5
            )
            :
                TutorUtils.invokeConstructor(
                    constructor, expectedField1, expectedField2, expectedField3, expectedField4
                );

        // Check if fields are initialized
        final var actualField1 = TutorUtils.assertField(instance, TutorConstants.H1_1_FIELD_NAME_1);
        final var actualField2 = TutorUtils.assertField(instance, TutorConstants.H1_1_FIELD_NAME_2);
        final var actualField3 = TutorUtils.assertField(instance, TutorConstants.H1_1_FIELD_NAME_3);
        final var actualField4 = TutorUtils.assertField(instance, TutorConstants.H1_1_FIELD_NAME_4);
        final var actualField5 = !combine ? null : TutorUtils.assertField(instance,
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

        if (combine) {
            Assertions.assertEquals(
                expectedField5, TutorUtils.getFieldContent(actualField5, instance),
                TutorMessage.FIELD_CONTENT_MISMATCH.format(expectedField5, actualField4)
            );
        }
    }
}
