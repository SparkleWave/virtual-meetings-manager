package ru.nsu.virtual_meetings.controller.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import java.util.Date;
import java.util.List;

public class MeetingRequest {

    public MeetingRequest() {
    }

    @JsonProperty(value = "meetingId")
    private Long meetingId;

    @JsonProperty(value = "meetingDate")
    private Date meetingDate;

    @JsonProperty(value = "users")
    private List<Long> users;

    @JsonProperty(value = "meetingName")
    private String meetingName;

    @JsonProperty(value = "meetingDescription")
    private String meetingDescription;

    public Long getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(Long meetingId) {
        this.meetingId = meetingId;
    }

    public Date getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(Date meetingDate) {
        this.meetingDate = meetingDate;
    }

    public List<Long> getUsers() {
        return users;
    }

    public void setUsers(List<Long> users) {
        this.users = users;
    }

    public String getMeetingName() {
        return meetingName;
    }

    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
    }

    public String getMeetingDescription() {
        return meetingDescription;
    }

    public void setMeetingDescription(String meetingDescription) {
        this.meetingDescription = meetingDescription;
    }
}
