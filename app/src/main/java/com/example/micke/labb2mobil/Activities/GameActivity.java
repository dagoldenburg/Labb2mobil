package com.example.micke.labb2mobil.Activities;

import android.app.Activity;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.micke.labb2mobil.Activities.Controller.Buttons.LoadGameListener;
import com.example.micke.labb2mobil.Activities.View.GameView;
import com.example.micke.labb2mobil.Activities.View.ViewState;
import com.example.micke.labb2mobil.Model.GameObjects.PosDrawable;
import com.example.micke.labb2mobil.Model.GameState;
import com.example.micke.labb2mobil.R;
import com.example.micke.labb2mobil.Tasks.SaveGame;

public class GameActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ConstraintLayout v = (ConstraintLayout) findViewById(R.id.activity_game);

        ViewState.getViewState().onTilt(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.game_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.ng:
                        Log.i("asd", "NEW GAME");
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                        startActivity(intent);
                        return true;
                    case R.id.sg:
                        Log.i("asd", "SAVE GAME");
                        SaveGame saveGame = new SaveGame(new SaveGame.TaskListener() {
                            @Override
                            public void onFinished(Boolean result) {
                                if (result == true) {

                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                                    startActivity(intent);


                                }
                            }
                        }, getApplicationContext(), GameState.getGameState().getGameName());
                        saveGame.execute();
                        return true;
                    default:
                        return false;
                }
            }
        });
        v.addView(new GameView(this));
        toolbar.bringToFront();
    }
}
