using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using MySql.Data.MySqlClient;


namespace Chestionare_Auto
{
    public partial class Administrator : Form
    {
        MySqlConnection conn;
        public string user;
        public int id_u;
        private int nr_intrebari=0;
        private int index = 0;
        private int index_a = 0;
        private int index_i = 0;
        private int index_c = 0;
        private int index_u = 0;
        private int nr_intrebari_adaugate=0;
        private int nr_utilizatori = 0;
        private int nr_chestionare = 0;
        private int nr_intrebari_s = 0;
        private string numescurt = "";
        public Administrator()
        {
            InitializeComponent();
        }

        private void Administrator_FormClosing(object sender, FormClosingEventArgs e)
        {
            Application.Exit();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            string utilizator = textBox1.Text;
            string parola = textBox2.Text;
            string cparola = textBox3.Text;
            if (utilizator != "" && parola != "" && cparola != "")
            {
                string q = "SELECT * FROM utilizatori WHERE Utilizator='" + utilizator + "'";
                DataBaseConnection DB = new DataBaseConnection();
                DB.openConnection();
                conn = DB.getConn();
                MySqlCommand c = new MySqlCommand(q, conn);
                MySqlDataReader dr = c.ExecuteReader();
                if (!dr.HasRows)
                {
                    if (parola == cparola)
                    {
                        q = "INSERT INTO utilizatori VALUES (null,'administrator','" + utilizator + "','" + parola + "')";
                        c = new MySqlCommand(q, conn);
                        dr.Close();
                        dr = c.ExecuteReader();

                        q = "SELECT max(id) from utilizatori";
                        c = new MySqlCommand(q, conn);
                        dr.Close();
                        dr = c.ExecuteReader();
                        dr.Read();
                        listBox6.Items.Add(dr[0].ToString() + "     " + "administrator" + "     " + utilizator);
                        nr_utilizatori++;

                        MessageBox.Show("Contul a fost creat!");
                    }
                    else MessageBox.Show("Parolele nu coincid!");
                }
                else MessageBox.Show("Nume utilizator existent!");
                DB.closeConnection();
            }
            else MessageBox.Show("Nu ați completat datele!");
        }




        private void button22_Click_1(object sender, EventArgs e)
        {
            string utilizator = textBox8.Text;
            if (utilizator != "")
            {
                string q = "SELECT * FROM utilizatori WHERE Utilizator='" + utilizator + "'";
                DataBaseConnection DB = new DataBaseConnection();
                DB.openConnection();
                conn = DB.getConn();
                MySqlCommand c = new MySqlCommand(q, conn);
                MySqlDataReader dr = c.ExecuteReader();
                if (!dr.HasRows)
                {
                    dr.Close();
                    q = "update utilizatori set Utilizator= '" + utilizator + "' where Utilizator='" + user + "'";
                    c = new MySqlCommand(q, conn);
                    c.ExecuteScalar();
                    textBox7.Text = utilizator;
                    user = utilizator;
                    MessageBox.Show("Numele de utilizator a fost actualizat!");

                    q = "SELECT * FROM utilizatori";
                    c = new MySqlCommand(q, conn);
                    dr = c.ExecuteReader();
                    listBox6.Items.Clear();
                    while (dr.Read())
                    {
                        listBox6.Items.Add(dr[0].ToString() + "     " + dr[1].ToString() + "     " + dr[2].ToString());
                        nr_utilizatori++;
                    }
                    listBox6.SetSelected(0, true);
                    dr.Close();

                }
                else MessageBox.Show("Nume utilizator existent!");
                DB.closeConnection();
            }
            else MessageBox.Show("Nu ati completat campul!");
        }

        private void button21_Click(object sender, EventArgs e)
        {
            string parola = textBox4.Text;
            string nparola = textBox5.Text;
            string nparolac = textBox6.Text;
            if (parola != "" && nparola != "" && nparolac != "")
            {
                DataBaseConnection DB = new DataBaseConnection();
                DB.openConnection();
                conn = DB.getConn();
                string q = "SELECT * FROM utilizatori WHERE Utilizator='" + user + "' AND Parola='" + parola + "'";
                MySqlCommand c = new MySqlCommand(q, conn);
                MySqlDataReader dr = c.ExecuteReader();
                if (dr.HasRows)
                {
                    if (nparola == nparolac)
                    {
                        dr.Close();
                        q = "update utilizatori set Parola = '" + nparola + "' where Utilizator='" + user + "'";
                        c = new MySqlCommand(q, conn);
                        c.ExecuteScalar();
                        MessageBox.Show("Parolele a fost actualizata!");
                    }
                    else MessageBox.Show("Parolele nu coincid!");
                }
                else MessageBox.Show("Parola actuala este gresita!");
                DB.closeConnection();
            }
            else MessageBox.Show("Nu ati completat campurile!");
        }

        private void Administrator_Shown(object sender, EventArgs e)
        {
            pictureBox1.Load("Imagini\\" + "imagine.jpg");
            textBox7.Text = user;
            DataBaseConnection DB = new DataBaseConnection();
            DB.openConnection();
            conn = DB.getConn();
            string q = "SELECT * FROM intrebare";
            MySqlCommand c = new MySqlCommand(q, conn);
            MySqlDataReader dr = c.ExecuteReader();
            listBox1.Items.Clear();
            listBox2.Items.Clear();
            while (dr.Read())
            {
                listBox1.Items.Add(dr[0].ToString() + "     " + dr[1].ToString() + "     " + dr[2].ToString() + "     " + dr[3].ToString() + "     " + dr[4].ToString());
                listBox2.Items.Add(dr[0].ToString() + "     " + dr[1].ToString() + "     " + dr[2].ToString() + "     " + dr[3].ToString() + "     " + dr[4].ToString());
                nr_intrebari++;
            }
            listBox2.SetSelected(0,true);
            dr.Close();

            q = "SELECT * FROM utilizatori";
            c = new MySqlCommand(q, conn);
            dr = c.ExecuteReader();
            listBox6.Items.Clear();
            while (dr.Read())
            {
                listBox6.Items.Add(dr[0].ToString() + "     " + dr[1].ToString() + "     " + dr[2].ToString());
                nr_utilizatori++;
            }
            listBox6.SetSelected(0, true);
            dr.Close();

            q = "SELECT * FROM intrebare";
            c = new MySqlCommand(q, conn);
            dr = c.ExecuteReader();
            listBox5.Items.Clear();
            while (dr.Read())
            {
                listBox5.Items.Add(dr[0].ToString() + "     " + dr[1].ToString() + "     " + dr[3].ToString() + "     " + dr[4].ToString());
                nr_intrebari_s++;
            }
            listBox5.SetSelected(0, true);
            dr.Close();

            q = "SELECT * FROM chestionar";
            c = new MySqlCommand(q, conn);
            dr = c.ExecuteReader();
            listBox4.Items.Clear();
            while (dr.Read())
            {
                listBox4.Items.Add(dr[0].ToString() + "     " + dr[1].ToString() + "     " + dr[2].ToString());
                nr_chestionare++;
            }
            listBox4.SetSelected(0, true);
            dr.Close();


            DB.closeConnection();
        }

        private void button6_Click(object sender, EventArgs e)
        {
            string intrebare = richTextBox1.Text;
            string ra = richTextBox2.Text;
            string rb = richTextBox3.Text;
            string rc = richTextBox4.Text;
            string rd = richTextBox5.Text;
            string sectiune = comboBox2.Text;
            string categorie = comboBox1.Text;
            char a = 'N', b = 'N', c = 'N', d = 'N';

            if (button8.BackColor == Color.Green) a = 'D';
            if (button2.BackColor == Color.Green) b = 'D';
            if (button3.BackColor == Color.Green) c = 'D';
            if (button4.BackColor == Color.Green) d = 'D';

            if (intrebare != ""&& ra != "" && rb != "" && rc != "" && rd != "" && categorie != "" && sectiune != "")
            {
                int id_i;
                DataBaseConnection DB = new DataBaseConnection();
                DB.openConnection();
                conn = DB.getConn();

                string q;
                MySqlCommand cm;

                if (numescurt != "")
                {
                    q = "INSERT INTO Intrebare (id_i,intrebare,poza,sectiune,categorie) VALUES (null,'" + intrebare + "','"+numescurt+"','" + sectiune + "','" + categorie + "')";
                    cm = new MySqlCommand(q, conn);
                    cm.ExecuteScalar();
                }
                else
                {
                    q = "INSERT INTO Intrebare (id_i,intrebare,poza,sectiune,categorie) VALUES (null,'" + intrebare + "',null,'" + sectiune + "','" + categorie + "')";
                    cm = new MySqlCommand(q, conn);
                    cm.ExecuteScalar();
                }

                q = "SELECT max(id_i)  FROM intrebare";
                cm = new MySqlCommand(q, conn);
                MySqlDataReader dr = cm.ExecuteReader();
                dr.Read();
                id_i = int.Parse(dr[0].ToString());
                dr.Close();

                q = "INSERT INTO Raspuns (id_i,id_r,raspuns,corect) VALUES (" + id_i + ",'a','" + ra + "','" + a + "')";
                cm = new MySqlCommand(q, conn);
                cm.ExecuteScalar();

                q = "INSERT INTO Raspuns (id_i,id_r,raspuns,corect) VALUES ("+id_i+",'b','"+rb+"','"+b+"')";
                cm = new MySqlCommand(q, conn);
                cm.ExecuteScalar();

                q = "INSERT INTO Raspuns (id_i,id_r,raspuns,corect) VALUES (" + id_i + ",'c','" + rc + "','" + c + "')";
                cm = new MySqlCommand(q, conn);
                cm.ExecuteScalar();

                q = "INSERT INTO Raspuns (id_i,id_r,raspuns,corect) VALUES (" + id_i + ",'d','" + rd + "','" + d + "')";
                cm = new MySqlCommand(q, conn);
                cm.ExecuteScalar();

                MessageBox.Show("Intreabarea a fost salvata!");

                listBox1.Items.Add(id_i + "     " + intrebare + "     " + "null" + "     " + sectiune + "     " + categorie);
                listBox2.Items.Add(id_i + "     " + intrebare + "     " + "null" + "     " + sectiune + "     " + categorie);
                listBox5.Items.Add(id_i + "     " + intrebare + "     " + sectiune + "     " + categorie);
                nr_intrebari++;
                nr_intrebari_s++;

                button8.BackColor = Color.White;
                button2.BackColor = Color.White;
                button3.BackColor = Color.White;
                button4.BackColor = Color.White;
                richTextBox2.BackColor = Color.White;
                richTextBox3.BackColor = Color.White;
                richTextBox4.BackColor = Color.White;
                richTextBox5.BackColor = Color.White;

                richTextBox1.Text = "";
                richTextBox2.Text = ""; 
                richTextBox3.Text = ""; 
                richTextBox4.Text = ""; 
                richTextBox5.Text = "";
                comboBox2.Text = ""; 
                comboBox1.Text = "B"; 

                DB.closeConnection();
            }
            else MessageBox.Show("Nu ati completat campurile!");
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

        private void button23_Click(object sender, EventArgs e)
        {
            this.Hide();
            Autentificare autentificare = new Autentificare();
            autentificare.Show();
        }

        private void button5_Click(object sender, EventArgs e)
        {
            if (nr_intrebari > 0)
            {
                listBox3.Items.Add(listBox2.Items[index]);
                nr_intrebari_adaugate++;
                string[] s = new string[--nr_intrebari];
                int j = 0;
                for (int i = 0; i <= nr_intrebari; i++)
                {
                    if (i != index) s[j++] = listBox2.Items[i].ToString();
                }
                listBox2.Items.Clear();
                for (int i = 0; i < nr_intrebari; i++)
                {
                    listBox2.Items.Add(s[i]);
                }
                if (index == nr_intrebari) index = 0;

                //if(nr_intrebari!=0) listBox3.Items.Add(listBox2.Items[index]);

                if (nr_intrebari > 0) listBox2.SetSelected(index, true);
                if (nr_intrebari_adaugate == 1) listBox3.SetSelected(0, true);
            }
            else MessageBox.Show("Nu mai exista intrabari care sa poata fi adaugate!");

        }

        private void listBox2_SelectedIndexChanged(object sender, EventArgs e)
        {
            index = listBox2.SelectedIndex;
        }

        private void listBox3_SelectedIndexChanged(object sender, EventArgs e)
        {
            index_a = listBox3.SelectedIndex;
        }

        private void button10_Click(object sender, EventArgs e)
        {
            index--;
            if (index == -1) index = nr_intrebari - 1;
            listBox2.SetSelected(index, true);
        }

        private void button11_Click(object sender, EventArgs e)
        {
            index++;
            if (index == nr_intrebari) index = 0;
            listBox2.SetSelected(index, true);
        }

        private void button13_Click(object sender, EventArgs e)
        {
            if (nr_intrebari_adaugate > 0)
            {
                index_a--;
                if (index_a == -1) index_a = nr_intrebari_adaugate - 1;
                listBox3.SetSelected(index_a, true);
            }
        }

        private void button12_Click(object sender, EventArgs e)
        {
            if (nr_intrebari_adaugate>0)
            {
                index_a++;
                if (index_a == nr_intrebari_adaugate) index_a = 0;
                listBox3.SetSelected(index_a, true);
            }
        }

        private void button7_Click(object sender, EventArgs e)
        {
            if (nr_intrebari_adaugate > 0)
            {
                listBox2.Items.Add(listBox3.Items[index_a]);
                nr_intrebari++;
                string[] s = new string[--nr_intrebari_adaugate];
                int j = 0;
                for (int i = 0; i <= nr_intrebari_adaugate; i++)
                {
                    if (i != index_a) s[j++] = listBox3.Items[i].ToString();
                }
                listBox3.Items.Clear();
                for (int i = 0; i < nr_intrebari_adaugate; i++)
                {
                    listBox3.Items.Add(s[i]);
                }
                if (index_a == nr_intrebari_adaugate) index_a = 0;

                //if (nr_intrebari_adaugate != 0)  listBox2.Items.Add(listBox3.Items[index_a]);

                if (nr_intrebari_adaugate > 0) listBox3.SetSelected(index_a, true);
                if (nr_intrebari == 1) listBox2.SetSelected(0, true);
            }
            else MessageBox.Show("Nu mai exista intrabari care sa poata fi eliminate!");
        }

        private void button9_Click(object sender, EventArgs e)
        {
            string nume = textBox9.Text;
            if(nume!="")
            {
                if(nr_intrebari_adaugate > 0)
                {
                    DataBaseConnection DB = new DataBaseConnection();
                    DB.openConnection();
                    conn = DB.getConn();
                    string q;
                    MySqlCommand cm;
                   


                    q = "INSERT INTO Chestionar (id_c,denumire,punctaj_max) VALUES (null,'"+nume+"',"+ nr_intrebari_adaugate + ")";
                    nr_chestionare++;
                    cm = new MySqlCommand(q, conn);
                    cm.ExecuteScalar();

                    q = "select max(id_c) from chestionar;";
                    cm = new MySqlCommand(q, conn);
                    MySqlDataReader dr = cm.ExecuteReader();
                    dr.Read();
                    int id_c = int.Parse(dr[0].ToString());
                    dr.Close();
                    listBox4.Items.Add(id_c + "     " + nume + "     " + nr_intrebari_adaugate);

                    for (int i = 0; i < nr_intrebari_adaugate; i++)
                    {
                        string t = listBox3.Items[i].ToString();
                        string[] s = t.Split();
                        int id_i = int.Parse(s[0].ToString());
                        q = "INSERT INTO Intrebari_Chestionar (id_c,id_i,nr_ordine) VALUES ("+id_c+","+id_i+",0)";
                        cm = new MySqlCommand(q, conn);
                        cm.ExecuteScalar();
                    }
                    MessageBox.Show("Chestionarul a fost creat!");

                    DB.closeConnection();
                }
                else MessageBox.Show("Chestionarul trebuie sa contina intrebari!");
            }
            else MessageBox.Show("Nu ati introdus Numele chestionarului!!");
        }

        private void button25_Click(object sender, EventArgs e)
        {
            index_u--;
            if (index_u == -1) index_u = nr_utilizatori - 1;
            listBox6.SetSelected(index_u, true);
        }

        private void button19_Click(object sender, EventArgs e)
        {
            index_i--;
            if (index_i == -1) index_i = nr_intrebari_s - 1;
            listBox5.SetSelected(index_i, true);
        }

        private void button15_Click(object sender, EventArgs e)
        {
            index_c--;
            if (index_c == -1) index_c = nr_chestionare - 1;
            listBox4.SetSelected(index_c, true);
        }

        private void button24_Click(object sender, EventArgs e)
        {
            index_u++;
            if (index_u == nr_utilizatori) index_u = 0;
            listBox6.SetSelected(index_u, true);
        }

        private void button18_Click(object sender, EventArgs e)
        {
            index_i++;
            if (index_i == nr_intrebari_s) index_i = 0;
            listBox5.SetSelected(index_i, true);
        }

        private void button14_Click(object sender, EventArgs e)
        {
            index_c++;
            if (index_c == nr_chestionare) index_c = 0;
            listBox4.SetSelected(index_c, true);
        }

        private void button16_Click(object sender, EventArgs e)
        {
            if (nr_chestionare > 0)
            {

                DataBaseConnection DB = new DataBaseConnection();
                DB.openConnection();
                conn = DB.getConn();
                string t = listBox4.Items[index_c].ToString();
                string[] s1 = t.Split();
                int id_c = int.Parse(s1[0].ToString());
                string q = "delete  from chestionar where id_c="+ id_c;
                MySqlCommand cm = new MySqlCommand(q, conn);
                cm.ExecuteScalar();
                DB.closeConnection();

                string[] s = new string[--nr_chestionare];
                int j = 0;
                for (int i = 0; i <= nr_chestionare; i++)
                {
                    if (i != index_c) s[j++] = listBox4.Items[i].ToString();
                }
                listBox4.Items.Clear();
                for (int i = 0; i < nr_chestionare; i++)
                {
                    listBox4.Items.Add(s[i]);
                }
                if (index_c == nr_chestionare) index_c = 0;


                if (nr_chestionare > 0) listBox4.SetSelected(index_c, true);
            }
            else MessageBox.Show("Nu mai exista chestionare care sa poata fi sterse!");
        }

        private void listBox4_SelectedIndexChanged(object sender, EventArgs e)
        {
            index_c = listBox4.SelectedIndex;
        }

        private void listBox5_SelectedIndexChanged(object sender, EventArgs e)
        {
            index_i = listBox5.SelectedIndex;
        }

        private void listBox6_SelectedIndexChanged(object sender, EventArgs e)
        {
            index_u = listBox6.SelectedIndex;
        }

        private void button17_Click(object sender, EventArgs e)
        {
            if (nr_intrebari_s > 0)
            {

                DataBaseConnection DB = new DataBaseConnection();
                DB.openConnection();
                conn = DB.getConn();
                string t = listBox5.Items[index_i].ToString();
                string[] s1 = t.Split();
                int id_i = int.Parse(s1[0].ToString());
                string q = "delete  from intrebare where id_i=" + id_i;
                MySqlCommand cm = new MySqlCommand(q, conn);
                cm.ExecuteScalar();

                string[] s = new string[--nr_intrebari_s];
                int j = 0;
                for (int i = 0; i <= nr_intrebari_s; i++)
                {
                    if (i != index_i) s[j++] = listBox5.Items[i].ToString();
                }
                listBox5.Items.Clear();
                for (int i = 0; i < nr_intrebari_s; i++)
                {
                    listBox5.Items.Add(s[i]);
                }
                if (index_i == nr_intrebari_s) index_i = 0;


                if (nr_intrebari_s > 0) listBox5.SetSelected(index_i, true);


                MySqlCommand cmd = new MySqlCommand("stergeIntrebariChestionar", conn);
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.ExecuteScalar();

                cmd = new MySqlCommand("stergeChestionar0", conn);
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.ExecuteScalar();

                q = "SELECT count(*) FROM chestionar";
                cm = new MySqlCommand(q, conn);
                MySqlDataReader dr = cm.ExecuteReader();
                dr.Read();
                nr_chestionare = int.Parse(dr[0].ToString());
                dr.Close();

                for (int i = 0; i < nr_chestionare; i++)
                {
                     t = listBox4.Items[i].ToString();
                     s1 = t.Split();
                    int id_c = int.Parse(s1[0].ToString());
                    q = "update chestionar set punctaj_max = (select count(*) from intrebari_chestionar where id_c = "+id_c+") where id_c ="+id_c;
                    cm = new MySqlCommand(q, conn);
                    cm.ExecuteScalar();
                }


                q = "SELECT * FROM chestionar";
                cm = new MySqlCommand(q, conn);
                 dr = cm.ExecuteReader();
                listBox4.Items.Clear();
                while (dr.Read())
                {
                    listBox4.Items.Add(dr[0].ToString() + "     " + dr[1].ToString() + "     " + dr[2].ToString());
                    nr_chestionare++;
                }
                listBox4.SetSelected(0, true);
                dr.Close();


                    DB.closeConnection();

            }
            else MessageBox.Show("Nu mai exista intrebari care sa poata fi sterse!");
        }

        private void button20_Click(object sender, EventArgs e)
        {
            if (nr_utilizatori > 0)
            {

                DataBaseConnection DB = new DataBaseConnection();
                DB.openConnection();
                conn = DB.getConn();
                string t = listBox6.Items[index_u].ToString();
                string[] s1 = t.Split();
                int id_u = int.Parse(s1[0].ToString());
                string q = "delete  from utilizatori where id=" + id_u;
                MySqlCommand cm = new MySqlCommand(q, conn);
                cm.ExecuteScalar();
                DB.closeConnection();

                string[] s = new string[--nr_utilizatori];
                int j = 0;
                for (int i = 0; i <= nr_utilizatori; i++)
                {
                    if (i != index_u) s[j++] = listBox6.Items[i].ToString();
                }
                listBox6.Items.Clear();
                for (int i = 0; i < nr_utilizatori; i++)
                {
                    listBox6.Items.Add(s[i]);
                }
                if (index_u == nr_utilizatori) index_u = 0;


                if (nr_utilizatori > 0) listBox6.SetSelected(index_u, true);
                MessageBox.Show("Utilizatorul a fost sters!");
            }
            else MessageBox.Show("Nu mai exista utilizatori care sa poata fi stersi!");
        }

        private void pictureBox1_Click(object sender, EventArgs e)
        {
            openFileDialog1.Filter = "Fisiere jpg|*.jpg";
            openFileDialog1.FileName = "";
            if (openFileDialog1.ShowDialog() == DialogResult.OK)
            {
                string numefis = openFileDialog1.FileName;
                pictureBox1.Load(numefis);
                string[] s = numefis.Split('\\');
                numescurt = s[s.Length - 1];
                if (numefis != "")
                    File.Copy(numefis, "Imagini\\" + numescurt, true);
                MessageBox.Show("Imaginea a fost încărcată!");

            }
        }
    }
}
