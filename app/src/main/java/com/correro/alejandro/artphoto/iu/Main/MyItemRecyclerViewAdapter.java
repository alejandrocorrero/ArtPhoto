package com.correro.alejandro.artphoto.iu.Main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.correro.alejandro.artphoto.R;
import com.correro.alejandro.artphoto.data.model.Artist;

import java.util.List;


public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final List<Artist> mValues;
    private final callback mListener;
    private final Artist artist;
    private View actualView;

    public interface callback {
        void clickItemAdapter(Artist artist);

        void startActivityCallback(Artist artist);
    }

    MyItemRecyclerViewAdapter(List<Artist> items, callback listener, Artist artist) {
        mValues = items;
        mListener = listener;
        this.artist = artist;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_activity_main_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.lblArtName.setText(mValues.get(position).getName());
        holder.lblYear.setText(mValues.get(position).getYear());
        holder.lblAuthor.setText(mValues.get(position).getArtist());
        //Set the actual artist checked
        if (position == mValues.indexOf(artist)) {
            holder.mView.setBackgroundColor(holder.mView.getContext().getResources().getColor(R.color.color_List_item_Check));
            actualView = holder.mView;
        }
        holder.mView.setOnClickListener(v -> {
            if (null != mListener && actualView != null) {
                actualView.setBackgroundColor(v.getContext().getResources().getColor(R.color.color_listview_background));
                v.setBackgroundColor(v.getContext().getResources().getColor(R.color.color_List_item_Check));
                actualView = v;
                mListener.clickItemAdapter(holder.mItem);
                mListener.startActivityCallback(holder.mItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //mview is the actual view of the recycleView
        public final View mView;
        public final TextView lblArtName;
        public final TextView lblAuthor;
        public final TextView lblYear;
        public Artist mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            lblYear = view.findViewById(R.id.lblYear);
            lblArtName = view.findViewById(R.id.lblArtName);
            lblAuthor = view.findViewById(R.id.lblAuthor);
        }


    }
}
