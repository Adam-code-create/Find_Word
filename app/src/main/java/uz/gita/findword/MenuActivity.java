package uz.gita.findword;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import uz.gita.findword.UI.MainActivity;

public class MenuActivity extends AppCompatActivity {
    private SharedPreferences preferences;
    private TextView playGame;
    private ImageView exit;
    private  TextView level1;
    private  TextView level2;
    private  TextView level3;
    private  TextView level4;
    private  TextView level5;
    private  TextView level6;
    private TextView ratingScore1;
    private TextView ratingScore2;
    private TextView ratingScore3;
    private TextView ratingScore4;
    private TextView ratingScore5;
    private TextView ratingScore6;

    private ImageView rating2;
    private ImageView rating3;
    private ImageView rating4;
    private ImageView rating5;
    private ImageView rating6;


    private ImageView lock2;
    private ImageView lock3;
    private ImageView lock4;
    private ImageView lock5;
    private ImageView lock6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        loadViews();
        preferences = this.getSharedPreferences("myApp", Context.MODE_PRIVATE);



    }

    private void loadViews(){
        playGame = findViewById(R.id.playGame);
        exit = findViewById(R.id.exit);
        level1 = findViewById(R.id.level1);
        level2 = findViewById(R.id.level2);
        level3 = findViewById(R.id.level3);
        level4 = findViewById(R.id.level4);
        level5 = findViewById(R.id.level5);
        level6 = findViewById(R.id.level6);

        ratingScore1 = findViewById(R.id.ratingscore1);
        ratingScore2 = findViewById(R.id.ratingScore2);
        ratingScore3 = findViewById(R.id.ratingScore3);
        ratingScore4 = findViewById(R.id.ratingScore4);
        ratingScore5 = findViewById(R.id.ratingScore5);
        ratingScore6 = findViewById(R.id.ratingScore6);

        rating2 = findViewById(R.id.rating2);
        rating3 = findViewById(R.id.rating3);
        rating4 = findViewById(R.id.rating4);
        rating5 = findViewById(R.id.rating5);
        rating6 = findViewById(R.id.rating6);

        lock2 = findViewById(R.id.lock);
        lock3 = findViewById(R.id.lock3);
        lock4 = findViewById(R.id.lock4);
        lock5 = findViewById(R.id.lock5);
        lock6 = findViewById(R.id.lock6);

    }

    @SuppressLint("SetTextI18n")
    private void loadViewsByClick(){
        setRatingScore();
        int pos = preferences.getInt("pos",0);
        playGame.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("1", pos);
            intent.putExtra("2", -1);
            startActivity(intent);
        });
        exit.setOnClickListener(view -> finish());

            level1.setOnClickListener(view -> {
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("1", pos);
                intent.putExtra("2", 0);
                startActivity(intent);
            });


        if (pos >= 10){
            level2.setBackgroundResource(R.drawable.item_bg);
            rating2.setImageResource(R.drawable.ic_rating);
            lock2.setVisibility(View.GONE);
            level2.setText("Level 2");

            level2.setOnClickListener(view -> {

                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("1", pos);
                intent.putExtra("2", 10);
                startActivity(intent);
            });
        }

        if (pos >= 20){
            level3.setBackgroundResource(R.drawable.item_bg);
            rating3.setImageResource(R.drawable.ic_rating);
            lock3.setVisibility(View.GONE);
            level3.setText("Level 3");
            level3.setOnClickListener(view -> {
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("1", pos);
                intent.putExtra("2", 20);
                startActivity(intent);
            });
        }

        if (pos >= 30){
            level4.setBackgroundResource(R.drawable.item_bg);
            rating4.setImageResource(R.drawable.ic_rating);
            lock4.setVisibility(View.GONE);
            level4.setText("Level 4");
            level4.setOnClickListener(view -> {
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("1", pos);
                intent.putExtra("2", 30);
                startActivity(intent);
            });
        }

        if (pos >= 40){
            level5.setBackgroundResource(R.drawable.item_bg);
            rating5.setImageResource(R.drawable.ic_rating);
            lock5.setVisibility(View.GONE);
            level5.setText("Level 5");
            level5.setOnClickListener(view -> {
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("1", pos);
                intent.putExtra("2", 40);
                startActivity(intent);
            });
        }
        if (pos >= 50){
            level6.setBackgroundResource(R.drawable.item_bg);
            rating6.setImageResource(R.drawable.ic_rating);
            lock6.setVisibility(View.GONE);
            level6.setText("Level 6");
            level6.setOnClickListener(view -> {
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("1", pos);
                intent.putExtra("2", 50);
                startActivity(intent);
            });
        }

    }

    private void setRatingScore(){
        ratingScore1.setText(String.valueOf(preferences.getInt("scoreStar1",0)));
        ratingScore2.setText(String.valueOf(preferences.getInt("scoreStar2",0)));
        ratingScore3.setText(String.valueOf(preferences.getInt("scoreStar3",0)));
        ratingScore4.setText(String.valueOf(preferences.getInt("scoreStar4",0)));
        ratingScore5.setText(String.valueOf(preferences.getInt("scoreStar5",0)));
        ratingScore6.setText(String.valueOf(preferences.getInt("scoreStar6",0)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadViewsByClick();
        TextView questionIndex = findViewById(R.id.questionIndexMenu);
        int index = preferences.getInt("1", 0);
        questionIndex.setText(String.valueOf(index+1));


    }
}