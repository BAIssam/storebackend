package com.iba.storebackend.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan({"com.iba.storebackend.dto"})
@EnableTransactionManagement
public class hibernateConfig {
	//Si la BD et l'application se trouvent sur le meme serveur on utilise localhost
	//Si la BD se trouve sur un autre serveur on met l'adresse du serveur BD
	//private final static String DATABSE_URL="jdbc:mysql://41.226.0.61:3306/onlinestore";

	private final static String DATABSE_URL="jdbc:mysql://localhost:3306/onlinestore";
	private final static String DATABSE_DRIVER="com.mysql.jdbc.Driver";
	private final static String DATABSE_DIALECT="org.hibernate.dialect.MySQLDialect";
	private final static String DATABSE_USERNAME="root";
	private final static String DATABSE_PASSWORD="password";
	
	// datasource bean will be available
	@Bean("dataSource")
	public DataSource getDataSource(){
		BasicDataSource datasource = new BasicDataSource();
		
		//Providing the database connection information
		datasource.setDriverClassName(DATABSE_DRIVER);
		datasource.setUrl(DATABSE_URL);
		datasource.setUsername(DATABSE_USERNAME);
		datasource.setPassword(DATABSE_PASSWORD);
		
		return datasource;
	}
	
	
	//sessionfactory bean will be available
	@Bean
	public SessionFactory getSessionFactory(DataSource dataSource){
		
		LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource);
		
		builder.addProperties(getHibernateProperties());
		builder.scanPackages("com.iba.storebackend.dto");
		
		return builder.buildSessionFactory();
		
	}


	private Properties getHibernateProperties() {
		
		Properties properties = new Properties();
		
		properties.put("hibernate.dialect", DATABSE_DIALECT);
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.format_sql", "true");
		
		return properties;
	}
	
	// Tansaction manager bean
	@Bean
	public HibernateTransactionManager getHibernateTransactionManager(SessionFactory sessionFactory){
		
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		
		return transactionManager;
		
	}


	

}
