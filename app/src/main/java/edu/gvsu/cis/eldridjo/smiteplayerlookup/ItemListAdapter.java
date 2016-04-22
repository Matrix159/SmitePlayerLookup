package edu.gvsu.cis.eldridjo.smiteplayerlookup;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import edu.gvsu.cis.eldridjo.smitedataretrieval.godinfo.GodInfo;
import edu.gvsu.cis.eldridjo.smitedataretrieval.iteminfo.ItemInfo;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ViewHolder> {
    private List<ItemInfo> mDataset;
    private ArrayList<Bitmap> mBitmaps;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // each data item is just a string in this case
        public ImageView mImageView;
        public TextView mTextView;
        public ViewHolder(View v) {
            super(v);
            mImageView = (ImageView) v.findViewById(R.id.item_icon_list);
            mTextView = (TextView) v.findViewById(R.id.item_name_list);
        }


        @Override
        public void onClick(View v) {

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ItemListAdapter(List<ItemInfo> list, ArrayList<Bitmap> bitmaps) {
        mDataset = list;
        mBitmaps = bitmaps;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ItemListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mImageView.setImageBitmap(mBitmaps.get(position));
        holder.mTextView.setText(mDataset.get(position).getDeviceName());
        System.out.println(mDataset.get(position).getDeviceName());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}