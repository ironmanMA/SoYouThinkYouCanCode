package com.ironman.ma.LinkedList;

/**
 * Created by 127.0.0.1.ma on 15/09/17.
 */
public class Node {
    public int data;
    public Node next;

    public Node(int data) {
        this.data = data;
    }

    public static void printList(Node node) {
        if (node != null) {
            System.out.print(node.data + ", ");
            printList(node.next);
        } else {
            System.out.println("");
        }
    }

    int GetNode(Node head, int n) {
        /*
            print node the is n from the end
         */
        Node curr = head, offset = head;
        for (int i = 0; i < n; i++) {
            offset = offset.next;
        }
        while (true) {
            if (offset.next == null) {
                return curr.data;
            } else {
                curr = curr.next;
                offset = offset.next;
            }
        }
    }

    Node RemoveDuplicates(Node head) {
        /*
            remove duplicate nodes
         */
        if (head != null) {
            Node currNode = head;
            while (true) {
                if (currNode.next != null) {
                    if (currNode.next.data == currNode.data) {
                        /*
                            get rid on currNode.next
                         */
                        currNode.next = currNode.next.next;
                    } else {
                        currNode = currNode.next;
                    }
                } else {
                    return head;
                }
            }
        }
        return null;
    }

    boolean hasCycle(Node head) {
        /*
            find if node has a cycle
         */
        Node slow = head;
        Node fast = head;
        if (head != null && head.next != null && head.next.next != null) {
            fast = head.next.next;
            while (true) {
                if (slow == fast) {
                    return true;
                }
                slow = slow.next;
                if (fast.next != null && fast.next.next != null) {
                    fast = fast.next.next;
                } else {
                    return false;
                }
            }
        }
        return false;
    }
}
