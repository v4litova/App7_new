package com.example.app7_new.UI.UI.View;

import android.os.Bundle;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.app7_new.R;
import com.example.app7_new.UI.UI.ViewModel.BookViewModel;

public class CreateBookFragment extends Fragment {
    private BookViewModel bookViewModel;
    private NavController navController;
    public CreateBookFragment() {
        super(R.layout.fragment_create_book);

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TransitionInflater inflater = TransitionInflater.from(requireContext());
        setExitTransition(inflater.inflateTransition(R.transition.fade));
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_book, container, false);
        return view;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        // navController = Navigation.findNavController(view);

        EditText bookName = view.findViewById(R.id.book_name_edit_text);
        EditText bookAuthor = view.findViewById(R.id.book_author_edit_text);

        BookViewModel bookViewModel = new ViewModelProvider(requireActivity()).get(BookViewModel.class);
        bookViewModel.getUIState().observe(getViewLifecycleOwner(), uiState -> {
            if (uiState.getBookName() != null && uiState.getBookAuthor()!= null){
                bookName.setText(uiState.getBookName());
                bookAuthor.setText(uiState.getBookAuthor());
            }
        });

        Button createButton = requireView().findViewById(R.id.create_button);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment, new AddBookFragment())
                        .addToBackStack(null)
                        .commit();

            }
        });

    }
}
