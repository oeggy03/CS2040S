/**
 * ScapeGoat Tree class
 *
 * This class contains some of the basic code for implementing a ScapeGoat tree.
 * This version does not include any of the functionality for choosing which node
 * to scapegoat.  It includes only code for inserting a node, and the code for rebuilding
 * a subtree.
 */

public class SGTree {

    // Designates which child in a binary tree
    enum Child {LEFT, RIGHT}

    /**
     * TreeNode class.
     *
     * This class holds the data for a node in a binary tree.
     *
     * Note: we have made things public here to facilitate problem set grading/testing.
     * In general, making everything public like this is a bad idea!
     *
     */
    public static class TreeNode {
        int key;
        public TreeNode left = null;
        public TreeNode right = null;

        TreeNode(int k) {
            key = k;
        }
    }

    // Root of the binary tree
    public TreeNode root = null;

    /**
     * Counts the number of nodes in the specified subtree
     *
     * @param node  the parent node, not to be counted
     * @param child the specified subtree
     * @return number of nodes
     */
    public int countNodes(TreeNode node, Child child) {
        TreeNode currRoot = null;
        if (child == Child.LEFT) {
            currRoot = node.left;
        } else {
            currRoot = node.right;
        }

        //base case
        if (currRoot == null) {
            return 0;
        } else {
            //recursion
            return 1 + countNodes(currRoot, Child.LEFT) + countNodes(currRoot, Child.RIGHT);
        }
    }

    public int countNodes2(TreeNode node, Child child) {
        int count = 0;

        if (node == null) {
            return 0;
        } else if (child == Child.LEFT) {
            count = 1 + countNodes(node.left, Child.LEFT) + countNodes(node.left, Child.RIGHT);
        } else if (child == Child.RIGHT) {
            count = 1 + countNodes(node.right, Child.LEFT) + countNodes(node.right, Child.RIGHT);
        }

        return count;
    }

    /**
     * Builds an array of nodes in the specified subtree
     *
     * @param node  the parent node, not to be included in returned array
     * @param child the specified subtree
     * @return array of nodes
     */
    public TreeNode[] enumerateNodes(TreeNode node, Child child) {
        int treeSize = countNodes(node, child);
        TreeNode[] enumeratedNodes = new TreeNode[treeSize];

        TreeNode[] leftTree;
        TreeNode[] rightTree;

        if (treeSize == 0) {
            return enumeratedNodes;
        }

        TreeNode currNode = null;

        if (child == Child.LEFT) {
            currNode = node.left;
        } else {
            currNode = node.right;
        }

        int sizeLeftTree = countNodes(currNode, Child.LEFT);
        int sizeRightTree = countNodes(currNode, Child.RIGHT);

        //go over left side
        if (sizeLeftTree > 0) {
            leftTree = enumerateNodes(currNode, Child.LEFT);
            for (int i = 0; i < sizeLeftTree; i++) {
                enumeratedNodes[i] = leftTree[i];
            }
        }

        //puts in the parent node, if there is one (edge: empty tree)
        if (currNode != null) {
            TreeNode root = currNode;
            enumeratedNodes[sizeLeftTree] = root;
        }

        //go over right side
        if (sizeRightTree > 0) {
            rightTree = enumerateNodes(currNode, Child.RIGHT);
            for (int j = sizeLeftTree + 1; j < sizeLeftTree + 1 + sizeRightTree; j++) {
                enumeratedNodes[j] = rightTree[j - sizeLeftTree - 1];
            }
        }

        return enumeratedNodes;
    }

    /**
     * Builds a tree from the list of nodes
     * Returns the node that is the new root of the subtree
     *
     * @param nodeList ordered array of nodes
     * @return the new root node
     */
    public TreeNode buildTree(TreeNode[] nodeList) {
        int start = 0;
        int end = nodeList.length - 1;

        if (end < 0) {
            return null;
        }

        TreeNode root;
        int mid = start + (end - start) / 2;
        root = nodeList[mid];

        TreeNode[] leftNodeList = new TreeNode[mid];

        for(int i = start; i < mid; i++) {
            leftNodeList[i] = nodeList[i];
        }

        TreeNode[] rightNodeList = new TreeNode[end - mid];

        for(int j = mid + 1; j <= end; j++) {
            rightNodeList[j - (mid + 1)] = nodeList[j];
        }

        root.left = buildTree(leftNodeList);
        root.right = buildTree(rightNodeList);

        return root;
    }

    /**
    * Rebuilds the specified subtree of a node
    * 
    * @param node the part of the subtree to rebuild
    * @param child specifies which child is the root of the subtree to rebuild
    */
    public void rebuild(TreeNode node, Child child) {
        // Error checking: cannot rebuild null tree
        if (node == null) return;
        // First, retrieve a list of all the nodes of the subtree rooted at child
        TreeNode[] nodeList = enumerateNodes(node, child);
        // Then, build a new subtree from that list
        TreeNode newChild = buildTree(nodeList);
        // Finally, replace the specified child with the new subtree
        if (child == Child.LEFT) {
            node.left = newChild;
        } else if (child == Child.RIGHT) {
            node.right = newChild;
        }
    }

    /**
    * Inserts a key into the tree
    *
    * @param key the key to insert
    */
    public void insert(int key) {
        if (root == null) {
            root = new TreeNode(key);
            return;
        }

        TreeNode node = root;

        while (true) {
            if (key <= node.key) {
                if (node.left == null) break;
                node = node.left;
            } else {
                if (node.right == null) break;
                node = node.right;
            }
        }

        if (key <= node.key) {
            node.left = new TreeNode(key);
        } else {
            node.right = new TreeNode(key);
        }
    }


    // Simple main function for debugging purposes
    public static void main(String[] args) {
        SGTree tree = new SGTree();
        for (int i = 0; i < 100; i++) {
            tree.insert(i);
        }
        tree.rebuild(tree.root, Child.RIGHT);
    }
}
