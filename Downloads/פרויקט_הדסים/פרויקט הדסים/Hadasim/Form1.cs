using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.SqlClient;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace WindowsFormsApp1
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        static string connectionString = @"Data Source=(localdb)\MSSQLLocalDB;Initial Catalog=PatientsDB";

        private void dataGridView1_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

        }

        private void textBox1_TextChanged(object sender, EventArgs e)
        {
            //search by ID
        }

        //search by ID
        private void button1_Click(object sender, EventArgs e)
        {
            using (SqlConnection connection = new SqlConnection(connectionString))
            {
                connection.Open();

                string query = "SELECT * FROM Patients_details WHERE patientId = @patientId";
                SqlCommand command = new SqlCommand(query, connection);
                command.Parameters.AddWithValue("@patientId", insertID.Text);
                using (SqlDataReader reader = command.ExecuteReader())
                {
                    while (reader.Read())//using read() method to read all rows one-by-one
                    {
                        Console.WriteLine($"Last Name: {reader["l_name"]}");
                        Console.WriteLine($"First Name: {reader["f_name"]}");
                        Console.WriteLine($"ID: {reader["patientId"]}");
                        Console.WriteLine($"date of birth: {reader["bornDate"]}");
                        Console.WriteLine($"phone: {reader["phone"]}");
                        Console.WriteLine($"mobile phone: {reader["mobilePhone"]}");
                        Console.WriteLine($"city: {reader["city"]}");
                        Console.WriteLine($"street: {reader["street"]}");
                        Console.WriteLine($"num of the apartment: {reader["numApartment"]}");
                    }

                    reader.Close();
                }
            }
        }



        private void label2_Click(object sender, EventArgs e) 
        {
            //vaccinatios details
        }

        private void searchVaccination_TextChanged(object sender, EventArgs e)
        {
            //select vaccination's details about specific ID
        }

        //select vaccination's details
        private void button1_Click_1(object sender, EventArgs e)
        {
            using (SqlConnection connection = new SqlConnection(connectionString))
            {
                connection.Open();
                //שליפת נתוני החיסונים של לקוח מסוים
                string query = "select * from [dbo].[Vaccination] where patientId = @patientId";
                SqlCommand command = new SqlCommand(query, connection);
                command.Parameters.AddWithValue("@patientId", searchVaccination.Text);
                using (SqlDataReader reader = command.ExecuteReader())
                {
                    while (reader.Read())
                    {
                        //Console.WriteLine($"vaccination ID: {reader["vaccination_id"]}");
                        Console.WriteLine($"ID: {reader["patientId"]}");
                        Console.WriteLine($"vaccineDate: {reader["vaccineDate"]}");
                        Console.WriteLine($"vaccineManufacturer: {reader["vaccineManufacturer"]}");
                        Console.WriteLine($"numVaccine: {reader["numVaccine"]}");
                    }
                    reader.Close();
                }
            }
        }

        private void textBox1_TextChanged_1(object sender, EventArgs e)
        {
            //select patients that did specific vaccination
        }

        private void label3_Click(object sender, EventArgs e)
        {
            //select patients that did specific vaccination
        }

        //select all patients that did specific vaccination
        private void button2_Click(object sender, EventArgs e)
        {
            using (SqlConnection connection = new SqlConnection(connectionString))
            {
                connection.Open();
                //שליפת נתוני החיסונים של לקוח מסוים
                string query = "select * from [dbo].[Vaccination] where numVaccine = @numVaccination";
                SqlCommand command = new SqlCommand(query, connection);
                command.Parameters.AddWithValue("@numVaccination", numVaccination.Text);
                using (SqlDataReader reader = command.ExecuteReader())
                {
                    while (reader.Read())
                    {
                        //Console.WriteLine($"vaccination ID: {reader["vaccination_id"]}");
                        Console.WriteLine($"ID: {reader["patientId"]}");
                        Console.WriteLine($"vaccineDate: {reader["vaccineDate"]}");
                        Console.WriteLine($"vaccineManufacturer: {reader["vaccineManufacturer"]}");
                        Console.WriteLine($"numVaccine: {reader["numVaccine"]}");
                    }

                    reader.Close();
                }
            }

        }

        private void textBox1_TextChanged_2(object sender, EventArgs e)
        {
            //ID textBox (for add...)
        }

        //add a patient to DB
        private void button3_Click(object sender, EventArgs e)
        {
            using (SqlConnection connection = new SqlConnection(connectionString))
            {
                connection.Open();

                //הכנסת מבוטח לטבלת המבוטחים
                string query = "insert into dbo.Patients_details (patientId, l_name, f_name, bornDate, phone, mobilePhone, city, street, numApartment) values(@ID, @LastName, @FirstName, @DateOfBirth, @Phone, @MobilePhone ,@City, @Street, @AddressNumber); ";
                SqlCommand command = new SqlCommand(query, connection);
                command.Parameters.AddWithValue("@ID", textBox1.Text);
                command.Parameters.AddWithValue("@LastName", textBox2.Text);
                command.Parameters.AddWithValue("@FirstName", textBox3.Text);
                command.Parameters.AddWithValue("@DateOfBirth", textBox4.Text);
                command.Parameters.AddWithValue("@Phone", textBox5.Text);
                command.Parameters.AddWithValue("@MobilePhone", textBox6.Text);
                command.Parameters.AddWithValue("@City", textBox7.Text);
                command.Parameters.AddWithValue("@Street", textBox8.Text);
                command.Parameters.AddWithValue("@AddressNumber", textBox9.Text);
                MessageBox.Show("Added successfully!");
                try //הסיבה: משום שאם מנסים להכניס מבוטח שכבר קיים נזרקת שגיאה
                {
                    using (SqlDataReader reader = command.ExecuteReader())
                    {
                        while (reader.Read())
                        {
                            int rowsAffected = command.ExecuteNonQuery();
                            Console.WriteLine($"Rows Affected: {rowsAffected}");
                        }

                        reader.Close();
                    }
                }
                catch //בדיקת תקינות- אם הגיע לקטע קוד זה- בנראה שהמבוטח הנ"ל נמצא כבר במאגר
                {
                    MessageBox.Show("ERROR: The patient is already exist!!");
                }
                
            }
        }

        // הוספת חיסונים למבוטח
        private void addVaccination_Click(object sender, EventArgs e)
        {
            using (SqlConnection connection = new SqlConnection(connectionString))
            {
                connection.Open();

                // בדיקת תקינות- האם המבוטח הנ"ל נמצא בטבלת המבוטחים הכללית של קופה"ח
                string checkQuery = "SELECT COUNT(*) FROM Patients_details WHERE patientID = @patientID";
                SqlCommand checkCommand = new SqlCommand(checkQuery, connection);
                checkCommand.Parameters.AddWithValue("@patientID", textBox10.Text);
                int patientCount = (int)checkCommand.ExecuteScalar();
                int flag = 0;
                if (patientCount == 0)
                {
                    MessageBox.Show("ERROR: Patient ID does not exist in the Patient_details table!");
                    flag = 1;
                    return; 
                }

                //שאילתא להכנסת מבוטח לטבלת המבוטחים
                string query = "insert into dbo.Vaccination (patientId, vaccineDate, vaccineManufacturer, numVaccine) values (@patientId, @vaccineDate, @vaccineManufacturer, @numVaccine);";
                SqlCommand command = new SqlCommand(query, connection);
                command.Parameters.AddWithValue("@patientId", textBox10.Text);
                command.Parameters.AddWithValue("@vaccineDate", textBox11.Text);
                command.Parameters.AddWithValue("@vaccineManufacturer", textBox12.Text);
                command.Parameters.AddWithValue("@numVaccine", textBox13.Text);
                //בדיקת תקינות-  לדוגמא
                //לוודא שלא מכניסים מבוטח עם מס' חיסון 3 ללא שעשה את חיסון 2,
                string subQuery = "select max(numVaccine) from [dbo].[Vaccination] where patientId = @patientID";
                SqlCommand subCommand = new SqlCommand(subQuery, connection);
                subCommand.Parameters.AddWithValue("@patientID", textBox10.Text);
                int hiestNumVaccinate = (int)subCommand.ExecuteScalar();//return a single value
                int numVaccinate = int.Parse(textBox13.Text);
                if(numVaccinate -1 != hiestNumVaccinate) // לא הוכנס המס' העוקב
                {
                    MessageBox.Show("The numVaccinate value is ERROR!");
                    flag = 1;
                    return;
                }
                
                if (flag == 0)//מס' החיסון שהוכנס עוקב למס' החיסון האחרון של המבוטח, וכן המבוטח מופיע בטבלת המבוטחים
                {
                    using (SqlDataReader reader = command.ExecuteReader())
                    {
                        while (reader.Read())
                        {
                            int rowsAffected = command.ExecuteNonQuery();
                            Console.WriteLine($"Rows Affected: {rowsAffected}");
                            MessageBox.Show("Added successfully!");
                        }
                        reader.Close();
                    }
                } 
            }
        }
    }
}
