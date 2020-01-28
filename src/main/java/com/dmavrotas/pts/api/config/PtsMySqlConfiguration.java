package com.dmavrotas.pts.api.config;

import com.dmavrotas.pts.api.models.AbstractEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.sql.DataSource;

/**
 * Configure the managerFactory, transactionManager, datasource,
 * to access the config (Mysql).
 * <p>
 * The profile test is coded in test file, with an embeded hsql server
 */
@Configuration
@EnableJpaRepositories(basePackages = {"com.dmavrotas.pts.api.repositories"},
                       entityManagerFactoryRef = "configurationManagerFactory",
                       transactionManagerRef = "configurationTransactionManager")
@EnableTransactionManagement
public class PtsMySqlConfiguration
{
    /**
     * Loading profile
     */
    @Configuration
    @Profile({"default"})
    @PropertySource("classpath:configuration.properties")
    static class ManagerFactorConfigurationEnv
    {
        ManagerFactorConfigurationEnv()
        {
        }

        static
        {
//            logger.info("-- MySQL config with profile");
        }
    }

    @Bean
    @Primary
    @Qualifier("configurationTransactionManager")
    PlatformTransactionManager configurationTransactionManager()
    {
        return new JpaTransactionManager(configurationManagerFactory());
    }

    @Bean
    @Primary
    @Qualifier("configurationManagerFactory")
    @PersistenceUnit
    EntityManagerFactory configurationManagerFactory()
    {
        var vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(false);

        var factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(configurationDataSource());
        factoryBean.setJpaVendorAdapter(vendorAdapter);
        factoryBean.setPackagesToScan(AbstractEntity.class.getPackage().getName());
        factoryBean.afterPropertiesSet();

        return factoryBean.getObject();
    }

    @ConfigurationProperties(prefix = "pts.api.datasource")
    @Primary
    @Bean
    @Qualifier("configurationDataSource")
    @Profile({"default"})
    public DataSource configurationDataSource()
    {
        return DataSourceBuilder.create().build();
    }
}

