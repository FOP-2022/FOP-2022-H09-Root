package h09.h2;

/**
 * Defines a dummy {@code Placental} used for testing.
 *
 * @author Nhan Huynh, Darya Nikitibna
 */
public final class TutorTestPlacental implements Placental {

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
