using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Threading;
//using System.Diagnostics.Process.Start("notepad.exe");

namespace lee
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }
        int st=0, fn = 0, r=0,ok=0,gata=1;
        Button[,] A;
        int n, m;
        private void afis(int i, int j)
        {
            MessageBox.Show("Ati apasat " + i + j);
        }
        private void CLICK(object sender, EventArgs e)
        {
            if (gata == 1)
            {
                if (ok == 1)
                {
                    for (int i = 1; i <= n; i++)
                        for (int j = 1; j <= m; j++)
                            if (sender == A[i, j])
                                if (st == 0 && fn == 0)
                                {
                                    A[i, j].BackColor = Color.Green;
                                    if (int.Parse(A[i, j].Text) == 0) A[i, j].Text = 1.ToString();
                                    else
                                    {
                                        A[i, j].BackColor = Color.White;
                                        A[i, j].Text = 0.ToString();
                                    }
                                    //  afis(i, j);
                                }
                                else
                                {
                                    if (st == 1)
                                    {
                                        if (textBox1.Text != "" && A[i, j].BackColor == Color.White)
                                        {
                                            int d = int.Parse(textBox1.Text.ToString());
                                            int f = int.Parse(textBox2.Text.ToString());
                                            A[d, f].BackColor = Color.White;
                                        }
                                        if (A[i, j].BackColor == Color.White)
                                        {
                                            A[i, j].BackColor = Color.Aqua;
                                            if (r == 1)
                                            {
                                                pictureBox1.Location = new Point(A[i, j].Left, A[i, j].Top);
                                                pictureBox1.Visible = true;
                                            }
                                            textBox1.Text = i.ToString();
                                            textBox2.Text = j.ToString();
                                        }
                                        else MessageBox.Show("Nu puteti selecta acest camp!");
                                    }
                                    else
                                    {
                                        if (textBox3.Text != "" && A[i, j].BackColor == Color.White)
                                        {
                                            int d = int.Parse(textBox3.Text.ToString());
                                            int f = int.Parse(textBox4.Text.ToString());
                                            A[d, f].BackColor = Color.White;
                                        }
                                        if (A[i, j].BackColor == Color.White)
                                        {
                                            A[i, j].BackColor = Color.Yellow;
                                            if (r == 1)
                                            {
                                                pictureBox2.Location = new Point(A[i, j].Left, A[i, j].Top);
                                                pictureBox2.Visible = true;
                                            }
                                            textBox3.Text = i.ToString();
                                            textBox4.Text = j.ToString();
                                        }
                                        else MessageBox.Show("Nu puteti selecta acest camp!");
                                    }
                                }
                }
                else MessageBox.Show("Incarca matricea sau o matrice noua daca algoritmul s-a terminat!.");
            }
            else MessageBox.Show("Programul ruleaza!Asteapta pana algoritmul se va incheia!");
        }
        private void button1_Click(object sender, EventArgs e)
        {
            textBox5.Text = "";
            if (gata == 1)
            {

                ok = 1;
                pictureBox1.Visible = false;
                pictureBox2.Visible = false;
                pictureBox3.Visible = false;
                for (int i = 1; i <= n; i++)
                    for (int j = 1; j <= m; j++)
                        this.Controls.Remove(A[i, j]);
                textBox1.Text = "";
                textBox2.Text = "";
                textBox3.Text = "";
                textBox4.Text = "";
                n = int.Parse(numericUpDown1.Value.ToString());
                m = int.Parse(numericUpDown2.Value.ToString());
                int z = 0;
                if (n > z) z = n;
                if (m > z) z = m;
                int l = 480 / z;
                A = new Button[n + 1, m + 1];
                for (int i = 1; i <= n; i++)
                    for (int j = 1; j <= m; j++)
                    {
                        A[i, j] = new Button();
                        // A[i, j].TextAlign = ContentAlignment.MiddleCenter;
                        // A[i, j].TextAlign = ContentAlignment.MiddleCenter;
                        A[i, j].Name = "btn" + i + j;
                        A[i, j].Width = l;
                        A[i, j].Height = l;
                        pictureBox1.Width = l;
                        pictureBox2.Width = l;
                        pictureBox3.Width = l;
                        pictureBox1.Height = l;
                        pictureBox2.Height = l;
                        pictureBox3.Height = l;
                        A[i, j].BackColor = Color.White;
                        A[i, j].Top = l * (i - 1) + 5;
                        A[i, j].Font = new Font("Arial", 100 / z);
                        A[i, j].Text = 0.ToString();
                        A[i, j].Left = l * (j - 1) + 5;
                        this.Controls.Add(A[i, j]);
                        A[i, j].Click += new EventHandler(CLICK);
                    }
            }
            else MessageBox.Show("Programul ruleaza!Asteapta pana algoritmul se va incheia!");
            if (n < 13 && n > 9 && m < 13 && m > 9) ;
            else
            {
                r = 0;
                button8.BackColor = Color.White;
                pictureBox1.Visible = false;
                pictureBox2.Visible = false;
                pictureBox3.Visible = false;
            }
        }

        private void button3_Click(object sender, EventArgs e)
        {
            if (fn == 0)
            {
                fn = 1;
                if (st == 1)
                {
                    st = 0;
                    button2.BackColor = Color.White;
                }
                button3.BackColor = Color.Red;
            }
            else
            {
                fn = 0;
                button3.BackColor = Color.White;
            }
        }
        public int inside(int i, int j)
        {
            int p = 1;
            if (i < 1) p = 0;
            if (i > n) p = 0;
            if (j < 1) p = 0;
            if (j > m) p = 0;
            //i >= 1 && i <= n && j >= 1 && j <= m;
            return p;
        }
        private void button4_Click(object sender, EventArgs e)
        {
            if (textBox1.Text != "" && textBox3.Text != "")
            {
                if (ok == 1)
                {
                    ok = 0;
                    gata = 0;
                    int st = 1, dr = 1, ist, jst, ifn, jfn;
                    ist = int.Parse(textBox1.Text.ToString());
                    jst = int.Parse(textBox2.Text.ToString());
                    ifn = int.Parse(textBox3.Text.ToString());
                    jfn = int.Parse(textBox4.Text.ToString());
                    int[] X;
                    int[] Y;
                    X = new int[10001];
                    Y = new int[10001];
                    X[1] = ist;
                    Y[1] = jst;
                    A[ist, jst].Text = 1.ToString();
                    while (st <= dr && int.Parse(A[ifn, jfn].Text.ToString()) == 0)
                    {
                        int i = X[st], j = Y[st];
                        if (inside(i - 1, j) == 1 && int.Parse(A[i - 1, j].Text.ToString()) == 0)
                        {
                            A[i - 1, j].Text = (int.Parse(A[i, j].Text.ToString()) + 1).ToString();
                            A[i - 1, j].BackColor = Color.DarkOrange;
                            dr = dr + 1;
                            X[dr] = i - 1; Y[dr] = j;
                        }
                        if (inside(i + 1, j) == 1 && int.Parse(A[i + 1, j].Text.ToString()) == 0)
                        {
                            A[i + 1, j].Text = (int.Parse(A[i, j].Text.ToString()) + 1).ToString();
                            A[i + 1, j].BackColor = Color.DarkOrange;
                            dr = dr + 1;
                            X[dr] = i + 1; Y[dr] = j;
                        }
                        if (inside(i, j - 1) == 1 && int.Parse(A[i, j - 1].Text.ToString()) == 0)
                        {
                            A[i, j - 1].Text = (int.Parse(A[i, j].Text.ToString()) + 1).ToString();
                            A[i, j - 1].BackColor = Color.DarkOrange;
                            dr = dr + 1;
                            X[dr] = i; Y[dr] = j - 1;
                        }
                        if (inside(i, j + 1) == 1 && int.Parse(A[i, j + 1].Text.ToString()) == 0)
                        {
                            A[i, j + 1].Text = (int.Parse(A[i, j].Text.ToString()) + 1).ToString();
                            A[i, j + 1].BackColor = Color.DarkOrange;
                            dr = dr + 1;
                            X[dr] = i; Y[dr] = j + 1;
                        }
                        Application.DoEvents();
                        Thread.Sleep(1000-trackBar1.Value);
                        st++;
                    }
                    A[ifn, jfn].BackColor = Color.Yellow;
                    Application.DoEvents();
                    Thread.Sleep(trackBar1.Value);
                    int a = ifn, b = jfn;
                    int[] X1 = new int[int.Parse(A[ifn, jfn].Text.ToString()) + 2];
                    int[] Y1 = new int[int.Parse(A[ifn, jfn].Text.ToString()) + 2];
                    int z = 0;
                    if (int.Parse(A[a, b].Text.ToString()) != 0)
                    {
                        while (ist != a || jst != b)
                        {
                            int p = 1;
                            if (a - 1 > 0) { if (int.Parse(A[a - 1, b].Text.ToString()) == (int.Parse(A[a, b].Text.ToString()) - 1) && A[a - 1, b].BackColor == Color.DarkOrange || A[a - 1, b].BackColor == Color.Aqua) { a = a - 1; p = 0; } }
                            if (a + 1 <= n && p == 1) { if (int.Parse(A[a + 1, b].Text.ToString()) == (int.Parse(A[a, b].Text.ToString()) - 1) && A[a + 1, b].BackColor == Color.DarkOrange || A[a + 1, b].BackColor == Color.Aqua) { a = a + 1; p = 0; } }
                            if (b - 1 > 0 && p == 1) { if (int.Parse(A[a, b - 1].Text.ToString()) == (int.Parse(A[a, b].Text.ToString()) - 1) && A[a, b - 1].BackColor == Color.DarkOrange || A[a, b - 1].BackColor == Color.Aqua) { b = b - 1; p = 0; } }
                            if (b + 1 <= m && p == 1) if (int.Parse(A[a, b + 1].Text.ToString()) == (int.Parse(A[a, b].Text.ToString()) - 1) && A[a, b + 1].BackColor == Color.DarkOrange || A[a, b + 1].BackColor == Color.Aqua) { b = b + 1; p = 0; }
                            X1[++z] = a;
                            Y1[z] = b;
                        }
                        textBox5.Text = A[ifn, jfn].Text;
                        for (int i = z; i >= 1; i--)
                        {
                            if(i != z) A[X1[i], Y1[i]].BackColor = Color.Red;
                            if (r == 1) pictureBox1.Location = new Point(A[X1[i], Y1[i]].Left, A[X1[i], Y1[i]].Top);
                            if (r == 1 && i == 1)
                            {
                                Application.DoEvents();
                                Thread.Sleep(1000-trackBar1.Value);
                                pictureBox1.Location = new Point(A[X1[i], Y1[i]].Left, A[X1[i], Y1[i]].Top);
                                Application.DoEvents();
                                Thread.Sleep(1000-trackBar1.Value);
                                pictureBox1.Visible = false;
                                pictureBox2.Visible = false;
                                pictureBox3.Visible = true;
                                pictureBox3.Location = new Point(A[ifn, jfn].Left, A[ifn, jfn].Top);
                            }
                            Application.DoEvents();
                            Thread.Sleep(1000-trackBar1.Value);
                        }
                        if (r == 0) A[ist, jst].BackColor = Color.Aqua;
                        if (r == 0) A[ifn, jfn].BackColor = Color.Yellow;
                    }
                    else MessageBox.Show("Nu există drum de la punctul de start la punctul de final.");
                }
                else MessageBox.Show("Incarca o matrice noua, algoritmul s-a terminat!.");
            }
            else
            {
                if (textBox1.Text == "" && textBox3.Text == "") MessageBox.Show("Nu aţi selectat punctul de start şi punctul de final!");
                else
                {
                    if (textBox1.Text == "") MessageBox.Show("Nu aţi selectat punctul de start!");
                    if (textBox3.Text == "") MessageBox.Show("Nu aţi selectat punctul de final!");
                }
            }
            gata = 1;
        }
        private void button6_Click(object sender, EventArgs e)
        {
            Form3 f3 = new Form3();
            f3.Text = "Despre algoritmul lui Lee!";
            f3.ShowDialog();
        }

        private void button5_Click(object sender, EventArgs e)
        {
            Form2 f2 = new Form2();
            f2.Text = "Instrucţiuni";
           f2.ShowDialog();
        }

        private void label9_Click(object sender, EventArgs e)
        {

        }

        private void button9_Click(object sender, EventArgs e)
        {
            Form5 f5 = new Form5();
            f5.Text = "Despre coada";
            f5.ShowDialog();
        }

        private void button10_Click(object sender, EventArgs e)
        {
            Form6 f6 = new Form6();
            f6.Text = "Coada";
            f6.ShowDialog();
        }

        private void button7_Click(object sender, EventArgs e)
        {
            //System.Diagnostics.Process.Start("notepad", "lee");
            Form4 f4 = new Form4();
            f4.Text = "Problema + Cod C++!";
            f4.ShowDialog();
        }

        private void Form1_Shown(object sender, EventArgs e)
        {
            pictureBox1.Visible = false;
            pictureBox2.Visible = false;
            pictureBox3.Visible = false;
            ok = 1;
            pictureBox1.Visible = false;
            pictureBox2.Visible = false;
            pictureBox3.Visible = false;
            for (int i = 1; i <= n; i++)
                for (int j = 1; j <= m; j++)
                    this.Controls.Remove(A[i, j]);
            textBox1.Text = "";
            textBox2.Text = "";
            textBox3.Text = "";
            textBox4.Text = "";
            n = int.Parse(numericUpDown1.Value.ToString());
            m = int.Parse(numericUpDown2.Value.ToString());
            int z = 0;
            if (n > z) z = n;
            if (m > z) z = m;
            int l = 480 / z;
            A = new Button[n + 1, m + 1];
            for (int i = 1; i <= n; i++)
                for (int j = 1; j <= m; j++)
                {
                    A[i, j] = new Button();
                    // A[i, j].TextAlign = ContentAlignment.MiddleCenter;
                    // A[i, j].TextAlign = ContentAlignment.MiddleCenter;
                    A[i, j].Name = "btn" + i + j;
                    A[i, j].Width = l;
                    A[i, j].Height = l;
                    pictureBox1.Width = l;
                    pictureBox2.Width = l;
                    pictureBox3.Width = l;
                    pictureBox1.Height = l;
                    pictureBox2.Height = l;
                    pictureBox3.Height = l;
                    A[i, j].BackColor = Color.White;
                    A[i, j].Top = l * (i - 1) + 5;
                    A[i, j].Font = new Font("Arial", 100 / z);
                    A[i, j].Text = 0.ToString();
                    A[i, j].Left = l * (j - 1) + 5;
                    this.Controls.Add(A[i, j]);
                    A[i, j].Click += new EventHandler(CLICK);
                }
        }

        private void button8_Click(object sender, EventArgs e)
        {
            if (gata == 1)
            {
                if (n < 13 && n > 9 && m < 13 && m > 9)
                {
                    if (r == 0)
                    {
                        r = 1;
                        button8.BackColor = Color.Red;
                        if (textBox1.Text != "")
                        {
                            int i = int.Parse(textBox1.Text.ToString());
                            int j = int.Parse(textBox2.Text.ToString());
                            pictureBox1.Location = new Point(A[i, j].Left, A[i, j].Top);
                            pictureBox1.Visible = true;
                        }
                        if (textBox3.Text != "")
                        {
                            int i = int.Parse(textBox3.Text.ToString());
                            int j = int.Parse(textBox4.Text.ToString());
                            pictureBox2.Location = new Point(A[i, j].Left, A[i, j].Top);
                            pictureBox2.Visible = true;

                        }
                    }
                    else
                    {
                        r = 0;
                        button8.BackColor = Color.White;
                        pictureBox1.Visible = false;
                        pictureBox2.Visible = false;
                        pictureBox3.Visible = false;
                    }
                }
                else MessageBox.Show("Dimensiunile matricei trebuie să fie între 9 și 13!");
            }
            else MessageBox.Show("Programul ruleaza!Asteapta pana algoritmul se va incheia!");
        }

        private void button2_Click(object sender, EventArgs e)
        {
           
            if(st==0)
            {
                st = 1;
                if(fn==1)
                {
                    fn = 0;
                    button3.BackColor = Color.White;
                }
                button2.BackColor = Color.Red;
            }
            else
            {
                st = 0;
                button2.BackColor = Color.White;
            }
        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }
    }
}
