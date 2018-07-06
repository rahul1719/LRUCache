package com.custom.myCache;

import com.custom.cache.DBHelper;
import com.custom.cache.Vehicle;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;


public class Demo1 implements Runnable {
    static int cacheHit = 0;

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        IntStream.range(1,10).parallel().forEach(i->{
            try{
                Runnable worker = new Demo1();
                executor.execute(worker);
            }
            catch (Exception e){
                System.out.println("Exception at " + i);
            }
        });
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("Finished all threads");

    }

    private static void getVehicleInfo(List ids) {
        CustomCache cache = new CustomCache(3);
        DBHelper helper = new DBHelper();
        long start = System.currentTimeMillis();
        System.out.println("Start");
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

    @Override
    public void run() {
        System.out.println("Thread started --> "+ Thread.currentThread().getName());
        List ids = new ArrayList();
        ids = createTestRequest();
        getVehicleInfo(ids);
        System.out.println("Thread end --> "+ Thread.currentThread().getName());
    }
}






