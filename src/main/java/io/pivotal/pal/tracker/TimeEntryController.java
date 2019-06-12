package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("time-entries")
public class TimeEntryController {

    private TimeEntryRepository ter;
    public TimeEntryController(@Autowired TimeEntryRepository timeEntryRepository) {
        this.ter = timeEntryRepository;
    }

    @DeleteMapping("{timeEntryId}")
    public ResponseEntity delete(@PathVariable Long timeEntryId) {
        ter.delete(timeEntryId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("{timeEntryId}")
    public ResponseEntity update(@PathVariable Long timeEntryId, @RequestBody TimeEntry timeEntry) {
        TimeEntry timeEntry2 = ter.update(timeEntryId, timeEntry);
        if (timeEntry2 == null){
            return new ResponseEntity<TimeEntry>(new TimeEntry(), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(timeEntry2, HttpStatus.OK);
        }
    }

    @GetMapping()
    public ResponseEntity<List<TimeEntry>> list() {


        return new ResponseEntity<List<TimeEntry>>(ter.list(), HttpStatus.OK);

    }

    @GetMapping("/{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable Long timeEntryId) {
        TimeEntry timeEntry = ter.find(timeEntryId);
        if (timeEntry == null){
            return new ResponseEntity<TimeEntry>(new TimeEntry(), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(timeEntry, HttpStatus.OK);
        }

    }

    @PostMapping()
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate) {
        System.out.println(timeEntryToCreate);
        return new ResponseEntity<TimeEntry>(ter.create(timeEntryToCreate), HttpStatus.CREATED);

    }
}
