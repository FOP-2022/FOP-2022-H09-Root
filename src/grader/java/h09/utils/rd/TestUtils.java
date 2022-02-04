package h09.utils.rd;

import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;
import org.sourcegrade.jagr.api.testing.extension.TestCycleResolver;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * A utility class used for JUnit tests which provides reflective access to some properties and
 * assertions.
 *
 * @author Ruben Deisenroth
 */
public class TestUtils {

    /**
     * Don't let anyone instantiate this class.
     */
    private TestUtils() {
    }

    public static final int BRIDGE = 0x00000040;
    public static final int VARARGS = 0x00000080;
    public static final int SYNTHETIC = 0x00001000;
    public static final int ANNOTATION = 0x00002000;
    public static final int ENUM = 0x00004000;
    public static final int MANDATED = 0x00008000;

    /**
     * Tests whether the modifiers are correct.
     *
     * @param expected the expected modifier count
     * @param actual   the actual modifier count
     * @param name     the name of the field to be checked
     */
    public static void assertModifier(int expected, int actual, String name) {
        if (expected < 0) {
            return;
        }
        assertEquals(expected, actual, String.format("Falsche Modifiers für %s! Gefordert: %s Erhalten: %s", name,
            Modifier.toString(expected), Modifier.toString(actual)));
    }

    /**
     * Tests whether the modifiers of a class are correct.
     *
     * @param expected the expected modifier count
     * @param clazz    the class to be checked
     */
    public static void assertModifier(int expected, Class<?> clazz) {
        assertModifier(expected, clazz.getModifiers(), "Klasse " + clazz.getName());
    }

    /**
     * Tests whether the modifiers of a method are correct.
     *
     * @param expected the expected modifier count
     * @param method   the method to be checked
     */
    public static void assertModifier(int expected, Method method) {
        assertModifier(expected, method.getModifiers(),
            "Methode " + method.getDeclaringClass() + "." + method.getName());
    }

    /**
     * Tests whether the modifiers of a constructor are correct.
     *
     * @param expected    the expected modifier count
     * @param constructor the constructor to be checked
     */
    public static void assertModifier(int expected, Constructor<?> constructor) {
        assertModifier(expected, constructor.getModifiers(),
            "Konstruktor " + constructor.getDeclaringClass() + "." + constructor.getName());
    }

    /**
     * Tests whether the modifiers of a field are correct.
     *
     * @param expected the expected modifier count
     * @param attribut the field to be checked
     */
    public static void assertModifier(int expected, Field attribut) {
        assertModifier(expected, attribut.getModifiers(),
            "Attribut " + attribut.getDeclaringClass() + "." + attribut.getName());
    }

    /**
     * Calculates the similarity (a number within 0 and 1) between two strings.
     *
     * @param s1 the first string used for the calculation of the similarity
     * @param s2 the second string used for the calculation  of the similarity
     *
     * @return the similarity
     */
    public static double similarity(String s1, String s2) {
        String longer = s1;
        String shorter = s2;
        if (s1.length() < s2.length()) {
            longer = s2;
            shorter = s1;
        }
        int longerLength = longer.length();
        if (longerLength == 0) {
            return 1.0;
            /* both strings are zero length */
        }
        /*
         * // If you have Apache Commons Text, you can use it to calculate the edit
         * distance: LevenshteinDistance levenshteinDistance = new
         * LevenshteinDistance(); return (longerLength -
         * levenshteinDistance.apply(longer, shorter)) / (double) longerLength;
         */
        return (longerLength - editDistance(longer, shorter)) / (double) longerLength;
    }

    /**
     * Calculates the similarity (a number within 0 and 1) between two strings.
     *
     * @param s1 the first string used for the calculation of the similarity
     * @param s2 the second string used for the calculation  of the similarity
     *
     * @return the calculated similarity (a number within 0 and 1) between two strings.
     *
     * @see <a href="http://rosettacode.org/wiki/Levenshtein_distance#Java">Levenshtein distance -
     * Java</a>
     */
    public static int editDistance(String s1, String s2) {
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();

        int[] costs = new int[s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            int lastValue = i;
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0) {
                    costs[j] = j;
                } else {
                    if (j > 0) {
                        int newValue = costs[j - 1];
                        if (s1.charAt(i - 1) != s2.charAt(j - 1)) {
                            newValue = Math.min(Math.min(newValue, lastValue), costs[j]) + 1;
                        }
                        costs[j - 1] = lastValue;
                        lastValue = newValue;
                    }
                }
            }
            if (i > 0) {
                costs[s2.length()] = lastValue;
            }
        }
        return costs[s2.length()];
    }

    /**
     * Scans all classes accessible from the context class loader which belong to the given package
     * and subpackages.
     *
     * @param packageName The base package name
     *
     * @return the found classes
     *
     * @throws ClassNotFoundException if the class(es) could not be found
     * @throws IOException            if an I/O Exception occurs
     */
    public static Class<?>[] getClasses(String packageName) throws ClassNotFoundException, IOException {
        var cycle = TestCycleResolver.getTestCycle();
        if (cycle != null) {
            // Autograder Run
            return cycle.getSubmission().getClassNames().stream()
                .map(x -> assertDoesNotThrow(() -> Class.forName(x))).toArray(Class<?>[]::new);
        } else {
            // Regular Junit Run
            final ClassLoader loader = Thread.currentThread().getContextClassLoader();
            return ClassPath.from(loader).getTopLevelClasses(packageName).stream().map(ClassInfo::load).toArray(Class<?>[]::new);
        }
    }

    /**
     * Returns {@code true} if {@link TestCycleResolver#getTestCycle} does not return {@code null}.
     *
     * @return {@code true} if {@link TestCycleResolver#getTestCycle} does not return {@code null}
     */
    public static boolean isAutograderRun() {
        return TestCycleResolver.getTestCycle() != null;
    }
}
