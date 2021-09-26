package com.example.swen90008sda8.UnitOfWork;
import com.example.swen90008sda8.Mappers.BookingMapper;
import com.example.swen90008sda8.Models.bookingModel;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UnitOfWork implements IUnitOfWork<bookingModel>{

    private Map<String, List<bookingModel>> context;
    private final BookingMapper bookingDB;


    public UnitOfWork(Map<String, List<bookingModel>> context, BookingMapper bookingDB) {
        this.context = context;
        this.bookingDB = bookingDB;
    }

    @Override
    public void registerNew(bookingModel booking) {
        register(booking, IUnitOfWork.INSERT);
    }

    @Override
    public void registerModified(bookingModel timeslot) {

    }

    @Override
    public void registerDeleted(bookingModel timeslot) {

    }
    private void register(bookingModel timeslot, String operation) {
        List<bookingModel> bookingModelOperate = context.get(operation);
        if (bookingModelOperate == null) {
            bookingModelOperate = new ArrayList<>();
        }
        bookingModelOperate.add(timeslot);
        context.put(operation, bookingModelOperate);
    }

    @Override
    public void commit() {
        if (context == null || context.size() == 0) {
            return;
        }
        if (context.containsKey(IUnitOfWork.INSERT)) {
            commitInsert();
        }

        if (context.containsKey(IUnitOfWork.MODIFY)) {
            commitModify();
        }
        if (context.containsKey(IUnitOfWork.DELETE)) {
            commitDelete();
        }
    }
    private void commitInsert() {
        List<bookingModel> bookingsToBeInserted = context.get(IUnitOfWork.INSERT);
        for (bookingModel booking : bookingsToBeInserted) {
            bookingDB.insert(booking);
        }
    }

    private void commitModify() {
        List<bookingModel> modifiedTimeslots = context.get(IUnitOfWork.MODIFY);
        for (bookingModel timeslot : modifiedTimeslots) {
        }
    }

    private void commitDelete() {
        List<bookingModel> deletedTimeslots = context.get(IUnitOfWork.DELETE);
        for (bookingModel timeslot : deletedTimeslots) {
        }
    }
}
