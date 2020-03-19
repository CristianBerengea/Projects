package Model;

public class PolynomialModel{
    private PolynomialCalculator p;
    private Polynomial result;
    private Polynomial reminderDiv;
    public PolynomialModel(){
        p = new PolynomialCalculator();
    }

    public void add(Polynomial a,Polynomial b)
    {
        result = p.add(a,b);
    }

    public void sub(Polynomial a,Polynomial b)
    {
        result = p.sub(a,b);
    }

    public void mul(Polynomial a,Polynomial b)
    {
        result = p.mul(a,b);
    }

    public void inte(Polynomial a)
    {
        result = p.integ(a);
    }

    public void deriv(Polynomial a)
    {
        result = p.deriv(a);
    }

    public void div(Polynomial a,Polynomial b)
    {
        result = p.div(a,b);
        reminderDiv=b;
    }

    public Polynomial getResult()
    {
        return result;
    }

    public Polynomial getReminder()
    {
        return reminderDiv;
    }

    public void addSameGrade(Polynomial a){
        result = p.addSameGrade(a);
    }
}
