package com.ssafy.mytown.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = {"com.ssafy.**.mapper"})
public class WebMvcConfiguration {
	
	//interceptor 설정 (implements WebMvcConfigurer)
	
}
