
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class AddStudent extends JFrame
        implements ActionListener {

    JTextField tf1, tf2, tf3, tf4, tf5;

    JButton saveButton, closeButton;

    JFrame parent;

    public AddStudent(JFrame parent) {

        this.parent = parent;

        setTitle("Add New Student");
        setSize(450, 400);
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

        saveButton = new JButton("Save");
        closeButton = new JButton("Close");

        add(saveButton);
        add(closeButton);

        saveButton.addActionListener(this);
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

        if (e.getSource() == saveButton) {

            String fn = tf1.getText().trim();
            String ln = tf2.getText().trim();
            String address = tf3.getText().trim();
            String id = tf4.getText().trim();
            String adviser = tf5.getText().trim();

            if (fn.isEmpty() || ln.isEmpty() ||
                    address.isEmpty() || id.isEmpty() ||
                    adviser.isEmpty()) {

                JOptionPane.showMessageDialog(this,
                        "Please fill in all fields.");

                return;
            }

            try {

                File file = new File("students.txt");

                boolean exists = false;

                if (file.exists()) {

                    BufferedReader reader =
                            new BufferedReader(new FileReader(file));

                    String line;

                    while ((line = reader.readLine()) != null) {

                        String[] data = line.split(";", -1);

                        if (data.length == 5 &&
                                data[3].equals(id)) {

                            exists = true;
                            break;
                        }
                    }

                    reader.close();
                }

                if (exists) {

                    JOptionPane.showMessageDialog(this,
                            "Student ID already exists!");

                    return;
                }

                FileWriter fw = new FileWriter(file, true);

                String studentInfo =
                        fn + ";" + ln + ";" + address +
                                ";" + id + ";" + adviser;

                fw.write(studentInfo + System.lineSeparator());

                fw.close();

                JOptionPane.showMessageDialog(this,
                        "Student information saved successfully!");

                tf1.setText("");
                tf2.setText("");
                tf3.setText("");
                tf4.setText("");
                tf5.setText("");

            }

            catch (IOException ex) {

                JOptionPane.showMessageDialog(this,
                        "Error saving student: " + ex.getMessage());
            }
        }

        else if (e.getSource() == closeButton) {

            dispose();
        }
    }
}
