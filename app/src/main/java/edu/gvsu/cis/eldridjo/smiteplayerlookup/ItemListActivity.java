package edu.gvsu.cis.eldridjo.smiteplayerlookup;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import edu.gvsu.cis.eldridjo.smitedataretrieval.SmiteMaster;
import edu.gvsu.cis.eldridjo.smitedataretrieval.iteminfo.ItemInfo;

public class ItemListActivity extends AppCompatActivity {

    private RecyclerView itemListRecyclerView;
    private RecyclerView.Adapter itemListAdapter;
    private RecyclerView.LayoutManager itemListLayoutManager;
    private SmiteMaster master;
    protected static List<ItemInfo> itemList;
    private ArrayList<Bitmap> itemBitmaps;
    private ImageSaver imageSaver;
    private ItemListActivity thisActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        thisActivity = this;
        itemListRecyclerView = (RecyclerView) findViewById(R.id.item_list_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        itemListRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        itemListLayoutManager = new LinearLayoutManager(this);
        itemListRecyclerView.setLayoutManager(itemListLayoutManager);
        master = new SmiteMaster(this, SmiteMaster.PC);
        itemList = new ArrayList<>();
        itemBitmaps = new ArrayList<>();
        imageSaver = new ImageSaver(this);
        // specify an adapter (see also next example)
        itemListAdapter = new ItemListAdapter(itemList, itemBitmaps);
        itemListRecyclerView.setAdapter(itemListAdapter);
        new AsynchCaller().execute();
    }





    private class AsynchCaller extends AsyncTask<Void, Void, Void>
    {

        List<ItemInfo> itemInfoList;
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
            itemInfoList = master.getItems(1);
            if (itemInfoList.get(0).getRet_msg() != null) {
                badServer = true;
                return null;
            }
            for (ItemInfo x : itemInfoList) {
                itemList.add(x);
                Bitmap mIcon;
                InputStream in = null;

                String itemURI = "@drawable/i" + x.getItemId();  // where myresource (without the extension) is the file
                int itemIcon = getResources().getIdentifier(itemURI, null, getPackageName());
                itemBitmaps.add(BitmapFactory.decodeResource(thisActivity.getResources(), itemIcon));


                /*try
                {
                        System.out.println(x.getName());

                    try {
                        if (imageSaver.setFileName(String.valueOf(x.getId())).setDirectoryName("images").load() != null)
                            itemBitmaps.add(imageSaver.setFileName(String.valueOf(x.getId())).setDirectoryName("images").load());
                        else
                        {
                            in = new URL(x.getGodIcon_URL()).openStream();
                            mIcon = BitmapFactory.decodeStream(in);
                            imageSaver.setFileName(String.valueOf(x.getId())).setDirectoryName("images").save(mIcon);
                            itemBitmaps.add(mIcon);
                            in.close();
                        }
                    }
                    catch (Exception ex)
                    {
                        ex.printStackTrace();
                        itemBitmaps.add(BitmapFactory.decodeResource(thisActivity.getResources(),
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
            itemListAdapter.notifyDataSetChanged();

        }
    }

    public void startItemActivity(View v) {
        Intent intent = new Intent(this, ItemActivity.class);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.item_list_recycler_view);
        int position = recyclerView.getChildAdapterPosition(v);
        intent.putExtra("position", position);
        startActivity(intent);

    }
}
