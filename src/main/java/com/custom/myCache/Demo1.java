package com.custom.myCache;

import com.custom.cache.DBHelper;
import com.custom.cache.Vehicle;


import java.util.ArrayList;
import java.util.List;


public class Demo1 {
    static int cacheHit = 0;

    public static void main(String[] args) {

        CustomCache cache = new CustomCache(3);
        //Vehicle vehicle = null;
        DBHelper helper = new DBHelper();
        long start = System.currentTimeMillis();
        System.out.println("Start");
        List ids = new ArrayList();
        ids = createTestRequest();

        ids.forEach(id -> {
            Vehicle vehicle = null;
            System.out.println("From lambda --->" + id);
            boolean isCacheEnabled = true;
            if (cache.get(id) != null && isCacheEnabled) {
                System.out.println("Accessing from cache");
                vehicle = (Vehicle) cache.get(id);
                cacheHit++;
            } else {
                System.out.println("Accessing from db");
                vehicle = helper.getVehicleInfoById((Integer) id);
                cache.put(Integer.valueOf((Integer) id), vehicle);
            }
            long endItr = System.currentTimeMillis();
            long total = endItr - start;

            System.out.println("Total time for each itr  -->" + total);
            System.out.println("Total cache hit === " + cacheHit);
            System.out.println(vehicle.getId() + "---" + vehicle.getMake() + "---" + vehicle.getModel() + "---" + vehicle.getYear() + "---" + vehicle.getDrive());
        });

        long end = System.currentTimeMillis();
        long total = end - start;
        System.out.println("End");
        System.out.println("Total time ===>" + total);

    }

    private static List createTestRequest() {
        TestVehicles testVehicles = new TestVehicles();
        return testVehicles.vehicleIds;
    }
}






