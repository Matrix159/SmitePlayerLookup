package edu.gvsu.cis.smiteplayerlookup;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import edu.gvsu.cis.smitedataretrieval.SmiteMaster;
import edu.gvsu.cis.smitedataretrieval.godinfo.GodInfo;

public class GodListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private SmiteMaster master;
    private List<GodInfo> godList;
    private ArrayList<Bitmap> godBitmaps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_god_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(godList, godBitmaps);
        mRecyclerView.setAdapter(mAdapter);
        new AsynchCaller().execute();
    }

    private class AsynchCaller extends AsyncTask<Void, Void, Void>
    {



        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            while(master.getSessionId() == null)
            {

            }
            List<GodInfo> godInfoList = master.getGods(1);
            for(GodInfo x: godInfoList) {
                godList.add(x);
                Bitmap mIcon;
                try {
                    InputStream in = new java.net.URL(x.getGodIcon_URL()).openStream();
                    mIcon = BitmapFactory.decodeStream(in);
                    godBitmaps.add(mIcon);
                } catch (Exception e) {
                    Log.e("Error", e.getMessage());
                    e.printStackTrace();
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
}
