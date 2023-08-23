package com.example.borgerapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.borgerapp.Database.Database_helper;

public class MainActivity2 extends AppCompatActivity {

    Database_helper db;
    EditText id,name;
    Button add, view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        id = findViewById(R.id.id_txt);
        name = findViewById(R.id.name_txt);

        add= findViewById(R.id.button_add);
        view= findViewById((R.id.button_view));
        db = new Database_helper(this);

        ADD();
        VIEW();

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }
    private void ADD() {
        add.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
                builder.setMessage("Do you want to add this?").setCancelable(false).
                        setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                boolean isInserted = db.insertdata(id.getText().toString(), name.getText().toString());
                                if (isInserted){
                                    Toast.makeText(MainActivity2.this, "Data inserted", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(MainActivity2.this, "Data not inserted", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener(){

                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                dialogInterface.cancel();

                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.setTitle("conform");
                alertDialog.show();
            }
        });

    }
    private void VIEW() {
        view.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Cursor res = db.ViewAll();
                if (res.getCount() == 0){
                    ShowMessage("ERROR","No DATA FOUND");
                    return;
                }
                StringBuffer buffer =new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("ID:" +res.getString(0)+"\n");
                    buffer.append("NAME:" + res.getString(1)+"\n\n");
                }
                ShowMessage("Details", buffer.toString());
            }
        });

    }
    public void ShowMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}