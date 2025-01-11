package com.timj.c196.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.Arrays;

@Entity(tableName="courses")
public class Course {
    @PrimaryKey(autoGenerate = true)
    private int courseID;
    private int termID;
    private int status;

    private String courseName;
    private String startDate;
    private String endDate;

    private String mentorName;
    private String mentorPhone;
    private String mentorEmail;
    private String courseNote;

    public Course(int courseID, int termID, int status, String courseName, String startDate, String endDate, String mentorName, String mentorPhone, String mentorEmail, String courseNote) {
        this.courseID = courseID;
        this.termID = termID;
        this.status = status;
        this.courseName = courseName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.mentorName = mentorName;
        this.mentorPhone = mentorPhone;
        this.mentorEmail = mentorEmail;
        this.courseNote = courseNote;
    }

    public Course() {

    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getTermID() {
        return termID;
    }

    public void setTermID(int termID) {
        this.termID = termID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getMentorName() {
        return mentorName;
    }

    public void setMentorName(String mentorName) {
        this.mentorName = mentorName;
    }

    public String getMentorPhone() {
        return mentorPhone;
    }

    public void setMentorPhone(String mentorPhone) {
        this.mentorPhone = mentorPhone;
    }

    public String getMentorEmail() {
        return mentorEmail;
    }

    public void setMentorEmail(String mentorEmail) {
        this.mentorEmail = mentorEmail;
    }

    public String getCourseNote() {
        return courseNote;
    }

    public void setCourseNote(String courseNote) {
        this.courseNote = courseNote;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseID=" + courseID +
                ", termID=" + termID +
                ", status=" + status +
                ", courseName='" + courseName + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", mentorName='" + mentorName + '\'' +
                ", mentorPhone='" + mentorPhone + '\'' +
                ", mentorEmail='" + mentorEmail + '\'' +
                ", courseNote='" + courseNote + '\'' +
                '}';
    }
}
