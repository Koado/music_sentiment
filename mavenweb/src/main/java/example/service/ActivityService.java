package example.service;

import example.dao.ActivityDao;
import example.pojo.Activity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("activityService")
public class ActivityService {
    @Resource
    ActivityDao activityDao;

    @Transactional
    public void addActivity(Activity activity){
        activityDao.addActivity(activity);
    }

    public List<Activity> queryAll(){
        List<Activity> list = null;
        list = activityDao.queryAll();
        return list;
    }

    public Activity findItemById(int id){
        Activity activity = null;
        activity = activityDao.findItemById(id);
        return activity;
    }
}
