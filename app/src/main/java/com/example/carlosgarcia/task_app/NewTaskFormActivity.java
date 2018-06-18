package com.example.carlosgarcia.task_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import com.example.carlosgarcia.task_app.taskTask;

public class NewTaskFormActivity extends AppCompatActivity {

    String strTag = "CEGG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task_form);

        initiateTheSeekbarPercentage();
        addListenerToTheSeekbarPercentage();

        initiateTheSwitchDone();
        addListenerToTheSwitchDone();
    }

    public void ShowReturnMainTask(View view)
    //                                                      //onClick ButtonNewTask
    {
        //                                                  //To verify the click get
        Log.i(strTag, "click ShowReturnMainTask");

        //                                                  //Explicit intent to start
        Intent intent = new Intent(
                getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    public void initiateTheSeekbarPercentage(){
        //                                                  //Conseguimos el objeto de seekbar para interactuar con el
        //                                                  //      mismo
        SeekBar seekbarPercentage = findViewById(R.id.SeekBarPercentage);

        //                                                  //Se establece el porcentaje máximo como 100
        seekbarPercentage.setMax(100);

        int defaultTaskPercentage = 0;

        seekbarPercentage.setProgress(defaultTaskPercentage);

        defaultTaskPercentage = seekbarPercentage.getProgress();

        Log.wtf(strTag, "Progress" + defaultTaskPercentage);

        TextView textViewPercentage = findViewById(R.id.TextViewPercentage);

        String strDefaultTaskPercentage = defaultTaskPercentage + "%";

        textViewPercentage.setText(strDefaultTaskPercentage);
    }
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    public void addListenerToTheSeekbarPercentage(){
        SeekBar seekbarPercentage = findViewById(R.id.SeekBarPercentage);

        seekbarPercentage.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            int progressChangedValue = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                progressChangedValue = progress;

            }

            public void onStartTrackingTouch(SeekBar seekBar) {}

            public void onStopTrackingTouch(SeekBar seekBar) {

                Log.d(strTag, "Percentage changed: " + progressChangedValue);

                Switch switchDone = findViewById(R.id.SwitchDone);

                TextView textViewPercentage = findViewById(R.id.TextViewPercentage);

                if (switchDone.isChecked()){

                    textViewPercentage.setText("100%");

                }
                else {
                    String strTaskPercentage = progressChangedValue + "%";

                    textViewPercentage.setText(strTaskPercentage);
                }
            }
        });
    }
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    public void initiateTheSwitchDone(){
        Switch switchDone = findViewById(R.id.SwitchDone);

        if (switchDone.isChecked()){

            TextView textViewPercentage = findViewById(R.id.TextViewPercentage);

            textViewPercentage.setText("100%");
        }
    }
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    public void addListenerToTheSwitchDone(){
        Switch switchDone = findViewById(R.id.SwitchDone);

        switchDone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                TextView textViewPercentage = findViewById(R.id.TextViewPercentage);

                SeekBar seekbarPercentage = findViewById(R.id.SeekBarPercentage);

                if( //
                        isChecked
                        )
                {
                    textViewPercentage.setText("100%");
                }
                else
                {
                    int TaskPercentage = seekbarPercentage.getProgress();

                    String strTaskPercentage = TaskPercentage + "%";

                    textViewPercentage.setText(strTaskPercentage);
                }
            }
        });
    }


    //------------------------------------------------------------------------------------------------------------------
    public void BackToMainActivity(View view) {
        finish();
    }
    //------------------------------------------------------------------------------------------------------------------

    public void taskTaskCreateTask(View view) {
        EditText editxtShortDesc = findViewById(R.id.EditTextShort);
        String strShortDesc = editxtShortDesc.getText().toString();

        EditText editxtLongDesc = findViewById(R.id.EditTextLong);
        String strLongDesc = editxtLongDesc.getText().toString();

        TextView textViewPercentage = findViewById(R.id.TextViewPercentage);
        int intPercentage = Integer.parseInt(textViewPercentage.getText().toString().substring(0, textViewPercentage.getText().toString().length() - 1));

        /*
        Log.d(strTag, "Short: " + strShortDesc);
        Log.d(strTag, "Long: " + strLongDesc);
        Log.d(strTag, "intPercentage: " + intPercentage);
        */

        taskTask taskNewTask = new taskTask(strShortDesc, strLongDesc, intPercentage);

        TaskDB taskDBInstance = TaskDB.getTaskDB(getApplicationContext());
        DBUtil.DBSaveNewTask(taskDBInstance, taskNewTask);

        System.out.println(taskNewTask);

        // TaskDB.destroyInstance();

        finish();
    }
}
