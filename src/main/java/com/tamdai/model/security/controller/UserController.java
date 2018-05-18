package com.tamdai.model.security.controller;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.tamdai.model.course.entity.Course;
import com.tamdai.model.course.repository.CourseItemRepository;
import com.tamdai.model.course.repository.CourseRepository;
import com.tamdai.model.course.service.CourseService;
import com.tamdai.model.security.dao.UserDao;
import com.tamdai.model.security.entity.Lists;
import com.tamdai.model.security.entity.UserEntity;
import com.tamdai.model.security.repository.ListsRepository;
import com.tamdai.model.security.repository.UserRepository;
import com.tamdai.model.security.service.NotificationServiceImpl;
import com.tamdai.model.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CourseService courseService;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    CourseItemRepository courseItemRepository;

    @Autowired
    ListsRepository listsRepository;

    @Autowired
    UserDao userDao;

    private static HttpServletRequest request;

    @Autowired
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    @RequestMapping(value = "user/login")
    public ResponseEntity<UserEntity> Login(@RequestParam("Email") String email, @RequestParam("Password") String password) {
        UserEntity user = userService.Login(email, password);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @RequestMapping(value = "getUserId/{id}", method = RequestMethod.GET)
    public UserEntity getUserId(@PathVariable("id") Long id) {
        return userService.getUserId(id);
    }

    @RequestMapping(value = "user/register", method = RequestMethod.POST)
    public UserEntity userRegister(@RequestBody UserEntity user, BindingResult bindingResult) throws Exception {
        userService.userRegister(user);
        NotificationServiceImpl.activateMessage(user);
        return user;
    }

    @RequestMapping(value = "activate/account/{id}", method = RequestMethod.GET)
    public String getUrlConfirm(@PathVariable("id") Long id,
                                @RequestParam("statusName") String statusName, HttpServletResponse httpServletResponse) throws IOException {
        UserEntity status = userService.getUserId(id);
        UserEntity user = userService.getUserId(id);
        status.setStatus(statusName);
        userService.updateStatus(status);
        userService.confirmDate(user);
        httpServletResponse.sendRedirect("http://tamdai.net/home");
        return "success";
    }

    @RequestMapping(value = "update/userStatus/{id}", method = RequestMethod.PUT)
    public UserEntity updateUserStatus(@PathVariable("id") Long id,
                                       @RequestParam("statusName") String statusName) {

        UserEntity user = userService.getUserId(id);
        user.setStatus(statusName);
        return userService.updateUserStatus(user);
    }

    @RequestMapping(value = "updateInstructorBio", method = RequestMethod.PUT)
    public UserEntity updateInstructorBio(@RequestParam("getUserId") Long getUserId,
                                          @RequestParam("instructorBio") String instructorBio) {

        UserEntity user = userService.getUserId(getUserId);
        user.setInstructorBio(instructorBio);
        return userService.updateUserStatus(user);
    }
    
    @RequestMapping(value = "updateUserProfile", method = RequestMethod.PUT)
    public UserEntity updateUserProfile(@RequestParam("getUserId") Long getUserId,
                                          @RequestParam("firstName") String firstName,
                                          @RequestParam("email") String email,
                                          @RequestParam("instructorBio") String instructorBio) {

        UserEntity user = userService.getUserId(getUserId);
        user.setInstructorBio(instructorBio);
        user.setFirstName(firstName);
        user.setEmail(email);
        return userService.updateUserStatus(user);
    }
    
    @RequestMapping(value = "user/list", method = RequestMethod.GET)
    public List<UserEntity> UserList() {
        return userService.userList();
    }

    @RequestMapping(value = "user/findByEmail", method = RequestMethod.GET)
    public UserEntity getUserByEmail(@RequestParam("Email") String email) {
        return userService.getUserByEmail(email);
    }

    @RequestMapping("/addDataInSystem")
    public String process() {

        userRepository.save(new UserEntity(1L, "filmpurelove@gmail.com", "ffffffff", "adminFilm", "Hattaya", "admin", "0"));
        userRepository.save(new UserEntity(2L, "hattaya.wpm@gmail.com", "ffffffff", "instructorA", "TeacherA", "instructor", "0"));
        userRepository.save(new UserEntity(3L, "blaze.yul@gmail.com", "ffffffff", "MemberActive", "Customer", "instructor", "0"));
        userRepository.save(new UserEntity(4L, "film_purelove@hotmail.com", "ffffffff", "instructorB", "TeacherB", "instructor", "0"));

        return "Create Done!!";
    }

    @RequestMapping(value = "user/forgotPassword", method = RequestMethod.GET)
    public UserEntity forgotPassword(@RequestParam("Email") String email) throws UnirestException {
        UserEntity user = userService.getUserByEmail(email);
        NotificationServiceImpl.forgotPasswordMessage(user);
        return user;
    }

    @RequestMapping(value = "user/getTeacherHistory", method = RequestMethod.GET)
    public UserEntity getTeacherHistory(@RequestParam("Email") String email) {
        return userService.getUserByEmail(email);
    }

    @RequestMapping(value = "courseUserDelete", method = RequestMethod.DELETE)
    @ResponseBody
    public UserEntity deleteCourse(@RequestParam("courseId") Long courseId,
                                   @RequestParam("userId") Long userId) {

        UserEntity users = userService.getUserId(userId);
        return userService.deleteUserCourse(users, courseId);
    }

    @RequestMapping(value = "getClientIp", method = RequestMethod.GET)
    public static String getClientIp() {

        InetAddress ip = null;
        try {

            ip = InetAddress.getLocalHost();
            System.out.println("Current IP address : " + ip.getHostAddress());

        } catch (UnknownHostException e) {

            e.printStackTrace();

        }
        return "Your IP: " + (ip != null ? ip.getHostAddress() : null);
    }

    @RequestMapping(value = "user/addList/{id}", method = RequestMethod.POST)
    public Lists addList(@RequestBody Lists lists,
                         @PathVariable("id") Long id,
                         @RequestParam("userId") Long userId, BindingResult bindingResult) {
        UserEntity userEntity = userService.getUserId(userId);
        Course course = courseService.getCourseId(id);
        lists.getCourses().add(course);
        listsRepository.save(lists);
        userEntity.getLists().add(lists);
        userDao.updateUser(userEntity);
        return lists;
    }

    @RequestMapping(value = "DeleteUserAccount/{id}", method = RequestMethod.DELETE)
    public UserEntity deleteUserAccount(@PathVariable("id") Long id) {
        return userService.deleteUserAccount(id);
    }

}

