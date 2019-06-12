package io.pivotal.pal.tracker;

import java.util.List;

public interface TimeEntryRepository {
    public boolean delete(long timeEntryId);

    public TimeEntry update(long eq, TimeEntry any);

    public List<TimeEntry> list();

    public TimeEntry find(long nonExistentTimeEntryId);

    public TimeEntry create(TimeEntry timeEntryToCreate);
}
