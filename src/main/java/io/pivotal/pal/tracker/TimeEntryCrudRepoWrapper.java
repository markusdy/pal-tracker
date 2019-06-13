package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TimeEntryCrudRepoWrapper implements TimeEntryRepository{

    private final TimeEntryCrudRepository repo;

    public TimeEntryCrudRepoWrapper(@Autowired TimeEntryCrudRepository repo){
        this.repo = repo;
    }


    @Override
    public boolean delete(long timeEntryId) {
        return false;
    }

    @Override
    public TimeEntry update(long eq, TimeEntry any) {
        return null;
    }

    @Override
    public List<TimeEntry> list() {
        return null;
    }

    @Override
    public TimeEntry find(long nonExistentTimeEntryId) {
        return null;
    }

    @Override
    public TimeEntry create(TimeEntry timeEntryToCreate) {
        return null;
    }
}
