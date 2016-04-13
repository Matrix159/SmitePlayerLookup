package edu.gvsu.cis.smiteplayerlookup;

import android.animation.Animator;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.List;

import edu.gvsu.cis.smitedataretrieval.godinfo.GodInfo;

public class GodActivity extends AppCompatActivity {

    private ImageSaver imageSaver;
    private ImageView godImage, statsImage, abilityPassiveImage, ability1Image,
            ability2Image, ability3Image, ability4Image;
    private TextView godName, race, role, statsText, statsHeaderText, abilityPassiveText,
            abilityPassiveHeaderText, ability1Text, ability1HeaderText, ability2Text, ability2HeaderText,
            ability3Text, ability3HeaderText, ability4Text, ability4HeaderText;
    private Button wikiButton;
    private Intent intent;
    private RelativeLayout statsLayout, innerStatsLayout, abilityPassiveLayout,
            innerAbilityPassiveLayout, ability1Layout, innerAbility1Layout,
            ability2Layout, innerAbility2Layout, ability3Layout, innerAbility3Layout, ability4Layout, innerAbility4Layout;
    private List<GodInfo> godList = GodListActivity.godList;
    private ScrollView scroller;
    private int maxHeight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_god);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        intent = this.getIntent();
        scroller = (ScrollView) findViewById(R.id.godScroller);
        maxHeight = this.getResources().getDisplayMetrics().heightPixels;
        int position = intent.getIntExtra("position", 0);
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
        statsHeaderText = (TextView) findViewById(R.id.stats_header_text);
        abilityPassiveText = (TextView) findViewById(R.id.passive_text);
        abilityPassiveHeaderText = (TextView) findViewById(R.id.abilitypassive_header_text);

        ability1Text = (TextView) findViewById(R.id.ability1_text);
        ability2Text = (TextView) findViewById(R.id.ability2_text);
        ability3Text = (TextView) findViewById(R.id.ability3_text);
        ability4Text = (TextView) findViewById(R.id.ability4_text);

        statsText.setText("To be filled");
        abilityPassiveHeaderText.append(" " + godList.get(position).getAbility5());
        abilityPassiveText.setText("Passive information here");
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
        if(imageSaver.setFileName(intent.getStringExtra("godID")).setDirectoryName("images").load() == null)
            godImage.setImageBitmap(BitmapFactory.decodeResource(this.getResources(),
                    R.drawable.placeholder));
        else
            godImage.setImageBitmap(imageSaver.setFileName(intent.getStringExtra("godID")).setDirectoryName("images").load());


        statsImage.setImageBitmap(BitmapFactory.decodeResource(this.getResources(),
                R.drawable.placeholder));
        if(imageSaver.setFileName(intent.getStringExtra("ability5ID")).setDirectoryName("images").load() == null)
            abilityPassiveImage.setImageBitmap((BitmapFactory.decodeResource(this.getResources(),
                    R.drawable.placeholder)));
        else
        abilityPassiveImage.setImageBitmap(imageSaver.setFileName(intent.getStringExtra("ability5ID")).setDirectoryName("images").load());

        if(imageSaver.setFileName(intent.getStringExtra("ability1ID")).setDirectoryName("images").load() == null)
            ability1Image.setImageBitmap(BitmapFactory.decodeResource(this.getResources(),
                    R.drawable.placeholder));
        else
            ability1Image.setImageBitmap(imageSaver.setFileName(intent.getStringExtra("ability1ID")).setDirectoryName("images").load());
        ability2Image.setImageBitmap(imageSaver.setFileName(intent.getStringExtra("ability2ID")).setDirectoryName("images").load());
        ability3Image.setImageBitmap(imageSaver.setFileName(intent.getStringExtra("ability3ID")).setDirectoryName("images").load());
        ability4Image.setImageBitmap(imageSaver.setFileName(intent.getStringExtra("ability4ID")).setDirectoryName("images").load());
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

        if(v.equals(abilityPassiveLayout))
        {
            if(innerAbilityPassiveLayout.getVisibility() == View.VISIBLE)
            {
                innerAbilityPassiveLayout.animate().alpha(0.0f).setDuration(500).setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        innerAbilityPassiveLayout.setVisibility(View.GONE);
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
                innerAbilityPassiveLayout.setVisibility(View.VISIBLE);
                innerAbilityPassiveLayout.animate().alpha(1.0f).setDuration(500).setListener(new Animator.AnimatorListener() {
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
            {
                innerAbility1Layout.animate().alpha(0.0f).setDuration(500).setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        innerAbility1Layout.setVisibility(View.GONE);
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
                innerAbility1Layout.setVisibility(View.VISIBLE);
                innerAbility1Layout.animate().alpha(1.0f).setDuration(500).setListener(new Animator.AnimatorListener() {
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
        if(v.equals(ability2Layout))
        {
            if(innerAbility2Layout.getVisibility() == View.VISIBLE)
            {
                innerAbility2Layout.animate().alpha(0.0f).setDuration(500).setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        innerAbility2Layout.setVisibility(View.GONE);
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
                innerAbility2Layout.setVisibility(View.VISIBLE);
                innerAbility2Layout.animate().alpha(1.0f).setDuration(500).setListener(new Animator.AnimatorListener() {
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

        if(v.equals(ability3Layout))
        {
            if(innerAbility3Layout.getVisibility() == View.VISIBLE)
            {
                innerAbility3Layout.animate().alpha(0.0f).setDuration(500).setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        innerAbility3Layout.setVisibility(View.GONE);
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
                innerAbility3Layout.setVisibility(View.VISIBLE);
                innerAbility3Layout.animate().alpha(1.0f).setDuration(500).setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if(ability3Layout.getBottom() > scroller.getHeight())
                            scroller.scrollTo(ability3Layout.getScrollX(), ability3Layout.getBottom());
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

        if(v.equals(ability4Layout))
        {
            if(innerAbility4Layout.getVisibility() == View.VISIBLE)
            {
                innerAbility4Layout.animate().alpha(0.0f).setDuration(500).setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        innerAbility4Layout.setVisibility(View.GONE);
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
                innerAbility4Layout.setVisibility(View.VISIBLE);
                innerAbility4Layout.animate().alpha(1.0f).setDuration(250).setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if(ability4Layout.getBottom() > scroller.getHeight())
                        scroller.scrollTo(ability4Layout.getScrollX(), ability4Layout.getBottom());
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
    }

}
