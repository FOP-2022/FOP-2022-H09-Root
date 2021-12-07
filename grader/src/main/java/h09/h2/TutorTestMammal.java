package h09.h2;

/**
 * Defines a dummy {@code Mammal} used for testing.
 *
 * @author Nhan Huynh, Darya Nikitibna
 */
public final class TutorTestMammal implements Mammal {

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
