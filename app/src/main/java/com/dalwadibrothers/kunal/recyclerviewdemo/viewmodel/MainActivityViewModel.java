package com.dalwadibrothers.kunal.recyclerviewdemo.viewmodel;

import com.dalwadibrothers.kunal.recyclerviewdemo.model.db.University;
import com.dalwadibrothers.kunal.recyclerviewdemo.model.repository.UniversityRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/*

WHY? we use LiveData

LiveData is an observable data holder class. Unlike a regular observable,
LiveData is lifecycle-aware, meaning it respects the lifecycle of other app components,
such as activities, fragments, or services.
This awareness ensures LiveData only updates app component observers that are in an active lifecycle state.

 */

public class MainActivityViewModel extends ViewModel {

    private static final String TAG = MainActivityViewModel.class.getSimpleName();

    private UniversityRepository universityRepository;

    //this should be observed from MainActivity.
    private MutableLiveData<List<University>> listUniversityMutableLiveData;

    public MainActivityViewModel(@NonNull UniversityRepository universityRepository) {

        this.universityRepository = universityRepository;

        listUniversityMutableLiveData = new MutableLiveData<>();
    }

    //this should be called from MainActivity.
    public void getDataFromRepository() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<University> universityListFromRepository = universityRepository.getUniversitiesListFromDatabase();
//                List<University> universityListFromRepository = universityRepository.getUniversityListFromRepository();
                listUniversityMutableLiveData.postValue(universityListFromRepository);
            }
        }).start();
    }

    public LiveData<List<University>> getLiveDataFromViewModel() {
        return listUniversityMutableLiveData;
    }


//    public void insertUniversity(University university) {
//        universityRepository.insertUniversity(university);
//    }
//
//    public void deleteUniversity(University university) {
//        universityRepository.deleteUniversity(university);
//    }
//
//    public void getUniversity(String university_name) {
//        universityRepository.getUniversity(university_name);
//    }
//
////    public LiveData<List<University>> getAllUniversitiesListLiveData() {
////        return universityRepository.getUniversitiesListLiveData();
////    }

}
