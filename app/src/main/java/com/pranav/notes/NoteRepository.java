package com.pranav.notes;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {
    //A repository is a standard java class. Its task is to establish connection between different data sources and the rest of the application


    private NoteDao noteDao;
    private LiveData<List<Note>> notes;

    public NoteRepository(Application application){
        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDao = database.noteDao();
        notes = noteDao.getAllNotes();

    }

    public void insert(Note note){

        new InsertNoteAsyncTask(noteDao).execute(note);

    }
    public void update(Note note){

        new UpdateNoteAsyncTask(noteDao).execute(note);

    }

    public void delete(Note note){

        new DeleteNoteAsyncTask(noteDao).execute(note);

    }

    public LiveData<List<Note>> getAllNotes(){
        return notes;
    }
    //1.parameter for doInBackground method
    //2.parameter for onProgressUpdate method
    //3.parameter return type of doInBackground
    private static class InsertNoteAsyncTask extends AsyncTask<Note,Void,Void>{

        private NoteDao noteDao;

        private InsertNoteAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }


        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);

            return null;//element inserted in the 0th index of the notes array
        }

    }
    private static class UpdateNoteAsyncTask extends AsyncTask<Note,Void,Void>{

        private NoteDao noteDao;

        private UpdateNoteAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }


        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);

            return null;//element inserted in the 0th index of the notes array
        }

    }
    private static class DeleteNoteAsyncTask extends AsyncTask<Note,Void,Void>{

        private NoteDao noteDao;

        private DeleteNoteAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }


        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);

            return null;//element inserted in the 0th index of the notes array
        }

    }
}
