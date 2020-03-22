package com.example.project2020;


import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;

public class Register extends Activity {
    FirebaseDatabase database;
    DatabaseReference PlayerInfo;
    ImageButton RegisterButton,LoginButton;
    EditText Username,Password,Email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        database = FirebaseDatabase.getInstance();
        PlayerInfo= database.getReference("PlayersInfo");
        RegisterButton = findViewById(R.id.RegisterButtttton);
        LoginButton = (ImageButton) findViewById(R.id.LoginButton);
        Username= findViewById(R.id.username);
        Password= findViewById(R.id.password);
        Email =findViewById(R.id.Email);
        System.out.println("hasfdad "+2000/99f);
        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Player player = new Player(Username.getText().toString(),Password.getText().toString(),Email.getText().toString());
                PlayerInfo.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(player.getUsername()).exists()){
                            Toast.makeText(Register.this, "Username already exists!", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            if(player.getEmail().equals(""))
                                Toast.makeText(Register.this, "Email is empty!", Toast.LENGTH_SHORT).show();
                            if(!player.getEmail().equals("")){
                                Toast.makeText(Register.this, "Registration complete!", Toast.LENGTH_SHORT).show();
                                PlayerInfo.child(player.getUsername()).setValue(player);
                                Intent s = new Intent(getApplicationContext(),StartGame.class);
                                s.putExtra("login",false);
                                s.putExtra("password",player.getPassword());
                                s.putExtra("email",player.getEmail());
                                s.putExtra("username",player.getUsername());
                                startActivity(s);
                            }

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Z = new Intent(getApplicationContext(),Login.class);
                startActivity(Z);
            }
        });
    }
}









