package com.aadhyaapps.anothertodo.model;

import java.sql.Date;
import java.util.UUID;

/**
 * Created by Maya on 1/14/2017.
 */
public class Task {

    private String description;
    private UUID taskId;
    private UUID parentTaskId;
    private Date createdDate;
    private boolean done;

    public Task(String description)
    {
        this(description, null);
    }

    public Task (String description, UUID parentTaskId)
    {
        this.description = description;
        this.taskId = UUID.randomUUID();
        this.parentTaskId = parentTaskId;
        this.createdDate = new Date(System.currentTimeMillis());
        this.done = false;
    }

    public UUID getTaskId() {
        return taskId;
    }

    public String getDescription() {
        return description;
    }

    public UUID getParentTaskId() {
        return parentTaskId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done)
    {
        this.done = done;
    }


}
