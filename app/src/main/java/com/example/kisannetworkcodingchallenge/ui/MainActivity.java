package com.example.kisannetworkcodingchallenge.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.Toast;

import com.example.kisannetworkcodingchallenge.R;
import com.example.kisannetworkcodingchallenge.data.MainRepo;
import com.example.kisannetworkcodingchallenge.ui.mainScreen.TabsFragment;

public class MainActivity extends AppCompatActivity{

    private FragmentManager fm = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Router.INSTANCE.init(getSupportFragmentManager());

        if (savedInstanceState==null){
            Router.INSTANCE.showMainScreen();
        }
    }
}
