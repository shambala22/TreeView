package com.splat.config;

import com.splat.dao.FolderJdbcDao;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * Created by shambala on 21.08.17.
 */
public class JdbcDaoContextConfiguration {
    @Bean
    public FolderJdbcDao folderJdbcDao(DataSource dataSource) {
        return new FolderJdbcDao(dataSource);
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.sqlite.JDBC");
        dataSource.setUrl("jdbc:sqlite:product.db");
        dataSource.setUsername("");
        dataSource.setPassword("");
        return dataSource;
    }
}

