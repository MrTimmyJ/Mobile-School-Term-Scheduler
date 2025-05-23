package com.timj.c196.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.timj.c196.entities.Assessment;

import java.util.List;

@Dao
public interface AssessmentDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Assessment assessment);

    @Update
    void update(Assessment assessment);

    @Delete
    void delete(Assessment assessment);

    @Query("SELECT * FROM ASSESSMENTS ORDER BY assessmentID ASC")
    List<Assessment> getAllAssessments();

//    @Query("SELECT * FROM ASSESSMENTS WHERE assessmentID=courseID ORDER BY assessmentID ASC")
//    List<Assessment> getAllAssessments();

//    @Query("SELECT * FROM ASSESSMENTS WHERE courseID= :courseID ORDER BY assessmentID ASC")
//    List<List<Assessment>> getAssociatedAssessments(int courseID);
}
