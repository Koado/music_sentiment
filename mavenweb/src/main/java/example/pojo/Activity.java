package example.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "activity")

public class Activity {
    private int id;
    private String title;
    private String club;
    private String school;
    private String address;
    private int money;

    public Activity(){

    }

    public Activity(String title, String club, String school, String address, int money){
        this.title = title;
        this.club = club;
        this.school = school;
        this.address = address;
        this.money = money;
    }

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

    public void setTitle(String title){
        this.title = title;
    }

    public String getClub(){
        return club;
    }

    public void setClub(String club){
        this.club = club;
    }

    public String getSchool(){
        return school;
    }

    public void setSchool(String school){
        this.school = school;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public int getMoney(){
        return money;
    }

    public void setMoney(int money){
        this.money = money;
    }
}
