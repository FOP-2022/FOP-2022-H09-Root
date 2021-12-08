package h09.h1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Defines the test cases for the class {@link FunctionFactory}.
 *
 * @author Nhan Huynh, Darya Nikitina
 */
class TestFunctionFactory {

  /**
   * Tests the method {@link FunctionFactory#createFunctionWithFilterMapAndFold(Traits)} using
   * the following operations:
   *
   * <ul>
   * <li>filter: integers greater than 10
   * <li>map: integer * 3
   * <li>fold: x * y mod 100, where x and y are integers
   * </ul>
   */
  @Test
  void testCreateFunctionWithFilterMapAndFold1() {
    final var length = 10;
    final var integers = new Integer[length];

    for (var i = 0; i < length; i++) {
      integers[i] = i * i;
    }

    Assertions.assertEquals(
      13,
      FunctionFactory.createFunctionWithFilterMapAndFold(new Traits<Integer, Integer, Integer>(
        i -> i > 10,
        i -> i * 3,
        (x, y) -> (x + y) % 100,
        0
      )).apply(integers)
    );
  }

  /**
   * Tests the method {@link FunctionFactory#createFunctionWithFilterMapAndFold(Traits)} using
   * the following operations:
   *
   * <ul>
   * <li>filter: First letter must be capitalized followed by lowercase letters
   * <li>map: Length of string without whitespaces
   * <li>fold: boolean if all strings length are smaller than 3
   * </ul>
   */
  @Test
  void testCreateFunctionWithFilterMapAndFold2() {
    final Traits<String, Integer, Boolean> traits = new Traits<>(
      s -> {
        final var chars = s.toCharArray();
        final var length = chars.length;
        // First letter must be in upper case
        if (length > 0 && Character.isUpperCase(chars[0])) {
          // Letters after the first one must be in lower case
          for (int i = 1; i < length; i++) {
            if (!Character.isLetter(chars[i])) {
              continue;
            }
            if (!Character.isLowerCase(chars[i])) {
              return false;
            }
          }
          return true;
        }
        return false;
      },
      s -> s.replaceAll("\\s", "").length(),
      (acc, length) -> acc && length <= 3,
      true
    );
    final var fct = FunctionFactory.createFunctionWithFilterMapAndFold(traits);


    final String[] stringsTrue = {"Fop", "junit", "test", "i love racket", "E g g", "Cow",
      "java", "racket", "No  ", "yes"
    };

    final String[] stringsFalse = {"Fop", "Junit", "Test", "I love racket", "E g g", "Cow",
      "Java", "Racket", "No  ", "Yes"
    };
    Assertions.assertTrue(fct.apply(stringsTrue));
    Assertions.assertFalse(fct.apply(stringsFalse));
  }

  /**
   * Returns {@code true} if the specified person does not live in Darmstadt.
   *
   * @param person the person to check its postal code
   *
   * @return @code true} if the specified person does not live in Darmstadt
   */
  private static boolean isNotFromDarmstadt(final Person person) {
    return person.getPostalCode()!=64289;
  }

  /**
   * Returns the absolute value of the difference between two numbers.
   *
   * @param a the first number to calculate the distance
   * @param b the second number to calculate the distance
   *
   * @return the absolute value of the difference between two numbers.
   */
  private static int distance(final int a, final int b) {
    return Math.abs(a - b);
  }

  /**
   * Tests the method {@link FunctionFactory#createFunctionWithFilterMapFoldAndCombine(Traits)}
   * using the following operations:
   *
   * <ul>
   * <li>filter: Persons that do not live in Darmstadt
   * <li>map: Postal code of the person
   * <lu>combine: The absolute value of the difference between two postal code
   * <li>fold: The sum of the differences
   * </ul>
   */
  @Test
  void testCreateFunctionWithFilterMapFoldAndCombine() {
    final Traits<Person, Integer, Integer> traits = new Traits<>(
      TestFunctionFactory::isNotFromDarmstadt,
      Person::getPostalCode,
      Integer::sum,
      TestFunctionFactory::distance,
      0
    );

    final var fct = FunctionFactory.createFunctionWithFilterMapFoldAndCombine(traits);

    final Person[] persons = {
      new Person("Rodriguez", "Vanessa", "Amselweg ", 52, 64289),
      new Person("Anderson", "Lise", " Hauptstrasse", 11, 64646),
      new Person("Young", "Joanne", "Oberstrasse", 52, 63225),
      new Person("Travis", "Joe", "Nordstrasse", 1, 60306),
      new Person("Kennedy", "Marc", "Lindenweg", 76, 64807),
      new Person("Sears", "David", "Vulkanstrasse", 22, 64289),
      new Person("Wise", "Marlon", "Charlottenstrasse ", 11, 60306),
      new Person("Hardin", "John", "Goethestrasse ", 5, 64807),
      new Person("Bray", "Keith", "Gartenstrasse ", 13, 64646),
      new Person("Jenkins", "Gilberto", "Im Vogelsang", 46, 64646)
    };
    Assertions.assertEquals(18004, fct.apply(persons));
  }

  /**
   * Represents a person.
   */
  private static class Person {

    /**
     * The last name of this person.
     */
    private String lastName;

    /**
     * The first name of this person.
     */
    private String firstName;

    /**
     * The street where this person lives.
     */
    private String street;

    /**
     * The house number of a street where this person lives.
     */
    private int houseNumber;

    /**
     * The postal code where the person lives and the street is located.
     */
    private int postalCode;

    /**
     * Constructs and initialized a person with its name and location where the person lives.
     *
     * @param lastName    the last name of the person
     * @param firstName   the first name of the person
     * @param street      the street where the person lives
     * @param houseNumber the house number where the person lives
     * @param postalCode  the postal code where the person lives
     */
    public Person(String lastName, String firstName, String street, int houseNumber,
                  int postalCode) {
      this.lastName = lastName;
      this.firstName = firstName;
      this.street = street;
      this.houseNumber = houseNumber;
      this.postalCode = postalCode;
    }

    /**
     * Returns the last name of this person.
     *
     * @return the last name of this person
     */
    public String getLastName() {
      return lastName;
    }

    /**
     * Sets the last name of the person to the specified value.
     *
     * @param lastName the new last name of the person
     */
    public void setLastName(String lastName) {
      this.lastName = lastName;
    }

    /**
     * Returns the first name of this person.
     *
     * @return the first name of this person
     */
    public String getFirstName() {
      return firstName;
    }

    /**
     * Sets the first name of the person to the specified value.
     *
     * @param firstName the new first name of the person
     */
    public void setFirstName(String firstName) {
      this.firstName = firstName;
    }

    /**
     * Returns the street where this person lives.
     *
     * @return the street where this person lives
     */
    public String getStreet() {
      return street;
    }

    /**
     * Sets the street where the person lives to the specified value.
     *
     * @param street the new street where the person should live
     */
    public void setStreet(String street) {
      this.street = street;
    }

    /**
     * Returns the house number of a street where this person lives
     *
     * @return the house number of a street where this person lives
     */
    public int getHouseNumber() {
      return houseNumber;
    }

    /**
     * Sets the house number of a street where the person lives to the specified value.
     *
     * @param houseNumber the new house number where the person lives
     */
    public void setHouseNumber(int houseNumber) {
      this.houseNumber = houseNumber;
    }

    /**
     * The postal code where the person lives and the street is located.
     *
     * @return postal code where the person lives and the street is located
     */
    public int getPostalCode() {
      return postalCode;
    }

    /**
     * Sets the postal code where the person lives to the specified value.
     *
     * @param postalCode the new postal code where the person lives
     */
    public void setPostalCode(int postalCode) {
      this.postalCode = postalCode;
    }
  }
}
