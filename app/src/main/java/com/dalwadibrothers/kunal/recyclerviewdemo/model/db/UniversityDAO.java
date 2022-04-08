package com.dalwadibrothers.kunal.recyclerviewdemo.model.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/*
Dao should always be an interface.
This interface abstracts all the methods that make database transactions, like insert/update/delete/query.
Annotate this interface with @Dao annotation.

Each table must have their own DAO to follow separation of concerns.
 */
@Dao
public interface UniversityDAO {

    @Insert
    void insertUniversity(University university);

    @Delete
    void deleteUniversity(University university);

    @Query("SELECT * FROM universities")
    LiveData<List<University>> getAllUniversities();

    /*
    When you want to write 'where' queries or queries that wants you to refer to the parameter you are passing,
    They can be referred to by putting ':' sign in front of it.
     */
    @Query("SELECT * FROM universities where name ==:name")
    University getUniversity(String name);

}
