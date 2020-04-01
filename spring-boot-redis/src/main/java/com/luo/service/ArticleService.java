package com.luo.service;

import com.luo.entity.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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

        Set<String> res = redisTemplate.opsForZSet().range(key, 0, -1);
        Iterator it = res.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }


        return null;
    }
}
