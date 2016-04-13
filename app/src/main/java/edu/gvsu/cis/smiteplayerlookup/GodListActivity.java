package edu.gvsu.cis.smiteplayerlookup;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;


import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import edu.gvsu.cis.smitedataretrieval.SmiteMaster;
import edu.gvsu.cis.smitedataretrieval.godinfo.GodInfo;

public class GodListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private SmiteMaster master;
    protected static List<GodInfo> godList;
    private ArrayList<Bitmap> godBitmaps;
    private ArrayList<Bitmap> abilityBitmaps;
    private ImageSaver imageSaver;
    private GodListActivity thisActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_god_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        thisActivity = this;
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        master = new SmiteMaster(this);
        godList = new ArrayList<>();
        godBitmaps = new ArrayList<>();
        abilityBitmaps = new ArrayList<>();
        imageSaver = new ImageSaver(this);
        // specify an adapter (see also next example)
        mAdapter = new GodListAdapter(godList, godBitmaps, this);
        mRecyclerView.setAdapter(mAdapter);
        new AsynchCaller().execute();
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
                godList.add(x);
                Bitmap mIcon;
                InputStream in = null;
                try
                {
                        System.out.println(x.getName());

                    try {
                        if (imageSaver.setFileName(String.valueOf(x.getId())).setDirectoryName("images").load() != null)
                            godBitmaps.add(imageSaver.setFileName(String.valueOf(x.getId())).setDirectoryName("images").load());
                        else {
                            in = new URL(x.getGodIcon_URL()).openStream();
                            mIcon = BitmapFactory.decodeStream(in);
                            imageSaver.setFileName(String.valueOf(x.getId())).setDirectoryName("images").save(mIcon);
                            godBitmaps.add(mIcon);
                            in.close();
                        }
                    }
                    catch (Exception ex)
                    {
                        ex.printStackTrace();
                        godBitmaps.add(BitmapFactory.decodeResource(thisActivity.getResources(),
                                R.drawable.placeholder));
                    }

                    try {
                        if (imageSaver.setFileName(String.valueOf(x.getAbilityId1())).setDirectoryName("images").load() == null) {
                            in = new URL(x.getGodAbility1_URL()).openStream();
                            mIcon = BitmapFactory.decodeStream(in);
                            imageSaver.setFileName(String.valueOf(x.getAbilityId1())).setDirectoryName("images").save(mIcon);
                            in.close();
                        }
                    }
                    catch(Exception ex)
                    {
                        ex.printStackTrace();
                    }


                    try {
                        if (imageSaver.setFileName(String.valueOf(x.getAbilityId2())).setDirectoryName("images").load() == null) {
                            in = new URL(x.getGodAbility2_URL()).openStream();
                            mIcon = BitmapFactory.decodeStream(in);
                            imageSaver.setFileName(String.valueOf(x.getAbilityId2())).setDirectoryName("images").save(mIcon);
                            in.close();
                        }

                    }
                    catch(Exception ex)
                    {
                        ex.printStackTrace();
                    }

                    try {
                        if (imageSaver.setFileName(String.valueOf(x.getAbilityId3())).setDirectoryName("images").load() == null) {
                            in = new URL(x.getGodAbility3_URL()).openStream();
                            mIcon = BitmapFactory.decodeStream(in);
                            imageSaver.setFileName(String.valueOf(x.getAbilityId3())).setDirectoryName("images").save(mIcon);
                            in.close();
                        }
                    }
                    catch(Exception ex)
                    {
                        ex.printStackTrace();
                    }

                    try {
                        if (imageSaver.setFileName(String.valueOf(x.getAbilityId4())).setDirectoryName("images").load() == null) {
                            in = new URL(x.getGodAbility4_URL()).openStream();
                            mIcon = BitmapFactory.decodeStream(in);
                            imageSaver.setFileName(String.valueOf(x.getAbilityId4())).setDirectoryName("images").save(mIcon);
                            in.close();
                        }

                    }
                    catch(Exception ex)
                    {
                        ex.printStackTrace();
                    }

                    try {
                        if (imageSaver.setFileName(String.valueOf(x.getAbilityId5())).setDirectoryName("images").load() == null) {
                            in = new URL(x.getGodAbility5_URL()).openStream();
                            mIcon = BitmapFactory.decodeStream(in);
                            imageSaver.setFileName(String.valueOf(x.getAbilityId5())).setDirectoryName("images").save(mIcon);
                            in.close();
                        }
                    }
                    catch(Exception ex)
                    {
                        ex.printStackTrace();
                    }
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
            findViewById(R.id.loadingPanelGods).setVisibility(View.GONE);
            mAdapter.notifyDataSetChanged();

        }
    }

    public void startGodActivity(View v) {
        Intent intent = new Intent(this, GodActivity.class);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        int position = recyclerView.getChildAdapterPosition(v);
        intent.putExtra("pantheon", godList.get(position).getPantheon());
        intent.putExtra("position", position);
        intent.putExtra("godName", godList.get(position).getName());
        intent.putExtra("role", godList.get(position).getRoles());
        intent.putExtra("title", godList.get(position).getTitle());
        intent.putExtra("type", godList.get(position).getType());
        intent.putExtra("godID", String.valueOf(godList.get(position).getId()));
        intent.putExtra("ability1ID", String.valueOf(godList.get(position).getAbilityId1()));
        intent.putExtra("ability2ID", String.valueOf(godList.get(position).getAbilityId2()));
        intent.putExtra("ability3ID", String.valueOf(godList.get(position).getAbilityId3()));
        intent.putExtra("ability4ID", String.valueOf(godList.get(position).getAbilityId4()));
        intent.putExtra("ability5ID", String.valueOf(godList.get(position).getAbilityId5()));
        startActivity(intent);

    }
}
