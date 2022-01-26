package h09;

import h09.utils.TutorConstants;
import h09.utils.rd.ClassTester;

import java.util.ArrayList;

/**
 * Contains all class testers for the exercises.
 */
public final class H09_Class_Testers {


    /**
     * Don't let anyone instantiate this class.
     */
    private H09_Class_Testers() {
    }

    /**
     * The minimum required similarity.
     */
    public static final double MINIMUM_SIMILARITY = 0.8;

    /* *********************************************************************
     *                                 H1                                  *
     **********************************************************************/

    /**
     * The class tester for {@value  TutorConstants#H1_1_CLASS_NAME}.
     */
    public static final ClassTester<?> H1_1 = new ClassTester<>(
        TutorConstants.H1_PACKAGE_NAME,
        TutorConstants.H1_1_CLASS_NAME, MINIMUM_SIMILARITY,
        -1
    );


    /**
     * The class tester for {@value  TutorConstants#H1_3_CLASS_NAME}.
     */
    public static final ClassTester<?> H1_2 = new ClassTester<>(
        TutorConstants.H1_PACKAGE_NAME,
        TutorConstants.H1_2_CLASS_NAME, MINIMUM_SIMILARITY,
        -1
    );

    /**
     * The class tester for {@value  TutorConstants#H1_3_CLASS_NAME}.
     */
    public static final ClassTester<?> H1_3 = new ClassTester<>(
        TutorConstants.H1_PACKAGE_NAME,
        TutorConstants.H1_3_CLASS_NAME, MINIMUM_SIMILARITY,
        -1,
        H1_2.getTheClass(),
        new ArrayList<>()
    );

    /**
     * The class tester for {@value  TutorConstants#H1_4_CLASS_NAME}.
     */
    public static final ClassTester<?> H1_4 = new ClassTester<>(
        TutorConstants.H1_PACKAGE_NAME,
        TutorConstants.H1_4_CLASS_NAME, MINIMUM_SIMILARITY,
        -1,
        H1_2.getTheClass(),
        new ArrayList<>()
    );

    /**
     * The class tester for {@value  TutorConstants#H1_5_CLASS_NAME}.
     */
    public static final ClassTester<?> H1_5 = new ClassTester<>(
        TutorConstants.H1_PACKAGE_NAME,
        TutorConstants.H1_5_CLASS_NAME, MINIMUM_SIMILARITY,
        -1
    );

    /**
     * The class tester for {@link  TutorConstants#H1_6_CLASS_NAME}.
     */
    public static final ClassTester<?> H1_6 = new ClassTester<>(
        TutorConstants.H1_PACKAGE_NAME,
        TutorConstants.H1_6_CLASS_NAME, MINIMUM_SIMILARITY
    );

    /**
     * The class tester for {@value  TutorConstants#H1_6_CLASS_NAME_1}.
     */
    public static final ClassTester<?> H1_6_1 = new ClassTester<>(
        TutorConstants.H1_PACKAGE_NAME,
        TutorConstants.H1_6_CLASS_NAME_1, MINIMUM_SIMILARITY,
        -1
    );

    /* *********************************************************************
     *                                 H2                                  *
     **********************************************************************/

    /**
     * The class tester for {@value  TutorConstants#H2_1_CLASS_NAME_1}.
     */
    public static final ClassTester<?> H2_1_1 = new ClassTester<>(
        TutorConstants.H2_PACKAGE_NAME,
        TutorConstants.H2_1_CLASS_NAME_1, MINIMUM_SIMILARITY,
        -1
    );

    /**
     * The class tester for {@value  TutorConstants#H2_1_CLASS_NAME_2}.
     */
    public static final ClassTester<?> H2_1_2 = new ClassTester<>(
        TutorConstants.H2_PACKAGE_NAME,
        TutorConstants.H2_1_CLASS_NAME_2, MINIMUM_SIMILARITY,
        -1
    );

    /**
     * The class tester for {@value  TutorConstants#H2_1_CLASS_NAME_3}.
     */
    public static final ClassTester<?> H2_1_3 = new ClassTester<>(
        TutorConstants.H2_PACKAGE_NAME,
        TutorConstants.H2_1_CLASS_NAME_3, MINIMUM_SIMILARITY,
        -1
    );

    /**
     * The class tester for {@value  TutorConstants#H2_1_CLASS_NAME_4}.
     */
    public static final ClassTester<?> H2_1_4 = new ClassTester<>(
        TutorConstants.H2_PACKAGE_NAME,
        TutorConstants.H2_1_CLASS_NAME_4, MINIMUM_SIMILARITY,
        -1
    );

    /**
     * The class tester for {@value  TutorConstants#H2_1_CLASS_NAME_5}.
     */
    public static final ClassTester<?> H2_1_5 = new ClassTester<>(
        TutorConstants.H2_PACKAGE_NAME,
        TutorConstants.H2_1_CLASS_NAME_5, MINIMUM_SIMILARITY,
        -1
    );

    /**
     * The class tester for {@value  TutorConstants#H2_1_CLASS_NAME_6}.
     */
    public static final ClassTester<?> H2_1_6 = new ClassTester<>(
        TutorConstants.H2_PACKAGE_NAME,
        TutorConstants.H2_1_CLASS_NAME_6, MINIMUM_SIMILARITY,
        -1
    );

    /**
     * The class tester for {@value  TutorConstants#H2_1_CLASS_NAME_7}.
     */
    public static final ClassTester<?> H2_1_7 = new ClassTester<>(
        TutorConstants.H2_PACKAGE_NAME,
        TutorConstants.H2_1_CLASS_NAME_7, MINIMUM_SIMILARITY,
        -1
    );

    /**
     * The class tester for {@value  TutorConstants#H2_1_CLASS_NAME_7}.
     */
    public static final ClassTester<?> H2_1_8 = new ClassTester<>(
        TutorConstants.H2_PACKAGE_NAME,
        TutorConstants.H2_1_CLASS_NAME_7, MINIMUM_SIMILARITY,
        -1
    );

    /**
     * The class tester for {@value  TutorConstants#H2_1_CLASS_NAME_9}.
     */
    public static final ClassTester<?> H2_1_9 = new ClassTester<>(
        TutorConstants.H2_PACKAGE_NAME,
        TutorConstants.H2_1_CLASS_NAME_9, MINIMUM_SIMILARITY,
        -1
    );

    /**
     * The class tester for {@value  TutorConstants#H2_1_CLASS_NAME_10}.
     */
    public static final ClassTester<?> H2_1_10 = new ClassTester<>(
        TutorConstants.H2_PACKAGE_NAME,
        TutorConstants.H2_1_CLASS_NAME_10, MINIMUM_SIMILARITY,
        -1
    );

    /**
     * The class tester for {@link TutorConstants#H2_2_CLASS_NAME}.
     */
    public static final ClassTester<?> H2_2 = new ClassTester<>(
        TutorConstants.H2_PACKAGE_NAME,
        TutorConstants.H2_2_CLASS_NAME[0], MINIMUM_SIMILARITY,
        -1
    );

    /**
     * The class tester for {@value TutorConstants#H2_3_CLASS_NAME}.
     */
    public static final ClassTester<?> H2_3 = new ClassTester<>(
        TutorConstants.H2_PACKAGE_NAME,
        TutorConstants.H2_3_CLASS_NAME, MINIMUM_SIMILARITY,
        -1
    );

    /**
     * The class tester for the test class {@value TutorConstants#H2_1_CLASS_NAME_1}.
     */
    public static final ClassTester<?> H2_3_1 = new ClassTester<>(
        TutorConstants.H2_PACKAGE_NAME,
        TutorConstants.H2_3_CLASS_NAME_1, MINIMUM_SIMILARITY,
        -1
    );

    /**
     * The class tester for the test class {@value TutorConstants#H2_1_CLASS_NAME_1}.
     */
    public static final ClassTester<?> H2_3_2 = new ClassTester<>(
        TutorConstants.H2_PACKAGE_NAME,
        TutorConstants.H2_3_CLASS_NAME_2, MINIMUM_SIMILARITY,
        -1
    );

    /**
     * The class tester for the test class {@value TutorConstants#H2_1_CLASS_NAME_3}.
     */
    public static final ClassTester<?> H2_3_3 = new ClassTester<>(
        TutorConstants.H2_PACKAGE_NAME,
        TutorConstants.H2_3_CLASS_NAME_3, MINIMUM_SIMILARITY,
        -1
    );

    /**
     * The class tester for the test class {@value TutorConstants#H2_1_CLASS_NAME_4}.
     */
    public static final ClassTester<?> H2_3_4 = new ClassTester<>(
        TutorConstants.H2_PACKAGE_NAME,
        TutorConstants.H2_3_CLASS_NAME_4, MINIMUM_SIMILARITY,
        -1
    );

    /**
     * The class tester for the test class {@value TutorConstants#H2_1_CLASS_NAME_5}.
     */
    public static final ClassTester<?> H2_3_5 = new ClassTester<>(
        TutorConstants.H2_PACKAGE_NAME,
        TutorConstants.H2_3_CLASS_NAME_5, MINIMUM_SIMILARITY,
        -1
    );

    /**
     * The class tester for the test class {@value TutorConstants#H2_1_CLASS_NAME_6}.
     */
    public static final ClassTester<?> H2_3_6 = new ClassTester<>(
        TutorConstants.H2_PACKAGE_NAME,
        TutorConstants.H2_3_CLASS_NAME_6, MINIMUM_SIMILARITY,
        -1
    );

    /**
     * The class tester for the test class {@value TutorConstants#H2_1_CLASS_NAME_7}.
     */
    public static final ClassTester<?> H2_3_7 = new ClassTester<>(
        TutorConstants.H2_PACKAGE_NAME,
        TutorConstants.H2_3_CLASS_NAME_7, MINIMUM_SIMILARITY,
        -1
    );

    /**
     * The class tester for the test class {@value TutorConstants#H2_1_CLASS_NAME_8}.
     */
    public static final ClassTester<?> H2_3_8 = new ClassTester<>(
        TutorConstants.H2_PACKAGE_NAME,
        TutorConstants.H2_3_CLASS_NAME_8, MINIMUM_SIMILARITY,
        -1
    );

    /**
     * The class tester for the test class {@value TutorConstants#H2_1_CLASS_NAME_9}.
     */
    public static final ClassTester<?> H2_3_9 = new ClassTester<>(
        TutorConstants.H2_PACKAGE_NAME,
        TutorConstants.H2_3_CLASS_NAME_9, MINIMUM_SIMILARITY,
        -1
    );
}
