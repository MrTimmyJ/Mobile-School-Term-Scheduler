package com.timj.c196.Database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

import com.timj.c196.dao.TermDAO;
import com.timj.c196.dao.CourseDAO;
import com.timj.c196.dao.AssessmentDAO;
import com.timj.c196.entities.Term;
import com.timj.c196.entities.Course;
import com.timj.c196.entities.Assessment;

@Database(entities={Term.class, Course.class, Assessment.class}, version=12, exportSchema = false)
public abstract class C196Database extends RoomDatabase {
    public abstract TermDAO termDAO();
    public abstract CourseDAO courseDAO();
    public abstract AssessmentDAO assessmentDAO();

    private static volatile C196Database INSTANCE;

    static C196Database getDatabase(final Context context) {
        if(INSTANCE==null) {
            synchronized (C196Database.class) {
                if(INSTANCE==null) {
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(), C196Database.class, "SchedulerDatabase.db").fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }

}
