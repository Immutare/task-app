package com.example.carlosgarcia.task_app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    String tag = "CEGG";
    int intTaskToDo = 0;
    int intTaskDoing = 0;
    int intTaskDone = 0;

    BroadcastReceiver  showTaskCountReceiver = new ShowTaskCountReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter intentFilter = new IntentFilter("com.CEGG.CUSTOM_INTENT.TasksCountReady");
        this.registerReceiver(this.showTaskCountReceiver, intentFilter);
    }

    protected void onStart()
    {
        Log.i(tag, "We are on onStart");
        super.onStart();
    }

    protected void onResume()
    {
        Log.i(tag, "We are on onResume");
        super.onResume();

        TaskDB taskDBInstance = TaskDB.getTaskDB(getApplicationContext());
        DBUtil.DBTaskCount(taskDBInstance, getApplicationContext());
    }

    protected void onPause()
    {
        super.onPause();
        Log.d(tag, "The onPause() event");

        //                                                  //To unregister a receiver of the broadcast
        Log.d(tag, "Unregistrando....");
        this.unregisterReceiver(this.showTaskCountReceiver);
    }

    protected void onStop()
    {
        Log.i(tag, "We are on onStop");
        super.onStop();
    }

    public void onDestroy()
    {
        Log.i(tag, "We are on onDestroy");
        super.onDestroy();
    }

    public void ShowNewTaskForm(View view)
    //                                                      //onClick ButtonNewTask
    {
        //                                                  //To verify the click get
        Log.d("CEGG", "click ButtonNewTask");

        //                                                  //Explicit intent to start
        Intent intent = new Intent(
                getApplicationContext(), NewTaskFormActivity.class);
        startActivity(intent);
    }

    public void ShowTaskListForm(View view)
    //                                                      //onClick ButtonNewTask
    {
        //                                                  //To verify the click get
        Log.d("CEGG", "click ShowTaskListForm");

        //                                                  //Explicit intent to start
        Intent intent = new Intent(
                getApplicationContext(), TaskListActivity.class);
        startActivity(intent);
    }


    //------------------------------------------------------------------------------------------------------------------
    private class ShowTaskCountReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //                                              //When com.LGF.CUSTOM_INTENT.TasksReady occurs


            intTaskToDo = DBUtil.getToDoTaskCount();
            intTaskDone = DBUtil.getDoneTaskCount();
            intTaskDoing = DBUtil.getDoingTaskCount();


            TextView txtTasksToDo = findViewById(R.id.txtTaskToDo);
            txtTasksToDo.setText(intTaskToDo + " Tasks To Do");

            TextView txtTasksDone = findViewById(R.id.txtTaskDone);
            txtTasksDone.setText(intTaskDone + " Tasks Done");

            TextView txtTasksDoing = findViewById(R.id.txtTaskDoing);
            txtTasksDoing.setText(intTaskDoing + " Tasks Doing");
        }
    }
}
