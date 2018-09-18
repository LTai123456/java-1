package com.jt.common.config;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 使用此配置类替换spring-configs.xml
 * 
 * @author lt
 *
 */
@Configuration
@PropertySource(value="classpath:configs.properties")
@ComponentScan(value="com.jt")
@MapperScan(basePackages="com.jt.**.dao")
public class AppRootConfig {

	 @Bean
	 public PropertySourcesPlaceholderConfigurer newPropertyPlaceholderConfigurer(){
		 return new PropertySourcesPlaceholderConfigurer();
	 }
	@Bean(value = "dataSource", initMethod = "init", destroyMethod = "close")
	@Lazy(false)
	public DataSource newDataSource(
			@Value("${jdbcDriver}") String driver, 
			@Value("${jdbcUrl}") String url,
			@Value("${jdbcUser}") String user, 
			@Value("${jdbcPassword}") String password) {
		DruidDataSource ds = new DruidDataSource();
		ds.setDriverClassName(driver);
		ds.setUrl(url);
		ds.setUsername(user);
		ds.setPassword(password);
		return ds;
	}
	@Bean(value="sqlSessionFactory")
	public SqlSessionFactoryBean newSqlSessionFactoryBean(
			@Autowired DataSource dataSource
			) throws Exception{
		SqlSessionFactoryBean 
			sqlSessionFactoryBean=new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		Resource[] mapperLocations=new PathMatchingResourcePatternResolver()
				.getResources("classpath*:mapper/*Mapper.xml");
		sqlSessionFactoryBean.setMapperLocations(mapperLocations);
		return sqlSessionFactoryBean;
	}
}
