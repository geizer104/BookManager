
import javax.swing.*;
import java.util.*;

public class BookHandler {
    private DefaultListModel<String> bookListModel;
    private JList<String> bookList;
    private JTextField bookInput;
    private JComboBox<String> priorityBox, statusBox;
    private Map<String, String> bookDetails;

    public BookHandler(DefaultListModel<String> bookListModel, JList<String> bookList,
                       JTextField bookInput, JComboBox<String> priorityBox, JComboBox<String> statusBox) {
        this.bookListModel = bookListModel;
        this.bookList = bookList;
        this.bookInput = bookInput;
        this.priorityBox = priorityBox;
        this.statusBox = statusBox;
        this.bookDetails = new HashMap<>();
    }

    public void addBook() {
        String book = bookInput.getText().trim();
        String priority = (String) priorityBox.getSelectedItem();
        String status = (String) statusBox.getSelectedItem();
        if (!book.isEmpty()) {
            String bookEntry = priority + " - " + status + " - " + book;
            bookListModel.addElement(bookEntry);
            bookDetails.put(bookEntry, "Деталі: " + book);
            bookInput.setText("");
        }
    }

    public void removeBook() {
        int selectedIndex = bookList.getSelectedIndex();
        if (selectedIndex != -1) {
            String selectedBook = bookListModel.getElementAt(selectedIndex);
            bookListModel.remove(selectedIndex);
            bookDetails.remove(selectedBook);
        }
    }

    public void updateBookStatus(String status) {
        int selectedIndex = bookList.getSelectedIndex();
        if (selectedIndex != -1) {
            String selectedBook = bookListModel.getElementAt(selectedIndex);
            String[] parts = selectedBook.split(" - ", 3);
            if (parts.length == 3) {
                String updatedBook = parts[0] + " - " + status + " - " + parts[2];
                bookListModel.set(selectedIndex, updatedBook);
            }
        }
    }

    public void clearBooks() {
        bookListModel.clear();
        bookDetails.clear();
    }

    public void sortBooks() {
        List<String> books = new ArrayList<>();
        for (int i = 0; i < bookListModel.size(); i++) {
            books.add(bookListModel.get(i));
        }
        books.sort(Comparator.comparing(book -> book.startsWith("Високий") ? 1 : book.startsWith("Середній") ? 2 : 3));
        bookListModel.clear();
        books.forEach(bookListModel::addElement);
    }

    public void editBook() {
        int selectedIndex = bookList.getSelectedIndex();
        if (selectedIndex != -1) {
            String selectedBook = bookListModel.getElementAt(selectedIndex);
            String[] parts = selectedBook.split(" - ", 3);
            if (parts.length == 3) {
                String newBook = JOptionPane.showInputDialog("Редагувати книгу:", parts[2]);
                if (newBook != null && !newBook.trim().isEmpty()) {
                    String updatedBook = parts[0] + " - " + parts[1] + " - " + newBook.trim();
                    bookListModel.set(selectedIndex, updatedBook);
                }
            }
        }
    }

    public void showStatistics() {
        JOptionPane.showMessageDialog(null, "Кількість книг: " + bookListModel.size(),
                "Статистика", JOptionPane.INFORMATION_MESSAGE);
    }
}
