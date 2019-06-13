package io.pivotal.pal.tracker;

import org.springframework.data.repository.CrudRepository;

public interface TimeEntryCrudRepository extends CrudRepository<TimeEntry, Long> {
}
