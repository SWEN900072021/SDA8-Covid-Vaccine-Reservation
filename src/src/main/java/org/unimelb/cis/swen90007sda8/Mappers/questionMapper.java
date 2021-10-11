package org.unimelb.cis.swen90007sda8.Mappers;

import org.unimelb.cis.swen90007sda8.LockManager.lockManager;
import org.unimelb.cis.swen90007sda8.Models.questionModel;
import org.unimelb.cis.swen90007sda8.DBConnector.postgresqlConnector;
import org.unimelb.cis.swen90007sda8.Models.vaccineModel;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public static void clearOldAnswer(String email){
        lockManager.getInstance().acquireLock("question "+email, Thread.currentThread().getName());
        String stmt = "DELETE FROM user_answers_question WHERE userid ='"+email+"';";
        new postgresqlConnector().connect(stmt);
        lockManager.getInstance().releaseLock("question "+email, Thread.currentThread().getName());
    }

    public static void insertNewAnswer(String email, Integer qid, Boolean answer){
        lockManager.getInstance().acquireLock("question "+email, Thread.currentThread().getName());
        String stmt = "INSERT INTO user_answers_question(userid,questionid,answer) VALUES('" +
                email+"',"+qid+","+answer+");";
        new postgresqlConnector().connect(stmt);
        lockManager.getInstance().releaseLock("question "+email, Thread.currentThread().getName());
    }

    public static List<String> getSuitableVaccines(String email){
        List<String> result = new ArrayList<>();
        String stmt = "SELECT vaccinename From questions Left JOIN user_answers_question on " +
                "questions.id=user_answers_question.questionid WHERE desiredanswer = answer AND " +
                "userid ='"+ email +"';";
        ResultSet rs = new postgresqlConnector().connect(stmt);
        try {
            while (rs.next()) {
                String vname = rs.getString("vaccinename");
                result.add(vname);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        Set<String> set = new HashSet<String>();
        set.addAll(result);
        result = new ArrayList<>();
        result.addAll(set);
        stmt = "SELECT vaccinename From questions Left JOIN user_answers_question on " +
                "questions.id=user_answers_question.questionid WHERE desiredanswer != answer AND " +
                "userid ='"+ email +"';";
        rs = new postgresqlConnector().connect(stmt);
        try {
            while (rs.next()) {
                String vname = rs.getString("vaccinename");
                result.remove(vname);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
