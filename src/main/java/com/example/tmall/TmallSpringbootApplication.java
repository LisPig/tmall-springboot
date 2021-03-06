package com.example.tmall;

import com.example.tmall.util.PortUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableCaching
@EnableElasticsearchRepositories(basePackages = "com.example.tmall.es")
@EnableJpaRepositories(basePackages = {"com.example.tmall.dao","com.example.tmall.pojo"})
public class TmallSpringbootApplication {
	static{
		PortUtil.checkPort(6379,"Redis 服务端",true);
		PortUtil.checkPort(9300,"ElasticSearch 服务端",true);
		PortUtil.checkPort(5601,"kibana工具",true);
	}

	public static void main(String[] args) {
		SpringApplication.run(TmallSpringbootApplication.class, args);
	}

}
