/*
 * This source file was generated by the Gradle 'init' task
 */
package pa5;

import org.checkerframework.checker.units.qual.m;

/**
 *  Interface for a binary tree implemented using an array
*   The tree must adhere to the properties of a complete binary tree: 
*      A complete binary tree is a binary tree in which every level,
*      The tree is completely filled on all levels except possibly 
*      for the lowest level, which is filled from left to right.
 */
interface ArrayBasedBinaryTree {

    /**
     * Inserts an element into the tree. 
     * Assume `element` is always a positive integer
     */  
    void insert(int element);

    /**
     * Returns the tree in level order
     */
    String levelOrder();


    /**
     * Returns the tree in in-order
     */
    String inOrder();
        
    /**
     * Returns the tree in pre-order
     */
    String preOrder();

    /**
     * Returns the tree in post-order
     */
    String postOrder();

    /**
     * Return the length of the longest path in the tree
     */
    int longestPath();

    /**
     * Delete an element from the tree
     * Ensure that the tree remains a complete binary tree
     */
    void delete(int element);
}

// Uncomment the following code to implement the BinaryTreeArray class
public class BinaryTreeArray implements ArrayBasedBinaryTree{

    private int[] data;
    private int maxCapacity = 10;
    private int size = 0; 


    public BinaryTreeArray(){
        this.data = new int[this.maxCapacity];  
    }

    public void insert(int element) {
        if (size >= maxCapacity) {
            System.out.println("Tree is full, cannot insert new element.");
            return;
        }
    
        //System.out.println("Inserting element: " + element); 
    
        this.data[this.size] = element;
        this.size++;
    
    }   

    public String levelOrder() {
        return levelOrderHelper();
    }
    
    private String levelOrderHelper() {
        String result = "";
        for (int i = 0; i < size; i++) {
            result += data[i] + " ";
        }
        return result;
    }

    public String inOrder() {
        return inOrder(0);
    }

    private String inOrder(int index){

        if (index >= size) {
            return "";
        }

        String left = inOrder(2 * index + 1); 
        String current = String.valueOf(data[index]);
        String right = inOrder(2 * index + 2); 
        return (left + " " + current + " " + right); 
    }

    public String preOrder() {
        return preOrder(0);
    }

    private String preOrder(int index){
        if (index >= size) {
            return "";
        }

        String current = String.valueOf(data[index]);
        String left = preOrder(2 * index + 1);
        String right = preOrder(2 * index + 2);
        return (current + " " + left + "" + right); 
    }

    public String postOrder() {
        return postOrder(0);
    }

    private String postOrder(int index){
        if (index >= size) {
            return "";
        }

        String current = String.valueOf(data[index]);
        String left = postOrder(2 * index + 1);
        String right = postOrder(2 * index + 2);


        return (left + "" + right + " " + current); 
    }

    public int longestPath() {
        return longestPath(0);
    }

    private int longestPath(int index){
        if (index >= size) {
            return 0;
        }

        int left = longestPath(2 * index + 1);
        int right = longestPath(2 * index + 2);
        return 1 + Math.max(left, right); 
    }

    public void delete(int element) {
        int result = deleteHelper(element);
        if (result == -1) {
            System.out.println("Element not found, unable to delete.");
        } 
    }

    private int deleteHelper(int element) {
        int i;
        for (i = 0; i < size; i++) {
            if (data[i] == element) {
                break;
            }
        }
        if (i == size) return -1; 
        // Swapning it with the last element
        data[i] = data[size - 1];
        size--;

        // Reheapify down, (he said we didnt have to use it)
        int current = i;
        while (current < size) {
            int leftChild = 2 * current + 1;
            int rightChild = 2 * current + 2;

            int smallestChild = leftChild;
            if (rightChild < size && data[rightChild] < data[leftChild]) {
                smallestChild = rightChild;
            }

            if (data[current] > data[smallestChild]) {
                swap(current, smallestChild);
                current = smallestChild;
            } 
        }

        return 0; 
        
    }


    private void swap(int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }


    public static void main(String[] args){

        BinaryTreeArray tree = new BinaryTreeArray();

        tree.insert(10);
        tree.insert(15);
        tree.insert(5);
        tree.insert(7);
        tree.insert(20);

        System.out.println("Level Order: " + tree.levelOrder());
        System.out.println("In Order: " + tree.inOrder());
        System.out.println("Pre Order: " + tree.preOrder());
        System.out.println("Post Order: " + tree.postOrder());

        tree.delete(7);
        System.out.println("After deleting 7, Level Order: " + tree.levelOrder());
    }   
}
