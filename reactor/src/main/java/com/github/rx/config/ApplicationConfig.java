package com.github.rx.config;

import javax.sql.DataSource;

import com.github.rx.persistence.config.CommonsConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig extends CommonsConfig {

    @Override
    public DataSource dataSource() {
        return super.dataSource();
    }


}
