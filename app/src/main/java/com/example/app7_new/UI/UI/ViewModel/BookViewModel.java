package com.example.app7_new.UI.UI.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.app7_new.data.Repository.BookRepository;

import java.util.Random;

public class BookViewModel extends ViewModel {
    String[] bookNames = {"Успех", "Приключения", "Счастье", "Любовь"};
    String[] bookAuthors = {"Иванов", "Смирнова", "Петров", "Козлова"};
    private final MutableLiveData<BookRepository> uiState =
            new MutableLiveData(new BookRepository(null, null));
    public LiveData<BookRepository> getUIState() {
        return uiState;
    }

    public void inputBookParameters(String BookName, String BookAuthor){
        uiState.setValue(
                new BookRepository(BookName, BookAuthor)
        );
    }
    public void RandomBook() {
        Random random = new Random();
        uiState.setValue(
                new BookRepository(
                        bookNames[random.nextInt(4)],
                        bookAuthors[random.nextInt(4)]
                )
        );
    }
}
