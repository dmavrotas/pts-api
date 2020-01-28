package com.dmavrotas.pts.api.config;

import org.hsqldb.util.DatabaseManagerSwing;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.util.List;

/**
 * Embedded mysql server containing the configuration
 */
@Configuration
@Profile("test")
public class EmbeddedPtsMySqlConfiguration
{
    public String getDatabaseName()
    {
        return "config";
    }

    public List<String> getScripts()
    {
        return List.of("classpath:sql/pts_db.sql");
    }

    @Bean(name = "configurationDataSource")
    @Qualifier("configurationDataSource")
    @Profile("test")
    public DataSource dataSource()
    {
        var builder = new EmbeddedDatabaseBuilder()
                              .setType(EmbeddedDatabaseType.HSQL)
                              .setName(getDatabaseName());

        getScripts().forEach(builder::addScript);

        return builder.build();
    }

    /**
     * Enables to open a HSQL database browser
     * to be use with -Djava.awt.headless=false
     * <p>
     * and add -Dspring.profiles.include=gui
     */
    @Bean
    @Profile({"gui"})
    public String getDbManager()
    {
        DatabaseManagerSwing.main(
                new String[]{"--url", "jdbc:hsqldb:mem:" + getDatabaseName(), "--user", "sa", "--password", "",
                             "--noexit"});
        return "";
    }
}
