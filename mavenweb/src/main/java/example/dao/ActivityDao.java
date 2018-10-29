package example.dao;

import example.pojo.Activity;
import org.hibernate.*;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import java.util.List;

@Repository
public class ActivityDao {

    @Resource
    SessionFactory sessionFactory;

    public void addActivity(Activity activity){
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.save(activity);
            transaction.commit();
        } catch (HibernateException e){
            e.printStackTrace();
            if(transaction != null){
                transaction.rollback();
            }
        } finally {
            if(session.isOpen() && session != null){
                session.close();
            }
        }
    }

    public List<Activity> queryAll(){
        Session session = sessionFactory.openSession();
        List<Activity> list = null;
        Transaction transaction = null;

        try{
            transaction = session.beginTransaction();
            String sql = "from Activity";
            Query query = session.createQuery(sql);
            list = query.list();
            transaction.commit();
        } catch (HibernateException e){
            e.printStackTrace();
            if(transaction != null){
                transaction.rollback();
            }
        } finally {
            if(session.isOpen() && session != null){
                session.close();
            }
        }
        return list;
    }

    public Activity findItemById(int id){
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Activity activity = null;

        try{
            transaction = session.beginTransaction();
            activity = (Activity) session.get(Activity.class, id);
            transaction.commit();
        } catch (HibernateException e){
            e.printStackTrace();
            if(transaction != null){
                transaction.rollback();
            }
        } finally {
            if(session.isOpen() && session != null){
                session.close();
            }
        }
        return activity;
    }
}
