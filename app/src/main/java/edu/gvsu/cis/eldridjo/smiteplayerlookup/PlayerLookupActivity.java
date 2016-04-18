package edu.gvsu.cis.eldridjo.smiteplayerlookup;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.nightonke.boommenu.BoomMenuButton;

import java.util.List;

import edu.gvsu.cis.eldridjo.smitedataretrieval.SmiteMaster;
import edu.gvsu.cis.eldridjo.smitedataretrieval.playerinfo.PlayerInfo;
import edu.gvsu.cis.eldridjo.smitedataretrieval.playerinfo.PlayerStatus;

public class PlayerLookupActivity extends AppCompatActivity{

    private BoomMenuButton boomButtonActionBar;
    private String[] Colors;
    private SmiteMaster master;
    private String name;
    private List<PlayerInfo> list;
    private List<PlayerStatus> statusList;
    private Context thisRef;
    //stuff for the fragments
    ConquestFragment conquestFragment;
    DuelFragment duelFragment;
    JoustFragment joustFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_lookup);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        master = new SmiteMaster(this);
        thisRef = this;
        Intent intent = this.getIntent();
        name = intent.getStringExtra("playerName");
        new AsynchCaller().execute();
        if(findViewById(R.id.fragmentHolder) != null){
            if(savedInstanceState != null){
                return;
            }

            conquestFragment = new ConquestFragment();
            joustFragment = new JoustFragment();
            duelFragment = new DuelFragment();

            conquestFragment.setArguments(getIntent().getExtras());
            joustFragment.setArguments(getIntent().getExtras());
            duelFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentHolder, conquestFragment)
                    .add(R.id.fragmentHolder, joustFragment)
                    .add(R.id.fragmentHolder, duelFragment)
                    .hide(duelFragment)
                    .hide(joustFragment)
                    .commit();
        }


    }

    private class AsynchCaller extends AsyncTask<Void, Void, Void>
    {

        TextView nameTextView = (TextView) findViewById(R.id.playername);
        TextView wins = (TextView) findViewById(R.id.win_text_value);
        TextView losses = (TextView) findViewById(R.id.loss_text_value);
        TextView clanName = (TextView) findViewById(R.id.clan_name);
        TextView createdText = (TextView) findViewById(R.id.created_text);
        TextView loginText = (TextView) findViewById(R.id.login_text);
        TextView playerStatus = (TextView) findViewById(R.id.player_status_value);
        TextView level = (TextView) findViewById(R.id.level_value);
        TextView masteryLevel = (TextView) findViewById(R.id.mastery_level_value);
        TextView leaves = (TextView) findViewById(R.id.leaves_value);
        String player = name;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            while(master.getSessionId() == null)
            {

            }
            System.out.println(master.testSession());
            list = master.getPlayer(player);
            statusList = master.getPlayerStatus(player);



            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            findViewById(R.id.player_lookup_loading_panel).setVisibility(View.GONE);
            if(list.size() == 0 || list.get(0).getRet_msg() != null)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(thisRef);
                builder.setMessage("Error with server")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
            else
            {
                nameTextView.setText(list.get(0).getName());

                //Checks if player is in clan or not
                if(list.get(0).getTeam_Name()!= "") {
                    clanName.setText(list.get(0).getTeam_Name());
                }else{
                    clanName.setVisibility(View.INVISIBLE);
                }

                createdText.setText("Created at: " + list.get(0).getCreated_Datetime());
                loginText.setText("Last login: " + list.get(0).getLast_Login_Datetime());
                wins.setText(String.valueOf(list.get(0).getWins()));
                losses.setText(String.valueOf(list.get(0).getLosses()));
                playerStatus.setText(statusList.get(0).getStatus_string());
                level.setText(String.valueOf(list.get(0).getLevel()));
                masteryLevel.setText(String.valueOf(list.get(0).getMasteryLevel()));
                leaves.setText(String.valueOf(list.get(0).getLeaves()));
                createBottomBar();

            }
        }



    }

    private void createBottomBar(){
        AHBottomNavigation bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);

        // Create items
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.tab_0, R.drawable.notification_background, R.color.colorPrimary);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.tab_1, R.drawable.notification_background, R.color.colorAccent);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.tab_2, R.drawable.notification_background, R.color.colorPrimaryDark);

        TextView rankedText = (TextView) findViewById(R.id.ranked_conquest_text);
        int tier = list.get(0).getRankedConquest().getTier();
        String tierText = "";
        if(tier == 0)
        {
            tierText = "Unranked";
        }
        else if(tier <= 5)
        {
            tierText = "Bronze " + (6-tier);
        }
        else if(tier <= 10)
        {
            tierText = "Silver " + (11-tier);
        }
        else if(tier <= 15)
        {
            tierText = "Gold " + (16-tier);
        }
        else if(tier <= 20)
        {
            tierText = "Platinum " + (21-tier);
        }
        else if(tier <= 25)
        {
            tierText = "Diamond " + (26-tier);
        }
        else
        {
            tierText = "Grandmaster";
        }
        rankedText.setText("");
        rankedText.append("Ranked Conquest Stats:" + "\n");
        rankedText.append(tierText + "\n");
        rankedText.append("Current TP: " + list.get(0).getRankedConquest().getPoints() + "\n");
        rankedText.append("Wins/Losses: " + list.get(0).getRankedConquest().getWins() + "/" + list.get(0).getRankedConquest().getLosses() + "\n");
        rankedText.append("Leaves: " + list.get(0).getRankedConquest().getLeaves());

        //stats for joust
        TextView joustText = (TextView) findViewById(R.id.ranked_joust_text);
        tier = list.get(0).getRankedJoust().getTier();
        if(tier == 0)
        {
            tierText = "Unranked";
        }
        else if(tier <= 5)
        {
            tierText = "Bronze " + (6-tier);
        }
        else if(tier <= 10)
        {
            tierText = "Silver " + (11-tier);
        }
        else if(tier <= 15)
        {
            tierText = "Gold " + (16-tier);
        }
        else if(tier <= 20)
        {
            tierText = "Platinum " + (21-tier);
        }
        else if(tier <= 25)
        {
            tierText = "Diamond " + (26-tier);
        }
        else
        {
            tierText = "Grandmaster";
        }
        joustText.setText("");
        joustText.append("Ranked Joust Stats: " + "\n");
        joustText.append(tierText + "\n");
        joustText.append("Current TP: " + list.get(0).getRankedJoust().getPoints() + "\n");
        joustText.append("Wins/Losses: " + list.get(0).getRankedJoust().getWins() + "/" + list.get(0).getRankedJoust().getLosses() + "\n");
        joustText.append("Leaves: " + list.get(0).getRankedJoust().getLeaves());

        //duel stats
        TextView duelText = (TextView) findViewById(R.id.ranked_duel_text);
        tier = list.get(0).getRankedDuel().getTier();
        if(tier == 0)
        {
            tierText = "Unranked";
        }
        else if(tier <= 5)
        {
            tierText = "Bronze " + (6-tier);
        }
        else if(tier <= 10)
        {
            tierText = "Silver " + (11-tier);
        }
        else if(tier <= 15)
        {
            tierText = "Gold " + (16-tier);
        }
        else if(tier <= 20)
        {
            tierText = "Platinum " + (21-tier);
        }
        else if(tier <= 25)
        {
            tierText = "Diamond " + (26-tier);
        }
        else
        {
            tierText = "Grandmaster";
        }
        duelText.setText("");
        duelText.append("Ranked Duel Stats: " + "\n");
        duelText.append(tierText + "\n");
        duelText.append("Current TP: " + list.get(0).getRankedDuel().getPoints() + "\n");
        duelText.append("Wins/Losses: " + list.get(0).getRankedDuel().getWins() + "/" + list.get(0).getRankedDuel().getLosses() + "\n");
        duelText.append("Leaves: " + list.get(0).getRankedDuel().getLeaves());

// Add items
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);

// Set background color
        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#FEFEFE"));

// Disable the translation inside the CoordinatorLayout
        bottomNavigation.setBehaviorTranslationEnabled(false);

// Change colors
        bottomNavigation.setAccentColor(Color.parseColor("#F63D2B"));
        bottomNavigation.setInactiveColor(Color.parseColor("#747474"));

// Force to tint the drawable (useful for font with icon for example)
        bottomNavigation.setForceTint(true);

// Force the titles to be displayed (against Material Design guidelines!)
        bottomNavigation.setForceTitlesDisplay(true);

// Use colored navigation with circle reveal effect
        bottomNavigation.setColored(true);

// Set current item programmatically
        bottomNavigation.setCurrentItem(0);

// Customize notification (title, background, typeface)
        bottomNavigation.setNotificationBackgroundColor(Color.parseColor("#F63D2B"));

// Add or remove notification for each item
        bottomNavigation.setNotification(4, 0);
        bottomNavigation.setNotification(0, 0);

// Set listener
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, boolean wasSelected) {
                if(position == 0){

                    getSupportFragmentManager().beginTransaction()
                            .hide(duelFragment)
                            .hide(joustFragment)
                            .show(conquestFragment)
                            .commit();
                }

                if(position == 1){
                    getSupportFragmentManager().beginTransaction()
                            .hide(conquestFragment)
                            .hide(duelFragment)
                            .show(joustFragment)
                            .commit();
                }

                if(position == 2){
                    getSupportFragmentManager().beginTransaction()
                            .hide(conquestFragment)
                            .hide(joustFragment)
                            .show(duelFragment)
                            .commit();
                }
            }
        });
    }

}
