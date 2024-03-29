package io.pivotal.pal.tracker;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("time-entries")
public class TimeEntryController {

    private TimeEntryRepository timeEntriesRepo;
    private final DistributionSummary timeEntrySummary;
    private final Counter actionCounter;

    public TimeEntryController(@Autowired TimeEntryRepository timeEntryRepository, MeterRegistry meterRegistry) {
        this.timeEntriesRepo = timeEntryRepository;
        timeEntrySummary = meterRegistry.summary("timeEntry.summary");
        actionCounter = meterRegistry.counter("timeEntry.actionCounter");
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        timeEntriesRepo.delete(id);
        actionCounter.increment();
        timeEntrySummary.record(timeEntriesRepo.list().size());

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody TimeEntry timeEntry) {
        TimeEntry updatedTimeEntry = timeEntriesRepo.update(id, timeEntry);
        if (updatedTimeEntry != null) {
            actionCounter.increment();
            return new ResponseEntity<>(updatedTimeEntry, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping()
    public ResponseEntity<List<TimeEntry>> list() {
        actionCounter.increment();
        return new ResponseEntity<List<TimeEntry>>(timeEntriesRepo.list(), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable Long id) {
        TimeEntry timeEntry = timeEntriesRepo.find(id);
        if (timeEntry != null) {
            actionCounter.increment();
            return new ResponseEntity<>(timeEntry, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping()
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry id) {
        TimeEntry createdTimeEntry = timeEntriesRepo.create(id);
        actionCounter.increment();
        timeEntrySummary.record(timeEntriesRepo.list().size());

        return new ResponseEntity<>(createdTimeEntry, HttpStatus.CREATED);

    }
}
