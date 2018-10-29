package example.service;

import example.dao.UserDao;
import example.pojo.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserService {
    @Resource
    UserDao userDao;

    @Transactional
    public void save(User user){
        userDao.save(user);
    }

    public List<User> queryAll(){
       return userDao.queryAll();
    }

    public User getUserById(int id){
        return userDao.getUserById(id);
    }
}
