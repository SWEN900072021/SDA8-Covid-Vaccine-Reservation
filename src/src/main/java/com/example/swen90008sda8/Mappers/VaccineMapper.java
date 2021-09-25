package com.example.swen90008sda8.Mappers;

import com.example.swen90008sda8.DBConnector.postgresqlConnector;

public class VaccineMapper {
    public static Boolean insertVaccine (String name, String from, String to) {
        Boolean result = true;
        Integer fromAge = Integer.parseInt(from);
        Integer toAge = Integer.parseInt(to);
        if(toAge>=fromAge){
            String stmt = "INSERT INTO vaccines(name, fromAge, toAge) VALUES (" +"'"+name+"'"+','+"'"+from+"'"+','+"'"+to+"'"+");";
            new postgresqlConnector().connect(stmt);
        }else{
            result = false;
        }
        return result;
    }
}
