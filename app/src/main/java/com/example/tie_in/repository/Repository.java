package com.example.tie_in.repository;

import android.content.Context;

import androidx.room.Room;

import com.example.tie_in.room.database.AppDatabase;

public class Repository {
    private final Context context;
    AppDatabase database;
    Repository(Context context) {
        this.context = context;
        database = Room.databaseBuilder(context, AppDatabase.class, "tie-in.db")
                .allowMainThreadQueries()
                .build();
    }
}
