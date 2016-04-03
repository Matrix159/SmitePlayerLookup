package edu.gvsu.cis.smiteplayerlookup;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.Types.BoomType;
import com.nightonke.boommenu.Types.ButtonType;
import com.nightonke.boommenu.Types.PlaceType;
import com.nightonke.boommenu.Util;

public class MainActivity extends AppCompatActivity implements BoomMenuButton.OnSubButtonClickListener, BoomMenuButton.AnimatorListener {

    private BoomMenuButton boomButtonActionBar;
    private String[] Colors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //String of colors for the boom menu.
        // TODO: Add more that fit the pallet for each additional button We need
        Colors = new String[]{
                "#b89500",  //Darker Golden color
                "#ffce00",  //Yellow
                "#16C3D9",    //Cyan Kind of color
                "#0D5B80"  //Slightly dark blue

        };

        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(true);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);



        View mCustomView = mInflater.inflate(R.layout.custom_actionbar, null);
        TextView mTitleTextView = (TextView) mCustomView.findViewById(R.id.title_text);
        mTitleTextView.setText(R.string.app_name);

        //mCustomView.isInEditMode();

        boomButtonActionBar = (BoomMenuButton) mCustomView.findViewById(R.id.boom);
        boomButtonActionBar.setOnSubButtonClickListener(this);
        boomButtonActionBar.setAnimatorListener(this);

        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);

        ((Toolbar) mCustomView.getParent()).setContentInsetsAbsolute(0,0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(int buttonIndex) {

    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        //Array of strings for the boom button
        String[] boomStrings = new String[]{"Test", "God Lookup", "Player Finder"};

        //2D array of ints for the colors
        int[][] intColors = new int[3][2];
        for(int i = 0; i< 3; i++){
            intColors[i][1] = GetColor(i);
            intColors[i][0] = Util.getInstance().getPressedColor(intColors[i][1]);
        }

        //Empty right now until we decide if we want pictures or not
        Drawable[] boomDrawables = new Drawable[3];


        boomButtonActionBar.init(
                boomDrawables,      // Array of Drawables
                boomStrings,        // Array of Strings for buttons, ok to be null.
                intColors,    // The colors of sub buttons, including pressed-state and normal-state.
                ButtonType.HAM,     // The button type.
                BoomType.PARABOLA,  // The boom type.
                PlaceType.HAM_3_1,  // The place type.
                null,               // Ease type to move the sub buttons when showing.
                null,               // Ease type to scale the sub buttons when showing.
                null,               // Ease type to rotate the sub buttons when showing.
                null,               // Ease type to move the sub buttons when dismissing.
                null,               // Ease type to scale the sub buttons when dismissing.
                null,               // Ease type to rotate the sub buttons when dismissing.
                null                // Rotation degree.
        );
    }

    /********************************************************
     * Returns an int for the color in a string of colors
     * @param index of Colors
     * @return int version of the color
     ********************************************************/
    private int GetColor (int index){
        return Color.parseColor(Colors[index]);
    }


    @Override
    public void toShow() {

    }

    @Override
    public void showing(float fraction) {

    }

    @Override
    public void showed() {

    }

    @Override
    public void toHide() {

    }

    @Override
    public void hiding(float fraction) {

    }

    @Override
    public void hided() {

    }

}
