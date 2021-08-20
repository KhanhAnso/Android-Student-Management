package com.example.studentmanagement.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.studentmanagement.ActivityStudent;
import com.example.studentmanagement.R;
import com.example.studentmanagement.model.Student;

import java.util.ArrayList;

public class adapterstudent extends BaseAdapter {

    private ActivityStudent context;
    private ArrayList<Student> ArrayListStudent;

    public adapterstudent(ActivityStudent context, ArrayList<Student> arrayListStudent) {
        this.context = context;
        ArrayListStudent = arrayListStudent;
    }

    @Override
    public int getCount() {
        return ArrayListStudent.size();
    }

    @Override
    public Object getItem(int i) {
        return ArrayListStudent.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(R.layout.liststudent,null);

        TextView txtName = view.findViewById(R.id.TextViewStudentName);
        TextView txtCode = view.findViewById(R.id.TextViewStudentCode);

        ImageButton imgbtnDelete = view.findViewById(R.id.studentdelete);
        ImageButton imgbtnupdate = view.findViewById(R.id.studentupdate);
        ImageButton imgInformation = view.findViewById(R.id.studentinformation);

        Student student = ArrayListStudent.get(i);

        txtName.setText(student.getStudent_name());
        txtCode.setText(student.getStudent_code());

        int id = student.getId_student();

        //click delete
        imgbtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.delete(id);
            }
        });
        //click update
        imgbtnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.update(id);
            }
        });
        //click infor
        imgInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.information(id);
            }
        });

        return view;
    }
    //ok
}
