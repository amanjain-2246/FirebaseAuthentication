package com.example.firebaseaauthentication.firebaseauthentication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {
EditText email,password;
    Button login,signup;
    private FirebaseAuth mAuth;
    String usereamil,userpass;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        email = (EditText) findViewById(R.id.emailtxt);
        password = (EditText) findViewById(R.id.passwordtxt);
        login = (Button) findViewById(R.id.btnlogin);

        signup = (Button) findViewById(R.id.btnsignup);
        mAuth = FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Signup.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }


 public void signuphere(View view)
 {
     usereamil=email.getText().toString();
     userpass=password.getText().toString();
     if(TextUtils.isEmpty(usereamil)){
         Toast.makeText(getBaseContext(),"Please enter email",Toast.LENGTH_LONG).show();
         return;
     }

     if(TextUtils.isEmpty(userpass)){
         Toast.makeText(getBaseContext(),"Please enter password",Toast.LENGTH_LONG).show();
         return;
     }
     progressDialog.setMessage("Registering Please Wait...");
     progressDialog.show();

     mAuth.createUserWithEmailAndPassword(usereamil,userpass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
         @Override
         public void onComplete(@NonNull Task<AuthResult> task) {
             if(task.isSuccessful())
             {
                 Toast.makeText(getBaseContext(),"Thankyou for Registration",Toast.LENGTH_LONG).show();
             }
             else
             {
                 Toast.makeText(getBaseContext(),"Sorry try again",Toast.LENGTH_LONG).show();
             }
             progressDialog.dismiss();
         }
     }) ;

 }
}
