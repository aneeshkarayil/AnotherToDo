package com.aadhyaapps.anothertodo.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.aadhyaapps.anothertodo.MainActivity;
import com.aadhyaapps.anothertodo.R;
import com.aadhyaapps.anothertodo.model.Task;
import com.aadhyaapps.anothertodo.utils.TaskUtils;

import java.util.List;

/**
 * Created by Maya on 1/14/2017.
 */
public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

   private List<Task> taskList;
    private Context context;

    public TaskAdapter(Context context, List<Task> taskList)
    {
        this.context = context;
        this.taskList = taskList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.taks_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Task task = taskList.get(position);

        TextView textView = holder.mItemTextView;
        Button progressButton = holder.mItemButton;
        CheckBox checkBox = holder.mItemCheckbox;

        textView.setText(task.getDescription());

        //get the children for the current task
        List<Task> childTasks = TaskUtils.getChildTasks(TaskUtils.generateTasks(), task.getTaskId());

        if (childTasks.size() == 0)
        {
            progressButton.setVisibility(View.INVISIBLE);
        }
        else
        {
            progressButton.setVisibility(View.VISIBLE);
            progressButton.setText(childTasks.size()+"");
        }

        if (task.isDone())
        {
            checkBox.setChecked(true);
        }
        else
        {
            checkBox.setChecked(false);
        }
    }

    public void swapData(List<Task> newData)
    {
        this.taskList.clear();
        this.taskList.addAll(newData);
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return this.taskList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public Button mItemButton;
        public CheckBox mItemCheckbox;
        public TextView mItemTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            mItemButton = (Button)itemView.findViewById(R.id.item_progress);
            mItemCheckbox = (CheckBox)itemView.findViewById(R.id.item_check_box);
            mItemTextView = (TextView)itemView.findViewById(R.id.item_description);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition(); // gets item position
            if (position != RecyclerView.NO_POSITION) {
                Task task = taskList.get(position);
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra(MainActivity.PARENT_UUID, task.getTaskId());
                ((Activity)context).startActivity(intent);
            }
        }
    }
}
