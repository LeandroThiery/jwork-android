package leandrothiery.jwork_android;

import java.io.Serializable;

/**
 * Recruiter's Location
 *
 * @author Leandro Thiery
 * @version 06/25/2021
 */
public class Location implements Serializable {
    private String province;
    private String description;
    private String city;

    /**
     * Constructor of Location
     *
     * @param province
     * @param description
     * @param city
     */
    public Location(String province, String description, String city) {
        this.province = province;
        this.description = description;
        this.city = city;
    }

    /**
     * @return province
     */
    public String getProvince() {
        return province;
    }

    /**
     * @param province new province
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city new city
     */
    public void setCity(String city) {
        this.city = city;
    }
}
