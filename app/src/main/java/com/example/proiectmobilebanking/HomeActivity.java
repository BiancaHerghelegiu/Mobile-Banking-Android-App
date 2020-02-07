package com.example.proiectmobilebanking;

import android.content.Intent;
import android.os.Bundle;

import com.example.proiectmobilebanking.database.models.Tranzaction;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
private TabLayout tablayout;
private ViewPager viewpager;
private TabItem home,transactions;
public PageAdapter pagerAdapter;
List<Tranzaction> tranz=new ArrayList<>();
public static final String ADD_TRANZACTION_KEY = "addPTranzaction";
    public static final String ADD_TRANZACTION_HISTORY = "addPTranzactionH";
    public static final int REQUEST_CODE_ADD_TRANSACTION = 200;
    Fragment currentFragment;
    List<Tranzaction> tranzactions=new ArrayList<>();
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
         intent=getIntent();
        initComponents();
        Tranzaction t1=new Tranzaction("Bianca","BRD100",100,"send");
        tranz.add((t1));
        //startActivityForResult(intent,REQUEST_CODE_ADD_TRANSACTION);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ADD_TRANSACTION
                && resultCode == RESULT_OK
                && data != null) {
            Tranzaction tranzaction = data.getParcelableExtra(MoneyActivity
                    .ADD_TRANZACTION_HISTORY);
            if (tranzaction != null) {
                Toast.makeText(getApplicationContext(),
                        tranzaction.toString(),
                        Toast.LENGTH_LONG).show();
                tranz.add(tranzaction);
            }
        }
    }

    private void initComponents() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ContactActivity.class);
                startActivity(intent);
            }
        });




        tablayout = findViewById(R.id.tablayout);
        home = findViewById(R.id.tab_home);
        transactions = findViewById(R.id.tab_transactions);
        //history=findViewById(R.id.tab_history);
        viewpager = findViewById(R.id.viewpager);

        pagerAdapter = new PageAdapter(getSupportFragmentManager(), tablayout.getTabCount());
        viewpager.setAdapter(pagerAdapter);

        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 0) {
                    pagerAdapter.notifyDataSetChanged();
                    currentFragment = new FeedbackFragment();
                } else if (tab.getPosition() == 1) {
                    pagerAdapter.notifyDataSetChanged();
                    currentFragment = new AboutFragment();
                }



            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));

    }
}
