import javax.swing.*;
import java.awt.*;

public class BookManager {
    private JFrame frame;
    private DefaultListModel<String> bookListModel;
    private JList<String> bookList;
    private JTextField bookInput;
    private JComboBox<String> priorityBox, statusBox;
    private JButton addButton, removeButton, completedButton, readingButton, notStartedButton, clearButton, sortButton, editButton, statsButton;

    public BookManager() {
        frame = new JFrame("Менеджер Книг");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(144, 238, 144));

        bookListModel = new DefaultListModel<>();
        bookList = new JList<>(bookListModel);
        frame.add(new JScrollPane(bookList), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.setBackground(new Color(152, 251, 152));

        bookInput = new JTextField();
        priorityBox = new JComboBox<>(new String[]{"Високий", "Середній", "Низький"});
        statusBox = new JComboBox<>(new String[]{"Не розпочато", "Читається", "Прочитано"});
        addButton = createStyledButton("Додати Книгу");

        inputPanel.add(bookInput, BorderLayout.CENTER);
        inputPanel.add(priorityBox, BorderLayout.WEST);
        inputPanel.add(statusBox, BorderLayout.EAST);
        inputPanel.add(addButton, BorderLayout.SOUTH);
        frame.add(inputPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBackground(new Color(152, 251, 152));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;

        removeButton = createStyledButton("Видалити Книгу");
        completedButton = createStyledButton("Прочитано");
        readingButton = createStyledButton("Читається");
        notStartedButton = createStyledButton("Не розпочато");
        clearButton = createStyledButton("Очистити Все");
        sortButton = createStyledButton("Сортувати");
        editButton = createStyledButton("Редагувати");
        statsButton = createStyledButton("Статистика");

        buttonPanel.add(removeButton, gbc);
        gbc.gridx = 1;
        buttonPanel.add(completedButton, gbc);
        gbc.gridx = 2;
        buttonPanel.add(readingButton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        buttonPanel.add(notStartedButton, gbc);
        gbc.gridx = 1;
        buttonPanel.add(clearButton, gbc);
        gbc.gridx = 2;
        buttonPanel.add(sortButton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        buttonPanel.add(editButton, gbc);
        gbc.gridx = 1;
        buttonPanel.add(statsButton, gbc);

        JButton exitButton = createStyledButton("Вийти");
        exitButton.setBackground(new Color(255, 69, 69));
        exitButton.addActionListener(e -> System.exit(0));
        gbc.gridx = 2;
        gbc.gridy = 3;
        buttonPanel.add(exitButton, gbc);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        BookHandler handler = new BookHandler(bookListModel, bookList, bookInput, priorityBox, statusBox);

        addButton.addActionListener(e -> handler.addBook());
        removeButton.addActionListener(e -> handler.removeBook());
        completedButton.addActionListener(e -> handler.updateBookStatus("Прочитано"));
        readingButton.addActionListener(e -> handler.updateBookStatus("Читається"));
        notStartedButton.addActionListener(e -> handler.updateBookStatus("Не розпочато"));
        clearButton.addActionListener(e -> handler.clearBooks());
        sortButton.addActionListener(e -> handler.sortBooks());
        editButton.addActionListener(e -> handler.editBook());
        statsButton.addActionListener(e -> handler.showStatistics());

        frame.setVisible(true);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(100, 149, 237));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BookManager::new);
    }
}
