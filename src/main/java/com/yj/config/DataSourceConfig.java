package com.yj.config;

import java.util.Properties;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource({"classpath:datasource.properties"})
public class DataSourceConfig {
	@Autowired
	private Environment env;
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean s = new LocalSessionFactoryBean();
		s.setDataSource(getDataSource());
		s.setPackagesToScan("com.yj");
		s.setHibernateProperties(hibernateProperties());
		return s;
	}
	
	@SuppressWarnings("serial")
	private Properties hibernateProperties() {
		return new Properties() {
			{
				setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
				setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
			}
		};
	}

	@Bean
	public DataSource getDataSource() {
		DataSource ds = new DataSource();
		ds.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		ds.setUrl(env.getProperty("jdbc.url"));
		ds.setUsername(env.getProperty("jdbc.user"));
		ds.setPassword(env.getProperty("jdbc.pass"));
		ds.setTestOnBorrow(true); //해당 Connection이 유효한지 요효성 검사를 할것인지 여부를 결정
		ds.setTestOnConnect(true);
		ds.setTestOnReturn(false); //유효성 검사 시점이 pool을 반환할때 수행한다
		ds.setTestWhileIdle(true); //Connection의 유효성 테스트를 할것인가 결정하는 설정
		ds.setInitialSize(4);
		ds.setMaxIdle(20);
		ds.setMinIdle(4);
		ds.setMaxActive(64);
		ds.setValidationQuery("SELECT 1");
		ds.setValidationInterval(30000);
		return ds;
	}

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);
		return txManager;
	}
}
