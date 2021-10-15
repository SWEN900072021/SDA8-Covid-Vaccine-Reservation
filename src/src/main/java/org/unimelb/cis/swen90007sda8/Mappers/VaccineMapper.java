package org.unimelb.cis.swen90007sda8.Mappers;

import org.unimelb.cis.swen90007sda8.DBConnector.postgresqlConnector;
import org.unimelb.cis.swen90007sda8.LockManager.lockManager;
import org.unimelb.cis.swen90007sda8.Models.vaccineModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VaccineMapper {
    public static String isVaccineExisted(String name) throws SQLException {
        String stmt = "SELECT name From vaccines WHERE name ="+"'"+name+"';";
        ResultSet rs = postgresqlConnector.getInstance().connect(stmt);
        if(rs.next()){
            return rs.getString(1);
        }else{
            return null;
        }
    }
    public static Boolean insertVaccine(String name, String from, String to){
        boolean result = true;
        int fromAge = Integer.parseInt(from);
        int toAge = Integer.parseInt(to);
        try {
            if(toAge>=fromAge && (isVaccineExisted(name)==null)){
                String stmt = "INSERT INTO vaccines(name, fromAge, toAge) VALUES (" +"'"+name+"'"+','+"'"+from+"'"+','+"'"+to+"'"+");";
                postgresqlConnector.getInstance().connect(stmt);
            }else{
                result = false;
            }
        } catch(SQLException e){
            e.printStackTrace();
        }

        return result;
    }
    public static List<vaccineModel> getVaccines() {
        List<vaccineModel> result = new ArrayList<>();
        String stmt = "SELECT name, fromage, toage FROM vaccines;";
        ResultSet rs = postgresqlConnector.getInstance().connect(stmt);
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
        ResultSet rs = postgresqlConnector.getInstance().connect(stmt);
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
