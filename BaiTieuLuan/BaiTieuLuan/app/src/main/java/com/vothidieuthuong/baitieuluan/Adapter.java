package com.vothidieuthuong.baitieuluan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.time.temporal.Temporal;
import java.util.List;

public class Adapter extends BaseAdapter {
    MainActivity ncontext;
    int nlayout;
    List<list_casi> nlistcasi;
    public Adapter(MainActivity context, int Layout, List<list_casi> lisst){
       ncontext=context;
       nlayout=  Layout;
       nlistcasi=lisst;

}


    @Override
    public int getCount() {
        return nlistcasi.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater) ncontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert inflater !=null;
        convertView= inflater.inflate(nlayout, null);
        //anhs xaj
        ImageView imgcasi=(ImageView)convertView.findViewById(R.id.imgcasi);
        imgcasi.setImageResource(nlistcasi.get(position).hinhcasi);
        TextView txttencasi=(TextView) convertView.findViewById(R.id.txtcasi);
        txttencasi.setText(nlistcasi.get(position).tencasi);
//        ImageButton imgupdate=(ImageButton) convertView.findViewById(R.id.imgedit);
//        ImageButton imgdelete=(ImageButton) convertView.findViewById(R.id.imgdelete);
//
//        imgupdate.setOnClickListener(new View.OnClickListener() {
//             @Override
//             public void onClick(View v) {
//                 ncontext.Update(position);
//             }
//         });
//        imgdelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ncontext.Delete(position);
//            }
//        });
        return convertView;
    }
}
