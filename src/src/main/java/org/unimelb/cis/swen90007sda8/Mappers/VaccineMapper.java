package org.unimelb.cis.swen90007sda8.Mappers;

import org.unimelb.cis.swen90007sda8.DBConnector.postgresqlConnector;
import org.unimelb.cis.swen90007sda8.Models.vaccineModel;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VaccineMapper {
    public static Boolean insertVaccine(String name, String from, String to) {
        boolean result = true;
        int fromAge = Integer.parseInt(from);
        int toAge = Integer.parseInt(to);
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

    public static vaccineModel find(String vname) {
        String stmt = "SELECT name, fromage, toage FROM vaccines WHERE name='"+ vname +"';";
        ResultSet rs = new postgresqlConnector().connect(stmt);
        vaccineModel vaccine = new vaccineModel();
        try {
            while (rs.next()) {
                vaccine.setName(rs.getString(1));
                vaccine.setFromAge(rs.getString(2));
                vaccine.setToAge(rs.getString(3));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return vaccine;
    }
}
