package com.vothidieuthuong.baitieuluan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listcasi;
    ArrayList<list_casi> list_casi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listcasi = (ListView) findViewById(R.id.list_casi);
        list_casi = new ArrayList<list_casi>();

        list_casi.add(new list_casi("Khởi My", R.drawable.my));
        list_casi.add(new list_casi("Thủy Tiên", R.drawable.tien));
        list_casi.add(new list_casi("Noo Phước Thịnh", R.drawable.thinh));
        list_casi.add(new list_casi("Hòa Minzy", R.drawable.hoa));
        list_casi.add(new list_casi("Sơn Tùng", R.drawable.tung));
        list_casi.add(new list_casi("Jack", R.drawable.meo));
        Adapter adaptercs = new Adapter(MainActivity.this, R.layout.list_ca_si, list_casi);
        listcasi.setAdapter(adaptercs);
        listcasi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ActivityBaihat.class);

                intent.putExtra("position", position);
                intent.putExtra("tencasi", list_casi.get(position).tencasi);
                startActivity(intent);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.actim:
                int i;
                Toast.makeText(MainActivity.this, " Danh sách bài hát yêu thích", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, ActivityYeuthich.class);
                startActivity(intent);
                // Gọi màn hình AditionSportActivity
                ;
                break;
        }
        return super.onOptionsItemSelected(item);
    }
//    public  void Delete( final int position){
//        Toast.makeText(this,"Bạn chọn xóa ca sĩ",Toast.LENGTH_LONG).show();
//        list_casi.remove(position);
//    }
//    public  void Update( final int position) {
//        Toast.makeText(this, "Bạn chọn sửa ca sĩ", Toast.LENGTH_LONG).show();
//        final list_casi casi = list_casi.get(position);
//
//        View dialogSheetView= LayoutInflater.from(MainActivity.this).inflate(R.layout.edit_casi,null);
//        BottomSheetDialog dialog=new BottomSheetDialog(MainActivity.this);
//        EditText edtten=(EditText) dialogSheetView.findViewById(R.id.edtten);
//        ImageView imgedit=(ImageView) dialog.findViewById(R.id.imgcasi);
//        Button btnupdate=(Button) dialog.findViewById(R.id.btnupdate);
//        btnupdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final String Ten=edtten.getText().toString().trim();
//
//            }
//        });
//        dialog.show();
//    }
}