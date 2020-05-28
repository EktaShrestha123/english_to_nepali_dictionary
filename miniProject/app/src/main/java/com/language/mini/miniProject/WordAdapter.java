package com.language.mini.miniProject;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    private int mColorResourceId;

    public WordAdapter(Activity context, ArrayList<Word> words, int ColorResourceId){
        super(context, 0,words);
        mColorResourceId = ColorResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        View ListItemView = convertView;
        if(ListItemView == null){
            ListItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);

        }

        Word currentWord = getItem(position);


        TextView miwokTextView = (TextView) ListItemView.findViewById(R.id.nepali_text_view);


        miwokTextView.setText(currentWord.getmMiwokTranslation());

        TextView defaultTextView = (TextView) ListItemView.findViewById(R.id.default_text_view);

        defaultTextView.setText(currentWord.getmDefaultTranslation());


        ImageView imageView = (ImageView) ListItemView.findViewById(R.id.image);

        if(currentWord.hasImage()) {

            imageView.setImageResource(currentWord.getImageResourceId());


            imageView.setVisibility(View.VISIBLE);
        }
        else{

            imageView.setVisibility(View.GONE);
        }

        View textContainer = ListItemView.findViewById(R.id.text_container);

        int color = ContextCompat.getColor(getContext(), mColorResourceId);

        textContainer.setBackgroundColor(color);


        return ListItemView;
    }

}
