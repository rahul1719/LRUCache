package com.custom.myCache;

public class CustomCacheImpl {




    public static void main (String[] args){

        CustomCache cache = new CustomCache(3);

        cache.put(1,"Bangalore");
        cache.put(2,"Delhi");
        cache.put(3,"Paris");
        cache.put(4,"London");
        cache.put(3,"Paris");


    }
}
