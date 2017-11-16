package com.example.firebaseaauthentication.firebaseauthentication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amanj on 7/21/2017.
 */

public class MyTaskAdapter extends ArrayAdapter<MyTasks> {
    ArrayList<MyTasks> taskArrayList;
    Context context;
       public MyTaskAdapter(Context context, ArrayList<MyTasks> taskArrayList) {
        super(context, R.layout.one_row, taskArrayList);
        this.context = context;
        this.taskArrayList = taskArrayList;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.one_row, parent, false);
        }
        TextView title = (TextView) row.findViewById(R.id.textView2);
        TextView desc = (TextView) row.findViewById(R.id.textView3);
        MyTasks myTasks = taskArrayList.get(position);
        title.setText(myTasks.getTitle());
        desc.setText(myTasks.getDescription());
        return row;
    }



}
