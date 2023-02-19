package ru.nsu.virtual_meetings.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.virtual_meetings.enity.Meeting;
import ru.nsu.virtual_meetings.enity.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUserId (Long userId);

    void deleteByUserId(Long userId);

    List<User> findAllByUserIdIn (List<Long> usersId);

    List<User> findUsersByMeetings (Meeting meeting);

    List<User> getAllByMeetings (Meeting meeting);

    User findUserByUserName (String userName);

}
