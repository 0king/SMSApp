package com.example.kisannetworkcodingchallenge.ui.mainScreen;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.kisannetworkcodingchallenge.ui.mainScreen.tab_1.ContactsFragment;
import com.example.kisannetworkcodingchallenge.ui.mainScreen.tab_2.MessagesFragment;

import static com.example.kisannetworkcodingchallenge.ui.mainScreen.TabsFragment.TAB_COUNT;

public class TabsPagerAdapter extends FragmentStateAdapter {
    public TabsPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position==0){
            return new ContactsFragment();
        }else {
            return new MessagesFragment();
        }
    }

    @Override
    public int getItemCount() {
        return TAB_COUNT;
    }
}
