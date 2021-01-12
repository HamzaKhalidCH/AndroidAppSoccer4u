package com.example.soccer4u;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class leagues_list_adapter extends ArrayAdapter<String> {

    private ArrayList<String> league_names = new ArrayList<>();
    Context context;

    public leagues_list_adapter(@NonNull Activity context, ArrayList<String> names) {
        super(context, R.layout.leagues_list_item,names);
        this.context=context;
        this.league_names=names;
    }

    public View getView(final int position, View convertView, ViewGroup parent)
    {
        parent.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        String leagueName = getItem(position);
        leagueName = leagueName.toLowerCase();
        leagueName = leagueName.replaceAll("\\s+","");
        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.leagues_list_item,parent,false);
        }

      //  ImageButton btn = (ImageButton) convertView.findViewById(R.id.LeaguesImageView);
        ImageView img = (ImageView) convertView.findViewById(R.id.LeaguesImageView);
        String uri = "drawable/"+leagueName;
        int imgid = this.context.getResources().getIdentifier(uri,null,context.getPackageName());
        Drawable image = context.getResources().getDrawable(imgid);
        img.setImageDrawable(image);

        //TextView text = (TextView) convertView.findViewById(R.id.PersonName);
        //text.setText(person_name);

        return convertView;
    }
}
