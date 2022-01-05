package h09.h2;

/**
 * Defines a dummy {@code Bird} used for testing.
 *
 * @author Nhan Huynh, Darya Nikitibna
 */
public final class TutorTestBird implements Bird {

  @Override
  public String getTypeOfAnimal() {
    return "Vertebrate";
  }

  @Override
  public String getTypeOfBird() {
    return null;
  }

  @Override
  public String getTypeOfVertebrate() {
    return "Bird";
  }
}
