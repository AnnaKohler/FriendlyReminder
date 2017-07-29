package ru.workingcorgi.friendlyreminder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NiceWordsFragment extends Fragment {

    private String[] niceWords;
    private String rndNiceWords;


    public static NiceWordsFragment newInstance() {

        Bundle args = new Bundle();

        NiceWordsFragment fragment = new NiceWordsFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Getting the NiceWords Array
        niceWords=getActivity().getResources().getStringArray(R.array.nice_words);
        rndNiceWords=niceWords[(int)(Math.random()*niceWords.length)];


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_nicewords, container, false);

        //Setting the words
        AutoResizeTextView words= (AutoResizeTextView)v.findViewById(R.id.txtWords);
        words.setText(rndNiceWords);
        //Getting the font
        

        return v;
    }
}
