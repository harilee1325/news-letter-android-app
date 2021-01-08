package com.example.newsletter.room;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface IHomeDb {


    @Insert
    public long addTag(HomeDb homeDb);


    @Query("Select * from home")
    public List<HomeDb> getAllTags();

    @Query("Delete from home")
    public int removeTags();

    @Update
    void update(List<HomeDb> homeDbsList);

    @Query("Select * from home where checked == 'true'")
    public List<HomeDb> getSelectedTags();

}
