package h09;

import h09.h1.TutorTest_H1_1;
import h09.h1.TutorTest_H1_2;
import h09.h1.TutorTest_H1_3;
import h09.h1.TutorTest_H1_4;
import h09.h1.TutorTest_H1_4.TestH1ExtraField.TestConstructor.Test4Args;
import h09.h1.TutorTest_H1_4.TestH1ExtraField.TestConstructor.Test5Args;
import h09.h1.TutorTest_H1_5;
import h09.h1.TutorTest_H1_6;
import h09.h2.TutorTest_H2_1;
import h09.h2.TutorTest_H2_2;
import h09.h2.TutorTest_H2_3;
import org.sourcegrade.jagr.api.rubric.Criterion;
import org.sourcegrade.jagr.api.rubric.Grader;
import org.sourcegrade.jagr.api.rubric.JUnitTestRef;
import org.sourcegrade.jagr.api.rubric.Rubric;
import org.sourcegrade.jagr.api.rubric.RubricForSubmission;
import org.sourcegrade.jagr.api.rubric.RubricProvider;
import org.sourcegrade.jagr.api.testing.TestCycle;

/**
 * Specifies the criteria of the rubric.
 *
 * @author Nhan Huynh, Darya Nikitina
 */
@RubricForSubmission("h09")
public final class H09_RubricProvider implements RubricProvider {

    /* *********************************************************************
     *                                H1.1                                 *
     **********************************************************************/

    public static final Criterion H1_1_1 = Criterion.builder()
        .shortDescription("Die Klassensignatur ist vollständig und korrekt.")
        .maxPoints(1)
        .minPoints(0)
        .grader(
            Grader.testAwareBuilder()
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_1.TestClassHeader.class.getMethod("testModifiers")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_1.TestClassHeader.class.getMethod("testExtension")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_1.TestClassHeader.class.getMethod("testTypeParameters")))
                .pointsPassedMax()
                .pointsFailedMin()
                .build())
        .build();

    public static final Criterion H1_1_2 = Criterion.builder()
        .shortDescription("Die Objektattribute existieren und haben die korrekten Modifiers.")
        .maxPoints(1)
        .minPoints(0)
        .grader(
            Grader.testAwareBuilder()
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_1.TestField1.class.getMethod("testModifiers")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_1.TestField2.class.getMethod("testModifiers")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_1.TestField3.class.getMethod("testModifiers")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_1.TestField4.class.getMethod("testModifiers")))
                .pointsPassedMax()
                .pointsFailedMin()
                .build())
        .build();

    public static final Criterion H1_1_3 = Criterion.builder()
        .shortDescription("Die Typparameter der Objektattribute sind vollständig und korrekt.")
        .maxPoints(1)
        .minPoints(0)
        .grader(
            Grader.testAwareBuilder()
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_1.TestField1.class.getMethod("testType")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_1.TestField2.class.getMethod("testType")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_1.TestField3.class.getMethod("testType")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_1.TestField4.class.getMethod("testType")))
                .pointsPassedMax()
                .pointsFailedMin()
                .build())
        .build();

    public static final Criterion H1_1_4 = Criterion.builder()
        .shortDescription("Der Konstruktor ist vollständig und korrekt.")
        .maxPoints(1)
        .minPoints(0)
        .grader(
            Grader.testAwareBuilder()
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_1.TestConstructor.class.getMethod("testModifiers")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_1.TestConstructor.class.getMethod("testParameterTypes")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_1.TestConstructor.class.getMethod("testFields")))
                .pointsPassedMax()
                .pointsFailedMin()
                .build())
        .build();

    public static final Criterion H1_1_5 = Criterion.builder()
        .shortDescription("Die Getter-Methoden für die Objektattribute sind vollständig und korrekt.")
        .maxPoints(0)
        .minPoints(-1)
        .grader(
            Grader.testAwareBuilder()
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_1.TestField1.class.getMethod("testGetter")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_1.TestField2.class.getMethod("testGetter")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_1.TestField3.class.getMethod("testGetter")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_1.TestField4.class.getMethod("testGetter")))
                .pointsPassedMax()
                .pointsFailedMin()
                .build())
        .build();

    public static final Criterion H1_1 = Criterion.builder()
        .shortDescription("H1.1: Klasse Traits")
        .addChildCriteria(H1_1_1, H1_1_2, H1_1_3, H1_1_4, H1_1_5)
        .build();


    /* *********************************************************************
     *                                H1.2                                 *
     **********************************************************************/

    public static final Criterion H1_2_1 = Criterion.builder()
        .shortDescription("Die Klassensignatur ist vollständig und korrekt.")
        .maxPoints(1)
        .minPoints(0)
        .grader(
            Grader.testAwareBuilder()
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_2.TestClassHeader.class.getMethod("testModifiers")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_2.TestClassHeader.class.getMethod("testExtension")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_2.TestClassHeader.class.getMethod("testTypeParameters")))
                .pointsPassedMax()
                .pointsFailedMin()
                .build())
        .build();

    public static final Criterion H1_2_2 = Criterion.builder()
        .shortDescription("Das Attribut traits und der Konstruktor sind vollständig und korrekt.")
        .maxPoints(1)
        .minPoints(0)
        .grader(
            Grader.testAwareBuilder()
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_2.TestConstructor.class.getMethod("testModifiers")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_2.TestConstructor.class.getMethod("testParameter")))
                .pointsPassedMax()
                .pointsFailedMin()
                .build())
        .build();

    public static final Criterion H1_2_3 = Criterion.builder()
        .shortDescription("Die Methode apply sind vollständig und korrekt.")
        .maxPoints(1)
        .minPoints(0)
        .grader(
            Grader.testAwareBuilder()
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_2.TestMethod.class.getMethod("testModifiers")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_2.TestMethod.class.getMethod("testReturnType")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_2.TestMethod.class.getMethod("testParameters")))
                .pointsPassedMax()
                .pointsFailedMin()
                .build())
        .build();

    public static final Criterion H1_2 = Criterion.builder()
        .shortDescription("H1.2: Klasse FunctionWithFilterMapAndFold")
        .addChildCriteria(H1_2_1, H1_2_2, H1_2_3)
        .build();

    /* *********************************************************************
     *                                H1.3                                 *
     **********************************************************************/

    public static final Criterion H1_3_1 = Criterion.builder()
        .shortDescription("Die Klassen- und Konstruktorsignatur sind vollständig und korrekt.")
        .maxPoints(1)
        .minPoints(0)
        .grader(
            Grader.testAwareBuilder()
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_3.TestClassHeader.class.getMethod("testModifiers")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_3.TestClassHeader.class.getMethod("testExtension")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_3.TestClassHeader.class.getMethod("testTypeParameters")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_3.TestConstructor.class.getMethod("testModifiers")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_3.TestConstructor.class.getMethod("testParameter")))
                .pointsPassedMax()
                .pointsFailedMin()
                .build())
        .build();

    public static final Criterion H1_3_2 = Criterion.builder()
        .shortDescription("Mind. fold Operation funktioniert.")
        .maxPoints(1)
        .minPoints(0)
        .grader(
            Grader.testAwareBuilder()
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_3.TestMethod.class.getMethod("testReturnValueOperation3")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_3.TestMethod.class.getMethod("testRequirementArrays",
                    TestCycle.class)))
                .pointsPassedMax()
                .pointsFailedMin()
                .build())
        .build();

    public static final Criterion H1_3_3 = Criterion.builder()
        .shortDescription("Alle Operationen funktioneren.")
        .maxPoints(1)
        .minPoints(0)
        .grader(
            Grader.testAwareBuilder()
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_3.TestMethod.class.getMethod("testReturnValue")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_3.TestMethod.class.getMethod("testRequirementArrays",
                    TestCycle.class)))
                .pointsPassedMax()
                .pointsFailedMin()
                .build())
        .build();

    public static final Criterion H1_3_4 = Criterion.builder()
        .shortDescription("Die Methodensignatur ist vollständig und korrekt.")
        .maxPoints(0)
        .minPoints(-1)
        .grader(
            Grader.testAwareBuilder()
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_3.TestMethod.class.getMethod("testModifiers")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_3.TestMethod.class.getMethod("testParameters")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_3.TestMethod.class.getMethod("testReturnType")))
                .pointsPassedMax()
                .pointsFailedMin()
                .build())
        .build();

    public static final Criterion H1_3_5 = Criterion.builder()
        .shortDescription("Es wurden keine Importe verwendet, die nicht explizit erlaubt waren.")
        .maxPoints(0)
        .minPoints(-(H1_3_1.getMaxPoints() + H1_3_2.getMaxPoints() + H1_3_3.getMaxPoints()))
        .grader(
            Grader.testAwareBuilder()
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_3.TestClassHeader.class.getMethod("testImports",
                    TestCycle.class)))
                .pointsPassedMax()
                .pointsFailedMin()
                .build())
        .build();

    public static final Criterion H1_3 = Criterion.builder()
        .shortDescription("H1.3: Klasse MyFunctionWithFilterMapAndFold")
        .addChildCriteria(H1_3_1, H1_3_2, H1_3_3, H1_3_4, H1_3_5)
        .build();

    /* *********************************************************************
     *                                H1.4                                 *
     **********************************************************************/

    public static final Criterion H1_4_1 = Criterion.builder()
        .shortDescription("Die Klassen- und Konstruktorsignatur sind vollständig und korrekt.")
        .maxPoints(1)
        .minPoints(0)
        .grader(
            Grader.testAwareBuilder()
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_4.TestClassHeader.class.getMethod("testModifiers")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_4.TestClassHeader.class.getMethod("testExtension")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_4.TestClassHeader.class.getMethod("testTypeParameters")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_4.TestConstructor.class.getMethod("testModifiers")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_4.TestConstructor.class.getMethod("testParameter")))
                .pointsPassedMax()
                .pointsFailedMin()
                .build())
        .build();

    public static final Criterion H1_4_2 = Criterion.builder()
        .shortDescription("Der Rückgabewert ist vollständig und korrekt.")
        .maxPoints(2)
        .minPoints(0)
        .grader(
            Grader.testAwareBuilder()
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_4.TestMethod.class.getMethod("testReturnValue")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_4.TestMethod.class.getMethod("testRequirementArrays",
                    TestCycle.class)))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_4.TestMethod.class.getMethod("testRequirementForeachLoop",
                    TestCycle.class)))
                .pointsPassedMax()
                .pointsFailedMin()
                .build())
        .build();

    public static final Criterion H1_4_3 = Criterion.builder()
        .shortDescription("Die Methodensignatur ist vollständig und korrekt.")
        .maxPoints(0)
        .minPoints(-1)
        .grader(
            Grader.testAwareBuilder()
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_4.TestMethod.class.getMethod("testModifiers")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_4.TestMethod.class.getMethod("testParameters")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_4.TestMethod.class.getMethod("testReturnType")))
                .pointsPassedMax()
                .pointsFailedMin()
                .build())
        .build();

    public static final Criterion H1_4_4 = Criterion.builder()
        .shortDescription("Es wurden keine Importe verwendet, die nicht explizit erlaubt waren.")
        .maxPoints(0)
        .minPoints(-(H1_4_1.getMaxPoints() + H1_4_2.getMaxPoints()))
        .grader(
            Grader.testAwareBuilder()
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_4.TestClassHeader.class.getMethod("testImports",
                    TestCycle.class)))
                .pointsPassedMax()
                .pointsFailedMin()
                .build())
        .build();

    public static final Criterion H1_4_5 = Criterion.builder()
        .shortDescription("Klasse: MyFunctionWithAdjacent")
        .addChildCriteria(H1_4_1, H1_4_2, H1_4_3, H1_4_4)
        .build();

    public static final Criterion H1_4_6 = Criterion.builder()
        .shortDescription("Das Attribut combine ist vollständig und korrekt.")
        .maxPoints(1)
        .minPoints(0)
        .grader(
            Grader.testAwareBuilder()
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_4.TestH1ExtraField.TestField.class.getMethod(
                    "testModifiers")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_4.TestH1ExtraField.TestField.class.getMethod("testType")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_4.TestH1ExtraField.TestField.class.getMethod("testGetter")))
                .pointsPassedMax()
                .pointsFailedMin()
                .build())
        .build();

    public static final Criterion H1_4_7 = Criterion.builder()
        .shortDescription("Der Konstruktor mit 4 Parametern in Traits ist vollständig und korrekt.")
        .maxPoints(1)
        .minPoints(0)
        .grader(
            Grader.testAwareBuilder()
                .requirePass(JUnitTestRef.ofMethod(() -> Test4Args.class.getMethod("testModifiers")))
                .requirePass(JUnitTestRef.ofMethod(() -> Test4Args.class.getMethod("testParameterTypes")))
                .requirePass(JUnitTestRef.ofMethod(() -> Test4Args.class.getMethod("testFields")))
                .pointsPassedMax()
                .pointsFailedMin()
                .build())
        .build();

    public static final Criterion H1_4_8 = Criterion.builder()
        .shortDescription("Der Konstruktor mit 5 Parametern in Traits ist vollständig und korrekt.")
        .maxPoints(1)
        .minPoints(0)
        .grader(
            Grader.testAwareBuilder()
                .requirePass(JUnitTestRef.ofMethod(() -> Test5Args.class.getMethod("testModifiers")))
                .requirePass(JUnitTestRef.ofMethod(() -> Test5Args.class.getMethod("testParameterTypes")))
                .requirePass(JUnitTestRef.ofMethod(() -> Test5Args.class.getMethod("testFields")))
                .pointsPassedMax()
                .pointsFailedMin()
                .build())
        .build();

    public static final Criterion H1_4_9 = Criterion.builder()
        .shortDescription("Klasse: Traits")
        .addChildCriteria(H1_4_6, H1_4_7, H1_4_8)
        .build();

    public static final Criterion H1_4 = Criterion.builder()
        .shortDescription("H1.4: Klasse MyFunctionWithAdjacent")
        .addChildCriteria(H1_4_5, H1_4_9)
        .build();

    /* *********************************************************************
     *                                H1.5                                 *
     **********************************************************************/

    public static final Criterion H1_5_1 = Criterion.builder()
        .shortDescription("Die Klassen- und Konstruktorsignatur sind vollständig und korrekt.")
        .maxPoints(1)
        .minPoints(0)
        .grader(
            Grader.testAwareBuilder()
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_5.TestClassHeader.class.getMethod("testModifiers")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_5.TestClassHeader.class.getMethod("testTypeParameter")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_5.TestClassHeader.class.getMethod("testModifiers")))
                .pointsPassedMax()
                .pointsFailedMin()
                .build())
        .build();

    public static final Criterion H1_5_2 = Criterion.builder()
        .shortDescription("Die Methodensignatur von createFunctionWithFilterMapFold und "
            + "createFunctionWithFilterMapFoldAndCombine sind vollständig und korrekt.")
        .maxPoints(1)
        .minPoints(0)
        .grader(
            Grader.testAwareBuilder()
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_5.TestMethod1.class.getMethod("testModifiers")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_5.TestMethod1.class.getMethod("testParameters")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_5.TestMethod1.class.getMethod("testReturnType")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_5.TestMethod2.class.getMethod("testModifiers")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_5.TestMethod2.class.getMethod("testParameters")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_5.TestMethod2.class.getMethod("testReturnType")))
                .pointsPassedMax()
                .pointsFailedMin()
                .build())
        .build();

    public static final Criterion H1_5_3 = Criterion.builder()
        .shortDescription("Der Rückgabewert von der Methode createFunctionWithFilterMapAndFold ist "
            + "vollständig und korrekt.")
        .maxPoints(1)
        .minPoints(0)
        .grader(
            Grader.testAwareBuilder()
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_5.TestMethod1.class.getMethod("testReturnValue")))
                .pointsPassedMax()
                .pointsFailedMin()
                .build())
        .build();

    public static final Criterion H1_5_4 = Criterion.builder()
        .shortDescription("Die Rückgabewert von der Methode createFunctionWithFilterMapFoldAndCombine"
            + " ist vollständig und korrekt.")
        .maxPoints(1)
        .minPoints(0)
        .grader(
            Grader.testAwareBuilder()
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_5.TestMethod2.class.getMethod("testReturnValue")))
                .pointsPassedMax()
                .pointsFailedMin()
                .build())
        .build();

    public static final Criterion H1_5 = Criterion.builder()
        .shortDescription("H1.5: Klasse FunctionFactory")
        .addChildCriteria(H1_5_1, H1_5_2, H1_5_3, H1_5_4)
        .build();

    /* *********************************************************************
     *                                H1.6                                 *
     **********************************************************************/

    public static final Criterion H1_6_1 = Criterion.builder()
        .shortDescription("Die Testmethode testCreateFunctionWithFilterMapAndFold1 ist vollständig "
            + "und korrekt.")
        .maxPoints(1)
        .minPoints(0)
        .grader(
            Grader.testAwareBuilder()
                // TODO CHECK RESULT
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_6.TestMethod1.class.getMethod("testRequirement",
                    TestCycle.class)))
                .pointsPassedMax()
                .pointsFailedMin()
                .build())
        .build();

    public static final Criterion H1_6_2 = Criterion.builder()
        .shortDescription("Die Testmethode testCreateFunctionWithFilterMapAndFold2 ist vollständig "
            + "und korrekt.")
        .maxPoints(1)
        .minPoints(0)
        .grader(
            Grader.testAwareBuilder()
                // TODO CHECK RESULT
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_6.TestMethod2.class.getMethod("testRequirement",
                    TestCycle.class)))
                .pointsPassedMax()
                .pointsFailedMin()
                .build())
        .build();

    public static final Criterion H1_6_3 = Criterion.builder()
        .shortDescription("Die Testmethode testCreateFunctionWithFilterMapFoldAndCombine ist "
            + "vollständig und korrekt.")
        .maxPoints(1)
        .minPoints(0)
        .grader(
            Grader.testAwareBuilder()
                // TODO CHECK RESULT
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_6.TestMethod3.class.getMethod("testRequirement",
                    TestCycle.class)))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_6.TestMethod3.TestPerson.TestClassHeader.class.getMethod(
                    "testModifiers")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_6.TestMethod3.TestPerson.TestClassHeader.class.getMethod(
                    "testExtension")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_6.TestMethod3.TestPerson.TestField1.class.getMethod(
                    "testGetter")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_6.TestMethod3.TestPerson.TestField1.class.getMethod(
                    "testSetter")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_6.TestMethod3.TestPerson.TestField1.class.getMethod(
                    "testModifiers")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_6.TestMethod3.TestPerson.TestField2.class.getMethod(
                    "testGetter")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_6.TestMethod3.TestPerson.TestField2.class.getMethod(
                    "testSetter")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_6.TestMethod3.TestPerson.TestField2.class.getMethod(
                    "testModifiers")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_6.TestMethod3.TestPerson.TestField3.class.getMethod(
                    "testGetter")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_6.TestMethod3.TestPerson.TestField3.class.getMethod(
                    "testSetter")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_6.TestMethod3.TestPerson.TestField3.class.getMethod(
                    "testModifiers")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_6.TestMethod3.TestPerson.TestField4.class.getMethod(
                    "testGetter")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_6.TestMethod3.TestPerson.TestField4.class.getMethod(
                    "testSetter")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_6.TestMethod3.TestPerson.TestField4.class.getMethod(
                    "testModifiers")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_6.TestMethod3.TestPerson.TestField5.class.getMethod(
                    "testGetter")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_6.TestMethod3.TestPerson.TestField5.class.getMethod(
                    "testSetter")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_6.TestMethod3.TestPerson.TestField5.class.getMethod(
                    "testModifiers")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_6.TestMethod3.TestPerson.TestConstructor.class.getMethod(
                    "testModifiers")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_6.TestMethod3.TestPerson.TestConstructor.class.getMethod(
                    "testFields")))
                .pointsPassedMax()
                .pointsFailedMin()
                .build())
        .build();

    public static final Criterion H1_6_4 = Criterion.builder()
        .shortDescription("Die Klassensignatur ist vollständig und korrekt.")
        .maxPoints(0)
        .minPoints(-1)
        .grader(
            Grader.testAwareBuilder()
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_6.TestClassHeader.class.getMethod("testModifiers")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H1_6.TestClassHeader.class.getMethod("testExtension")))
                .pointsPassedMax()
                .pointsFailedMin()
                .build())
        .build();

    public static final Criterion H1_6 = Criterion.builder()
        .shortDescription("H1.6: JUnit-Tests")
        .addChildCriteria(H1_6_1, H1_6_2, H1_6_3, H1_6_4)
        .build();

    /* *********************************************************************
     *                                 H1                                  *
     **********************************************************************/

    public static final Criterion H1 = Criterion.builder()
        .shortDescription("H1: Generische Operationen - Filter, Map und Fold")
        .addChildCriteria(H1_1, H1_2, H1_3, H1_4, H1_5, H1_6)
        .build();

    /* *********************************************************************
     *                                H2.1                                 *
     **********************************************************************/

    public static final Criterion H2_1_1 = Criterion.builder()
        .shortDescription("Es wurden 4 Interfaces vollständig und korrekt implementiert.")
        .maxPoints(1)
        .minPoints(0)
        .grader(
            Grader.testAwareBuilder()
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H2_1.TestInterfaces.class.getMethod("testInterfaces4")))
                .pointsPassedMax()
                .pointsFailedMin()
                .build())
        .build();

    public static final Criterion H2_1_2 = Criterion.builder()
        .shortDescription("Es wurden alle Interfaces vollständig und korrekt implementiert.")
        .maxPoints(1)
        .minPoints(0)
        .grader(
            Grader.testAwareBuilder()
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H2_1.TestInterfaces.class.getMethod("testInterfaces9")))
                .pointsPassedMax()
                .pointsFailedMin()
                .build())
        .build();

    public static final Criterion H2_1_3 = Criterion.builder()
        .shortDescription("Die Klassensignatur, Konstruktor und die Attribute von Rabbit sind "
            + "vollständig und korrekt.")
        .maxPoints(1)
        .minPoints(0)
        .grader(
            Grader.testAwareBuilder()
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H2_1.TestRabbit.TestClassHeader.class.getMethod(
                    "testModifiers")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H2_1.TestRabbit.TestClassHeader.class.getMethod(
                    "testSuperClass")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H2_1.TestRabbit.TestConstructor.class.getMethod(
                    "testModifiers")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H2_1.TestRabbit.TestConstructor.class.getMethod(
                    "testFieldStatic")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H2_1.TestRabbit.TestConstructor.class.getMethod("testField")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H2_1.TestRabbit.TestConstructor.class.getMethod(
                    "testMethodNameOfIndividual")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H2_1.TestRabbit.TestFieldStatic.class.getMethod(
                    "testModifiers")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H2_1.TestRabbit.TestFieldStatic.class.getMethod("testValue")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H2_1.TestRabbit.TestField.class.getMethod("testModifiers")))
                .pointsPassedMax()
                .pointsFailedMin()
                .build())
        .build();

    public static final Criterion H2_1_4 = Criterion.builder()
        .shortDescription("Die getTypeOfX Methoden von Rabbit sind vollständig und korrekt.")
        .maxPoints(1)
        .minPoints(0)
        .grader(
            Grader.testAwareBuilder()
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H2_1.TestRabbit.TestMethodsTypeOfX.class.getMethod(
                    "testTypeOfAnimal")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H2_1.TestRabbit.TestMethodsTypeOfX.class.getMethod(
                    "testGetTypeOfVertebrate")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H2_1.TestRabbit.TestMethodsTypeOfX.class.getMethod(
                    "testTypeOfMammal")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H2_1.TestRabbit.TestMethodsTypeOfX.class.getMethod(
                    "testTypeOfPlacental")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H2_1.TestRabbit.TestMethodsTypeOfX.class.getMethod(
                    "testTypeOfLagomorpha")))
                .pointsPassedMax()
                .pointsFailedMin()
                .build())
        .build();

    public static final Criterion H2_1 = Criterion.builder()
        .shortDescription("H2.1: Beispielmaterial")
        .addChildCriteria(H2_1_1, H2_1_2, H2_1_3, H2_1_4)
        .build();

    /* *********************************************************************
     *                                H2.2                                 *
     **********************************************************************/

    public static final Criterion H2_2_1 = Criterion.builder()
        .shortDescription("Die Konstruktor von BiologyHierarchy ist vollständig und korrekt.")
        .maxPoints(0)
        .minPoints(-1)
        .grader(
            Grader.testAwareBuilder()
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H2_2.TestClassHeader.class.getMethod("testModifiers")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H2_2.TestClassHeader.class.getMethod("testTypeParameter")))
                .pointsPassedMax()
                .pointsFailedMin()
                .build())
        .build();

    public static final Criterion H2_2_2 = Criterion.builder()
        .shortDescription("Die Methodensignatur von getTypeOfVertebrate ist vollständig und korrekt.")
        .maxPoints(1)
        .minPoints(0)
        .grader(
            Grader.testAwareBuilder()
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H2_2.TestMethod1.class.getMethod("testModifiers")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H2_2.TestMethod1.class.getMethod("testGeneric")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H2_2.TestMethod1.class.getMethod("testParameters")))
                .pointsPassedMax()
                .pointsFailedMin()
                .build())
        .build();

    public static final Criterion H2_2_3 = Criterion.builder()
        .shortDescription("Die Methode getTypeOfVertebrate ist vollständig und korrekt.")
        .maxPoints(1)
        .minPoints(0)
        .grader(
            Grader.testAwareBuilder()
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H2_2.TestMethod1.class.getMethod("testResult")))
                .pointsPassedMax()
                .pointsFailedMin()
                .build())
        .build();

    public static final Criterion H2_2_4 = Criterion.builder()
        .shortDescription("Die Methodensignatur von filterLagomorphs ist vollständig und korrekt.")
        .maxPoints(1)
        .minPoints(0)
        .grader(
            Grader.testAwareBuilder()
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H2_2.TestMethod2.class.getMethod("testModifiers")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H2_2.TestMethod2.class.getMethod("testParameters")))
                .pointsPassedMax()
                .pointsFailedMin()
                .build())
        .build();

    public static final Criterion H2_2_5 = Criterion.builder()
        .shortDescription("Die Methode filterLagomorphs ist vollständig und korrekt.")
        .maxPoints(1)
        .minPoints(0)
        .grader(
            Grader.testAwareBuilder()
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H2_2.TestMethod2.class.getMethod("testResultListOfPlacental")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H2_2.TestMethod2.class.getMethod("testResultListOfMammal")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H2_2.TestMethod2.class.getMethod(
                    "testResultListOfVertebrate")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H2_2.TestMethod2.class.getMethod("testResultListOfAnimal")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H2_2.TestMethod2.class.getMethod("testResultListOfObject")))
                .pointsPassedMax()
                .pointsFailedMin()
                .build())
        .build();

    public static final Criterion H2_2_6 = Criterion.builder()
        .shortDescription("Die Methodensignatur von testGetTypesOfMammals ist vollständig und korrekt.")
        .maxPoints(1)
        .minPoints(0)
        .grader(
            Grader.testAwareBuilder()
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H2_2.TestMethod3.class.getMethod("testModifiers")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H2_2.TestMethod3.class.getMethod("testParameters")))
                .pointsPassedMax()
                .pointsFailedMin()
                .build())
        .build();

    public static final Criterion H2_2_7 = Criterion.builder()
        .shortDescription("Die Methode testGetTypesOfMammals ist vollständig und korrekt.")
        .maxPoints(1)
        .minPoints(0)
        .grader(
            Grader.testAwareBuilder()
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H2_2.TestMethod3.class.getMethod("testResultListOfMammal")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H2_2.TestMethod3.class.getMethod("testResultListOfPlacental")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H2_2.TestMethod3.class.getMethod("testResultListOfMonotreme")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H2_2.TestMethod3.class.getMethod(
                    "testResultListOfLagomorpha")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H2_2.TestMethod3.class.getMethod("testResultListOfRodent")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H2_2.TestMethod3.class.getMethod("testResultListOfLeporidae")))
                .pointsPassedMax()
                .pointsFailedMin()
                .build())
        .build();

    public static final Criterion H2_2 = Criterion.builder()
        .shortDescription("H2.2: Testmethoden für Restricted Genericity und Wildcards")
        .addChildCriteria(H2_2_1, H2_2_2, H2_2_3, H2_2_4, H2_2_5, H2_2_6, H2_2_7)
        .build();

    /* *********************************************************************
     *                                H2.3                                 *
     **********************************************************************/

    public static final Criterion H2_3_1 = Criterion.builder()
        .shortDescription("Die Klassensignatur ist vollständig und korrekt.")
        .maxPoints(0)
        .minPoints(-1)
        .grader(
            Grader.testAwareBuilder()
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H2_3.TestClassHeader.class.getMethod("testModifiers")))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H2_3.TestClassHeader.class.getMethod("testExtension")))
                .pointsPassedMax()
                .pointsFailedMin()
                .build())
        .build();

    public static final Criterion H2_3_2 = Criterion.builder()
        .shortDescription("Die Methode testGetTypeOfVertebrate ist vollständig und korrekt.")
        .maxPoints(1)
        .minPoints(0)
        .grader(
            Grader.testAwareBuilder()
                // TODO CHECK RESULT
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H2_3.TestMethod1.class.getMethod("testRequirements3",
                    TestCycle.class)))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H2_3.TestMethod1.class.getMethod("testRequirements7",
                    TestCycle.class)))
                .pointsPassedMax()
                .pointsFailedMin()
                .build())
        .build();

    public static final Criterion H2_3_3 = Criterion.builder()
        .shortDescription("Die Methode testFilterLagomorphs ist vollständig und korrekt.")
        .maxPoints(1)
        .minPoints(0)
        .grader(
            Grader.testAwareBuilder()
                // TODO CHECK RESULT
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H2_3.TestMethod2.class.getMethod("testRequirements4",
                    TestCycle.class)))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H2_3.TestMethod2.class.getMethod("testRequirements9",
                    TestCycle.class)))
                .pointsPassedMax()
                .pointsFailedMin()
                .build())
        .build();

    public static final Criterion H2_3_4 = Criterion.builder()
        .shortDescription("Die Methode testGetTypesOfMammals ist vollständig und korrekt.")
        .maxPoints(1)
        .minPoints(0)
        .grader(
            Grader.testAwareBuilder()
                // TODO CHECK RESULT
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H2_3.TestMethod3.class.getMethod("testRequirements3",
                    TestCycle.class)))
                .requirePass(JUnitTestRef.ofMethod(() -> TutorTest_H2_3.TestMethod3.class.getMethod("testRequirements6",
                    TestCycle.class)))
                .pointsPassedMax()
                .pointsFailedMin()
                .build())
        .build();

    public static final Criterion H2_3 = Criterion.builder()
        .shortDescription("H2.3: JUnit-Tests")
        .addChildCriteria(H2_3_1, H2_3_2, H2_3_3, H2_3_4)
        .build();

    /* *********************************************************************
     *                                 H2                                  *
     **********************************************************************/

    public static final Criterion H2 = Criterion.builder()
        .shortDescription("H2: Restricted Genericity und Wildcards")
        .addChildCriteria(H2_1, H2_2, H2_3)
        .build();

    /* *********************************************************************
     *                              Rubric                                 *
     **********************************************************************/

    public static final Rubric RUBRIC = Rubric.builder()
        .title("h09")
        .addChildCriteria(H1, H2)
        .build();

    @Override
    public Rubric getRubric() {
        return RUBRIC;
    }

}
