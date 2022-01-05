package h09.h2;

/**
 * Defines a dummy {@code Lagomorpha} used for testing.
 *
 * @author Nhan Huynh, Darya Nikitibna
 */
public final class TutorTestLagomorpha implements Lagomorpha {

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
    return "Lagomorph";
  }

  @Override
  public String getTypeOfVertebrate() {
    return "Mammal";
  }
}
