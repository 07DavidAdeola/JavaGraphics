import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StudentManagement extends JFrame
        implements ActionListener {

    private JButton addButton;
    private JButton searchButton;

    public StudentManagement() {
        setTitle("Student Management System");
        setSize(400, 200);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addButton = new JButton("Add New Student");
        searchButton = new JButton("Search Student");

        add(addButton);
        add(searchButton);

        // Register event listeners
        addButton.addActionListener(this);
        searchButton.addActionListener(this);

        setVisible(true);

        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            System.out.println("Add New Student clicked");
        }
        if (e.getSource() == addButton) {
            new AddStudent(this);
        }
        else if (e.getSource() == searchButton) {
            System.out.println("Search Student clicked");
        }
        if (e.getSource() == searchButton){
            new SearchStudent(this);
        }
    }

    public static void main(String[] args) {
        new StudentManagement();
    }
}
