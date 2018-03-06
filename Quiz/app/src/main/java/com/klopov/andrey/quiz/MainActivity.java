package com.klopov.andrey.quiz;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final String KEY_INDEX = "current_index";
    private static final String KEY_CORRECT_COUNT = "correct_count";
    private static final int REQUEST_CHEAT = 0;


    private TextView questionTextView;
    private ImageButton trueButton;
    private ImageButton falseButton;
    private ImageButton nextButton;
    private ImageButton prevButton;
    private TextView rightAnswerCount;
    private Button cheatButton;


    private QuestionBank questionBank = new QuestionBank();

    int currentIndex = 0;
    int correct_answers = 0;

    private boolean isCheater;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CHEAT && resultCode == RESULT_OK && data != null) {
            isCheater = CheatActivity.wasAnswerShown(data);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState(Bundle outState) вызван");
        outState.putInt(KEY_INDEX, currentIndex);
        outState.putInt(KEY_CORRECT_COUNT, correct_answers);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "onRestoreInstanceState(Bundle savedInstanceState) вызван");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate()");
        Log.e(TAG, "Ошибка");
        Log.w(TAG, "Внимание");
        Log.d(TAG, "Ищем ошибки");
        Log.v(TAG, "Много слов");
        Log.wtf(TAG, "WTF");
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt(KEY_INDEX);
            correct_answers = savedInstanceState.getInt(KEY_CORRECT_COUNT);
        }

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
        }

        questionTextView = (TextView) findViewById(R.id.question_text_view);
        questionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNextQuestion();
            }
        });

        rightAnswerCount = (TextView) findViewById(R.id.countText);
        rightAnswerCount.setText(String.valueOf(correct_answers));

        trueButton = (ImageButton) findViewById(R.id.true_button);
        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
            }
        });

        falseButton = (ImageButton) findViewById(R.id.false_button);
        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });

        nextButton = (ImageButton) findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNextQuestion();
            }
        });

        prevButton = (ImageButton) findViewById(R.id.prev_button);
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentIndex > 0) {
                    currentIndex = currentIndex - 1;
                } else {
                    currentIndex = questionBank.getQuestionBankCapacity() - 1;
                }
                updateQuestion();
            }
        });

        cheatButton = (Button) findViewById(R.id.cheatButton);
        cheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isTrue = questionBank.getQuestionByIndex(currentIndex).isAnswerIsTrue();
                Intent intent = CheatActivity.getIntent(MainActivity.this, isTrue);
                startActivityForResult(intent, REQUEST_CHEAT);
            }
        });

        updateQuestion();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart() вызван!");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume() вызван!");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause() вызван!");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop() вызван!");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy() вызван!");
    }

    private void showNextQuestion() {
        currentIndex = (currentIndex + 1) % questionBank.getQuestionBankCapacity();
        isCheater = false;
        updateQuestion();
    }

    private void updateQuestion() {
        int question = questionBank.getQuestionByIndex(currentIndex).getQuestionResId();
        questionTextView.setText(question);
        rightAnswerCount.setText(String.valueOf(correct_answers));

    }

    private void checkAnswer(boolean userPressTrue) {
        boolean answerIsTrue = questionBank.getQuestionByIndex(currentIndex).isAnswerIsTrue();
        if (isCheater) {
            Toast.makeText(this, R.string.judgment_toast, Toast.LENGTH_SHORT).show();
        } else if (userPressTrue == answerIsTrue) {
            Toast.makeText(this, R.string.correct, Toast.LENGTH_SHORT).show();
            correct_answers++;
            updateQuestion();
        } else {
            Toast.makeText(this, R.string.incorrect, Toast.LENGTH_SHORT).show();
        }
    }


}