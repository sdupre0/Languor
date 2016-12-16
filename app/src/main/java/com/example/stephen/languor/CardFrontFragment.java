package com.example.stephen.languor;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static com.example.stephen.languor.learnActivity.WORD;

public class CardFrontFragment extends Fragment {

    public CardFrontFragment() {}

    //widgets
    TextView word;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View front = inflater.inflate(R.layout.fragment_card_front, container, false);
        word = (TextView) front.findViewById(R.id.card_front_word);
        word.setText(getArguments().getString(WORD));

        return front;
    }

    public static CardFrontFragment setFrontWord(String str) {
        CardFrontFragment fragment = new CardFrontFragment();
        Bundle b = new Bundle();
        b.putString(WORD, str);
        fragment.setArguments(b);
        return fragment;
    }
}