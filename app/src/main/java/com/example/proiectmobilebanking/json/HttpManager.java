package com.example.proiectmobilebanking.json;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpManager extends AsyncTask<String, Void, String>
{
private URL url;
private HttpURLConnection connection;
private InputStream inputStream;
private InputStreamReader inputStreamReader;
private BufferedReader bufferedReader;

    @Override
    protected String doInBackground(String... strings) {
        StringBuilder result=new StringBuilder();
        try {
            url=new URL(strings[0]);
            connection=(HttpURLConnection)url.openConnection();
            inputStream=connection.getInputStream();
            inputStreamReader=new InputStreamReader(inputStream);
            bufferedReader=new BufferedReader(inputStreamReader);
            String line;
            while((line=bufferedReader.readLine())!=null){
                result.append(line);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally
            {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                connection.disconnect();
            }
            return result.toString();
        }
    }

