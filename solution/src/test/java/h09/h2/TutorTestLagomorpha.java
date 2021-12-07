package h09.h2;

/**
 * Defines a dummy {@code Lagomorpha} used for testing.
 *
 * @author Nhan Huynh, Darya Nikitibna
 */
public final class TutorTestLagomorpha implements Lagomorpha {

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
    return "Lagomorph";
  }

  @Override
  public String typeOfVertebrate() {
    return "Mammal";
  }
}
