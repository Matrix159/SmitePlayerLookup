package edu.gvsu.cis.eldridjo.smiteplayerlookup;

import android.animation.Animator;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import edu.gvsu.cis.eldridjo.smitedataretrieval.godinfo.GodInfo;
import edu.gvsu.cis.eldridjo.smitedataretrieval.godinfo.Menuitem;
import edu.gvsu.cis.eldridjo.smitedataretrieval.godinfo.Rankitem;

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
    private GodInfo currentGod;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_god);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        intent = this.getIntent();
        scroller = (ScrollView) findViewById(R.id.godScroller);

        int position = intent.getIntExtra("position", 0);
        currentGod = GodListActivity.godList.get(position);

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


        statsHeaderText = (TextView) findViewById(R.id.stats_header_text);
        abilityPassiveHeaderText = (TextView) findViewById(R.id.abilitypassive_header_text);
        ability1HeaderText = (TextView) findViewById(R.id.ability1_header_text);
        ability1HeaderText.append(godList.get(position).getAbility1());
        ability2HeaderText = (TextView) findViewById(R.id.ability2_header_text);
        ability2HeaderText.append(godList.get(position).getAbility2());
        ability3HeaderText = (TextView) findViewById(R.id.ability3_header_text);
        ability3HeaderText.append(godList.get(position).getAbility3());
        ability4HeaderText = (TextView) findViewById(R.id.ability4_header_text);
        ability4HeaderText.append(godList.get(position).getAbility4());

        String bold = "Pros: ";
        SpannableString span = new SpannableString(bold + godList.get(position).getPros() + "\n");
        span.setSpan(new StyleSpan(Typeface.BOLD), 0, bold.length(), 0);
        statsText = (TextView) findViewById(R.id.stats_text);
        statsText.append(span);
        bold = "Cons: ";
        span = new SpannableString(bold + godList.get(position).getCons() + "\n");
        span.setSpan(new StyleSpan(Typeface.BOLD), 0 ,bold.length(), 0);
        statsText.append(span);
        bold = "Movement Speed: ";
        span = new SpannableString(bold + godList.get(position).getSpeed() + "\n\n");
        span.setSpan(new StyleSpan(Typeface.BOLD), 0 ,bold.length(), 0);
        statsText.append(span);
        bold = "Health: ";
        span = new SpannableString(bold + godList.get(position).getHealth() +" (+" + godList.get(position).getHealthPerLevel() + " per level)\n");
        span.setSpan(new StyleSpan(Typeface.BOLD), 0 ,bold.length(), 0);
        statsText.append(span);
        bold = "Mana: ";
        span = new SpannableString(bold + godList.get(position).getMana() +" (+" + godList.get(position).getManaPerLevel() + " per level)\n\n");
        span.setSpan(new StyleSpan(Typeface.BOLD), 0 ,bold.length(), 0);
        statsText.append(span);
        bold = "HP5: ";
        span = new SpannableString(bold + godList.get(position).getHealthPerFive() +" (+" + godList.get(position).getHP5PerLevel() + " per level)\n");
        span.setSpan(new StyleSpan(Typeface.BOLD), 0 ,bold.length(), 0);
        statsText.append(span);
        bold = "MP5: ";
        span = new SpannableString(bold + godList.get(position).getManaPerFive() +" (+" + godList.get(position).getMP5PerLevel() + " per level)\n\n");
        span.setSpan(new StyleSpan(Typeface.BOLD), 0 ,bold.length(), 0);
        statsText.append(span);
        bold = "Physical Protection: ";
        span = new SpannableString(bold + godList.get(position).getPhysicalProtection() +" (+" + godList.get(position).getPhysicalProtectionPerLevel() + " per level)\n");
        span.setSpan(new StyleSpan(Typeface.BOLD), 0 ,bold.length(), 0);
        statsText.append(span);
        bold = "Magical Protection: ";
        span = new SpannableString(bold + godList.get(position).getMagicProtection() +" (+" + godList.get(position).getMagicProtectionPerLevel() + " per level)\n\n");
        span.setSpan(new StyleSpan(Typeface.BOLD), 0 ,bold.length(), 0);
        statsText.append(span);
        if(godList.get(position).getType().contains("Physical"))
        {
            bold = "Physical Power: ";
            span = new SpannableString(bold + godList.get(position).getPhysicalPower() +" (+" + godList.get(position).getPhysicalPowerPerLevel() + " per level)\n\n");
            span.setSpan(new StyleSpan(Typeface.BOLD), 0 ,bold.length(), 0);
            statsText.append(span);
        }
        else
        {
            bold = "Magical Power: ";
            span = new SpannableString(bold + godList.get(position).getMagicalPower() +" (+" + godList.get(position).getMagicalPowerPerLevel() + " per level)\n\n");
            span.setSpan(new StyleSpan(Typeface.BOLD), 0 ,bold.length(), 0);

            statsText.append(span);
        }
        bold = "Basic Attacks:\n";
        span = new SpannableString(bold);
        span.setSpan(new StyleSpan(Typeface.BOLD), 0 ,bold.length(), 0);
        span.setSpan(new UnderlineSpan(), 0 ,bold.length(), 0);
        statsText.append(span);
        if(godList.get(position).getBasicAttack().getItemDescription().getMenuitems() != null)
        {
            for (Menuitem m : godList.get(position).getBasicAttack().getItemDescription().getMenuitems())
            {
                String bold2 = m.getDescription();
                SpannableString span2 = new SpannableString(bold2);
                span2.setSpan(new StyleSpan(Typeface.BOLD), 0 ,bold2.length(), 0);
                statsText.append(span2);
                statsText.append(" " + m.getValue() + "\n");
            }
        }
        if(godList.get(position).getBasicAttack().getItemDescription().getRankitems() != null)
        {
            for (Rankitem r : godList.get(position).getBasicAttack().getItemDescription().getRankitems())
            {
                String bold2 = r.getDescription();
                SpannableString span2 = new SpannableString(bold2);
                span2.setSpan(new StyleSpan(Typeface.BOLD), 0 ,bold2.length(), 0);
                statsText.append(span2);
                statsText.append(" " + r.getValue().replaceFirst("pexistspprop[^\\/]*\\/[0-9.]*\\/\\+#\\/", "") + "\n");
            }
        }
        bold = "Attack Speed: ";
        span = new SpannableString(bold + godList.get(position).getAttackSpeed() +" (+" + godList.get(position).getAttackSpeedPerLevel()+ " per level)\n");
        span.setSpan(new StyleSpan(Typeface.BOLD), 0 ,bold.length(), 0);

        statsText.append(span);



        abilityPassiveText = (TextView) findViewById(R.id.passive_text);
        System.out.println(position);
        if(godList.get(position).getAbilityDescription5().getItemDescription().getMenuitems() != null)
        {
            for (Menuitem m : godList.get(position).getAbilityDescription5().getItemDescription().getMenuitems())
            {
                    abilityPassiveText.append(m.getDescription() + " " + m.getValue() + "\n");
            }
        }
        if(godList.get(position).getAbilityDescription5().getItemDescription().getRankitems() != null)
        {
            for (Rankitem r : godList.get(position).getAbilityDescription5().getItemDescription().getRankitems())
            {
                abilityPassiveText.append("\n" + r.getDescription() + " " + r.getValue().replaceFirst("pexistspprop[^\\/]*\\/[0-9.]*\\/\\+#\\/", "") + "\n");
            }
        }
        abilityPassiveText.append("\n" + godList.get(position).getAbilityDescription5().getItemDescription().getDescription() + "\n");

        ability1Text = (TextView) findViewById(R.id.ability1_text);
        for(Menuitem m: godList.get(position).getAbilityDescription1().getItemDescription().getMenuitems())
        {
            ability1Text.append(m.getDescription() + " " + m.getValue() + "\n");
        }
        for(Rankitem r: godList.get(position).getAbilityDescription1().getItemDescription().getRankitems())
        {
            ability1Text.append("\n" + r.getDescription() + " " + r.getValue() + "\n");
        }
        ability1Text.append("\n" + godList.get(position).getAbilityDescription1().getItemDescription().getDescription() + "\n");
        ability1Text.append("\n" + "Cost: " + godList.get(position).getAbilityDescription1().getItemDescription().getCost() + "\n");
        ability1Text.append("Cooldown: " + godList.get(position).getAbilityDescription1().getItemDescription().getCooldown() + "\n");

        ability2Text = (TextView) findViewById(R.id.ability2_text);
        for(Menuitem m: godList.get(position).getAbilityDescription2().getItemDescription().getMenuitems())
        {
            ability2Text.append(m.getDescription() + " " + m.getValue() + "\n");
        }
        for(Rankitem r: godList.get(position).getAbilityDescription2().getItemDescription().getRankitems())
        {
            ability2Text.append("\n" + r.getDescription() + " " + r.getValue() + "\n");
        }
        ability2Text.append("\n" + godList.get(position).getAbilityDescription2().getItemDescription().getDescription() + "\n");
        ability2Text.append("\n" + "Cost: " + godList.get(position).getAbilityDescription2().getItemDescription().getCost() + "\n");
        ability2Text.append("Cooldown: " + godList.get(position).getAbilityDescription2().getItemDescription().getCooldown() + "\n");

        ability3Text = (TextView) findViewById(R.id.ability3_text);
        for(Menuitem m: godList.get(position).getAbilityDescription3().getItemDescription().getMenuitems())
        {
            ability3Text.append(m.getDescription() + " " + m.getValue() + "\n");
        }
        for(Rankitem r: godList.get(position).getAbilityDescription3().getItemDescription().getRankitems())
        {
            ability3Text.append("\n" + r.getDescription() + " " + r.getValue() + "\n");
        }
        ability3Text.append("\n" + godList.get(position).getAbilityDescription3().getItemDescription().getDescription() + "\n");
        ability3Text.append("\n" + "Cost: " + godList.get(position).getAbilityDescription3().getItemDescription().getCost() + "\n");
        ability3Text.append("Cooldown: " + godList.get(position).getAbilityDescription3().getItemDescription().getCooldown() + "\n");

        ability4Text = (TextView) findViewById(R.id.ability4_text);
        for(Menuitem m: godList.get(position).getAbilityDescription4().getItemDescription().getMenuitems())
        {
            ability4Text.append(m.getDescription() + " " + m.getValue() + "\n");
        }
        for(Rankitem r: godList.get(position).getAbilityDescription4().getItemDescription().getRankitems())
        {
            ability4Text.append("\n" + r.getDescription() + " " + r.getValue() + "\n");
        }
        ability4Text.append("\n" + godList.get(position).getAbilityDescription4().getItemDescription().getDescription() + "\n");
        ability4Text.append("\n" + "Cost: " + godList.get(position).getAbilityDescription4().getItemDescription().getCost() + "\n");
        ability4Text.append("Cooldown: " + godList.get(position).getAbilityDescription4().getItemDescription().getCooldown() + "\n");

        abilityPassiveHeaderText.append(" " + godList.get(position).getAbility5());
        godName.setText(" " + currentGod.getName());
        race.setText(" " + currentGod.getPantheon() + ": " + currentGod.getTitle());
        role.setText(currentGod.getRoles() + " " + "(" + currentGod.getType() + " )");
        imageSaver = new ImageSaver(this);
        godImage = (ImageView) findViewById(R.id.god_icon);
        statsImage = (ImageView) findViewById(R.id.stats_image);
        abilityPassiveImage = (ImageView) findViewById(R.id.ability_passive_image);
        ability1Image = (ImageView) findViewById(R.id.ability1_image);
        ability2Image = (ImageView) findViewById(R.id.ability2_image);
        ability3Image = (ImageView) findViewById(R.id.ability3_image);
        ability4Image = (ImageView) findViewById(R.id.ability4_image);

        String placeholderURI = "@drawable/placeholder";
        String godURI = "@drawable/i" + currentGod.getId();  // where myresource (without the extension) is the file
        int godIcon = getResources().getIdentifier(godURI, null, getPackageName());
        if(godIcon == 0)
        {
            godIcon = getResources().getIdentifier(placeholderURI, null, getPackageName());
        }
        Picasso.with(this).load(godIcon)
                .error(R.drawable.placeholder)
                .into(godImage);



        String godAbility1URI = "@drawable/i" + currentGod.getAbilityId1();  // where myresource (without the extension) is the file
        int godAbility1Icon= getResources().getIdentifier(godAbility1URI, null, getPackageName());
        if(godAbility1Icon == 0)
        {
            godAbility1Icon = getResources().getIdentifier(placeholderURI, null, getPackageName());
        }
        Picasso.with(this).load(godAbility1Icon)
                .error(R.drawable.placeholder)
                .into(ability1Image);;

        String godAbility2URI = "@drawable/i" + currentGod.getAbilityId2();  // where myresource (without the extension) is the file
        int godAbility2Icon= getResources().getIdentifier(godAbility2URI, null, getPackageName());
        if(godAbility2Icon == 0)
        {
            godAbility2Icon = getResources().getIdentifier(placeholderURI, null, getPackageName());
        }
        Picasso.with(this).load(godAbility2Icon)
                .error(R.drawable.placeholder)
                .into(ability2Image);

        String godAbility3URI = "@drawable/i" + currentGod.getAbilityId3();  // where myresource (without the extension) is the file
        int godAbility3Icon= getResources().getIdentifier(godAbility3URI, null, getPackageName());
        if(godAbility3Icon == 0)
        {
            godAbility3Icon = getResources().getIdentifier(placeholderURI, null, getPackageName());
        }
        Picasso.with(this).load(godAbility3Icon)
                .error(R.drawable.placeholder)
                .into(ability3Image);

        String godAbility4URI = "@drawable/i" + currentGod.getAbilityId4();  // where myresource (without the extension) is the file
        int godAbility4Icon= getResources().getIdentifier(godAbility4URI, null, getPackageName());
        if(godAbility4Icon == 0)
        {
            godAbility4Icon = getResources().getIdentifier(placeholderURI, null, getPackageName());
        }
        Picasso.with(this).load(godAbility4Icon)
                .error(R.drawable.placeholder)
                .into(ability4Image);

        String godAbility5URI = "@drawable/i" + currentGod.getAbilityId5();  // where myresource (without the extension) is the file
        int godAbility5Icon= getResources().getIdentifier(godAbility5URI, null, getPackageName());
        if(godAbility5Icon == 0)
        {
            godAbility5Icon= getResources().getIdentifier(placeholderURI, null, getPackageName());
        }

        Picasso.with(this).load(godAbility5Icon)
                .error(R.drawable.placeholder)
                .into(abilityPassiveImage);


        /*if(imageSaver.setFileName(String.valueOf(currentGod.getId())).setDirectoryName("images").load() == null)
            godImage.setImageBitmap(BitmapFactory.decodeResource(this.getResources(),
                    R.drawable.placeholder));
        else
            godImage.setImageBitmap(imageSaver.setFileName(String.valueOf(currentGod.getId())).setDirectoryName("images").load());


        statsImage.setImageBitmap(BitmapFactory.decodeResource(this.getResources(),
                R.drawable.placeholder));
        if(imageSaver.setFileName(String.valueOf(currentGod.getAbilityId5())).setDirectoryName("images").load() == null)
            abilityPassiveImage.setImageBitmap((BitmapFactory.decodeResource(this.getResources(),
                    R.drawable.placeholder)));
        else
        abilityPassiveImage.setImageBitmap(imageSaver.setFileName(String.valueOf(currentGod.getAbilityId5())).setDirectoryName("images").load());

        if(imageSaver.setFileName(String.valueOf(currentGod.getAbilityId1())).setDirectoryName("images").load() == null)
            ability1Image.setImageBitmap(BitmapFactory.decodeResource(this.getResources(),
                    R.drawable.placeholder));
        else
            ability1Image.setImageBitmap(imageSaver.setFileName(String.valueOf(currentGod.getAbilityId1())).setDirectoryName("images").load());

        if(imageSaver.setFileName(String.valueOf(currentGod.getAbilityId2())).setDirectoryName("images").load() == null)
            ability2Image.setImageBitmap(BitmapFactory.decodeResource(this.getResources(),
                    R.drawable.placeholder));
        else
            ability2Image.setImageBitmap(imageSaver.setFileName(String.valueOf(currentGod.getAbilityId2())).setDirectoryName("images").load());

        if(imageSaver.setFileName(String.valueOf(currentGod.getAbilityId3())).setDirectoryName("images").load() == null)
            ability3Image.setImageBitmap(BitmapFactory.decodeResource(this.getResources(),
                    R.drawable.placeholder));
        else
            ability3Image.setImageBitmap(imageSaver.setFileName(String.valueOf(currentGod.getAbilityId3())).setDirectoryName("images").load());

        if(imageSaver.setFileName(String.valueOf(currentGod.getAbilityId4())).setDirectoryName("images").load() == null)
            ability4Image.setImageBitmap(BitmapFactory.decodeResource(this.getResources(),
                    R.drawable.placeholder));
        else
            ability4Image.setImageBitmap(imageSaver.setFileName(String.valueOf(currentGod.getAbilityId4())).setDirectoryName("images").load());
            */
    }


    public void startWiki(View v)
    {
        Intent i = new Intent();
        i.setAction(Intent.ACTION_VIEW);
        i.addCategory(Intent.CATEGORY_BROWSABLE);
        i.setData(Uri.parse("http://smite.gamepedia.com/" + currentGod.getName()));
        startActivity(i);
    }

    public void showLore(View v)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.lore_theme);

// set title
        builder.setTitle("Lore");

// set dialog message
        String message =currentGod.getLore().replaceAll("\\\\n", "\n");
        builder.setMessage(message).setCancelable(true);

// create alert dialog
        AlertDialog alertDialog = builder.create();

// show it
        alertDialog.show();
    }

    public void hide(View v)
    {
        if(v.equals(statsLayout))
        {
            if(innerStatsLayout.getVisibility() == View.VISIBLE)
            {
                innerStatsLayout.animate().alpha(0.0f).setDuration(100).setListener(new Animator.AnimatorListener() {
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
                innerStatsLayout.animate().alpha(1.0f).setDuration(100).setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if ((statsLayout.getBottom()) - scroller.getScrollY() > scroller.getHeight()) {
                            scroller.scrollTo(0, statsLayout.getBottom() - scroller.getHeight());
                        }
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
                innerAbilityPassiveLayout.animate().alpha(0.0f).setDuration(100).setListener(new Animator.AnimatorListener() {
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
                innerAbilityPassiveLayout.animate().alpha(1.0f).setDuration(100).setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if ((abilityPassiveLayout.getBottom()) - scroller.getScrollY() > scroller.getHeight()) {
                            scroller.scrollTo(0, abilityPassiveLayout.getBottom() - scroller.getHeight());
                        }
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
                innerAbility1Layout.animate().alpha(0.0f).setDuration(100).setListener(new Animator.AnimatorListener() {
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
                innerAbility1Layout.animate().alpha(1.0f).setDuration(100).setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if ((ability1Layout.getBottom()) - scroller.getScrollY() > scroller.getHeight()) {
                            scroller.scrollTo(0, ability1Layout.getBottom() - scroller.getHeight());
                        }
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
                innerAbility2Layout.animate().alpha(0.0f).setDuration(100).setListener(new Animator.AnimatorListener() {
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
                innerAbility2Layout.animate().alpha(1.0f).setDuration(100).setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if ((ability2Layout.getBottom()) - scroller.getScrollY() > scroller.getHeight()) {
                            scroller.scrollTo(0, ability2Layout.getBottom() - scroller.getHeight());
                        }
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
                innerAbility3Layout.animate().alpha(0.0f).setDuration(100).setListener(new Animator.AnimatorListener() {
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
                innerAbility3Layout.animate().alpha(1.0f).setDuration(100).setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if ((ability3Layout.getBottom()) - scroller.getScrollY() > scroller.getHeight()) {
                            scroller.scrollTo(0, ability3Layout.getBottom() - scroller.getHeight());
                        }
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
                innerAbility4Layout.animate().alpha(0.0f).setDuration(100).setListener(new Animator.AnimatorListener() {
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
                innerAbility4Layout.animate().alpha(1.0f).setDuration(100).setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if ((ability4Layout.getBottom()) - scroller.getScrollY() > scroller.getHeight()) {
                            scroller.scrollTo(0, ability4Layout.getBottom() - scroller.getHeight());
                        }
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
