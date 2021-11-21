package com.github.kefaming.uid.impl;

import com.github.kefaming.uid.utils.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.concurrent.TimeUnit;

@ConfigurationProperties(prefix = "uid")
public class UidProperties {
    private int timeBits = 29;
    private int workerBits = 21;
    private int seqBits = 13;
    private String epochStr = "2016-09-20";
    private long epochSeconds = TimeUnit.MILLISECONDS.toSeconds(1463673600000L);

    public int getTimeBits() {
        return timeBits;
    }

    public void setTimeBits(int timeBits) {
        if(timeBits > 0){
            this.timeBits = timeBits;
        }
    }

    public int getWorkerBits() {
        return workerBits;
    }

    public void setWorkerBits(int workerBits) {
        if(workerBits > 0){
            this.workerBits = workerBits;
        }
    }

    public int getSeqBits() {
        return seqBits;
    }

    public void setSeqBits(int seqBits) {
        if(seqBits > 0){
            this.seqBits = seqBits;
        }
    }

    public String getEpochStr() {
        return epochStr;
    }

    public void setEpochStr(String epochStr) {
        if(StringUtils.isNotBlank(epochStr)){
            this.epochStr = epochStr;
            this.epochSeconds = TimeUnit.MILLISECONDS.toSeconds(DateUtils.parseByDayPattern(epochStr).getTime());
        }
    }

    public long getEpochSeconds() {
        return epochSeconds;
    }

    public void setEpochSeconds(long epochSeconds) {
        this.epochSeconds = epochSeconds;
    }
}
