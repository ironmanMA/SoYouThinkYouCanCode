package com.ironman.ma.DataStructures.Tree.self.balancing.tree;

import com.ironman.ma.DataStructures.Tree.TreeNode;

/**
 * Created by 127.0.0.1.ma on 01/10/17.
 */
public class Solution {

    private static int getHeight(TreeNode treeNode) {
        if (treeNode == null)
            return -1;
        else
            return treeNode.ht;
    }

    private static TreeNode Balance(TreeNode root) {
        if (getHeight(root.left) > getHeight(root.right)) {
            // overall left heavy, check for num rotations
            int localHeightRight = getHeight(root.left.right),
                    localHeightLeft = getHeight(root.left.left);
            // check heavyness of left child.
            if (localHeightLeft > localHeightRight) {
                // heavy on left, one rotation
                TreeNode newRoot = root.left;
                root.left = newRoot.right;
                newRoot.right = root;
                // recalculate heights for oldRoot and newRoot.
                root.ht = Math.max(getHeight(root.left), getHeight(root.right)) + 1;
                newRoot.ht = Math.max(getHeight(newRoot.left), getHeight(newRoot.right)) + 1;
                return newRoot;
            } else {
                // heavy on right, two rotations
                TreeNode newRoot = root.left.right;
                TreeNode leftChild = root.left;

                leftChild.right = newRoot.left;
                root.left = newRoot.right;
                newRoot.left = leftChild;
                newRoot.right = root;

                root.ht = Math.max(getHeight(root.left), getHeight(root.right)) + 1;
                leftChild.ht = Math.max(getHeight(leftChild.left), getHeight(leftChild.right)) + 1;
                newRoot.ht = Math.max(getHeight(newRoot.left), getHeight(newRoot.right)) + 1;
                return newRoot;
            }
        } else {
            // over all right heavy, check for num rotations
            int localHeightRight = getHeight(root.right.right),
                    localHeightLeft = getHeight(root.right.left);
            // check heavyness of right child.
            if (localHeightRight > localHeightLeft) {
                //heavy on right, one rotation
                TreeNode newRoot = root.right;
                root.right = newRoot.left;
                newRoot.left = root;
                root.ht = Math.max(getHeight(root.left), getHeight(root.right)) + 1;
                newRoot.ht = Math.max(getHeight(newRoot.left), getHeight(newRoot.right)) + 1;
                return newRoot;
            } else {
                // two rotations
                TreeNode rightChild = root.right;
                TreeNode newRoot = rightChild.left;

                root.right = newRoot.left;
                rightChild.left = newRoot.right;

                newRoot.left = root;
                newRoot.right = rightChild;

                root.ht = Math.max(getHeight(root.left), getHeight(root.right)) + 1;
                rightChild.ht = Math.max(getHeight(rightChild.left), getHeight(rightChild.right)) + 1;
                newRoot.ht = Math.max(getHeight(newRoot.left), getHeight(newRoot.right)) + 1;
                return newRoot;
            }
        }

    }


    static TreeNode insert(TreeNode root, int val) {
        if (root == null) {
            TreeNode treeNode = new TreeNode();
            treeNode.val = val;
            treeNode.ht = 0;
            return treeNode;
        }
        if (val < root.val)
            root.left = insert(root.left, val);
        else
            root.right = insert(root.right, val);

        int heightRight = getHeight(root.right),
                heightLeft = getHeight(root.left);
        if (Math.abs(heightLeft - heightRight) <= 1) {
//            not balancing needed
            root.ht = Math.max(heightLeft, heightRight) + 1;
            return root;
        } else {
//        balance the tree
            return Balance(root);
        }
    }

    public static void main(String[] args) {
        TreeNode root = insert(null, 5);
        root = insert(root, 2);
        root = insert(root, 6);
        root = insert(root, 4);

        System.out.println(root);

        root = insert(root, 3);

        System.out.println(root);
    }

}
