package com.custom.cache1;

public class MRUCache extends AbstractCache {
 
    public MRUCache(int size) {
        super(size);
    }
 
    @Override
    public void put(Object key, Object val) {
        // check if pruning is needed
        if (list.size() == this.cacheSize) {
            this.prune();
        }
        list.addLast(key);
        map.put(key, val);
    }
 
    @Override
    public Object get(Object key) {
        boolean res = list.remove(key);
        if (res) {
            list.addLast(key);
            return map.get(key);
        }
        return null;
    }
}