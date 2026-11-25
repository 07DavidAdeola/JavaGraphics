import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MyTextField extends JFrame implements ActionListener {

    JButton button;
    JTextField textField;

    JTextField usernameField;
    JTextField passwordField;

    JLabel usernameLabel;
    JLabel passwordLabel;


    MyTextField() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());

        usernameLabel = new JLabel("Username:");

        // Username text field
        usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(250, 30));

        passwordLabel = new JLabel("Password:");

        // Password text field
        passwordField = new JTextField();
        passwordField.setPreferredSize(new Dimension(250, 30));

        button = new JButton("Login");
        button.addActionListener(this);

        this.add(usernameLabel);
        this.add(usernameField);

        this.add(passwordLabel);
        this.add(passwordField);

        this.add(button);

        this.setTitle("Login System");
        this.setSize(350, 200);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == button) {
            String enteredUsername = usernameField.getText();

            String enteredPassword = passwordField.getText();

            ArrayList<User> users = new ArrayList<>();

            try {

                File file = new File("user.txt");

                System.out.println(file.getAbsolutePath());

                BufferedReader reader = new BufferedReader(
                        new FileReader("Users.txt"));

                reader.readLine();

                String line;

                while ((line = reader.readLine()) != null) {

                    String[] data = line.split(",");

                    String username = data[0].trim();
                    String password = data[1].trim();

                    User user = new User(username, password);

                    users.add(user);
                }

                reader.close();

                boolean found = false;

                for (User user : users) {

                    if (enteredUsername.equals(user.username) && enteredPassword.equals(user.password)) {

                        found = true;
                        break;

                    }
                }

                if (found) {
                    new StudentManagement();
                    this.dispose();
                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Incorrect Username or Password, try again!"
                    );

                }

            } catch (IOException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Error reading users.txt: " + ex.getMessage());

            }
        }
    }
}




//        if (e.getSource() == button) {
//            System.out.println(textField.getText());
//            button.setEnabled(false);
//            textField.setEditable(false);
//        }
//    }
//}
