package Model;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class PolynomialCalculator {
    public Polynomial add(Polynomial p1, Polynomial p2)
    {
        Polynomial rez = new Polynomial();
        for (Monomial e: p1.getList()) {
            boolean found = false;
            for (Monomial f: p2.getList()) {
                int expe=e.getExp(),expf=f.getExp();
                double coefe=e.getCoef(),coeff=f.getCoef();
                if (expe==expf) {
                    if(coefe+coeff!=0) rez.addMonomial(new Monomial(coeff + coefe, expe));
                    found = true;
                    break;
                }
            }
            if(!found) rez.addMonomial(e);
        }
        for (Monomial f: p2.getList()) {
            boolean found = false;
            for (Monomial e: p1.getList()) {
                if (e.getExp()==f.getExp()) {
                    found = true;
                    break;
                }
            }
            if(!found) rez.addMonomial(f);
        }
        return rez;
    }

    public Polynomial sub(Polynomial p1, Polynomial p2)
    {
        Polynomial rez = new Polynomial();
        for (Monomial e: p1.getList()) {
            boolean found = false;
            for (Monomial f: p2.getList()) {
                int expe=e.getExp(),expf=f.getExp();
                double coefe=e.getCoef(),coeff=f.getCoef();
                if (expe==expf) {
                    if(coefe-coeff!=0) rez.addMonomial(new Monomial(coefe - coeff, expe));
                    found = true;
                    break;
                }
            }
            if(!found) rez.addMonomial(e);
        }
        for (Monomial f: p2.getList()) {
            boolean found = false;
            for (Monomial e: p1.getList()) {
                if (e.getExp()==f.getExp()) {
                    found = true;
                    break;
                }
            }
            if(!found) rez.addMonomial(new Monomial(-f.getCoef(),f.getExp()));
        }
        return rez;
    }

    public Polynomial mul(Polynomial p1, Polynomial p2)
    {
        Polynomial rez = new Polynomial();
        for (Monomial e: p1.getList()) {
            for (Monomial f: p2.getList()) {
                int expRez=e.getExp()+f.getExp();
                double coefRez=e.getCoef()*f.getCoef();
                boolean existExp=false;
                for (Monomial r: rez.getList()){
                    if(r.getExp()==expRez){
                        if(r.getExp()+coefRez!=0) r.setCoef(r.getExp()+coefRez);
                        existExp=true;
                    }
                }
                if(!existExp) rez.addMonomial(new Monomial(coefRez,expRez));
            }
        }
        return rez;
    }

    public Polynomial deriv(Polynomial p)
    {
        Polynomial rez = new Polynomial();
        for (Monomial e: p.getList()){
            int exp = e.getExp();
            double coef = e.getCoef();
            if(exp!=0)
            {
                if(exp==1) rez.addMonomial(new Monomial(coef,0));
                else
                {
                    rez.addMonomial(new Monomial(coef*exp,exp-1));
                }
            }
        }
        return rez;
    }

    public Polynomial integ(Polynomial p)
    {
        Polynomial rez = new Polynomial();
        for (Monomial e: p.getList()){
            int exp = e.getExp();
            double coef = e.getCoef();
            rez.addMonomial(new Monomial(coef/(exp+1),exp+1));
        }
        return rez;
    }

    public Polynomial div(Polynomial p1, Polynomial p2)
    {
        Polynomial quotient = new Polynomial();
        Monomial a = p1.getMaxMonom();
        Monomial b = p2.getMaxMonom();

        while (a.getExp()>=b.getExp()&&b.getCoef()!=0)
        {
            Monomial c = new Monomial(a.getCoef() / b.getCoef(), a.getExp() - b.getExp());
            quotient.addMonomial(c);
            Polynomial z = new Polynomial();
            z.addMonomial(c);
            p1 = sub(p1,mul(p2,z));
            a = p1.getMaxMonom();
        }
        p2.clearList();
        for(Monomial e : p1.getList()) {
            p2.addMonomial(e);
        }
        return quotient;
    }

    public Polynomial addSameGrade(Polynomial p1)
    {
        Polynomial rez = new Polynomial();
        for (Monomial e: p1.getList()) {
            boolean existExp=false;
            for (Monomial r: rez.getList()) {
                    if(r.getExp()==e.getExp()){
                        if(r.getCoef()+e.getCoef()==0) rez.getList().remove(r);
                        r.setCoef(r.getCoef()+e.getCoef());
                        existExp=true;
                    }
                }
            if(!existExp) rez.addMonomial(e);
            }
        return rez;
    }
}
