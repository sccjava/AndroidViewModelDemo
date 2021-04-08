package com.harman.viewmodeldemo.ui.main;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.harman.viewmodeldemo.R;
import com.harman.viewmodeldemo.databinding.MainFragmentBinding;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        MainFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false);

        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // send command
                mViewModel.sentCommandToDevice(System.currentTimeMillis() / 1000);
            }
        });
        binding.setViewModel(mViewModel);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        // Update UI once received command response
        mViewModel.getData().observe(this, new Observer<MainData>() {
            @Override
            public void onChanged(MainData mainData) {
//                Log.i("TAG", "Received command response, " + mainData.getMessage());
            }
        });
    }

}
