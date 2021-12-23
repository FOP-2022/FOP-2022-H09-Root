package h09.h2;

import java.util.ArrayList;
import java.util.List;

/**
 * A biological animal hierarchy which allows interacting with the animals
 */
public class BiologyHierarchy {

  /**
   * Returns the type of the specified vertebrate
   *
   * @param vertebrate the vertebrate to return its type
   * @param <T>        the type of the vertebrate
   *
   * @return the type of the specified vertebrate
   */
  public <T extends Vertebrate> String typeOfVertebrate(final T vertebrate) {
    return vertebrate.typeOfVertebrate();
  }

  /**
   * Returns a list which contains only lagomorphs from the specified list.
   *
   * @param list the list to be filtered by lagomorphs
   *
   * @return a list which contains only lagomorphs from the specified list
   */
  public List<Lagomorpha> returnAsLagomorphs(final List<? super Lagomorpha> list) {
    final List<Lagomorpha> lagomorphs = new ArrayList<>();
    for (final var o : list) {
      if (o instanceof Lagomorpha) {
        lagomorphs.add((Lagomorpha) o);
      }
    }
    return lagomorphs;
  }

  /**
   * Returns a list which contains the types of the mammals.
   *
   * @param mammals the list in which the types of mammals should be extracted
   *
   * @return a list which contains the types of the mammals
   */
  public List<String> typeOfMammals(final List<? extends Mammal> mammals) {
    final List<String> types = new ArrayList<>();
    for (final var mammal : mammals) {
      types.add(mammal.typeOfMammal());
    }
    return types;
  }
}
