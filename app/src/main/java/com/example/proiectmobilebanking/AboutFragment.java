package com.example.proiectmobilebanking;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends Fragment {
ImageView img;
Button btnMaps;


    public AboutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View view =inflater.inflate(R.layout.fragment_about, container, false);
      img=view.findViewById(R.id.imgDebitCard);
      //Uri uri= Uri.parse("http://i.imgur.com/bIRGzVO.jpg");
      //img.setImageURI(uri);
        btnMaps=view.findViewById(R.id.btnMaps);
        btnMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),MapsActivity.class);
                startActivity(intent);
            }
        });
        final Drawable[] d = new Drawable[1];
        new AsyncTask<Void, Void, Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    URL url = new URL("https://www.exiap.com/wp-content/uploads/2019/07/How_to_Transfer_Money_Overseas_without_Fees.png?x95487");
                    InputStream content = (InputStream) url.getContent();
                     d[0] = Drawable.createFromStream(content, "src");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                if(d[0]!=null){
                    img.setImageDrawable(d[0]);
                }
            }
        }.execute();

        return  view;
    }



}
