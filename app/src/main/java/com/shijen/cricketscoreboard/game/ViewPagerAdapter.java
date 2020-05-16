package com.shijen.cricketscoreboard.game;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.shijen.cricketscoreboard.game.pitch.PitchFragment;
import com.shijen.cricketscoreboard.game.scoreboard.FragmentScoreboard;

public class ViewPagerAdapter extends FragmentStateAdapter {


    public ViewPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position == 0 ){
            return PitchFragment.getInstance();
        }else{
            return FragmentScoreboard.getInstance();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
