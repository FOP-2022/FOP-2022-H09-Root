package h09.h2;

/**
 * Defines a dummy {@code Mammal} used for testing.
 *
 * @author Nhan Huynh, Darya Nikitibna
 */
public final class TutorTestMammal implements Mammal {

    @Override
    public String getTypeOfAnimal() {
        return "Vertebrate";
    }

    @Override
    public String getTypeOfMammal() {
        return null;
    }

    @Override
    public String getTypeOfVertebrate() {
        return "Mammal";
    }
}
