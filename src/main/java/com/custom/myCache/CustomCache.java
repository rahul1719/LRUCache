package com.custom.myCache;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Custom cache.
 *
 * @param <T> the type parameter
 * @author rahulreddy
 */
public class CustomCache<T> {

    /**
     * The Map.
     */
    private Map<Object, Node> map;

    /**
     * The Values.
     */
    private MyLinkedList<T> list;

    /**
     * The Cache size.
     */
    private int cacheSize;


    /**
     * Instantiates a new Custom cache.
     *
     * @param cacheSize the cache size
     */
    public CustomCache(int cacheSize) {
        this.cacheSize = cacheSize;
        this.map = new HashMap<Object, Node>();
        this.list = new MyLinkedList<T>();
    }

    /**
     * Invalidate cache.
     */
    public void invalidateCache() {
        this.cacheSize = cacheSize;
        this.map = new HashMap<Object, Node>();
        this.list = new MyLinkedList<T>();
    }

    /**
     * Invalidate entry.
     *
     * @param key the key
     */
    public void invalidateEntry(Object key) {
        this.map.remove(key);
    }

    /**
     * Print key order.
     */
    public void printKeyOrder() {
        System.out.println("KeyOrder() " + this.map.entrySet());
    }

    /**
     * Put.
     *
     * @param key the key
     * @param val the val
     */
    public void put(Object key, Object val) {
        // check if pruning is needed
        if (map.size() == this.cacheSize) {
            this.prune();
        }
        Node node = new Node(val);
        node.setKeyRef(key);
        this.map.put(key, node);
        this.list.addLast(node);

    }

    /**
     * Contains boolean.
     *
     * @param key the key
     * @return the boolean
     */
    public boolean contains(Object key) {
        return this.map.containsValue(key);
    }

    /**
     * Get object.
     *
     * @param key the key
     * @return the object
     */
    public Object get(Object key) {
        Node resObj = map.get(key);
        if (resObj != null) {
            this.list.addLast(resObj);
            return resObj.getObject();
        }
        return null;
    }
    /**
     * Delete the least recently
     * used object.
     */
    private void prune() {
        Node node = this.list.getHead();
        Node tempNode = node.getNext();
        tempNode.setPrev(null);
        this.list.setHead(tempNode);
        this.map.remove(node.getKeyRef());
    }

    /**
     * Gets map.
     *
     * @return the map
     */
    public Map<Object, Node> getMap() {
        return map;
    }

    /**
     * Sets map.
     *
     * @param map the map
     */
    public void setMap(Map<Object, Node> map) {
        this.map = map;
    }

    /**
     * Gets list.
     *
     * @return the list
     */
    public MyLinkedList<T> getList() {
        return list;
    }

    /**
     * Sets list.
     *
     * @param list the list
     */
    public void setList(MyLinkedList<T> list) {
        this.list = list;
    }

    /**
     * Gets cache size.
     *
     * @return the cache size
     */
    public int getCacheSize() {
        return cacheSize;
    }

    /**
     * Sets cache size.
     *
     * @param cacheSize the cache size
     */
    public void setCacheSize(int cacheSize) {
        this.cacheSize = cacheSize;
    }
}
