package ru.nsu.virtual_meetings.enity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "meeting")
public class Meeting {

    public Meeting() {
    }

    public Meeting(long meetingId, Date meetingDate) {
        this.meetingId = meetingId;
        this.meetingDate = meetingDate;
    }

    @Id
    @Column(name = "meetingId")
    private long meetingId;

//    @Column(name = "meetingName", nullable = false)
//    private String meetingName;

    @Column(name = "meetingDate", nullable = false)
    private Date meetingDate;

    @Column(name = "meetingDescription")
    private String meetingDescription;

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

//    public String getMeetingName() {
//        return meetingName;
//    }
//
//    public void setMeetingName(String meetingName) {
//        this.meetingName = meetingName;
//    }

    public String getMeetingDescription() {
        return meetingDescription;
    }

    public void setMeetingDescription(String meetingDescription) {
        this.meetingDescription = meetingDescription;
    }
}
