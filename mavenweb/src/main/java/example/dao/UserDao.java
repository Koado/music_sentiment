package example.dao;

import example.pojo.User;
import org.hibernate.*;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class UserDao {

    @Resource
    SessionFactory sessionFactory;

    public void save(User user){
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (HibernateException e){
            e.printStackTrace();
            if(transaction != null){
                transaction.rollback();
            }
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }

    }

    public List<User> queryAll(){
        Session session = sessionFactory.openSession();
        List<User> list = null;
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();

            String sql = "from User";
            Query query = session.createQuery(sql);
            list = query.list();
            transaction.commit();
        } catch (HibernateException e){
            e.printStackTrace();
            if(transaction != null){
                transaction.rollback();
            }
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        return list;
    }

    public User getUserById(int id){
        User user = null;
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            user = (User) session.get(User.class, id);
            transaction.commit();
        } catch (HibernateException e){
            e.printStackTrace();
            if(transaction != null){
                transaction.rollback();
            }
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        return user;
    }
}
