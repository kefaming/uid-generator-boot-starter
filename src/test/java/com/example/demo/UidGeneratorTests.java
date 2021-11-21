package com.example.demo;

import com.github.wujun234.uid.UidGenerator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
@Slf4j
public class UidGeneratorTests {

    @Resource
    private UidGenerator defaultUidGenerator;

    @Resource
    private UidGenerator cachedUidGenerator;

    @Test
    public void testSerialGenerate() {
        // Generate UID
        long uid = defaultUidGenerator.getUID();

        // Parse UID into [Timestamp, WorkerId, Sequence]
        // {"UID":"450795408770","timestamp":"2019-02-20 14:55:39","workerId":"27","sequence":"2"}
        System.out.println(uid);
    }

//    @Test
    public void testSerialGenerate2() {
        // Generate UID
        long uid = cachedUidGenerator.getUID();

        // Parse UID into [Timestamp, WorkerId, Sequence]
        // {"UID":"450795408770","timestamp":"2019-02-20 14:55:39","workerId":"27","sequence":"2"}
        System.out.println(cachedUidGenerator.parseUID(uid));
    }

}
