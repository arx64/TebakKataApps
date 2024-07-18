package com.example.tebakkataapps;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<DataModel> {
    private Context mContext;
    private ArrayList<DataModel> mData;

    public CustomAdapter(Context context, ArrayList<DataModel> data) {
        super(context, R.layout.history_item, data);
        this.mContext = context;
        this.mData = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.history_item, parent, false);
        }

        TextView textViewName = convertView.findViewById(R.id.textViewName);
        TextView textViewDate = convertView.findViewById(R.id.textViewDate);
        TextView textViewScoreBenar = convertView.findViewById(R.id.textViewScoreBenar);
        TextView textViewScoreSalah = convertView.findViewById(R.id.textViewScoreSalah);
        TextView textViewScoreTotal = convertView.findViewById(R.id.textViewScoreTotal);

        DataModel currentItem = mData.get(position);

        textViewName.setText("Nama: " + currentItem.getName());
        textViewDate.setText("Tanggal: " + currentItem.getDatePlay());
        textViewScoreBenar.setText("Benar: " + currentItem.getScoreBenar());
        textViewScoreSalah.setText("Salah: " + currentItem.getScoreSalah());
        textViewScoreTotal.setText("Score: " + currentItem.getScoreTotal());

        return convertView;
    }
}
