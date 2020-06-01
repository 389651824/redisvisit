package com.hhh;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisException;

import java.util.concurrent.TimeUnit;

public class Service {
    public void service(String name) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        String value = jedis.get("name:" + name);
        try {
            if (value == null) {
                jedis.setex("name:" + name, 60, Long.MAX_VALUE - 5 + "");
            } else {
                long val=jedis.incr("name:"+name);
                visit();
            }
        } catch (JedisException e) {
            System.out.println("已到达访问次数，请稍后再试");
            return;
        }finally {
            jedis.close();
        }
    }
    public void visit(){
        System.out.println("已访问");
    }
}
class MyThred extends Thread{
    Service sr=new Service();
    public void run(){
        while (true){
            sr.service("user");
            try {
                Thread.sleep(1000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class Main{
    public static void main(String[] args){
        MyThred mt=new MyThred();
        mt.start();
    }
}
