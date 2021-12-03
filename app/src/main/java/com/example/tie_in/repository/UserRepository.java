package com.example.tie_in.repository;

import  android.content.Context;
import android.os.Build;
import android.telephony.SmsManager;

import com.example.tie_in.data.Status;
import com.example.tie_in.room.dao.UserDao;
import com.example.tie_in.room.model.User;

import java.util.Random;

public class UserRepository extends Repository {
    UserDao userDao;

    public UserRepository(Context context) {
        super(context);
        userDao = database.getUserDao();
    }

    public Status login(String phone, String password) {
        User user = userDao.getUser();
        if (phone.equals(user.getPhone()) && password.equals(user.getPassword()))
            return Status.SUCCESS;
        else
            return Status.ERROR;
    }

    public String register(String phone, String name, String email) {
        User user = userDao.getUser();
        if(user != null && phone.equals(user.getPhone()))
            return Status.ERROR.getCode() + ": Number already Register!!";

        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        String autoPass = String.format("%06d", number);

        try {
            SmsManager sms = SmsManager.getDefault(); // using android SmsManager sms.sendTextMessage(phone_Num, null, send_msg, null, null); // adding number and text
            sms.sendTextMessage(phone, null, "Welcome to Tie-IN\n Your login Password is "+autoPass, null, null);
        } catch (Exception e) {
            return Status.ERROR.getCode() + ": " + e.toString();
        }

        User newUser = new User(phone, name, null, email, autoPass);
        userDao.insertUser(newUser);
        return Status.SUCCESS.getCode();
    }
}
