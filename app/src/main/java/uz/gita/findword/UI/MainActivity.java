package uz.gita.findword.UI;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;


import uz.gita.findword.MenuActivity;
import uz.gita.findword.R;
import uz.gita.findword.UserInterface;
import uz.gita.findword.contract.MainContract;
import uz.gita.findword.dialog.Final_Dialog;
import uz.gita.findword.presenter.MainPresenter;
import uz.gita.findword.repository.MainRepository;

public class MainActivity extends AppCompatActivity implements MainContract.View {
    private MainContract.Presenter presenter;
    private ImageView imageQuestion;
    private TextView score;
    private TextView questionIndexMain;
    private int scoreStars = 100;
    private int wrongAnswer;
    private SharedPreferences preferences;
    private ArrayList<Button> answers;
    private ArrayList<Button> variants;
    private ArrayList<ImageView> stars;
    private Final_Dialog dialog;
    private int posLevel;
    private int posQuestions;
    private MediaPlayer mediaPlayer1;
    private MediaPlayer mediaPlayer2;
    private MediaPlayer mediaPlayer3;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadViews();
        mediaPlayer1 = MediaPlayer.create(MainActivity.this, R.raw.wrong);
        mediaPlayer2 = MediaPlayer.create(MainActivity.this, R.raw.correct);
        mediaPlayer3 = MediaPlayer.create(MainActivity.this, R.raw.congratulations);

        preferences = this.getSharedPreferences("myApp", Context.MODE_PRIVATE);
        posQuestions = getIntent().getExtras().getInt("1",0);
        posLevel = getIntent().getExtras().getInt("2",0);
        int pos = preferences.getInt("1",0);
        int score_stars= preferences.getInt("score",100);

        scoreStars=score_stars;
        dialog = new  Final_Dialog(MainActivity.this, scoreStars);
        presenter = new MainPresenter(new MainRepository(), this,pos , posLevel );

        if (posLevel == -1){
            questionIndexMain.setText(String.valueOf(pos + 1));
            score.setText(String.valueOf(score_stars));
            wrongAnswer = preferences.getInt("wrongAnswer",0);
            changeStarsStatus();
        } else {
            questionIndexMain.setText(String.valueOf(posLevel + 1));
            scoreStars = 100;
            score.setText(String.valueOf(100));

        }

    }

    private void loadViews (){
        imageQuestion = findViewById(R.id.image_question);
        answers = findButtonById(0, R.id.answer_container,this::clickAnswer);
        variants = findButtonById(0, R.id.variant_container1, this::clickVariant);
        variants.addAll(findButtonById(variants.size(),R.id.variant_container2, this::clickVariant));
        ImageView back = findViewById(R.id.backMain);
        back.setOnClickListener(view -> {
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
            finish();
        });
        questionIndexMain = findViewById(R.id.questionIndex);
        score = findViewById(R.id.score);
        stars = findStarsById(R.id.stars);

    }
    private void restart(){
        int n = ((presenter.getQuestionIndex()-1) / 10) * 10;
        preferences.edit().putInt("1",n).apply();
        questionIndexMain.setText(String.valueOf(posLevel+1));
        preferences.edit().putInt("score",100).apply();
        score.setText(String.valueOf(100));
        finish();
    }
    private void changeStarsStatus () {
        for (int i = 0; i < wrongAnswer; i++) {
            int index = 5- (wrongAnswer-i);
            stars.get(index).setImageResource(R.drawable.ic_star_2);
            if (wrongAnswer == 5){
                restart();
            }
        }
    }
    private void setWrongAnswerColor (){
        for (int i = 0; i < answers.size(); i++) {
            findViewById(R.id.answer_container).setBackgroundResource(R.drawable.wrong_answer_bg);
            answers.get(i).setBackgroundResource(R.drawable.wrong_answer_btn);
        }
    }

    private ArrayList<ImageView> findStarsById (int groupID){
        ViewGroup group = findViewById(groupID);
        ArrayList<ImageView> stars = new ArrayList<>();
        for (int i = 0; i < group.getChildCount(); i++) {
            ImageView image = (ImageView) group.getChildAt(i);
            stars.add(image);
        }
        return stars;
    }

    private ArrayList<Button> findButtonById (int startIndex, int groupID, View.OnClickListener onClickListener){
        ViewGroup group = findViewById(groupID);
        ArrayList<Button> buttons = new ArrayList<>();
        for (int i = 0; i < group.getChildCount(); i++) {
            Button button = (Button) group.getChildAt(i);
            buttons.add(button);
            button.setOnClickListener(onClickListener);
            button.setTag(i + startIndex);
        }
        return buttons;
    }

    private void clickAnswer (View view){
        int index = (int) view.getTag();
        presenter.clickAnswer(index);
    }

    private void clickVariant (View view){
        int index = (int) view.getTag();
        presenter.clickVariant(index);
    }

    private void changeColor (){
        for (int i = 0; i < answers.size(); i++) {
            findViewById(R.id.answer_container).setBackgroundResource(R.drawable.answer_bg);
            answers.get(i).setBackgroundResource(R.drawable.answer_btn);
        }
    }

    @SuppressLint("ResourceType")
    @Override
    public void hideVariant(int index) {
        variants.get(index).setVisibility(View.INVISIBLE);

    }

    @Override
    public void setAnswer(int index, String text) {
        answers.get(index).setText(text);
    }

    @Override
    public void showAnswerButtonState(int index, boolean state) {
        answers.get(index).setVisibility(state ? View.VISIBLE : View.GONE);
    }

    @Override
    public void clearAnswer(int index) {
        answers.get(index).setText("");
    }

    @Override
    public void setVariant(int index, String text) {
        variants.get(index).setText(text);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void showVariant(int index) {
        variants.get(index).setVisibility(View.VISIBLE);
        changeColor();
    }
    @Override
    public void loadQuestion(String text) {
        int id = getResources().getIdentifier(text,"drawable", getPackageName());
        imageQuestion.setImageResource(id);
    }


    public void wrongAnswer() {
        mediaPlayer1.start();
        wrongAnswer++;
        setWrongAnswerColor ();
        changeStarsStatus ();
        preferences.edit().putInt("wrongAnswer",wrongAnswer).apply();

    }

    @Override
    public void correctAnswer() {
        mediaPlayer2.start();
        scoreStars = scoreStars +(5-wrongAnswer);
        preferences.edit().putInt("score",scoreStars).apply();
        score.setText(String.valueOf(scoreStars));
        preferences.edit().putInt("1",presenter.getQuestionIndex()).apply();
        questionIndexMain.setText(String.valueOf(presenter.getQuestionIndex()+1));
    }

    @Override
    public void finishGame() {
        mediaPlayer3.start();
        dialog = new  Final_Dialog(MainActivity.this, scoreStars);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setListener(new UserInterface() {
            @Override
            public void clickRestart() {
                restart();
            }
            @Override
            public void clickNextLevel() {

                int pos = presenter.getQuestionIndex();
                if (pos == 60){
                    preferences.edit().putInt("1",1).apply();
                    preferences.edit().putInt("score",100).apply();
                }
                if (posQuestions < pos){
                    preferences.edit().putInt("pos",pos-1).apply();
                }
                preferences.edit().putInt("1",pos-1).apply();
                preferences.edit().putInt("score",100).apply();

                if ((pos / 10)  == 1){
                    preferences.edit().putInt("scoreStar1",scoreStars).apply();
                } else if ((pos / 10) == 2){
                    preferences.edit().putInt("scoreStar2",scoreStars).apply();
                }else if ((pos / 10) == 3){
                    preferences.edit().putInt("scoreStar3",scoreStars).apply();
                }else if ((pos / 10)  == 4){
                    preferences.edit().putInt("scoreStar4",scoreStars).apply();
                }else if ((pos / 10)  == 5){
                    preferences.edit().putInt("scoreStar5",scoreStars).apply();
                }else preferences.edit().putInt("scoreStar6",scoreStars).apply();

                finish();
            }
        });
        dialog.show();

    }

    @Override
    public void showMessage(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}