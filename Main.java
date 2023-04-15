import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BST binarySearchTree = new BST();
        Scanner input = new Scanner(System.in);
        int commandCount = input.nextInt();
        for (int i = 0; i < commandCount; i++) {
            String command = input.next();
            if (command.equals("add")) {binarySearchTree.add(input.nextInt());}
            if (command.equals("inOrder")) {binarySearchTree.inOrder();}
            if (command.equals("preOrder")){System.out.println(binarySearchTree.preOrder(binarySearchTree.getRoot()));}
            if (command.equals("postOrder")){System.out.println(binarySearchTree.postOrder(binarySearchTree.getRoot()));}
            if (command.equals("height")){System.out.println(binarySearchTree.height());}
            if (command.equals("countLeaves")){System.out.println(binarySearchTree.countLeaves());}
            if (command.equals("countOneChildNodes")){System.out.println(binarySearchTree.countOneChildNodes());}
        }
    }
}

class Node
{
    public int data;
    public Node left;
    public Node right;

    public Node(int data, Node left, Node right)
    {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}

class BST
{
    private Node root;
    public Node getRoot() {return root;}

    public void add(int item)
    {
        Node newNode = new Node(item, null, null);
        if (root == null) {root = newNode;}
        else {insert(newNode, root);}
    }
    private void insert(Node newNode, Node subTree)
    {
        if (newNode.data < subTree.data)
        {
            if(subTree.left == null) {subTree.left = newNode;}
            else {insert(newNode, subTree.left);}
        }
        else {
            if(subTree.right == null) {subTree.right = newNode;}
            else {insert(newNode, subTree.right);}
        }
    }

    // Conduct the in-order traversal.
    // Actual traversal happens in another method named inOrder() with the root.
    // We have two inOrder() methods (= method overloading).
    public void inOrder()
    {
        inOrder(root);
    }

    private void inOrder(Node subTree)
    {
        if (subTree != null)
        {
            inOrder(subTree.left);
            System.out.print(subTree.data + " ");
            inOrder(subTree.right);
        }
    }

    ///preOrder & postOrder functions taken from: https://www.geeksforgeeks.org/tree-traversals-inorder-preorder-and-postorder/
    public static String preOrder(Node root) {
        StringBuilder sb = new StringBuilder();
        preOrder(root, sb);
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    private static void preOrder(Node node, StringBuilder sb) {
        if (node == null) {
            return;
        }
        sb.append(node.data).append(" ");
        preOrder(node.left, sb);
        preOrder(node.right, sb);
    }

    // Method to perform post-order traversal of the tree
    public static StringBuilder postOrder(Node root) {
        StringBuilder sb = new StringBuilder();
        postOrder(root, sb);
        sb.deleteCharAt(sb.length()-1);
        return sb;
    }

    private static void postOrder(Node node, StringBuilder sb) {
        if (node == null) {
            return;
        }
        postOrder(node.left, sb);
        postOrder(node.right, sb);
        sb.append(node.data).append(" ");
    }

    // Method to calculate the height of the tree taken from: https://www.geeksforgeeks.org/find-the-maximum-depth-or-height-of-a-tree/
    public int height() {
        return height(root);
    }

    private int height(Node node) {
        if (node == null)
            return -1;
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    // Method to count the number of leaves in the tree taken from: https://www.geeksforgeeks.org/write-a-c-program-to-get-count-of-leaf-nodes-in-a-binary-tree/
    public int countLeaves() {
        return countLeaves(root);
    }

    private int countLeaves(Node node) {
        if (node == null)
            return 0;
        if (node.left == null && node.right == null)
            return 1;
        return countLeaves(node.left) + countLeaves(node.right);
    }

     ///Method to count the number of nodes with only one child
    public int countOneChildNodes() {
        return countOneChildNodes(root);
    }

    private int countOneChildNodes(Node node) {
        if (node == null)
            return 0;
        int count = 0;
        if ((node.left == null && node.right != null) || (node.left != null && node.right == null))
            count++;
        count += countOneChildNodes(node.left) + countOneChildNodes(node.right);
        return count;
    }
}
