package com.pranav.notes;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")//this is the room annotations process by enabling this we can enable the room database to write the necessary code in the background
//the room database will now automatically create the sqllite tables for this project
public class Note {


    //defining the column names
    @PrimaryKey(autoGenerate = true)//only one of these value in the primary databse
    public int id;

    public String title;

    public String description;

    public Note(String title, String description) {//we donot choose id as autogenerate is true
        this.title = title;
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
