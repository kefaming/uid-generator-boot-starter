package com.github.kefaming.uid.config;

import com.github.kefaming.uid.config.UidProperties;
import com.github.kefaming.uid.impl.CachedUidGenerator;
import com.github.kefaming.uid.impl.DefaultUidGenerator;
import com.github.kefaming.uid.worker.DisposableWorkerIdAssigner;
import com.github.kefaming.uid.worker.WorkerIdAssigner;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
@ConditionalOnClass({ DefaultUidGenerator.class, CachedUidGenerator.class })
@MapperScan({ "com.github.kefaming.uid.worker.dao" })
@EnableConfigurationProperties(UidProperties.class)
public class UidAutoConfigure {

	@Autowired
	private UidProperties uidProperties;

	@Bean
	@ConditionalOnMissingBean
	@Lazy
	DefaultUidGenerator defaultUidGenerator() {
		return new DefaultUidGenerator(uidProperties);
	}

	@Bean
	@ConditionalOnMissingBean
	@Lazy
	CachedUidGenerator cachedUidGenerator() {
		return new CachedUidGenerator(uidProperties);
	}

	@Bean
	@ConditionalOnMissingBean
	WorkerIdAssigner workerIdAssigner() {
		return new DisposableWorkerIdAssigner();
	}
}
