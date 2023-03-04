package ru.nsu.virtual_meetings.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.nsu.virtual_meetings.controller.requests.MeetingRequest;
import ru.nsu.virtual_meetings.enity.Meeting;
import ru.nsu.virtual_meetings.exceptions.AlreadyExistException;
import ru.nsu.virtual_meetings.exceptions.NotFoundException;
import ru.nsu.virtual_meetings.service.MeetingService;

import java.util.List;


@RestController
public class MeetingController {

    @Autowired
    private MeetingService meetingService;

    @PostMapping(value = "/meetings/meeting")
    public ResponseEntity<Void> addMeeting(@RequestBody MeetingRequest meetingRequest) throws NotFoundException {
        Meeting meeting = new Meeting(meetingRequest.getMeetingId(), meetingRequest.getMeetingName(), meetingRequest.getMeetingDate(), meetingRequest.getMeetingDescription());
        meetingService.createMeeting(meeting, meetingRequest.getMeetingsOrg());
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/meetings/{meetingId}")
    public ResponseEntity<Meeting> getMeeting(@PathVariable(name = "meetingId") Long meetingId) throws NotFoundException {
        Meeting meeting = meetingService.getMeeting(meetingId);
        return new ResponseEntity<>(meeting, HttpStatus.OK);
    }

    @GetMapping(value = "/meetings/get-users/{meetingId}")
    public ResponseEntity<List<String>> getMeetingUsers(@PathVariable(name = "meetingId") Long meetingId) throws NotFoundException {
        return new ResponseEntity<>(meetingService.getMeetingUsers(meetingId), HttpStatus.OK);
    }

    @PostMapping(value = "/meetings/add-users/{meetingId}/{userId}")
    public ResponseEntity<Void> addUserToMeeting(@PathVariable(name = "meetingId") Long meetingId, @PathVariable(name = "userId") Long userId) throws AlreadyExistException, NotFoundException {
        meetingService.addUserToMeeting(meetingId, userId);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/meetings/{meetingId}")
    public ResponseEntity<Void> updateMeeting(@RequestBody MeetingRequest meetingRequest, @PathVariable(name = "meetingId") Long meetingId) throws NotFoundException {
        Meeting meeting = new Meeting(meetingRequest.getMeetingId(), meetingRequest.getMeetingName(), meetingRequest.getMeetingDate(), meetingRequest.getMeetingDescription());
        meetingService.updateMeeting(meeting, meetingId);
        return ResponseEntity.ok().build();
    }

    @Transactional
    @DeleteMapping(value = "/meetings/{meetingId}")
    public ResponseEntity<Void> deleteMeeting(@PathVariable(name = "meetingId") Long meetingId) throws NotFoundException {
        meetingService.deleteMeeting(meetingId);
        return ResponseEntity.ok().build();
    }

}
