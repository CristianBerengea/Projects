using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using MySql.Data.MySqlClient;

namespace Chestionare_Auto
{
    public partial class Client : Form
    {
        MySqlConnection conn;

        public string user;
        public int id_u;
        private int nr_intrebari_mv = 0;
        private int nr_intrebari_vi = 0;
        private int punctaj = 0;
        private int gresite = 0;
        private int index;
        private int index1;
        public int id_i;
        public int id_i1;
        private char A = 'N', B = 'N', C = 'N', D = 'N';
        public Client()
        {
            InitializeComponent();
        }
        


        private void Client_Shown(object sender, EventArgs e)
        {
            string q = "SELECT Chestionar.id_c, Chestionar.denumire, Chestionar.punctaj_max , X.NR, x.pMaxim FROM Chestionar LEFT OUTER JOIN (SELECT Chestionar.id_c, Test.id_u, COUNT(*) AS NR, MAX(Test.punctaj) as pMaxim FROM Chestionar JOIN Test ON Chestionar.id_c = Test.id_c and test.id_u = " + id_u+"  GROUP BY Chestionar.id_c) X ON X.id_c = Chestionar.id_c order by Chestionar.id_c;";
            DataBaseConnection DB = new DataBaseConnection();
            DB.openConnection();
            conn = DB.getConn();
            MySqlCommand c = new MySqlCommand(q, conn);
            MySqlDataReader dr = c.ExecuteReader();
            listBox1.Items.Clear();

            while (dr.Read())
            {
                listBox1.Items.Add(dr[0].ToString() +"   "+dr[1].ToString() + "                                                          " + dr[2].ToString()+ "                                                          "+ dr[3].ToString() + "                                                          " + dr[4].ToString());
            }

            dr.Close();

            q = "SELECT id_i, intrebare FROM intrebare";

            c = new MySqlCommand(q, conn);
            dr = c.ExecuteReader();
            listBox2.Items.Clear();
            listBox3.Items.Clear();
            while (dr.Read())
            {
                listBox2.Items.Add(dr[0].ToString() + "   " + dr[1].ToString());
                listBox3.Items.Add(dr[0].ToString() + "   " + dr[1].ToString());
                nr_intrebari_mv++;
            }
            nr_intrebari_vi = nr_intrebari_mv;
            dr.Close();

            q = "SELECT distinct sectiune FROM intrebare;";

            c = new MySqlCommand(q, conn);
            dr = c.ExecuteReader();
            while (dr.Read())
            {
                comboBox1.Items.Add(dr[0].ToString());
                comboBox4.Items.Add(dr[0].ToString());
            }
            dr.Close();

            q = "SELECT distinct categorie FROM intrebare;";

            c = new MySqlCommand(q, conn);
            dr = c.ExecuteReader();
            while (dr.Read())
            {
                comboBox2.Items.Add(dr[0].ToString());
                comboBox3.Items.Add(dr[0].ToString());
            }
            dr.Close();

            // textBox1.Text = nr_intrebari_mv.ToString();
            textBox2.Text = punctaj.ToString();
            textBox3.Text = gresite.ToString();

            listBox2.SetSelected(0, true);
            listBox3.SetSelected(0, true);


            textBox7.Text = user.ToString();

            DB.closeConnection();
        }


        private void button1_Click(object sender, EventArgs e)
        {
            if (listBox1.SelectedIndex != -1)
            {
                Test test = new Test();
                string t = listBox1.SelectedItem.ToString();
                string[] s = t.Split();
                test.id_c = int.Parse(s[0]);
  

                DataBaseConnection DB = new DataBaseConnection();
                DB.openConnection();
                conn = DB.getConn();
                string q = "select punctaj_max from chestionar where id_c="+ test.id_c;
                MySqlCommand c = new MySqlCommand(q,conn);
                MySqlDataReader dr = c.ExecuteReader();
                dr.Read();
                test.m = int.Parse(dr[0].ToString());
                dr.Close();

                test.user = user;
                test.id_u = id_u;
                


                 q = "INSERT INTO Test (id_t,data,punctaj,id_c,id_u) VALUES (null,sysdate(),0,"+test.id_c+"," + id_u + ")";
                 c = new MySqlCommand(q, conn);
                 c.ExecuteScalar();

                 q = "SELECT max(id_t) from test";
                 c = new MySqlCommand(q, conn);
                dr = c.ExecuteReader();
                dr.Read();
                test.id_t = int.Parse(dr[0].ToString());
                //MessageBox.Show(test.id_t.ToString());

                test.Show();
                this.Hide();
                DB.closeConnection();
            }
            else MessageBox.Show("Nu ați selectat un chestionar!");
        }

        private void listBox2_SelectedIndexChanged_1(object sender, EventArgs e)
        {
            index = listBox2.SelectedIndex;
            string t = listBox2.SelectedItem.ToString();
            string[] s = t.Split();
            id_i = int.Parse(s[0].ToString());
            string q = "SELECT intrebare from intrebare where id_i = " + s[0];
            DataBaseConnection DB = new DataBaseConnection();
            DB.openConnection();
            conn = DB.getConn();
            MySqlCommand c = new MySqlCommand(q, conn);
            MySqlDataReader dr = c.ExecuteReader();

            dr.Read();
            richTextBox1.Text = dr[0].ToString();


            dr.Close();
            q = "SELECT raspuns,corect from raspuns where id_i = " + s[0] + " and id_r = 'a' ";
            c = new MySqlCommand(q, conn);
            dr = c.ExecuteReader();
            dr.Read();
            richTextBox2.Text = dr[0].ToString();
            A = dr[1].ToString() == "D" ? 'D' : 'N';

                dr.Close();
            q = "SELECT raspuns,corect from raspuns where id_i = " + s[0] + " and id_r = 'b' ";
            c = new MySqlCommand(q, conn);
            dr = c.ExecuteReader();
            dr.Read();
            richTextBox3.Text = dr[0].ToString();
            B = dr[1].ToString() == "D" ? 'D' : 'N';

            dr.Close();
            q = "SELECT raspuns,corect from raspuns where id_i = " + s[0] + " and id_r = 'c' ";
            c = new MySqlCommand(q, conn);
            dr = c.ExecuteReader();
            dr.Read();
            richTextBox4.Text = dr[0].ToString();
            C = dr[1].ToString() == "D" ? 'D' : 'N';

            dr.Close();
            q = "SELECT raspuns,corect from raspuns where id_i = " + s[0] + " and id_r = 'd' ";
            c = new MySqlCommand(q, conn);
            dr = c.ExecuteReader();
            dr.Read();
            richTextBox5.Text = dr[0].ToString();
            D = dr[1].ToString() == "D" ? 'D' : 'N';

            button8.BackColor = Color.White;
            button2.BackColor = Color.White;
            button3.BackColor = Color.White;
            button4.BackColor = Color.White;
            button20.BackColor = Color.White;
            button19.BackColor = Color.White;
            button18.BackColor = Color.White;
            button17.BackColor = Color.White;
            richTextBox2.BackColor = Color.White;
            richTextBox3.BackColor = Color.White;
            richTextBox4.BackColor = Color.White;
            richTextBox5.BackColor = Color.White;

            dr.Close();

            q = "SELECT poza from intrebare where id_i = " + s[0];
            c = new MySqlCommand(q, conn);
            dr = c.ExecuteReader();
            dr.Read();
            string numescurt = dr[0].ToString();
            if(numescurt!="") pictureBox1.Load("Imagini\\" + numescurt);
            else pictureBox1.Load("Imagini\\" + "imagine1.jpg");

            DB.closeConnection();
        }

        private void listBox3_SelectedIndexChanged(object sender, EventArgs e)
        {
            index1 = listBox3.SelectedIndex;
            string t = listBox3.SelectedItem.ToString();
            string[] s = t.Split();
            id_i1 = int.Parse(s[0].ToString());
            string q = "SELECT intrebare from intrebare where id_i = " + s[0];
            DataBaseConnection DB = new DataBaseConnection();
            DB.openConnection();
            conn = DB.getConn();
            MySqlCommand c = new MySqlCommand(q, conn);
            MySqlDataReader dr = c.ExecuteReader();

            dr.Read();
            richTextBox10.Text = dr[0].ToString();


            dr.Close();
            q = "SELECT raspuns,corect from raspuns where id_i = " + s[0] + " and id_r = 'a' ";
            c = new MySqlCommand(q, conn);
            dr = c.ExecuteReader();
            dr.Read();
            richTextBox9.Text = dr[0].ToString();
            if (dr[1].ToString() == "D") { button15.BackColor = Color.Green; richTextBox9.BackColor = Color.Green; }
            else { button15.BackColor = Color.Red;  richTextBox9.BackColor = Color.Red; };

            dr.Close();
            q = "SELECT raspuns,corect from raspuns where id_i = " + s[0] + " and id_r = 'b' ";
            c = new MySqlCommand(q, conn);
            dr = c.ExecuteReader();
            dr.Read();
            richTextBox8.Text = dr[0].ToString();
            if (dr[1].ToString() == "D") { button14.BackColor = Color.Green;  richTextBox8.BackColor = Color.Green; }
            else { button14.BackColor = Color.Red;  richTextBox8.BackColor = Color.Red; };

            dr.Close();
            q = "SELECT raspuns,corect from raspuns where id_i = " + s[0] + " and id_r = 'c' ";
            c = new MySqlCommand(q, conn);
            dr = c.ExecuteReader();
            dr.Read();
            richTextBox7.Text = dr[0].ToString();
            if (dr[1].ToString() == "D") { button13.BackColor = Color.Green; richTextBox7.BackColor = Color.Green; }
            else { button13.BackColor = Color.Red; richTextBox7.BackColor = Color.Red; };

            dr.Close();
            q = "SELECT raspuns,corect from raspuns where id_i = " + s[0] + " and id_r = 'd' ";
            c = new MySqlCommand(q, conn);
            dr = c.ExecuteReader();
            dr.Read();
            richTextBox6.Text = dr[0].ToString();
            if (dr[1].ToString() == "D") { button12.BackColor = Color.Green; richTextBox6.BackColor = Color.Green; }
            else { button12.BackColor = Color.Red; richTextBox6.BackColor = Color.Red; };
            dr.Close();

            q = "SELECT poza from intrebare where id_i = " + s[0];
            c = new MySqlCommand(q, conn);
            dr = c.ExecuteReader();
            dr.Read();
            string numescurt = dr[0].ToString();
            if (numescurt != "") pictureBox2.Load("Imagini\\" + numescurt);
            else pictureBox2.Load("Imagini\\" + "imagine1.jpg");

            DB.closeConnection();
        }

        private void button6_Click(object sender, EventArgs e)
        {  
            index--;
            if (index == -1) index = nr_intrebari_mv - 1;
            listBox2.SetSelected(index, true);
        }

        private void button7_Click(object sender, EventArgs e)
        {
            index++;
            if (index == nr_intrebari_mv) index = 0;
            listBox2.SetSelected(index, true);

        }

        private void button10_Click(object sender, EventArgs e)
        {
            index1--;
            if (index1 == -1) index1 = nr_intrebari_vi - 1;
            listBox3.SetSelected(index1, true);
        }

        private void button9_Click(object sender, EventArgs e)
        {
            index1++;
            if (index1 == nr_intrebari_vi) index1 = 0;
            listBox3.SetSelected(index1, true);
        }

        private void button8_Click(object sender, EventArgs e)
        {
            if (button8.BackColor != Color.Green)
            {
                button8.BackColor = Color.Green;
                richTextBox2.BackColor = Color.Green;
            }
            else
            {
                button8.BackColor = Color.White;
                richTextBox2.BackColor = Color.White;
            }
        }

        private void button2_Click(object sender, EventArgs e)
        {
            if (button2.BackColor != Color.Green)
            {
                button2.BackColor = Color.Green;
                richTextBox3.BackColor = Color.Green;
            }
            else
            {
                button2.BackColor = Color.White;
                richTextBox3.BackColor = Color.White;
            }
        }

        private void Client_FormClosing(object sender, FormClosingEventArgs e)
        {
            Application.Exit();
        }


        private void button16_Click(object sender, EventArgs e)
        {
            string sectiune = comboBox4.Text;
            string categorie = comboBox3.Text;
            if (sectiune != "" || categorie != "")
            {
                DataBaseConnection DB = new DataBaseConnection();
                DB.openConnection();
                conn = DB.getConn();
                string q="";
                if (sectiune != "" && categorie != "")
                {
                    q = "SELECT id_i, intrebare FROM intrebare where sectiune = '" + sectiune + "' and categorie ='" + categorie + "'";
                }
                else if (sectiune == "" && categorie != "")
                {
                     q = "SELECT id_i, intrebare FROM intrebare where  categorie ='" + categorie + "'";
                }
                else if (sectiune != "" && categorie == "")
                {
                    q = "SELECT id_i, intrebare FROM intrebare where sectiune = '" + sectiune + "'";
                }

                MySqlCommand c = new MySqlCommand(q, conn);
                MySqlDataReader dr = c.ExecuteReader();
                nr_intrebari_mv = 0;
                if (dr.HasRows)
                {
                    listBox2.Items.Clear();
                    while (dr.Read())
                    {
                        listBox2.Items.Add(dr[0].ToString() + "   " + dr[1].ToString());
                        nr_intrebari_mv++;
                    }
                    dr.Close();

                    listBox2.SetSelected(0, true);
                }
                else MessageBox.Show("Cautarea nu are reziltate, au fost pastrate rezultatete anterioare!");
                DB.closeConnection();
            }
            else MessageBox.Show("Nu ati selectat nici un criteriu de cautare!");
        }

        private void button11_Click(object sender, EventArgs e)
        {
            string sectiune = comboBox1.Text;
            string categorie = comboBox2.Text;
            if (sectiune != "" || categorie != "")
            {
                DataBaseConnection DB = new DataBaseConnection();
                DB.openConnection();
                conn = DB.getConn();
                string q = "";
                if (sectiune != "" && categorie != "")
                {
                    q = "SELECT id_i, intrebare FROM intrebare where sectiune = '" + sectiune + "' and categorie ='" + categorie + "'";
                }
                else if (sectiune == "" && categorie != "")
                {
                    q = "SELECT id_i, intrebare FROM intrebare where  categorie ='" + categorie + "'";
                }
                else if (sectiune != "" && categorie == "")
                {
                    q = "SELECT id_i, intrebare FROM intrebare where sectiune = '" + sectiune + "'";
                }

                MySqlCommand c = new MySqlCommand(q, conn);
                MySqlDataReader dr = c.ExecuteReader();
                nr_intrebari_vi = 0;
                if (dr.HasRows)
                {
                    listBox3.Items.Clear();
                    while (dr.Read())
                    {
                        listBox3.Items.Add(dr[0].ToString() + "   " + dr[1].ToString());
                        nr_intrebari_vi++;
                    }
                    dr.Close();

                    listBox3.SetSelected(0, true);
                }
                else MessageBox.Show("Cautarea nu are reziltate, au fost pastrate rezultatete anterioare!");
                DB.closeConnection();
            }
            else MessageBox.Show("Nu ati selectat nici un criteriu de cautare!");
        }

        private void button23_Click(object sender, EventArgs e)
        {
            this.Hide();
            Autentificare autentificare = new Autentificare();
            autentificare.Show();

        }

       

        private void button3_Click(object sender, EventArgs e)
        {
            if (button3.BackColor != Color.Green)
            {
                button3.BackColor = Color.Green;
                richTextBox4.BackColor = Color.Green;
            }
            else
            {
                button3.BackColor = Color.White;
                richTextBox4.BackColor = Color.White;
            }
        }

        private void button4_Click(object sender, EventArgs e)
        {
            if (button4.BackColor != Color.Green)
            {
                button4.BackColor = Color.Green;
                richTextBox5.BackColor = Color.Green;
            }
            else
            {
                button4.BackColor = Color.White;
                richTextBox5.BackColor = Color.White;
            }
        }

        private void button5_Click(object sender, EventArgs e)
        {
           
            char a = 'N', b = 'N', c = 'N', d = 'N';
            if (button8.BackColor == Color.Green) a = 'D';
            if (button2.BackColor == Color.Green) b = 'D';
            if (button3.BackColor == Color.Green) c = 'D';
            if (button4.BackColor == Color.Green) d = 'D';

            if (A == a && B == b && C == c && D == d) { punctaj++; textBox2.Text = punctaj.ToString();}
            else { gresite++; textBox3.Text = gresite.ToString(); };

            if( A=='D') { button20.BackColor = Color.Green; }
            else { button20.BackColor = Color.Red; }
            if (B == 'D') { button19.BackColor = Color.Green; }
            else { button19.BackColor = Color.Red; }
            if (C == 'D') { button18.BackColor = Color.Green; }
            else { button18.BackColor = Color.Red; }
            if (D == 'D') { button17.BackColor = Color.Green; }
            else { button17.BackColor = Color.Red; }

        }
    }
}
