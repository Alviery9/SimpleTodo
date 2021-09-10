package com.example.simpletodo;

import android.content.ClipData;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    List<String> items;

    Button btnAdd;
    EditText etItem;
    RecyclerView rvItems;
    itemsAdapter itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id. btnAdd);
        etItem = findViewById(R.id. etItem);
        rvItems =findViewById(R.id. rvitems);



        loadItems();


        itemsAdapter.OnLongClickListener onLongClickListener = new itemsAdapter.OnLongClickListener(){
            @Override
            public void onItemLongClicked(int position) {
              items.remove(position);
              itemsAdapter.notifyItemRemoved(position);
              Toast.makeText(getApplicationContext(), "Item was removed", Toast.LENGTH_SHORT).show();
              saveItems();
            }
        };
        itemsAdapter = new itemsAdapter(items, onLongClickListener);
        rvItems.setAdapter(itemsAdapter);
        rvItems.setLayoutManager(new LinearLayoutManager(this));

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             String todoItem = etItem.getText().toString();

             items.add(todoItem);
             itemsAdapter.notifyItemInserted(items.size() -1);
             etItem.setText("");
             Toast.makeText(getApplicationContext(), "Item was added", Toast.LENGTH_SHORT).show();
             saveItems();
            }
        });
    }
    private File getDataFile(){
        return new File(getFilesDir(), "data.txt");
    }
    private void loadItems(){
        try {
            items = new ArrayList<>(FileUtils.readLines(getDataFile(), Charset.defaultCharset()));
        } catch (IOException e) {
            Log.e("MainActivity","Error reading items", e);
            items = new ArrayList<>();
        }
    }
    private void saveItems() {
        try {
            FileUtils.writeLines(getDataFile(), items);
        } catch (IOException e) {
            Log.e("MainActivity","Error writing items", e);
        }
    }
}