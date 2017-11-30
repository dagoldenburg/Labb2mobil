package com.example.micke.labb2mobil.Tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.micke.labb2mobil.Activities.GameActivity;
import com.example.micke.labb2mobil.Activities.View.GameView;
import com.example.micke.labb2mobil.Activities.View.ViewState;
import com.example.micke.labb2mobil.Model.GameState;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by Micke on 11/26/2017.
 */

public class SaveGame extends AsyncTask<Void,Void,Boolean> {

    private Context context;
    private String name;

    public SaveGame(Context context,String name){
        this.context = context;
        this.name = name;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        FileOutputStream fos = null;
        try {
            fos = context.openFileOutput(name+"GameState", Context.MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(GameState.getGameState());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        fos = null;

        try {
            fos = context.openFileOutput(name+"ViewState", Context.MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(ViewState.getViewState());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        Log.i("APPLICATION","Successfull save");
        return true;
    }

    @Override
    protected void onPostExecute(Boolean isSave) {
        GameActivity.saveGame(isSave);
    }
}
