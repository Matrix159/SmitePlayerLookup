package edu.gvsu.cis.smiteplayerlookup;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.nightonke.boommenu.BoomMenuButton;

import org.w3c.dom.Text;

import java.util.List;

import edu.gvsu.cis.smitedataretrieval.SmiteMaster;
import edu.gvsu.cis.smitedataretrieval.playerinfo.PlayerInfo;
import edu.gvsu.cis.smitedataretrieval.playerinfo.PlayerStatus;

public class PlayerLookupActivity extends AppCompatActivity{

    private BoomMenuButton boomButtonActionBar;
    private String[] Colors;
    private SmiteMaster master;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_lookup);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        master = new SmiteMaster(this);
        Intent intent = this.getIntent();
        name = intent.getStringExtra("playerName");

        new AsynchCaller().execute();
    }

    private class AsynchCaller extends AsyncTask<Void, Void, Void>
    {
        List<PlayerInfo> list;
        List<PlayerStatus> statusList;
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
            list = master.getPlayer(player);
            statusList = master.getPlayerStatus(player);



            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            findViewById(R.id.loadingPanel).setVisibility(View.GONE);
            if(list.size() == 0)
            {
                finish();
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

            }
        }


    }

}
