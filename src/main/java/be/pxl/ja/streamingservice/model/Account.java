package be.pxl.ja.streamingservice.model;

import be.pxl.ja.streamingservice.exception.TooManyProfilesException;
import be.pxl.ja.streamingservice.util.PasswordUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public class Account {
    private static final int MINIMUM_PASSWORD_STRENGTH = 5;
    private String email;
    private String password;
    private PaymentInfo paymentInfo;
    private StreamingPlan streamingPlan;
    private List<Profile> profiles = new ArrayList<Profile>();

    public Account(String email, String password) {
        if(email.length() == 0 || password.length() == 0){
            throw new IllegalArgumentException("Email and Password have to be entered");
        }
        else {
            this.email = email;
            setPassword(password);
            profiles.add(new Profile("Profile1"));
            streamingPlan = StreamingPlan.BASIC;
        }
    }

    public void setStreamingPlan(StreamingPlan streamingPlan) {
        this.streamingPlan = streamingPlan;
    }

    public void addProfile(Profile profile) {
        if(profiles.size() < streamingPlan.getNumberOfScreens()) {
            profiles.add(profile);
        }
        else {
            throw new TooManyProfilesException("Maximum number of profiles in use, cannot create new profile");
        }
    }

    public String getEmail() {
        return email;
    }

    public boolean verifyPassword(String password) {
        return PasswordUtil.isValid(password, this.password);
    }

    public void setPaymentInfo(PaymentInfo paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public PaymentInfo getPaymentInfo(){
        return paymentInfo;
    }

    public void setPassword(String password) {
        if(PasswordUtil.calculateStrength(password) >= MINIMUM_PASSWORD_STRENGTH) {
            this.password = PasswordUtil.encodePassword(password);
        }
        else{
            throw new IllegalArgumentException("Password is too weak");
        }
    }

    public int getNumberOfProfiles(){
        return profiles.size();
    }

    public List<Profile> getProfiles() {
        Collections.sort(profiles);
        return profiles;
    }
}
