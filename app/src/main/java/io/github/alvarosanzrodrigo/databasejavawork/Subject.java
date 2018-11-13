package io.github.alvarosanzrodrigo.databasejavawork;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "subject_table")
public class Subject {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "id")
    private int subjectId;
    @ColumnInfo(name = "teacher")
   private String teacher;
    @ColumnInfo(name = "nStudents")
    private int students;
    @ColumnInfo(name = "hours")
    private int hours;

    public Subject(@NonNull String name, int subjectId, String teacher, int students, int hours) {
        this.name = name;
        this.subjectId = subjectId;
        this.teacher = teacher;
        this.students = students;
        this.hours = hours;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public int getStudents() {
        return students;
    }

    public void setStudents(int students) {
        this.students = students;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }
}