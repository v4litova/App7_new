package com.example.app7_new.UI.UI.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.app7_new.R;

public class Fragment2 extends Fragment {
    public Fragment2() {
        super(R.layout.fragment_2);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_2, container, false);
        EditText editText = view.findViewById(R.id.editText);
        Button btn = view.findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Navigation.findNavController(view).navigate(R.id.action_fragment2_to_createBookFragment);
                getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(
                                R.anim.fade_out,
                                0,
                                0,
                                0
                        )
                        .replace(R.id.nav_host_fragment, new CreateBookFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
        return view;
    }
}