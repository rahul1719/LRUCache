package com.custom.redis;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.api.sync.RedisListCommands;

/**
 * @author Mark Paluch
 */
public class ConnectToRedis {

  public static void main(String[] args) {

    // Syntax: redis://[password@]host[:port][/databaseNumber]
    RedisClient redisClient = RedisClient.create("redis://localhost:6379/0");
    StatefulRedisConnection<String, String> connection = redisClient.connect();
    RedisCommands<String, String> commands = connection.sync();
    //RedisCommands<Object,Object> commands = connection.sync();
    commands.set("1","foo");
    String value = commands.get("1");
    System.out.println(value);
    System.out.println("Connected to Redis");

    connection.close();
    redisClient.shutdown();
  }
}