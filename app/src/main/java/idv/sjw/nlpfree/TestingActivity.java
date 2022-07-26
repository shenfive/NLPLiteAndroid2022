package idv.sjw.nlpfree;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestingActivity extends AppCompatActivity {


    DBManager dbManager;
    Button but1,but2,but3;
    TextView quistionView,counterView;
    int[] qustionRamdomSq;

    int maxQustionNumber = 19;
    int qustionCount = -1;
    int[] userAnswers = {0,0,0};

    class Qustion{
        String qustion;
        String a;
        String b;
        String c;
        String[] answers = new String[3];

        Qustion(String qustion,String a,String b,String c){
            this.qustion= qustion;
            this.a = a;
            this.b = b;
            this.c = c;
            String[] t = {a,b,c};
            this.answers = t;
        }
    }


    List<Qustion> qustions = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);
        but1 = (Button) findViewById(R.id.btn1);
        but2 = (Button) findViewById(R.id.btn2);
        but3 = (Button) findViewById(R.id.btn3);
        quistionView = (TextView)findViewById(R.id.qustionView);
        counterView = (TextView)findViewById(R.id.countView);

        //設定隱藏狀態
        getSupportActionBar().hide();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        dbManager = new DBManager(this);

        //取題目, 若無, 由FireBase　下載
        updateQustionList();
        if ( qustions.size() == 0 ){
            downloadQustionList();
        }else {
            qustionRamdomSq = ramdomArray(maxQustionNumber,qustions.size());
            nextQustion();
        }
    }


    protected void nextQustion(){
        qustionCount++;
        if(qustionCount == maxQustionNumber){
            Intent intent = new Intent(this,ResultActivity.class);
            intent.putExtra("userAnswers",userAnswers);
            startActivity(intent);
            finish();
            return;
        }

        Qustion currenQustion = qustions.get(qustionRamdomSq[qustionCount]);

        quistionView.setText(currenQustion.qustion);


        int[] answerRamdomSq = ramdomArray(3,3);


        but1.setText(currenQustion.answers[answerRamdomSq[0]]);
        but1.setTag(answerRamdomSq[0]);

        but2.setText(currenQustion.answers[answerRamdomSq[1]]);
        but2.setTag(answerRamdomSq[1]);

        but3.setText(currenQustion.answers[answerRamdomSq[2]]);
        but3.setTag(answerRamdomSq[2]);

        counterView.setText( (qustionCount + 1) + "/" + maxQustionNumber);

    }

    public void aClick(View view){
//        Log.d("Click",""+view.getTag().toString());
        userAnswers[Integer.parseInt(view.getTag().toString())]++;
        nextQustion();
    }



    public void updateQustionList(){
        String[] col = {dbManager.ColKey,dbManager.ColQustion,dbManager.ColA,dbManager.ColB,dbManager.ColC};
        Cursor cursor = dbManager.query(col,null,null,dbManager.ColKey);
        if(cursor.moveToFirst()){
            do{
                Qustion qustion = new Qustion(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
                qustions.add(qustion);
            }while (cursor.moveToNext());
        }
//        Toast.makeText(this,"Update"+qustions.size(),Toast.LENGTH_SHORT).show();
    }


    protected void downloadQustionList(){ //更新題目清單

        //[issue]應先檢查網路

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("data").child("zh-tw");

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {



                for(DataSnapshot item:dataSnapshot.getChildren()){

                    if (item.getKey().equals("version")){break;};

                    ContentValues contentValues = new ContentValues();
                    contentValues.put(dbManager.ColKey,Integer.parseInt(item.getKey()));
                    contentValues.put(dbManager.ColQustion,item.child("qustion").getValue().toString());
                    contentValues.put(dbManager.ColA,item.child("a").getValue().toString());
                    contentValues.put(dbManager.ColB,item.child("b").getValue().toString());
                    contentValues.put(dbManager.ColC,item.child("c").getValue().toString());
                    dbManager.insertData(contentValues);
                };
                updateQustionList();
                qustionRamdomSq = ramdomArray(maxQustionNumber,qustions.size());
                nextQustion();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };

        myRef.addListenerForSingleValueEvent(valueEventListener);
    }


    private int[] ramdomArray(int number,int in){

        int[] array = new int[in];
        int[] returnArray = new int[number];
        for(int i=0;i<in;i++){
            array[i]=i;
        }
        Random ran = new Random();

        for(int i=0;i<in*10;i++){
            int random = ran.nextInt(in);
            int temp = array[random];
            array[random]=array[0];
            array[0] = temp;
        }

        for(int i=0;i<number;i++){
            returnArray[i]=array[i];

        }
        return returnArray;

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return true;
    }








}