package com.example.newsletter.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.newsletter.room.HomeDb;

import java.util.List;

public class HomeViewModel extends AndroidViewModel implements HomeRepository.ITagsResponse {

    private MutableLiveData<List<HomeDb>> tagsResponse = new MutableLiveData<>();
    private MutableLiveData<List<HomeDb>> selectedTagsResponse = new MutableLiveData<>();

    private final HomeRepository homeRepository;
    private Application mContext;


    public HomeViewModel(@NonNull Application application) {
        super(application);
        this.mContext = application;
        homeRepository = new HomeRepository();
        homeRepository.getTags(application, this);
    }

    public LiveData<List<HomeDb>> getTags() {
        return tagsResponse;
    }

    public LiveData<List<HomeDb>> getSelectedTags() {
        return selectedTagsResponse;
    }

    @Override
    public void getTagsData(List<HomeDb> tagsName) {
        tagsResponse.setValue(tagsName);
    }

    @Override
    public void getSelectedTagsData(List<HomeDb> tagsSelected) {
        selectedTagsResponse.setValue(tagsSelected);
    }

    public void updateTags(List<HomeDb> tagsName) {
        homeRepository.updateTags(tagsName);
    }
}
