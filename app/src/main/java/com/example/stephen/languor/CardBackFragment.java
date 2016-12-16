package com.example.stephen.languor;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static com.example.stephen.languor.learnActivity.INFO;
import static com.example.stephen.languor.learnActivity.WORD;

public class CardBackFragment extends Fragment {

    public CardBackFragment() {}

    //widgets
    TextView word, info;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View back = inflater.inflate(R.layout.fragment_card_back, container, false);

        word = (TextView) back.findViewById(R.id.card_back_word);
        info = (TextView) back.findViewById(R.id.card_back_extra);

        word.setText(getArguments().getString(WORD));
        if (getArguments().getString(INFO) != null)
            info.setText(getArguments().getString(INFO));
        return back;
    }

    public static CardBackFragment setBackInfo(String word, String info) {
        CardBackFragment fragment = new CardBackFragment();
        Bundle b = new Bundle();
        b.putString(WORD, word);
        b.putString(INFO, info);
        fragment.setArguments(b);
        return fragment;
    }
}