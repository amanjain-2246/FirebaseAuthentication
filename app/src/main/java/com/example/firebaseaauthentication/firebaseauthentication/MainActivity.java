package com.example.firebaseaauthentication.firebaseauthentication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuAdapter;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.R.attr.password;

public class MainActivity extends AppCompatActivity {
Button login,signup;
    private static final String TAG="onAuth";
    EditText email,pass;
    String useremail,userpass;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;
       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login=(Button)findViewById(R.id.login);
        signup=(Button)findViewById(R.id.signup);
        email=(EditText)findViewById(R.id.emailtxt);
        pass=(EditText)findViewById(R.id.passwordtxt);

        mAuth = FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Signup.class);
                startActivity(intent);
            }
        });

     /*   FirebaseUser user=mAuth.getCurrentUser();
        if(user !=null)
        {

            String email = user.getEmail();
            Toast.makeText(getApplicationContext(),"email : "+email,Toast.LENGTH_LONG).show();
            //Log.d(TAG,"onAuth:sign_in:"+user.getUid());
        }
        else
        {

            Toast.makeText(getApplicationContext(),"no user login : ",Toast.LENGTH_LONG).show();
            Log.d(TAG,"onAuth:sign_out:");
        }
*/
    }
















    public void loginhere(View view)
    {
       useremail=email.getText().toString();
        userpass=pass.getText().toString();
        if(TextUtils.isEmpty(useremail)){
            Toast.makeText(getBaseContext(),"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(userpass)){
            Toast.makeText(getBaseContext(),"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }
        progressDialog.setMessage("Login Please wait...");
        progressDialog.show();

        mAuth.signInWithEmailAndPassword(useremail,userpass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            if (userpass.length() < 6) {
                                pass.setError("Password length should be 8 character");
                            }
                            else
                            {
                               Intent intent=new Intent(MainActivity.this,TaskActivity.class);
                                startActivity(intent);
                               // Toast.makeText(MainActivity.this, "login successful..."+useremail, Toast.LENGTH_SHORT).show();
                            }


                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "login denied...", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }
                });

    }





public void sendmail(View view)
{
    startActivity(new Intent(this,ResetPassword.class));
}







}
