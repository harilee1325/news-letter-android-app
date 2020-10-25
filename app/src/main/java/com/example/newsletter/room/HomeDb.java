package com.example.newsletter.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "home")
public class HomeDb {


    @ColumnInfo(name = "tag_name")
    private String tagName;

    @ColumnInfo(name = "checked")
    private boolean isChecked;

    @NonNull
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private long id;

    public HomeDb(String tagName, boolean isChecked, long id) {
        this.tagName = tagName;
        this.isChecked = isChecked;
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
