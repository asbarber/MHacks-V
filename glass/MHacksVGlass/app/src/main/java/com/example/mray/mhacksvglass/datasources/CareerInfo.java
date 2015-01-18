package com.example.mray.mhacksvglass.datasources;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Aaron Barber on 17/01/15.
 */
public class CareerInfo {
    private static final List<CareerInfo> initData(){
        CareerInfo Aaron = new CareerInfo("Aaron", "Barber", "University of Michigan, Computer Science", "file1.png");
        CareerInfo Katelyn = new CareerInfo("Katelyn", "Dunaski", "Michigan State University, Applied Engineering Sciences", "file2.png");
        CareerInfo Michael = new CareerInfo("Michael", "Ray", "University of Michigan, Electrical Engineering", "file3.png");
        CareerInfo Sean = new CareerInfo("Sean", "Acker", "University of Michigan, Mechanical Engineering", "file4.png");

        return Arrays.asList(Aaron, Katelyn, Michael, Sean);
    }

    public static final List<CareerInfo> Dataset = initData();

    private String firstName;
    private String lastName;
    private String headline;
    private String pictureUrl;

    public CareerInfo(){
        this("Dan", "Smith", "BYU", "<unknown>");
    }

    public CareerInfo(String firstName, String lastName, String headline, String pictureUrl){
        this.firstName = firstName;
        this.lastName = lastName;
        this.headline = headline;
        this.pictureUrl = pictureUrl;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getHeadline(){
        return headline;
    }

    public String getPictureUrl(){
        return pictureUrl;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public void setPictureUrl(String pictureUrl) { this.pictureUrl = pictureUrl; }
}
