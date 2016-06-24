package com.ahmedalsherif.myapplication123;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class EditItemActivity extends AppCompatActivity {
int pos2=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        String old_name = getIntent().getStringExtra("old_name");
        String pos_ = getIntent().getStringExtra("pos");
        pos2=Integer.parseInt(pos_);
        EditText etNewItem = (EditText) findViewById(R.id.editText);
        etNewItem.setText(old_name);

        ////////
        /*
        AlertDialog alertDialog = new AlertDialog.Builder(EditItemActivity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("==>"+old_name);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
*/
        ////////
    }
    // ActivityTwo.java

    // ActivityTwo.java (subactivity) can access any extras passed in
    ArrayList<String> items;

    public void onSubmit(View v) {
        fun("edit");
    }


    public void onDel(View v) {
        fun("delete");
    }

    public void fun(String fff) {

        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            items = new ArrayList<String>(FileUtils.readLines(todoFile));
        } catch (IOException e) {
            items = new ArrayList<String>();
        }
        for (int i = items.size()-1; i >=0 ; i--) {
            String value = items.get(i);
            ////////
String note="";
if(pos2==i) {
    EditText etNewItem = (EditText) findViewById(R.id.editText);
    String itemText = etNewItem.getText().toString();

if(fff=="edit"){
items.set(i,itemText);note=value+ " has been changed to "+itemText;}
else if(fff=="delete"){
    items.remove(i);note=value+ " has been removed ";}

    AlertDialog alertDialog = new AlertDialog.Builder(EditItemActivity.this).create();
    alertDialog.setTitle("Information!");
    alertDialog.setMessage( note);
    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
            new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
    alertDialog.show();
}
            //////////
        }
        writeItems();

    }


    private void writeItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            FileUtils.writeLines(todoFile, items);
        } catch (IOException e) {
            e.printStackTrace();
        }

      //  this.finish();
    }



    public void back(View v) {

        Intent i = new Intent(EditItemActivity.this ,MainActivity.class);

        startActivity(i);

        this.finish();
    }

}
