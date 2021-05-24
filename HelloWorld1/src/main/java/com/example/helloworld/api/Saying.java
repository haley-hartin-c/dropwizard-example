package com.example.helloworld.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Saying {
    private long id;

    private String content;

    public Saying() {
        // Jackson deserialization
    }

    public Saying(long id, String content) {
        this.id = id;
        this.content = content;
    }


}
