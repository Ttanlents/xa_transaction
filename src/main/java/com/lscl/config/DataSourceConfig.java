package com.lscl.config;

import com.atomikos.icatch.jta.UserTransactionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.jta.JtaTransactionManager;

import java.util.Properties;

@Configuration
public class DataSourceConfig {


    /**
     * t_orders数据源
     *
     * @return
     */
    @Bean(name = "ordersDS")
    @Qualifier("ordersDS")
    public AtomikosDataSourceBean ordersDS() {
        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
        atomikosDataSourceBean.setUniqueResourceName("ordersDS");
        atomikosDataSourceBean.setXaDataSourceClassName(
                "com.mysql.jdbc.jdbc2.optional.MysqlXADataSource");
        Properties properties = new Properties();
        properties.put("URL","jdbc:mysql://localhost:3306/orders");
        properties.put("user", "root");
        properties.put("password", "12345678");
        atomikosDataSourceBean.setXaProperties(properties);
        return atomikosDataSourceBean;
    }


    /**
     * t_store数据源
     *
     * @return
     */
    @Bean(name = "storeDS")
    @Qualifier("storeDS")
    public AtomikosDataSourceBean storeDS() {
        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
        atomikosDataSourceBean.setUniqueResourceName("symbolPosition");
        atomikosDataSourceBean.setXaDataSourceClassName(
                "com.mysql.jdbc.jdbc2.optional.MysqlXADataSource");
        Properties properties = new Properties();
        properties.put("URL", "jdbc:mysql://localhost:3306/store");
        properties.put("user", "root");
        properties.put("password", "12345678");
        atomikosDataSourceBean.setXaProperties(properties);
        return atomikosDataSourceBean;
    }

    /**
     * transaction manager
     *
     * @return
     */
    @Bean
    public UserTransactionManager userTransactionManager() {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        userTransactionManager.setForceShutdown(true);
        return userTransactionManager;
    }

    /**
     * jta transactionManager
     *
     * @return
     */
    @Bean
    public JtaTransactionManager transactionManager() {
        JtaTransactionManager jtaTransactionManager = new JtaTransactionManager();
        jtaTransactionManager.setTransactionManager(userTransactionManager());
        return jtaTransactionManager;
    }

}
