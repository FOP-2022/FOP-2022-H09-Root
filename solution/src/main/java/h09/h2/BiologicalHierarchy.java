package h09.h2;

import java.util.ArrayList;
import java.util.List;

public class BiologicalHierarchy {

  public <T extends Vertebrate> String typeOfVertebrate(T vertebrate) {
    return vertebrate.typeOfVertebrate();
  }

  public List<Lagomorpha> returnAsLagomorphs(List<? super Lagomorpha> list) {
    final List<Lagomorpha> lagomorphs = new ArrayList<>();
    for (final var o : list) {
      if (o instanceof Lagomorpha) {
        lagomorphs.add((Lagomorpha) o);
      }
    }
    return lagomorphs;
  }

  public List<String> typeOfMammals(List<? extends Mammal> mammals) {
    final List<String> types = new ArrayList<>();
    for (final var mammal : mammals) {
      types.add(mammal.typeOfMammal());
    }
    return types;
  }
}
