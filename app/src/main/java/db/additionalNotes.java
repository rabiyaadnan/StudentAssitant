package db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "additionalNotes")
public class additionalNotes {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    @NonNull
    private String title;

    @ColumnInfo
    private String description;

    public void setId(@NonNull int id) {
        this.id = id;
    }

    @ColumnInfo
    private String date;

    @ColumnInfo
    private String time;

    @ColumnInfo
    private boolean isDel = false;

    @ColumnInfo
    private String dod;

    public additionalNotes() {

    }

    public additionalNotes(@NonNull String title, String description, String date, String time) {
        //this.id = getID();
        this.title = title;
        this.description = description;
        this.date = date;
        this.time = time;
        this.isDel = false;
        this.dod = "";
    }

    public String getDod() {
        return dod;
    }

    public void setDod(String dod) {
        this.dod = dod;
    }

    @NonNull
    public int getId() {
        return id;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
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

    public boolean getDel() {
        return isDel;
    }

    public void setDel(boolean del) {
        isDel = del;
    }
}

