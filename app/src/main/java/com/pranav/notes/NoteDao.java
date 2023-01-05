package com.pranav.notes;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDao{//a dao has to be an interface or an abstract class

    @Insert
    void insert(Note note);//this is the method that will be used to insert the data into the database

    @Update
    void update(Note note);//this is the method that will be used to update the data into the database

    @Delete
    void delete(Note note);//this is the method that will be used to delete the data into the database

    //use the query to list the notes in the database

    @Query("SELECT * FROM note_table ORDER BY id ASC")
    LiveData<List<Note>> getAllNotes();//this is the method that will be used to get the data from the database


}
