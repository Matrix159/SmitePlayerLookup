package edu.gvsu.cis.smiteplayerlookup;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.firebase.client.Firebase;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.Types.BoomType;
import com.nightonke.boommenu.Types.ButtonType;
import com.nightonke.boommenu.Types.PlaceType;
import com.nightonke.boommenu.Util;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import edu.gvsu.cis.smitedataretrieval.SmiteMaster;
import edu.gvsu.cis.smitedataretrieval.godinfo.GodInfo;
import edu.gvsu.cis.smitedataretrieval.playerinfo.PlayerInfo;

public class MainActivity extends AppCompatActivity implements BoomMenuButton.OnSubButtonClickListener, BoomMenuButton.AnimatorListener {

    private BoomMenuButton boomButtonActionBar;
    private String[] Colors;
    private EditText editPlayerName;
    private ImageSaver imageSaver;
    private SmiteMaster master;
    private RelativeLayout loading_panel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        loading_panel = (RelativeLayout) findViewById(R.id.main_loading_panel);
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
        boomButtonActionBar = (BoomMenuButton) mCustomView.findViewById(R.id.boom);
        boomButtonActionBar.setOnSubButtonClickListener(this);
        boomButtonActionBar.setAnimatorListener(this);
        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);
        ((Toolbar) mCustomView.getParent()).setContentInsetsAbsolute(0, 0);

        editPlayerName = (EditText) findViewById(R.id.edit_name);
        imageSaver = new ImageSaver(this);
        master = new SmiteMaster(this);
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
            loading_panel.setVisibility(View.VISIBLE);
            new AsynchCaller().execute();
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
    }



    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        //Array of strings for the boom button
        String[] boomStrings = new String[]{"Gods", "Items", "Placeholder"};

        //2D array of ints for the colors
        int[][] intColors = new int[3][2];
        for(int i = 0; i< 3; i++){
            intColors[i][1] = GetColor(i);
            intColors[i][0] = Util.getInstance().getPressedColor(intColors[i][1]);
        }

        //Empty right now until we decide if we want pictures or not
        Drawable[] boomDrawables = new Drawable[3];

        boomButtonActionBar.setDuration(200);
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

    public void lookupPlayer(View v)
    {
        Intent intent = new Intent(this,  PlayerLookupActivity.class);
        if(editPlayerName.getText().toString().isEmpty())
            return;
        intent.putExtra("playerName", editPlayerName.getText().toString());
        this.startActivity(intent);

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

    private class AsynchCaller extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            while (master.getSessionId() == null) {

            }
            List<GodInfo> godInfoList = master.getGods(1);
            for (GodInfo x : godInfoList) {
                Bitmap mIcon;
                InputStream in = null;
                if(x.getName().equals("Ravana"))
                {
                    System.out.println(x.getGodAbility1_URL());
                    System.out.println(x.getGodAbility2_URL());
                    System.out.println(x.getGodAbility3_URL());
                    System.out.println(x.getGodAbility4_URL());
                    System.out.println(x.getGodAbility5_URL());
                }
                try {
                        System.out.println(x.getName());
                        in = new URL(x.getGodIcon_URL()).openStream();
                        mIcon = BitmapFactory.decodeStream(in);
                        imageSaver.setFileName(x.getName()).setDirectoryName("images").save(mIcon);
                        in.close();

                        in = new URL(x.getGodAbility1_URL()).openStream();
                        mIcon = BitmapFactory.decodeStream(in);
                        imageSaver.setFileName(x.getName() + "ability1").setDirectoryName("images").save(mIcon);
                        in.close();

                        in = new URL(x.getGodAbility2_URL()).openStream();
                        mIcon = BitmapFactory.decodeStream(in);
                        imageSaver.setFileName(x.getName() + "ability2").setDirectoryName("images").save(mIcon);
                        in.close();

                        in = new URL(x.getGodAbility3_URL()).openStream();
                        mIcon = BitmapFactory.decodeStream(in);
                        imageSaver.setFileName(x.getName() + "ability3").setDirectoryName("images").save(mIcon);
                        in.close();

                        in = new URL(x.getGodAbility4_URL()).openStream();
                        mIcon = BitmapFactory.decodeStream(in);
                        imageSaver.setFileName(x.getName() + "ability4").setDirectoryName("images").save(mIcon);
                        in.close();

                        in = new URL(x.getGodAbility5_URL()).openStream();
                        mIcon = BitmapFactory.decodeStream(in);
                        imageSaver.setFileName(x.getName() + "ability5").setDirectoryName("images").save(mIcon);
                        in.close();

                } catch (Exception e) {
                    Log.e("Error", e.getMessage());
                    e.printStackTrace();
                }
                finally
                {
                    try
                    {
                        if(in != null)
                            in.close();
                    }
                    catch(IOException ex)
                    {
                        ex.printStackTrace();
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            loading_panel.setVisibility(View.GONE);
        }
    }
}
