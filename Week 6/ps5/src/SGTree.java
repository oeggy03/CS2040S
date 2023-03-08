import com.sun.source.tree.Tree;

/**
 * ScapeGoat Tree class
 * <p>
 * This class contains some basic code for implementing a ScapeGoat tree. This version does not include any of the
 * functionality for choosing which node to scapegoat. It includes only code for inserting a node, and the code for
 * rebuilding a subtree.
 */

public class SGTree {

    // Designates which child in a binary tree
    enum Child {LEFT, RIGHT}

    //count for the enumerate function
    private static int count = 0;
    /**
     * TreeNode class.
     * <p>
     * This class holds the data for a node in a binary tree.
     * <p>
     * Note: we have made things public here to facilitate problem set grading/testing. In general, making everything
     * public like this is a bad idea!
     */
    public static class TreeNode {
        int key;
        int weight;
        public TreeNode left = null;
        public TreeNode right = null;
        TreeNode(int k) {
            key = k;
            weight = 1;
        }
    }

    // Root of the binary tree
    public TreeNode root = null;

    /**
     * Counts the number of nodes in the specified subtree.
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
        if (currRoot == null) {
            return 0;
        } else {
            return 1 + countNodes(currRoot, Child.LEFT) + countNodes(currRoot, Child.RIGHT);
        }
    }

    /**
     * Builds an array of nodes in the specified subtree.
     *
     * @param node  the parent node, not to be included in returned array
     * @param child the specified subtree
     * @return array of nodes
     */
    public TreeNode[] enumerateNodes(TreeNode node, Child child) {
        TreeNode[] treeNodes = new TreeNode[countNodes(node, child)];
        if (child == Child.LEFT) {
            enumerateHelper(node.left, treeNodes);
        } else if (child == Child.RIGHT) {
            enumerateHelper(node.right, treeNodes);
        }

        //static variable so need to reset it
        this.count = 0;
        return treeNodes;
    }

    private void enumerateHelper(TreeNode node, TreeNode[] arrays){
        if (node == null) {
            return;
        } else if (node.left == null && node.right == null) {
            arrays[this.count++] = node;
        } else if (node.left == null) {
            arrays[this.count++] = node;
            enumerateHelper(node.right, arrays);
        } else if (node.right == null) {
            enumerateHelper(node.left, arrays);
            arrays[this.count++] = node;
        } else {
            enumerateHelper(node.left, arrays);
            arrays[this.count++] = node;
            enumerateHelper(node.right, arrays);
        }
    }

    /**
     * Builds a tree from the list of nodes Returns the node that is the new root of the subtree
     *
     * @param nodeList ordered array of nodes
     * @return the new root node
     */
    TreeNode buildTree(TreeNode[] nodeList) {
        return buildTreeHelper(nodeList, 0, nodeList.length - 1);
    }

    private TreeNode buildTreeHelper(TreeNode[] nodeList, int start, int end) {
        // Base case: empty subtree
        if (start > end) return null;

        // Find the middle node
        int mid = (start + end) / 2;
        TreeNode root = nodeList[mid];

        // Recursively build the left and right subtrees
        root.left = buildTreeHelper(nodeList, start, mid - 1);
        root.right = buildTreeHelper(nodeList, mid + 1, end);

        return root;
    }

    /**
     * Determines if a node is balanced. If the node is balanced, this should return true. Otherwise, it should return
     * false. A node is unbalanced if either of its children has weight greater than 2/3 of its weight.
     *
     * @param u a node to check balance on
     * @return true if the node is balanced, false otherwise
     */
    public boolean checkBalance(TreeNode u) {
        if (u == null) {
            return true; // an empty subtree is considered balanced
        }
        int leftWeight = u.left != null ? u.left.weight : 0;
        int rightWeight = u.right != null ? u.right.weight : 0;
        int totalWeight = leftWeight + rightWeight + 1;

        if (u.left != null && u.left.weight > (totalWeight * 2 / 3)
            || u.right != null && u.right.weight > (totalWeight * 2 / 3)) {
            return false;
        }

        return true;
    }

    /**
     * Rebuilds the specified subtree of a node.
     *
     * @param node  the part of the subtree to rebuild
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
        fixWeights(node, child);
    }

    public void fixWeights(TreeNode node, Child child) {
        if (node == null) { return; }
        if (child == Child.LEFT) {
            node.weight = 1 + (node.left != null ? node.left.weight : 0) + (node.right != null ? node.right.weight : 0);
            fixWeights(node.left, Child.LEFT);
            fixWeights(node.right, Child.RIGHT);
        } else if (child == Child.RIGHT) {
            node.weight = 1 + (node.left != null ? node.left.weight : 0) + (node.right != null ? node.right.weight : 0);
            fixWeights(node.right, Child.RIGHT);
            fixWeights(node.left, Child.LEFT);
        }
    }

    /**
     * Inserts a key into the tree.
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
            node.weight++;

            if (key <= node.key) {
                if (node.left == null) {
                    node.left = new TreeNode(key);
                    break;
                }
                node = node.left;
            } else {
                if (node.right == null) {
                    node.right = new TreeNode(key);
                    break;
                }
                node = node.right;
            }
        }

        // this checks if nodes are balanced, so that we can rebuild
        // we start from root node
        insertRebuild(root, key);
    }

    /**
     * Helps the insert function identify where it should start rebuilding.
     *
     * @param key the key to insert
     */
    private void insertRebuild(TreeNode node, int key) {
        //Checks if node is null or node is the inserted node
        if (node == null || node.key == key) {
            return;
        }

        if (key <= node.key) {
            if (checkBalance(node.left)) {
                insertRebuild(node.left, key);
            } else {
                rebuild(node, Child.LEFT);
            }
        } else {
            if (checkBalance(node.right)) {
                insertRebuild(node.right, key);
            } else {
                rebuild(node, Child.RIGHT);
            }
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
