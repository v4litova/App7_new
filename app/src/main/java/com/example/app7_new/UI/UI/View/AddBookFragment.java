package com.example.app7_new.UI.UI.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.app7_new.R;
import com.example.app7_new.UI.UI.ViewModel.BookViewModel;
import com.example.app7_new.UI.UI.ViewModel.BooksListViewModel;
import com.example.app7_new.data.Model.Item;

public class AddBookFragment extends Fragment {

    public AddBookFragment(){
        super(R.layout.fragment_add_book);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_book, container, false);
        return view;
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        TextView bookName = view.findViewById(R.id.text_name);
        TextView bookAuthor = view.findViewById(R.id.text_author);

        Button button_add = view.findViewById(R.id.add_button);
        Button button_back = view.findViewById(R.id.back_button);

        BookViewModel bookViewModel = new ViewModelProvider(requireActivity()).get(BookViewModel.class);
        if (getArguments() == null){
            bookViewModel.getUIState().observe(getViewLifecycleOwner(), uiState -> {
                bookName.setText("Наименование: " + uiState.getBookName());
                bookAuthor.setText("Автор: " + uiState.getBookAuthor());
            });
            button_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Navigation.findNavController(view).navigate(R.id.action_addBookFragment_to_createBookFragment);
                }
            });
        }
        else{
            Item book = (Item) getArguments().getSerializable("Item");
            String[] parts = book.getName().split(" ");
            bookName.setText("Наименование: " + parts[0]);
            bookAuthor.setText("Автор: " + parts[1]);

            button_add.setVisibility(View.GONE);

            button_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Navigation.findNavController(view).navigate(R.id.action_addBookFragment_to_listFragment);
                }
            });
        }

        BooksListViewModel booksListViewModel = new ViewModelProvider(getActivity()).get(BooksListViewModel.class);
        button_add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Item book = bookViewModel.getUIState().getValue().getBook();
                booksListViewModel.addGoodToOrder(book);
                bookViewModel.inputBookParameters(null, null);
                Navigation.findNavController(view).navigate(R.id.action_addBookFragment_to_listFragment);
            }
        });
    }
}