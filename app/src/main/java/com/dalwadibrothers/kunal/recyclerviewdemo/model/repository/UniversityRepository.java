package com.dalwadibrothers.kunal.recyclerviewdemo.model.repository;

import android.util.Log;

import com.dalwadibrothers.kunal.recyclerviewdemo.model.db.AppDatabase;
import com.dalwadibrothers.kunal.recyclerviewdemo.model.db.University;
import com.dalwadibrothers.kunal.recyclerviewdemo.model.remotedatasource.UniversityRDS;

import java.util.List;


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
    private static UniversityRepository universityRepository;
    private final AppDatabase appDatabase;
    private final UniversityRDS universityRDS;
    
    public UniversityRepository(AppDatabase appDatabase, UniversityRDS universityRDS) {

        /*

      Two things any class needs to access database:
      1. This class only needs two things in Constructor
        1. AppDatabase object to access database
        2. UniversityRDS object to access Remote Data Source.

      2. (Optional) put the data we received from database into a MutableLiveData object
        1. we must post the data that we received using livedata.
        2. as well as return it in a separate function to whoever calls it.

        */
        
        this.appDatabase = appDatabase;
        this.universityRDS = universityRDS;
    }
    
    public static UniversityRepository getInstance(AppDatabase appDatabase, UniversityRDS universityRDS) {
        if (universityRepository == null)
        {
            synchronized (UniversityRepository.class)
            {
                if (universityRepository == null)
                {
                    universityRepository = new UniversityRepository(appDatabase, universityRDS);
                }
            }
        }
        return universityRepository;
    }
    
    //This should be called from ViewModel so that this data reaches ViewModel.
    //If we want to get data from the Remote Data Source we call this function.
    public void getUniversityListFromRepository() {
        List<University> universities = universityRDS.makeNetworkCallGetUniversities();
    
    
        Log.d(TAG, "getUniversityListFromRepository: " + universities.toString());
        
        //TODO Send to database;
        insertDataIntoDatabase(universities);
    
    }
    
    private void insertDataIntoDatabase(List<University> universityList) {
        for (University university : universityList)
        {
            appDatabase.getUniversityDao().insertUniversity(university);
        }
    }
    
    //This should be called from ViewModel so that this data reaches ViewModel.
    //If we want to get data from the Database we call this function.
    public List<University> getUniversitiesListFromDatabase() {
        getUniversityListFromRepository();
    
        Log.d(TAG, "getUniversitiesListFromDatabase: " + appDatabase.getUniversityDao().getAllUniversities());
        
        return appDatabase.getUniversityDao().getAllUniversities();
    }
    
}
