package com.example.tie_in.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.tie_in.room.model.User;

@Dao
public interface UserDao {
    @Insert
    Long insertUser(User user);

    @Delete
    void deleteUser(User user);

    @Query("SELECT * FROM user limit 1")
    User getUser();
}
