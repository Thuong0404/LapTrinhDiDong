package com.vothidieuthuong.baitieuluan;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ActivityYeuthich extends AppCompatActivity {
    ListView listView;
    TextView txttencs, txtstart, txtend,txtdagphat;
    ImageButton imgplay, imgnext, imgback, imghinhbh;
    ListView listViewbh;
    SeekBar seekBar;
    ArrayList<list_baihat> bh;
    ArrayList<list_casi> cs;
    MediaPlayer Player= new MediaPlayer();
    int vitriphat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yeuthich2);
        loadView();



        bh=new ArrayList<list_baihat>();

        bh.add(new list_baihat(R.drawable.buongtay,"Buông tay",time(R.raw.my2),R.raw.my2));


        bh.add(new list_baihat(R.drawable.maithuocveanh,"Mãi thuộc về anh",time(R.raw.tien1),R.raw.tien1));
        bh.add(new list_baihat(R.drawable.saoanhkhongan,"Sao anh không ăn",time(R.raw.tien2),R.raw.tien2));


        bh.add(new list_baihat(R.drawable.nhungkemongmo,"Những kẻ mộng mơ",time(R.raw.thinh1),R.raw.thinh1));
        bh.add(new list_baihat(R.drawable.emdathuongngtahona,"Em đã thương người ta hơn anh",time(R.raw.thinh2),R.raw.thinh2));



        bh.add(new list_baihat(R.drawable.thuchuagoianh,"Thư chưa gởi anh",time(R.raw.hoa1),R.raw.hoa1));



        bh.add(new list_baihat(R.drawable.muonroi,"Muộn rồi mà sao còn",time(R.raw.tung1),R.raw.tung1));



        bh.add(new list_baihat(R.drawable.laylalay,"Laylalay",time(R.raw.meo1),R.raw.meo1));


        Adapter_baihat adapter_baihat= new Adapter_baihat(ActivityYeuthich.this, R.layout.list_bai_hat,bh);
        listViewbh.setAdapter(adapter_baihat);


        tg_ht();

        listViewbh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(Player.isPlaying()){
                    Player.stop();
                }else{
                    vitriphat=position;
                    khoitao();
                }
                imgplay.setImageResource(R.drawable.pause);
            }

        });
        imgplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Player.isPlaying()){
                    Player.pause();
                    Intent intent=new Intent(ActivityYeuthich.this,MyService.class);
                    clickStopService();
                    imgplay.setImageResource(R.drawable.dung);

                }else{
                    Player.start();

                    imgplay.setImageResource(R.drawable.pause);
                    Intent intent=new Intent(ActivityYeuthich.this,MyService.class);
                    intent.putExtra("Ten_ba_hat",txtdagphat.getText().toString().trim());
                    intent.putExtra("Ca_si", txttencs.getText().toString().trim());


                }

            }
        });

        imgnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vitriphat++;
                if(vitriphat>(bh.size()-1)){
                    vitriphat=0;
                }

                Player.stop();
                khoitao();
            }
        });
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vitriphat--;
                if (vitriphat<0){
                    vitriphat=bh.size()-1;
                }

                Player.stop();
                khoitao();
            }
        });
//     imgtim.setOnClickListener(new View.OnClickListener() {
//         @Override
//         public void onClick(View v) {
//             Intent intent= new Intent(ActivityBaihat.this, Yeuthich.class);
//             intent.putExtra("Ten_ba_ha",txtdagphat.getText().toString().trim());
//             intent.putExtra("Ca_s", txttencs.getText().toString().trim());
//             intent.putExtra("thoigian",txtend.getText().toString().trim());
//         }
//     });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Player.seekTo(seekBar.getProgress());
            }
        });
    }
//        Intent intent=getIntent();
//        String tenbh= intent.getStringExtra("ten_ba_ha");
//        String tencs= intent.getStringExtra("Ca_s");
//        String tg= intent.getStringExtra("thoigian");
//        txtcs.setText(tenbh);
//        txtbh.setText(tencs);
//        txttime.setText(tg);


    private void clickStopService() {
        Intent intent =new Intent(this, MyService.class);
        stopService(intent);

    }

    private void clickStartService() {

        Intent intent =new Intent(this, MyService.class);
        //intent.putExtra("Anh",imghinhbh.getBackground().toString().trim());
        intent.putExtra("Ten_ba_hat",txtdagphat.getText().toString().trim());
        intent.putExtra("Ca_si", txttencs.getText().toString().trim());


        startService(intent);



    }

    void tg_ht(){
        Handler handler= new Handler();
        Boolean b=handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat simpleDateFormat= new SimpleDateFormat("mm:ss");
                txtstart.setText(simpleDateFormat.format(Player.getCurrentPosition()));
                seekBar.setProgress(Player.getCurrentPosition());
                Player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        vitriphat++;
                        if(vitriphat>bh.size()-1){
                            Player.stop();
                        } else {
                            Player.stop();
                            khoitao();
                            Player.start();
                        }
                    }
                });
                handler.postDelayed(this,500);
            }
        }, 100);

    }

    private void khoitao() {
        Intent intent = new Intent(ActivityYeuthich.this, MyService.class);
        Player= MediaPlayer.create(ActivityYeuthich.this,bh.get(vitriphat).baihat);
        txtdagphat.setText("Đang phát : "+bh.get(vitriphat).tenbaihat);

        txtend.setText(time(bh.get(vitriphat).baihat));
        seekBar.setMax(Player.getDuration());
        Player.start();
        intent.putExtra("Ten_ba_hat",txtdagphat.getText().toString().trim());
       startService(intent);
    }


    private String time(int baihat){
        String t;
        MediaPlayer Player=new MediaPlayer();
        Player=MediaPlayer.create(ActivityYeuthich.this,baihat);
        SimpleDateFormat tg = new SimpleDateFormat("mm:ss");
        t=tg.format(Player.getDuration());
        return t;
    }

    @SuppressLint("WrongViewCast")
    private void loadView() {

        imghinhbh=findViewById(R.id.imgbh);
        txttencs=findViewById(R.id.tencasi);
        txtstart=findViewById(R.id.timeplayyt);
        txtdagphat=findViewById(R.id.dagphatyt);
        txtend=findViewById(R.id.timeoutyt);

        imgplay=findViewById(R.id.stopyt);
        imgback=findViewById(R.id.luiyt);
        imgnext=findViewById(R.id.nextyt);
        listViewbh=findViewById(R.id.listyt);
        seekBar=findViewById(R.id.seebaryt);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
}
