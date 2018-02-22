package jp.palamfairy.project.android.palamfairygame;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by appu2 on 2018/01/23.
 */

public class UserPetEntryActivity extends AppCompatActivity {
    private Bundle UserprofBundle;
    private ArrayList<String> userProfArray;
    private TranslateAnimation dogJumpTransAnime;
    private ImageView dogJumpAnimeView;
    private MediaPlayer entryBgmPlayer;
    private EditText userNameEdit;
    private EditText petNameEdit;
    private Button okButton;
    private String userName;
    private String petName;
    private RelativeLayout entryMainLayout;
    private FirebaseUser user;
    private FirebaseAuth auth;
    private DatabaseReference userRef;
    private DatabaseReference databasereference;
    private ProgressDialog userEntryProgress;
    private MediaPlayer JumpPlayer;
    private Intent EntryToStartIntent;
    private boolean IsMusicTrue;


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_pet_entry);
        setTitle("スタート");
        IsMusicTrue = true;
//========================ユーザー情報取得======================================================================
        auth= FirebaseAuth.getInstance();
        databasereference = FirebaseDatabase.getInstance().getReference();
        user = auth.getCurrentUser();
        userRef =databasereference.child(Const.userPATH).child(user.getUid());
//        ==================================================================================================
//        ==============レイアウトID読み込み===========================================================================
        dogJumpAnimeView = (ImageView)findViewById(R.id.EntrydogView);
        userNameEdit = (EditText)findViewById(R.id.userName);
        petNameEdit = (EditText)findViewById(R.id.petName);
        okButton = (Button)findViewById(R.id.nextButton);
        entryMainLayout = (RelativeLayout)findViewById(R.id.entryActivityView);
//        ============================================================================================================
//        ======================intentで渡ってきた情報をここで受け取る===================================================
        UserprofBundle = getIntent().getExtras();
        userProfArray = (ArrayList<String>)UserprofBundle.get("userProfArray");
//        ================================================================================================================
//        ===========================ダイアログ設定======================================================================
        userEntryProgress = new ProgressDialog(this);
        userEntryProgress.setMessage("ペットと一緒の生活をはじめましょう");
//        =================================================================================================================
//        ★アニメーション呼び出し★
        dogJumpAnime();
        entryBgm();
//        =======================決定ボタンクリックイベント===========================================================
        okButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                userName = userNameEdit.getText().toString();
                petName = petNameEdit.getText().toString();
                if(userName.length() !=0&& petName.length() !=0){
//                    =====================ペットの名前とユーザー名を保存==============================================
                    Map<String,String> userData = new HashMap<String,String>();
                    userData.put("userName",userName);
                    userData.put("petName",petName);
                    userRef.push().setValue(userData, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                            if(databaseError != null){
                                System.out.println("Data could not be saved"+databaseError.getMessage());
                            }else{
                                System.out.println("Data saved successfully");
                            }
                        }
                    });
                    EntryToStartIntent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(EntryToStartIntent);
                    soundStop();
                    userEntryProgress.show();
//                    ===================================================================================================
                }else if(userName.length()==0){
                    Snackbar.make(entryMainLayout,"ユーザー名を入力してください",Snackbar.LENGTH_LONG).show();
                }else if(petName.length() ==0){
                    Snackbar.make(entryMainLayout,"ペットの名前を入力してください",Snackbar.LENGTH_LONG).show();
                }
            }
        });
//        ===============================================================================================================
//        ===========================================================================================================

    }
//================onCreateここまで=================================================================
//    ===========犬がジャンプするアニメーション==========================================================
    private void dogJumpAnime(){
        dogJumpTransAnime = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_SELF,0,
                TranslateAnimation.RELATIVE_TO_SELF,0,
                TranslateAnimation.RELATIVE_TO_SELF,0,
                TranslateAnimation.RELATIVE_TO_SELF,-1
        );
        dogJumpTransAnime.setDuration(2000);
        dogJumpTransAnime.setRepeatMode(TranslateAnimation.RESTART);
        dogJumpTransAnime.setRepeatCount(TranslateAnimation.INFINITE);
        dogJumpAnimeView.startAnimation(dogJumpTransAnime);
        if(IsMusicTrue) {
            jumpSound();
        }
    }
//    ====================================================================================================
//        ==============================BGMの設定===============================================================
    private void entryBgm(){
        entryBgmPlayer = MediaPlayer.create(this,R.raw.entry);
        entryBgmPlayer.setLooping(true);
        entryBgmPlayer.setVolume(0.3f,0.3f);
//        entryBgmPlayer.start();
    }
//===============================================================================================================
//=========ジャンプサウンド==========================================================================
    private void jumpSound(){
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                JumpPlayer = MediaPlayer.create(getApplicationContext(),R.raw.jump);
//                JumpPlayer.setLooping(true);
                JumpPlayer.start();
                if(IsMusicTrue) {
                    jumpSound();
                }
            }
        },2000);

    }
//    ===========================================================================================
//    ======================インテントプレイヤー停止==============================================
    private void soundStop(){
//        if(JumpPlayer.isPlaying()||entryBgmPlayer.isPlaying()) {
//            JumpPlayer.stop();
            entryBgmPlayer.stop();
            IsMusicTrue = false;
//        }
    }
//    =============================================================================================
//    ===================アプリを閉じるときの音楽の再生・一時停止===========================================
    @Override
    protected void onResume(){
        super.onResume();
        entryBgmPlayer.start();
        IsMusicTrue = true;
        jumpSound();
    }
    @Override
    protected void onPause(){
        super.onPause();
        entryBgmPlayer.pause();
        IsMusicTrue = false;
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        entryBgmPlayer.release();
        entryBgmPlayer = null;
    }
//    ===========================================================================================================
}
