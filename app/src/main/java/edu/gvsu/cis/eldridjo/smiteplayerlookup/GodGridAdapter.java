package edu.gvsu.cis.eldridjo.smiteplayerlookup;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.gvsu.cis.eldridjo.smitedataretrieval.godinfo.GodInfo;

public class GodGridAdapter extends RecyclerView.Adapter<GodGridAdapter.ViewHolder> {
    private List<GodInfo> mDataset;
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
            //mImageView = (ImageView) v.findViewById(R.id.god_icon_list);
            //mTextView = (TextView) v.findViewById(R.id.god_name_list);
            mImageView = (ImageView) v.findViewById(R.id.god_grid_image);
            mTextView = (TextView) v.findViewById(R.id.god_grid_text);
        }


        @Override
        public void onClick(View v) {

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public GodGridAdapter(List<GodInfo> list, ArrayList<Bitmap> bitmaps) {
        mDataset = list;
        mBitmaps = bitmaps;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public GodGridAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        // create a new view
        // View view = LayoutInflater.from(parent.getContext())
        //         .inflate(R.layout.god_list_view, parent, false);
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.god_grid_view, parent, false);
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
        holder.mTextView.setText(mDataset.get(position).getName());
        System.out.println(mDataset.get(position).getName());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}