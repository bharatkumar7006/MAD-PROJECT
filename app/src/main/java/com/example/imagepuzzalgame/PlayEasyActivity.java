package com.example.imagepuzzalgame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ColorStateListInflaterCompat;

import android.content.Context;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import static com.example.imagepuzzalgame.R.*;
import static com.example.imagepuzzalgame.R.anim.*;

public class PlayEasyActivity extends AppCompatActivity {


    Dialog epicDialog;
    TextView title,msg;
   static Button clsBtn;
   static LinearLayout overbox, mykonten;
    ImageView close;

    public static final int COLUMNS = 3;
    public static final int DIMENSIONS = COLUMNS * COLUMNS;

    private static String[] tileList;

    private static GestureDetectGridView mGridView;

    private static int mColumnWidth, mColumnHeight;

    public static final String UP = "up";
    public static final String DOWN = "down";
    public static final String LEFT = "left";
    public static final String RIGHT = "right";

    static  Dialog dialog;
    static Animation fromsmall;

    static MediaPlayer myTrack1;

    static TextView showTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_play_easy);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        dialog = new Dialog(this);
        dialog.setContentView(layout.popup);
        clsBtn = (Button) dialog.findViewById(id.closeBtn);
        mykonten = (LinearLayout) findViewById(id.mykonten);
        fromsmall = AnimationUtils.loadAnimation(this,anim.fromsmall);
        myTrack1 = MediaPlayer.create(this, R.raw.melodyloops);
       // showTv = (TextView) dialog.findViewById(id.showtv);

        inti();

        scramble();

        display(getApplicationContext());

        setDimentions();


    }

    public static void showPopUp(){
       //showTv.setAnimation(fromsmall);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        clsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

   }


    private void setDimentions() {
        ViewTreeObserver vto = mGridView.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mGridView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int displayWidth = mGridView.getMeasuredWidth();
                int displayHeight = mGridView.getMeasuredHeight();

                int statusbarHeight =getStatusBarHeight(getApplicationContext());
                int requiredHeight = displayHeight - statusbarHeight;

                mColumnWidth = displayWidth / COLUMNS;
                mColumnHeight = requiredHeight / COLUMNS;


                display(getApplicationContext());


            }
        });
    }

    private int getStatusBarHeight(Context context){
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");

        if (resourceId > 0){
            result = context.getResources().getDimensionPixelSize(resourceId);
        }

        return result;
    }



    private static void display(Context applicationContext){
        ArrayList<Button> buttons = new ArrayList<>();
        Button button;
        for(int i=0; i<tileList.length; i++){

            button = new  Button(applicationContext);
            if (tileList[i].equals("0"))
                button.setBackgroundResource(drawable.funny1);
            else if (tileList[i].equals("1"))
                button.setBackgroundResource(drawable.funny2);
            else if (tileList[i].equals("2"))
                button.setBackgroundResource(drawable.funny3);
            else if (tileList[i].equals("3"))
                button.setBackgroundResource(drawable.funny4);
            else if (tileList[i].equals("4"))
                button.setBackgroundResource(drawable.funny5);
            else if (tileList[i].equals("5"))
                button.setBackgroundResource(drawable.funny6);
            else if (tileList[i].equals("6"))
                button.setBackgroundResource(drawable.funny7);
            else if (tileList[i].equals("7"))
                button.setBackgroundResource(drawable.funny8);
            else if (tileList[i].equals("8"))
                button.setBackgroundResource(drawable.funny9);

            buttons.add(button);

        }

        mGridView.setAdapter(new CustomAdapter(buttons, mColumnWidth, mColumnHeight));



    }

    private void scramble(){

        int index;
        String temp;
        Random random = new Random();

        for(int i = tileList.length - 1; i>0; i--){

            index = random.nextInt(i +1);
            temp = tileList[index];
            tileList[index] = tileList[i];
            tileList[i] = temp;
        }

    }

    private void inti(){
        mGridView = (GestureDetectGridView) findViewById(id.grid);
        mGridView.setNumColumns(COLUMNS);

        tileList = new String[DIMENSIONS];
        for(int i = 0; i<DIMENSIONS; i++){
            tileList[i] = String.valueOf(i);


        }
    }

    private static void swap(Context context, int position, int swap){
        String newPosition = tileList[position + swap];
        tileList[position + swap] = tileList[position];
        tileList[position] = newPosition;
        display(context);

        if (isSolved()){

            myTrack1.stop();
            showPopUp();

            //   Toast.makeText(context, "YOU WIN! Congratulations!", Toast.LENGTH_SHORT).show();


        }
    }

    private static boolean isSolved(){

        boolean solved = false;

        for (int i = 0; i < tileList.length; i++){
            if (tileList[i].equals(String.valueOf(i))){
                solved = true;
            }else{
                solved = false;
                break;
            }
        }

        return solved;
    }

    public static  void moveTiles(Context context, String direction, int position){
        //upper-left-corner-tile
        if (position == 0){
            if (direction.equals(RIGHT)){
                swap(context, position, 1);
            }else if(direction.equals(DOWN)){
                swap(context, position, COLUMNS);
            }else {
                Toast.makeText(context, "Invalid Move", Toast.LENGTH_SHORT).show();
            }
            //upper-center-tile
        }else if (position > 0 && position < COLUMNS - 1){
            if (direction.equals(LEFT)){
                swap(context, position, -1);
            }else if (direction.equals(DOWN)){
                swap(context, position, COLUMNS);
            }else if (direction.equals(RIGHT)){
                swap(context, position, 1);
            }else {
                Toast.makeText(context, "invalid Move", Toast.LENGTH_SHORT).show();
            }
            //upper-right-corner-tile
        }else if (position == COLUMNS - 1){
            if (direction.equals(LEFT)){
                swap(context, position, -1);
            }else if (direction.equals(DOWN)){
                swap(context, position, COLUMNS);
            }else {
                Toast.makeText(context, "Invalid Move", Toast.LENGTH_SHORT).show();
            }
            //left-side-tile
        }else if(position > COLUMNS - 1 && position < DIMENSIONS - COLUMNS && position % COLUMNS == 0) {
            if (direction.equals(UP)) {
                swap(context, position, -COLUMNS);
            } else if (direction.equals(RIGHT)) {
                swap(context, position, 1);
            } else if (direction.equals(DOWN)) {
                swap(context, position, COLUMNS);
            } else {
                Toast.makeText(context, "Invalid Move", Toast.LENGTH_SHORT).show();
            }
            //Right-side and bottom-right-corner-tiles
        }else if (position == COLUMNS * 2 - 1 || position == COLUMNS * 3 - 1){
            if (direction.equals(UP)){
                swap(context, position, -COLUMNS);
            }else if (direction.equals(LEFT)){
                swap(context, position, -1);
            }else if (direction.equals(DOWN)){
                //right-corner-tile
                if (position <= DIMENSIONS - COLUMNS - 1){
                    swap(context, position, COLUMNS);
                }else {
                    Toast.makeText(context, "Invalid Move", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(context, "Invalid Move", Toast.LENGTH_SHORT).show();
            }
            //Bottom-left-corner-tile
        }else if (position == DIMENSIONS - COLUMNS){
            if (direction.equals(UP)){
                swap(context, position, -COLUMNS);
            }else if (direction.equals(RIGHT)){
                swap(context, position, 1);
            }else {
                Toast.makeText(context, "Invalid Move", Toast.LENGTH_SHORT).show();
            }
            //Bottom-center-tile
        }else if (position < DIMENSIONS - 1 && position > DIMENSIONS - COLUMNS){
            if (direction.equals(UP)){
                swap(context, position, -COLUMNS);
            }else if (direction.equals(RIGHT)){
                swap(context, position, 1);
            }else if (direction.equals(LEFT)){
                swap(context, position, -1);
            }else {
                Toast.makeText(context, "Invalid Move", Toast.LENGTH_SHORT).show();
            }
            //center-tile
        }else{
            if (direction.equals(UP)){
                swap(context, position, -COLUMNS);
            }else if (direction.equals(RIGHT)){
                swap(context, position, 1);
            }else if (direction.equals(LEFT)){
                swap(context, position, -1);
            }else {
                swap(context, position, COLUMNS);
            }
        }
    }







}