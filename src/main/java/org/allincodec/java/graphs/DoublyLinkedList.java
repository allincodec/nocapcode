package org.allincodec.java.graphs;

public class DoublyLinkedList<T> {

    private Node head;
    private Node tail;
    private int size;

    public DoublyLinkedList() {
    }

    public Node head() {
        return head;
    }

    public Node tail() {
        return tail;
    }

    public int size() {
        return size;
    }

    public class Node {
        private final T data;
        private Node prevNode;
        private Node nextNode;


        public Node nextNode() {
            return nextNode;
        }

        public Node prevNode() {
            return prevNode;
        }

        public Node(T data) {
            this.data = data;
        }

        public T data() {
            return data;
        }
    }

    public boolean isEmpty() {
        return head() == null && tail() == null;
    }

    public void insertAtHead(T data) {
        Node newNode = new Node(data);
        newNode.nextNode = head;
        newNode.prevNode = null;

        if (head != null) {
           head.prevNode = newNode;
        } else {
            tail = newNode;
        }

        newNode.prevNode = null;
        head = newNode;
        size++;
    }

    public void insertAtTail(T dest) {
        if (isEmpty()) {
            insertAtHead(dest);
            return;
        }
        Node newNode = new Node(dest);
        newNode.prevNode = tail;
        tail.nextNode = newNode;
        tail = newNode;
        size++;
    }


    public void printList() {
        Node current = head;
        if(isEmpty()) return;
        System.out.print("List \n null <-- ");
        while(current != null) {
            System.out.print(current.data + " <--> ");
            current = current.nextNode;
        }
        System.out.print(" --> null");
    }


}
