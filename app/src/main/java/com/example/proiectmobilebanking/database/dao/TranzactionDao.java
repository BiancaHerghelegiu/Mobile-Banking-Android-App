package com.example.proiectmobilebanking.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.proiectmobilebanking.database.models.Tranzaction;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface TranzactionDao {

    @Query("SELECT * from tranzactions where idUser=:idUser")
    List<Tranzaction> tranzactionsByUser(long idUser);

    @Query("select * from tranzactions")
    List<Tranzaction> getAllTransactions();

    @Insert
    long insert(Tranzaction tranzaction);

    @Delete
    int delete(Tranzaction tranzaction);

    @Update
    void update(Tranzaction tranzaction);

    @Query("select * from tranzactions where idUser=:idUser and amount> :amount")
    List<Tranzaction> tranzactionsByAmount(long idUser,Integer amount);

    @Query("select beneficiaryName from tranzactions where idUser= :idUser and status='Send'")
    List<String> transactionsSend(long idUser);

    @Query("select SUM(amount) from tranzactions where idUser=:idUser and status='Send'")
    Integer sumAmount(long idUser);

    @Query("select SUM(amount) from tranzactions where idUser=:idUser and status='Ask'")
    Integer sumAsk(long idUser);
}
