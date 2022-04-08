package com.dalwadibrothers.kunal.recyclerviewdemo;

import android.app.Application;

import com.dalwadibrothers.kunal.recyclerviewdemo.model.db.AppDatabase;
import com.dalwadibrothers.kunal.recyclerviewdemo.model.network.NetworkApi;
import com.dalwadibrothers.kunal.recyclerviewdemo.model.network.NetworkModule;
import com.dalwadibrothers.kunal.recyclerviewdemo.model.remotedatasource.UniversityRDS;
import com.dalwadibrothers.kunal.recyclerviewdemo.model.repository.UniversityRepository;

public class BaseApplication extends Application {

    private NetworkApi networkApi;
//    private UniversityRDS universityRDS;

    @Override
    public void onCreate() {
        super.onCreate();

        networkApi = NetworkModule.getRetrofit().create(NetworkApi.class);
//        universityRDS = UniversityRDS.getInstance(networkApi);
    }


    public AppDatabase getDatabase() {
        return AppDatabase.getAppDatabase(this);
    }

    public UniversityRepository getUniversityRepository() {
        return UniversityRepository.getInstance(getDatabase(), getNetworkApi(), getUniRDSInstance());
    }

    public UniversityRDS getUniRDSInstance() {
        return UniversityRDS.getInstance(getNetworkApi());
    }

    public NetworkApi getNetworkApi() {
        return networkApi;
    }
}
