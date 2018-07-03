package com.custom.cache1;

public class CustomCache extends AbstractCache{

    //LRUCache lruCache=null;
    public MRUCache mruCache=null;

    public CustomCache(int cacheSize) {
        super(cacheSize);
        this.mruCache=new MRUCache(cacheSize);
    }

    public void put(Object key, Object val) {
        // check if pruning is needed
        if (list.size() == this.cacheSize) {
            this.prune();
        }
        list.addFirst(key);
        map.put(key, val);
    }

    public Object get(Object key) {
        boolean res = list.remove(key);
        if (res) {
            list.addFirst(key);
            return map.get(key);
        }
        return null;
    }

    public void prune()// removes the tail
    {
        Object key = list.removeLast();
       Object val=map.remove(key);
        if (mruCache.list.size() == this.cacheSize) {
            mruCache.prune();
        }
        mruCache.list.addLast(key);
        mruCache.map.put(key, val);
    }
}
