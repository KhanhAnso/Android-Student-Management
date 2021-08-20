package com.example.studentmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.studentmanagement.database.database;
import com.example.studentmanagement.model.Subject;

public class ActivityUpdateSubject extends AppCompatActivity {

    EditText edtUpdateTitile,edtUpdateCredit,edtUpdateTime,edtUpdatePlace;
    Button btnUpdatesubject;

    com.example.studentmanagement.database.database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_subject);

        edtUpdateCredit = findViewById(R.id.EditTextUpdateSubjectCredit);
        edtUpdatePlace = findViewById(R.id.EditTextUpdateSubjectPlace);
        edtUpdateTime = findViewById(R.id.EditTextUpdateSubjectTime);
        edtUpdateTitile = findViewById(R.id.EditTextUpdateSubjectTitle);
        btnUpdatesubject = findViewById(R.id.buttonUpdateSubject);

        //Lấy dữ liệu intent
        Intent intent = getIntent();

        int id = intent.getIntExtra("id",0);
        String title = intent.getStringExtra("title");
        int credit = intent.getIntExtra("credit",0);
        String time = intent.getStringExtra("time");
        String place = intent.getStringExtra("place");

        edtUpdateTitile.setText(title);
        edtUpdateTime.setText(time);
        edtUpdatePlace.setText(place);
        edtUpdateCredit.setText(credit+"");

        database = new database(this);

        btnUpdatesubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogUpdate(id);
            }
        });

    }

    private void DialogUpdate(int id) {

        Dialog dialog = new Dialog(this);

        dialog.setContentView(R.layout.dialogupdatesubject);

        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = dialog.findViewById(R.id.buttonYesupdate);
        Button btnNo = dialog.findViewById(R.id.buttonNoupdate);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subjecttitle = edtUpdateTitile.getText().toString().trim();
                String credit = edtUpdateCredit.getText().toString().trim();
                String time = edtUpdateTime.getText().toString().trim();
                String place = edtUpdatePlace.getText().toString().trim();

                if (subjecttitle.equals("") || credit.equals("") || time.equals("") || place.equals("")){
                    Toast.makeText(ActivityUpdateSubject.this,"Did you enter enough information",Toast.LENGTH_SHORT).show();
                }
                else {
                    Subject subject = updatesubject();

                    database.UpdateSubject(subject,id);
                    //update thành công thì qua activity subeject
                    Intent intent = new Intent(ActivityUpdateSubject.this,ActivitySubject.class);
                    startActivity(intent);
                    Toast.makeText(ActivityUpdateSubject.this,"more success",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        //lưu ý cần show dialog
        dialog.show();
    }
    //lưu trữ dữ liệu editext cập nhật
    private Subject updatesubject(){
        String subjecttitle = edtUpdateTitile.getText().toString().trim();
        int credit = Integer.parseInt(edtUpdateCredit.getText().toString().trim());
        String time = edtUpdateTime.getText().toString().trim();
        String place = edtUpdatePlace.getText().toString().trim();

        Subject subject = new Subject(subjecttitle,credit,time,place);
        return subject;
    }
    //ok ok ok
}