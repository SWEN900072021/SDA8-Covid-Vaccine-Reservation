package org.unimelb.cis.swen90007sda8.LockManager;

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

    public synchronized void acquireLock(String lockable, String owner) {
        while(lockMap.containsKey(lockable)) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        lockMap.put(lockable, owner);
    }

    public synchronized void releaseLock(String lockable, String owner) {
        lockMap.remove(lockable);
        notifyAll();
    }
}
