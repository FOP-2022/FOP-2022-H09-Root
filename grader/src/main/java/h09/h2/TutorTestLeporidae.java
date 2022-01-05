package h09.h2;

/**
 * Defines a dummy {@code Leporidae} used for testing.
 *
 * @author Nhan Huynh, Darya Nikitibna
 */
public final class TutorTestLeporidae implements Leporidae {

  @Override
  public String getTypeOfAnimal() {
    return "Vertebrate";
  }

  @Override
  public String getTypeOfVertebrate() {
    return "Mammal";
  }

  @Override
  public String getTypeOfMammal() {
    return "Placental";
  }

  @Override
  public String getTypeOfPlacental() {
    return "Lagomorph";
  }

  @Override
  public String getTypeOfLagomorpha() {
    return "Leporid";
  }

  @Override
  public String getNameOfIndividualeOfVertebrate() {
    return "Tutor Test Leporid";
  }
}
