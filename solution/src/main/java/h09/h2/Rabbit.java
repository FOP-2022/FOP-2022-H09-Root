package h09.h2;

public class Rabbit implements Leporidae {

  private static int ID = 1;

  private final int id;

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
  public String typeOfLagomorph() {
    return "Leporidae";
  }

  @Override
  public String nameOfIndividual() {
    return String.valueOf(id);
  }
}
