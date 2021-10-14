package org.unimelb.cis.swen90007sda8.Mappers;

import org.unimelb.cis.swen90007sda8.DBConnector.postgresqlConnector;
import org.unimelb.cis.swen90007sda8.LockManager.lockManager;

import java.sql.ResultSet;
import java.util.Dictionary;
import java.util.Hashtable;

public class hcpMapper implements UserInterface {

    public static void setVaccinatedByEmail(String email){
        lockManager.getInstance().acquireLock("user "+email, Thread.currentThread().getName());
        String s = "UPDATE  users  SET vaccinated = True WHERE email ="+"'"+ email+"';";
        postgresqlConnector.getInstance().connect(s);
        lockManager.getInstance().releaseLock("user "+email, Thread.currentThread().getName());
    }
    public static void setNotVaccinatedByEmail(String email){
        lockManager.getInstance().acquireLock("user "+email, Thread.currentThread().getName());
        String s = "UPDATE  users  SET vaccinated = False WHERE email ="+"'"+ email+"';";
        postgresqlConnector.getInstance().connect(s);
        lockManager.getInstance().releaseLock("user "+email, Thread.currentThread().getName());
    }

    public static Dictionary<Object, Object> findUserByEmail(String email){
        String stmt = "SELECT email, dateofbirth, firstname, lastname, user_identity, postcode, typeofprovider, vaccinated, hcpname FROM users " +
                "WHERE email = '"+email+"';";
        ResultSet rs = postgresqlConnector.getInstance().connect(stmt);
        Dictionary<Object, Object> user = new Hashtable<>();
        try {
            while (rs.next()) {
                user.put("firstname", rs.getString("firstname"));
                user.put("lastname", rs.getString("lastname"));
                user.put("dateofbirth", rs.getDate("dateofbirth"));
                user.put("user_identity", rs.getString("user_identity"));
                user.put("vaccinated", rs.getBoolean("vaccinated"));
                user.put("postcode", rs.getString("postcode"));
                user.put("typeofprovider", rs.getString("typeofprovider"));
                if(rs.getString("hcpname")!=null){
                    user.put("hcpname", rs.getString("hcpname"));
                }else{
                    user.put("hcpname", "----");
                }

            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return user;
    }
}
