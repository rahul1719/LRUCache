package com.custom.myCache;

/**
 * The type Node.
 *
 * @param <T> the type parameter
 *
 * @author rahulreddy
 */
public class Node<T> {

    private Node<T> next;
    private Node<T> prev;
    private T object;
    private T keyRef;

    /**
     * Gets key ref.
     *
     * @return the key ref
     */
    public Object getKeyRef() {
        return keyRef;
    }

    /**
     * Sets key ref.
     *
     * @param keyRef the key ref
     */
    public void setKeyRef(T keyRef) {
        this.keyRef = keyRef;
    }

    /**
     * Instantiates a new Node.
     *
     * @param object the object
     */
    public Node(T object) {
        this.object = object;
    }

    /**
     * Gets next.
     *
     * @return the next
     */
    public Node<T> getNext() {
        return next;
    }

    /**
     * Sets next.
     *
     * @param next the next
     */
    public void setNext(Node<T> next) {
        this.next = next;
    }

    /**
     * Gets prev.
     *
     * @return the prev
     */
    public Node<T> getPrev() {
        return prev;
    }

    /**
     * Sets prev.
     *
     * @param prev the prev
     */
    public void setPrev(Node<T> prev) {
        this.prev = prev;
    }

    /**
     * Gets object.
     *
     * @return the object
     */
    public T getObject() {
        return object;
    }

    /**
     * Sets object.
     *
     * @param object the object
     */
    public void setObject(T object) {
        this.object = object;
    }
}
