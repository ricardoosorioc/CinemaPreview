package com.example.cinema.BD;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.cinema.Model.User;


@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class UserDataBase extends RoomDatabase {

    public abstract com.example.cinema.BD.UserDAO getUserDao();

}