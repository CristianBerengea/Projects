package Controller;

import Model.Monomial;
import Model.Polynomial;
import Model.PolynomialModel;
import View.PolynomialView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PolynomialController {
    private PolynomialModel polynomialModel;
    private PolynomialView polynomialView;
    private Polynomial a;
    private Polynomial b;

    public PolynomialController(PolynomialModel polynomialModel, PolynomialView polynomialView) {
        this.polynomialModel = polynomialModel;
        this.polynomialView = polynomialView;

        polynomialView.addAddListener(new AddListener());
        polynomialView.subAddListener(new SubListener());
        polynomialView.mulAddListener(new MulListener());
        polynomialView.divAddListener(new DivListener());
        polynomialView.inteAAddListener(new AInteListener());
        polynomialView.derivAAddListener(new ADerivListener());
        polynomialView.inteBAddListener(new BInteListener());
        polynomialView.derivBAddListener(new BDerivListener());
        polynomialView.clearAddListener(new ClearListener());
        polynomialView.moveToAAddListener(new MoveResultToAListener());
        polynomialView.moveToBAddListener(new MoveResultToBListener());
        polynomialView.clearAAddListener(new ClearAListener());
        polynomialView.clearBAddListener(new ClearBListener());
        polynomialView.moveReminderToAAddListener(new MoveReminderToAListener());
        polynomialView.moveReminderToBAddListener(new MoveReminderToBListener());
    }

    class AddListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            polynomialView.noDivisionMode();
            polynomialView.setOperation("    +    ");
            if(getA()&&getB()) {
                polynomialModel.add(a, b);
                setResult();
            }
        }
    }

    class SubListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            polynomialView.noDivisionMode();
            polynomialView.setOperation("    -    ");
            if(getA()&&getB()) {
                b = new Polynomial(polynomialView.getImputB());
                polynomialModel.sub(a, b);
                setResult();
            }
        }
    }

    class MulListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            polynomialView.noDivisionMode();
            polynomialView.setOperation("    *    ");
            if(getA()&&getB()){
                polynomialModel.mul(a,b);
                setResult();
            }
        }
    }

    class DivListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            polynomialView.divisionMode();
            polynomialView.setOperation("    /    ");
            if(getA()&&getB()){
                polynomialModel.div(a,b);
                setResult();
                polynomialModel.getReminder().sort();
                polynomialView.setReminder(polynomialModel.getReminder().toString());
            }
        }
    }

    class AInteListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            polynomialView.noDivisionMode();
            polynomialView.setOperation("Inte A");
            if(getA()) {
                polynomialModel.inte(a);
                setResult();
            }
        }
    }

    class ADerivListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            polynomialView.noDivisionMode();
            polynomialView.setOperation("Deriv A");
            if(getA()) {
                polynomialModel.deriv(a);
                setResult();
            }
        }
    }

    class BInteListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            polynomialView.noDivisionMode();
            polynomialView.setOperation("Inte B");
            if(getB()) {
                polynomialModel.inte(b);
                setResult();
            }
        }
    }

    class BDerivListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            polynomialView.noDivisionMode();
            polynomialView.setOperation("Deriv B");
            if(getB()) {
                polynomialModel.deriv(b);
                setResult();
            }
        }
    }

    class ClearListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            polynomialView.reset();
        }
    }
    class ClearAListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            polynomialView.setImputA("");
            polynomialView.resetOperationAndResult();
        }
    }
    class ClearBListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            polynomialView.setImputB("");
            polynomialView.resetOperationAndResult();
        }
    }

    class MoveResultToAListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            polynomialView.setImputA(polynomialView.getResult());
            polynomialView.resetOperationAndResult();
        }
    }

    class MoveResultToBListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            polynomialView.setImputB(polynomialView.getResult());
            polynomialView.resetOperationAndResult();
        }
    }

    class MoveReminderToAListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            polynomialView.setImputA(polynomialView.getReminder());
            polynomialView.resetOperationAndResult();
        }
    }

    class MoveReminderToBListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            polynomialView.setImputB(polynomialView.getReminder());
            polynomialView.resetOperationAndResult();
        }
    }

    public boolean getA(){
        boolean ok = true;
        try
        {
            String s = polynomialView.getImputA();
            if(s.isEmpty())
            {
                polynomialView.input1Empty();
                ok = false;
                polynomialView.resetOperationAndResult();
            }
            a = new Polynomial(s);
        }
        catch (Exception z)
        {
            System.out.println("ERR A");
            if(ok) polynomialView.input1InorrectFormat();
            ok = false;
        }
        if(ok) {

           polynomialModel.addSameGrade(a);
            a = polynomialModel.getResult();
            a.sort();
            polynomialView.setImputA(a.toString());
        }
        return ok;
    }

    public boolean getB(){
        boolean ok = true;
        try
        {
            String s = polynomialView.getImputB();
            if(s.isEmpty())
            {
                polynomialView.input2Empty();
                ok = false;
                polynomialView.resetOperationAndResult();
            }
            b = new Polynomial(s);
        }
        catch (Exception z)
        {
            System.out.println("ERR B");
            if(ok) polynomialView.input2InorrectFormat();
            ok = false;
        }
       if(ok) {
           polynomialModel.addSameGrade(b);
           b = polynomialModel.getResult();
           b.sort();
           polynomialView.setImputB(b.toString());
       }
        return ok;
    }

    public void setResult(){
        Polynomial p = polynomialModel.getResult();
        p.sort();
        polynomialView.setResult(p.toString());
    }
}
