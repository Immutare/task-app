package com.example.carlosgarcia.task_app;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface TaskDAO {
    @Query("SELECT * FROM taskTask")
    List<taskTask> getAll();
    @Query("SELECT * FROM taskTask WHERE intPercentage = 0")
    List<taskTask> getToDo();
    @Query("SELECT * FROM taskTask WHERE intPercentage > 0 and intPercentage < 100")
    List<taskTask> getDoing();
    @Query("SELECT * FROM taskTask WHERE intPercentage = 100")
    List<taskTask> getDone();

    @Insert
    void insertTask(taskTask task);
    @Delete
    void deleteTask(taskTask task);
}
