package h09.h2;

/**
 * Defines a dummy {@code Rodent} used for testing.
 *
 * @author Nhan Huynh, Darya Nikitibna
 */
public final class TutorTestRodent implements Rodent {

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
