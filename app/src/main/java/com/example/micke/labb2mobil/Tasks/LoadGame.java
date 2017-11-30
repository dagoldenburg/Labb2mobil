package com.example.micke.labb2mobil.Tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.micke.labb2mobil.Activities.View.ViewState;
import com.example.micke.labb2mobil.Model.GameState;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Created by Micke on 11/26/2017.
 */

public class LoadGame extends AsyncTask<Void,Void,Boolean>{

    private Context context;
    private String name;


    public interface TaskListener {
        public void onFinished(Boolean result);
    }
    private final TaskListener taskListener;

    public LoadGame(TaskListener taskListener, Context context, String name){
        this.context = context;
        this.name = name;
        this.taskListener = taskListener;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);
        taskListener.onFinished(result);
    }

    /**
     * a new task to loading a saved game from file
     * @param voids
     * @return
     */
    @Override
    protected Boolean doInBackground(Void... voids){
        try {
            FileInputStream fis = context.openFileInput(name);
            ObjectInputStream ois = new ObjectInputStream(fis);

            GameState.setGameState( (GameState) ois.readObject());
        } catch (IOException|ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        Log.i("APPLICATION","Successfull open");
        return true;
    }
}
