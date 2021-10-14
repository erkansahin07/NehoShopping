package com.ekoo321.nehoshopping.ui.profil.category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.ekoo321.nehoshopping.R;

public class CategoryFragment {

    public class SlideshowFregmant extends Fragment {

        public View onCreateView(@NonNull LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState){
            View root = inflater.inflate(R.layout.fragment_category,container,false);
            return root;
        }

    }

}
