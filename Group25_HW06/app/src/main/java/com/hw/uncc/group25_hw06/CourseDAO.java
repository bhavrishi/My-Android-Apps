package com.hw.uncc.group25_hw06;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Rishi on 01/11/17.
 */

public class CourseDAO {
    private SQLiteDatabase db;
    String value1,key;
    CourseDAO(SQLiteDatabase db) {
        this.db = db;
    }

    long save(Course course) {
        ContentValues value = new ContentValues();
        HashMap<String,String> map=course.getUpwd();
        for ( Map.Entry<String, String> entry : map.entrySet()) {
             key = entry.getKey();
             value1= entry.getValue();
            // do something with key and/or tab
        }
       // Map.Entry<String,String> entry= (Map.Entry<String, String>) map.entrySet().;
        value.put(CourseTable.COLUMN_UNAME, key);
        value.put(CourseTable.COLUMN_PWD, value1);
        value.put(CourseTable.COLUMN_TITLE, course.getCourseName());
        value.put(CourseTable.COLUMN_CREDIT, course.getCredit_hours());
        value.put(CourseTable.COLUMN_DAY, course.getDay());
        value.put(CourseTable.COLUMN_SEM, course.getSemester());
        value.put(CourseTable.COLUMN_TIME, course.getTime());
        value.put(CourseTable.COLUMN_INST, course.getInstructor());
        return db.insert(CourseTable.TABLE_NAME, null, value);
    }



    boolean delete(Course tune) {
        return db.delete(CourseTable.TABLE_NAME, CourseTable.COLUMN_ID + "=?", new String[]{tune.getId() + ""}) > 0;
    }

    boolean update(Course course) {
        ContentValues value = new ContentValues();
        HashMap<String,String> map=course.getUpwd();
        Map.Entry<String,String> entry= (Map.Entry<String, String>) map.entrySet();
        value.put(CourseTable.COLUMN_UNAME, entry.getKey());
        value.put(CourseTable.COLUMN_PWD, entry.getValue());
        value.put(CourseTable.COLUMN_TITLE, course.getCourseName());
        value.put(CourseTable.COLUMN_CREDIT, course.getCredit_hours());
        value.put(CourseTable.COLUMN_DAY, course.getDay());
        value.put(CourseTable.COLUMN_SEM, course.getSemester());
        value.put(CourseTable.COLUMN_TIME, course.getTime());
        value.put(CourseTable.COLUMN_INST, course.getInstructor());
        return db.update(CourseTable.TABLE_NAME, value, CourseTable.COLUMN_ID + "=?", new String[]{course.getId() + ""}) > 0;

    }



    Course get(long id) {
        Course tune = null;
        Cursor c = db.query(true, CourseTable.TABLE_NAME, new String[]{CourseTable.COLUMN_ID, CourseTable.COLUMN_UNAME, CourseTable.COLUMN_PWD,
                        CourseTable.COLUMN_TITLE,CourseTable.COLUMN_DAY,CourseTable.COLUMN_TIME,CourseTable.COLUMN_SEM,CourseTable.COLUMN_INST,
                        CourseTable.COLUMN_CREDIT}
                , CourseTable.COLUMN_ID + "=?", new String[]{id + ""}, null, null, null, null);

        if (c != null && c.moveToFirst()) {
            tune = buildTuneFromCursor(c);
            if (!c.isClosed()) {
                c.close();
            }
        }

        return null;
    }

    List<Course> getAll(String uname) {
        List<Course> notes = new ArrayList<>();
        Cursor c = db.query(CourseTable.TABLE_NAME,
                new String[]{CourseTable.COLUMN_ID, CourseTable.COLUMN_UNAME, CourseTable.COLUMN_PWD,
                        CourseTable.COLUMN_TITLE,CourseTable.COLUMN_DAY,CourseTable.COLUMN_TIME,CourseTable.COLUMN_SEM,CourseTable.COLUMN_INST,
                        CourseTable.COLUMN_CREDIT}
                , CourseTable.COLUMN_UNAME + "=?", new String[]{uname + ""}, null, null, null, null);
        if (c != null && c.moveToFirst()) {
            do {
                Course course = buildTuneFromCursor(c);
                notes.add(course);
            } while (c.moveToNext());
        }
        return notes;
    }

    private Course buildTuneFromCursor(Cursor c) {
        Course course = null;
        if (c != null) {
            course = new Course();
            course.setId(c.getLong(0));
            HashMap<String,String> map=new HashMap<String, String>();
            map.put(c.getString(1),c.getString(2));
            course.setUpwd((HashMap<String, String>) map);
            course.setCourseName(c.getString(3));
            course.setDay(c.getString(4));
            course.setTime(c.getString(5));
            course.setSemester(c.getString(6));
            course.setInstructor(c.getString(7));
            course.setCredit_hours(c.getString(8));

        }
        return course;
    }

}
