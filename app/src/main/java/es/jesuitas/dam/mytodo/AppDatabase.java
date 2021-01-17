package es.jesuitas.dam.mytodo;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Task.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();

    private static AppDatabase noteDB;

    public static AppDatabase getInstance(Context context) {
        if (noteDB == null) {
            noteDB = buildDatabaseInstance(context);
        }
        return noteDB;
    }

    private static AppDatabase buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(context,
                AppDatabase.class,
                "MyToDos")
                .allowMainThreadQueries()
                .build();
    }
}
