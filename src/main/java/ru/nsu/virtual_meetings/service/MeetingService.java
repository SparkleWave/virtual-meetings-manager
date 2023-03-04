package ru.nsu.virtual_meetings.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nsu.virtual_meetings.enity.Meeting;
import ru.nsu.virtual_meetings.enity.User;
import ru.nsu.virtual_meetings.exceptions.AlreadyExistException;
import ru.nsu.virtual_meetings.exceptions.NotFoundException;
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

    public void createMeeting(Meeting meeting, String userName) throws NotFoundException {
        meeting.setUserCount(1);
        User user = userRepository.findUserByUserName(userName);
        if (user == null)
            throw new NotFoundException("User not found");
        meeting.setMeetingsOrg(user.getUserName());
        meetingRepository.save(meeting);
        List<Meeting> userMeetings = user.getMeetings();
        userMeetings.add(meeting);
        user.setMeetings(userMeetings);
        userRepository.saveAndFlush(user);
    }

    public Meeting getMeeting(Long meetingId) throws NotFoundException {
        Meeting meeting = meetingRepository.getByMeetingId(meetingId);
        if (meeting == null)
            throw new NotFoundException("Meeting not found");
        return meeting;
    }

    public void addUserToMeeting(Long meetingId, Long userId) throws NotFoundException, AlreadyExistException {
        Meeting meeting = meetingRepository.getByMeetingId(meetingId);
        if (meeting == null)
            throw new NotFoundException("Meeting not found");
        User user = userRepository.findUserByUserId(userId);
        if (user == null)
            throw new NotFoundException("User not found");

        List<String> usersList = getMeetingUsers(meetingId);

        for (String userName : usersList) {
            if (userName.equals(user.getUserName()))
                throw new AlreadyExistException("User already in meeting");
        }

        int userCount = meeting.getUserCount();
        meeting.setUserCount(userCount + 1);
        updateMeeting(meeting, meetingId);

        List<Meeting> meetings = user.getMeetings();
        meetings.add(meeting);
        user.setMeetings(meetings);
        userRepository.saveAndFlush(user);
    }

    public List<String> getMeetingUsers(Long meetingId) throws NotFoundException {
        if (meetingRepository.getByMeetingId(meetingId) == null)
            throw new NotFoundException("Meeting not found");
        List<User> users = userRepository.getAllByMeetings(meetingRepository.getByMeetingId(meetingId));
        List<String> usersLogins = new ArrayList<>();
        for (User user : users) {
            usersLogins.add(user.getUserName());
        }
        return usersLogins;
    }

    public void deleteMeeting(Long meetingId) throws NotFoundException {
        Meeting meeting = meetingRepository.getByMeetingId(meetingId);
        if (meeting == null)
            throw new NotFoundException("Meeting not found");
        meetingRepository.deleteByMeetingId(meetingId);
    }

    public void updateMeeting(Meeting meeting, Long meetingId) throws NotFoundException {
        if (meetingRepository.getByMeetingId(meetingId) == null)
            throw new NotFoundException("Meeting not found");
        meeting.setMeetingId(meetingId);
        meetingRepository.saveAndFlush(meeting);
    }
}
