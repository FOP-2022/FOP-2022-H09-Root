package h09.h2;

/**
 * Defines a dummy {@code Rodent} used for testing.
 *
 * @author Nhan Huynh, Darya Nikitibna
 */
public final class TutorTestRodent implements Rodent {

    @Override
    public String getTypeOfAnimal() {
        return "Vertebrate";
    }

    @Override
    public String getTypeOfMammal() {
        return "Placental";
    }

    @Override
    public String getTypeOfPlacental() {
        return "Rodent";
    }

    @Override
    public String getTypeOfRodent() {
        return null;
    }

    @Override
    public String getTypeOfVertebrate() {
        return "Mammal";
    }
}
