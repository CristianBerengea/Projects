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
    public partial class ContNou : Form
    {
        MySqlConnection conn;
        public ContNou()
        {
            InitializeComponent();
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
                    if(parola==cparola)
                    {
                        q = "INSERT INTO utilizatori VALUES (null,'client','"+utilizator +"','"+parola+"')";
                        c = new MySqlCommand(q, conn);
                        dr.Close();
                        c.ExecuteScalar();
                        this.Hide();

                        MessageBox.Show("Contul a fost creat!");
                        Autentificare autentificare = new Autentificare();
                        autentificare.Show();
                    }
                    else MessageBox.Show("Parolele nu coincid!");
                }
                else MessageBox.Show("Nume utilizator existent!");
                DB.closeConnection();
            }
            else MessageBox.Show("Nu ați completat datele!");
        }

        private void button2_Click(object sender, EventArgs e)
        {
            this.Hide();
            Autentificare autentificare = new Autentificare();
            autentificare.Show();
        }

        private void ContNou_FormClosing(object sender, FormClosingEventArgs e)
        {
            Application.Exit();
        }
    }
}
