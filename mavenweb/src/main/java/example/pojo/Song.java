package example.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "songs")
public class Song {
    private int id;
    private String title;
    private String author;
    private String time;
    private String image;

    @Id
    @GeneratedValue
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){ this.title = title;}

    public String getAuthor(){
        return author;
    }

    public void setAuthor(String author) {this.author = author;}

    public String getTime(){
        return time;
    }

    public void setTime(String time) {this.time = time;}

    public String getImage(){
        return image;
    }

    public void setImage(String image){
        this.image = image;
    }
}
