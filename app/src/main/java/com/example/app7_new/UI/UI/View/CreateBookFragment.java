package com.example.app7_new.UI.UI.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.app7_new.R;
import com.example.app7_new.UI.UI.ViewModel.BookViewModel;

public class CreateBookFragment extends Fragment {
    private BookViewModel bookViewModel;
    public CreateBookFragment() {
        super(R.layout.fragment_create_book);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_book, container, false);
        return view;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

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
                String book_name = bookName.getText().toString();
                String book_author = bookAuthor.getText().toString();

                bookViewModel.inputBookParameters(book_name, book_author);
                Navigation.findNavController(view).navigate(R.id.action_createBookFragment_to_addBookFragment);
            }
        });

        Button createRandomButton = requireView().findViewById(R.id.random_button);
        createRandomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bookViewModel.RandomBook();
                Navigation.findNavController(view).navigate(R.id.action_createBookFragment_to_addBookFragment);
            }
        });
    }
}
