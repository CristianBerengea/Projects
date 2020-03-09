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
    public partial class Autentificare : Form
    {
        MySqlConnection conn;
        public Autentificare()
        {
            InitializeComponent();
        }


        private void button1_Click(object sender, EventArgs e)
        {
            string utilizator = textBox1.Text;
            string parola = textBox2.Text;
            if (utilizator != "" && parola != "")
            {
                string q = "SELECT * FROM utilizatori WHERE Utilizator='" + utilizator + "'";
                DataBaseConnection DB = new DataBaseConnection();
                DB.openConnection();
                conn = DB.getConn();
                MySqlCommand c = new MySqlCommand(q,conn);
                MySqlDataReader dr = c.ExecuteReader();
                if (dr.HasRows)
                {
                    q = "SELECT * FROM utilizatori WHERE Utilizator='" + utilizator + "' AND Parola='" + parola + "'";
                    c = new MySqlCommand(q, conn);
                    dr.Close();
                    dr = c.ExecuteReader();
                    if (dr.HasRows)
                    {
                        dr.Read();
                        string tip = dr[1].ToString();

                         
                        if (tip == "client")
                        {
                            Client client = new Client();
                            client.user = dr[2].ToString();
                            client.id_u =int.Parse(dr[0].ToString());
                            client.Show();
                            this.Visible = false;
                        }
                        else if (tip == "administrator")
                        {
                            Administrator administrator = new Administrator();
                            administrator.user = dr[2].ToString();
                            administrator.id_u = int.Parse(dr[0].ToString());
                            administrator.Show();
                            this.Visible = false;
                        }


                    }
                    else MessageBox.Show("Parola este gresita!");
                }
                else MessageBox.Show("Utilizator inexistent!");
                DB.closeConnection();
            }
            else MessageBox.Show("Nu ați completat datele!");
        }

        private void button2_Click(object sender, EventArgs e)
        {
            ContNou cont_nou = new ContNou();
            cont_nou.Show();
            this.Hide();
        }

        public void showF()
        {
            this.Hide();
        }

        private void Autentificare_FormClosing(object sender, FormClosingEventArgs e)
        {
            Application.Exit();
        }
    }
}
