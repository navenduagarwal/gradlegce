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

import com.sparshik.jokedisplay.JokeActivity;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements OnTaskCompleted {
    Button newJokeButton;
    EndpointsAsyncTask endpointsAsyncTask;
    ProgressBar jokeProgress;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        newJokeButton = (Button) root.findViewById(R.id.joke_button);
        jokeProgress = (ProgressBar) root.findViewById(R.id.joke_progress);

        newJokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchGCETask();
            }
        });
        return root;
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
