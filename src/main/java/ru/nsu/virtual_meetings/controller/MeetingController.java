package ru.nsu.virtual_meetings.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.nsu.virtual_meetings.controller.requests.MeetingRequest;
import ru.nsu.virtual_meetings.enity.Meeting;
import ru.nsu.virtual_meetings.enity.User;
import ru.nsu.virtual_meetings.service.MeetingService;

import java.util.List;


@RestController
public class MeetingController {

    @Autowired
    private MeetingService meetingService;

    @PostMapping(value = "/meetings/meeting")
    public ResponseEntity<Void> addMeeting(@RequestBody MeetingRequest meetingRequest) {
        Meeting meeting = new Meeting(meetingRequest.getMeetingId(), meetingRequest.getMeetingDate());
        meetingService.createMeeting(meeting, meetingRequest.getUsers().get(0));
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/meetings/{meetingId}")
    public ResponseEntity<Meeting> getMeeting(@PathVariable(name = "meetingId") Long meetingId) {
        Meeting meeting = meetingService.getMeeting(meetingId);
        return new ResponseEntity<>(meeting, HttpStatus.OK);
    }

    @GetMapping(value = "/meetings/get-users/{meetingId}")
    public ResponseEntity<List<String>> getMeetingUsers(@PathVariable(name = "meetingId") Long meetingId) {
        return new ResponseEntity<>(meetingService.getMeetingUsers(meetingId), HttpStatus.OK);
    }

    @PutMapping(value = "/meetings/{meetingId}")
    public ResponseEntity<Void> updateMeeting(@RequestBody MeetingRequest meetingRequest, @PathVariable(name = "meetingId") Long meetingId) {
        Meeting meeting = new Meeting(meetingRequest.getMeetingId(), meetingRequest.getMeetingDate());
        meetingService.updateMeeting(meeting, meetingId);
        return ResponseEntity.ok().build();
    }

    @Transactional
    @DeleteMapping(value = "/meetings/{meetingId}")
    public ResponseEntity<Void> deleteMeeting(@PathVariable(name = "meetingId") Long meetingId) {
        meetingService.deleteMeeting(meetingId);
        return ResponseEntity.ok().build();
    }

}
