package db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {courses.class, details.class, additionalNotes.class},version = 4)
public abstract class database extends RoomDatabase {

    private static database INSTANCE;
    public abstract coursesDAO coursesDao();
    public abstract detailsDAO detailsDao();
    public abstract additionalNotesDAO additionalNotesDao();

    public static database getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), database.class, "user-database").build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}