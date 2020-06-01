package com.ithema;
import org.junit.Test;
import redis.clients.jedis.Jedis;
public class JedisTest {
    @Test
    public void testJedis(){
        Jedis jedis=new Jedis("127.0.0.1",6379);
        //jedis.set("name","jedis");
        //jedis.get("name");
        String name=jedis.get("name");
       System.out.println(name);
        jedis.close();
    }
}
