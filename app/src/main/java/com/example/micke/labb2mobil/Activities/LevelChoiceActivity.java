package com.example.micke.labb2mobil.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.micke.labb2mobil.Activities.View.ViewState;
import com.example.micke.labb2mobil.Model.GameObjects.PosDrawable;
import com.example.micke.labb2mobil.Model.GameState;
import com.example.micke.labb2mobil.R;

public class LevelChoiceActivity extends AppCompatActivity {

    private Button threeButton;
    private Button sixButton;
    private Button nineButton;
    private EditText edit;

    public EditText getEdit() {
        return edit;
    }

    public void setEdit(EditText edit) {
        this.edit = edit;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_choice);
        threeButton = findViewById(R.id.button_three_morris);
        sixButton = findViewById(R.id.button_six_morris);
        nineButton = findViewById(R.id.button_nine_morris);
        edit = findViewById(R.id.editText);
        threeButton.setOnClickListener(new StartGameListener(3,this));
        sixButton.setOnClickListener(new StartGameListener(6,this));
        nineButton.setOnClickListener(new StartGameListener(9,this));
    }

    /**
     * load a new game with game size 3 or 6 or 9 , depends on user choice
     */
    private class StartGameListener implements View.OnClickListener{

        private int gameSize;
        private Context context;
        public StartGameListener(int gameSize, LevelChoiceActivity context){
            this.gameSize = gameSize;
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            Log.i("asda",edit.getText().toString());
            if(edit.getText().toString().equals("") || edit.getText().toString()==null){
                showToast("Name is required");
                return;
            }
            GameState.startNewGame(gameSize, edit.getText().toString());

            ViewState.startNewGame(context,gameSize);
            Intent intent = new Intent(context, GameActivity.class);
            context.startActivity(intent);
        }

        private void showToast(String msg){
            Toast toast = Toast.makeText(context,msg,Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
        }

    }
}
