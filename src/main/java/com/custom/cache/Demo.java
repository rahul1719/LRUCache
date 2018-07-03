package com.custom.cache;

import com.custom.cache1.CustomCache;
import com.custom.cache1.LRUCache;
import com.custom.cache1.MRUCache;
import com.java.cache.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Demo {
    static int cacheHit=0;
    public static void main(String[] args) {
       // LRUCacheImpl lruCache = new LRUCacheImpl(3, 0.75f);
      // LRUCache lruCache = new LRUCache(3);
        //MRUCache mruCache = new MRUCache(3);
        CustomCache cache = new CustomCache(3);
        Vehicle vehicle = null;
        DBHelper helper = new DBHelper();
        long start = System.currentTimeMillis();
        System.out.println("Start");
        //helper.getAllVehicleInfo();
        //Scanner scanner = new Scanner(System.in);
        //String id=scanner.next();
        //Vehicle vehicle= helper.getVehicleInfoById(Integer.parseInt(id));
        //int id = 25533;
        List ids= new ArrayList();
        ids.add(25533);
        ids.add(25555);
        ids.add(25556);
        ids.add(25533);
        ids.add(25509);
        ids.add(25533);
        ids.add(25512);
        ids.add(25555);
        ids.add(18451);
        ids.add(25533);
        ids.add(18451);

        for (int i = 0; i < ids.size(); i++) {
            boolean isCacheEnabled=true;
            if (cache.get(ids.get(i))!=null  && isCacheEnabled) {
                System.out.println("Accessing from cache");
                vehicle = (Vehicle) cache.get(ids.get(i));
                cacheHit++;
            }
            else if(cache.mruCache.get(ids.get(i))!=null && isCacheEnabled){
                System.out.println("Accessing from L2 cache");
                vehicle = (Vehicle) cache.mruCache.get(ids.get(i));
                cacheHit++;
            }
            else {
                System.out.println("Accessing from db");
                vehicle = helper.getVehicleInfoById((Integer) ids.get(i));
                cache.put(Integer.valueOf((Integer) ids.get(i)), vehicle);
            }


            long endItr = System.currentTimeMillis();
            long total = endItr - start;
            //System.out.println("End");
            System.out.println("Total time for each itr  == "+ i +"-->"+ total);
            //System.out.println("Cache size==" + lruCache.size());
            System.out.println("Total cache hit === "+cacheHit);
            System.out.println(vehicle.getId() + "---" + vehicle.getMake() + "---" + vehicle.getModel() + "---" + vehicle.getYear() + "---" + vehicle.getDrive());

        }
        long end = System.currentTimeMillis();
        long total = end - start;
        System.out.println("End");
        System.out.println("Total time ===>"+ total);

    }
}






