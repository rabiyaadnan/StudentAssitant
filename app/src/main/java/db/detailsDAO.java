package db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
import java.sql.Date;
import java.sql.Time;
@Dao
public interface detailsDAO {

    @Query("SELECT * FROM details")
    List<details> findAll();

    @Insert
    void insertAll(details... details);

    @Query("SELECT * FROM details where id = :ID")
    details findByID(int ID);

    @Update
    void updateAll(details... details);
//
//    @Query("UPDATE details SET courseName = :newName WHERE id = :id")
//    boolean updateName(String newName, String id);

//    @Query("UPDATE details SET courseName = :newName WHERE id = :id")
//    boolean updateTitle(String newName, String id);
//
//    @Query("UPDATE details SET completed = :completed WHERE id = :id")
//    boolean updateCompleted(boolean completed, String id);
//
//    @Query("UPDATE details SET description = :description WHERE id = :id")
//    boolean updateDay(String description, String id);
//
//    @Query("UPDATE details SET date = :date WHERE id = :id")
//    boolean updateDate(Date date, String id);
//
//    @Query("UPDATE details SET time = :newTime WHERE id = :id")
//    boolean updateTime(Time newTime, String id);
//
//    @Query("UPDATE details SET PQA = :pqa WHERE id = :id")
//    boolean updatePQA(String pqa, String id);
//
//    @Query("UPDATE details SET courseDel = :cd WHERE id = :id")
//    boolean updateCourseDel(boolean cd, String id);

    @Delete
    void delete(details... details);

    @Query("SELECT * FROM details where courseName = :name")
    List<details> findByCourseName(String name);
}