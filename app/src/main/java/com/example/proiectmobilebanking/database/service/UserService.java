package com.example.proiectmobilebanking.database.service;

import android.content.Context;
import android.os.AsyncTask;

import com.example.proiectmobilebanking.database.DatabaseManager;
import com.example.proiectmobilebanking.database.dao.UserDao;
import com.example.proiectmobilebanking.database.models.User;

import java.util.List;

public class UserService {
    private static UserDao userDao;

    public static class GetAll
            extends AsyncTask<Void, Void, List<User>> {

        public GetAll(Context context) {
            userDao = DatabaseManager
                    .getInstance(context)
                    .getUserDao();
        }

        @Override
        protected List<User> doInBackground(Void... voids) {
            return userDao.getAll();
        }
    }


    public static class Insert extends
            AsyncTask<User, Void, User> {
        public Insert(Context context) {
            userDao = DatabaseManager
                    .getInstance(context)
                    .getUserDao();
        }

        @Override
        protected User doInBackground(User... users) {
            if (users == null || users.length != 1) {
                return null;
            }
            User user = users[0];
            long id = userDao.insert(user);
            if (id != -1) {
                user.setId(id);
                return user;
            }
            return null;
        }
    }

    public static class Delete extends
            AsyncTask<User, Void, Integer> {
        public Delete(Context context) {
            userDao = DatabaseManager
                    .getInstance(context)
                    .getUserDao();
        }

        @Override
        protected Integer doInBackground(User... users) {
            if (users == null || users.length != 1) {
                return -1;
            }
            return userDao.delete(users[0]);
        }
    }
}
