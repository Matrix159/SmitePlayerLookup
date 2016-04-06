package edu.gvsu.cis.smiteplayerlookup;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GodActivity extends AppCompatActivity {

    private ImageSaver imageSaver;
    private ImageView imageView, statsImage, ability1Image;
    private TextView godName, race, role;
    private Button wikiButton;
    private Intent intent;
    private RelativeLayout statsLayout, innerStatsLayout, ability1Layout, innerAbility1Layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_god);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        intent = this.getIntent();
        godName = (TextView) findViewById(R.id.god_name);
        race = (TextView) findViewById(R.id.race);
        role = (TextView) findViewById(R.id.role);
        wikiButton = (Button) findViewById(R.id.wiki_button);
        statsLayout = (RelativeLayout) findViewById(R.id.stats_layout);
        innerStatsLayout = (RelativeLayout) findViewById(R.id.inner_stats_layout);
        ability1Layout = (RelativeLayout) findViewById(R.id.ability1_layout);
        innerAbility1Layout = (RelativeLayout) findViewById(R.id.inner_ability1_layout);
        godName.setText(" " + intent.getStringExtra("godName"));
        race.setText(" " + intent.getStringExtra("pantheon") + ": " + intent.getStringExtra("title"));
        role.setText(intent.getStringExtra("role") + " " + "(" + intent.getStringExtra("type") + " )");
        imageSaver = new ImageSaver(this);
        imageView = (ImageView) findViewById(R.id.god_icon);
        statsImage = (ImageView) findViewById(R.id.stats_image);
        ability1Image = (ImageView) findViewById(R.id.ability1_image);
        imageView.setImageBitmap(imageSaver.setFileName(intent.getStringExtra("godName")).setDirectoryName("images").load());
        statsImage.setImageBitmap(imageSaver.setFileName(intent.getStringExtra("godName")).setDirectoryName("images").load());
        ability1Image.setImageBitmap(imageSaver.setFileName(intent.getStringExtra("godName")).setDirectoryName("images").load());
    }


    public void startWiki(View v)
    {
        Intent i = new Intent();
        i.setAction(Intent.ACTION_VIEW);
        i.addCategory(Intent.CATEGORY_BROWSABLE);
        i.setData(Uri.parse("http://smite.gamepedia.com/" + intent.getStringExtra("godName")));
        startActivity(i);
    }

    public void hide(View v)
    {
        if(v.equals(statsLayout))
        {
            if(innerStatsLayout.getVisibility() == View.VISIBLE)
            {
                innerStatsLayout.setVisibility(View.GONE);
            }
            else
                innerStatsLayout.setVisibility(View.VISIBLE);
        }
        if(v.equals(ability1Layout))
        {
            if(innerAbility1Layout.getVisibility() == View.VISIBLE)
                innerAbility1Layout.setVisibility(View.GONE);
            else
                innerAbility1Layout.setVisibility(View.VISIBLE);
        }
    }
}
