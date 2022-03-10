package com.dalwadibrothers.kunal.recyclerviewdemo.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.dalwadibrothers.kunal.recyclerviewdemo.model.db.University;
import com.dalwadibrothers.kunal.recyclerviewdemo.model.repository.UniversityRepository;

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

    private UniversityRepository universityRepository;
    private LiveData<List<University>> allUniversitiesListLiveData;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);

        this.universityRepository = new UniversityRepository(application);
        this.allUniversitiesListLiveData = universityRepository.getUniversitiesListLiveData();
    }

    public void insertUniversity(University university) {
        universityRepository.insertUniversity(university);
    }

    public void deleteUniversity(University university) {
        universityRepository.deleteUniversity(university);
    }

    public void getUniversity(String university_name) {
        universityRepository.getUniversity(university_name);
    }

    public LiveData<List<University>> getAllUniversitiesListLiveData() {
        universityRepository.makeApiCall();
        return allUniversitiesListLiveData;
    }
}
