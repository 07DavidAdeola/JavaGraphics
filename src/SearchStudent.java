import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class SearchStudent extends JFrame
        implements ActionListener {

    JTextField tf1, tf2, tf3, tf4, tf5;

    JButton searchButton, updateButton, closeButton;

    JFrame parent;

    public SearchStudent(JFrame parent) {

        this.parent = parent;

        setTitle("Search Student");
        setSize(500, 400);
        setLayout(new GridLayout(7, 2, 10, 10));

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parent);

        add(new JLabel("First Name:"));
        tf1 = new JTextField();
        add(tf1);

        add(new JLabel("Last Name:"));
        tf2 = new JTextField();
        add(tf2);

        add(new JLabel("Address:"));
        tf3 = new JTextField();
        add(tf3);

        add(new JLabel("Student ID:"));
        tf4 = new JTextField();
        add(tf4);

        add(new JLabel("Adviser:"));
        tf5 = new JTextField();
        add(tf5);

        searchButton = new JButton("Search");
        updateButton = new JButton("Update");
        closeButton = new JButton("Close");

        add(searchButton);
        add(updateButton);

        // Put Close button in a separate panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(closeButton);

        add(new JLabel(""));
        add(bottomPanel);

        searchButton.addActionListener(this);
        updateButton.addActionListener(this);
        closeButton.addActionListener(this);

        parent.setEnabled(false);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                parent.setEnabled(true);
                parent.toFront();
            }
        });

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == searchButton) {

            String id = tf4.getText().trim();

            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Enter a Student ID.");
                return;
            }

            try {

                BufferedReader reader =
                        new BufferedReader(
                                new FileReader("src/students.txt"));

                String line;
                boolean found = false;

                while ((line = reader.readLine()) != null) {

                    String[] data = line.split(";", -1);

                    if (data.length == 5 &&
                            data[3].equals(id)) {

                        tf1.setText(data[0]);
                        tf2.setText(data[1]);
                        tf3.setText(data[2]);
                        tf5.setText(data[4]);

                        found = true;
                        break;
                    }
                }

                reader.close();

                if (found) {

                    JOptionPane.showMessageDialog(this,
                            "Student found!");

                } else {

                    JOptionPane.showMessageDialog(this,
                            "Student not found.");

                    tf1.setText("");
                    tf2.setText("");
                    tf3.setText("");
                    tf5.setText("");
                }

            }

            catch (IOException ex) {

                JOptionPane.showMessageDialog(this,
                        "Error reading file: " + ex.getMessage());
            }
        }

        else if (e.getSource() == updateButton) {

            String id = tf4.getText().trim();

            if (id.isEmpty() || tf1.getText().trim().isEmpty()
                    || tf2.getText().trim().isEmpty()
                    || tf3.getText().trim().isEmpty()
                    || tf5.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(this,
                        "Search for a student and fill in all fields first.");

                return;
            }

            try {

                File file = new File("students.txt");

                ArrayList<String> records = new ArrayList<>();

                BufferedReader reader =
                        new BufferedReader(new FileReader(file));

                String line;
                boolean found = false;

                while ((line = reader.readLine()) != null) {

                    String[] data = line.split(";", -1);

                    if (data.length == 5 &&
                            data[3].equals(id)) {

                        line = tf1.getText().trim() + ";" +
                                tf2.getText().trim() + ";" +
                                tf3.getText().trim() + ";" +
                                id + ";" +
                                tf5.getText().trim();

                        found = true;
                    }

                    records.add(line);
                }

                reader.close();

                if (found) {

                    FileWriter fw = new FileWriter(file);

                    for (String record : records) {

                        fw.write(record + System.lineSeparator());
                    }

                    fw.close();

                    JOptionPane.showMessageDialog(this,
                            "Student information updated successfully!");

                } else {

                    JOptionPane.showMessageDialog(this,
                            "Student ID not found. Search first.");
                }

            }

            catch (IOException ex) {

                JOptionPane.showMessageDialog(this,
                        "Error updating file: " + ex.getMessage());
            }
        }

        else if (e.getSource() == closeButton) {

            dispose();
        }
    }
}
