//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P09 Chugidex
// Course: CS 300 Fall 2022
//
// Author: Cole Bielby
// Email: cbielby@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: (name of your pair programming partner)
// Partner Email: (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: None
// Online Sources: None
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This class models the Chugimon data type.
 * 
 * @author Cole Bielby
 *
 */
public class Chugimon implements Comparable<Chugimon> {
  private final int FIRST_ID; // The first ID of the Chugimon
  private final int SECOND_ID; // The second ID of the Chugimon
  private final double HEIGHT; // The height of the Chugimon in meters
  private final double WEIGHT; // The weight of the Chugimon in kilograms
  static final int MAX_ID = 151; // The maximum ID number
  static final int MIN_ID = 1; // The minimum ID number
  private final String NAME; // The name of the Chugimon
  private final ChugiType PRIMARY_TYPE; // The primary type of the Chugimon; cannot be null; cannot
                                        // be the same as the secondary type
  private final ChugiType SECONDARY_TYPE; // The secondary type of the Chugimon; may be null; cannot
                                          // be the same as the primary type

  /**
   * Creates a new Chugimon with specific first and second IDs and initializes all its data fields
   * accordingly.
   * 
   * @param firstID  the first ID of the Chugimon, between 1-151
   * @param secondID the second ID of the Chugimon, between 1-151
   * @throws IllegalArgumentException if the first and second ID are out of bounds or equal to each
   *                                  other.
   */
  public Chugimon(int firstID, int secondID) throws IllegalArgumentException {
    if (firstID > MAX_ID || firstID < MIN_ID || secondID > MAX_ID || secondID < MIN_ID
        || firstID == secondID) {
      throw new IllegalArgumentException("IDs must be within 1-151 and cannot be equal");
    }
    this.FIRST_ID = firstID;
    this.SECOND_ID = secondID;
    // Utilizes ChugidexUtility class to get data based on IDs
    this.NAME = ChugidexUtility.getChugimonName(FIRST_ID, SECOND_ID);
    this.HEIGHT = ChugidexUtility.getChugimonHeight(FIRST_ID, SECOND_ID);
    this.WEIGHT = ChugidexUtility.getChugimonWeight(FIRST_ID, SECOND_ID);
    this.PRIMARY_TYPE = ChugidexUtility.getChugimonTypes(FIRST_ID, SECOND_ID)[0];
    this.SECONDARY_TYPE = ChugidexUtility.getChugimonTypes(FIRST_ID, SECOND_ID)[1];
  }

  /**
   * Gets the name of this Chugimon
   * 
   * @return the name of the Chugimon
   */
  public String getName() {
    return this.NAME;
  }

  /**
   * Gets the first ID of this Chugimon
   * 
   * @return the first ID of the Chugimon
   */
  public int getFirstID() {
    return this.FIRST_ID;
  }

  /**
   * Gets the second ID of thid Chugimon
   * 
   * @return the second ID of the Chugimon
   */
  public int getSecondID() {
    return this.SECOND_ID;
  }

  /**
   * Gets the primary type of this Chugimon
   * 
   * @return the primary type of the Chugimon
   */
  public ChugiType getPrimaryType() {
    return this.PRIMARY_TYPE;
  }

  /**
   * Gets the secondary type of this Chugimon
   * 
   * @return the secondary type of the Chugimon
   */
  public ChugiType getSecondaryType() {
    return this.SECONDARY_TYPE;
  }

  /**
   * Gets the height of this Chugimon
   * 
   * @return the height of the Chugimon
   */
  public double getHeight() {
    return this.HEIGHT;
  }

  /**
   * Gets the the weight of the Chugimon.
   * 
   * @return the weight of the Chugimon.
   */
  public double getWeight() {
    return this.WEIGHT;
  }

  /**
   * Determines the ordering of Chugimon. Chugimon are ordered by: 1) name (alphabetical) 2) the
   * first ID (if name is equal). The one with the smaller first ID is less than the other. 3) the
   * second ID (if name and first ID are equal). The one with the smaller second ID is less than the
   * other. A Chugimon with identical #1-3 are considered equal.
   * 
   * @param otherChugi the other Chugimon to compare this Chugimon to
   * @return a negative int if this Chugimon is less than other, a positive int if this Chugimon is
   *         greater than other, or 0 if this and the other Chugimon are equal.
   * @see Comparable.compareTo(java.lang.Object)
   */
  @Override
  public int compareTo(Chugimon otherChugi) {
    if (this.NAME.equals(otherChugi.getName())) {
      if (this.FIRST_ID == otherChugi.getFirstID()) {
        if (this.SECOND_ID == otherChugi.getSecondID()) {
          // If they are equal
          return 0;
        }
        // If their names and first IDs are equal (but second IDs are not)
        if (this.SECOND_ID > otherChugi.getSecondID()) {
          return 1;
        }
        if (this.SECOND_ID < otherChugi.getSecondID()) {
          return -1;
        }
      }
      // If their names are equal (but first IDs are not)
      if (this.FIRST_ID > otherChugi.getFirstID()) {
        return 1;
      }
      if (this.FIRST_ID < otherChugi.getFirstID()) {
        return -1;
      }
    }
    // If their names are not equal
    return this.NAME.compareTo(otherChugi.getName());
  }

  /**
   * A Chugimon's String representation is its name followed by "#FIRST_ID.SECOND_ID"
   * 
   * @return a String representation of this Chugimon
   * @see Object.toString()
   */
  @Override
  public String toString() {
    return this.NAME + "#" + this.FIRST_ID + "." + this.SECOND_ID;
  }

  /**
   * Equals method for Chugimon. This Chugimon equals another object if other is a Chugimon with the
   * exact same name, and their both first and second IDs match.
   * 
   * @param other Object to determine equality against this Chugimon
   * @return true if this Chugimon and other Object are equal, false otherwise
   */
  @Override
  public boolean equals(Object other) {
    if (other instanceof Chugimon && this.FIRST_ID == ((Chugimon) other).getFirstID()
        && this.SECOND_ID == ((Chugimon) other).getSecondID()) {
      return true;
    }
    return false; // Only reached if not equal
  }
}
