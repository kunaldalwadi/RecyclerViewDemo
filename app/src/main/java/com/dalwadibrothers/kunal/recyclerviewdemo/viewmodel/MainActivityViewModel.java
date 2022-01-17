package com.dalwadibrothers.kunal.recyclerviewdemo.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.dalwadibrothers.kunal.recyclerviewdemo.model.db.University;
import com.dalwadibrothers.kunal.recyclerviewdemo.model.repository.AppRepository;

import java.util.List;

/*

WHY? we use LiveData

LiveData is an observable data holder class. Unlike a regular observable,
LiveData is lifecycle-aware, meaning it respects the lifecycle of other app components,
such as activities, fragments, or services.
This awareness ensures LiveData only updates app component observers that are in an active lifecycle state.

 */

public class MainActivityViewModel extends AndroidViewModel {

    private static final String TAG = MainActivityViewModel.class.getSimpleName();

    private AppRepository appRepository;
    private LiveData<List<University>> allUniversitiesListLiveData;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);

        this.appRepository = new AppRepository(application);
        this.allUniversitiesListLiveData = appRepository.getUniversitiesListLiveData();
    }

    public void insertUniversity(University university) {
        appRepository.insertUniversity(university);
    }

    public void deleteUniversity(University university) {
        appRepository.deleteUniversity(university);
    }

    public void getUniversity(String university_name) {
        appRepository.getUniversity(university_name);
    }

    public LiveData<List<University>> getAllUniversitiesListLiveData() {
        appRepository.makeApiCall();
        return allUniversitiesListLiveData;
    }
}
