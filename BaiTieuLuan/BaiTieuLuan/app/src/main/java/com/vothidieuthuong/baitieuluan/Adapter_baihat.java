package com.vothidieuthuong.baitieuluan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Adapter_baihat extends BaseAdapter {

    Context ncontext;
    int nlayout;
    List<list_baihat> nlistbaihat;
    Adapter_baihat(Context context, int Layout, List<list_baihat> listbh) {
        ncontext = context;
        nlayout = Layout;
        nlistbaihat = listbh;

    }
        @Override
    public int getCount() {
        return nlistbaihat.size();
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

        ImageView imgbh=(ImageView) convertView.findViewById(R.id.imgbh) ;
        imgbh.setImageResource(nlistbaihat.get(position).Anh);
        TextView txttenbh=(TextView) convertView.findViewById(R.id.tenbh);
        txttenbh.setText(nlistbaihat.get(position).tenbaihat);
        TextView txttencasi=(TextView) convertView.findViewById(R.id.sumtime);
        txttencasi.setText(nlistbaihat.get(position).thoigian);
        return convertView;
    }
}
