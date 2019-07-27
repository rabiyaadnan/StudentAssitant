package db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Time;

@Entity(tableName = "courses")
public class courses {

    @PrimaryKey()
    @NonNull
    private String CourseName;

    @ColumnInfo
    private String Days;

    @ColumnInfo
    private String classroom;

    @ColumnInfo
    private String time;

    @ColumnInfo
    private boolean courseDel = false;

    public String getDod() {
        return dod;
    }

    public void setDod(String dod) {
        this.dod = dod;
    }

    @ColumnInfo
    private String dod;

    public courses() {
    }

    @NonNull
    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(@NonNull String courseName) {
        CourseName = courseName;
    }

    public String getDays() {
        return Days;
    }

    public void setDays(String days) {
        Days = days;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isCourseDel() {
        return courseDel;
    }

    public void setCourseDel(boolean courseDel) {
        this.courseDel = courseDel;
    }

    public courses(@NonNull String courseName, String days, String classroom, String time) {
        CourseName = courseName;
        Days = days;
        this.classroom = classroom;
        this.time = time;
        this.courseDel = false;
        this.dod = "";
    }
}
