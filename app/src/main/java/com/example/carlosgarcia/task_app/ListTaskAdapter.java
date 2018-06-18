package com.example.carlosgarcia.task_app;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ListTaskAdapter extends Adapter<ListTaskAdapter.TaskHolder> {
    private  List<taskTask> listOfTasks;

    public ListTaskAdapter(List<taskTask> listOfTasks) {
        this.listOfTasks = listOfTasks;
    }
    //-----------------------------------------------------------------------------------------------------------------
    @Override
    public ListTaskAdapter.TaskHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.single_task_item, parent, false);

        TaskHolder taskHolder = new TaskHolder(view);
        return taskHolder;
    }
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    public class TaskHolder extends ViewHolder
    {
        TextView textView;

        public TaskHolder(View view)
        {
            super(view);

            this.textView = (TextView) itemView.findViewById(R.id.taskItem);
        }
    }
    //-----------------------------------------------------------------------------------------------------------------
    @Override
    public void onBindViewHolder(ListTaskAdapter.TaskHolder holder, int position) {
        taskTask task = listOfTasks.get(position);
        String strTask = task.getStrShortDesc() + "    " + String.valueOf(task.getIntPercentage()) + "%";

        holder.textView.setText(strTask);
    }

    @Override
    public int getItemCount() {
        return this.listOfTasks.size();
    }
}
