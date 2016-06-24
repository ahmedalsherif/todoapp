package com.ahmedalsherif.myapplication123;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class MainActivity extends AppCompatActivity {
ArrayList<String> items;
    ArrayList<String> lll;
    ArrayAdapter<String> itemsAdapter;
    ListView lvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lll=readItems(); // <---- Add this line 1
//        itemsAdapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1, items);

        lvItems = (ListView)findViewById(R.id.lvItems);
        items =  new ArrayList<>();
        itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,items);
        lvItems.setAdapter(itemsAdapter);
//        items.add("First item");
//        items.add("second items");


        for (int iii = 0; iii < lll.size(); iii++)
        {
            String value = lll.get(iii);
            items.add(value);
        }

        setupListViewListener();

    }
    // ...onCreate method
 private void onResume(View v)
 {

 }
    public void onedit(View v) {

        int pos = lvItems.getPositionForView(v);
        String old_name =(String) lvItems.getItemAtPosition(pos);
        ////////
        /*


*/

       launchComposeView(old_name,pos+"");
        ////////
    };
    public void onAddItem(View v) {


        EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
        String itemText = etNewItem.getText().toString();
        itemsAdapter.add(itemText);
        etNewItem.setText("");
        writeItems(); // <---- Add this line 2

      //
    }

    // Attaches a long click listener to the listview

    private void setupListViewListener() {/*
        lvItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter,
                                                   View item, int pos, long id) {
                        items.remove(pos);
                        itemsAdapter.notifyDataSetChanged();
                        writeItems(); // <---- Add this line 1

                        return true;
                    }

                });*/

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                onedit(v);

            }
        });
    }

///

    // ...onAddItem method



    private ArrayList readItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            items = new ArrayList<String>(FileUtils.readLines(todoFile));
        } catch (IOException e) {
            items = new ArrayList<String>();
        }/*
        for (int i = 0; i < items.size(); i++) {
            String value = items.get(i);
            ////////

            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
            alertDialog.setTitle("Alert");
            alertDialog.setMessage("Element: " + value);
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
            //////////
        }*/

        return items;
    }

    private void writeItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            FileUtils.writeLines(todoFile, items);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    // ActivityOne.java


    public void launchComposeView(String old_name, String pos) {
        // first parameter is the context, second is the class of the activity to launch
        Intent i = new Intent(MainActivity.this, EditItemActivity.class);
        // put "extras" into the bundle for access in the second activity
        i.putExtra("old_name", old_name);
        i.putExtra("pos", pos);
        // brings up the second activity
        startActivity(i);
    }

}
