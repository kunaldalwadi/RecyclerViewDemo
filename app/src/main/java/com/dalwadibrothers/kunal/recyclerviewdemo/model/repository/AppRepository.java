package com.dalwadibrothers.kunal.recyclerviewdemo.model.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.dalwadibrothers.kunal.recyclerviewdemo.model.db.AppDatabase;
import com.dalwadibrothers.kunal.recyclerviewdemo.model.db.University;
import com.dalwadibrothers.kunal.recyclerviewdemo.model.db.UniversityDAO;
import com.dalwadibrothers.kunal.recyclerviewdemo.model.network.NetworkApi;
import com.dalwadibrothers.kunal.recyclerviewdemo.model.network.NetworkModule;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/*

Repository class gives one more abstraction layer between database/web_service/datasources and ViewModel.

Repository should be the only class that fetches data from different sources and gathers them.

 */
public class AppRepository {

    /*
    Create all objects that you need to access different data sources.

    for example : create dao object if you need to access Room database
                  create retrofit objects if you need to access web_service data.

     Since we are accessing Database we initialize database in the constructor of Repository and fetch initial data to show on the screen.
     */

    private static final String TAG = AppRepository.class.getSimpleName();

    private UniversityDAO universityDAO;
    private LiveData<List<University>> universitiesListLiveData;
    private AppDatabase appDatabase;
    private NetworkApi networkApi;

    public AppRepository(Application application) {

        this.appDatabase = AppDatabase.getAppDatabase(application);

        this.universityDAO = appDatabase.getUniversityDao();
        this.universitiesListLiveData = universityDAO.getAllUniversities();

        this.networkApi = NetworkModule.getRetrofit().create(NetworkApi.class);

    }

    public void insertUniversity(University university){
        universityDAO.insertUniversity(university);
    }

    public void deleteUniversity(University university){
        universityDAO.deleteUniversity(university);
    }

    public void getUniversity(String name){
        universityDAO.getUniversity(name);
    }

    public LiveData<List<University>> getUniversitiesListLiveData() {
        return universitiesListLiveData;
    }

    //Single responsibility principle - only makes the call
    public void makeApiCall() {

        Call<List<University>> uniNames = networkApi.getUniNames();

        Log.d(TAG, "makeApiCall: URL = " + uniNames.request().url().toString());
        Log.d(TAG, "makeApiCall: -------------------------------------------------------------");

        uniNames.enqueue(new Callback<List<University>>() {
            @Override
            public void onResponse(Call<List<University>> call, Response<List<University>> response) {

                if (response.isSuccessful()) {
                    if (response.body() != null) {

                        List<University> universityList = response.body();

                        insertDataIntoDatabase(universityList);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<University>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.toString());
            }
        });
    }

    private void insertDataIntoDatabase(List<University> universityList) {
        for (University university: universityList) {
            universityDAO.insertUniversity(university);
        }
    }

}
