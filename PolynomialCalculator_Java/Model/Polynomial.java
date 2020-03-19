package Model;

import java.util.*;

public class Polynomial {
    private ArrayList<Monomial> list;

    public Polynomial() {
        list = new ArrayList<>();
    }

    public Polynomial(String p) {
        list = new ArrayList<>();
        p = p.replace("-x", "-1x");
        p = p.replace("-", "+-");
        String[] s = p.split("\\+");

        for (int i = 0; i < s.length; i++)
            if (!s[i].isEmpty())
            {
            Monomial x = new Monomial(s[i]);
            list.add(x);
        }


         for (Monomial e : list) {
             System.out.println(e);
         }
        System.out.println("");
    }


    public void addMonomial(Monomial x)
    {
        list.add(x);
    }

    public ArrayList<Monomial> getList() {
        return list;
    }

    public String toString() {
        String out="";
        if(list.isEmpty()) out+="0";
        else {
            for (Monomial e : list) {
                if (e.getCoef() >= 0) {
                    out += "+" + e.toString();
                } else {
                    out += e.toString();
                }
            }
            if (out.charAt(0) == '+') {
                out = out.substring(1);
            }
        }
        return out;
    }

    public Monomial getMaxMonom()
    {
        if(list.isEmpty()) return new Monomial(0,Integer.MIN_VALUE);
        else {
            Monomial maxim = list.get(0);
            System.out.println("---------" + list.get(0));
            for (Monomial e : list) {
                if (e.getExp() > maxim.getExp()) maxim = e;
            }
            return maxim;
        }
    }
    public void sort()
    {
        Collections.sort(list);
    }

    public void clearList()
    {
        list.clear();
    }
}
