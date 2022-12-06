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

import java.util.NoSuchElementException;

/**
 * This class checks the correctness of the implementation of the methods defined in the Chugimon
 * and ChugiTree classes.
 * 
 * @author TODO add your name(s)
 *
 */
public class ChugidexTester {


  /**
   * Checks the correctness of the implementation of both compareTo() and equals() methods defined
   * in the Chugimon class.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testChugimonCompareToEquals() {
    Chugimon mon1 = new Chugimon(1, 55);
    Chugimon mon2 = new Chugimon(1, 55); // Should be equal to mon1
    Chugimon mon3 = new Chugimon(62, 133); // Should not be equal to mon1 or mon2
    Chugimon mon4 = new Chugimon(33, 110);


    // 1) Tests two mons that are equal
    if (!mon1.equals(mon2)) {
      System.out.println("Chugimon.equals() failed to return true with two different mons with "
          + "the same ID nums");
      return false;
    }

    // 2) Tests two mons that are not equal
    if (mon2.equals(mon3)) {
      System.out.println("Chugimon.equals() failed to return false when given two different mons");
      return false;
    }
    // Used to find names for comparisons
    /*
     * System.out.println("mon1 name:" + mon1.getName()); // Bulbduck
     * System.out.println("mon3 name:" + mon3.getName()); // Polivee System.out.println("mon4 name:"
     * + mon4.getName()); // Nidozing
     */

    // 3) Tests compareTo for different names (less)
    if (mon1.compareTo(mon3) > -1) {
      System.out.println("Chugimon.compareTo() failed to return a negative when comparing a name "
          + "starting w B to a name starting with P");
      return false;
    }

    // 4) Tests compareTo for different names (more)
    if (mon4.compareTo(mon1) < 1) {
      System.out.println("Chugimon.compareTo() failed to return a positive when comparing a name "
          + "starting w N to a name starting with B");
      return false;
    }

    // 5) Tests compareTo for equal mons
    if (mon1.compareTo(mon2) != 0) {
      System.out.println("Chugimon.compareTo() failed to return 0 when comparing equal mons");
      return false;
    }

    return true;
  }

  /**
   * Checks the correctness of the implementation of Chugimon.toString() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testChugimonToString() {
    Chugimon mon1 = new Chugimon(1, 55);
    Chugimon mon2 = new Chugimon(62, 133);
    String expectedString1 = mon1.getName() + "#" + mon1.getFirstID() + "." + mon1.getSecondID();
    String expectedString2 = mon2.getName() + "#" + mon2.getFirstID() + "." + mon2.getSecondID();

    // 1) Tests mon1
    if (!mon1.toString().equals(expectedString1)) {
      System.out.println("Chugimon.toString() failed test 1");
      return false;
    }

    // 2) Tests mon2
    if (!mon2.toString().equals(expectedString2)) {
      System.out.println("Chugimon.toString() failed test 2");
      return false;
    }

    return true;
  }

  /**
   * Checks the correctness of the implementation of ChugiTree.isValidBSTHelper() method. This
   * tester should consider at least three scenarios. (1) An empty tree whose root is null should be
   * a valid BST. (2) Consider a valid BST whose height is at least 3. Create the tree using the
   * constructors of the BSTNode and its setters methods, (3) Consider a NON-valid BST where the
   * search order property is violated at at least one internal node.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testIsValidBSTHelper() {

    // 1) Testing empty tree (null input)
    if (ChugiTree.isValidBSTHelper(null) != true) {
      System.out
          .println("ChugiTree.isValidBSTHelper() did not return true when passed an empty tree");
      return false;
    }


    // 2) Testing a valid BST
    Chugimon one = new Chugimon(15, 1); // Beeasaur
    Chugimon two = new Chugimon(10, 1); // Caterasaur
    Chugimon three = new Chugimon(36, 1); // Clefasaur
    Chugimon four = new Chugimon(2, 1); // Ivyasaur
    Chugimon five = new Chugimon(30, 1); // Nidoasaur
    Chugimon six = new Chugimon(25, 1); // Pikaasaur
    Chugimon seven = new Chugimon(57, 1); // Primeasaur
    Chugimon eight = new Chugimon(20, 1); // Ratasaur
    Chugimon nine = new Chugimon(45, 1); // Vileasaur


    BSTNode root = new BSTNode(five);
    BSTNode left1 = new BSTNode(three);
    BSTNode right1 = new BSTNode(eight);
    root.setLeft(left1);
    root.setRight(right1); // Root now has left and right

    BSTNode leftLeft = new BSTNode(two);
    BSTNode leftRight = new BSTNode(four);
    left1.setLeft(leftLeft);
    left1.setRight(leftRight); // Left (height 2) now has left and right

    BSTNode rightLeft = new BSTNode(six);
    BSTNode rightRight = new BSTNode(nine);
    right1.setLeft(rightLeft);
    right1.setRight(rightRight); // Right (height 2) now has left and right

    leftLeft.setLeft(new BSTNode(one)); // Creates height 4 (leftmost node)
    rightLeft.setRight(new BSTNode(seven)); // Another height 4

    if (!ChugiTree.isValidBSTHelper(root)) {
      System.out
          .println("ChugiTree.isValidBSTHelper() did not return true when given a valid tree");
      return false;
    }

    // 3) Tests an invalid tree (locally wrong)
    leftLeft.setLeft(new BSTNode(nine)); // Changes height 4 left node into a higher val than parent

    if (ChugiTree.isValidBSTHelper(root)) {
      System.out.println("ChugiTree.isValidBSTHelper() did not return false when given an out "
          + "of order tree (locally invalid)");
      return false;
    }

    // 4) Tests an invalid tree (not locally) with all local checks valid
    leftLeft.setLeft(new BSTNode(one));
    BSTNode right2 = new BSTNode(seven);
    BSTNode right2Left = new BSTNode(six);
    BSTNode right2Right = new BSTNode(eight);
    root.setRight(right2);
    right2.setLeft(right2Left);
    right2.setRight(right2Right);
    right2Left.setRight(new BSTNode(nine));

    if (ChugiTree.isValidBSTHelper(root)) {
      System.out.println("ChugiTree.isValidBSTHelper() did not return false when given a tree "
          + "with incorrect non-local nodes");
      return false;
    }

    return true;
  }

  /**
   * Checks the correctness of the implementation of both add() and toString() methods implemented
   * in the ChugiTree class. This unit test considers at least the following scenarios. (1) Create a
   * new empty ChugiTree, and check that its size is 0, it is empty, and that its string
   * representation is an empty string "". (2) try adding one Chugimon and then check that the add()
   * method call returns true, the tree is not empty, its size is 1, and the toString() called on
   * the tree returns the expected output. (3) Try adding another Chugimon which is less than the
   * Chugimon at the root, (4) Try adding a third Chugimon which is greater than the one at the
   * root, (5) Try adding at least two further Chugimons such that one must be added at the left
   * subtree, and the other at the right subtree. For all the above scenarios, and more, double
   * check each time that size() method returns the expected value, the add method call returns
   * true, that the ChugiTree is a valid BST, and that the toString() method returns the expected
   * string representation of the contents of the binary search tree in an increasing order from the
   * smallest to the greatest Chugimon. (6) Try adding a Chugimon already stored in the tree. Make
   * sure that the add() method call returned false, and that the size of the tree did not change.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddToStringSize() {
    ChugiTree testTree = new ChugiTree();

    // 1) Tests size, isEmpty(), and toString() on empty tree
    if (testTree.size() != 0) {
      System.out.println("ChugiTree.size() did not return 0 for an empty tree");
      return false;
    }
    if (!testTree.isEmpty()) {
      System.out.println("ChugiTree.isEmpty() did not retunr true for an empty tree");
      return false;
    }
    if (!testTree.toString().equals("")) {
      System.out.println("ChugiTree.toString() did not return an empty string for an empty tree");
      return false;
    }

    // 2) Tests adding one Chugimon and tests size, isEmpty(), and toString() with one chugimon
    Chugimon five = new Chugimon(30, 1); // Nidoasaur
    if (!testTree.add(five)) {
      System.out.println("ChugiTree.add() returned false when adding a Chugimon to an empty tree");
      return false;
    }
    if (testTree.size() != 1) {
      System.out.println("ChugiTree.size() did not return 1 with a tree with only a root");
      return false;
    }
    if (testTree.isEmpty()) {
      System.out.println("ChugiTree.isEmpty() returned true when the tree had a root node");
      return false;
    }
    if (!testTree.toString().equals(five.toString())) {
      System.out.println(testTree.toString());
      System.out
          .println("ChugiTree.toString() did not return the correct string with only a root node");
      return false;
    }

    // 3) Adding a Chugimon less than root
    Chugimon three = new Chugimon(36, 1); // Clefasaur
    if (!testTree.add(three)) {
      System.out
          .println("ChugiTree.add() returned false when adding the first Chugimon less than root");
      return false;
    }
    if (testTree.size() != 2) {
      System.out.println("ChugiTree.size() did not return 2 for test area 3");
      return false;
    }
    if (!testTree.toString().equals(three.toString() + "," + five.toString())) {
      System.out.println("ChugiTree.toString() did not return the correct string in test area 3");
      return false;
    }
    if (!testTree.isValidBST()) {
      System.out.println("ChugiTree.isValidBST() failed for test area 3");
      return false;
    }

    // 4) Adding a Chugimon greater than the root
    Chugimon eight = new Chugimon(20, 1); // Ratasaur
    if (!testTree.add(eight)) {
      System.out.println(
          "ChugiTree.add() returned false when adding the first Chugimon greater than root");
      return false;
    }
    if (testTree.size() != 3) {
      System.out.println("ChugiTree.size() did not return 3 with 3 nodes (test area 4)");
      return false;
    }
    if (!testTree.toString()
        .equals(three.toString() + "," + five.toString() + "," + eight.toString())) {
      System.out.println("ChugiString.toString() did not return the correct toString() with the "
          + "3 node tree (test area 4)");
      return false;
    }
    if (!testTree.isValidBST()) {
      System.out.println("ChugiTree.isValidBST() failed in test area 4");
      return false;
    }

    // 5) Adding two new Chugimon (one to left, one to right)
    Chugimon nine = new Chugimon(45, 1); // Vileasaur
    Chugimon four = new Chugimon(2, 1); // Ivyasaur
    if (!testTree.add(four)) {
      System.out.println("ChugiTree.add() returned false when adding test area 5.1");
      return false;
    }
    if (!testTree.add(nine)) {
      System.out.println("ChugiTree.add() returned false when adding test area 5.2");
      return false;
    }
    if (testTree.size() != 5) {
      System.out.println("ChugiTree.size() did not return 5 with 5 nodes (test area 5)");
      return false;
    }
    if (!testTree.toString().equals(three.toString() + "," + four.toString() + "," + five.toString()
        + "," + eight.toString() + "," + nine.toString())) {
      System.out.println("ChugiString.toString() did not return the correct toString() with the "
          + "5 node tree (test area 5)");
      return false;
    }
    if (!testTree.isValidBST()) {
      System.out.println("ChugiTree.isValidBST() failed in test area 5");
      return false;
    }

    // 6) Attempt to add chugimon that already exists
    if (testTree.add(eight)) {
      System.out.println("testTree() incorrectly added a Chugimon already within the tree");
      return false;
    }

    return true;
  }

  /**
   * This method checks mainly for the correctness of the ChugiTree.lookup() method. It must
   * consider at least the following test scenarios. (1) Create a new ChugiTree. Then, check that
   * calling the lookup() method on an empty ChugiTree returns false. (2) Consider a ChugiTree of
   * height 3 which contains at least 5 Chugimons. Then, try to call lookup() method to search for a
   * Chugimon having a match at the root of the tree. (3) Then, search for a Chugimon at the right
   * and left subtrees at different levels considering successful and unsuccessful search
   * operations. Make sure that the lookup() method returns the expected output for every method
   * call.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testLookup() {
    ChugiTree testTree = new ChugiTree();
    Chugimon one = new Chugimon(15, 1); // Beeasaur
    Chugimon two = new Chugimon(10, 1); // Caterasaur
    Chugimon three = new Chugimon(36, 1); // Clefasaur
    Chugimon four = new Chugimon(2, 1); // Ivyasaur
    Chugimon five = new Chugimon(30, 1); // Nidoasaur
    Chugimon six = new Chugimon(25, 1); // Pikaasaur

    // 1) Tests lookup() on empty tree
    if (testTree.lookup(5, 10) != null) {
      System.out.println("ChugiTree.lookup() did not return null when looking thru empty tree");
      return false;
    }

    // 2) Looking up root (height 3 size 6)
    testTree.add(three);
    testTree.add(two);
    testTree.add(one);
    testTree.add(five);
    testTree.add(four);
    testTree.add(six);
    try {
      if (!testTree.lookup(36, 1).equals(three)) {
        System.out.println("ChugiTree.lookup() did not return the correct Chugimon given valid "
            + "IDs (test 2) ie the root");
        return false;
      }
    } catch (NullPointerException e) {
      System.out.println("ChugiTree.lookup() threw an unexpected NullP.Ex. (test 2)");
      return false;
    }

    // 3) Extra testing
    try {
      if (!testTree.lookup(2, 1).equals(four)) {
        System.out.println(
            "ChugiTree.lookup() did not return the correct Chugimon given valid IDs (test 3.1)");
        return false;
      }
      if (!testTree.lookup(15, 1).equals(one)) {
        System.out.println(
            "ChugiTree.lookup() did not return the correct Chugimon given valid IDs (test 3.2)");
        return false;
      }
      if (!testTree.lookup(30, 1).equals(five)) {
        System.out.println(
            "ChugiTree.lookup() did not return the correct Chugimon given valid IDs (test 3.3)");
        return false;
      }
    } catch (NullPointerException e) {
      System.out.println("ChugiTree.lookup() threw an unexpected NullP.Ex. (test 3)");
      return false;
    }
    if (testTree.lookup(25, 30) != null) {
      System.out.println("ChugiTree.lookup() did not return null when given invalid IDs (test 3)");
      return false;
    }

    return true;
  }

  /**
   * Checks for the correctness of ChugiTree.countType() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testCountType() {
    ChugiTree testTree = new ChugiTree();
    Chugimon one = new Chugimon(15, 1); // Beeasaur TYPES: BUG, GRASS
    Chugimon two = new Chugimon(10, 1); // Caterasaur TYPES: BUG, GRASS
    Chugimon three = new Chugimon(36, 1); // Clefasaur TYPES: NORMAL, GRASS
    Chugimon four = new Chugimon(2, 1); // Ivyasaur TYPES: GRASS, POISON
    Chugimon five = new Chugimon(30, 1); // Nidoasaur TYPES: POISON, GRASS
    Chugimon six = new Chugimon(25, 1); // Pikaasaur TYPES: ELECTRIC, GRASS
    Chugimon oneType = new Chugimon(24, 30); // TYPES: POISON, null
    // Bug: 2 --- Grass: 6 --- Electric: 1 --- Dragon: 0

    // 1) Tests that a empty tree returns 0
    if (testTree.countType(ChugiType.BUG) != 0) {
      System.out.println("ChugiTree.countType() did not return 0 for an empty tree");
      return false;
    }

    // 2) Tests counting for various types
    testTree.add(three);
    testTree.add(two);
    testTree.add(one);
    testTree.add(five);
    testTree.add(four);
    testTree.add(six);
    testTree.add(oneType);
    /*
     * System.out.println("one: " + one.getPrimaryType() + one.getSecondaryType());
     * System.out.println("two: " + two.getPrimaryType() + two.getSecondaryType());
     * System.out.println("three: " + three.getPrimaryType() + three.getSecondaryType());
     * System.out.println("four: " + four.getPrimaryType() + four.getSecondaryType());
     * System.out.println("five: " + five.getPrimaryType() + five.getSecondaryType());
     * System.out.println("six: " + six.getPrimaryType() + six.getSecondaryType());
     * System.out.println("oneType: " + oneType.getPrimaryType() + oneType.getSecondaryType());
     */

    if (testTree.countType(ChugiType.BUG) != 2) {
      System.out.println("ChugiTree.countType() miscounted BUG types");
      return false;
    }
    if (testTree.countType(ChugiType.GRASS) != 6) {
      System.out.println("ChugiTree.countType() miscounted GRASS types");
      return false;
    }
    if (testTree.countType(ChugiType.ELECTRIC) != 1) {
      System.out.println("ChugiTree.countType() miscounted ELECTRIC types");
      return false;
    }
    if (testTree.countType(ChugiType.DRAGON) != 0) {
      System.out.println("ChugiTree.countType() miscounted DRAGON types");
      return false;
    }

    return true;
  }

  /**
   * Checks for the correctness of ChugiTree.height() method. This test must consider several
   * scenarios such as, (1) ensures that the height of an empty Chugimon tree is zero. (2) ensures
   * that the height of a tree which consists of only one node is 1. (3) ensures that the height of
   * a ChugiTree with four levels for instance, is 4.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testHeight() {
    ChugiTree testTree = new ChugiTree();
    Chugimon one = new Chugimon(15, 1); // Beeasaur TYPES: BUG, GRASS
    Chugimon two = new Chugimon(10, 1); // Caterasaur TYPES: BUG, GRASS
    Chugimon three = new Chugimon(36, 1); // Clefasaur TYPES: NORMAL, GRASS
    Chugimon four = new Chugimon(2, 1); // Ivyasaur TYPES: GRASS, POISON
    Chugimon five = new Chugimon(30, 1); // Nidoasaur TYPES: POISON, GRASS
    Chugimon six = new Chugimon(25, 1); // Pikaasaur TYPES: ELECTRIC, GRASS
    Chugimon seven = new Chugimon(57, 1); // Primeasaur
    Chugimon eight = new Chugimon(20, 1); // Ratasaur
    Chugimon nine = new Chugimon(45, 1); // Vileasaur

    // 1) Tests empty tree
    if (testTree.height() != 0) {
      System.out.println("ChugiTree.height() did not return 0 for an empty tree");
      return false;
    }

    // 2) Test tree with only root
    testTree.add(five);
    if (testTree.height() != 1) {
      System.out.println("ChugiTree.height() did not return 1 for a tree w only root");
      return false;
    }

    // 3) Tests height with a height of 3 (perfect tree)
    testTree.add(eight);
    testTree.add(three);
    testTree.add(two);
    testTree.add(four);
    testTree.add(nine);
    testTree.add(six);
    if (testTree.height() != 3) {
      System.out.println("ChugiTree.height() did not return 3 when height is 3");
      return false;
    }

    // 4) Tests height with a height of 4 (imperfect tree)
    testTree.add(seven);
    testTree.add(one);
    if (testTree.height() != 4) {
      System.out.println("ChugiTree.height() did not return 4 when height is 4");
      return false;
    }

    return true;
  }

  /**
   * Checks for the correctness of ChugiTree.getFirst() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testGetFirst() {
    ChugiTree testTree = new ChugiTree();
    Chugimon one = new Chugimon(15, 1); // Beeasaur TYPES: BUG, GRASS
    Chugimon two = new Chugimon(10, 1); // Caterasaur TYPES: BUG, GRASS
    Chugimon three = new Chugimon(36, 1); // Clefasaur TYPES: NORMAL, GRASS
    Chugimon four = new Chugimon(2, 1); // Ivyasaur TYPES: GRASS, POISON
    Chugimon five = new Chugimon(30, 1); // Nidoasaur TYPES: POISON, GRASS
    Chugimon six = new Chugimon(25, 1); // Pikaasaur TYPES: ELECTRIC, GRASS
    Chugimon seven = new Chugimon(57, 1); // Primeasaur
    Chugimon eight = new Chugimon(20, 1); // Ratasaur
    Chugimon nine = new Chugimon(45, 1); // Vileasaur

    // 1) Tests getFirst() on empty tree
    if (testTree.getFirst() != null) {
      System.out.println("ChugiTree.getFirst() did not return null on an empty tree");
      return false;
    }

    // 2) Tests getFirst() on perfect tree
    testTree.add(five);
    testTree.add(eight);
    testTree.add(three);
    testTree.add(two);
    testTree.add(four);
    testTree.add(nine);
    testTree.add(six);
    if (!testTree.getFirst().equals(two)) {
      System.out.println("ChugiTree.getFirst() did not return the lowest Chugimon (perf tree)");
      return false;
    }

    // 3) Tests getFirst() on imperfect tree
    testTree.add(seven);
    testTree.add(one);
    if (!testTree.getFirst().equals(one)) {
      System.out.println("ChugiTree.getFirst() did not return the lowest Chugimon (imperf tree)");
      return false;
    }

    return true;
  }

  /**
   * Checks for the correctness of ChugiTree.getLast() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testGetLast() {
    ChugiTree testTree = new ChugiTree();
    Chugimon two = new Chugimon(10, 1); // Caterasaur TYPES: BUG, GRASS
    Chugimon three = new Chugimon(36, 1); // Clefasaur TYPES: NORMAL, GRASS
    Chugimon four = new Chugimon(2, 1); // Ivyasaur TYPES: GRASS, POISON
    Chugimon five = new Chugimon(30, 1); // Nidoasaur TYPES: POISON, GRASS
    Chugimon six = new Chugimon(25, 1); // Pikaasaur TYPES: ELECTRIC, GRASS
    Chugimon eight = new Chugimon(20, 1); // Ratasaur
    Chugimon nine = new Chugimon(45, 1); // Vileasaur

    // 1) Tests getLast() on empty tree
    if (testTree.getLast() != null) {
      System.out.println("ChugiTree.getLast() did not return null on an empty tree");
      return false;
    }

    // 2) Tests getLast() on fuller tree
    testTree.add(five);
    testTree.add(eight);
    testTree.add(three);
    testTree.add(two);
    testTree.add(four);
    testTree.add(nine);
    testTree.add(six);
    if (!testTree.getLast().equals(nine)) {
      System.out.println("ChugiTree.getLast() did not return the highest Chugimon");
      return false;
    }

    return true;
  }

  /**
   * Checks for the correctness of ChugiTree.delete() method. This test must consider at least 3
   * test scenarios. (1) Remove a Chugimon that is at leaf node (2) Remove a Chugimon at non-leaf
   * node. For each of these scenarios, check that the size of the tree was decremented by one and
   * that the resulting ChugiTree is a valid BST, (3) ensures that the ChugiTree.delete() method
   * returns false when called on an Chugimon that is not present in the BST.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testDelete() {
    ChugiTree testTree = new ChugiTree(); // Will be size 9, height 4
    Chugimon one = new Chugimon(15, 1); // Beeasaur TYPES: BUG, GRASS
    Chugimon two = new Chugimon(10, 1); // Caterasaur TYPES: BUG, GRASS
    Chugimon three = new Chugimon(36, 1); // Clefasaur TYPES: NORMAL, GRASS
    Chugimon four = new Chugimon(2, 1); // Ivyasaur TYPES: GRASS, POISON
    Chugimon five = new Chugimon(30, 1); // Nidoasaur TYPES: POISON, GRASS
    Chugimon six = new Chugimon(25, 1); // Pikaasaur TYPES: ELECTRIC, GRASS
    Chugimon seven = new Chugimon(57, 1); // Primeasaur
    Chugimon eight = new Chugimon(20, 1); // Ratasaur
    Chugimon nine = new Chugimon(45, 1); // Vileasaur
    testTree.add(five);
    testTree.add(three);
    testTree.add(eight);
    testTree.add(two);
    testTree.add(one);
    testTree.add(four);
    testTree.add(six);
    testTree.add(nine);
    testTree.add(seven);

    // 1) Removing a leaf node
    if (!testTree.delete(one)) {
      System.out.println("ChugiTree.delete() failed to delete a leaf node");
      return false;
    }
    if (testTree.size() != 8) {
      System.out.println("ChugiTree.delete() did not correctly update size (test 1)");
      return false;
    }
    if (testTree.height() != 4) {
      System.out.println("ChugiTree.delete() incorrectly changed height (test 1)");
      return false;
    }
    if (!testTree.isValidBST()) {
      System.out.println("ChugiTree.delete() made an invalid tree (test 1)");
      return false;
    }
    if (!testTree.toString()
        .equals(two.toString() + "," + three.toString() + "," + four.toString() + ","
            + five.toString() + "," + six.toString() + "," + seven.toString() + ","
            + eight.toString() + "," + nine.toString())) {
      System.out.println("Test 1 to string (incorrect): " + testTree.toString());
      System.out.println("Test 1 expected: " + two.toString() + "," + three.toString() + ","
          + four.toString() + "," + five.toString() + "," + six.toString() + "," + seven.toString()
          + "," + eight.toString() + "," + nine.toString());
      return false;
    }
    // System.out.println("String after 1: " + testTree.toString());

    // 2) Removing non-leaf (that has two children, successor has no children)
    if (!testTree.delete(three)) {
      System.out.println("ChugiTree.delete() failed to delete a non-leaf node (two children, "
          + "successor has no child");
      return false;
    }
    if (testTree.size() != 7) {
      System.out.println("ChugiTree.delete() did not correctly update size (test 2)");
      return false;
    }
    if (testTree.height() != 4) {
      System.out.println("ChugiTree.delete() incorrectly changed height (test 2)");
      return false;
    }
    if (!testTree.isValidBST()) {
      System.out.println("ChugiTree.delete() made an invalid tree (test 2)");
      return false;
    }
    if (!testTree.toString().equals(
        two.toString() + "," + four.toString() + "," + five.toString() + "," + six.toString() + ","
            + seven.toString() + "," + eight.toString() + "," + nine.toString())) {
      System.out.println("Test 2 incorrect tostring: " + testTree.toString());
      return false;
    }
    // System.out.println("String after 2: " + testTree.toString());

    // 3) Removing non-leaf node (two children, successor has a child)
    // Also the root node so root should be updated accordingly
    if (!testTree.delete(five)) {
      System.out.println("ChugiTree.delete() failed to delete a non-leaf node (two children, "
          + "successor has one child");
      return false;
    }

    if (testTree.size() != 6) {
      System.out.println("ChugiTree.delete() did not correctly update size (test 3)");
      return false;
    }
    if (testTree.height() != 3) {
      System.out.println("ChugiTree.delete() made tree no longer have correct height (test 3)");
      return false;
    }
    if (!testTree.isValidBST()) {
      System.out.println("ChugiTree.delete() made an invalid tree (test 3)");
      return false;
    }
    if (!testTree.getRoot().equals(six)) {
      System.out.println("Incorrect tostring 3: " + testTree.toString());
      System.out.println("ChugiTree.delete() did not correctly change root when deleting root");
      return false;
    }
    if (!testTree.toString().equals(two.toString() + "," + four.toString() + "," + six.toString()
        + "," + seven.toString() + "," + eight.toString() + "," + nine.toString())) {
      System.out.println("Incorrect tostring 3: " + testTree.toString());
      return false;
    }

    return true;
  }

  /**
   * Checks for the correctness of ChugiTree.next() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testNext() {
    ChugiTree testTree = new ChugiTree();
    Chugimon one = new Chugimon(15, 1); // Beeasaur TYPES: BUG, GRASS
    Chugimon two = new Chugimon(10, 1); // Caterasaur TYPES: BUG, GRASS
    Chugimon three = new Chugimon(36, 1); // Clefasaur TYPES: NORMAL, GRASS
    Chugimon four = new Chugimon(2, 1); // Ivyasaur TYPES: GRASS, POISON
    Chugimon five = new Chugimon(30, 1); // Nidoasaur TYPES: POISON, GRASS
    Chugimon six = new Chugimon(25, 1); // Pikaasaur TYPES: ELECTRIC, GRASS
    Chugimon seven = new Chugimon(57, 1); // Primeasaur
    Chugimon eight = new Chugimon(20, 1); // Ratasaur
    Chugimon nine = new Chugimon(45, 1); // Vileasaur

    // 1) Test with null input
    boolean exceptionThrown = false;
    try {
      testTree.next(null);
    } catch (IllegalArgumentException e) {
      exceptionThrown = true;
    }
    if (!exceptionThrown) {
      System.out.println("ChugiTree.next() did not throw correct exception when given null param");
      return false;
    }

    // 2) Tests with empty tree
    exceptionThrown = false;
    try {
      testTree.next(one);
    } catch (NoSuchElementException e) {
      exceptionThrown = true;
    }
    if (!exceptionThrown) {
      System.out
          .println("ChugiTree.next() did not throw correct exception when searching in empty tree");
      return false;
    }

    // 3) Tests finding successor when node has right subtree
    testTree.add(five);
    testTree.add(three);
    testTree.add(eight);
    testTree.add(two);
    testTree.add(one);
    testTree.add(four);
    testTree.add(six);
    testTree.add(nine);
    testTree.add(seven);

    if (!testTree.next(five).equals(six)) {
      System.out.println("ChugiTree.next() did not return roots correct successor");
      return false;
    }

    // 4) Tests finding successor when parent is successor
    if (!testTree.next(two).equals(three)) {
      System.out.println("ChugiTree.next() did not return twos correct successor (its parent)");
      return false;
    }

    // 5) Tests finding successor when it is its parent's parent
    if (!testTree.next(seven).equals(eight)) {
      System.out.println(
          "ChugiTree.next() did not return sevens correct successor (its parent's parent)");
      return false;
    }

    // 6) Tests finding successor of rightmost node
    exceptionThrown = false;
    try {
      testTree.next(nine);
    } catch (NoSuchElementException e) {
      exceptionThrown = true;
    }
    if (!exceptionThrown) {
      System.out.println("ChugiTree.next() did not throw an exception when searching for "
          + "successor of rightmost node");
      return false;
    }

    return true;
  }

  /**
   * Checks for the correctness of ChugiTree.previous() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testPrevious() {
    ChugiTree testTree = new ChugiTree();
    Chugimon one = new Chugimon(15, 1); // Beeasaur TYPES: BUG, GRASS
    Chugimon two = new Chugimon(10, 1); // Caterasaur TYPES: BUG, GRASS
    Chugimon three = new Chugimon(36, 1); // Clefasaur TYPES: NORMAL, GRASS
    Chugimon four = new Chugimon(2, 1); // Ivyasaur TYPES: GRASS, POISON
    Chugimon five = new Chugimon(30, 1); // Nidoasaur TYPES: POISON, GRASS
    Chugimon six = new Chugimon(25, 1); // Pikaasaur TYPES: ELECTRIC, GRASS
    Chugimon seven = new Chugimon(57, 1); // Primeasaur
    Chugimon eight = new Chugimon(20, 1); // Ratasaur
    Chugimon nine = new Chugimon(45, 1); // Vileasaur

    // 1) Test with null input
    boolean exceptionThrown = false;
    try {
      testTree.previous(null);
    } catch (IllegalArgumentException e) {
      exceptionThrown = true;
    }
    if (!exceptionThrown) {
      System.out
          .println("ChugiTree.previous() did not throw correct exception when given null param");
      return false;
    }

    // 2) Tests with empty tree
    exceptionThrown = false;
    try {
      testTree.previous(one);
    } catch (NoSuchElementException e) {
      exceptionThrown = true;
    }
    if (!exceptionThrown) {
      System.out.println(
          "ChugiTree.previous() did not throw correct exception when searching in empty tree");
      return false;
    }
    // 3) Tests finding predecessor when node has left subtree
    testTree.add(five);
    testTree.add(three);
    testTree.add(eight);
    testTree.add(two);
    testTree.add(one);
    testTree.add(four);
    testTree.add(six);
    testTree.add(nine);
    testTree.add(seven);

    if (!testTree.previous(five).equals(four)) {
      System.out.println("ChugiTree.previous() did not return predecessor of root");
      return false;
    }

    // 4) Tests finding predecessor when parent is predecessor
    if (!testTree.previous(nine).equals(eight)) {
      System.out.println("ChugiTree.previous() did not return predecessor when parent is p.d.");
      return false;
    }

    // 5) Tests finding predecessor when parent's parent is predecessor
    if (!testTree.previous(six).equals(five)) {
      System.out
          .println("ChugiTree.previous() did not return predecessor when parent's parent is p.d.");
      return false;
    }

    // 6) Tests finding predecessor of leftmost node
    exceptionThrown = false;
    try {
      testTree.previous(one);
    } catch (NoSuchElementException e) {
      exceptionThrown = true;
    }
    if (!exceptionThrown) {
      System.out.println("ChugiTree.previous() did not throw exception when finding predecessor "
          + "of leftmost node");
      return false;
    }

    return true;
  }

  /**
   * Calls the test methods
   * 
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    System.out.println("testChugimonCompareToEquals: " + testChugimonCompareToEquals());
    System.out.println("testChugimonToString(): " + testChugimonToString());
    System.out.println("testIsValidBSTHelper(): " + testIsValidBSTHelper());
    System.out.println("testAddToStringSize(): " + testAddToStringSize());
    System.out.println("testLookup(): " + testLookup());
    System.out.println("testHeight(): " + testHeight());
    System.out.println("testCountType(): " + testCountType());
    System.out.println("testGetFirst(): " + testGetFirst());
    System.out.println("testGetLast(): " + testGetLast());
    System.out.println("testDelete(): " + testDelete());
    System.out.println("testNext(): " + testNext());
    System.out.println("testPrevious(): " + testPrevious());
  }

}
