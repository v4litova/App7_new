package com.example.app7_new.data.Repository;

import com.example.app7_new.data.DataSource.CurrentBook;
import com.example.app7_new.data.Model.Item;

public class BookRepository {
    private CurrentBook currentBook;
    private String BookName = "test";
    private String BookAuthor = "test";
    public BookRepository() {}
    public BookRepository(String BookName) {
        this.BookName = BookName;
        currentBook = new CurrentBook(new Item(BookName,0));
    }
    public BookRepository(String BookName, String BookAuthor) {
        this.BookAuthor = BookAuthor;
        this.BookName = BookName;
        currentBook = new CurrentBook(new Item(BookName + ", " + BookAuthor, 0 ));
    }
    public void setBookName(String bookName) {
        this.BookName = bookName;
        currentBook.SetCurrentBook(new Item(bookName,0));
    }
    public void setBookAuthor(String bookAuthor) {
        this.BookAuthor = bookAuthor;
    }
    public String getBookName() {
        return BookName;
    }
    public String getBookAuthor() {
        return BookAuthor;
    }
    public Item getBook(){
        return currentBook.getCurrentBook();
    }
}
