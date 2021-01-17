package es.jesuitas.dam.mytodo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton buttonAddTask;
    private RecyclerView recyclerView;

    private List<Task> taskList;
    private TaskAdapter mAdapter;
    private AppDatabase db;

    static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = AppDatabase.getInstance(this);
        taskList = db.taskDao().getAll();
        mAdapter = new TaskAdapter(MainActivity.this, taskList);

        recyclerView.setAdapter(mAdapter);




        buttonAddTask = findViewById(R.id.fab);
        buttonAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,
                        AddTaskActivity.class);
                startActivityForResult(intent,REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            String newTask = data.getStringExtra("task");
            String newDesc = data.getStringExtra("desc");
            Task task = new Task(newTask, newDesc,false);
            db.taskDao().insert(task);
            taskList.add(task);
            mAdapter.notifyDataSetChanged();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_delete_all:
                clearDB();
                return true;
            default:
        }
        return super.onOptionsItemSelected(item);
    }

    private void clearDB() {
        db.taskDao().deleteAll();
        taskList.clear();
        mAdapter.notifyDataSetChanged();
    }

}