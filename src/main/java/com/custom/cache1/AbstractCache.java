package com.custom.cache1;

import java.util.HashMap;
import java.util.LinkedList;
 
public abstract class AbstractCache implements ICache {
    int cacheSize;
    HashMap map;
    LinkedList list;
 
    public AbstractCache(int cacheSize) {
        this.cacheSize = cacheSize;
        map = new HashMap(cacheSize);
        list = new LinkedList();
    }
 
    public abstract void put(Object key, Object val);
 
    public abstract Object get(Object key);
 
    public void invalidate(Object key) {
        list.remove(key);
        map.remove(key);
    }
 
    public void printKeyOrder() {
        System.out.println("KeyOrder() " + list + " Cache content: " + map);
    }
 
    public void prune()// removes the tail
    {
        Object key = list.removeLast();
        map.remove(key);
    }





}