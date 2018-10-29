package example.service;

import example.dao.SongDao;
import example.pojo.Song;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("songService")
public class SongService {
    @Resource
    SongDao songDao;

    @Transactional
    public List<Song> queryAll(){
        List<Song> list = null;
        list = songDao.queryAll();
        return list;
    }

    public List<Song> findById(int id){
        List<Song> list = null;
        list = songDao.findById(id);
        return list;
    }

    public List<Song> findByTag(String tag){
        List<Song> list = null;
        list = songDao.findByTag(tag);
        return list;
    }

    public List<Song> findByTwoTags(String tags[]){
        List<Song> list = null;
        list = songDao.findByTwoTags(tags);
        return list;
    }
}
