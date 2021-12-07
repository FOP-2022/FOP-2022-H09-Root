package h09.h2;

/**
 * Represents a rabbit.
 *
 * @author Nhan Huynh, Darya Nikitina
 */
public class Rabbit implements Leporidae {

  /**
   * The next available ID for a rabbit.
   */
  private static int ID = 1;

  /**
   * The ID of the rabbit which also acts as its name.
   */
  private final int id;

  /**
   * Constructs and initialized rabbit with its ID.
   */
  public Rabbit() {
    id = ID++;
  }

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
    return "Lagomorpha";
  }

  @Override
  public String typeOfLagomorpha() {
    return "Leporidae";
  }

  @Override
  public String nameOfIndividual() {
    return String.valueOf(id);
  }
}
