package com.example.app7_new.data.DataSource;

import com.example.app7_new.data.Model.Item;

public class CurrentBook {
    private final Item[] currentItem = new Item[1];
    public CurrentBook (Item book) {
        this.currentItem[0] = book;
    }
    public Item getCurrentBook() {
        return currentItem[0];
    }
    public void SetCurrentBook(Item book) {
        this.currentItem[0] = book;
    }
}
