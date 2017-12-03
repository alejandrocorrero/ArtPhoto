package com.correro.alejandro.artphoto.iu.Main;

import android.arch.lifecycle.ViewModelProviders;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.correro.alejandro.artphoto.R;
import com.correro.alejandro.artphoto.data.model.Artist;
import com.correro.alejandro.artphoto.data.utils.FragmentUtils;
import com.correro.alejandro.artphoto.iu.Detail.ActivityDetailFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainFragment.Callback {

    @BindView(R.id.mainFrame)
    FrameLayout mainFrame;
    FrameLayout FrameDetail;
    MainActivityViewModel viewModel;
    private static final String TAG_MAIN_FRAGMENT = "TAG_MAIN_FRAGMENT";
    private static final String TAG_DETAIL_FRAGMENT = "TAG_DETAIL_FRAGMENT";
    boolean first = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //If orientation is portrait replace the actual mainfragment  with a new fragment if it is null.
        if (getSupportFragmentManager().findFragmentByTag(TAG_MAIN_FRAGMENT) == null && getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            FragmentUtils.replaceFragment(getSupportFragmentManager(), R.id.mainFrame,
                    MainFragment.newInstance(viewModel.getActualArtist()), TAG_MAIN_FRAGMENT);
        }
        //If orientation is Landscape load mainframe and detailfragment
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            FragmentUtils.replaceFragmentAddToBackstack(getSupportFragmentManager(), R.id.mainFrame,
                    MainFragment.newInstance(viewModel.getActualArtist()), TAG_MAIN_FRAGMENT, viewModel.actualArtist.getArtist(), 0);
            FragmentUtils.replaceFragment(getSupportFragmentManager(), R.id.FrameDetail,
                    ActivityDetailFragment.newInstance(viewModel.getActualArtist()), TAG_DETAIL_FRAGMENT);

        }


    }


    //Everytime we change a item, it load and add to backstack a new main and detail fragments
    @Override
    public void onElementClick(Artist artist) {
        first = true;
        viewModel.setActualArtist(artist);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
            ActivityDetailFragment frgDetail = ActivityDetailFragment.newInstance(viewModel.getActualArtist());
            FrameDetail = findViewById(R.id.FrameDetail);
            transaction2.add(R.id.FrameDetail, frgDetail);
            transaction2.addToBackStack(artist.getName());
            transaction2.commit();


            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            MainFragment frgmain = MainFragment.newInstance(viewModel.getActualArtist());
            mainFrame = findViewById(R.id.mainFrame);
            transaction.add(R.id.mainFrame, frgmain);
            transaction.addToBackStack(artist.getName());
            transaction.commit();


        }
    }

    //when click back if orientation is portrait remove backstack and call super,if orientation is landscape and is first time
    //call 3 times backstack fragment ,one for remove the actual state of the activity and 2 for both fragments
    //else every time we click back it call 2 times backstack fragment for both fragments while count isn't 0;
    @Override
    public void onBackPressed() {

        if (!(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)) {
            getSupportFragmentManager().popBackStack(null,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE);


        } else {
            if (first) {
                getSupportFragmentManager().popBackStack();
                getSupportFragmentManager().popBackStack();
                getSupportFragmentManager().popBackStack();
                first = false;
            } else {
                getSupportFragmentManager().popBackStack();
                getSupportFragmentManager().popBackStack();
            }

        }
        if (getSupportFragmentManager().getBackStackEntryCount() == 0)
            super.onBackPressed();
    }
}
