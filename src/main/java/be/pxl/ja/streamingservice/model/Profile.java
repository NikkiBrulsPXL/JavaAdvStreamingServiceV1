package be.pxl.ja.streamingservice.model;

import be.pxl.ja.streamingservice.exception.InvalidDateException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Profile implements Comparable<Profile>{
    private String name;
    private LocalDate dateOfBirth;
    private List<Content> recentlyWatched;
    private List<Content> currentlyWatching;
    private ArrayList<Content> myList;
    private String avatar;

    public Profile(String name){
        this(name, "profile1");
    }

    public Profile(String name, String avatar){
        this.name = name;
        this.avatar = avatar;
        recentlyWatched = new ArrayList<>();
        currentlyWatching = new ArrayList<>();
        myList = new ArrayList<>();
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth(){
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth){
        if(dateOfBirth.isAfter(LocalDate.now())) {
            throw new InvalidDateException(dateOfBirth, "date of birth", "No date of birth in future allowed.");
        }
        this.dateOfBirth = dateOfBirth;
    }

    public String getAvatar(){
        return avatar;
    }

    public int getAge() {
        if (dateOfBirth == null) {
            return 0;
        }
        return (int) ChronoUnit.YEARS.between(dateOfBirth, LocalDateTime.now());
    }

    public void startWatching(Content content){
        if(!currentlyWatching.contains(content)) {
            currentlyWatching.add(0, content);
        }
    }

    public void finishWatching(Content content){
        currentlyWatching.remove(content);
        if(!recentlyWatched.contains(content)){
            recentlyWatched.add(0, content);
        }
    }

    public List<Content> getRecentlyWatched(){
        return recentlyWatched;
    }

    public List<Content> getCurrentlyWatching(){
        return currentlyWatching;
    }

    public List<Content> getMyList() {
        return myList;
    }

    public void addToMyList(Content content) {
        if(!isInMyList(content)){
            myList.add(content);
        }
    }

    public boolean isInMyList(Content content) {
        return myList.contains(content);
    }

    public boolean allowedToWatch(Content content){
        return content.getMaturityRating().getMinimumAge() <= getAge();
    }

    public void removeFromMyList(Content content) {
        myList.remove(content);
    }

    @Override
    public int compareTo(Profile o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }

        Profile profile = (Profile) o;

        return getName() != null ? getName().equals(profile.getName()) : profile.getName() == null;
    }

    @Override
    public String toString(){
        return "Profile{" + "name='" + name + "'" + "}";
    }
}
