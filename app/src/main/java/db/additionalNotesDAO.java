package db;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface additionalNotesDAO {
    @Insert
    void insertAll(additionalNotes... additionalNotes);

    @Query("SELECT * FROM additionalNotes where id =  :id")
    additionalNotes findByID(int id);

    @Update
    void updateAll(additionalNotes... additionalNotes);

    @Query("SELECT * FROM additionalNotes")
    List<additionalNotes> findAll();

    @Delete
    void delete(additionalNotes... additionalNotes);
}
