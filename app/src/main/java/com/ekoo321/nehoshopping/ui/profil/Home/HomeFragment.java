package com.ekoo321.nehoshopping.ui.profil.Home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.ekoo321.nehoshopping.R;

public class HomeFragment {

    public class SlideshowFregmant extends Fragment {

        public View onCreateView(@NonNull LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState){
            View root = inflater.inflate(R.layout.fragment_home,container,false);
            return root;
        }

    }

}
