package Model;

public class Monomial implements Comparable<Monomial>{
    private double coef;
    private int exp;

    public Monomial(String p)
    {
        if(p.indexOf("x^")==0)
        {
            String[] s = p.split("x\\^");
            coef = 1;
            exp = Integer.parseInt(s[1]);
        }
        else {
            if (p.indexOf("x^") != -1) {
                String[] s = p.split("x\\^");
                 coef = Double.parseDouble(s[0]);
                exp = Integer.parseInt(s[1]);
            } else {
                if (p.indexOf('x') == -1) {
                    coef = Double.parseDouble(p);
                    exp = 0;
                } else {
                    String[] s = p.split("x");
                    if (s.length == 0) {
                        coef = 1;
                        exp = 1 ;
                    } else {
                         coef = Double.parseDouble(s[0]);
                        exp = 1;
                    }

                }

            }
            //System.out.println(coef + " " + exp);
        }
    }

    public Monomial(double coef, int exp) {
        this.coef = coef;
        this.exp = exp;
    }

    public int getExp() {
        return exp;
    }

    public double getCoef() {
        return coef;
    }

    public void setCoef(double coef) {
        this.coef = coef;
    }


    @Override
    public String toString() {
        String coefS = "";
        if(coef==(int)coef) coefS +=(int)coef;
        else coefS +=coef;
        String out = "";
        if(exp == 0) out+=coefS;
        else {
            if(coef == 1){
               if(exp == 1) out+="x";
               else out+="x^"+exp;
            } else if(coef == -1){
                if(exp == 1) out+="-x";
                else out+="-x^"+exp;
            }else{
                if(exp == 1)  out+=coefS+"x";
                else out+=coefS+"x^"+exp;
            }
        }
        return out;
    }


    @Override
    public int compareTo(Monomial o) {
        return exp-o.exp;
    }
}
