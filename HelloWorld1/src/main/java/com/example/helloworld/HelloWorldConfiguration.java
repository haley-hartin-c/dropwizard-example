package com.example.helloworld;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotEmpty;

import io.dropwizard.db.DataSourceFactory;
import lombok.Data;
@Data
public class HelloWorldConfiguration extends Configuration {

    private String template;

    private String defaultName = "Stranger";

    private final DataSourceFactory dataSourceFactory
            = new DataSourceFactory();

}
