package com.vothidieuthuong.baitieuluan;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ActivityBaihat extends AppCompatActivity {
 TextView txttencs, txtstart, txtend,txtdagphat;
 ImageButton imgplay, imgnext, imgback, imghinhbh, imgtim ;
 ListView listViewbh;
 SeekBar seekBar;
 ArrayList<list_baihat> bh;
 ArrayList<list_casi> cs;
 MediaPlayer Player= new MediaPlayer();
 int vitriphat;

 @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baihat);
        loadView();
        Intent intent= getIntent();
        int vt=intent.getIntExtra("position",0);
         String ten=intent.getStringExtra("tencasi");
         txttencs.setText("Ca sĩ : "+ ten);

         bh=new ArrayList<list_baihat>();
         switch (vt){
             case 0:
                 bh.add(new list_baihat(R.drawable.ai,"Ai Ai Ai", time(R.raw.my1),R.raw.my1));
                 bh.add(new list_baihat(R.drawable.buongtay,"Buông tay",time(R.raw.my2),R.raw.my2));
                 break;
             case 1:
                 bh.add(new list_baihat(R.drawable.maithuocveanh,"Mãi thuộc về anh",time(R.raw.tien1),R.raw.tien1));
                 bh.add(new list_baihat(R.drawable.saoanhkhongan,"Sao anh không ăn",time(R.raw.tien2),R.raw.tien2));
                 break;
             case 2:
                 bh.add(new list_baihat(R.drawable.nhungkemongmo,"Những kẻ mộng mơ",time(R.raw.thinh1),R.raw.thinh1));
                 bh.add(new list_baihat(R.drawable.emdathuongngtahona,"Em đã thương người ta hơn anh",time(R.raw.thinh2),R.raw.thinh2));
                 bh.add(new list_baihat(R.drawable.yeumotnguoisaobuondenthe,"Yêu một người sao buồn đến thế",time(R.raw.thinh3),R.raw.thinh3));
                 break;
             case 3:
                 bh.add(new list_baihat(R.drawable.thuchuagoianh,"Thư chưa gởi anh",time(R.raw.hoa1),R.raw.hoa1));
                 bh.add(new list_baihat(R.drawable.dieubuonnhat,"Điều buồn nhất khi yêu",time(R.raw.hoa2),R.raw.hoa2));
                 break;
             case 4:
                 bh.add(new list_baihat(R.drawable.muonroi,"Muộn rồi mà sao còn",time(R.raw.tung1),R.raw.tung1));
                 bh.add(new list_baihat(R.drawable.goinguoiyeucu,"Gửi người yêu cũ",time(R.raw.tung2),R.raw.tung2));
                 break;
             case 5:
                 bh.add(new list_baihat(R.drawable.laylalay,"Laylalay",time(R.raw.meo1),R.raw.meo1));
                 break;
         }
         Adapter_baihat adapter_baihat= new Adapter_baihat(ActivityBaihat.this, R.layout.list_bai_hat,bh);
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
          imgplay.setImageResource(R.drawable.dung1);
         }

     });
     imgplay.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

             if(Player.isPlaying()){
                 Player.pause();
                 Intent intent=new Intent(ActivityBaihat.this,MyService.class);
                 clickStopService();
                 imgplay.setImageResource(R.drawable.dung);

             }else{
                 Player.start();

                 imgplay.setImageResource(R.drawable.pause);
                 Intent intent=new Intent(ActivityBaihat.this,MyService.class);
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
        Intent intent = new Intent(ActivityBaihat.this, MyService.class);
        Player= MediaPlayer.create(ActivityBaihat.this,bh.get(vitriphat).baihat);
        txtdagphat.setText("Đang phát : "+bh.get(vitriphat).tenbaihat);

        txtend.setText(time(bh.get(vitriphat).baihat));
       seekBar.setMax(Player.getDuration());
        Player.start();
        intent.putExtra("Ten_ba_hat",txtdagphat.getText().toString().trim());
        clickStartService();
    }

//    public boolean onOptionsItemSelected(MenuItem item){
//        int id =item.getItemId();
//        switch (id) {
//            case R.id.actim:
//                int i;
//                Toast.makeText(ActivityBaihat.this, "Yêu thích", Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(ActivityBaihat.this, ActivityYeuthich.class);
//                startActivity(intent);
//                // Gọi màn hình AditionSportActivity
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }
    private String time(int baihat){
        String t;
        MediaPlayer Player=new MediaPlayer();
        Player=MediaPlayer.create(ActivityBaihat.this,baihat);
       SimpleDateFormat tg = new SimpleDateFormat("mm:ss");
        t=tg.format(Player.getDuration());
        return t;
    }

    @SuppressLint("WrongViewCast")
    private void loadView() {
         imgtim = findViewById(R.id.imgtim);
        imghinhbh=findViewById(R.id.imgbh);
        txttencs=findViewById(R.id.tencasi);
        txtstart=findViewById(R.id.timeplay);
        txtdagphat=findViewById(R.id.dagphat);
        txtend=findViewById(R.id.timeout);

        imgplay=findViewById(R.id.stop);
        imgback=findViewById(R.id.lui);
        imgnext=findViewById(R.id.next);
        listViewbh=findViewById(R.id.nhac);
        seekBar=findViewById(R.id.seebar);


    }

    //@Override
//    protected void onStop() {
//     Player.stop();
//        super.onStop();
//    }
}