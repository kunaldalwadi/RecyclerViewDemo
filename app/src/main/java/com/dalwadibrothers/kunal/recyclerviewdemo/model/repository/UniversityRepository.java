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

Repository class gives one more abstraction layer between ViewModel and database/web_service/datasources.

Repository should be the only class that fetches data from different sources and gathers them.

 */
public class UniversityRepository {

    /*
    Create all objects that you need to access different data sources.

    for example : create dao object if you need to access Room database
                  create retrofit objects if you need to access web_service data.

     Since we are accessing Database we initialize database in the constructor of Repository and fetch initial data to show on the screen.
     */

    private static final String TAG = UniversityRepository.class.getSimpleName();

    private UniversityDAO universityDAO;
    private LiveData<List<University>> universitiesListLiveData;
    private AppDatabase appDatabase;
    private NetworkApi networkApi;

    public UniversityRepository(Application application) {

        /*

      Two things any class needs to access database:
      1. AppDatabase object (object of class that extends RoomDatabase)
      2. Dao object (of each dao in the application)

      3. LiveData if we are using one (getter method of LiveData only as it will be available directly from the db as LiveData itself)

        */

        this.appDatabase = AppDatabase.getAppDatabase(application);

        this.universityDAO = appDatabase.getUniversityDao();
        this.universitiesListLiveData = universityDAO.getAllUniversities();

        this.networkApi = NetworkModule.getRetrofit().create(NetworkApi.class);

    }

    /*
    Repository is the only class now that is accessing Database,
    so Repository mimic's all methods of DAO class so that it can be accessed.
     */

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
