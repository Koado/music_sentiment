package example.dao;

import example.pojo.Song;
import example.pojo.Tag;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import org.hibernate.*;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class SongDao {
    @Resource
    SessionFactory sessionFactory;

    public List<Song> queryAll(){
        Session session = sessionFactory.openSession();
        List<Song> list = null;
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            //hql语句查询不是接的表名而是对象名称
            String sql = "from Song";
            Query query = session.createQuery(sql);
            list = query.list();
            transaction.commit();
        } catch (HibernateException e){
            e.printStackTrace();
            if (transaction != null){
                transaction.rollback();
            }
        } finally {
                if(session.isOpen() && session != null){
                    session.close();
                }
        }
        return list;
    }

    public List<Song> findById(int id){
        Session session = sessionFactory.openSession();
        List<Song> list = null;
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            String sql = "from Song where id = :n";
            Query query = session.createQuery(sql);
            query.setInteger("n", id);
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

    public List<Song> findByTag(String tag){
        Session session = sessionFactory.openSession();
        List<Song> list = null;
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            String hql = "select s.title, s.author, s.time, s.image from Song s, Tag t where s.id = t.id and t.tag = :n";
            Query query = session.createQuery(hql);
            query.setString("n", tag);
            list = query.list();
            transaction.commit();
        } catch (HibernateException e){
            e.printStackTrace();
            if (transaction != null){
                transaction.rollback();
            }
        } finally {
            if (session.isOpen() && session != null){
                session.close();
            }
        }
        return list;
    }

    public List<Song> findByTwoTags(String[] tags){
        Session session = sessionFactory.openSession();
        List<Song> list = null;
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            String hql = "select s.title, s.author, s.time, s.image from Song s where s.id = any(" +
                    "select t.id from Tag t where t.tag = :n and t.id = any(" +
                    "select u.id from Tag u where u.tag = :m ))";
            Query query = session.createQuery(hql);
            query.setString("n", tags[0]);
            query.setString("m", tags[1]);
            list = query.list();
            transaction.commit();
        } catch (HibernateException e){
            e.printStackTrace();
            if (transaction != null){
                transaction.rollback();
            }
        } finally {
            if(session.isOpen() && session != null){
                session.close();
            }
        }
        return list;
    }
}
