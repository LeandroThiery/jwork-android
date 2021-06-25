package leandrothiery.jwork_android;

import java.io.Serializable;

/**
 * Recruiter of Job
 *
 * @author Leandro Thiery
 * @version 06/25/2021
 */
public class Recruiter implements Serializable {
    private int id;
    private String name;
    private String email;
    private String phoneNumber;
    private Location location;

    /**
     * Constructor of Recruiter
     * @param id recruiter id
     * @param name name of recruiter
     * @param email email of recruiter
     * @param phoneNumber recruiter's phone number
     * @param location recruiter's Location
     */
    public Recruiter(int id, String name, String email, String phoneNumber, Location location) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.location = location;
    }

    /**
     * @return id of recruiter
     */
    public int getId() {
        return id;
    }

    /**
     * @param id new recruiter id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return name of recruiter
     */
    public String getName() {
        return name;
    }

    /**
     * @param name new recruiter name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return email of recruiter
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email new recruiter email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return recruiter's phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber new phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return Location of recruiter
     */
    public Location getLocation() {
        return location;
    }

    /**
     * @param location new recruiter Location
     */
    public void setLocation(Location location) {
        this.location = location;
    }
}
