package ru.nsu.virtual_meetings.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.virtual_meetings.enity.Meeting;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {

    Meeting getByMeetingId(Long meetingId);

    void deleteByMeetingId(Long meetingId);


}
