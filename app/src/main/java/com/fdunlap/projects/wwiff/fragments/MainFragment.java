package com.fdunlap.projects.wwiff.fragments;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.fdunlap.projects.wwiff.R;
import com.google.firebase.auth.FirebaseAuth;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainFragment extends Fragment {

    private final static String TAG = "MainFragment";

    CircleImageView imageViewProfile;
    TextView textViewMessage;
    TextView textViewLastPost;
    ImageButton imageButtonSettings;
    ProgressBar profileProgressBar;

    public MainFragment() {
        // Required empty public constructor
    }
    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_main, container, false);
        //Connect UI elements
        imageViewProfile = (CircleImageView) layout.findViewById(R.id.profile_image);
        textViewMessage = (TextView) layout.findViewById(R.id.main_chat_textview);
        textViewLastPost = (TextView) layout.findViewById(R.id.text_view_last_post);
        imageButtonSettings = (ImageButton) layout.findViewById(R.id.settingsButton);
        profileProgressBar = (ProgressBar) layout.findViewById(R.id.progress_bar_profile_image);

        Uri photoUri = FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl();
        if(photoUri.toString().contains("_normal")){
            String newPhotoUri = photoUri.toString().replace("_normal", "");
            photoUri = Uri.parse(newPhotoUri);
        }
        Log.d(TAG, "photoUri String: " + photoUri.toString());
        ImageLoader.getInstance().loadImage(photoUri.toString(),
                new ImageSize(imageViewProfile.getWidth(),
                imageViewProfile.getHeight()), new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                imageViewProfile.setVisibility(View.INVISIBLE);
                profileProgressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                imageViewProfile.setVisibility(View.VISIBLE);
                profileProgressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(getActivity().getApplicationContext(), "Avatar loading failed.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                imageViewProfile.setVisibility(View.VISIBLE);
                profileProgressBar.setVisibility(View.INVISIBLE);
                if(loadedImage != null) {
                    imageViewProfile.setImageBitmap(loadedImage);
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Avatar loading failed.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {

            }
        });




        //Return the layout for this fragment
        return layout;
    }

}
