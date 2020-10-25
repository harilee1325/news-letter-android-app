package com.example.newsletter.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.newsletter.utild.Config;


@Database(entities = {HomeDb.class} , version = 1)
public abstract class NewsLetterDatabase extends RoomDatabase {


    public abstract IHomeDb getHomeData();

    private static NewsLetterDatabase instance;


    public static synchronized NewsLetterDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NewsLetterDatabase.class, Config.DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
