package com.custom.myCache;

import com.custom.cache.DBHelper;
import com.custom.cache.Vehicle;


import java.util.ArrayList;
import java.util.List;


public class Demo1 {
    static int cacheHit=0;
    public static void main(String[] args) {

        CustomCache cache = new CustomCache(3);
        Vehicle vehicle = null;
        DBHelper helper = new DBHelper();
        long start = System.currentTimeMillis();
        System.out.println("Start");
        List ids= new ArrayList();
        ids=createTestRequest();

        for (int i = 0; i < ids.size(); i++) {
            boolean isCacheEnabled=true;
            if (cache.get(ids.get(i))!=null  && isCacheEnabled) {
                System.out.println("Accessing from cache");
                vehicle = (Vehicle) cache.get(ids.get(i));
                cacheHit++;
            }
            /*else if(cache.mruCache.get(ids.get(i))!=null && isCacheEnabled){
                System.out.println("Accessing from L2 cache");
                vehicle = (Vehicle) cache.mruCache.get(ids.get(i));
                cacheHit++;
            }*/
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

    private static List createTestRequest() {
        TestVehicles testVehicles = new TestVehicles();
    return testVehicles.vehicleIds;
    }
}






