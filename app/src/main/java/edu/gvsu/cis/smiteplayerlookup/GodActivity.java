package edu.gvsu.cis.smiteplayerlookup;

import android.animation.Animator;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class GodActivity extends AppCompatActivity {

    private ImageSaver imageSaver;
    private ImageView godImage, statsImage, abilityPassiveImage, ability1Image, ability2Image, ability3Image, ability4Image;
    private TextView godName, race, role, statsText, abilityPassiveText, ability1Text, ability2Text, ability3Text, ability4Text;
    private Button wikiButton;
    private Intent intent;
    private RelativeLayout statsLayout, innerStatsLayout, abilityPassiveLayout, innerAbilityPassiveLayout, ability1Layout, innerAbility1Layout,
            ability2Layout, innerAbility2Layout, ability3Layout, innerAbility3Layout, ability4Layout, innerAbility4Layout;

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
        abilityPassiveLayout = (RelativeLayout) findViewById(R.id.ability_passive_layout);
        innerAbilityPassiveLayout = (RelativeLayout) findViewById(R.id.inner_ability_passive_layout);
        ability1Layout = (RelativeLayout) findViewById(R.id.ability1_layout);
        innerAbility1Layout = (RelativeLayout) findViewById(R.id.inner_ability1_layout);
        ability2Layout = (RelativeLayout) findViewById(R.id.abillity2_layout);
        innerAbility2Layout = (RelativeLayout) findViewById(R.id.inner_ability2_layout);
        ability3Layout = (RelativeLayout) findViewById(R.id.ability3_layout);
        innerAbility3Layout = (RelativeLayout) findViewById(R.id.inner_ability3_layout);
        ability4Layout = (RelativeLayout) findViewById(R.id.ability4_layout);
        innerAbility4Layout = (RelativeLayout) findViewById(R.id.inner_ability4_layout);
        statsText = (TextView) findViewById(R.id.stats_text);
        abilityPassiveText = (TextView) findViewById(R.id.passive_text);
        ability1Text = (TextView) findViewById(R.id.ability1_text);
        ability2Text = (TextView) findViewById(R.id.ability2_text);
        ability3Text = (TextView) findViewById(R.id.ability3_text);
        ability4Text = (TextView) findViewById(R.id.ability4_text);
        statsText.setText("To be filled");
        abilityPassiveText.setText("");
        godName.setText(" " + intent.getStringExtra("godName"));
        race.setText(" " + intent.getStringExtra("pantheon") + ": " + intent.getStringExtra("title"));
        role.setText(intent.getStringExtra("role") + " " + "(" + intent.getStringExtra("type") + " )");
        imageSaver = new ImageSaver(this);
        godImage = (ImageView) findViewById(R.id.god_icon);
        statsImage = (ImageView) findViewById(R.id.stats_image);
        abilityPassiveImage = (ImageView) findViewById(R.id.ability_passive_image);
        ability1Image = (ImageView) findViewById(R.id.ability1_image);
        ability2Image = (ImageView) findViewById(R.id.ability2_image);
        ability3Image = (ImageView) findViewById(R.id.ability3_image);
        ability4Image = (ImageView) findViewById(R.id.ability4_image);
        godImage.setImageBitmap(imageSaver.setFileName(intent.getStringExtra("godName")).setDirectoryName("images").load());
        statsImage.setImageBitmap(imageSaver.setFileName(intent.getStringExtra("godName") + "ability1").setDirectoryName("images").load());
        abilityPassiveImage.setImageBitmap(imageSaver.setFileName(intent.getStringExtra("godName") + "ability5").setDirectoryName("images").load());
        ability1Image.setImageBitmap(imageSaver.setFileName(intent.getStringExtra("godName") + "ability1").setDirectoryName("images").load());
        ability2Image.setImageBitmap(imageSaver.setFileName(intent.getStringExtra("godName") + "ability2").setDirectoryName("images").load());
        ability3Image.setImageBitmap(imageSaver.setFileName(intent.getStringExtra("godName") + "ability3").setDirectoryName("images").load());
        ability4Image.setImageBitmap(imageSaver.setFileName(intent.getStringExtra("godName") + "ability4").setDirectoryName("images").load());
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
                innerStatsLayout.animate().alpha(0.0f).setDuration(500).setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        innerStatsLayout.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });

            }
            else
            {
                innerStatsLayout.setVisibility(View.VISIBLE);
                innerStatsLayout.animate().alpha(1.0f).setDuration(500).setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });

            }

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
