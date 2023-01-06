package com.pranav.notes;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Note.class},version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    private static NoteDatabase instance;//this is the instance of the database

    public abstract NoteDao noteDao();//this is the method that will be used to access the database

    public static synchronized NoteDatabase getInstance(Context context){

        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext()
                    ,NoteDatabase.class,"note_database")
                    //.allowMainThreadQueries()//not recommended
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

//            new PopulateDbAsyncTask(instance).execute();

            NoteDao noteDao = instance.noteDao();

            ExecutorService executorsService = Executors.newSingleThreadExecutor();

            executorsService.execute(new Runnable() {
                @Override
                public void run() {
                    noteDao.insert(new Note("Title 1","Description 1"));
                    noteDao.insert(new Note("Title 2","Description 2"));
                    noteDao.insert(new Note("Title 3","Description 3"));
                    noteDao.insert(new Note("Title 4","Description 4"));
                    noteDao.insert(new Note("Title 5","Description 5"));
                }
            });

        }
    };
/*
    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void> {
        private NoteDao noteDao;

        private PopulateDbAsyncTask(NoteDatabase db){
            noteDao = db.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new Note("Title 1","Description 1"));
            noteDao.insert(new Note("Title 2","Description 2"));
            noteDao.insert(new Note("Title 3","Description 3"));
            noteDao.insert(new Note("Title 4","Description 4"));
            noteDao.insert(new Note("Title 5","Description 5"));
            return null;
        }
    }
    */

}
