package com.dalwadibrothers.kunal.recyclerviewdemo.viewmodel;

import com.dalwadibrothers.kunal.recyclerviewdemo.model.repository.UniversityRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ViewModelFactory implements ViewModelProvider.Factory{


    private final UniversityRepository universityRepository;

    public ViewModelFactory(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MainActivityViewModel(universityRepository);
    }
}
