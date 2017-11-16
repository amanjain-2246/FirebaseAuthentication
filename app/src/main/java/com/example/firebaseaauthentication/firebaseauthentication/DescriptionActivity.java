package com.example.firebaseaauthentication.firebaseauthentication;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DescriptionActivity extends AppCompatActivity {
TextView e1,e2;
    Toolbar toolbar;
    private FirebaseAuth mAuth= FirebaseAuth.getInstance();
    FirebaseDatabase database= FirebaseDatabase.getInstance();
    DatabaseReference userRef=database.getReference("user");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        e1=(TextView) findViewById(R.id.t1);
        e2=(TextView) findViewById(R.id.t2);
        Intent intent=getIntent();
        String a=intent.getStringExtra("title");
        String b=intent.getStringExtra("desc");
        FirebaseUser user=mAuth.getCurrentUser();
        if(user !=null)
        {
            e1.setText(a);
            e2.setText(b);

        }


        toolbar=(Toolbar)findViewById(R.id.toolbar4);
        setSupportActionBar(toolbar);
        ActionBar ab=getSupportActionBar();
        ab.setTitle("Detailed Tasks");
        ab.setDisplayHomeAsUpEnabled(true);

    }
}
