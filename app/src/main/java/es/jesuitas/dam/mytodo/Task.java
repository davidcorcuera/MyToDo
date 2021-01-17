package es.jesuitas.dam.mytodo;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Task {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "task")
    public String mTask;

    @ColumnInfo(name = "desc")
    public String mDesc;

    @ColumnInfo(name = "finished")
    public boolean mFinished;

    public Task(String task, String desc, boolean finished) {
        this.mTask = task;
        this.mDesc = desc;
        this.mFinished = finished;
    }
}
