package ru.nsu.virtual_meetings.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nsu.virtual_meetings.enity.Meeting;
import ru.nsu.virtual_meetings.enity.User;
import ru.nsu.virtual_meetings.repository.MeetingRepository;
import ru.nsu.virtual_meetings.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class MeetingService {

    @Autowired
    private MeetingRepository meetingRepository;

    @Autowired
    private UserRepository userRepository;

    public void createMeeting(Meeting meeting, long userId) {
        meetingRepository.save(meeting);
        User user = userRepository.findUserByUserId(userId);
        List<Meeting> meetings = user.getMeetings();
        meetings.add(meeting);
        user.setMeetings(meetings);
        userRepository.saveAndFlush(user);
    }

    public Meeting getMeeting(Long meetingId) {
        return meetingRepository.getByMeetingId(meetingId);
    }

    public List<String> getMeetingUsers(Long meetingId) {
        List<User> users = userRepository.getAllByMeetings(meetingRepository.getByMeetingId(meetingId));
        List<String> usersLogins = new ArrayList<>();
        for (User user : users) {
            usersLogins.add(user.getUserName());
        }
        return usersLogins;
    }

    public void deleteMeeting(Long meetingId) {
        meetingRepository.deleteByMeetingId(meetingId);
    }

    public void updateMeeting(Meeting meeting, Long meetingId) {
        meeting.setMeetingId(meetingId);
        meetingRepository.saveAndFlush(meeting);
    }
}
