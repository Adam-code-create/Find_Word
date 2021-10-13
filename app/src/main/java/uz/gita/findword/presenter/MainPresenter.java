package uz.gita.findword.presenter;


import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

import uz.gita.findword.contract.MainContract;
import uz.gita.findword.questiondata.QuestionData;

public class MainPresenter implements MainContract.Presenter {
    private MainContract.Model model;
    private MainContract.View view;
    private int questionIndex;
    private int sizeAnswer;
    private int questionPosion;
    private ArrayList<String> answerSpaces = new ArrayList<>(MainContract.MAX_ANSWER_COUNT);
    private ArrayList<Boolean> variantSpaces = new ArrayList<>(MainContract.MAX_VARIANT_COUNT);

    public MainPresenter(MainContract.Model model, MainContract.View view, int lastIndex, int levPosition) {
        this.model = model;
        this.view = view;


        if (levPosition == -1){
            levPosition = lastIndex;
            this.questionIndex = lastIndex + 1;
        } else  {
            this.questionIndex = levPosition + 1;
        }
        questionPosion = levPosition / 10 + 1;
        loadQuestion();
    }

    private void loadQuestion () {
        if (questionIndex <= questionPosion * 10) {
            QuestionData data = model.getQuestionByIndex(questionIndex);
            String answer = data.getAnswer();
            String variant = data.getVariant();
            String question = data.getQuestion();
            sizeAnswer = answer.length();
            view.loadQuestion(question);
            answerSpaces.clear();

            for (int i = 0; i < MainContract.MAX_ANSWER_COUNT; i++) {
                boolean state = i < answer.length();
                view.showAnswerButtonState(i, state);
                view.clearAnswer(i);
                answerSpaces.add(null);
            }
            for (int i = 0; i < MainContract.MAX_VARIANT_COUNT; i++) {
                view.setVariant(i, String.valueOf(variant.charAt(i)));
                view.showVariant(i);
                variantSpaces.add(true);
            }
        }
    }

    private int getEmptyAnswerBtn (){
        for (int i = 0; i < sizeAnswer; i++) {
            if (answerSpaces.get(i) == null) return i;
        }
        return -1;
    }

    private int getVariantIndexByAnswer (int index){
        String answerBtn = answerSpaces.get(index);
        QuestionData data = model.getQuestionByIndex(questionIndex);
        String variant = data.getVariant();

        for (int i = 0; i < variant.length(); i++) {
            String variantBtn = String.valueOf(variant.charAt(i));
            if (!variantSpaces.get(i) && variantBtn.equals(answerBtn)) return i;
        }
        return -1;
    }

    private void checkAnswer (){
        QuestionData data = model.getQuestionByIndex(questionIndex);
        String answer = data.getAnswer();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < answer.length(); i++) {
            String answerBtn = answerSpaces.get(i);
            if ( answerBtn != null){
                builder.append(answerBtn);
            }
        }
        String userAnswer = builder.toString();
        if (userAnswer.length()!=answer.length()) return;
        if (userAnswer.equals(answer)){
            view.correctAnswer();
            questionIndex++;
            if (isFinished()){
                view.finishGame();
            } else {
                loadQuestion();
            }

        } else {
            view.wrongAnswer();
        }

    }

    private boolean isFinished(){
        return questionIndex  == 1 + (questionPosion * 10);
    }


    @Override
    public void clickVariant(int index) {
        int emptyAnswerBtn = getEmptyAnswerBtn();
        if (emptyAnswerBtn == -1) return;
        QuestionData data = model.getQuestionByIndex(questionIndex);
        String variantBtnText = String.valueOf(data.getVariant().charAt(index));
        view.setAnswer(emptyAnswerBtn, variantBtnText);
        view.hideVariant(index);
        answerSpaces.set(emptyAnswerBtn, variantBtnText);
        variantSpaces.set(index, false);
        checkAnswer();
    }

    @Override
    public void clickAnswer(int index) {
        view.clearAnswer(index);
      int emptyVariantBtn = getVariantIndexByAnswer(index);
      if (emptyVariantBtn==-1) {
          view.showMessage("Bosh tugmani bosdingiz");
         return;
      }else {
          view.showVariant(emptyVariantBtn);
      }

        variantSpaces.set(emptyVariantBtn, true);
        answerSpaces.set(index, null);
    }

    @Override
    public int getQuestionIndex() {
        return questionIndex;
    }
}
