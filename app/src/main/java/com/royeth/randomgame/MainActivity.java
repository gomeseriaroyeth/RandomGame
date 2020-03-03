package com.royeth.randomgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

//    Widgets
    TextView firstNumber, secondNumber, resultText, correctTextScore, wrongTextScore, operatorSignChar;
    EditText answerOfUser;
    Button generate, submit;

//    Class of methods
    Process process;
    CountDownTimer countDownTimer;

//    Variables
    float ans;
    int correctAnswer = 0;
    int wrongAnswer = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Initialization of class process
        process = new Process();

//        Linking widgets
        correctTextScore = findViewById(R.id.correctScore);
        wrongTextScore = findViewById(R.id.wrongScore);
        firstNumber = findViewById(R.id.firstRandomNumber);
        secondNumber = findViewById(R.id.secondRandomNumber);
        operatorSignChar = findViewById(R.id.operatorSignage);
        resultText = findViewById(R.id.result);
        answerOfUser = findViewById(R.id.usersGuess);
        generate = findViewById(R.id.generateButton);
        submit = findViewById(R.id.submitButton);

//        Buttons
        generate.setOnClickListener(this);
        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {


        if (view == generate) {

            answerOfUser.setEnabled(true);
            submit.setEnabled(true);

            float frst = process.firstNumber();
            float scnd = process.secondNumber();
            int operatorSignNumber = process.operator();
            char operatorSign = process.operatorChar(operatorSignNumber);

            firstNumber.setText(Float.toString(frst));
            secondNumber.setText(Float.toString(scnd));
            operatorSignChar.setText(Character.toString(operatorSign));

            ans = process.perform(frst, scnd, operatorSignNumber);
            clearField();

            countDownTimer = new CountDownTimer(10000, 1000) {

                public void onTick(long millisUntilFinished) {
                    resultText.setText("seconds remaining: " + millisUntilFinished / 1000);
                    generate.setEnabled(false);
                }

                public void onFinish() {
                    resultText.setText("Time is up!\nThe answer is " + ans);
                    generate.setEnabled(true);
                    answerOfUser.setEnabled(false);
                    submit.setEnabled(false);
                }

            }.start();

        } else if (view == submit) {

            if (answerOfUser.getText().toString().isEmpty()){
                Toast.makeText(getApplicationContext(), "Try to guess", Toast.LENGTH_SHORT).show();
            } else {
                float answer = Float.parseFloat(answerOfUser.getText().toString());

                if (ans == answer) {
                    countDownTimer.cancel();
                    submit.setEnabled(false);
                    answerOfUser.setEnabled(false);
                    resultText.setText("You're Correct!");
                    correctAnswer = correctAnswer + 1;
                    correctTextScore.setText("" + correctAnswer);
                    generate.setEnabled(true);
                } else if (ans != answer) {
                    countDownTimer.cancel();
                    submit.setEnabled(false);
                    answerOfUser.setEnabled(false);
                    resultText.setText("You're wrong!\nThe correct answer is: "+ ans);
                    wrongAnswer = wrongAnswer + 1;
                    wrongTextScore.setText("" + wrongAnswer);
                    generate.setEnabled(true);
                }
            }
        }
    }

    public void clearField(){
        answerOfUser.setText("");
        resultText.setText("");
    }
}
