package com.klopov.andrey.quiz;

import java.util.ArrayList;

public class QuestionBank {
    private ArrayList<Question> questions;
    // Список вопросов в простом массиве
    public QuestionBank() {
        questions = new ArrayList<>();
        questions.add(new Question(R.string.question_oceans, true));
        questions.add(new Question(R.string.question_mideast, false));
        questions.add(new Question(R.string.question_africa, false));
        questions.add(new Question(R.string.question_america, true));
        questions.add(new Question(R.string.question_asia, true));
    }

    public Question getQuestionByIndex(int index) {
        return questions.get(index);
    }

    public int getQuestionBankCapacity() {
        return questions.size();
    }
}
