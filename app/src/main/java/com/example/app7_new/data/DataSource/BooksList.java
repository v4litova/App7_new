package com.example.app7_new.data.DataSource;

import com.example.app7_new.data.Model.Item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BooksList {
    private Map<Integer, Item> books;
    public BooksList(Map<Integer, Item> books) {
        this.books = books;
    }
    public ArrayList<Item> getBooks() {
        return new ArrayList<Item>(books.values());
    }
    public int addBookToList(Item item) {
        if (books != null) {
            Set<Integer> set = books.keySet();
            if (set.size() > 0) {
                int maxId = Collections.max(set);
                books.put(maxId + 1, item);
                return maxId + 1;
            } else {
                int index = 0;
                books.put(index, item);
                return index;
            }
        } else {
            books = new HashMap<Integer, Item>();
            int index = 0;
            books.put(index, item);
            return index;
        }
    }
    public Item getBookById(int id) {
        return books != null ? books.get(id) : null;
    }
    public boolean updateBookById(int id, Item book) {
        if (books == null || books.get(id) == null) return false;
        books.put(id, book);
        return true;
    }
    public boolean deleteBookById(int id) {
        if (books == null || books.get(id) == null) return false;
        books.remove(id);
        return true;
    }
}
