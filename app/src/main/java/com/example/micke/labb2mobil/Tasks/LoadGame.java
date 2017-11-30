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

    public LoadGame(Context context,String name){
        this.context = context;
        this.name = name;
    }

    @Override
    protected Boolean doInBackground(Void... voids){
        try {
            FileInputStream fis = context.openFileInput(name+"GameState");
            ObjectInputStream ois = new ObjectInputStream(fis);

            GameState.setGameState( (GameState) ois.readObject());
        } catch (IOException|ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        try {
            FileInputStream fis = context.openFileInput(name+"ViewState");
            ObjectInputStream ois = new ObjectInputStream(fis);

            ViewState.setViewState( (ViewState) ois.readObject());
        } catch (IOException|ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        Log.i("APPLICATION","Successfull open");
        return true;
    }
}
