package com.custom.myCache;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * The type My linked list.
 *
 * @param <T> the type parameter
 *
 * @author rahulreddy
 */
public class MyLinkedList<T> {

    private Node<T> head;
    private Node<T> tail;
    private AtomicInteger count= new AtomicInteger(0) ;



    /**
     * Instantiates a new My linked list.
     */
    public MyLinkedList() {
    }

    /**
     * Instantiates a new My linked list.
     *
     * @param head the head
     */
    public MyLinkedList(Node<T> head) {
        this.head = head;
        getCount().getAndIncrement();
        //count++;
    }

    /**
     * Gets head.
     *
     * @return the head
     */
    public Node<T> getHead() {
        return head;
    }

    /**
     * Sets head.
     *
     * @param head the head
     */
    public void setHead(Node<T> head) {
        this.head = head;
    }

    /**
     * Gets tail.
     *
     * @return the tail
     */
    public Node<T> getTail() {
        return tail;
    }

    /**
     * Sets tail.
     *
     * @param tail the tail
     */
    public void setTail(Node<T> tail) {
        this.tail = tail;
    }

    /**
     * Gets count.
     *
     * @return the count
     */
    public AtomicInteger getCount() {
        return count;
    }

    /**
     * Sets count.
     *
     * @param count the count
     */
    public void setCount(AtomicInteger count) {
        this.count = count;
    }

    /**
     * Add last node.
     *
     * @param newNode the new node
     * @return the node
     */
// add node to the last
    public Node addLast(Node newNode) {
        synchronized (this){
            if (this.getHead() != null) {
                Node lastNode = this.getTail();
                if (lastNode != null) {
                    lastNode.setNext(newNode);
                    newNode.setPrev(lastNode);
                    this.setTail(newNode);

                } else {
                    this.getHead().setNext(newNode);
                    newNode.setPrev(this.getHead());
                    this.setTail(newNode);


                }
                getCount().getAndIncrement();
                return this.getHead();
            } else {
                this.setHead(newNode);
                this.setTail(newNode);
                getCount().getAndIncrement();
                return this.getHead();
            }
        }


    }

    /**
     * Add first node.
     *
     * @param obj the obj
     * @return the node
     */
// add in the beginning
    public Node addFirst(Object obj) {
        synchronized (this){
            Node headNode = this.getHead();
            Node newNode = new Node(obj);
            newNode.setNext(headNode);
            headNode.setPrev(newNode);
            this.setHead(newNode);
            getCount().getAndIncrement();
            return this.getHead();
        }

    }


    /**
     * Gets length.
     *
     * @return the length
     */
    public AtomicInteger getLength() {
        return this.getCount();
    }

    /**
     * Is empty boolean.
     *
     * @return the boolean
     */
    public boolean isEmpty() {
        return this.getHead() == null;
    }

    /**
     * Remove first node.
     *
     * @return the node
     */
// remove from front of the list
    public Node removeFirst() {
        synchronized (this){
            Node newHead = this.getHead().getNext();
            this.setHead(newHead);
            getCount().getAndDecrement();
            return this.getHead();
        }

    }

}
