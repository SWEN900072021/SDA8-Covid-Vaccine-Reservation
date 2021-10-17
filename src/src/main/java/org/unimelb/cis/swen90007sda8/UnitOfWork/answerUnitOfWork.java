package org.unimelb.cis.swen90007sda8.UnitOfWork;
import org.unimelb.cis.swen90007sda8.Mappers.questionMapper;
import org.unimelb.cis.swen90007sda8.Models.answerModel;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class answerUnitOfWork implements IUnitOfWork<answerModel>{

    private Map<String, List<answerModel>> context;
    private final questionMapper answerDB;


    public answerUnitOfWork(Map<String, List<answerModel>> context, questionMapper answerDB) {
        this.context = context;
        this.answerDB = answerDB;
    }

    @Override
    public void registerNew(answerModel answer) {
        register(answer, IUnitOfWork.INSERT);
    }


    @Override
    public void registerModified(answerModel timeslot) {

    }

    @Override
    public void registerDeleted(answerModel answer) { register(answer, IUnitOfWork.DELETE); }

    private void register(answerModel answer, String operation) {
        List<answerModel> answerModelOperate = context.get(operation);
        if (answerModelOperate == null) {
            answerModelOperate = new ArrayList<>();
        }
        answerModelOperate.add(answer);
        context.put(operation, answerModelOperate);
    }

    @Override
    public void commit() {
        if (context == null || context.size() == 0) {
            return;
        }
        if (context.containsKey(IUnitOfWork.DELETE)) {
            commitDelete();
        }
        if (context.containsKey(IUnitOfWork.INSERT)) {
            commitInsert();
        }
        if (context.containsKey(IUnitOfWork.MODIFY)) {
            commitModify();
        }
    }
    private void commitInsert() {
        List<answerModel> bookingsToBeInserted = context.get(IUnitOfWork.INSERT);
        for (answerModel answer : bookingsToBeInserted) {
            answerDB.insertNewAnswer(answer.getUser().getEmail(),answer.getQuestion().getId(),answer.getAnswer());
        }
    }

    private void commitModify() {
        List<answerModel> modifiedTimeslots = context.get(IUnitOfWork.MODIFY);
        for (answerModel answer : modifiedTimeslots) {
        }
    }

    private void commitDelete() {
        List<answerModel> deletedTimeslots = context.get(IUnitOfWork.DELETE);
        for (answerModel answer : deletedTimeslots) {
            answerDB.clearOldAnswer(answer.getUser().getEmail());
        }
    }
}