package kamilszerlag.config;


import org.h2.tools.Server;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

@EnableTransactionManagement
@Configuration
@ComponentScan(basePackages = "kamilszerlag")
@PropertySource("classpath:app.properties")
public class RootConfig {

    final private
    Environment env;

    private Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    public RootConfig(Environment env) {
        this.env = env;
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server server() throws SQLException {
        return Server.createTcpServer();
    }

    @Bean
    public DataSource myDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setPassword(env.getProperty("jdbc.driver"));
        dataSource.setUsername(env.getProperty("jdbc.username"));
        dataSource.setPassword(env.getProperty("jdbc.password"));
        dataSource.setUrl(env.getProperty("jdbc.url"));


        return dataSource;
    }


    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(myDataSource());
        localSessionFactoryBean.setPackagesToScan("kamilszerlag.model");
        localSessionFactoryBean.setHibernateProperties(hibernateProperties());
        return localSessionFactoryBean;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {


        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);

        return txManager;
    }


    private Properties hibernateProperties() {
        return new Properties() {
            {
                setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
                setProperty("hibernate_show", "true");
            }
        };
    }




}
