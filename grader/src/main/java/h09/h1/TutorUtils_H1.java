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
import java.util.Arrays;
import java.util.stream.Collectors;

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
}
