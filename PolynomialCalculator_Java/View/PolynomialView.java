package View;

import Model.Polynomial;
import Model.PolynomialModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PolynomialView extends JFrame {

    private PolynomialModel polynomialModel;
    private JTextField input1 = new JTextField(60);
    private JTextField input2 = new JTextField(60);
    private JTextField output1 = new JTextField(65);
    private JTextField output2 = new JTextField(65);
    private JButton mulBtn = new JButton("    x    ");
    private JButton addBtn = new JButton("    +    ");
    private JButton subBtn = new JButton("    -    ");
    private JButton divBtn = new JButton("    /    ");
    private JButton derivABtn = new JButton("Deriv A");
    private JButton inteABtn = new JButton( "Integ A");
    private JButton derivBBtn = new JButton("Deriv B");
    private JButton inteBBtn = new JButton( "Integ B");
    private JButton clearBtn = new JButton("Clear");
    private JButton clearABtn = new JButton("Clear A");
    private JButton clearBBtn = new JButton("Clear B");
    private JButton moveResultToABtn = new JButton("Move result to A");
    private JButton moveResultToBBtn = new JButton("Move result to B");
    private JButton moveReminderToABtn = new JButton("Move reminder to A");
    private JButton moveReminderToBBtn = new JButton("Move reminder to B");
    private JLabel operation = new JLabel("operation");
    private JPanel reminder = new JPanel();
    private JPanel result = new JPanel();
    private JLabel resultLabel = new JLabel("    Result        ");
    private JPanel pVert6 = new JPanel();

    public PolynomialView(PolynomialModel polynomialModel) {
        this.polynomialModel = polynomialModel;

        JPanel content = new JPanel();
        content.setLayout(new FlowLayout());
        content.add(new JLabel("Input Polynomial (ax^n+x-a+x^n) Ex: 2x^2+7-x"));

        JPanel l1 = new JPanel();
        l1.add(new JLabel("Polynomyal A"));
        l1.add(input1);
        l1.add(clearABtn);
        content.add(l1);

        JPanel l6 = new JPanel();
        l6.add(operation);
        content.add(l6);

        JPanel l2 = new JPanel();
        l2.add(new JLabel("Polynomyal B"));
        l2.add(input2);
        l2.add(clearBBtn);
        content.add(l2);

        JPanel l8 = new JPanel();
        l8.add(new JLabel("    =    "));
        content.add(l8);

        output1.setEditable(false);
        result.add(resultLabel);
        result.add(output1);
        content.add(result);

        reminder.add(new JLabel("Reminder      "));
        output2.setEditable(false);
        reminder.add(output2);
        content.add(reminder);
        reminder.setVisible(false);

        JPanel pVert = new JPanel();
        pVert.setLayout(new BoxLayout(pVert,
                BoxLayout.Y_AXIS));
        JPanel pHoriz = new JPanel();
        pHoriz.setLayout(new BoxLayout(pHoriz,
                BoxLayout.X_AXIS));
        JPanel pVert1 = new JPanel();
        JPanel pVert2 = new JPanel();
        JPanel pVert3 = new JPanel();
        JPanel pVert4 = new JPanel();
        JPanel pVert5 = new JPanel();
        pVert1.setLayout(new BoxLayout(pVert1,
                BoxLayout.Y_AXIS));
        pVert1.add(addBtn);
        pVert1.add( Box.createRigidArea(new Dimension(10,10)) );
        pVert1.add(mulBtn);
        pHoriz.add(pVert1);
        pHoriz.add( Box.createRigidArea(new Dimension(10,40)) );
        pVert2.setLayout(new BoxLayout(pVert2,BoxLayout.Y_AXIS));
        pVert2.add(subBtn);
        pVert2.add( Box.createRigidArea(new Dimension(10,10)) );
        pVert2.add(divBtn);
        pHoriz.add(pVert2);
        pHoriz.add( Box.createRigidArea(new Dimension(10,40)) );
        pVert3.setLayout(new BoxLayout(pVert3, BoxLayout.Y_AXIS));
        pVert3.add(derivABtn);
        pVert3.add( Box.createRigidArea(new Dimension(10,10)) );
        pVert3.add(inteABtn);
        pHoriz.add(pVert3);
        pHoriz.add( Box.createRigidArea(new Dimension(10,40)) );
        pVert4.setLayout(new BoxLayout(pVert4, BoxLayout.Y_AXIS));
        pVert4.add(derivBBtn);
        pVert4.add( Box.createRigidArea(new Dimension(10,10)) );
        pVert4.add(inteBBtn);
        pHoriz.add(pVert4);
        pHoriz.add( Box.createRigidArea(new Dimension(10,40)) );
        pVert5.setLayout(new BoxLayout(pVert5, BoxLayout.Y_AXIS));
        pVert5.add(moveResultToABtn);
        pVert5.add( Box.createRigidArea(new Dimension(10,10)) );
        pVert5.add(moveResultToBBtn);
        pHoriz.add(pVert5);
        pVert6.setLayout(new BoxLayout(pVert6, BoxLayout.Y_AXIS));
        pVert6.add(moveReminderToABtn);
        pVert6.add( Box.createRigidArea(new Dimension(10,10)) );
        pVert6.add(moveReminderToBBtn);
        pHoriz.add(pVert6);
        pVert6.setVisible(false);

        pVert.add(pHoriz);
        pVert.add( Box.createRigidArea(new Dimension(10,10)) );
        JPanel l7 = new JPanel();
        l7.add(clearBtn);
        content.add(l7);
        pVert.add(l7);

        content.add(pVert);

        this.setContentPane(content);
        this.pack();

        setTitle("Polybomial Calculator");
        setVisible(true);
        setSize(800, 400);
        setLocation(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    public void addAddListener(ActionListener add) {
        addBtn.addActionListener(add);
    }

    public void subAddListener(ActionListener sub) {
        subBtn.addActionListener(sub);
    }

    public void mulAddListener(ActionListener mul) {
        mulBtn.addActionListener(mul);
    }

    public void divAddListener(ActionListener div) {
        divBtn.addActionListener(div);
    }

    public void inteAAddListener(ActionListener inte) {
        inteABtn.addActionListener(inte);
    }

    public void derivAAddListener(ActionListener deriv) {
        derivABtn.addActionListener(deriv);
    }

    public void inteBAddListener(ActionListener inte) {
        inteBBtn.addActionListener(inte);
    }

    public void derivBAddListener(ActionListener deriv) {
        derivBBtn.addActionListener(deriv);
    }

    public void clearAddListener(ActionListener clear) {
        clearBtn.addActionListener(clear);
    }
    public void clearAAddListener(ActionListener clear) {
        clearABtn.addActionListener(clear);
    }
    public void clearBAddListener(ActionListener clear) {
        clearBBtn.addActionListener(clear);
    }

    public void moveToAAddListener(ActionListener clear) {
        moveResultToABtn.addActionListener(clear);
    }

    public void moveToBAddListener(ActionListener clear) {
        moveResultToBBtn.addActionListener(clear);
    }

    public void moveReminderToAAddListener(ActionListener clear) {
        moveReminderToABtn.addActionListener(clear);
    }

    public void moveReminderToBAddListener(ActionListener clear) {
        moveReminderToBBtn.addActionListener(clear);
    }

    public void reset() {
        input1.setText("");
        input2.setText("");
        output1.setText("");
        output2.setText("");
        operation.setText("operation");
        reminder.setVisible(false);
        resultLabel.setText("    Result        ");
        pVert6.setVisible(false);
        moveResultToABtn.setText("Move result to A");
        moveResultToBBtn.setText("Move result to B");
    }

    public void resetOperationAndResult() {
        output1.setText("");
        output2.setText("");
        operation.setText("operation");
        reminder.setVisible(false);
        resultLabel.setText("    Result        ");
        moveResultToABtn.setText("Move result to A");
        moveResultToBBtn.setText("Move result to B");
        pVert6.setVisible(false);
        moveResultToABtn.setText("Move result to A");
        moveResultToBBtn.setText("Move result to B");
    }

    public void setOperation(String txt) {
        operation.setText(txt);
    }

    public void divisionMode() {
        reminder.setVisible(true);
        resultLabel.setText(" Quotient        ");
        moveResultToABtn.setText("Move quotient to A");
        moveResultToBBtn.setText("Move quotient to B");
        pVert6.setVisible(true);
    }

    public void noDivisionMode() {
        reminder.setVisible(false);
        resultLabel.setText("    Result        ");
        pVert6.setVisible(false);
        moveResultToABtn.setText("Move result to A");
        moveResultToBBtn.setText("Move result to B");
    }

    public String getImputA() {
        return input1.getText();
    }

    public void setImputA(String txt)
    {
        input1.setText(txt);
    }

    public String getImputB() {
        return  input2.getText();
    }

    public void setImputB(String txt) {
        input2.setText(txt);
    }

    public String getReminder() {
        return output2.getText();
    }

    public void setResult(String text) {
        output1.setText(text);
    }

    public void setReminder(String text) {
        output2.setText(text);
    }

    public String getResult() {
        return output1.getText();
    }
    public void input1Empty() {
        JOptionPane.showMessageDialog(this, "Polynomial A is empty!", "User error", JOptionPane.ERROR_MESSAGE);
    }
    public void input2Empty() {
        JOptionPane.showMessageDialog(this, "Polynomial B is empty!", "User error", JOptionPane.ERROR_MESSAGE);
    }
    public void input1InorrectFormat() {
        JOptionPane.showMessageDialog(this, "Inorrect format for A!", "User error", JOptionPane.ERROR_MESSAGE);
    }
    public void input2InorrectFormat() {
        JOptionPane.showMessageDialog(this, "Inorrect format for B!", "User error", JOptionPane.ERROR_MESSAGE);
    }
}
