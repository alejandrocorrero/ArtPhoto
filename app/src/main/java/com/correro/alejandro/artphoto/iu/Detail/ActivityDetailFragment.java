package com.correro.alejandro.artphoto.iu.Detail;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.correro.alejandro.artphoto.R;
import com.correro.alejandro.artphoto.data.model.Artist;
import com.correro.alejandro.artphoto.iu.Main.MyItemRecyclerViewAdapter;

public class ActivityDetailFragment extends Fragment  {

    Artist artist = null;
    ImageView ivAvatar;
    TextView lblName;
    TextView lblAuthor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        artist = bundle.getParcelable("artist");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activity_detail, container, false);
        ivAvatar = view.findViewById(R.id.ivAvatarDetail);
        lblName = view.findViewById(R.id.lblNameDetail);
        lblAuthor = view.findViewById(R.id.lblAuthorDetail);

        if (artist != null) {
            ivAvatar.setImageResource(artist.getAvatar());
            lblName.setText(artist.getName());
            lblAuthor.setText(artist.getArtist());
        }
        return view;
    }

    public static ActivityDetailFragment newInstance(Artist artist) {
        ActivityDetailFragment frg = new ActivityDetailFragment();
        Bundle arguments = new Bundle();
        arguments.putParcelable("artist", artist);
        frg.setArguments(arguments);
        return frg;
    }


}
