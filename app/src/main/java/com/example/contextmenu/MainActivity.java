package com.example.contextmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    ArrayList<String>list;
    ArrayAdapter<String>adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv=findViewById(R.id.listView);
        Toolbar toolbar=findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        list=new ArrayList<>();
        for(int i=0;i<10;i++){
            list.add("Item "+i);
        }
        adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,list);
        lv.setAdapter(adapter);
        registerForContextMenu(lv);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.context_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
       AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.del:
                list.remove(info.position);
                adapter.notifyDataSetChanged();

                return true;


            default:
                return super.onContextItemSelected(item);
        }
    }}