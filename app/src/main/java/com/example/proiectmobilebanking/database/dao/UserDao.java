package com.example.proiectmobilebanking.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.proiectmobilebanking.database.models.User;

import java.util.List;
@Dao
public interface UserDao {
    @Query("SELECT * FROM users where email= :email and password= :password")
    User getUserByEmail(String email, String password);

    @Query("select * from users")
    List<User> getAll();

    @Query("select * from users where id=:id")
    User getUserById(long id);
    @Insert
    long insert(User user);

    @Delete
    int delete(User user);

    @Query("select email from users where id=:id")
    String getUserEmailById(long id);
}
