package es.jesuitas.dam.mytodo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private List<Task> taskList;
    private LayoutInflater mInflater;

    class TaskViewHolder extends RecyclerView.ViewHolder{

        TextView textViewStatus;
        TextView textViewTask;
        TextView textViewDesc;

        public TaskViewHolder(View itemView) {
            super(itemView);

            textViewStatus = itemView.findViewById(R.id.textViewStatus);
            textViewTask = itemView.findViewById(R.id.textViewTask);
            textViewDesc = itemView.findViewById(R.id.textViewDesc);
        }
    }

    public TaskAdapter(Context mCtx, List<Task> taskList) {
        this.taskList = taskList;
        mInflater = LayoutInflater.from(mCtx);
    }

    @NonNull
    @Override
    public TaskAdapter.TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                         int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_tasks,
                parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        Task t = taskList.get(position);
        holder.textViewTask.setText(t.mTask);
        holder.textViewDesc.setText(t.mDesc);

        if (t.mFinished)
            holder.textViewStatus.setText("Completed");
        else
            holder.textViewStatus.setText("Not Completed");
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }
}
