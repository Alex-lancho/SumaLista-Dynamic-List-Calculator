package com.example.ejercicio_propuesto1;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText editTextNumber;
    private Button buttonAdd;
    private ListView listViewNumbers;
    private TextView textViewSum;
    private ArrayList<Integer> numbers;
    private ArrayAdapter<Integer> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNumber = findViewById(R.id.editTextNumber);
        buttonAdd = findViewById(R.id.buttonAdd);
        listViewNumbers = findViewById(R.id.listViewNumbers);
        textViewSum = findViewById(R.id.textViewSum);

        numbers = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, numbers);
        listViewNumbers.setAdapter(adapter);

        buttonAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                addNumber();
            }
        });

        listViewNumbers.setOnItemClickListener((parent, view, position, id) -> {
            removeNumber(position);
        });
    }

    private void addNumber() {
        String input = editTextNumber.getText().toString();
        if (!input.isEmpty()) {
            int number = Integer.parseInt(input);
            numbers.add(number);
            adapter.notifyDataSetChanged();
            updateSum();
            editTextNumber.setText("");
        } else {
            Toast.makeText(this, "Por favor, ingrese un número", Toast.LENGTH_SHORT).show();
        }
    }

    private void removeNumber(int position) {
        numbers.remove(position);
        adapter.notifyDataSetChanged();
        updateSum();
    }

    private void updateSum() {
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        textViewSum.setText("Suma: " + sum);
    }
}