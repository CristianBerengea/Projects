using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Threading;
namespace lee
{
    public partial class Form6 : Form
    {
        public Form6()
        {
            InitializeComponent();
        }
        Button[,] A;
        List<Button> L1;
        List<Button> L2;
        private void Form6_Shown(object sender, EventArgs e)
        {
            int l = 480 / 10;
            A = new Button[9, 9];
            for (int i = 1; i <= 8; i++)
                for (int j = 1; j <= 8; j++)
                {
                    A[i, j] = new Button();
                    // A[i, j].TextAlign = ContentAlignment.MiddleCenter;
                    // A[i, j].TextAlign = ContentAlignment.MiddleCenter;
                    A[i, j].Name = "btn" + i + j;
                    A[i, j].Width = l;
                    A[i, j].Height = l;
                    A[i, j].BackColor = Color.White;
                    A[i, j].Top = l * (i - 1) + 40;
                    A[i, j].Font = new Font("Arial", 100 / 8);
                    A[i, j].Text = 0.ToString();
                    A[i, j].Left = l * (j - 1) + 40;
                    this.Controls.Add(A[i, j]);
                }
            A[1, 1].BackColor = Color.Green;
            A[1, 1].Text = 1.ToString();

            A[1, 2].BackColor = Color.Green;
            A[1, 2].Text = 1.ToString();

            A[1, 3].BackColor = Color.Green;
            A[1, 3].Text = 1.ToString();

            A[1, 4].BackColor = Color.Green;
            A[1, 4].Text = 1.ToString();

            A[2, 5].BackColor = Color.Green;
            A[2, 5].Text = 1.ToString();

            A[1, 5].BackColor = Color.Green;
            A[1, 5].Text = 1.ToString();

            A[2, 5].BackColor = Color.Green;
            A[2, 5].Text = 1.ToString();

            A[3, 5].BackColor = Color.Green;
            A[3, 5].Text = 1.ToString();

            A[2, 1].BackColor = Color.Green;
            A[2, 1].Text = 1.ToString();

            A[3, 1].BackColor = Color.Green;
            A[3, 1].Text = 1.ToString();

            A[4, 1].BackColor = Color.Green;
            A[4, 1].Text = 1.ToString();

            A[6, 1].BackColor = Color.Green;
            A[6, 1].Text = 1.ToString();

            A[3, 3].BackColor = Color.Green;
            A[3, 3].Text = 1.ToString();

            A[3, 4].BackColor = Color.Green;
            A[3, 4].Text = 1.ToString();

            A[6, 2].BackColor = Color.Green;
            A[6, 2].Text = 1.ToString();

            A[6, 3].BackColor = Color.Green;
            A[6, 3].Text = 1.ToString();

            A[5, 4].BackColor = Color.Green;
            A[5, 4].Text = 1.ToString();

            A[5, 5].BackColor = Color.Green;
            A[5, 5].Text = 1.ToString();

            A[5, 6].BackColor = Color.Green;
            A[5, 6].Text = 1.ToString();

            A[1, 7].BackColor = Color.Green;
            A[1, 7].Text = 1.ToString();

            A[2, 7].BackColor = Color.Green;
            A[2, 7].Text = 1.ToString();

            A[3, 7].BackColor = Color.Green;
            A[3, 7].Text = 1.ToString();

            A[4, 8].BackColor = Color.Green;
            A[4, 8].Text = 1.ToString();

            A[5, 7].BackColor = Color.Green;
            A[5, 7].Text = 1.ToString();

            A[6, 7].BackColor = Color.Green;
            A[6, 7].Text = 1.ToString();

            A[7, 7].BackColor = Color.Green;
            A[7, 7].Text = 1.ToString();

            A[2, 4].BackColor = Color.Aqua;
            A[2, 6].BackColor = Color.Yellow;
        }
        public int inside(int i, int j)
        {
            int p = 1;
            if (i < 1) p = 0;
            if (i > 8) p = 0;
            if (j < 1) p = 0;
            if (j > 8) p = 0;
            return p;
        }
        private void button1_Click(object sender, EventArgs e)
        {

            int l = 480 / 10;
            for (int i = 1; i <= 8; i++)
                for (int j = 1; j <= 8; j++)
                {
                    A[i, j].Text = 0.ToString();
                    A[i, j].BackColor = Color.White;
                }
            A[1, 1].BackColor = Color.Green;
            A[1, 1].Text = 1.ToString();

            A[1, 2].BackColor = Color.Green;
            A[1, 2].Text = 1.ToString();

            A[1, 3].BackColor = Color.Green;
            A[1, 3].Text = 1.ToString();

            A[1, 4].BackColor = Color.Green;
            A[1, 4].Text = 1.ToString();

            A[2, 5].BackColor = Color.Green;
            A[2, 5].Text = 1.ToString();

            A[1, 5].BackColor = Color.Green;
            A[1, 5].Text = 1.ToString();

            A[2, 5].BackColor = Color.Green;
            A[2, 5].Text = 1.ToString();

            A[3, 5].BackColor = Color.Green;
            A[3, 5].Text = 1.ToString();

            A[2, 1].BackColor = Color.Green;
            A[2, 1].Text = 1.ToString();

            A[3, 1].BackColor = Color.Green;
            A[3, 1].Text = 1.ToString();

            A[4, 1].BackColor = Color.Green;
            A[4, 1].Text = 1.ToString();

            A[6, 1].BackColor = Color.Green;
            A[6, 1].Text = 1.ToString();

            A[3, 3].BackColor = Color.Green;
            A[3, 3].Text = 1.ToString();

            A[3, 4].BackColor = Color.Green;
            A[3, 4].Text = 1.ToString();

            A[6, 2].BackColor = Color.Green;
            A[6, 2].Text = 1.ToString();

            A[6, 3].BackColor = Color.Green;
            A[6, 3].Text = 1.ToString();

            A[5, 4].BackColor = Color.Green;
            A[5, 4].Text = 1.ToString();

            A[5, 5].BackColor = Color.Green;
            A[5, 5].Text = 1.ToString();

            A[5, 6].BackColor = Color.Green;
            A[5, 6].Text = 1.ToString();

            A[1, 7].BackColor = Color.Green;
            A[1, 7].Text = 1.ToString();

            A[2, 7].BackColor = Color.Green;
            A[2, 7].Text = 1.ToString();

            A[3, 7].BackColor = Color.Green;
            A[3, 7].Text = 1.ToString();

            A[4, 8].BackColor = Color.Green;
            A[4, 8].Text = 1.ToString();

            A[5, 7].BackColor = Color.Green;
            A[5, 7].Text = 1.ToString();

            A[6, 7].BackColor = Color.Green;
            A[6, 7].Text = 1.ToString();

            A[7, 7].BackColor = Color.Green;
            A[7, 7].Text = 1.ToString();

            A[2, 4].BackColor = Color.Aqua;
            A[2, 6].BackColor = Color.Yellow;


            int st = 1, dr = 1, ist, jst, ifn, jfn;
            ist = 2;
            jst = 4;
            ifn = 2;
            jfn = 6;
            int[] X;
            int[] Y;
            X = new int[10001];
            Y = new int[10001];
            X[1] = ist;
            Y[1] = jst;

            L1 = new List<Button>();
            Button bc = new Button();
            bc.Text = X[1].ToString();
            bc.Size = new Size(35, 35);
            bc.Location = new Point(60, 465);
            this.Controls.Add(bc);
            L1.Add(bc);

            L2 = new List<Button>();
             bc = new Button();
            bc.Text = Y[1].ToString();
            bc.Size = new Size(35, 35);
            bc.Location = new Point(60, 500);
            this.Controls.Add(bc);
            L2.Add(bc);
           // int poz = 1;
            A[ist, jst].Text = 1.ToString();
            while (st <= dr && int.Parse(A[ifn, jfn].Text.ToString()) == 0)
            {
                int i = X[st], j = Y[st];
                Application.DoEvents();
                Thread.Sleep(500);
                L1[st-1].BackColor = Color.Red;
                L2[st-1].BackColor = Color.Red;
                Application.DoEvents();
                Thread.Sleep(600);
                if (inside(i - 1, j) == 1 && int.Parse(A[i - 1, j].Text.ToString()) == 0)
                {
                    A[i - 1, j].Text = (int.Parse(A[i, j].Text.ToString()) + 1).ToString();
                    A[i - 1, j].BackColor = Color.DarkOrange;
                    dr = dr + 1;
                    X[dr] = i - 1; Y[dr] = j;

                    bc = new Button();
                    bc.Text = X[dr].ToString();
                    bc.Size = new Size(35, 35);
                    bc.Location = new Point((dr-1)*35+60, 465);
                    this.Controls.Add(bc);
                    L1.Add(bc);

                   
                    bc = new Button();
                    bc.Text = Y[dr].ToString();
                    bc.Size = new Size(35, 35);
                    bc.Location = new Point((dr-1) * 35 + 60, 500);
                    this.Controls.Add(bc);
                    L2.Add(bc);
                    Application.DoEvents();
                    Thread.Sleep(600);

                }
                if (inside(i + 1, j) == 1 && int.Parse(A[i + 1, j].Text.ToString()) == 0)
                {
                    A[i + 1, j].Text = (int.Parse(A[i, j].Text.ToString()) + 1).ToString();
                    A[i + 1, j].BackColor = Color.DarkOrange;
                    dr = dr + 1;
                    X[dr] = i + 1; Y[dr] = j;

                    bc = new Button();
                    bc.Text = X[dr].ToString();
                    bc.Size = new Size(35, 35);
                    bc.Location = new Point((dr - 1) * 35 + 60, 465);
                    this.Controls.Add(bc);
                    L1.Add(bc);

                    bc = new Button();
                    bc.Text = Y[dr].ToString();
                    bc.Size = new Size(35, 35);
                    bc.Location = new Point((dr - 1) * 35 + 60, 500);
                    this.Controls.Add(bc);
                    L2.Add(bc);
                    Application.DoEvents();
                    Thread.Sleep(600);
                }
                if (inside(i, j - 1) == 1 && int.Parse(A[i, j - 1].Text.ToString()) == 0)
                {
                    A[i, j - 1].Text = (int.Parse(A[i, j].Text.ToString()) + 1).ToString();
                    A[i, j - 1].BackColor = Color.DarkOrange;
                    dr = dr + 1;
                    X[dr] = i; Y[dr] = j - 1;

                    
                    bc = new Button();
                    bc.Text = X[dr].ToString();
                    bc.Size = new Size(35, 35);
                    bc.Location = new Point((dr - 1) * 35 + 60, 465);
                    this.Controls.Add(bc);
                    L1.Add(bc);

                    bc = new Button();
                    bc.Text = Y[dr].ToString();
                    bc.Size = new Size(35, 35);
                    bc.Location = new Point((dr - 1) * 35 + 60, 500);
                    this.Controls.Add(bc);
                    L2.Add(bc);
                    Application.DoEvents();
                    Thread.Sleep(600);
                }
                if (inside(i, j + 1) == 1 && int.Parse(A[i, j + 1].Text.ToString()) == 0)
                {
                    A[i, j + 1].Text = (int.Parse(A[i, j].Text.ToString()) + 1).ToString();
                    A[i, j + 1].BackColor = Color.DarkOrange;
                    dr = dr + 1;
                    X[dr] = i; Y[dr] = j + 1;

                   
                    bc = new Button();
                    bc.Text = X[dr].ToString();
                    bc.Size = new Size(35, 35);
                    bc.Location = new Point((dr - 1) * 35 + 60, 465);
                    this.Controls.Add(bc);
                    L1.Add(bc);

                   
                    bc = new Button();
                    bc.Text = Y[dr].ToString();
                    bc.Size = new Size(35, 35);
                    bc.Location = new Point((dr - 1) * 35 + 60, 500);
                    this.Controls.Add(bc);
                    L2.Add(bc);
                    Application.DoEvents();
                    Thread.Sleep(600);
                }
                Application.DoEvents();
                Thread.Sleep(400);
                L1[st - 1].BackColor = Color.White;
                L2[st - 1].BackColor = Color.White;
                st++;
            }
            A[ifn, jfn].BackColor = Color.Yellow;
            Application.DoEvents();
            Thread.Sleep(600);
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
                    if (a + 1 <= 8 && p == 1) { if (int.Parse(A[a + 1, b].Text.ToString()) == (int.Parse(A[a, b].Text.ToString()) - 1) && A[a + 1, b].BackColor == Color.DarkOrange || A[a + 1, b].BackColor == Color.Aqua) { a = a + 1; p = 0; } }
                    if (b - 1 > 0 && p == 1) { if (int.Parse(A[a, b - 1].Text.ToString()) == (int.Parse(A[a, b].Text.ToString()) - 1) && A[a, b - 1].BackColor == Color.DarkOrange || A[a, b - 1].BackColor == Color.Aqua) { b = b - 1; p = 0; } }
                    if (b + 1 <= 8 && p == 1) if (int.Parse(A[a, b + 1].Text.ToString()) == (int.Parse(A[a, b].Text.ToString()) - 1) && A[a, b + 1].BackColor == Color.DarkOrange || A[a, b + 1].BackColor == Color.Aqua) { b = b + 1; p = 0; }
                    X1[++z] = a;
                    Y1[z] = b;
                }
                for (int i = z; i >= 1; i--)
                {
                    if (i != z)
                    {
                        A[X1[i], Y1[i]].BackColor = Color.Red;

                        Application.DoEvents();
                        Thread.Sleep(400);
                    }

                    A[ist, jst].BackColor = Color.Aqua;
                    A[ifn, jfn].BackColor = Color.Yellow;
                }
            }
        }

        private void button2_Click(object sender, EventArgs e)
        {
            Form5 f5 = new Form5();
            f5.Text = "Despre coada";
            f5.ShowDialog();
        }
    }
}
