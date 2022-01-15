package com.dalwadibrothers.kunal.recyclerviewdemo;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

/*
1. Make a class that extends RoomDatabase, annotate it with @Database.
2. Add entities as all the Model(POJO) classes that resides in that database.
3. Give it a version number.
4. MAKE THIS CLASS ABSTRACT.
5. Make an abstract method return the DAO interface.


Database: This annotation marks a class as a database. It should be an abstract class that extends RoomDatabase.

Entity: This annotation marks a class as a database row. For each Entity,
a database table is created to hold the items. The Entity class must be referenced in the Database#entities array.

Dao: This annotation marks a class or interface as a Data Access Object.
Data access objects are the main components of Room that are responsible for defining the methods that access the database.
The class that is annotated with Database must have an abstract method that has 0 arguments and returns the class that is annotated with Dao.
While generating the code at compile time, Room will generate an implementation of this class.

 */
@Database(entities = {University.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    private static final String TAG = AppDatabase.class.getSimpleName();

    public abstract UniversityDAO getUniversityDao();

    private static AppDatabase appDatabase;

    public static synchronized AppDatabase getAppDatabase(Context context) {

        if (appDatabase == null) {
            appDatabase = Room
                    .databaseBuilder(context, AppDatabase.class, "database_one")
                    .addCallback(callback)
                    .allowMainThreadQueries()
                    .build();
        }

        return appDatabase;
    }

    static RoomDatabase.Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            Log.d(TAG, "onCreate: Database Created");
        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            Log.d(TAG, "onOpen: Database Opened");
        }

        @Override
        public void onDestructiveMigration(@NonNull SupportSQLiteDatabase db) {
            super.onDestructiveMigration(db);
        }
    };

}
