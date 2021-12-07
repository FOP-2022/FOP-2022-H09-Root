package h09.h2;

/**
 * Defines a dummy {@code Bird} used for testing.
 *
 * @author Nhan Huynh, Darya Nikitibna
 */
public final class TutorTestBird implements Bird {

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
