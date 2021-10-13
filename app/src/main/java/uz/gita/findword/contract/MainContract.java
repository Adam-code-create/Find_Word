package uz.gita.findword.contract;

import uz.gita.findword.questiondata.QuestionData;

public interface MainContract {
    int MAX_ANSWER_COUNT = 6;
    int MAX_VARIANT_COUNT = 14;

    interface View {
        void hideVariant (int index);
        void setAnswer (int index, String text);
        void showAnswerButtonState (int index, boolean state);
        void clearAnswer (int index);
        void setVariant (int index, String text);
        void showVariant (int index);
        void loadQuestion (String text);
        void wrongAnswer();
        void correctAnswer();
        void finishGame();
        void showMessage(String text);
    }
    interface Model {
        int getQuestionCount ();
        QuestionData getQuestionByIndex (int index);
    }

    interface Presenter {
        void clickVariant (int index);
        void clickAnswer (int index);
        int getQuestionIndex();
    }

}
