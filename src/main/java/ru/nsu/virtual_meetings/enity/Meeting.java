package ru.nsu.virtual_meetings.enity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "meeting")
public class Meeting {

    public Meeting() {
    }

    public Meeting(long meetingId, String meetingName, Date meetingDate, String meetingDescription) {
        this.meetingId = meetingId;
        this.meetingName = meetingName;
        this.meetingDate = meetingDate;
        this.meetingDescription = meetingDescription;
    }

    @Id
    @Column(name = "meetingId")
    private long meetingId;

    @Column(name = "meetingName", nullable = false)
    private String meetingName;

    @Column(name = "meetingDate", nullable = false)
    private Date meetingDate;

    @Column(name = "meetingDescription")
    private String meetingDescription;

    @Column(name = "meetingsOrgName")
    private String meetingsOrgName;

    @Column(name = "userCount")
    private int userCount;

    public long getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(long meetingId) {
        this.meetingId = meetingId;
    }

    public Date getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(Date meetingDate) {
        this.meetingDate = meetingDate;
    }

    public String getMeetingName() {
        return meetingName;
    }

    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
    }

    public String getMeetingsOrg() {
        return meetingsOrgName;
    }

    public void setMeetingsOrg(String meetingsOrg) {
        this.meetingsOrgName = meetingsOrg;
    }

    public int getUserCount() {
        return userCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }

    public String getMeetingDescription() {
        return meetingDescription;
    }

    public void setMeetingDescription(String meetingDescription) {
        this.meetingDescription = meetingDescription;
    }
}
