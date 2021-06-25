package leandrothiery.jwork_android;

import java.io.Serializable;

/**
 * Job that are available by Recruiters
 *
 * @author Leandro Thiery
 * @version 06/25/2021
 */
public class Job implements Serializable {
    private int id;
    private String name;
    private Recruiter recruiter;
    private int fee;
    private String category;

    /**
     * Constructor of a Job
     *
     * @param id        id of job
     * @param name      name of job
     * @param recruiter job's recruiter
     * @param fee       job fee
     * @param category  job category
     */
    public Job(int id, String name, Recruiter recruiter, int fee, String category) {
        this.id = id;
        this.name = name;
        this.recruiter = recruiter;
        this.fee = fee;
        this.category = category;
    }

    /**
     * @return id of job
     */
    public int getId() {
        return id;
    }

    /**
     * @param id new id of job
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return name of job
     */
    public String getName() {
        return name;
    }

    /**
     * @param name new job name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Recruiter of Job
     */
    public Recruiter getRecruiter() {
        return recruiter;
    }

    /**
     * @param recruiter new job's Recruiter
     */
    public void setRecruiter(Recruiter recruiter) {
        this.recruiter = recruiter;
    }

    /**
     * @return fee of Job
     */
    public int getFee() {
        return fee;
    }

    /**
     * @param fee new job fee
     */
    public void setFee(int fee) {
        this.fee = fee;
    }

    /**
     * @return category of job
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category new job category
     */
    public void setCategory(String category) {
        this.category = category;
    }
}
