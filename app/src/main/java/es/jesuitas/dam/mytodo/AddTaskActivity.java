package es.jesuitas.dam.mytodo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddTaskActivity extends AppCompatActivity {

    private EditText editTextTask;
    private EditText editTextDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        editTextTask = findViewById(R.id.editTextTask);
        editTextDesc = findViewById(R.id.editTextDesc);

        findViewById(R.id.button_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveTask();
            }
        });
    }

    private void saveTask() {
        final String sTask = editTextTask.getText().toString();
        final String sDesc = editTextDesc.getText().toString();

        if (sTask.isEmpty() || sDesc.isEmpty()){
            displayToast("fill out all fields");
            return;
        }

        Intent returnIntent = new Intent();
        returnIntent.putExtra("task",sTask);
        returnIntent.putExtra("desc",sDesc);
        setResult(RESULT_OK,returnIntent);
        finish();
    }

    private void displayToast(String s) {
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }
}