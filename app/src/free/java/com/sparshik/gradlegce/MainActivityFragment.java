package com.sparshik.gradlegce;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.sparshik.jokedisplay.JokeActivity;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements OnTaskCompleted {
    InterstitialAd mInterstitialAd;
    Button newJokeButton;
    EndpointsAsyncTask endpointsAsyncTask;
    ProgressBar jokeProgress;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        newJokeButton = (Button) root.findViewById(R.id.joke_button);
        jokeProgress = (ProgressBar) root.findViewById(R.id.joke_progress);


        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
        mInterstitialAd = new InterstitialAd(getContext());
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                launchGCETask();
            }
        });
        requestNewInterstitial();

        newJokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    launchGCETask();
                }
            }
        });

        return root;
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mInterstitialAd.loadAd(adRequest);
    }

    public void launchGCETask() {
        endpointsAsyncTask = new EndpointsAsyncTask(getContext());
        endpointsAsyncTask.listener = this;
        jokeProgress.setVisibility(View.VISIBLE);
        endpointsAsyncTask.execute();
    }

    @Override
    public void onComplete(String result) {
        jokeProgress.setVisibility(View.GONE);
        if (result != null) {
            Intent intent = new Intent(getContext(), JokeActivity.class);
            intent.putExtra(JokeActivity.JOKE_KEY, result);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getContext().startActivity(intent);
        } else {
            Toast.makeText(getContext(), "Joke not available", Toast.LENGTH_LONG).show();
        }
    }
}
