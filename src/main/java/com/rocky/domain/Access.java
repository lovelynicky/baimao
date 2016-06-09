package com.rocky.domain;

/**
 * Created by liluoqi on 16/6/2.
 */
public class Access {
    private String comment;
    private String evaluation;
    private String summary;

    public Access(){

    }

    public Access(String comment, String evaluation, String summary) {
        this.comment = comment;
        this.evaluation = evaluation;
        this.summary = summary;
    }

    public String getComment() {
        return comment;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public String getSummary() {
        return summary;
    }
}
