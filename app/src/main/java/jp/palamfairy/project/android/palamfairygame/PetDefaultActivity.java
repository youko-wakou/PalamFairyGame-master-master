package jp.palamfairy.project.android.palamfairygame;

import android.app.AlertDialog;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.MobileAds;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by appu2 on 2018/01/23.
 */

public class PetDefaultActivity extends AppCompatActivity {
    private AdView mAdView;
    private MediaPlayer mBgm;
    private MediaPlayer mOnara;
    private ImageView petImage;
    private MediaPlayer OnigiriPlay;
    private Button foodItem;
    private Button handItem;
    private Button cleanItem;
    private AnimationDrawable dogSmileAnime;
    private AlphaAnimation cleanGoodsAlpha;
    private boolean showItem;
    private boolean wantShow;
    private ToileRoop toileRoop;
    private MediaPlayer untiplayer;
    private ImageView cleanUntiA;
    private ImageView cleanUntiB;
    private ImageView cleanUntiC;
    private ImageView cleanUntiD;
    private AnimationDrawable HandAnimeDrawable;
    private TranslateAnimation petMoveRight;
    //    private TranslateAnimation OnigiriTrans;
    private AlphaAnimation OnigiriAlfa;
    private ImageView petFaceAnimeView;
    //    private LinearLayout layoutClean;
    //    private RelativeLayout mParentLayout;
//    private ViewTreeObserver.OnGlobalLayoutListener mGlobalLayoutListener;
    private Button toileA;
    private Button toileB;
    private Button toileC;
    private Button toileD;
    private int mCount;
    private int count;
    private int removeCount;
    private ArrayList<Integer> ToileRoopList;
    private ImageView OnigiriView;
    private RelativeLayout relativelayout;
    private ImageView toileImg;
    private ImageView petNadeView;
    private ImageView handAnimeView;
    private MediaPlayer petNaderuPlayer;
    private ImageView commentBackView;
    private TextView commentText;
    private String[] talkText;
    private int TalkCount;
    private Random randomTalk;
    private int SelectTalk;
    private String talkString;
    private Random randOutTalk;
    private int randOutCount;
    private AlphaAnimation commentAlphaAnimation;
    private MediaPlayer selectPlayer;
    private MediaPlayer commentPlayer;
    private MediaPlayer menuOpenPlayer;
    private FirebaseUser user;
    private FirebaseAuth auth;
    private DatabaseReference userRef;
    private DatabaseReference databasereference;
    private String petName;
    private String userName;
    private Toolbar MenuToolbar;
    private Intent logoutIntent;
    private ProgressDialog userLogoutDialog;
    private int levelHave;
    private int levelAdd;
    private  DatabaseReference levelRef;
    private String levelAddString;
    private String levelHaveString;
    private DatabaseReference levelInfoRef;
    private TextView levelTextView;
    private ImageView levelbarView;
    private ImageView levelBoxView;
    private ImageView levelShowView1;
    private ImageView levelShowView2;
    private ImageView levelShowView3;
    private ImageView levelShowView4;
    private ImageView levelShowView5;
    private ImageView levelShowView6;
    private ImageView levelShowView7;
    private ImageView levelShowView8;
    private ImageView levelShowView9;
    private ImageView levelShowView10;
    private ImageView petIMG;
    private Button transItemButton;
    private boolean transIsOk;
    private AlphaAnimation levelUPAlpha;
    private ImageView levelUPImage;
    private MediaPlayer levelUPplayer;
    private ImageView plusVewImage;
    private AlphaAnimation PlusAnimeAlpha;
    private MediaPlayer PlusLevelPlayer;
    private MediaPlayer TransPlayer;
    private Button transReturnButton;
    private AlertDialog.Builder levelTenDialog;
    private boolean IsMusicPlay;
    //    Handler mhandler= new Handler();
    private int random;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pet_default_activity);
//        koukoku
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

//        トイレのビュー取得
        toileA = (Button)findViewById(R.id.toileA);
        toileB = (Button)findViewById(R.id.toileB);
        toileC = (Button)findViewById(R.id.toileC);
        toileD = (Button)findViewById(R.id.toileD);
        //        トイレの表示
        toileA.setVisibility(View.INVISIBLE);
        toileB.setVisibility(View.INVISIBLE);
        toileC.setVisibility(View.INVISIBLE);
        toileD.setVisibility(View.INVISIBLE);
//        MenuToolbar = (Toolbar)findViewById(R.id.toolbar);
//        setSupportActionBar(MenuToolbar);
//        ==========================表示名取得=================================================
        auth = FirebaseAuth.getInstance();
        databasereference = FirebaseDatabase.getInstance().getReference();
        user = auth.getCurrentUser();
        userRef = databasereference.child(Const.userPATH).child(user.getUid());
        userRef.addChildEventListener(userInfoListener);
//============================================================================================
//        =======================レベル取得==================================================================
        levelRef = databasereference.child(Const.levelupPATH).child(user.getUid()).child("levelResult");
        levelRef.addChildEventListener(levelEventListener);

        levelInfoRef = databasereference.child(Const.levelupPATH).child(user.getUid()).child("levelBase");
        levelInfoRef.addChildEventListener(levelInfoEventListener);
//        =================================================================================================
// ==============================プログレスダイアログ作成========================================================
        userLogoutDialog = new ProgressDialog(this);
        userLogoutDialog.setMessage("ログアウトしています…");
//====================== ================================================================================================
//======================掃除道具==================================================
        cleanUntiA = (ImageView)findViewById(R.id.cleanGoodsA);
        cleanUntiB = (ImageView)findViewById(R.id.cleanGoodsB);
        cleanUntiC = (ImageView)findViewById(R.id.cleanGoodsC);
        cleanUntiD = (ImageView)findViewById(R.id.cleanGoodsD);
        cleanUntiA.setVisibility(View.INVISIBLE);
        cleanUntiB.setVisibility(View.INVISIBLE);
        cleanUntiC.setVisibility(View.INVISIBLE);
        cleanUntiD.setVisibility(View.INVISIBLE);
//============================================================================
        count = 0;
        IsMusicPlay = true;
        toileRoop = new ToileRoop();
//==================レベルビュー===============================================
        transIsOk = false;
        levelTextView = (TextView)findViewById(R.id.levelText);
        levelbarView = (ImageView)findViewById(R.id.levelbar);
        levelBoxView = (ImageView)findViewById(R.id.level_box);
        levelUPImage = (ImageView)findViewById(R.id.levelUP);
        levelUPImage.setVisibility(View.INVISIBLE);
        plusVewImage = (ImageView)findViewById(R.id.plus);
        plusVewImage.setVisibility(View.INVISIBLE);
        transReturnButton = (Button)findViewById(R.id.transItemBt);
        transReturnButton.setVisibility(View.INVISIBLE);
        levelHave = 1;
        levelTextView.setText("1");


        levelShowView1 = (ImageView)findViewById(R.id.level_amount1);
        levelShowView2 = (ImageView)findViewById(R.id.level_amount2);
        levelShowView3 = (ImageView)findViewById(R.id.level_amount3);
        levelShowView4 = (ImageView)findViewById(R.id.level_amount4);
        levelShowView5 = (ImageView)findViewById(R.id.level_amount5);
        levelShowView6 = (ImageView)findViewById(R.id.level_amount6);
        levelShowView7 = (ImageView)findViewById(R.id.level_amount7);
        levelShowView8 = (ImageView)findViewById(R.id.level_amount8);
        levelShowView9 = (ImageView)findViewById(R.id.level_amount9);
        levelShowView10 = (ImageView)findViewById(R.id.level_amount10);

        levelShowView1.setVisibility(View.INVISIBLE);
        levelShowView2.setVisibility(View.INVISIBLE);
        levelShowView3.setVisibility(View.INVISIBLE);
        levelShowView4.setVisibility(View.INVISIBLE);
        levelShowView5.setVisibility(View.INVISIBLE);
        levelShowView6.setVisibility(View.INVISIBLE);
        levelShowView7.setVisibility(View.INVISIBLE);
        levelShowView8.setVisibility(View.INVISIBLE);
        levelShowView9.setVisibility(View.INVISIBLE);
        levelShowView10.setVisibility(View.INVISIBLE);
//        ======================================================================
        foodItem = (Button)findViewById(R.id.foodItem);
        handItem = (Button)findViewById(R.id.handItem);
        cleanItem = (Button)findViewById(R.id.cleanItem);
        transItemButton = (Button)findViewById(R.id.transItem);

        transItemButton.setVisibility(View.INVISIBLE);
        foodItem.setVisibility(View.INVISIBLE);
        handItem.setVisibility(View.INVISIBLE);
        cleanItem.setVisibility(View.INVISIBLE);
        setShowItem(false);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        relativelayout = (RelativeLayout)findViewById(R.id.petcontainer);
//        setContentView(relativelayout);
//        layout = new LinearLayout(getApplicationContext());
//      ~~~~~~~~~~~~~~~~~~~~~~~~~~トイレ掃除クリックイベント~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        cleanItem.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                selectSound();
                toileA.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        toileA.setVisibility(View.INVISIBLE);
                        cleanToile(cleanUntiA);
                        setroopCount();
                        levelPlus();
                    }
                });
                toileB.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        toileB.setVisibility(View.INVISIBLE);
                        cleanToile(cleanUntiB);
                        setroopCount();
                        levelPlus();
                    }
                });
                toileC.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        toileC.setVisibility(View.INVISIBLE);
                        cleanToile(cleanUntiC);
                        setroopCount();
                        levelPlus();
                    }
                });
                toileD.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        toileD.setVisibility(View.INVISIBLE);
                        cleanToile(cleanUntiD);
                        setroopCount();
                        levelPlus();
                    }
                });
                setShowItem(false);
                foodItem.setVisibility(View.INVISIBLE);
                handItem.setVisibility(View.INVISIBLE);
                cleanItem.setVisibility(View.INVISIBLE);
                transItemButton.setVisibility(View.INVISIBLE);
                transReturnButton.setVisibility(View.INVISIBLE);
            }
        });
//        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//        ======================なでなでクリックイベント===================================================================
        petNadeView = (ImageView)findViewById(R.id.petHandSmile);

        petNadeView.setVisibility(View.INVISIBLE);
        handAnimeView = (ImageView)findViewById(R.id.handSlide);
        handAnimeView.setVisibility(View.INVISIBLE);
        handItem.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(transIsOk){
                    petNadeView.setImageResource(R.drawable.hituzi_smile);
                }else{
                    petNadeView.setImageResource(R.drawable.pet_smile);
                }
                selectSound();
                dogDefaultDelete();
                naderuAnime();
                levelPlus();

                setShowItem(false);
                foodItem.setVisibility(View.INVISIBLE);
                handItem.setVisibility(View.INVISIBLE);
                cleanItem.setVisibility(View.INVISIBLE);
                transItemButton.setVisibility(View.INVISIBLE);
                transReturnButton.setVisibility(View.INVISIBLE);
            }
        });
//        ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
//        ==========返信クリックイベント====================================================================================
        transReturnButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                petImage.setImageResource(R.drawable.pet);
                petDefaultAnime();
                transIsOk = false;

                petDefaultAnime();
                transSound();
                setShowItem(false);
                foodItem.setVisibility(View.INVISIBLE);
                handItem.setVisibility(View.INVISIBLE);
                cleanItem.setVisibility(View.INVISIBLE);
                transItemButton.setVisibility(View.INVISIBLE);
                transReturnButton.setVisibility(View.INVISIBLE);
            }
        });
        transItemButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(transIsOk){
                    transIsOk = false;
                    petImage.setImageResource(R.drawable.pet);
                    petDefaultAnime();
                }else {
                    petImage.setImageResource(R.drawable.hituzi);
                    petDefaultAnime();
                    transIsOk = true;
                }
                petDefaultAnime();
                transSound();
                setShowItem(false);
                foodItem.setVisibility(View.INVISIBLE);
                handItem.setVisibility(View.INVISIBLE);
                cleanItem.setVisibility(View.INVISIBLE);
                transItemButton.setVisibility(View.INVISIBLE);
                transReturnButton.setVisibility(View.INVISIBLE);
            }
        });
//        ===================================================================================================================
//       ================================= ごはんアイテムクリックイベント==============================================
        OnigiriView = (ImageView)findViewById(R.id.OnigiriView);
        OnigiriView.setVisibility(View.INVISIBLE);
        foodItem.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                selectSound();
                OnigiriAlfa = new AlphaAnimation(1,0);
                OnigiriAlfa.setDuration(4000);
                OnigiriView.startAnimation(OnigiriAlfa);
//                petImage.setVisibility(View.INVISIBLE);
                dogSmile();
                levelPlus();
                setShowItem(false);
                foodItem.setVisibility(View.INVISIBLE);
                handItem.setVisibility(View.INVISIBLE);
                cleanItem.setVisibility(View.INVISIBLE);
                transItemButton.setVisibility(View.INVISIBLE);
                transReturnButton.setVisibility(View.INVISIBLE);

            }
        });
//        =================================================================================================================
//        ===============メニューオープンボタン=================================================
        Button fab = (Button) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuOpenSound();
                wantShow = getShowItem();
                if(wantShow) {
                    foodItem.setVisibility(View.INVISIBLE);
                    handItem.setVisibility(View.INVISIBLE);
                    cleanItem.setVisibility(View.INVISIBLE);
                    if(levelHave >=10) {
                        transItemButton.setVisibility(View.INVISIBLE);
                        transReturnButton.setVisibility(View.INVISIBLE);
                    }
                    setShowItem(false);
                }else{
                    foodItem.setVisibility(View.VISIBLE);
                    handItem.setVisibility(View.VISIBLE);
                    cleanItem.setVisibility(View.VISIBLE);
                    if(levelHave >=10) {
                        if(transIsOk){
                            transReturnButton.setVisibility(View.VISIBLE);
                            transItemButton.setVisibility(View.INVISIBLE);
                        }else {
                            transReturnButton.setVisibility(View.INVISIBLE);
                            transItemButton.setVisibility(View.VISIBLE);
                        }
                    }
                    setShowItem(true);
                }
            }
        });
                MobileAds.initialize(getApplicationContext(), "ca-app-pub-8822482186754971/7851195160");
    }
    //    ==========================================================================================================
//        ==========================表示名取得リスナーonCreateで呼び出し=================================================
    private ChildEventListener userInfoListener = new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            HashMap getNameMap = (HashMap)dataSnapshot.getValue();
            petName = (String)getNameMap.get("petName");
            userName = (String)getNameMap.get("userName");
            commentArrayCall();
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
//    ===============================================================================================================
    private void commentArrayCall(){
//        =========================おしゃべり配列設定================================================
        commentBackView = (ImageView)findViewById(R.id.commentBG);
        commentText = (TextView)findViewById(R.id.commentTEX);
        commentBackView.setVisibility(View.INVISIBLE);
        commentText.setVisibility(View.INVISIBLE);
//        参照変数にしゃべる内容を入れておく
        talkText = new String[]{
                "今日もいっしょにあそぼうね！",
                String.format("%s をなでなでして～～",petName),
                "おはよう！今日はどんなことがあったかな？" ,
                "何か楽しいことあった？？",
                String.format("%s だいすき＞＜",userName),
                "お昼寝すると気持ちいいよ",
                "ごはんがたべたい、ごはんがたべたい",
                String.format("いやなことがあったら %s のカオを見て元気だして",petName),
                String.format("%s がウンチをしたらキレイキレイにしてね！",petName)
        };
        CommentOut();
//        ====================================================================================
    }
//    ================R.menu.logout_menu表示==========================================================================
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
         int id=item.getItemId();
         if(id == R.id.logout_item){
             if(user !=null) {
                 auth.signOut();
                 mediaPlayerStop();
                 Snackbar.make(relativelayout,"ログアウトしました",Snackbar.LENGTH_LONG).show();
                 userLogoutDialog.show();
                 logoutIntent = new Intent(this, LoginActivity.class);
                 startActivity(logoutIntent);
             }else{
                 Snackbar.make(relativelayout,"ログインしていません",Snackbar.LENGTH_LONG).show();
             }
             return true;
         }
         return super.onOptionsItemSelected(item);
    }
//    ==========================================================================================================================
//    ==================================レベルデータベース===============================================================================
    private ChildEventListener levelEventListener = new ChildEventListener() {
    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//        HashMap levelMap = (HashMap)dataSnapshot.getValue();
//        String levelNOW = (String)levelMap.get("levelNow");
        String levelNOW = (String)dataSnapshot.getValue();
        setlevelTake(Integer.parseInt(levelNOW));
//        if(levelMap.get("levelMore")!=null) {
//            String levelMORE = (String) levelMap.get("levelMore");
//            levelAdd = Integer.parseInt(levelMORE);
//        }else if(levelMap.get("levelNow") != null){
//            String levelNOW = (String)levelMap.get("levelNow");
//            levelHave = Integer.parseInt(levelNOW);
//        }else if(levelMap.get("levelMore")==null){
//            levelAdd = 0;
//        }else if(levelMap.get("levelNow")==null){
//            levelHave = 1;
//        }
    }

    @Override
    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onChildRemoved(DataSnapshot dataSnapshot) {

    }

    @Override
    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
};

    private ChildEventListener levelInfoEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//            HashMap levelInfoMap = (HashMap)dataSnapshot.getValue();
//            String levelMORE = (String)levelInfoMap.get("levelMore");
            String levelMORE = (String)dataSnapshot.getValue();
            setlevelTakeOut(Integer.parseInt(levelMORE));
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
//    ===================================================================================================================================
    private void setlevelTake(int Have){
        if(Have ==0){
            levelHave = 1;
        }else {
            levelHave = Have;
        }
        String levelHaveString = String.valueOf(levelHave);
            levelTextView.setText(levelHaveString);
//        if(levelHave >=10){
//            setImagePet(true);
//        }else{
//            setImagePet(false);
//        }
//        levelTextView.setText(levelHave);
        setImagePet();
    }
    private  void setlevelTakeOut(int Add){
        levelAdd = Add;
        levelViewShow();
    }
//=================レベルアップ（現在レベル１０までしか上がりません）======================================================================
//    private void Levelmanagement() {
//        HashMap<String,String> levelHaveMap = new HashMap<String,String>();
//        if(levelAdd <=10) {
//            levelHave += 1;
//        }
//        levelHaveString = String.valueOf(levelHave);
//        levelHaveMap.put("levelNow",levelAddString);
//        levelRef.setValue(levelHaveMap, new DatabaseReference.CompletionListener() {
//            @Override
//            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
//                if(databaseError != null){
//                    System.out.println("Data could not be saved"+ databaseError.getMessage());
//                }else{
//                    System.out.println("Data saved successfully");
//                }
//            }
//        });
//    }
//        ============================================================================================
//    ============================どうぶつ画像入れ替え=====================================================================
    private void setImagePet(){
            petImage.setImageResource(R.drawable.pet);
    }
//    ===============================================================================================================================
//    ====================レベル加算（１０を超えたらまた０からやり直す）================================================================================
    private void levelPlus(){
        HashMap<String,String> levelAddMap = new HashMap<String,String>();
        HashMap<String,String> levelInfoMap = new HashMap<String, String>();
        if(levelAdd<=9) {
            levelAdd += 1;
            levelViewShow();
            String levelHaveString = String.valueOf(levelHave);
            levelInfoMap.put("levelNow",levelHaveString);
            levelAddAnime();
        }else if(levelAdd == 10){
            levelAdd = 1;
            levelViewShow();
//            if(levelHave <=10) {
                levelHave += 1;
                if(levelHave == 10){
                    levelTenDialog = new AlertDialog.Builder(this);
                    levelTenDialog.setTitle("レベル１０に到達");
                    levelTenDialog.setMessage("メニュー→「へんしん」が使えるようになりました");
                    levelTenDialog.setPositiveButton("Ok",null);
                    levelTenDialog.show();
                }
                levelInfoMap.put("levelNow", String.valueOf(levelHave));
                levelTextView.setText(String.valueOf(levelHave));
                levelUPAnime();
//            Levelmanagement();
//            }
        }
        levelAddString = String.valueOf(levelAdd);
        levelAddMap.put("levelMore",levelAddString);
        levelInfoRef.setValue(levelAddMap, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if(databaseError != null){
                    System.out.println("Data could not be saved"+ databaseError.getMessage());
                }else{
                    System.out.println("Data saved successfully");
                }
            }
        });
        levelRef.setValue(levelInfoMap, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if(databaseError != null){
                    System.out.println("Data could not be saved"+databaseError.getMessage());
                }else{
                    System.out.println("Data saved successfully");
                }
            }
        });
    }
//    =============================================================================================================
//    =======================レベル表示・非表示======================================================================
    private void levelViewShow(){
        if(levelAdd ==1){
            levelShowView1.setVisibility(View.VISIBLE);
//============================================他の表示を切っておく===========================================================
            levelShowView2.setVisibility(View.INVISIBLE);
            levelShowView3.setVisibility(View.INVISIBLE);
            levelShowView4.setVisibility(View.INVISIBLE);
            levelShowView5.setVisibility(View.INVISIBLE);
            levelShowView6.setVisibility(View.INVISIBLE);
            levelShowView7.setVisibility(View.INVISIBLE);
            levelShowView8.setVisibility(View.INVISIBLE);
            levelShowView9.setVisibility(View.INVISIBLE);
            levelShowView10.setVisibility(View.INVISIBLE);
// ===========================================================================================================================
        }else if(levelAdd==2){
            levelShowView1.setVisibility(View.VISIBLE);
            levelShowView2.setVisibility(View.VISIBLE);
        }else if(levelAdd==3){
            levelShowView1.setVisibility(View.VISIBLE);
            levelShowView2.setVisibility(View.VISIBLE);
            levelShowView3.setVisibility(View.VISIBLE);
        }else if(levelAdd==4){
            levelShowView1.setVisibility(View.VISIBLE);
            levelShowView2.setVisibility(View.VISIBLE);
            levelShowView3.setVisibility(View.VISIBLE);
            levelShowView4.setVisibility(View.VISIBLE);
        }else if(levelAdd==5){
            levelShowView1.setVisibility(View.VISIBLE);
            levelShowView2.setVisibility(View.VISIBLE);
            levelShowView3.setVisibility(View.VISIBLE);
            levelShowView4.setVisibility(View.VISIBLE);
            levelShowView5.setVisibility(View.VISIBLE);
        }else if(levelAdd==6){
            levelShowView1.setVisibility(View.VISIBLE);
            levelShowView2.setVisibility(View.VISIBLE);
            levelShowView3.setVisibility(View.VISIBLE);
            levelShowView4.setVisibility(View.VISIBLE);
            levelShowView5.setVisibility(View.VISIBLE);
            levelShowView6.setVisibility(View.VISIBLE);
        }else if(levelAdd==7){
            levelShowView1.setVisibility(View.VISIBLE);
            levelShowView2.setVisibility(View.VISIBLE);
            levelShowView3.setVisibility(View.VISIBLE);
            levelShowView4.setVisibility(View.VISIBLE);
            levelShowView5.setVisibility(View.VISIBLE);
            levelShowView6.setVisibility(View.VISIBLE);
            levelShowView7.setVisibility(View.VISIBLE);
        }else if(levelAdd==8){
            levelShowView1.setVisibility(View.VISIBLE);
            levelShowView2.setVisibility(View.VISIBLE);
            levelShowView3.setVisibility(View.VISIBLE);
            levelShowView4.setVisibility(View.VISIBLE);
            levelShowView5.setVisibility(View.VISIBLE);
            levelShowView6.setVisibility(View.VISIBLE);
            levelShowView7.setVisibility(View.VISIBLE);
            levelShowView8.setVisibility(View.VISIBLE);
        }else if(levelAdd==9){
            levelShowView1.setVisibility(View.VISIBLE);
            levelShowView2.setVisibility(View.VISIBLE);
            levelShowView3.setVisibility(View.VISIBLE);
            levelShowView4.setVisibility(View.VISIBLE);
            levelShowView5.setVisibility(View.VISIBLE);
            levelShowView6.setVisibility(View.VISIBLE);
            levelShowView7.setVisibility(View.VISIBLE);
            levelShowView8.setVisibility(View.VISIBLE);
            levelShowView9.setVisibility(View.VISIBLE);
        }else if(levelAdd==10){
            levelShowView1.setVisibility(View.VISIBLE);
            levelShowView2.setVisibility(View.VISIBLE);
            levelShowView3.setVisibility(View.VISIBLE);
            levelShowView4.setVisibility(View.VISIBLE);
            levelShowView5.setVisibility(View.VISIBLE);
            levelShowView6.setVisibility(View.VISIBLE);
            levelShowView7.setVisibility(View.VISIBLE);
            levelShowView8.setVisibility(View.VISIBLE);
            levelShowView9.setVisibility(View.VISIBLE);
            levelShowView10.setVisibility(View.VISIBLE);
        }
    }
//    ================================================================================================================
//    ==============ウンチを何個削除した確認、４つ消したらトイレ呼び出し========================================
    private void setroopCount(){
//        mCount = count;
//        if(mCount!=0) {
//            removeCount = getroopCount();
            mCount =ToileRoopList.size();
            mCount = mCount -1;

            if(mCount>=1) {
                ToileRoopList.remove(mCount);
            }else if(mCount ==0){
                ToileRoopList.remove(mCount);
                toile();
            }
//        }
    }
//    ==========================================================================================================
    private int getroopCount(){
        return mCount;
    }
//    ===================なでなでアニメーション＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
    private void naderuAnime(){
        petNadeView.setVisibility(View.VISIBLE);
        handAnimeView.setVisibility(View.VISIBLE);
        handAnimeView.setBackgroundResource(R.drawable.hand_animation);
        HandAnimeDrawable =(AnimationDrawable)handAnimeView.getBackground();
        HandAnimeDrawable.start();
//        なでるときのサウンド
        petNaderuPlayer = MediaPlayer.create(this,R.raw.hand);
        petNaderuPlayer.start();
//        3秒で止める
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run(){
                HandAnimeDrawable.stop();
                petDefaultAnime();
                petNadeView.setVisibility(View.INVISIBLE);
                handAnimeView.setVisibility(View.INVISIBLE);
            }
        },3000);
    }
//    ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
//========================レベルアップアニメーション=================================================================
    private void levelUPAnime(){
        levelUPImage = (ImageView)findViewById(R.id.levelUP);
        levelUPAlpha = new AlphaAnimation(1,0);
        levelUPAlpha.setDuration(3000);
        levelUPImage.startAnimation(levelUPAlpha);
        levelUPsound();
    }
//    ==============================================================================================================
//    ~~~~~~~~~~~~~~~~~~~~ウンチを掃除するアニメーション~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    private void cleanToile(ImageView image){
        ImageView IMG = image;
        cleanGoodsAlpha = new AlphaAnimation(1,0);
        cleanGoodsAlpha.setDuration(2000);
        IMG.startAnimation(cleanGoodsAlpha);

        untiplayer = MediaPlayer.create(this,R.raw.clean);
        untiplayer.start();
    }
//    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//    ======================================レベル経過アニメーション====================================================
    private void levelAddAnime(){
        PlusAnimeAlpha = new AlphaAnimation(0,1);
        PlusAnimeAlpha.setDuration(2000);
        plusVewImage.startAnimation(PlusAnimeAlpha);
        plusLevelSound();
    }
//    =====================================================================================================================
//    ~~~~~~~~~~~~~~~~~~~~~~~~犬が食べるアニメーション~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    private void dogSmile(){
        dogDefaultDelete();
        petFaceAnimeView = (ImageView)findViewById(R.id.petFaceAnime);
        petFaceAnimeView.setVisibility(View.VISIBLE);
        if(transIsOk){
            petFaceAnimeView.setBackgroundResource(R.drawable.hituzi_smile_animation);
        }else {
            petFaceAnimeView.setBackgroundResource(R.drawable.dogface_animation);
        }
        dogSmileAnime = (AnimationDrawable)petFaceAnimeView.getBackground();
        dogSmileAnime.start();
        OnigiriSound();
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                petDefaultAnime();
                petFaceAnimeView.setVisibility(View.INVISIBLE);
                dogSmileAnime.stop();
            }
        },3000);
    }
    //    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//===================コメントが表示されるアニメーション============================================
    private void CommentOut(){
        TalkCount =talkText.length;
        randomTalk = new Random();
        SelectTalk = randomTalk.nextInt(TalkCount);
        talkString = talkText[SelectTalk];
        commentText.setText(talkString);
        randOutTalk = new Random();
        randOutCount = randOutTalk.nextInt(30000)+1000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(IsMusicPlay ==true) {
                    commentSound();
                }
                commentAlphaAnimation = new AlphaAnimation(1,0);
                commentAlphaAnimation.setDuration(3000);
                commentText.startAnimation(commentAlphaAnimation);
                commentBackView.startAnimation(commentAlphaAnimation);
                CommentOut();
            }
        },randOutCount);
    }
//    =============================================================================================
//  ~~~~~~~~~~~~~~~~~~~~~~犬のデフォルトアニメーション~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    private void petDefaultAnime(){
        petImage = (ImageView) findViewById(R.id.petViewImage);

        petImage.setVisibility(View.VISIBLE);
        TranslateAnimation petMoveRight = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_SELF, -1,
                TranslateAnimation.RELATIVE_TO_SELF, 1,
                TranslateAnimation.RELATIVE_TO_SELF, 0,
                TranslateAnimation.RELATIVE_TO_SELF, 0
        );
        petMoveRight.setDuration(5000);
        petMoveRight.setRepeatMode(Animation.REVERSE);
        petMoveRight.setRepeatCount(Animation.INFINITE);
        petImage.startAnimation(petMoveRight);
    }
    private void dogDefaultDelete(){
        petImage = (ImageView) findViewById(R.id.petViewImage);
        TranslateAnimation petMoveRight = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_SELF, 0,
                TranslateAnimation.RELATIVE_TO_SELF, 0,
                TranslateAnimation.RELATIVE_TO_SELF, 0,
                TranslateAnimation.RELATIVE_TO_SELF, 0
        );
        petMoveRight.setDuration(0);
        petMoveRight.setRepeatMode(0);
        petMoveRight.setRepeatCount(0);
       petImage.startAnimation(petMoveRight);
        petImage.setVisibility(View.INVISIBLE);
    }

//    }
//    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Override
    protected void onStart() {
        super.onStart();
        sound();
        petDefaultAnime();
        toile();
    }
    private void setShowItem(boolean isShow){
        showItem = isShow;
    }
    private boolean getShowItem(){
        return showItem;
    }
//    =======================BGM===============================================================================
    public void sound() {
        mBgm = MediaPlayer.create(this, R.raw.bgm);
        mBgm.setLooping(true);
        mBgm.setVolume(0.3f,0.3f);
//        mBgm.start();
    }
//    ===========================================================================================================
//    ====================================レベルアップサウンド===============================================
    private void levelUPsound(){
        levelUPplayer = MediaPlayer.create(this,R.raw.level);
        levelUPplayer.start();
    }
//    ===========================================================================================================
    //    =======================トイレお片付けサウンド===================================================================
    private void toileSound(){
        mOnara = MediaPlayer.create(this,R.raw.onara);
        mOnara.start();
    }
    //    =============================================================================================================
//    ==========================トランスサウンド=======================================================================
    private void transSound(){
        TransPlayer = MediaPlayer.create(this,R.raw.trans);
        TransPlayer.start();
    }
//    ===========================================================================================================
//    =======================ボタン選択サウンド==================================================================
    private void selectSound(){
        selectPlayer =MediaPlayer.create(this,R.raw.select);
        selectPlayer.start();
    }
    //    ==========================================================================================================
//    =================コメントサウンド========================================================================
    private void commentSound(){
        commentPlayer = MediaPlayer.create(this,R.raw.comment);
        commentPlayer.start();
    }
//    ==========================================================================================================
    private void plusLevelSound(){
        PlusLevelPlayer = MediaPlayer.create(this,R.raw.level_add);
        PlusLevelPlayer.start();
    }

    //    ========================メニューオープンサウンド========================================================
    private void menuOpenSound(){
        menuOpenPlayer = MediaPlayer.create(this,R.raw.menu);
        menuOpenPlayer.start();
    }
    //    =======================================================================================================
//    ===========================おにぎりを食べるときのサウンド==============================================
    private void OnigiriSound(){
        OnigiriPlay = MediaPlayer.create(this,R.raw.onigiri);
        OnigiriPlay.start();
    }
//    ============================================================================================================
//====================intentの際にすべてのプレイヤーを止める=====================================================
    private void mediaPlayerStop(){
//            commentPlayer.stop();
//            mOnara.stop();
            mBgm.stop();
            IsMusicPlay = false;
    }
    @Override
    protected void onResume(){
        super.onResume();
        mBgm.start();
        IsMusicPlay = true;
    }
    @Override
    protected void onPause(){
        super.onPause();
        mBgm.pause();
        IsMusicPlay = false;
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        mBgm.release();
        mBgm = null;
    }
//    ========================================================================================================
    //    ★
    private void toile(){
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Random randomInt = new Random();
                random = randomInt.nextInt(60000)+1000;
                Log.d("hatena","実験");
//                トイレを4回呼びたい。
                if(toileRoop.roop == 1){
                    if(toileA.getVisibility() == View.INVISIBLE) {
                        if (IsMusicPlay == true) {
                            toileSound();
                        }
                    }
                    int count =0;
                    count +=1;
                    ToileRoopList =toileRoop.getRoopList();
                    if(toileRoop.roop !=0) {
                        toileRoop.setRoopList(count);
                    }
                    toileA.setVisibility(View.VISIBLE);
                }else if(toileRoop.roop == 2){
                    if(toileB.getVisibility() == View.INVISIBLE) {
                        if (IsMusicPlay == true) {
                            toileSound();
                        }
                    }
                    int count =0;
                    count +=1;
                    ToileRoopList =toileRoop.getRoopList();
                    if(toileRoop.roop !=0) {
                        toileRoop.setRoopList(count);
                    }
                    toileB.setVisibility(View.VISIBLE);
                }else if(toileRoop.roop == 3){
                    if(toileC.getVisibility() == View.INVISIBLE) {
                        if (IsMusicPlay == true) {
                            toileSound();
                        }
                    }
                    int count =0;
                    count +=1;
                    ToileRoopList =toileRoop.getRoopList();
                    if(toileRoop.roop !=0) {
                        toileRoop.setRoopList(count);
                    }
                    toileC.setVisibility(View.VISIBLE);
                }else if(toileRoop.roop == 4){
                    if(toileD.getVisibility() == View.INVISIBLE) {
                        if (IsMusicPlay == true) {
                            toileSound();
                        }
                    }
                    int count =0;
                    count +=1;
                    ToileRoopList =toileRoop.getRoopList();
                    if(toileRoop.roop !=0) {
                        toileRoop.setRoopList(count);
                    }
                    toileD.setVisibility(View.VISIBLE);
                }

                toileRoop.roop +=1;
                if(toileRoop.roop >4) {
                    toileRoop.roop = 0;
                }
                toile();

//                int count =0;
//                count +=1;
//                ToileRoopList =toileRoop.getRoopList();
//                if(toileRoop.roop !=0) {
//                    toileRoop.setRoopList(count);
//                }

//                if(ToileRoopList.size() <=4){

//                    toile();
//                }

            }
        },random);
    }
}
