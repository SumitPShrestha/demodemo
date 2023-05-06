package com.demo.drone.dto;


import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActivityLogDTO {


    private String activity;
    private String before;
    private String after;


    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}
