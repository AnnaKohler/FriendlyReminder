package ru.workingcorgi.friendlyreminder;


import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class MainActivity extends FragmentActivity {

    public NiceWordsFragment CreateNiceWordsFragment(int color){
        return NiceWordsFragment.newInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setting the random background color
        int colorArray[]=getResources().getIntArray(R.array.background_colors);
        int rndColor = colorArray[(int)(Math.random()*colorArray.length)];

        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.main_relativeLayout);
        linearLayout.setBackgroundColor(rndColor);

        //Getting the screen size
        /*DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int dispHeight = displayMetrics.heightPixels;

        //Setting the size of a container for words
        FrameLayout containerWords=(FrameLayout)findViewById(R.id.container_words);
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) containerWords.getLayoutParams();
        params.height=(int)(dispHeight*0.6);
        containerWords.setLayoutParams(params); //TODO: убрать, если weights нормально работают*/


    }
}
