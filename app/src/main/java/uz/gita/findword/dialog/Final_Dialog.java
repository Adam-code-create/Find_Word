package uz.gita.findword.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatButton;
import uz.gita.findword.R;
import uz.gita.findword.UserInterface;


public class Final_Dialog extends AlertDialog {
    private UserInterface listener;
    private int score;

    public Final_Dialog(Context context, int score) {
        super(context);
        this.score = score;
        View view= LayoutInflater.from(context).inflate(R.layout.final_part_dialog,null);
        setView(view);
    }

    public void setListener(UserInterface listener) {
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppCompatButton restart = findViewById(R.id.btn_restart);
        AppCompatButton nextLevel = findViewById(R.id.btn_next_level);
        TextView finalScore = findViewById(R.id.finalScore);
        TextView description = findViewById(R.id.description);
        TextView description2 = findViewById(R.id.description2);
        finalScore.setText(String.valueOf(score));
        if (score >= 140) {
            restart.setVisibility(View.INVISIBLE);
            nextLevel.setVisibility(View.VISIBLE);
            description.setVisibility(View.INVISIBLE);
            description2.setVisibility(View.VISIBLE);
        }
        restart.setOnClickListener( view -> {
            listener.clickRestart();
        });

        nextLevel.setOnClickListener(view -> {
            listener.clickNextLevel();
        });


    }
}
