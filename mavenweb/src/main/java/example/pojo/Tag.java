package example.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "song_tags")
public class Tag {
    private int id;
    private String tag;

    @Id
    @GeneratedValue
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getTag(){
        return tag;
    }

    public void setTag(String tag){
        this.tag = tag;
    }
}
