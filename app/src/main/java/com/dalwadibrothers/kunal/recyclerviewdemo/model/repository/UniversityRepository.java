package com.dalwadibrothers.kunal.recyclerviewdemo.model.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dalwadibrothers.kunal.recyclerviewdemo.model.db.AppDatabase;
import com.dalwadibrothers.kunal.recyclerviewdemo.model.db.University;
import com.dalwadibrothers.kunal.recyclerviewdemo.model.db.UniversityDAO;
import com.dalwadibrothers.kunal.recyclerviewdemo.model.network.NetworkApi;
import com.dalwadibrothers.kunal.recyclerviewdemo.model.remotedatasource.UniversityRDS;

import java.util.ArrayList;
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
    private NetworkApi networkApi;
    private UniversityRDS universityRDS;

    public UniversityRepository(AppDatabase appDatabase, NetworkApi networkApi, UniversityRDS universityRDS) {

        /*

      Two things any class needs to access database:
      1. AppDatabase object (object of class that extends RoomDatabase)
      2. Dao object (of each dao in the application)

      3. LiveData if we are using one (getter method of LiveData only as it will be available directly from the db as LiveData itself)

        */

//        this.appDatabase = appDatabase;
        this.networkApi = networkApi;
        this.universityRDS = universityRDS;
    }

    public static UniversityRepository getInstance(AppDatabase appDatabase, NetworkApi networkApi, UniversityRDS universityRDS) {
        if (universityRepository == null) {
            synchronized (UniversityRepository.class) {
                if (universityRepository == null) {
                    universityRepository = new UniversityRepository(appDatabase, networkApi, universityRDS);
                }
            }
        }
        return universityRepository;
    }


    //This should be called from ViewModel so that this data reaches ViewModel.
    public List<University> getUniversityListFromRepository() {
        List<University> universities = universityRDS.makeNetworkCallGetUniversities();

        //TODO Send to database;

        return universities;
    }


    /*
    Repository is the only class now that is accessing Database,
    so Repository mimic's all methods of DAO class so that it can be accessed.
     */

//    public void insertUniversity(University university){
//        appDatabase.getUniversityDao().insertUniversity(university);
//    }
//
//    public void deleteUniversity(University university){
//        appDatabase.getUniversityDao().deleteUniversity(university);
//    }
//
//    public void getUniversity(String name){
//        appDatabase.getUniversityDao().getUniversity(name);
//    }
//
//    private void insertDataIntoDatabase(List<University> universityList) {
//        for (University university: universityList) {
//            appDatabase.getUniversityDao().insertUniversity(university);
//        }
//    }

}
