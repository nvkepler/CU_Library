package com.nvkepler.cu;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//Created by neelv on 22/3/18.

public class StudentInfoActivity extends FragmentsActivity {
    FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studentinfo_activity);
        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        final TextView name1=(TextView)findViewById(R.id.name);
        final TextView roll1=(TextView)findViewById(R.id.roll);
        final TextView email1=(TextView)findViewById(R.id.email);
        final TextView book11=(TextView)findViewById(R.id.book1);
        final TextView book22=(TextView)findViewById(R.id.book2);
        final TextView book33=(TextView)findViewById(R.id.book3);
        final TextView book44=(TextView)findViewById(R.id.book4);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                   String name=(String)dataSnapshot.child(user.getUid()).child("Name").getValue();
                   String roll=(String)dataSnapshot.child(user.getUid()).child("RollNo").getValue();
                   String email=(String)dataSnapshot.child(user.getUid()).child("Email").getValue();
                   if(dataSnapshot.child(user.getUid()).child("Book4").exists()) {
                       String book4=(String)dataSnapshot.child(user.getUid()).child("Book4").getValue();
                       book44.setText(book4);
                   }else{
                       book44.setText("-");
                   }

                if(dataSnapshot.child(user.getUid()).child("Book3").exists()) {
                    String book3=(String)dataSnapshot.child(user.getUid()).child("Book3").getValue();
                    book33.setText(book3);
                }else{
                    book33.setText("-");
                }

                if(dataSnapshot.child(user.getUid()).child("Book2").exists()) {
                    String book2=(String)dataSnapshot.child(user.getUid()).child("Book2").getValue();
                    book22.setText(book2);
                }else{
                    book22.setText("-");
                }

                if(dataSnapshot.child(user.getUid()).child("Book1").exists()) {
                    String book1=(String)dataSnapshot.child(user.getUid()).child("Book1").getValue();
                    book11.setText(book1);
                }else{
                    book11.setText("-");
                }

                name1.setText(name);
                roll1.setText(roll);
                email1.setText(email);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
