package h09.h2;

/**
 * Defines a dummy {@code Placental} used for testing.
 *
 * @author Nhan Huynh, Darya Nikitibna
 */
public final class TutorTestPlacental implements Placental {

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
        return null;
    }

    @Override
    public String getTypeOfVertebrate() {
        return "Mammal";
    }
}
