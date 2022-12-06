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
 * This class implements a ChugidexStorage as a Binary Search Tree.
 * 
 * Notes: 1) You may NOT use any arrays or Collections objects (ArrayLists, etc) in this class. 2)
 * You may NOT use any loops (for, while, etc) in this class. Recursive strategies only.
 *
 */
public class ChugiTree implements ChugidexStorage {

  // The root of this ChugiTree. Set to null when tree is empty.
  private BSTNode<Chugimon> root;

  // The size of this ChugiTree (total number of Chugimon stored in this BST)
  private int size;


  /**
   * Getter method for the Chugimon at the root of this BST.
   * 
   * @return the root of the BST.
   */
  public Chugimon getRoot() {
    return this.root.getData();
  }

  /**
   * A helper method for determining whether this ChugiTree is a valid BST. In order to be a valid
   * BST, the following must be true: For every internal (non-leaf) node X of a binary tree, all the
   * values in the node's left subtree are less than the value in X, and all the values in the
   * node's right subtree are greater than the value in X.
   * 
   * @return true if this ChugiTree is a valid BST, false otherwise
   */
  public boolean isValidBST() {
    return isValidBSTHelper(root);
  }

  /**
   * Gets the lowest BSTNode given the root. Differs from getFirst() in that this can be called
   * statically (can be called with any node, don't need a ChugiTree object). Used in
   * isValidBSTHelper
   * 
   * @param root root of the tree
   * @return the lowest BSTNode within the given tree
   */
  private static BSTNode<Chugimon> getLowest(BSTNode<Chugimon> root) {
    // Base Case: At the lowest node
    if (root.getLeft() == null) {
      return root;
    } else { // Recursive: lower nodes exist
      return getLowest(root.getLeft());
    }
  }

  /**
   * Gets the highest BSTNode given the root. Differs from getLast() in that this can be called
   * statically (can be called with any node, don't need a ChugiTree object). Used in
   * isValidBSTHelper
   * 
   * @param root root of the tree
   * @return the highest BSTNode within the given tree
   */
  private static BSTNode<Chugimon> getHighest(BSTNode<Chugimon> root) {
    // Base Case: At the highest node
    if (root.getRight() == null) {
      return root;
    } else { // Recursive: higher nodes exist
      return getHighest(root.getRight());
    }
  }

  /**
   * A helper method for determining whether a BST rooted at node is a valid BST. In order to be a
   * valid BST, the following must be true: For every internal (non-leaf) node of a binary tree, all
   * the values in a node's left subtree are less than the values in a node's right subtree.
   * 
   * @param node The root of the BST.
   * @return true if the binary tree rooted at node is a BST, false otherwise
   */
  public static boolean isValidBSTHelper(BSTNode<Chugimon> node) {
    // Valid BST cannot contain equal nodes
    boolean toReturn = true;
    // Should only occur when passed null input
    if (node == null) {
      return toReturn;
    }

    // Base Case: No Children
    if (node.getLeft() == null && node.getLeft() == null) {
      return toReturn;
    }

    Integer comparisonLeft = null;
    Integer comparisonRight = null;
    if (node.getLeft() != null) {
      // Should be positive if valid
      comparisonLeft = node.getData().compareTo(node.getLeft().getData());
    }
    if (node.getRight() != null) {
      // Should be negative if valid
      comparisonRight = node.getData().compareTo(node.getRight().getData());
    }

    // Checks locally that node is greater/less than left/right
    if ((comparisonLeft != null && comparisonLeft <= 0)
        || (comparisonRight != null && comparisonRight >= 0)) {
      return false;
    }

    // Checks nonlocally that current tree is correct
    if (node.getRight() != null) {
      if (node.getData().compareTo(ChugiTree.getLowest(node.getRight()).getData()) >= 0) {
        return false;
      }
    }
    if (node.getLeft() != null) {
      if (node.getData().compareTo(ChugiTree.getHighest(node.getLeft()).getData()) <= 0) {
        return false;
      }
    }

    // Recursive case
    boolean tempBool = true;
    if (node.getLeft() != null) {
      tempBool = isValidBSTHelper(node.getLeft());
    }
    if (tempBool == false) {
      toReturn = false;
    }

    if (node.getRight() != null) {
      tempBool = isValidBSTHelper(node.getRight());
    }
    if (tempBool == false) {
      toReturn = false;
    }

    return toReturn;
  }

  /**
   * Checks whether this ChugiTree is empty or not
   * 
   * @return true if this tree is empty, false otherwise
   */
  @Override
  public boolean isEmpty() {
    if (this.size == 0 && this.root == null) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Gets the size of this ChugiTree
   * 
   * @return the total number of Chugimons stored in this tree
   */
  @Override
  public int size() {
    return this.size;
  }

  /**
   * Returns a String representation of all the Chugimons stored within this ChugiTree in the
   * increasing order with respect to the result of Chugimon.compareTo() method. The string should
   * be a comma-separated list of all the Chugimon toString() values. No spaces are expected to be
   * in the resulting string. No comma should be at the end of the resulting string. For instance,
   * 
   * "nameOne#12.25,nameTwo#12.56,nameTwo#89.27"
   * 
   * @return a string containing all of the Chugimon, in the increasing order. Returns an empty
   *         string "" if this BST is empty.
   * 
   */
  @Override
  public String toString() {
    if (this.isEmpty()) {
      return "";
    }
    String extraComma = toStringHelper(root);
    extraComma = extraComma.substring(0, extraComma.length() - 1);
    return extraComma;
  }

  /**
   * Recursive helper method which returns a String representation of the ChugiTree rooted at node.
   * An example of the String representation of the contents of a ChugiTree storing three Chugimons
   * is provided in the description of the above toString() method.
   * 
   * @param node references the root of a subtree
   * @return a String representation of all the Chugimons stored in the sub-tree rooted at node in
   *         increasing order. Returns an empty String "" if current is null.
   */
  protected static String toStringHelper(BSTNode<Chugimon> node) {
    String formattedString = "";
    // Base Case: null node
    if (node == null) {
      return "";
    }
    // Recursive: need to add nodes
    formattedString += toStringHelper(node.getLeft());
    formattedString += node.getData().toString() + ",";
    formattedString += toStringHelper(node.getRight());
    return formattedString;
  }

  /**
   * Adds a new Chugimon to this ChugiTree. Duplicate Chugimons are NOT allowed.
   * 
   * @param newChugimon Chugimon to add to this ChugiTree
   * @return true if if the newChugimon was successfully added to the ChugiTree, false if a match
   *         with newChugimon is already present in the tree.
   * @throws IllegalArgumentException with a descriptive error message if newChugimon is null.
   */
  @Override
  public boolean add(Chugimon newChugimon) {
    if (newChugimon == null) {
      throw new IllegalArgumentException("cannot add null");
    }
    // If empty, root is the node
    if (this.isEmpty()) {
      this.root = new BSTNode(newChugimon);
      this.size++;
      return true;
    }
    // If not empty
    boolean added = addHelper(newChugimon, this.root);
    if (added == true) {
      ++size;
    }

    return added;
  }

  /**
   * Recursive helper method to insert a new Chugimon to a Pokedex rooted at node.
   * 
   * @param node        The "root" of the subtree we are inserting the new Chugimon into.
   * @param newChugimon The Chugimon to be added to a BST rooted at node. We assume that newChugimon
   *                    is NOT null.
   * @return true if the newChugimon was successfully added to the ChugiTree, false if a match with
   *         newChugimon is already present in the subtree rooted at node.
   */
  protected static boolean addHelper(Chugimon newChugimon, BSTNode<Chugimon> node) {
    Integer comparison = node.getData().compareTo(newChugimon);

    if (comparison.equals(0)) {
      return false; // cannot have equal nodes
    } else if (comparison < 0) { // node to add is greater
      if (node.getRight() == null) { // If no right child, add it
        node.setRight(new BSTNode(newChugimon));
        return true;
      } else {
        return addHelper(newChugimon, node.getRight());
      }
    } else { // if comparison is > 0 (node to add is less)
      if (node.getLeft() == null) { // If no left child, add it
        node.setLeft(new BSTNode(newChugimon));
        return true;
      } else {
        return addHelper(newChugimon, node.getLeft());
      }
    }
  }

  /**
   * Searches a Chugimon given its first and second identifiers.
   * 
   * @param firstId  First identifier of the Chugimon to find
   * @param secondId Second identifier of the Chugimon to find
   * @return the matching Chugimon if match found, null otherwise.
   */
  @Override
  public Chugimon lookup(int firstId, int secondId) {
    // Ensure no exception is thrown
    if (firstId == secondId) {
      return null;
    } else if (this.isEmpty()) { // If empty (ensures to exception)
      return null;
    } else {
      Chugimon toFind = new Chugimon(firstId, secondId);
      return lookupHelper(toFind, this.root);
    }
  }

  /**
   * Recursive helper method to search and return a match with a given Chugimon in the subtree
   * rooted at node, if present.
   * 
   * @param toFind a Chugimon to be searched in the BST rooted at node. We assume that toFind is NOT
   *               null.
   * @param node   "root" of the subtree we are checking whether it contains a match to target.
   * @return a reference to the matching Chugimon if found, null otherwise.
   */
  protected static Chugimon lookupHelper(Chugimon toFind, BSTNode<Chugimon> node) {
    // Base Case: found a match
    if (toFind.equals(node.getData())) {
      return node.getData();
    }
    // Recursive case
    Integer comparisonVal = toFind.compareTo(node.getData());
    if (comparisonVal < 0) { // If toFind could be in left subtree
      if (node.getLeft() == null) {
        return null;
      } else {
        return lookupHelper(toFind, node.getLeft());
      }
    } else { // If toFind could be in right subtree
      if (node.getRight() == null) {
        return null;
      } else {
        return lookupHelper(toFind, node.getRight());
      }
    }
  }

  /**
   * Computes and returns the height of this BST, counting the number of NODES from root to the
   * deepest leaf.
   * 
   * @return the height of this Binary Search Tree
   */
  public int height() {
    if (this.isEmpty()) {
      return 0;
    }

    return heightHelper(this.root);
  }

  /**
   * Recursive helper method that computes the height of the subtree rooted at node counting the
   * number of nodes and NOT the number of edges from node to the deepest leaf
   * 
   * @param node root of a subtree
   * @return height of the subtree rooted at node
   */
  protected static int heightHelper(BSTNode<Chugimon> node) {
    int left = 0;
    int right = 0;
    int toReturn = 0;
    // Base Case: null node
    if (node == null) {
      return 0;
    }
    // Recursive Case
    ++left;
    ++right;
    left += heightHelper(node.getLeft());
    right += heightHelper(node.getRight());
    toReturn = Math.max(left, right);

    return toReturn;
  }

  /**
   * Recursive method to find and return the first Chugimon, in the increasing order, within this
   * ChugiTree (meaning the smallest element stored in the tree).
   * 
   * @return the first element in the increasing order of this BST, and null if the tree is empty.
   */
  @Override
  public Chugimon getFirst() {
    if (this.root == null) {
      return null;
    } else {
      return getFirstHelper(this.root);
    }
    // HINT: The smallest element in a non-empty BST is the left most element
  }

  /**
   * Recursive helper method for getFirst().
   * 
   * @param root the node from which to find the the minimum node
   * @return the minimum element in the increasing order from the node <b>root</b>
   */
  protected static Chugimon getFirstHelper(BSTNode<Chugimon> root) {
    // Base Case: at the lowest node
    if (root.getLeft() == null) {
      return root.getData();
    } else { // Recursive case: more left children
      return getFirstHelper(root.getLeft());
    }
  }

  /**
   * Recursive method to find and return the last Chugimon, in the increasing order, within this
   * ChugiTree (meaning the greatest element stored in the tree).
   * 
   * @return the last element in the increasing order of this BST, and null if the tree is empty.
   */
  @Override
  public Chugimon getLast() {
    if (this.root == null) {
      return null;
    } else {
      return getLastHelper(this.root);
    }
  }

  /**
   * Recursive helper method for getLast().
   * 
   * @param root the node from which to find the the maximum node
   * @return the maximum element in the increasing order from the node <b>root</b>
   */
  protected static Chugimon getLastHelper(BSTNode<Chugimon> root) {
    // Base Case: at the highest node
    if (root.getRight() == null) {
      return root.getData();
    } else { // Recursive: more higher nodes
      return getLastHelper(root.getRight());
    }
  }

  /**
   * Recursive method to get the number of Chugimon with a primary or secondary type of the
   * specified type, stored in this ChugiTree.
   * 
   * @param chugiType the type of Chugimons to count. We assume that chugiType is NOT null.
   * @return the number of all the Chugimon objects with a primary or secondary type of the
   *         specified type stored in this ChugiTree.
   */
  public int countType(ChugiType chugiType) {
    // Ensuring chugiType isn't null or the initial node passed to helper isn't null
    if (chugiType == null) {
      return 0;
    }
    if (this.isEmpty()) {
      return 0;
    }
    return countTypeHelper(chugiType, this.root);
  }

  /**
   * Recursive helper method for countType(). Returns number of occurrences of chugiType
   * 
   * @param chugiType type of Chugimons to count
   * @param node      the current root of the tree to check
   * @return number of times Chugitype occurs within the tree
   */
  private int countTypeHelper(ChugiType chugiType, BSTNode<Chugimon> node) {
    int numOcc = 0;
    // Base Case: null node
    if (node == null) {
      return 0;
    }
    // Recursive case
    if (node.getData().getPrimaryType().equals(chugiType)) {
      ++numOcc;
    }
    if (node.getData().getSecondaryType() != null) {
      if (node.getData().getSecondaryType().equals(chugiType)) {
        ++numOcc;
      }
    }
    numOcc += countTypeHelper(chugiType, node.getLeft());
    numOcc += countTypeHelper(chugiType, node.getRight());

    return numOcc;
  }

  /**
   * Finds and returns the in-order successor of a specified Chugimon in this ChugiTree
   * 
   * @param chugi the Chugimon to find its successor
   * @return the in-order successor of a specified Chugimon in this ChugiTree
   * 
   * @throws IllegalArgumentException with a descriptive error message if <b>chugi</b> is null
   * @throws NoSuchElementException   with a descriptive error message if the Chugimon provided as
   *                                  input has no in-order successor in this ChugiTree.
   */
  @Override
  public Chugimon next(Chugimon chugi) {
    if (chugi == null) {
      throw new IllegalArgumentException("Cannot use null Chugimon");
    }
    return nextHelper(chugi, this.root, null);
  }

  /**
   * Recursive helper method to find and return the next Chugimon in the tree rooted at node.
   * 
   * @param chugi a Chugimon to search its in-order successor. We assume that <b>chugi</b> is NOT
   *              null.
   * @param node  "root" of a subtree storing Chugimon objects
   * @param next  a BSTNode which stores a potentional candidate for next node
   * @return the next Chugimon in the tree rooted at node.
   * @throws NoSuchElementException with a descriptive error message if the Chugimon provided as
   *                                input has no in-order successor in the subtree rooted at node.
   */
  protected static Chugimon nextHelper(Chugimon chugi, BSTNode<Chugimon> node, BSTNode next) {
    // Base Case: node is null
    if (node == null) {
      throw new NoSuchElementException("No Successor");
    }

    // Recursive Cases:
    // if chugi is found and if the right child is not null, get lowest value of right subtree
    if (node.getData().equals(chugi)) {
      if (node.getRight() != null) {
        return getFirstHelper(node.getRight());
      }
    }
    // If chugi is found and getRight is null, successor is next value (or nothing if next is null)
    if (chugi.equals(node.getData())) {
      if (node.getRight() == null) {
        if (next != null) {
          return (Chugimon) next.getData();
        } else { // Next is null
          throw new NoSuchElementException("No successor");
        }
      }
    }

    // if chugi is less than the Chugimon at node, set next as the root node and search
    // recursively into the left subtree
    if (chugi.compareTo(node.getData()) < 0) {
      next = node;
      return nextHelper(chugi, node.getLeft(), next);
    } else { // if chugi is greater than the Chugimon at node, recurse right
      return nextHelper(chugi, node.getRight(), next);
    }

  }

  /**
   * Finds and returns the in-order predecessor of a specified Chugimon in this ChugiTree
   * 
   * @param chugi the Chugimon to find its predecessor
   * @return the in-order predecessor of a specified Chugimon in this ChugiTree.
   * 
   * @throws IllegalArgumentException with a descriptive error message if <b>chugi</b> is null
   * @throws NoSuchElementException   if there is no Chugimon directly before the provided Chugimon
   */
  @Override
  public Chugimon previous(Chugimon chugi) {
    if (chugi == null) {
      throw new IllegalArgumentException("Cannot use null Chugimon");
    }
    return previousHelper(chugi, root, null);
  }

  /**
   * Recursive helper method to find and return the previous Chugimon in the tree rooted at node.
   * 
   * @param chugi a Chugimon to search its in-order predecessor. We assume that <b>chugi</b> is NOT
   *              null.
   * @param node  "root" of a subtree storing Chugimon objects
   * @param prev  a BSTNode which stores a potentional candidate for previous node
   * @return the previous Chugimon in the tree rooted at node.
   * @throws NoSuchElementException with a descriptive error message if the Chugimon provided as
   *                                input has no in-order predecessor in the subtree rooted at node.
   */
  protected static Chugimon previousHelper(Chugimon chugi, BSTNode<Chugimon> node,
      BSTNode<Chugimon> prev) {

    // Base Base: node is null
    if (node == null) {
      throw new NoSuchElementException("No Predecessor");
    }

    // recursive cases:
    // (1) if chugi is found and if the left child is not null, find right most child of the left
    // subtree
    if (node.getData().equals(chugi)) {
      if (node.getLeft() != null) {
        return getLastHelper(node.getLeft());
      } else { // 2) If left is null, return prev (or throw exception if prev is null)
        if (prev != null) {
          return prev.getData();
        } else {
          throw new NoSuchElementException("No Predecessor");
        }
      }
    }
    // 3) If chugi is greater than current node, set prev as current node (potential predecessor)
    // and recurse thru right subtree
    if (chugi.compareTo(node.getData()) > 0) {
      prev = node;
      return previousHelper(chugi, node.getRight(), prev);
    } else { // 4) If chugi is less than current node, recurse thru left subtree
      return previousHelper(chugi, node.getLeft(), prev);
    }
  }

  /**
   * Deletes a specific Chugimon from this ChugiTree.
   * 
   * @param chugi the Chugimon to delete
   * @return true if the specific Chugimon is successfully deleted, false if no match found with any
   *         Chugimon in this tree.
   * @throws IllegalArgumentException with a descriptive error message if <b>chugi</b> is null
   */
  @Override
  public boolean delete(Chugimon chugi) {
    if (chugi == null) {
      throw new IllegalArgumentException("Cannot delete null Chugimon");
    }

    try {
      BSTNode temp = deleteChugimonHelper(chugi, this.root);
      if (this.root.getLeft() == null && this.root.getRight() == null && temp != null) {
        this.root = temp;
      }
    } catch (NoSuchElementException e) {
      return false;
    }
    --size;

    return true;
  }

  /**
   * Recursive helper method to search and delete a specific Chugimon from the BST rooted at node
   * 
   * @param target a reference to a Chugimon to delete from the BST rooted at node. We assume that
   *               target is NOT null.
   * @param node   "root" of the subtree we are checking whether it contains a match with the target
   *               Chugimon.
   * 
   * 
   * @return the new "root" of the subtree we are checking after trying to remove target
   * @throws NoSuchElementException with a descriptive error message if there is no Chugimon
   *                                matching target in the BST rooted at <b>node</b>
   */
  protected static BSTNode<Chugimon> deleteChugimonHelper(Chugimon target, BSTNode<Chugimon> node) {

    // if node == null (empty subtree rooted at node), no match found, throw an
    // exception
    if (node == null) {
      throw new NoSuchElementException("Chugimon doesn't exist");
    }

    // Compare the target to the data at node and proceed accordingly
    // Recurse on the left or right subtree with respect to the comparison result
    // Make sure to use the output of the recursive call to appropriately set the
    // left or the right child of node accordingly
    int comparison = target.compareTo(node.getData());

    if (comparison < 0) { // Target could be in left subtree
      if (node.getLeft() != null && node.getLeft().getData().equals(target)) {
        // 1) If left has no children
        if (node.getLeft().getLeft() == null && node.getLeft().getRight() == null) {
          node.setLeft(null);
          return node;
        }
        // 2.1) If left has one child (on the right)
        if (node.getLeft().getLeft() == null && node.getLeft().getRight() != null) {
          node.setLeft(node.getLeft().getRight());
          return node;
        }
        // 2.2) If left has one child (on the left)
        if (node.getLeft().getLeft() != null && node.getLeft().getRight() == null) {
          node.setLeft(node.getLeft().getLeft());
          return node;
        }
        // 3) If left has two children
        if (node.getLeft().getLeft() != null && node.getLeft().getRight() != null) {
          Chugimon successorMon = getFirstHelper(node.getLeft().getRight());
          BSTNode successor = new BSTNode(successorMon);
          deleteChugimonHelper(successorMon, node);
          successor.setLeft(node.getLeft().getLeft());
          successor.setRight(node.getLeft().getRight());
          node.setLeft(successor);
          return node;
        }
      } else {
        return deleteChugimonHelper(target, node.getLeft());
      }
    } else if (comparison > 0) { // Target could be in right subtree
      if (node.getRight() != null && node.getRight().getData().equals(target)) {
        // 1) right has no children
        if (node.getRight().getLeft() == null && node.getRight().getLeft() == null) {
          node.setRight(null);
          return node;
        }
        // 2.1) If right has one child (on the right)
        if (node.getRight().getLeft() == null && node.getRight().getRight() != null) {
          node.setRight(node.getRight().getRight());
          return node;
        }
        // 2.2) If right has one child (on the left)
        if (node.getRight().getLeft() != null && node.getRight().getRight() == null) {
          node.setRight(node.getRight().getLeft());
          return node;
        }
        // 3) If left has two children
        if (node.getRight().getLeft() != null && node.getRight().getRight() != null) {
          Chugimon successorMon = getFirstHelper(node.getRight().getRight());
          BSTNode successor = new BSTNode(successorMon);
          deleteChugimonHelper(successorMon, node);
          successor.setLeft(node.getRight().getLeft());
          successor.setRight(node.getRight().getRight());
          node.setRight(successor);
          return node;
        }
      } else {
        return deleteChugimonHelper(target, node.getRight());
      }
    } else { // If target is root
      // If root has a right subtree, get successor to replace root, delete succesor, return new
      // root (will be made new root in og method)
      if (node.getRight() != null) {
        Chugimon successorMon = getFirstHelper(node.getRight());
        BSTNode successor = new BSTNode(successorMon);
        deleteChugimonHelper(successorMon, node);
        successor.setLeft(node.getLeft());
        successor.setRight(node.getRight());
        node.setLeft(null);
        node.setRight(null);
        return successor;
      } else if (node.getRight() == null && node.getLeft() != null) {
        BSTNode temp = node.getLeft();
        node.setLeft(null);
        return temp; // Will be made new root in og method
      } else { // Both children null, only root in tree
        return null;
      }
    }

    return null; // Default return statement
  }
}
