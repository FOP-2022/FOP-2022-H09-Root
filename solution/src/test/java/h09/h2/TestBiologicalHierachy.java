package h09.h2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Defines the test cases for the class {@link BiologyHierarchy}.
 *
 * @author Nhan Huynh, Darya Nikitina
 */
class TestBiologicalHierachy {

    /**
     * The instance of a biology hierarchy used to test the methods.
     */
    private final BiologyHierarchy hierarchy = new BiologyHierarchy();

    /**
     * Tests whether the method {@link BiologyHierarchy#getTypeOfVertebrate(Vertebrate)} returns
     * the correct string.
     */
    @Test
    void testGetTypeOfVertebrate() {
        Assertions.assertNull(hierarchy.getTypeOfVertebrate(new TestVertebrate()));
        Assertions.assertEquals("Mammal", hierarchy.getTypeOfVertebrate(new TestMammal()));
        Assertions.assertEquals("Bird", hierarchy.getTypeOfVertebrate(new TestBird()));
        Assertions.assertEquals("Mammal", hierarchy.getTypeOfVertebrate(new TestPlacental()));
        Assertions.assertEquals("Mammal", hierarchy.getTypeOfVertebrate(new TestMonotreme()));
        Assertions.assertEquals("Mammal", hierarchy.getTypeOfVertebrate(new TestRodent()));
        Assertions.assertEquals("Mammal", hierarchy.getTypeOfVertebrate(new TestLagomorpha()));
    }

    /**
     * Tests whether the method {@link BiologyHierarchy#filterLagomorphs(List)} returns
     * the correct animals.
     */
    @Test
    void testFilterLagomorphs() {
        final var animal = new TestAnimal();
        final var vertebrate = new TestVertebrate();
        final var mammal = new TestMammal();
        final var bird = new TestBird();
        final var placental = new TestPlacental();
        final var monotreme = new TestMonotreme();
        final var rodent = new TestRodent();
        final var lagomorpha = new TestLagomorpha();
        final var leporidae = new Rabbit();

        final List<Placental> lp = List.of(
            placental,
            rodent,
            lagomorpha,
            leporidae
        );
        final List<Mammal> lm = List.of(
            mammal,
            placental,
            monotreme,
            rodent,
            lagomorpha,
            leporidae
        );
        final List<Vertebrate> lv = List.of(
            vertebrate,
            mammal,
            bird,
            placental,
            monotreme,
            rodent,
            lagomorpha,
            leporidae
        );
        final List<Animal> la = List.of(
            animal,
            vertebrate,
            mammal,
            bird,
            placental,
            monotreme,
            rodent,
            lagomorpha,
            leporidae
        );
        final List<Object> lo = List.of(
            animal,
            vertebrate,
            mammal,
            bird,
            12345,
            placental,
            monotreme,
            new Object(),
            rodent,
            lagomorpha,
            leporidae,
            "Lagomorph"
        );

        final List<Lagomorpha> expected = List.of(
            lagomorpha,
            leporidae
        );

        Assertions.assertEquals(expected, hierarchy.filterLagomorphs(lp));
        Assertions.assertEquals(expected, hierarchy.filterLagomorphs(lm));
        Assertions.assertEquals(expected, hierarchy.filterLagomorphs(lv));
        Assertions.assertEquals(expected, hierarchy.filterLagomorphs(la));
        Assertions.assertEquals(expected, hierarchy.filterLagomorphs(lo));
    }

    /**
     * Tests whether the method {@link BiologyHierarchy#getTypesOfMammals(List)} returns
     * the correct outputs of the method calls {@link Mammal#getTypeOfMammal()}
     */
    @Test
    void testGetTypesOfMammals() {
        final var mammal = new TestMammal();
        final var placental = new TestPlacental();
        final var monotreme = new TestMonotreme();
        final var rodent = new TestRodent();
        final var lagomorpha = new TestLagomorpha();
        final var leporidae = new Rabbit();

        final List<Mammal> lm = List.of(
            mammal,
            placental,
            monotreme,
            rodent,
            lagomorpha,
            leporidae
        );
        final List<Placental> lp = List.of(
            placental,
            rodent,
            lagomorpha,
            leporidae
        );
        final List<Monotreme> lmo = List.of(
            monotreme
        );
        final List<Lagomorpha> ll = List.of(
            lagomorpha,
            leporidae
        );
        final List<Rodent> lr = List.of(
            rodent
        );
        final List<Leporidae> lle = List.of(
            leporidae
        );

        final var em = new ArrayList<String>();
        for (final var m : lm) {
            em.add(m.getTypeOfMammal());
        }
        final var ep = new ArrayList<String>();
        for (final var m : lp) {
            ep.add(m.getTypeOfMammal());
        }
        final var emo = new ArrayList<String>();
        for (final var m : lmo) {
            emo.add(m.getTypeOfMammal());
        }
        final var el = new ArrayList<String>();
        for (final var m : ll) {
            el.add(m.getTypeOfMammal());
        }
        final var er = new ArrayList<String>();
        for (final var m : lr) {
            er.add(m.getTypeOfMammal());
        }
        final var ele = new ArrayList<String>();
        for (final var m : lle) {
            ele.add(m.getTypeOfMammal());
        }

        Assertions.assertEquals(em, hierarchy.getTypesOfMammals(lm));
        Assertions.assertEquals(ep, hierarchy.getTypesOfMammals(lp));
        Assertions.assertEquals(emo, hierarchy.getTypesOfMammals(lmo));
        Assertions.assertEquals(el, hierarchy.getTypesOfMammals(ll));
        Assertions.assertEquals(er, hierarchy.getTypesOfMammals(lr));
        Assertions.assertEquals(ele, hierarchy.getTypesOfMammals(lle));
    }

    /**
     * Defines a test animal used for testing.
     */
    private static class TestAnimal implements Animal {

        @Override
        public String getTypeOfAnimal() {
            return "Animal";
        }
    }

    /**
     * Defines a test vertebrate used for testing.
     */
    private static class TestVertebrate implements Vertebrate {

        @Override
        public String getTypeOfAnimal() {
            return "Vertebrate";
        }

        @Override
        public String getTypeOfVertebrate() {
            return null;
        }
    }

    /**
     * Defines a test mammal used for testing.
     */
    private static class TestMammal implements Mammal {

        @Override
        public String getTypeOfAnimal() {
            return "Vertebrate";
        }

        @Override
        public String getTypeOfMammal() {
            return null;
        }

        @Override
        public String getTypeOfVertebrate() {
            return "Mammal";
        }
    }

    /**
     * Defines a test bird used for testing.
     */
    private static class TestBird implements Bird {

        @Override
        public String getTypeOfAnimal() {
            return "Vertebrate";
        }

        @Override
        public String getTypeOfBird() {
            return null;
        }

        @Override
        public String getTypeOfVertebrate() {
            return "Bird";
        }
    }

    /**
     * Defines a test placental used for testing.
     */
    private static class TestPlacental implements Placental {

        @Override
        public String getTypeOfAnimal() {
            return "Vertebrate";
        }

        @Override
        public String getTypeOfMammal() {
            return "Placental";
        }

        @Override
        public String getTypeOfPlacental() {
            return null;
        }

        @Override
        public String getTypeOfVertebrate() {
            return "Mammal";
        }
    }

    /**
     * Defines a test monotreme used for testing.
     */
    private static class TestMonotreme implements Monotreme {

        @Override
        public String getTypeOfAnimal() {
            return "Vertebrate";
        }

        @Override
        public String getTypeOfMammal() {
            return "Monotreme";
        }

        @Override
        public String getTypeOfMonotreme() {
            return null;
        }

        @Override
        public String getTypeOfVertebrate() {
            return "Mammal";
        }
    }

    /**
     * Defines a test rodent used for testing.
     */
    private static class TestRodent implements Rodent {

        @Override
        public String getTypeOfAnimal() {
            return "Vertebrate";
        }

        @Override
        public String getTypeOfMammal() {
            return "Placental";
        }

        @Override
        public String getTypeOfPlacental() {
            return "Rodent";
        }

        @Override
        public String getTypeOfRodent() {
            return null;
        }

        @Override
        public String getTypeOfVertebrate() {
            return "Mammal";
        }
    }

    /**
     * Defines a test lagomorpha used for testing.
     */
    private static class TestLagomorpha implements Lagomorpha {

        @Override
        public String getTypeOfAnimal() {
            return "Vertebrate";
        }

        @Override
        public String getTypeOfLagomorpha() {
            return null;
        }

        @Override
        public String getTypeOfMammal() {
            return "Placental";
        }

        @Override
        public String getTypeOfPlacental() {
            return "Lagomorpha";
        }

        @Override
        public String getTypeOfVertebrate() {
            return "Mammal";
        }
    }
}
