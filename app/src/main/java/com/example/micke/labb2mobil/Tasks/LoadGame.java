package com.example.micke.labb2mobil.Tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

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
            FileInputStream fis = context.openFileInput(name);
            ObjectInputStream ois = new ObjectInputStream(fis);
            //CurrenciesApplicationData.setCurrenciesApplicationData((CurrenciesApplicationData) ois.readObject());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        Log.i("APPLICATION","Successfull open");
        return true;
    }
}
