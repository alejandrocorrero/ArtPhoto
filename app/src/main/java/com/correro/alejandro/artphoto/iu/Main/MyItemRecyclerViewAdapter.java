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
    private View viewReplace;

    public interface callback {
        void changeImage(Artist item);
        void startActivity(Artist item);
    }

    public MyItemRecyclerViewAdapter(List<Artist> items, callback listener) {
        mValues = items;
        mListener = listener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_activity_main_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getName());
        holder.yearView.setText(mValues.get(position).getYear());
        holder.mContentView.setText(mValues.get(position).getArtist());
        if (position == 0) {
            holder.mView.setBackgroundColor(holder.mView.getContext().getResources().getColor(R.color.colorPrimary));
            viewReplace = holder.mView;
            mListener.changeImage(holder.mItem);
        }

        holder.mView.setOnClickListener(v -> {
            if (null != mListener) {
                viewReplace.setBackgroundColor(v.getContext().getResources().getColor(R.color.color_listview_background));
                v.setBackgroundColor(v.getContext().getResources().getColor(R.color.colorPrimary));
                viewReplace = v;
                mListener.changeImage(holder.mItem);
                mListener.startActivity(holder.mItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public final TextView yearView;
        public Artist mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            yearView=view.findViewById(R.id.lblYear);
            mIdView = (TextView) view.findViewById(R.id.lblArtName);
            mContentView = (TextView) view.findViewById(R.id.lblAuthor);
        }

    }
}
