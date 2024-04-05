package com.example.app7_new.UI.UI.ViewModel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.app7_new.data.Model.Item;
import com.example.app7_new.data.Repository.BooksListRepository;

import java.util.ArrayList;

public class BooksListViewModel extends ViewModel {
    private final MutableLiveData<BooksListRepository> uiState =
            new MutableLiveData(new BooksListRepository(null));
    public LiveData<BooksListRepository> getUIState() {
        return uiState;
    }
    public void addGoodToOrder(Item item)  {
        BooksListRepository bookList = uiState.getValue();
        if (bookList != null) {
            bookList.putBook(item);
        }
        else {
            ArrayList<Item> items = new ArrayList<>();
            items.add(item);
            bookList = new BooksListRepository(items);
        }
        uiState.setValue(bookList);
    }
}
