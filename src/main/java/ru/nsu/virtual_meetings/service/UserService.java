package ru.nsu.virtual_meetings.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nsu.virtual_meetings.enity.Meeting;
import ru.nsu.virtual_meetings.enity.Role;
import ru.nsu.virtual_meetings.enity.User;
import ru.nsu.virtual_meetings.exceptions.AlreadyExistException;
import ru.nsu.virtual_meetings.repository.UserRepository;

import java.util.Collections;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void createUser(User user) throws AlreadyExistException {
        if (userRepository.findUserByUserName(user.getUserName()) != null)
            throw new AlreadyExistException("User with this login already exist");

        user.setRoles(Collections.singleton(Role.USER));
        user.setEnabled(true);
        //user.setRoles(Collections.singleton(new Role(1L, "USER")));
        //user.setUserPassword(bCryptPasswordEncoder.encode(user.getUserPassword()));
        userRepository.save(user);
    }

//    public User getUser(Long userId)   {
//        return userRepository.findUserByUserId(userId);
//    }

    public List<Meeting> getUserMeetings(Long userId) {
        User user = userRepository.findUserByUserId(userId);
        System.out.println(user.getMeetings());
        return user.getMeetings();
    }

//    public void deleteUser(Long userId) {
//        userRepository.deleteByUserId(userId);
//    }

    public void updateUser(User user, Long userId) throws AlreadyExistException {
        if (userRepository.findUserByUserName(user.getUserName()) != null)
            throw new AlreadyExistException("User with this login already exist");
        user.setUserId(userId);
        userRepository.saveAndFlush(user);
    }
}
