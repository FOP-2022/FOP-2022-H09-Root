package h09.h2;

/**
 * Defines a dummy {@code Leporidae} used for testing.
 *
 * @author Nhan Huynh, Darya Nikitibna
 */
public final class TutorTestLeporidae implements Leporidae {

  @Override
  public String typeOfAnimal() {
    return "Vertebrate";
  }

  @Override
  public String typeOfVertebrate() {
    return "Mammal";
  }

  @Override
  public String typeOfMammal() {
    return "Placental";
  }

  @Override
  public String typeOfPlacental() {
    return "Lagomorph";
  }

  @Override
  public String typeOfLagomorpha() {
    return "Leporid";
  }

  @Override
  public String nameOfIndividual() {
    return "Tutor Test Leporid";
  }
}
