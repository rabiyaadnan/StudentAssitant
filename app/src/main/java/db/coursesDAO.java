package db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface coursesDAO {
    @Insert
    void insertAll(courses... courses);

    @Query("SELECT * FROM courses where coursename =  :CourseName") // Read
    courses findByName(String CourseName);

    @Query("SELECT * FROM courses")
    List<courses> findAll();

    @Update
    void updateAll(courses... courses);

//    @Query("UPDATE courses SET courseName = :newName WHERE courseName = :oldName")
//    boolean updateName(String newName, String oldName);
//
//    @Query("UPDATE courses SET Days = :newDay WHERE courseName = :course")
//    boolean updateDay(String newDay, String course);
//
//    @Query("UPDATE courses SET classroom = :newClass WHERE courseName = :course")
//    boolean updateClass(String newClass, String course);
//
//    @Query("UPDATE courses SET classroom = :newTime WHERE courseName = :course")
//    boolean updateTime(String newTime, String course);

    @Delete
    void delete(courses... courses);
}
