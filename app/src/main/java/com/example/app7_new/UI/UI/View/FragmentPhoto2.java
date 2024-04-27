package com.example.app7_new.UI.UI.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.app7_new.R;


public class FragmentPhoto2 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_photo2, container, false);

        ImageView imageView = view.findViewById(R.id.imageView4);
        Button button = view.findViewById(R.id.button4);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipCard();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment, new ListFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }

    private void flipCard() {
        FragmentPhoto1 fragmentPhoto1 = new FragmentPhoto1();
        FragmentTransaction fragmentTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(
                R.animator.blue_books_flip_in,
                R.animator.blue_books_flip_out,
                R.animator.dif_books_flip_in,
                R.animator.dif_books_flip_out
        );
        fragmentTransaction.replace(R.id.nav_host_fragment, fragmentPhoto1);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}