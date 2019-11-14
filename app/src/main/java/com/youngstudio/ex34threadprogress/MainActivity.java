package com.youngstudio.ex34threadprogress;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    ProgressDialog dialog;
    int gauge=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickBtn(View view) { // 시작
        if( dialog!=null) return;

        //wheel type progress dialog
        dialog= new ProgressDialog(this);
        dialog.setTitle("wheel dialog");
        dialog.setMessage("downloading...");
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        dialog.setCanceledOnTouchOutside(false);//다른작업누를때 켄슬 x
        dialog.show();

        //3초뒤에 dialog 종료 dialog.dismiss();
        handler.sendEmptyMessageDelayed(0,3000);

    }


    Handler handler= new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            if(dialog!=null){
                dialog.dismiss();
                dialog=null;
            }

        }
    };


    public void clickBtn2(View view) {
        if(dialog!=null)return;

        //bar type progress dialog
        dialog= new ProgressDialog(this);
        dialog.setTitle("막대바 다이얼로그");
        dialog.setMessage("다운로드 중........");
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

        dialog.setMax(100);

        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        dialog.setProgress(gauge);

        new Thread(){
            @Override
            public void run() {
                gauge=0;
                while(gauge<100){
                    gauge++;
                    dialog.setProgress(gauge);

                    //50ms(0.05초)
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }//while
                dialog.dismiss();
                dialog=null;

            }//run
        }.start();

    }

    public void clickBtn3(View view) {


    }
}//MainActivity
