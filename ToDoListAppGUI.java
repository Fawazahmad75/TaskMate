import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ToDoListAppGUI {
    private List<String> tasks;
    private DefaultListModel<String> listModel;
    private JList<String> taskList;

    public ToDoListAppGUI() {
        tasks = new ArrayList<>();
        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);
    }

    public void addTask(String task) {
        tasks.add(task);
        listModel.addElement(task);
    }

    public void removeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
            listModel.remove(index);
        }
    }

    public void createAndShowGUI() {
        JFrame frame = new JFrame("TaskMate");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JTextField taskTextField = new JTextField();
        taskTextField.setPreferredSize(new Dimension(200, 30));
        panel.add(taskTextField, BorderLayout.NORTH);

        JButton addButton = new JButton("Add Task");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String task = taskTextField.getText();
                if (!task.isEmpty()) {
                    addTask(task);
                    taskTextField.setText("");
                }
            }
        });
        panel.add(addButton, BorderLayout.WEST);

        JButton removeButton = new JButton("Remove Task");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = taskList.getSelectedIndex();
                if (selectedIndex != -1) {
                    removeTask(selectedIndex);
                }
            }
        });
        panel.add(removeButton, BorderLayout.EAST);

        JScrollPane scrollPane = new JScrollPane(taskList);
        panel.add(scrollPane, BorderLayout.CENTER);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ToDoListAppGUI toDoListGUI = new ToDoListAppGUI();
                toDoListGUI.createAndShowGUI();
            }
        });
    }
}