package example.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import example.pojo.Activity;
import example.pojo.Song;
import example.pojo.User;
import example.service.ActivityService;
import example.service.SongService;
import example.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/home")
public class DemoController {
    @Resource(name = "userService")
    private UserService userService;
    @Resource(name = "activityService")
    private ActivityService activityService;
    @Resource(name = "songService")
    private SongService songService;

    /** localhost:8080/zkf/home/user/userinfo?name=koado&password=123456 返回Json格式 **/
    @RequestMapping(value = "/user/{userinfo}", method = RequestMethod.GET)
    @ResponseBody public User foo5(@PathVariable String userinfo, HttpServletRequest request){
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        int id = 9527;

        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setId(id);

        return user;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllActivity(){
        List<User> users = userService.queryAll();
        if(users.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/music/songs", method = RequestMethod.GET)
    public ResponseEntity<List<Song>> listAllSongs(){
        List<Song> songs = songService.queryAll();
        if(songs.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }
//    /** localhost:8080/zkf/home/users/userinfo?name=koado&password=123456 返回Json格式(list) **/
//    @RequestMapping(value = "/users/{userinfo}", method = RequestMethod.GET)
//    @ResponseBody public List<User> foo6(@PathVariable String userinfo, HttpServletRequest request){
//        String name = request.getParameter("name");
//        String password = request.getParameter("password");
//
//        User user = new User();
//        User user1 = new User();
//
//        user.setName(name);
//        user.setPassword(password);
//        user.setId(9527);
//
//        user1.setName(name + name);
//        user1.setPassword(password + password);
//        user1.setId(4836);
//
//        List<User> list = new ArrayList<>();
//        list.add(user);
//        list.add(user1);
//
//        return list;
//    }
    /** localhost:8080/zkf/home/user_/2 返回Json格式 结合Hibernate **/
    @RequestMapping(value = "/user_/{id}", method = RequestMethod.GET)
    @ResponseBody public User getById(@PathVariable int id){
        User user = userService.getUserById(id);
        return user;
    }

    @RequestMapping(value = "/activity/{id}", method = RequestMethod.GET)
    @ResponseBody public ResponseEntity<Activity> findItemById(@PathVariable int id){
        Activity activity = activityService.findItemById(id);
        return new ResponseEntity<>(activity, HttpStatus.OK);
    }

    @RequestMapping(value = "/music/{id}", method = RequestMethod.GET)
    @ResponseBody public ResponseEntity<Song> findById(@PathVariable int id){
        List<Song> list = songService.findById(id);
        Song song = list.get(0);
        return new ResponseEntity<>(song, HttpStatus.OK);
    }

    @RequestMapping(value = "/music_/{tag}", method = RequestMethod.POST)
    @ResponseBody public ResponseEntity<List<Song>> findByTag(@PathVariable String tag){
        List<Song> list = null;

        if(tag.contains(",")){
            String tags[] = tag.split(",");
            list = songService.findByTwoTags(tags);
        } else
         list = songService.findByTag(tag);
        if(list.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestBody User user){
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/activity/", method = RequestMethod.POST)
    public ResponseEntity<Activity> createActivity(@RequestBody Activity activity){
        System.out.println("Creating Activity" + activity.getTitle());
        activityService.addActivity(activity);
        return new ResponseEntity<>(activity, HttpStatus.OK);
    }

    @RequestMapping(value = "/sentiment/{text}", method = RequestMethod.POST)
    @ResponseBody public ResponseEntity<String> sentimentDefined(@PathVariable String text) throws Exception{
        String[] args_ = new String[] {"python", "D:\\sentiment\\sentiment.py", text};
        Process pr = Runtime.getRuntime().exec(args_);
        BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line = in.readLine();
        if(line != null)
            return new ResponseEntity<String>(line, HttpStatus.OK);
        else
            return new ResponseEntity<String>("Failed", HttpStatus.OK);
    }
}
