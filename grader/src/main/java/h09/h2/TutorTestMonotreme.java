package h09.h2;

/**
 * Defines a dummy {@code Monotreme} used for testing.
 *
 * @author Nhan Huynh, Darya Nikitibna
 */
public final class TutorTestMonotreme implements Monotreme {

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
