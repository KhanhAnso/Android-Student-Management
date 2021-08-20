package com.example.studentmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.studentmanagement.database.database;
import com.example.studentmanagement.model.Student;

public class ActivityUpdateStudent extends AppCompatActivity {

    EditText editTextUpdateName, editTextUpdateCode, editTextUpdateBirthday;
    RadioButton rdMale,rdFemale;
    Button btnUpdateStudent;
    database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);

        editTextUpdateBirthday = findViewById(R.id.EditTextStudentBirthdayUpdate);
        editTextUpdateCode = findViewById(R.id.EditTextStudentCodeUpdate);
        editTextUpdateName = findViewById(R.id.EditTextStudentNameUpdate);

        rdFemale = findViewById(R.id.radiobuttonFeMaleUpdate);
        rdMale = findViewById(R.id.radiobuttonMaleUpdate);
        btnUpdateStudent = findViewById(R.id.buttonUpdateStudent);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id",0);
        String name = intent.getStringExtra("name");
        String sex = intent.getStringExtra("sex");
        String code = intent.getStringExtra("code");
        String birthday = intent.getStringExtra("birthday");
        int id_subject = intent.getIntExtra("id_subject",0);

        //Gán giá trị lên editText và radiobuton
        editTextUpdateName.setText(name);
        editTextUpdateCode.setText(code);
        editTextUpdateBirthday.setText(birthday);

        if (sex.equals("Male")){
            rdMale.setChecked(true);
            rdFemale.setChecked(false);
        }
        else{
            rdFemale.setChecked(true);
            rdMale.setChecked(false);
        }

        database = new database(this);

        btnUpdateStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogUpdate(id,id_subject);
            }
        });



    }

    private void DialogUpdate(int id,int id_subject) {
        Dialog dialog = new Dialog(this);

        dialog.setContentView(R.layout.dialogupdatestudent);

        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = dialog.findViewById(R.id.buttonYesupdateStudent);
        Button btnNo = dialog.findViewById(R.id.buttonNoupdateStudent);
        //nếu đồng ý update
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextUpdateName.getText().toString().trim();
                String code = editTextUpdateCode.getText().toString().trim();
                String birthday = editTextUpdateBirthday.getText().toString().trim();

                Student student = createstudent();

                if (name.equals("") || code.equals("") || birthday.equals("")){
                    Toast.makeText(ActivityUpdateStudent.this,"Did not enter enough information",Toast.LENGTH_SHORT).show();
                }
                else {
                    //update
                    database.UpdateStudent(student,id);

                    //Chuyển qua activity student và cập nhật lại danh sách sinh viên
                    Intent intent = new Intent(ActivityUpdateStudent.this,ActivityStudent.class);
                    //gửi id của subject
                    intent.putExtra("id_subject",id_subject);
                    startActivity(intent);
                    Toast.makeText(ActivityUpdateStudent.this,"more success",Toast.LENGTH_SHORT).show();

                }

            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        dialog.show();
    }

    //create student
    private Student createstudent(){
        String name = editTextUpdateName.getText().toString().trim();
        String code = editTextUpdateCode.getText().toString().trim();
        String birthday = editTextUpdateBirthday.getText().toString().trim();
        String sex = "";
        if (rdMale.isChecked()){
            sex="Male";
        }
        else {
            sex="Female";
        }

        Student student = new Student(name,sex,code,birthday);
        return student;
    }
}