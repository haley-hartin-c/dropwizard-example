package com.example.helloworld;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


import io.dropwizard.db.DataSourceFactory;
import lombok.Data;

@Data
public class HelloWorldConfiguration extends Configuration {

    private String template;

    private String defaultName = "Stranger";

    @Valid
    @NotNull
    private DataSourceFactory database = new DataSourceFactory();

    private final DataSourceFactory dataSourceFactory
            = new DataSourceFactory();

    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return database;
    }

}
