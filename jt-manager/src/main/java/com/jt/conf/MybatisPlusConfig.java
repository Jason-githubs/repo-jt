package com.jt.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;

@Configuration	//标识我是一个配置类
public class MybatisPlusConfig {
	
	// 将分页的拦截器交给spring容器管理.
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		
		return new PaginationInterceptor();
	}
	
	
}
