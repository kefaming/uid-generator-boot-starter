package com.example.demo;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.exception.BizException;
import com.example.demo.mapper.StorageMapper;
import com.example.demo.model.Storage;
import com.example.demo.service.SerialService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 分布式ID和分布式锁
 */
@SpringBootTest
@Slf4j
class DistributedIdTests {

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private SerialService serialService;

    // 分布式ID
    @Test
    void distributedId() {

        int orderId = new Random().nextInt(1000);
        log.debug(String.valueOf(orderId));

        String key = "dec_store_lock_" + orderId;
        RLock lock = redissonClient.getLock(key);
        try {
            //加锁 操作很类似Java的ReentrantLock机制
            lock.lock(); // 也可用下面的写法
            // boolean res = lock.tryLock(3, 10, TimeUnit.SECONDS); //尝试加锁，最多等待3秒，上锁以后10秒自动解锁
            // if(res){
            //     // do your business
            // }

            Long userId = serialService.getNextValue("SEQ_USER_ID");
            System.out.println("=============== 分布式ID: " + userId + " ===============");

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        } finally {
            //解锁
            lock.unlock();
        }
    }

}
