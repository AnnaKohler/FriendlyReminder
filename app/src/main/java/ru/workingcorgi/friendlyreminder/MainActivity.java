package ru.workingcorgi.friendlyreminder;


import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setting the random background color
        int colorArray[]=getResources().getIntArray(R.array.background_colors);
        int rndColor = colorArray[(int)(Math.random()*colorArray.length)];

        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.main_relativeLayout);
        linearLayout.setBackgroundColor(rndColor);


    }
}
