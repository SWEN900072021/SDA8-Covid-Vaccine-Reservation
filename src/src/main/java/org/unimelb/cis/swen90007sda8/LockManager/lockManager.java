package org.unimelb.cis.swen90007sda8.LockManager;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class lockManager {

    private static lockManager instance;

    private ConcurrentMap<String, String> lockMap;

    public static synchronized lockManager getInstance() {
        if(instance == null) {
            instance = new lockManager();
        }
        return instance;
    }

    private lockManager() {
        lockMap = new ConcurrentHashMap<String, String>();
    }

    public synchronized void acquireLock(ArrayList<String> lockables, String owner) {
        while(!checkLocked(lockables)) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (String lockable: lockables) {
            lockMap.put(lockable, owner);
        }
    }

    public synchronized void releaseLock(ArrayList<String> lockables, String owner) {
        for (String lockable: lockables) {
            lockMap.remove(lockable);
        }
        notifyAll();
    }

    private boolean checkLocked(ArrayList<String> lockables) {
        for (String lockable: lockables) {
            if (lockMap.containsKey(lockable)) {
                return false;
            }
        }
        return true;
    }
}
