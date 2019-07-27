package db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;
import java.sql.Time;
import java.util.UUID;

@Entity(tableName = "details")
public class details {

    @NonNull
    private String CourseName;

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private String title;

    @ColumnInfo
    private boolean completed;

    @ColumnInfo
    private String description;

    @ColumnInfo
    private String date;

    @ColumnInfo
    private String time;

    @ColumnInfo
    private String PQA;


    @ColumnInfo
    private Boolean courseDel = false;

    @ColumnInfo
    private Boolean detailDel =  false;

    @ColumnInfo
    private String dod;

    public details() {
    }

    @NonNull
    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(@NonNull String courseName) {
        CourseName = courseName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPQA() {
        return PQA;
    }

    public void setPQA(String PQA) {
        this.PQA = PQA;
    }

    public Boolean getCourseDel() {
        return courseDel;
    }

    public void setCourseDel(Boolean courseDel) {
        this.courseDel = courseDel;
    }

    public Boolean getDetailDel() {
        return detailDel;
    }

    public void setDetailDel(Boolean detailDel) {
        this.detailDel = detailDel;
    }

    public String getDod() {
        return dod;
    }

    public void setDod(String dod) {
        this.dod = dod;
    }

    public details(@NonNull String courseName, @NonNull String title, boolean completed, String description, String date, String time, String PQA) {
        this.CourseName = courseName;
        this.title = title;
        this.completed = completed;
        this.description = description;
        this.date = date;
        this.time = time;
        this.PQA = PQA;
        this.courseDel = false;
        this.detailDel = false;
        this.dod = "";
    }
}


