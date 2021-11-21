package com.example.demo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.mapper.StorageMapper;
import com.example.demo.model.Storage;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * MyBatis-Plus测试
 */
@SpringBootTest
@Slf4j
class MyBatisPlusTests {
    @Resource
    private StorageMapper storageMapper;

    // 测试MyBatis-Plus分页
    @Test
    public void queryUserForPage(){
        IPage<Storage> userPage = new Page<>(1, 2);//参数一是当前页，参数二是每页个数
        userPage = storageMapper.selectPage(userPage, null);
        List<Storage> list = userPage.getRecords();
        for(Storage storage : list){
            System.out.println(storage);
        }
    }

    // 测试MyBatis-Plus
    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<Storage> storageList = storageMapper.selectList(null);
        Assertions.assertEquals(1, storageList.size());
        storageList.forEach(System.out::println);

        Storage s = storageMapper.selectById("1");
        System.out.println(s);
    }
}
