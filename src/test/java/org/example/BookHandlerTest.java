package org.example;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTextField;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BookHandlerTest {
    private BookHandler bookHandler;
    private DefaultListModel<String> bookListModel;
    private JList<String> bookList;
    private JTextField bookInput;
    private JComboBox<String> priorityBox;
    private JComboBox<String> statusBox;

    @Before
    public void setUp() {
        bookListModel = new DefaultListModel<>();
        bookList = new JList<>(bookListModel);
        bookInput = new JTextField();
        priorityBox = new JComboBox<>(new String[]{"Високий", "Середній", "Низький"});
        statusBox = new JComboBox<>(new String[]{"Не розпочато", "Читається", "Прочитано"});
        bookHandler = new BookHandler(bookListModel, bookList, bookInput, priorityBox, statusBox);
    }

    @Test
    public void testAddBook() {
        bookInput.setText("Гаррі Поттер");
        priorityBox.setSelectedItem("Високий");
        statusBox.setSelectedItem("Читається");
        bookHandler.addBook();
        Assert.assertEquals(1, bookListModel.size());
        Assert.assertEquals("Високий - Читається - Гаррі Поттер", bookListModel.getElementAt(0));
    }

    @Test
    public void testRemoveBook() {
        bookListModel.addElement("Високий - Читається - Гаррі Поттер");
        bookList.setSelectedIndex(0);
        bookHandler.removeBook();
        Assert.assertEquals(0, bookListModel.size());
    }

    @Test
    public void testUpdateBookStatus() {
        bookListModel.addElement("Високий - Читається - Гаррі Поттер");
        bookList.setSelectedIndex(0);
        bookHandler.updateBookStatus("Прочитано");
        Assert.assertEquals("Високий - Прочитано - Гаррі Поттер", bookListModel.getElementAt(0));
    }

    @Test
    public void testClearBooks() {
        bookListModel.addElement("Високий - Читається - Гаррі Поттер");
        bookListModel.addElement("Середній - Не розпочато - Війна і мир");
        bookHandler.clearBooks();
        Assert.assertEquals(0, bookListModel.size());
    }
}
