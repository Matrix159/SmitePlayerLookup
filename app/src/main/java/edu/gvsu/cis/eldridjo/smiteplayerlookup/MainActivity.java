package edu.gvsu.cis.eldridjo.smiteplayerlookup;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.appinvite.AppInviteInvitation;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.Types.BoomType;
import com.nightonke.boommenu.Types.ButtonType;
import com.nightonke.boommenu.Types.PlaceType;
import com.nightonke.boommenu.Util;

import edu.gvsu.cis.eldridjo.smitedataretrieval.SmiteMaster;

public class MainActivity extends AppCompatActivity implements BoomMenuButton.OnSubButtonClickListener, BoomMenuButton.AnimatorListener {

    private BoomMenuButton boomButtonActionBar;
    private String[] Colors;
    private EditText editPlayerName;
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int REQUEST_INVITE = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        //String of colors for the boom menu.
        // TODO: Add more that fit the pallet for each additional button We need
        Colors = new String[]{
                "#cc0b00",  //Darker Golden color
                "#cc0b00",  //Yellow
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
        boomButtonActionBar = (BoomMenuButton) mCustomView.findViewById(R.id.boom);
        boomButtonActionBar.setOnSubButtonClickListener(this);
        boomButtonActionBar.setAnimatorListener(this);
        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);
        ((Toolbar) mCustomView.getParent()).setContentInsetsAbsolute(0, 0);
        editPlayerName = (EditText) findViewById(R.id.edit_name);
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
        if(buttonIndex == 0)
        {
            Intent intent = new Intent(this, GodListActivity.class);
            this.startActivity(intent);
        }
        if(buttonIndex == 1)
        {
            Intent intent = new Intent(this, ItemListActivity.class);
            this.startActivity(intent);
        }
    }



    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        //Array of strings for the boom button
        String[] boomStrings = new String[]{"Gods", "Items"};

        //2D array of ints for the colors
        int[][] intColors = new int[2][2];
        for(int i = 0; i< 2; i++){
            intColors[i][1] = GetColor(i);
            intColors[i][0] = Util.getInstance().getPressedColor(intColors[i][1]);
        }

        //Empty right now until we decide if we want pictures or not
        Drawable[] boomDrawables = new Drawable[2];

        boomButtonActionBar.setDuration(200);
        boomButtonActionBar.init(
                boomDrawables,      // Array of Drawables
                boomStrings,        // Array of Strings for buttons, ok to be null.
                intColors,    // The colors of sub buttons, including pressed-state and normal-state.
                ButtonType.HAM,     // The button type.
                BoomType.PARABOLA,  // The boom type.
                PlaceType.HAM_2_1,  // The place type.
                null,               // Ease type to move the sub buttons when showing.
                null,               // Ease type to scale the sub buttons when showing.
                null,               // Ease type to rotate the sub buttons when showing.
                null,               // Ease type to move the sub buttons when dismissing.
                null,               // Ease type to scale the sub buttons when dismissing.
                null,               // Ease type to rotate the sub buttons when dismissing.
                null                // Rotation degree.
        );
    }

    public void lookupPlayer(View v)
    {
        Intent intent = new Intent(this,  PlayerLookupActivity.class);
        if(editPlayerName.getText().toString().isEmpty())
            return;
        intent.putExtra("playerName", editPlayerName.getText().toString());
        this.startActivity(intent);

    }

    public void onInviteClicked(View v)
    {

        Intent intent = new AppInviteInvitation.IntentBuilder("App Referral")
                .setEmailHtmlContent("<html><body>"
                        + "<a href=\"%%APPINVITE_LINK_PLACEHOLDER%%\">Come try out Utility for Smite!</a>"
                        + "</body></html>")
                .setEmailSubject("Utility for Smite")
                .setMessage("Try out this app!")
                .build();
        startActivityForResult(intent, REQUEST_INVITE);


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: requestCode=" + requestCode + ", resultCode=" + resultCode);

        if (requestCode == REQUEST_INVITE) {
            if (resultCode == RESULT_OK) {
                // Check how many invitations were sent and log a message
                // The ids array contains the unique invitation ids for each invitation sent
                // (one for each contact select by the user). You can use these for analytics
                // as the ID will be consistent on the sending and receiving devices.
                String[] ids = AppInviteInvitation.getInvitationIds(resultCode, data);

            } else {
                // Sending failed or it was canceled, show failure message to the user
                System.out.println("Sending failed");
            }
        }
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
