package jp.catalyna.controller;

import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.api.StatefulRedisConnection;
import com.lambdaworks.redis.api.async.RedisStringAsyncCommands;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by ishida on 2017/05/02.
 */
@Controller
public class RedisController {
    private static final String KEY = "mykey";
    private static final long LIMIT = 10000;
    private static final Logger LOGGER = LogManager.getLogger(RedisController.class);
    private static final JedisPool jPool = new JedisPool(new JedisPoolConfig(), "localhost");

    private static final RedisClient client = RedisClient.create("redis://localhost");
    private static final StatefulRedisConnection<String, String> connection = client.connect();

    @RequestMapping(value = "/redis/sync")
    public String sync() {
        Jedis jedis = jPool.getResource();
        Long count = jedis.incr(KEY);
        if (count == LIMIT) {
            LOGGER.info("SYNC: count equals to " + LIMIT);
            jedis.set(KEY, "0");
        }
        jedis.close();
        return "Ok";
    }

    @RequestMapping(value = "/redis/async")
    public String async() {
        RedisStringAsyncCommands<String, String> async = connection.async();
        async.incr(KEY).thenAccept(count -> {
            if (count == LIMIT) {
                LOGGER.info("ASYNC: count equals to " + LIMIT);
                async.set(KEY, "0");
            }
        });
        return "Ok";
    }
}
