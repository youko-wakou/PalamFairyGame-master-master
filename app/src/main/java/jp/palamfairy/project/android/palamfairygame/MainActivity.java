package jp.palamfairy.project.android.palamfairygame;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private Intent loginIntent;
    private FirebaseUser userCheckFirebase;
    private Intent petDefaultIntent;
    private FirebaseUser user;
    private FirebaseAuth auth;
    private DatabaseReference databaseReference;
    private DatabaseReference userRef;
    private String userName;
    private String petName;
    private Intent petSettingIntent;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("まいぺっと");

//        auth = FirebaseAuth.getInstance();
//        databaseReference = FirebaseDatabase.getInstance().getReference();
//        userRef = databaseReference.child(Const.userPATH).child(user.getUid());
//        userRef.addChildEventListener(userNameListener);
//        =========================ログインしているか確認==============================================

        userCheckFirebase = FirebaseAuth.getInstance().getCurrentUser();
        if(userCheckFirebase == null){
            loginIntent = new Intent(this,LoginActivity.class);
            startActivity(loginIntent);
        }else{
            petDefaultIntent = new Intent(this,PetDefaultActivity.class);
            startActivity(petDefaultIntent);
        }
//        =============================================================================================
//        ===================================================================================================
    }
//    private ChildEventListener userNameListener = new ChildEventListener() {
//        @Override
//        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//            HashMap username = (HashMap)dataSnapshot.getValue();
//            userName = (String)username.get("userName");
//            petName = (String)username.get("petName");
//        }
//
//        @Override
//        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//        }
//
//        @Override
//        public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//        }
//
//        @Override
//        public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//        }
//
//        @Override
//        public void onCancelled(DatabaseError databaseError) {
//
//        }
//    };
//    private void loginSet(){
//        =========================ログインしているか確認==============================================
//        userCheckFirebase = FirebaseAuth.getInstance().getCurrentUser();
////        if(userCheckFirebase == null){
////            loginIntent = new Intent(this,LoginActivity.class);
////            startActivity(loginIntent);
////        }else{
//            if(userName !=null||petName != null) {
//                petDefaultIntent = new Intent(this, PetDefaultActivity.class);
//                startActivity(petDefaultIntent);
//            }else{
//                petSettingIntent = new Intent(this,UserPetEntryActivity.class);
//                startActivity(petSettingIntent);
//            }
//        }

//        =============================================================================================
//    }

}

