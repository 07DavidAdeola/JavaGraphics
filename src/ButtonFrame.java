import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ButtonFrame extends JFrame implements ActionListener {

    JButton button;
    JLabel label;
    ButtonFrame(){

//        super();
        button = new JButton();
        button.setText("I'm a Button!");
        button.setBounds(50, 50, 150, 50);

        //
        button.addActionListener(this);

        JLabel label = new JLabel();
        label.setText("Button is clicked and I appear");
        label.setBounds(50, 80, 150, 50);
        label.setVisible(false);

        //this = refers to the frame
        this.setSize(500, 500);
        this.setTitle("My First Frame with label");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(button);
        this.add(label);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button){
            System.out.println("My first button");
            label.setVisible(true);
            button.setEnabled(false);
        }
    }
}