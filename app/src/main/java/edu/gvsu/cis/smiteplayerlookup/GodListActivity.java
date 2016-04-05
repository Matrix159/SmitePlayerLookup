package edu.gvsu.cis.smiteplayerlookup;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

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
    private List<GodInfo> godList;
    private ArrayList<Bitmap> godBitmaps;
    private ImageSaver imageSaver;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

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
        imageSaver = new ImageSaver(this);
        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(godList, godBitmaps, this);
        mRecyclerView.setAdapter(mAdapter);
        new AsynchCaller().execute();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "GodList Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://edu.gvsu.cis.smiteplayerlookup/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "GodList Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://edu.gvsu.cis.smiteplayerlookup/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
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
            boolean save = true;
            List<GodInfo> godInfoList = master.getGods(1);
            if (imageSaver.setFileName(godInfoList.get(0).getName()).setDirectoryName("images").load() != null) {
                save = false;
                System.out.println("Does this return true?");
            }
            for (GodInfo x : godInfoList) {
                godList.add(x);
                Bitmap mIcon;
                try {

                    if (save) {
                        InputStream in = new URL(x.getGodIcon_URL()).openStream();
                        mIcon = BitmapFactory.decodeStream(in);
                        imageSaver.setFileName(x.getName()).setDirectoryName("images").save(mIcon);
                        godBitmaps.add(mIcon);
                    } else {
                        godBitmaps.add(imageSaver.setFileName(x.getName()).setDirectoryName("images").load());
                    }

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
        startActivity(intent);

    }
}
