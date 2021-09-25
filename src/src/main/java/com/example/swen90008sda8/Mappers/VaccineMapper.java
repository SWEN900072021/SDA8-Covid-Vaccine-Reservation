package com.example.swen90008sda8.Mappers;

import com.example.swen90008sda8.DBConnector.postgresqlConnector;
import com.example.swen90008sda8.Models.userModel;
import com.example.swen90008sda8.Models.vaccineModel;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VaccineMapper {
    public static Boolean insertVaccine(String name, String from, String to) {
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
    public static List<vaccineModel> getVaccines() {
        List<vaccineModel> result = new ArrayList<>();
        String stmt = "SELECT name, fromage, toage FROM vaccines;";
        ResultSet rs = new postgresqlConnector().connect(stmt);
        try {
            while (rs.next()) {
                vaccineModel vaccine = new vaccineModel();
                vaccine.setName(rs.getString(1));
                vaccine.setFromAge(rs.getString(2));
                vaccine.setToAge(rs.getString(3));
                result.add(vaccine);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
