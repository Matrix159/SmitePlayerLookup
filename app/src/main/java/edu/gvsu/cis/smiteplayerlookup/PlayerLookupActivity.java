package edu.gvsu.cis.smiteplayerlookup;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.Types.BoomType;
import com.nightonke.boommenu.Types.ButtonType;
import com.nightonke.boommenu.Types.PlaceType;
import com.nightonke.boommenu.Util;

import org.w3c.dom.Text;

import java.util.List;

import edu.gvsu.cis.smitedataretrieval.SmiteMaster;
import edu.gvsu.cis.smitedataretrieval.playerinfo.PlayerInfo;

public class PlayerLookupActivity extends AppCompatActivity{

    private BoomMenuButton boomButtonActionBar;
    private String[] Colors;
    private SmiteMaster master;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_lookup);

        //String of colors for the boom menu.
        // TODO: Add more that fit the pallet for each additional button We need
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        master = new SmiteMaster(this);

    }

    private class AsynchCaller extends AsyncTask<Void, Void, Void>
    {
        List<PlayerInfo> list;
        TextView name = (TextView) findViewById(R.id.playername);
        EditText editText = (EditText) findViewById(R.id.editText);
        TextView wins = (TextView) findViewById(R.id.winText);
        TextView losses = (TextView) findViewById(R.id.lossesText);
        TextView clanName = (TextView) findViewById(R.id.clan_name);
        String player = "";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            player = editText.getText().toString();
        }

        @Override
        protected Void doInBackground(Void... params) {
            list = master.getPlayer(player);


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            name.setText(list.get(0).getName());
            wins.setText(String.valueOf(list.get(0).getWins()));
            losses.setText(String.valueOf(list.get(0).getLosses()));
            clanName.setText(String.valueOf(list.get(0).getTeam_Name()));
        }


    }
    public void test(View v)
    {
        new AsynchCaller().execute();


    }




}
