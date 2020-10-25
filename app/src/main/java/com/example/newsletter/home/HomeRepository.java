package com.example.newsletter.home;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.AsyncTask;

import com.example.newsletter.room.HomeDb;
import com.example.newsletter.room.NewsLetterDatabase;
import com.example.newsletter.utild.Config;
import com.example.newsletter.utild.Utility;

import java.util.ArrayList;
import java.util.List;

public class HomeRepository {

    private NewsLetterDatabase db;
    private List<HomeDb> tagsName = new ArrayList<>();
    private ITagsResponse tagsResponse;

    public void getTags(Application application, ITagsResponse iTagsResponse) {
        db = NewsLetterDatabase.getInstance(application);
        tagsResponse = iTagsResponse;

        if (Utility.getPreference(application, Config.IS_FIRST_TIME).equalsIgnoreCase("no"))
            new GetTags().execute();
        else
            new AddNewTag().execute();

    }

    @SuppressLint("StaticFieldLeak")
    private class AddNewTag extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... voids) {

            HomeDb homeDb = new HomeDb("International", false, 0);
            db.getHomeData().addTag(homeDb);
            HomeDb homeDb2 = new HomeDb("Sports", false, 0);
            db.getHomeData().addTag(homeDb2);
            HomeDb homeDb3 = new HomeDb("Tech", false, 0);
            db.getHomeData().addTag(homeDb3);
            HomeDb homeDb4 = new HomeDb("Cooking", false, 0);
            db.getHomeData().addTag(homeDb4);
            HomeDb homeDb5 = new HomeDb("Covid", false, 0);
            db.getHomeData().addTag(homeDb5);
            HomeDb homeDb6 = new HomeDb("Elections", false, 0);
            db.getHomeData().addTag(homeDb6);
            HomeDb homeDb7 = new HomeDb("Politics", false, 0);
            db.getHomeData().addTag(homeDb7);
            HomeDb homeDb8 = new HomeDb("Automobile", false, 0);
            db.getHomeData().addTag(homeDb8);
            HomeDb homeDb9 = new HomeDb("IOS", false, 0);
            db.getHomeData().addTag(homeDb9);
            HomeDb homeDb10 = new HomeDb("Educational", false, 0);
            db.getHomeData().addTag(homeDb10);
            HomeDb homeDb11 = new HomeDb("Movies", false, 0);
            db.getHomeData().addTag(homeDb11);
            HomeDb homeDb12 = new HomeDb("Android", false, 0);
            db.getHomeData().addTag(homeDb12);
            HomeDb homeDb13 = new HomeDb("Music", false, 0);
            db.getHomeData().addTag(homeDb13);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            new GetTags().execute();
        }
    }


    @SuppressLint("StaticFieldLeak")
    private class GetTags extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            tagsName.addAll(db.getHomeData().getAllTags());
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            tagsResponse.getTagsData(tagsName);
        }
    }

    public interface ITagsResponse {
        void getTagsData(List<HomeDb> tagsName);
    }

}
