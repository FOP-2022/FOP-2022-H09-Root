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
class TestBiologyHierarchy {

  /**
   * The instance of a biology hierarchy used to test the methods.
   */
  private final BiologyHierarchy hierarchy = new BiologyHierarchy();

  /**
   * Tests whether the method {@link BiologyHierarchy#typeOfVertebrate(Vertebrate)} returns
   * the correct string.
   */
  @Test
  void testTypeOfVertebrate() {
    Assertions.assertNull(hierarchy.typeOfVertebrate(new TestVertebrate()));
    Assertions.assertEquals("Mammal", hierarchy.typeOfVertebrate(new TestMammal()));
    Assertions.assertEquals("Bird", hierarchy.typeOfVertebrate(new TestBird()));
    Assertions.assertEquals("Mammal", hierarchy.typeOfVertebrate(new TestPlacental()));
    Assertions.assertEquals("Mammal", hierarchy.typeOfVertebrate(new TestMonotreme()));
    Assertions.assertEquals("Mammal", hierarchy.typeOfVertebrate(new TestRodent()));
    Assertions.assertEquals("Mammal", hierarchy.typeOfVertebrate(new TestLagomorpha()));
  }

  /**
   * Tests whether the method {@link BiologyHierarchy#returnAsLagomorphs(List)} returns
   * the correct animals.
   */
  @Test
  void testReturnAsLagomorphs() {
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

    Assertions.assertEquals(expected, hierarchy.returnAsLagomorphs(lp));
    Assertions.assertEquals(expected, hierarchy.returnAsLagomorphs(lm));
    Assertions.assertEquals(expected, hierarchy.returnAsLagomorphs(lv));
    Assertions.assertEquals(expected, hierarchy.returnAsLagomorphs(la));
    Assertions.assertEquals(expected, hierarchy.returnAsLagomorphs(lo));
  }


  /**
   * Tests whether the method {@link BiologyHierarchy#typeOfMammals(List)} returns
   * the correct outputs of the method calls {@link Mammal#typeOfMammal()}
   */
  @Test
  void testTypeOfMammals() {
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
      em.add(m.typeOfMammal());
    }
    final var ep = new ArrayList<String>();
    for (final var m : lp) {
      ep.add(m.typeOfMammal());
    }
    final var emo = new ArrayList<String>();
    for (final var m : lmo) {
      emo.add(m.typeOfMammal());
    }
    final var el = new ArrayList<String>();
    for (final var m : ll) {
      el.add(m.typeOfMammal());
    }
    final var er = new ArrayList<String>();
    for (final var m : lr) {
      er.add(m.typeOfMammal());
    }
    final var ele = new ArrayList<String>();
    for (final var m : lle) {
      ele.add(m.typeOfMammal());
    }

    Assertions.assertEquals(em, hierarchy.typeOfMammals(lm));
    Assertions.assertEquals(ep, hierarchy.typeOfMammals(lp));
    Assertions.assertEquals(emo, hierarchy.typeOfMammals(lmo));
    Assertions.assertEquals(el, hierarchy.typeOfMammals(ll));
    Assertions.assertEquals(er, hierarchy.typeOfMammals(lr));
    Assertions.assertEquals(ele, hierarchy.typeOfMammals(lle));
  }

  /**
   * Defines a test animal used for testing.
   */
  private static class TestAnimal implements Animal {

    @Override
    public String typeOfAnimal() {
      return "Animal";
    }
  }

  /**
   * Defines a test vertebrate used for testing.
   */
  private static class TestVertebrate implements Vertebrate {

    @Override
    public String typeOfAnimal() {
      return "Vertebrate";
    }

    @Override
    public String typeOfVertebrate() {
      return null;
    }
  }

  /**
   * Defines a test mammal used for testing.
   */
  private static class TestMammal implements Mammal {

    @Override
    public String typeOfAnimal() {
      return "Vertebrate";
    }

    @Override
    public String typeOfMammal() {
      return null;
    }

    @Override
    public String typeOfVertebrate() {
      return "Mammal";
    }
  }

  /**
   * Defines a test bird used for testing.
   */
  private static class TestBird implements Bird {

    @Override
    public String typeOfAnimal() {
      return "Vertebrate";
    }

    @Override
    public String typeOfBird() {
      return null;
    }

    @Override
    public String typeOfVertebrate() {
      return "Bird";
    }
  }

  /**
   * Defines a test placental used for testing.
   */
  private static class TestPlacental implements Placental {

    @Override
    public String typeOfAnimal() {
      return "Vertebrate";
    }

    @Override
    public String typeOfMammal() {
      return "Placental";
    }

    @Override
    public String typeOfPlacental() {
      return null;
    }

    @Override
    public String typeOfVertebrate() {
      return "Mammal";
    }
  }

  /**
   * Defines a test monotreme used for testing.
   */
  private static class TestMonotreme implements Monotreme {

    @Override
    public String typeOfAnimal() {
      return "Vertebrate";
    }

    @Override
    public String typeOfMammal() {
      return "Monotreme";
    }

    @Override
    public String typeOfMonotreme() {
      return null;
    }

    @Override
    public String typeOfVertebrate() {
      return "Mammal";
    }
  }

  /**
   * Defines a test rodent used for testing.
   */
  private static class TestRodent implements Rodent {

    @Override
    public String typeOfAnimal() {
      return "Vertebrate";
    }

    @Override
    public String typeOfMammal() {
      return "Placental";
    }

    @Override
    public String typeOfPlacental() {
      return "Rodent";
    }

    @Override
    public String typeOfRodent() {
      return null;
    }

    @Override
    public String typeOfVertebrate() {
      return "Mammal";
    }
  }

  /**
   * Defines a test lagomorpha used for testing.
   */
  private static class TestLagomorpha implements Lagomorpha {

    @Override
    public String typeOfAnimal() {
      return "Vertebrate";
    }

    @Override
    public String typeOfLagomorpha() {
      return null;
    }

    @Override
    public String typeOfMammal() {
      return "Placental";
    }

    @Override
    public String typeOfPlacental() {
      return "Lagomorpha";
    }

    @Override
    public String typeOfVertebrate() {
      return "Mammal";
    }
  }
}
