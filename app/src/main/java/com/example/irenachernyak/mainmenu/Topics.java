package com.example.irenachernyak.mainmenu;

import java.io.Serializable;

/**
 * Created by irenachernyak on 8/10/15.
 */
public class Topics implements Serializable {

    String [] topics;

    public Topics(String[] topics) {
        this.topics = topics;
    }

    public String[] getTopics() {
        return topics;
    }

    public void setTopics(String[] topics) {
        this.topics = topics;
    }
}
