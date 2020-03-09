using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using MySql.Data.MySqlClient;

namespace Chestionare_Auto
{
    class DataBaseConnection
    {
        MySqlConnection conn = new MySqlConnection("server=localhost;user id=root;database=chestionare_auto;persistsecurityinfo=True;sshport=3307;port=3307;Password=123qweasdZXC*");

        public void openConnection()
        {
            conn.Open();
        }

        public void closeConnection()
        {
            conn.Close();
        }

        public MySqlConnection getConn()
        {
            return conn;
        }

    }
}
