package h09.h2;

/**
 * Defines a dummy {@code Monotreme} used for testing.
 *
 * @author Nhan Huynh, Darya Nikitibna
 */
public final class TutorTestMonotreme implements Monotreme {

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
