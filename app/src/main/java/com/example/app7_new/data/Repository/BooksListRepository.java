package com.example.app7_new.data.Repository;


import com.example.app7_new.data.Model.Item;
import com.example.app7_new.data.DataSource.BooksList;
import java.util.ArrayList;
import java.util.HashMap;

public class BooksListRepository {
    BooksList booksList = null;
    public BooksListRepository(ArrayList<Item> values) {
        if (values != null) {
            this.booksList = new BooksList(new HashMap<Integer, Item>());
            for (Item good : values) {
                booksList.addBookToList(good);
            }
        }
    }
    public ArrayList<Item> getBooksPositions() {
        if (booksList != null) return booksList.getBooks();
        return null;
    }
    public void setBooksPositions(ArrayList<Item> orderedPositions) {
        if (booksList == null)
            booksList = new BooksList(new HashMap<Integer, Item>());
        for (Item book : orderedPositions) {
            booksList.addBookToList(book);
        }
    }
    public void putBook(Item book) {
        if (booksList != null)
            booksList.addBookToList(book);
        else {
            booksList = new BooksList(new HashMap<Integer, Item>());
            booksList.addBookToList(book);
        }
    }
    public Item getBook(int position) {
        if (booksList != null)
            return booksList.getBookById(position);
        return null;
    }
}
