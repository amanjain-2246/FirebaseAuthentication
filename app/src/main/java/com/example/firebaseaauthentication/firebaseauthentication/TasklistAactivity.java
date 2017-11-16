package com.example.firebaseaauthentication.firebaseauthentication;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TasklistAactivity extends AppCompatActivity {
    ListView lv;

    ArrayList<MyTasks> taskArrayList = new ArrayList<>();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference userRef=database.getReference("user");
    Toolbar toolbar;

    FirebaseUser user = mAuth.getCurrentUser();
    String id=user.getUid();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasklist_aactivity);
        lv = (ListView) findViewById(R.id.listview);
        registerForContextMenu(lv);
        toolbar=(Toolbar)findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        ActionBar ab=getSupportActionBar();
        ab.setTitle("Your Tasks");
        ab.setDisplayHomeAsUpEnabled(true);
        final MyTaskAdapter myTaskAdapter = new MyTaskAdapter(TasklistAactivity.this, taskArrayList);
        lv.setAdapter(myTaskAdapter);
        FirebaseUser user=mAuth.getCurrentUser();


        if(user!=null) {

            userRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    taskArrayList.clear();
                    for (DataSnapshot postSnapshot : dataSnapshot.child(id).getChildren()) {
                        MyTasks myTasks = postSnapshot.getValue(MyTasks.class);
                        taskArrayList.add(myTasks);
                    }
                    MyTaskAdapter myTaskAdapter = new MyTaskAdapter(TasklistAactivity.this, taskArrayList);
                    lv.setAdapter(myTaskAdapter);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }








   lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
       @Override
       public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

           taskArrayList.get(position).getId();
           Intent intent = new Intent(TasklistAactivity.this,DescriptionActivity.class);
           intent.putExtra("title",taskArrayList.get(position).getTitle());
           intent.putExtra("desc",taskArrayList.get(position).getDescription());
           startActivity(intent);

/*           AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getBaseContext());
           dialogBuilder.setMessage(taskArrayList.get(position).getTitle());
           dialogBuilder.setMessage(taskArrayList.get(position).getDescription());
           AlertDialog b = dialogBuilder.create();
           b.show();
*/

       }
   });




    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Select The Action");
        menu.add(0, v.getId(), 0, "Edit");
        menu.add(0, v.getId(), 1, "Delete");
    }



    @Override
    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final int index = info.position;
       // String pos=String.valueOf(index);

        if(item.getTitle()=="Edit"){
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
            LayoutInflater inflater = LayoutInflater.from(this);
            final View dialogView = inflater.inflate(R.layout.update_dialog, null);
            dialogBuilder.setView(dialogView);
            final EditText updatetitle = (EditText) dialogView.findViewById(R.id.updatetitle);
            final EditText updatedesc = (EditText) dialogView.findViewById(R.id.updatedesc);
            final Button buttonUpdate = (Button) dialogView.findViewById(R.id.update);
            final AlertDialog b = dialogBuilder.create();
            b.show();
           final String taskid=taskArrayList.get(index).getId();

            buttonUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String title = updatetitle.getText().toString().trim();
                    String desc= updatedesc.getText().toString().trim();
                    if (!TextUtils.isEmpty(title)) {
                        MyTasks myTasks = new MyTasks(title,desc,taskid);
                        userRef.child(id).child(taskid).setValue(myTasks);
                        b.dismiss();
                    }
                }
            });

        }
        else if(item.getTitle()=="Delete"){
        userRef.child(id).child(taskArrayList.get(index).getId()).removeValue();
            Toast.makeText(getApplicationContext(),"position :"+index,Toast.LENGTH_LONG).show();
        }else{
            return false;
        }
        return true;
    }



}