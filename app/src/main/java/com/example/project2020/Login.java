package com.example.project2020;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    private static final String TAG ="Login" ;
    FirebaseDatabase database;
    Player user1;
    DatabaseReference PlayerInfo;
    ImageButton RegisterButton,LoginButton;
    EditText Username,Password;
    Player player1;
    Intent s ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        s= new Intent(getApplicationContext(),StartGame.class);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        database = FirebaseDatabase.getInstance();
        PlayerInfo= database.getReference("PlayersInfo");
        RegisterButton = findViewById(R.id.RegisterButton);
        LoginButton = findViewById(R.id.BtnLogin);
        Username= findViewById(R.id.username);
        Password= findViewById(R.id.password);
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
         //       System.out.println(Username.getText().toString()+ "  b  "+Password.getText().toString());
              //  Player player = new Player(Username.getText().toString(),Password.getText().toString(),"a",0,0,0,0,0);
               // System.out.println("this is  "+player.getUsername() );
               signInCheck(Username.getText().toString(),Password.getText().toString());

            }
        });
        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent s = new Intent(getApplicationContext(),Register.class);
                startActivity(s);
            }
        });
    }

    private void signInCheck(final String Username,final String Password) {
       PlayerInfo.addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               if(dataSnapshot.child(Username).exists()) {
                   if (!Username.isEmpty()) {
                         player1 = dataSnapshot.child(Username).getValue(Player.class);
                       if(player1.getPassword().equals(Password)){
                           //  numberOfGames=0;
                           //        logestGame=0;
                           //        MostGoldEarnedInSingleGame=0;
                           //        longestCombo=0;
                           //        currentGold=0;
                           s.putExtra("password",player1.getPassword());
                           s.putExtra("email",player1.getEmail());
                               s.putExtra("username",player1.getUsername());
                            s.putExtra("gamenum",player1.getNumberOfGames());
                           s.putExtra("longestgame",player1.getLogestGame());
                           s.putExtra("mostgoldinasinglegame",player1.getMostGoldEarnedInSingleGame());
                           s.putExtra("longestcombo",player1.getLongestCombo());
                           s.putExtra("currentgold",player1.getCurrentGold());
                           s.putExtra("highscore",player1.getHighScore());
                           s.putExtra("BK",player1.isBK());
                           s.putExtra("SE",player1.isSE());
                           s.putExtra("login",true);
                           startActivity(s);}
                       else{
                           Toast.makeText(Login.this,"WrongPassword",Toast.LENGTH_LONG).show();
                       }
                   }
               }
           }
           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) { }
       });
    }
}
