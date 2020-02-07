package com.example.proiectmobilebanking.database.service;

import android.content.Context;
import android.os.AsyncTask;

import com.example.proiectmobilebanking.database.DatabaseManager;
import com.example.proiectmobilebanking.database.dao.TranzactionDao;
import com.example.proiectmobilebanking.database.dao.UserDao;
import com.example.proiectmobilebanking.database.models.Tranzaction;
import com.example.proiectmobilebanking.database.models.User;

import java.util.List;

public class TranzactionService {
    private static TranzactionDao transactionDao;

    public static class GetAllTransactions
            extends AsyncTask<Void, Void, List<Tranzaction>> {

        public GetAllTransactions(Context context) {
            transactionDao = DatabaseManager
                    .getInstance(context)
                    .getTranzactionDao();
        }

        @Override
        protected List<Tranzaction> doInBackground(Void... voids) {
            return transactionDao.getAllTransactions();
        }
    }


    public static class Insert extends
            AsyncTask<Tranzaction, Void, Tranzaction> {
        public Insert(Context context) {
            transactionDao = DatabaseManager
                    .getInstance(context)
                    .getTranzactionDao();
        }

        @Override
        protected Tranzaction doInBackground(Tranzaction... tranzactions) {
            if (tranzactions == null || tranzactions.length != 1) {
                return null;
            }
            Tranzaction tranzaction = tranzactions[0];
            long id = transactionDao.insert(tranzaction);
            if (id != -1) {
                tranzaction.setIdTranzactie(id);
                return tranzaction;
            }
            return null;
        }
    }

    public static class Delete extends
            AsyncTask<Tranzaction, Void, Integer> {
        public Delete(Context context) {
            transactionDao = DatabaseManager
                    .getInstance(context)
                    .getTranzactionDao();
        }

        @Override
        protected Integer doInBackground(Tranzaction... transactions) {
            if (transactions == null || transactions.length != 1) {
                return -1;
            }
            return transactionDao.delete(transactions[0]);
        }
    }
}
