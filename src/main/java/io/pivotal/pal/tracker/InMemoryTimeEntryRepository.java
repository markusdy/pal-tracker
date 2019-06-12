package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private ArrayList<TimeEntry> timeEntrys = new ArrayList<>();
    private long count = 0;


    @Override
    public boolean delete(long timeEntryId) {
        return timeEntrys.removeIf(obj -> obj.getId() == timeEntryId);
    }

    @Override
    public TimeEntry update(long eq, TimeEntry entry) {
        if (!delete(eq)) {
            return null;
        }
        entry.setId(eq);
        timeEntrys.add(entry);
        return entry;
    }

    @Override
    public List<TimeEntry> list() {
        return timeEntrys;
    }

    @Override
    public TimeEntry find(long nonExistentTimeEntryId) {
        for(TimeEntry entry : timeEntrys) {
            if(entry.getId() == nonExistentTimeEntryId) return entry;
        }
        return null;
    }

    @Override
    public TimeEntry create(TimeEntry timeEntryToCreate) {
        count++;
        if (timeEntryToCreate.getId() == 0){
            timeEntryToCreate.setId(count);
        }
        timeEntrys.add(timeEntryToCreate);
        System.out.println(timeEntryToCreate);
        return timeEntryToCreate;
    }

}
