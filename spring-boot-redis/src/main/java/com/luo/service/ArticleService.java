package com.luo.service;

import com.luo.entity.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

@Slf4j
@Service
public class ArticleService {

    @Resource
    RedisTemplate redisTemplate;
    private static final String key = "rank";

    public List<Article> sortByLikedNum() {
        List<Article> articles = new ArrayList<>();
        articles.add(new Article(1L, "a", 21));
        articles.add(new Article(1L, "b", 33));
        articles.add(new Article(1L, "c", 11));
        articles.add(new Article(1L, "d", 7));
        ZSetOperations op = redisTemplate.opsForZSet();
        for (Article article : articles) {
            op.add(key, article.getName(), -article.getLikedNum().doubleValue());
        }
        Set<String> res = redisTemplate.opsForZSet().range(key, 0, 2);
        // topN
        Set<String> res2 = redisTemplate.opsForZSet().reverseRange(key, 0, 2);
        Iterator it = res.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        System.out.println("---");
        Iterator it2 = res2.iterator();
        while (it2.hasNext()) {
            System.out.println(it2.next());
        }
        return null;
    }

    // 去重
    public void distinctIP() {
        final String logKey = "loglog：";
        String[] arr = new String[20];
        for (int i = 0; i < 20; i++) {
            arr[i] = "A" + new Random().nextInt(10) + 1;
        }
        // 超级日志，比集合更节省内存
        redisTemplate.opsForHyperLogLog().add(logKey, arr);
        System.out.println(redisTemplate.opsForHyperLogLog().size(logKey));
    }

    public void getSharedFriends() {
        final String myKey = "f1";
        final String otherKey = "f2";
        final String sharedKey = "f1:f2";
        // f1的好友 有 a1,a2,a3
        redisTemplate.opsForSet().add(myKey, "a1", "a2", "a3");
        // f2的好友 有 a5,a2,a3,a4
        redisTemplate.opsForSet().add(otherKey, "a5", "a2", "a3", "a4");
        //交集
        Set<String> shared = redisTemplate.opsForSet().intersect(myKey, otherKey);
        redisTemplate.opsForSet().intersectAndStore(myKey, otherKey, sharedKey);
        Set<String> members = redisTemplate.opsForSet().members(sharedKey);
        // 差集
        Set<String> aDiff = redisTemplate.opsForSet().difference(myKey, otherKey);
        Set<String> bDiff = redisTemplate.opsForSet().difference(otherKey, myKey);
        Iterator<String> it = shared.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        System.out.println("--");
        Iterator<String> it2 = members.iterator();
        while (it2.hasNext()) {
            System.out.println(it2.next());
        }

        System.out.println("--差集");
        Iterator<String> it3 = bDiff.iterator();
        while (it3.hasNext()) {
            System.out.println(it3.next());
        }
        System.out.println(redisTemplate.opsForSet().isMember(myKey, "y"));
    }

    public void scan() {
        final String scanKey = "scan:";
        redisTemplate.opsForSet().add(scanKey, "a1", "a2", "a3", "a4", "a5");
        try {
            Cursor<String> cursor = redisTemplate.opsForSet().scan(scanKey,
                    ScanOptions.scanOptions().match("*").count(3).build());
            while (cursor.hasNext()) {
                System.out.println(cursor.next());
            }
            cursor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void activeUser() {
        final String ACTIVE_USER = "active_user";
        Integer userId = new Random().nextInt(10);
        redisTemplate.opsForValue().setBit(ACTIVE_USER, userId, true);
        //  统计
        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
        Long count = connection.bitCount(ACTIVE_USER.getBytes());
        System.out.println("count:" + count);
    }

}
