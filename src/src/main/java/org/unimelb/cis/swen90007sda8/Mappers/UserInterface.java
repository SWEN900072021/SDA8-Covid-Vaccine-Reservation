package org.unimelb.cis.swen90007sda8.Mappers;

import org.unimelb.cis.swen90007sda8.DBConnector.postgresqlConnector;
import org.unimelb.cis.swen90007sda8.Models.userModel;
import org.unimelb.cis.swen90007sda8.Models.vaccineModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public interface UserInterface {
    public static void getVaccinatedByEmail(String email){
    }
    public static void setVaccinatedByEmail(String email){
    }
    public static void setNotVaccinatedByEmail(String email){

    }
    public static void findUser(String email, String password){
    }

    public static void isUserExisted(String email) throws SQLException {
    }
    public static void findWithVaccineName (vaccineModel vaccineName){
    }

    public static void getUserModels(List<userModel> result, String stmt) {
    }

    public static void findWithVaccinated (String vaccinated,String identity) {
    }
    public static void insertNewProvider(String user, String pass, String identity, String post, String top, String hcpname) throws SQLException {
    }

    public static void insertNewRecipient(String user, String pass, String date, String firstName, String lastName, String identity) throws SQLException {
    }

    public static void findUserByEmail(String email){
    }
}
