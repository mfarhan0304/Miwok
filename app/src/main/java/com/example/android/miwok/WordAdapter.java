package com.example.android.miwok;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    private int mBackgroundColor = 0;

    public WordAdapter(@NonNull Context context, @NonNull ArrayList<Word> objects, int backgroundColor) {
        super(context, 0, objects);
        mBackgroundColor = backgroundColor;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Word currentWord = getItem(position);

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        // Set the theme color for the list item
        View wordContainer = listItemView.findViewById(R.id.wordContainer);
        // Find the color that the resource ID maps to
        int color = ContextCompat.getColor(getContext(), mBackgroundColor);
        // Set the background color of the text container View
        wordContainer.setBackgroundColor(color);
        
        TextView defaultText = (TextView) listItemView.findViewById(R.id.default_text);
        defaultText.setText(currentWord.getDefaultTranslation());

        TextView miwokText = (TextView) listItemView.findViewById(R.id.miwok_text);
        miwokText.setText(currentWord.getMiwokTranslation());

        ImageView resourceImage = (ImageView) listItemView.findViewById(R.id.miwok_image);

        if (currentWord.hasImage()) {
            resourceImage.setVisibility(View.GONE);
        } else {
            resourceImage.setImageResource(currentWord.getImageResourceId());
        }

        return listItemView;
    }
}
