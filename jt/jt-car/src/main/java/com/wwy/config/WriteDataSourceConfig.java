package com.wwy.config;

import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
/**
 * 多数据源的数据源配置类（写）
 * @author wwy
 * @date 2019年12月12日
 * @version v0.0.1
 *
 * 配置数据源通常需要配置以下bean：
 * DataSource
 * SqlSessionFactory
 * SqlSessionTemplate
 */
@Primary
@Configuration
@MapperScan(basePackages = "com.wwy.**.write",
sqlSessionFactoryRef = "writeSqlSessionFactory")
public class WriteDataSourceConfig {

/**
 * 1.DataSource
 */
	//务必为bean添加名字，因为多数据源注入时需要按照名字注入，否则会引起冲突
	@Bean("writeDataSource")
	//指定yml中配置的参数的前缀
	@ConfigurationProperties("spring.datasource.write")
	public DataSource writeDataSource() {
		return DataSourceBuilder.create().build();
	}
	/**
	 * 2.SqlSessionFactory
	 * @throws Exception 
	 */
	@Bean("writeSqlSessionFactory")
	public SqlSessionFactory writeSqlSessionFactory(
			//这里注意:要按名称注入！
			@Qualifier("writeDataSource")DataSource dataSource) throws Exception {
		SqlSessionFactoryBean ssf=new SqlSessionFactoryBean();
		//指定数据源
		ssf.setDataSource(dataSource);
		//指定xml位置
		ssf.setMapperLocations(
				new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/**/write/*.xml")
				);
		return ssf.getObject();		
	}
	/**
	 * 3.SqlSessionTemplate
	 */
	@Bean("writeSqlSessionTemplate")
	public SqlSessionTemplate writeSqlSessionTemplate(@Qualifier("writeSqlSessionFactory")SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}
