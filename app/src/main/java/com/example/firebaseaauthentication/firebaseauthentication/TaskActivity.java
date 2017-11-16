package com.example.firebaseaauthentication.firebaseauthentication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TaskActivity extends AppCompatActivity {
EditText title,desc;

    Button addtask;
    String usertitle,userdesc,key,uid;
    Toolbar toolbar;
    private FirebaseAuth mAuth= FirebaseAuth.getInstance();
    FirebaseDatabase database= FirebaseDatabase.getInstance();
    DatabaseReference userRef=database.getReference("user");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        addtask=(Button)findViewById(R.id.taskbtn);
        title=(EditText)findViewById(R.id.titletxt);
        desc=(EditText)findViewById(R.id.desctxt);
        toolbar=(Toolbar)findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        ActionBar ab=getSupportActionBar();
        ab.setTitle("Add Tasks");
        FirebaseUser user=mAuth.getCurrentUser();
        /*if(user !=null)
        {
            //String email = user.getEmail();
            //String uid =user.getUid();
            //Toast.makeText(getApplicationContext(),"email : "+email+uid,Toast.LENGTH_LONG).show();

        }
        else
        {
            Toast.makeText(getApplicationContext(),"no user login : ",Toast.LENGTH_LONG).show();

        }*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

            return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.viewtask:
                startActivity(new Intent(TaskActivity.this, TasklistAactivity.class));
                return true;

            case R.id.logout:
                mAuth.signOut();
                startActivity(new Intent(TaskActivity.this, MainActivity.class));
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }




 public void addtask(View view)
 {
     usertitle=title.getText().toString();
     userdesc=desc.getText().toString();
    uid = mAuth.getCurrentUser().getUid();
   key=userRef.push().getKey();
    MyTasks myTasks=new MyTasks(usertitle,userdesc,key);
     userRef.child(uid).child(key).setValue(myTasks).addOnCompleteListener(new OnCompleteListener<Void>() {
         @Override
         public void onComplete(@NonNull Task<Void> task) {
             if (task.isSuccessful()){
                 Toast.makeText(TaskActivity.this, "Added Successfully...", Toast.LENGTH_SHORT).show();
             }
             else{
                 Toast.makeText(TaskActivity.this, "Failed", Toast.LENGTH_SHORT).show();
             }
         }
     });

 }


}
