package h09.h2;

/**
 * Defines a dummy {@code Vertebrate} used for testing.
 *
 * @author Nhan Huynh, Darya Nikitibna
 */
public final class TutorTestVertebrate implements Vertebrate {

    @Override
    public String getTypeOfAnimal() {
        return "Vertebrate";
    }

    @Override
    public String getTypeOfVertebrate() {
        return null;
    }
}
