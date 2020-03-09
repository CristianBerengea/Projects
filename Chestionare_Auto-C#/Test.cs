using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Timers;
using System.Windows.Forms;
using MySql.Data.MySqlClient;

namespace Chestionare_Auto
{
    public partial class Test : Form
    {
        MySqlConnection conn;
        public int id_c;
        private int punctaj = 0;
        private int gresite = 0;
        private int nr_intrebari = 0;
        private int index = 0;
        public int id_t;
        public int id_u;
        public string user;
        private int id_i;

        System.Timers.Timer t = new System.Timers.Timer();
        public int m=0, s=0;
        public Test()
        {
            InitializeComponent();
        }

        private void button5_Click(object sender, EventArgs e)
        {
            string[] s = new string[--nr_intrebari];
            int j = 0;
            for(int i=0;i<= nr_intrebari; i++)
            {
                if(i!=index) s[j++] = listBox1.Items[i].ToString(); 
            }
            listBox1.Items.Clear();
            for (int i = 0; i < nr_intrebari; i++)
            {
                listBox1.Items.Add(s[i]);
            }
            

            char a = 'N', b = 'N', c = 'N', d = 'N';
            if (button1.BackColor == Color.Green) a = 'D';
            if (button2.BackColor == Color.Green) b = 'D';
            if (button3.BackColor == Color.Green) c = 'D';
            if (button4.BackColor == Color.Green) d = 'D';

            string q = "INSERT INTO Raspuns_Test (id_t,id_i,id_r,corect)  VALUES ("+id_t+","+id_i+",'a','"+a+"');";
            DataBaseConnection DB = new DataBaseConnection();
            DB.openConnection();
            conn = DB.getConn();
            MySqlCommand cm = new MySqlCommand(q, conn);
            cm.ExecuteScalar();

            q = "INSERT INTO Raspuns_Test (id_t,id_i,id_r,corect)  VALUES (" + id_t + "," + id_i + ",'b','" + b + "');";
            cm = new MySqlCommand(q, conn);
            cm.ExecuteScalar();

            q = "INSERT INTO Raspuns_Test (id_t,id_i,id_r,corect)  VALUES (" + id_t + "," + id_i + ",'c','" + c + "');";
            cm = new MySqlCommand(q, conn);
            cm.ExecuteScalar();

            q = "INSERT INTO Raspuns_Test (id_t,id_i,id_r,corect)  VALUES (" + id_t + "," + id_i + ",'d','" + d + "');";
            cm = new MySqlCommand(q, conn);
            cm.ExecuteScalar();

            q = "SELECT COUNT(*) FROM Raspuns_Test WHERE id_t = "+id_t+" AND id_i = "+id_i+ " AND  (id_i,id_r,corect) IN (SELECT id_i,id_r,corect FROM Raspuns)";
            cm = new MySqlCommand(q, conn);
            MySqlDataReader dr = cm.ExecuteReader();
            dr.Read();

            int x = int.Parse(dr[0].ToString());
            //MessageBox.Show(x.ToString()+" "+id_t.ToString()+"  "+id_i.ToString());
            dr.Close();

            if (x == 4)
            {
                punctaj++;
                textBox2.Text = punctaj.ToString();
            }
            else
            {
                gresite++;
                textBox3.Text = gresite.ToString();
            }

            if (index == nr_intrebari) index = 0;
            if (nr_intrebari > 0) listBox1.SetSelected(index, true);
            textBox1.Text = nr_intrebari.ToString();

            if (nr_intrebari==0)
            {
                q = "UPDATE Test SET punctaj = "+punctaj+" WHERE id_t ="+id_t+"";
                cm = new MySqlCommand(q, conn);
                cm.ExecuteScalar();

                Client client = new Client();
                client.Show();
                client.id_u = id_u;
                client.user = user;
                this.Hide();
                MessageBox.Show("Punctajul obtinut este:"+punctaj);

                t.Stop();
            }
            DB.closeConnection();
        }

        private void Test_Shown(object sender, EventArgs e)
        {
            t.Start();
            textBox4.Text = string.Format("{0}:{1}", m.ToString().PadLeft(2, '0'), s.ToString().PadLeft(2, '0'));
            string q = "SELECT intrebare.id_i, intrebare.intrebare FROM intrebari_chestionar join intrebare on intrebari_chestionar.id_i = intrebare.id_i where id_c = " + id_c;
            DataBaseConnection DB = new DataBaseConnection();
            DB.openConnection();
            conn = DB.getConn();
            MySqlCommand c = new MySqlCommand(q, conn);
            MySqlDataReader dr = c.ExecuteReader();
            listBox1.Items.Clear();

            while (dr.Read())
            {
                nr_intrebari++;
                listBox1.Items.Add(dr[0].ToString() + "  " + dr[1].ToString());
            }

            listBox1.SetSelected(0,true);
            textBox1.Text = nr_intrebari.ToString();
            textBox2.Text = punctaj.ToString();
            textBox3.Text = gresite.ToString();


            DB.closeConnection();
        }

        private void listBox1_SelectedIndexChanged(object sender, EventArgs e)
        {
            index = listBox1.SelectedIndex;
            string t = listBox1.SelectedItem.ToString();
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
            q = "SELECT raspuns from raspuns where id_i = " + s[0] +" and id_r = 'a' ";
            c = new MySqlCommand(q, conn);
            dr = c.ExecuteReader();
            dr.Read();
            richTextBox2.Text = dr[0].ToString();

            dr.Close();
            q = "SELECT raspuns from raspuns where id_i = " + s[0] + " and id_r = 'b' ";
            c = new MySqlCommand(q, conn);
            dr = c.ExecuteReader();
            dr.Read();
            richTextBox3.Text = dr[0].ToString();

            dr.Close();
            q = "SELECT raspuns from raspuns where id_i = " + s[0] + " and id_r = 'c' ";
            c = new MySqlCommand(q, conn);
            dr = c.ExecuteReader();
            dr.Read();
            richTextBox4.Text = dr[0].ToString();

            dr.Close();
            q = "SELECT raspuns from raspuns where id_i = " + s[0] + " and id_r = 'd' ";
            c = new MySqlCommand(q, conn);
            dr = c.ExecuteReader();
            dr.Read();
            richTextBox5.Text = dr[0].ToString();

            button1.BackColor = Color.White;
            button2.BackColor = Color.White;
            button3.BackColor = Color.White;
            button4.BackColor = Color.White;
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
            if (numescurt != "") pictureBox1.Load("Imagini\\" + numescurt);
            else pictureBox1.Load("Imagini\\" + "imagine1.jpg");

            DB.closeConnection();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            if (button1.BackColor != Color.Green)
            {
                button1.BackColor = Color.Green;
                richTextBox2.BackColor = Color.Green;
            }
            else
            {
                button1.BackColor = Color.White;
                richTextBox2.BackColor = Color.White;
            }
        }

        private void button2_Click(object sender, EventArgs e)
        {
            if (button2.BackColor != Color.Green)
            {
                button2.BackColor = Color.Green;
                richTextBox3.BackColor = Color.Green;
            }else
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
            }else
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
            }else
            {
                button4.BackColor = Color.White;
                richTextBox5.BackColor = Color.White;
            }
       
        }

        private void button7_Click(object sender, EventArgs e)
        {
            index++;
            if (index == nr_intrebari) index = 0;
            listBox1.SetSelected(index, true);

        }

        private void button6_Click(object sender, EventArgs e)
        {
            index--;
            if (index == -1) index = nr_intrebari -1;
            listBox1.SetSelected(index, true);
        }

        private void Test_Load(object sender, EventArgs e)
        {
            t.Interval = 1000; //1s
            t.Elapsed += OnTimeEvent;
        }

        private void OnTimeEvent(object sender, ElapsedEventArgs e)
        {
            Invoke(new Action(() =>
           {
              if(s!=0) s -= 1;
              
                if(s == 0 && m!=0)
               {
                   m --;
                   s = 59;
               }
               textBox4.Text = string.Format("{0}:{1}",m.ToString().PadLeft(2,'0'),s.ToString().PadLeft(2,'0'));
           }));
        }

        private void textBox4_TextChanged(object sender, EventArgs e)
        {
            if (m == 0 && s == 0)
            {
                t.Stop();

                char a = 'N', b = 'N', c = 'N', d = 'N';
                if (button1.BackColor == Color.Green) a = 'D';
                if (button2.BackColor == Color.Green) b = 'D';
                if (button3.BackColor == Color.Green) c = 'D';
                if (button4.BackColor == Color.Green) d = 'D';

                string q = "INSERT INTO Raspuns_Test (id_t,id_i,id_r,corect)  VALUES (" + id_t + "," + id_i + ",'a','" + a + "');";
                DataBaseConnection DB = new DataBaseConnection();
                DB.openConnection();
                conn = DB.getConn();
                MySqlCommand cm = new MySqlCommand(q, conn);
                cm.ExecuteScalar();

                q = "INSERT INTO Raspuns_Test (id_t,id_i,id_r,corect)  VALUES (" + id_t + "," + id_i + ",'b','" + b + "');";
                cm = new MySqlCommand(q, conn);
                cm.ExecuteScalar();

                q = "INSERT INTO Raspuns_Test (id_t,id_i,id_r,corect)  VALUES (" + id_t + "," + id_i + ",'c','" + c + "');";
                cm = new MySqlCommand(q, conn);
                cm.ExecuteScalar();

                q = "INSERT INTO Raspuns_Test (id_t,id_i,id_r,corect)  VALUES (" + id_t + "," + id_i + ",'d','" + d + "');";
                cm = new MySqlCommand(q, conn);
                cm.ExecuteScalar();

                q = "SELECT COUNT(*) FROM Raspuns_Test WHERE id_t = " + id_t + " AND id_i = " + id_i + " AND  (id_i,id_r,corect) IN (SELECT id_i,id_r,corect FROM Raspuns)";
                cm = new MySqlCommand(q, conn);
                MySqlDataReader dr = cm.ExecuteReader();
                dr.Read();

                int x = int.Parse(dr[0].ToString());
                MessageBox.Show(x.ToString() + " " + id_t.ToString() + "  " + id_i.ToString());
                dr.Close();

                if (x == 4)
                {
                    punctaj++;
                    textBox2.Text = punctaj.ToString();
                }
                else
                {
                    gresite++;
                    textBox3.Text = gresite.ToString();
                }

                q = "UPDATE Test SET punctaj = " + punctaj + " WHERE id_t =" + id_t + "";
                cm = new MySqlCommand(q, conn);
                cm.ExecuteScalar();


                DB.closeConnection();


                Client client = new Client();
                client.Show();
                client.id_u = id_u;
                client.user = user;
                MessageBox.Show("Timpul s-a terminat! Punctajul obtinut este:" + punctaj);
                client.Show();
                this.Hide();
            }
        }

        private void Test_FormClosing(object sender, FormClosingEventArgs e)
        {
            Application.Exit();
            t.Stop();
        }
    }
}
