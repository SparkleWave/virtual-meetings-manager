package ru.nsu.virtual_meetings.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.nsu.virtual_meetings.controller.requests.UserRequest;
import ru.nsu.virtual_meetings.enity.Meeting;
import ru.nsu.virtual_meetings.enity.User;
import ru.nsu.virtual_meetings.exceptions.AlreadyExistException;
import ru.nsu.virtual_meetings.exceptions.NotFoundException;
import ru.nsu.virtual_meetings.service.UserService;

import java.util.List;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/registration")
    public ResponseEntity<Void> createUser(@RequestBody UserRequest userRequest) throws AlreadyExistException {
        User user = new User(userRequest.getUserId(), userRequest.getUserLogin(), userRequest.getUserPassword());
        userService.createUser(user);
        return ResponseEntity.ok().build();
    }

//    @GetMapping(value = "/users/{userId}")
//    public ResponseEntity<User> getUser (@PathVariable(name = "userId") Long userId) {
//        User user = userService.getUser(userId);
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }

    @GetMapping(value = "/users/get-meetings/{userId}")
    public ResponseEntity<List<Meeting>> getUsersMeetings (@PathVariable(name = "userId") Long userId) throws NotFoundException {
        List<Meeting> meetings = userService.getUserMeetings(userId);
        for (Meeting m : meetings) {
            System.out.println(m.getMeetingId());
        }
        return new ResponseEntity<>(meetings, HttpStatus.OK);
    }

    @PutMapping(value = "/users/{userId}")
    public ResponseEntity<Void> updateUser(@RequestBody UserRequest userRequest, @PathVariable(name = "userId") Long userId) throws AlreadyExistException, NotFoundException {
        User user = new User(userRequest.getUserId(), userRequest.getUserLogin(), userRequest.getUserPassword());
        userService.updateUser(user, userId);
        return ResponseEntity.ok().build();
    }

//    @Transactional
//    @DeleteMapping(value = "/users/user/{userId}")
//    public ResponseEntity<Void> deleteUser(@PathVariable(name = "userId") Long userId) {
//        userService.deleteUser(userId);
//        return ResponseEntity.ok().build();
//    }

}
