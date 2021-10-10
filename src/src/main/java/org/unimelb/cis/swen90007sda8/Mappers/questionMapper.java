package org.unimelb.cis.swen90007sda8.Mappers;

import org.unimelb.cis.swen90007sda8.Models.questionModel;
import org.unimelb.cis.swen90007sda8.DBConnector.postgresqlConnector;
import org.unimelb.cis.swen90007sda8.Models.vaccineModel;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class questionMapper {
    public static List<questionModel> getQuestions (){
        List<questionModel> result = new ArrayList<>();
        String stmt = "SELECT * FROM questions";
        ResultSet rs = new postgresqlConnector().connect(stmt);
        try {
            while (rs.next()) {
                Integer id = rs.getInt("id");
                vaccineModel vaccine = new vaccineModel(rs.getString("vaccinename"));
                String body = rs.getString("question");
                Boolean answer = rs.getBoolean("desiredanswer");
                questionModel question = new questionModel(id,vaccine,body,answer);
                result.add(question);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
