package com.example.autotalk;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FragmentAdapter extends FragmentStateAdapter {

    public FragmentAdapter(FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new CarDoorsFragment();
            case 1:
                return new CarThermostatFragment();
            case 2:
                return new CarLightFragment();
            case 3:
                return new CarSensorsFragment();
            case 4:
                return new CarConnectsFragment();
            default:
                throw new IllegalStateException("Unexpected position: " + position);
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}

