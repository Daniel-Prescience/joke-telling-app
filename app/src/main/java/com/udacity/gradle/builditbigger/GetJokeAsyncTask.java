package com.udacity.gradle.builditbigger;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.udacity.andlibdisplayjoke.DisplayJokeActivity;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

class GetJokeAsyncTask extends AsyncTask<Object, Void, String> {
    private static MyApi myApiService = null;
    @SuppressLint("StaticFieldLeak")
    private Context mContext;
    private SimpleIdlingResource mIdlingResource;

    @Override
    protected final String doInBackground(Object... params) {
        // The IdlingResource is null in production.
        if (mIdlingResource != null) {
            mIdlingResource.setIdleState(false);
        }

        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    //.setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    //.setRootUrl("http://192.168.1.8:8080/_ah/api/")
                    .setRootUrl("https://squawker-daniel-udacity.appspot.com/_ah/api/");
/*                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });*/
            // end options for devappserver

            myApiService = builder.build();
        }

        mContext = (Context)params[0];
        mIdlingResource = (SimpleIdlingResource)params[1];

        try {
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        Intent jokeIntent = new Intent(mContext, DisplayJokeActivity.class);
        jokeIntent.putExtra(DisplayJokeActivity.JOKE_KEY, result);
        mContext.startActivity(jokeIntent);

        if (mIdlingResource != null) {
            mIdlingResource.setIdleState(true);
        }
    }
}