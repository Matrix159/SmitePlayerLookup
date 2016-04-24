package edu.gvsu.cis.eldridjo.smiteplayerlookup;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import edu.gvsu.cis.eldridjo.smitedataretrieval.SmiteMaster;
import edu.gvsu.cis.eldridjo.smitedataretrieval.godinfo.GodInfo;

public class GodListActivity extends AppCompatActivity {

    private RecyclerView godListRecyclerView;
    private RecyclerView.Adapter godListAdapter, godGridAdapter;
    private RecyclerView.LayoutManager godListLayoutManager;
    private SmiteMaster master;
    protected static List<GodInfo> godList;
    private ArrayList<Bitmap> godBitmaps;
    private ImageSaver imageSaver;
    private GodListActivity thisActivity;
    private boolean isGrid;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_god_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        thisActivity = this;
        godListRecyclerView = (RecyclerView) findViewById(R.id.god_list_recycler_view);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        if(prefs.contains("grid")){
            isGrid = prefs.getBoolean("grid", true);
        }else {
            isGrid = true;
        }

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        godListRecyclerView.setHasFixedSize(true);

        // use a linear layout manager

            //TODO: Make button clickable (add onResume)

        godListLayoutManager = (isGrid) ? new GridLayoutManager(this, 5): new LinearLayoutManager(this);
        godListRecyclerView.setLayoutManager(godListLayoutManager);
        master = new SmiteMaster(this);
        godList = new ArrayList<>();
        godBitmaps = new ArrayList<>();
        imageSaver = new ImageSaver(this);
        // specify an adapter (see also next example)
        if(isGrid) {
            godGridAdapter = new GodGridAdapter(godList, godBitmaps);
        }else {
            godListAdapter = new GodListAdapter(godList, godBitmaps);
        }
        godListRecyclerView.setAdapter((isGrid) ? godGridAdapter:godListAdapter);
        new AsynchCaller().execute();
    }


    private class AsynchCaller extends AsyncTask<Void, Void, Void>
    {

        List<GodInfo> godInfoList;
        boolean badServer = false;
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {

            while (master.getSessionId() == null) {

            }
            godInfoList = master.getGods(1);
            if (godInfoList.get(0).getRet_msg() != null) {
                badServer = true;
                return null;
            }
            for (GodInfo x : godInfoList) {
                godList.add(x);
                Bitmap mIcon;
                InputStream in = null;

                String godURI = "@drawable/g" + x.getId();  // where myresource (without the extension) is the file
                int godIcon = getResources().getIdentifier(godURI, null, getPackageName());
                godBitmaps.add(BitmapFactory.decodeResource(thisActivity.getResources(), godIcon));


                /*try
                {
                        System.out.println(x.getName());

                    try {
                        if (imageSaver.setFileName(String.valueOf(x.getId())).setDirectoryName("images").load() != null)
                            godBitmaps.add(imageSaver.setFileName(String.valueOf(x.getId())).setDirectoryName("images").load());
                        else
                        {
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
                        if (imageSaver.setFileName(String.valueOf(x.getAbilityId1())).setDirectoryName("images").load() == null)
                        {
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
                        if (imageSaver.setFileName(String.valueOf(x.getAbilityId3())).setDirectoryName("images").load() == null)
                        {
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
                        if (imageSaver.setFileName(String.valueOf(x.getAbilityId4())).setDirectoryName("images").load() == null)
                        {
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
                        if (imageSaver.setFileName(String.valueOf(x.getAbilityId5())).setDirectoryName("images").load() == null)
                        {
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
                }
                catch (Exception e)
                {
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

            }*/



            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(badServer)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(thisActivity);
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
            if(isGrid)
                godGridAdapter.notifyDataSetChanged();
            else
                godListAdapter.notifyDataSetChanged();
        }
    }

    public void startGodActivity(View v) {
        Intent intent = new Intent(this, GodActivity.class);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.god_list_recycler_view);
        int position = recyclerView.getChildAdapterPosition(v);
        intent.putExtra("position", position);
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater m = getMenuInflater();
        m.inflate(R.menu.god_menu, menu);

        //Changes the menu item picture accordingly
        MenuItem i = menu.getItem(0);
        changeIcon(i);

        return true;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        SharedPreferences.Editor ped = prefs.edit();
        ped.putBoolean("grid", isGrid);
        ped.commit();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.list_button:
                if(!isGrid) {
                    item.setIcon(R.drawable.ic_grid);
                    isGrid = true;
                    recreate();
                }else{
                    item.setIcon(R.drawable.ic_list);
                    isGrid = false;
                    recreate();
                }
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void changeIcon(MenuItem i){
        if(isGrid){
            i.setIcon(R.drawable.ic_grid);
        }else{
            i.setIcon(R.drawable.ic_list);
        }
    }
}
