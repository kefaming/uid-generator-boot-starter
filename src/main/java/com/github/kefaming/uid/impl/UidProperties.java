package com.github.kefaming.uid.impl;

import com.github.kefaming.uid.utils.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@ConfigurationProperties(prefix = "uid")
public class UidProperties {
    private int timeBits = 29;
    private int workerBits = 21;
    private int seqBits = 13;
    private String epochStr = "2016-09-20";


}
