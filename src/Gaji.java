import java.util.ArrayList;
import java.util.Scanner;
import java.sql.*;
import com.mysql.cj.protocol.Resultset;

public class Gaji implements PTABC, Database
{
    static Connection conn;
	String link = "jdbc:mysql://localhost:3306/karyawan";

    Integer potongan, gajiPokok, jmlHadir, showJabatan;
    String nama, nomor, jabatan;
    Integer [] listJabatan = {1,2,3,4,5};
    Integer cekInput;

    Scanner input = new Scanner(System.in);

    @Override
    public void NoPegawai() 
    {
        System.out.print("Masukkan nomor : ");
        this.nomor = input.nextLine();
    }

    @Override
    public void NamaPegawai()
    {
        System.out.print("Masukkan nama : ");
        this.nama = input.nextLine();
    }

    @Override
    public void Jabatan() 
    {
        System.out.println("Pilihan : \n1.Kepala Manager\n2.Wakil Manager \n3.Sekretaris\n4.Anggota\n5.Satpam");
        System.out.print("Masukkan angka saja : ");
        this.cekInput = input.nextInt();

        if (cekInput.equals(listJabatan[0])) //method equals()
        {
            this.showJabatan = 1;
            this.jabatan = "Kepala Manager";
        }
        else if (cekInput.equals(listJabatan[1])) //method equals()
        {
            this.showJabatan = 2;
            this.jabatan = "Wakil Manager";
        }
        else if (cekInput.equals(listJabatan[2])) //method equals()
        {
            this.showJabatan = 3;
            this.jabatan = "Sekretaris";
        }
        else if (cekInput.equals(listJabatan[3])) //method equals()
        {
            this.showJabatan = 4;
            this.jabatan = "Anggota";
        }
        else if (cekInput.equals(listJabatan[4])) //method equals()
        {
            this.showJabatan = 5;
            this.jabatan = "Satpam";
        }
        else 
        {
            listJabatan[5] = 0;
        }
    }

    @Override
    public void GajiPokok() 
    {
        System.out.println();
        if (this.showJabatan == 1)
        {
            this.gajiPokok = 15000000;
        }
        else if (this.showJabatan == 2)
        {
            this.gajiPokok = 12000000;
        }
        else if (this.showJabatan == 3)
        {
            this.gajiPokok = 10000000;
        }
        else if (this.showJabatan == 4)
        {
            this.gajiPokok = 8000000;
        }
        else if (this.showJabatan == 5)
        {
            this.gajiPokok = 5000000;
        }
    }

    @Override
    public void JumlahHariMasuk() 
    {
        ArrayList<Integer> hari = new ArrayList<Integer>();
        for (int i=0; i<30; i++)
        {
            hari.add(i+1);
        }

        System.out.print("Masukkan jumlah hadir : ");
        this.jmlHadir = input.nextInt();
        this.jmlHadir = hari.get(jmlHadir-1);
    }

    
    public void Potongan() 
    {
        
    }

    
    public void TotalGaji() 
    {
        
    }

    @Override
    public void view()throws SQLException 
    {
        System.out.println("Daftar seluruh pegawai");
        String sql = "SELECT * FROM tugas";
        conn = DriverManager.getConnection(link,"root","");
        Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery(sql);
        
        while (result.next())
        {
            System.out.print("\nNama pegawai\t\t: ");
            System.out.print(result.getString("Nama"));
            System.out.print("\nNo pegawai\t\t: ");
            System.out.print(result.getString("Nomor"));
            System.out.print("\nJabatan\t\t\t: ");
            System.out.print(result.getString("Jabatan"));
            System.out.print("\nKehadiran\t\t: ");
            System.out.print(result.getInt("JumlHadir"));
            System.out.print("\nTotal gaji\t\t: ");
            System.out.println(result.getInt("TotalGaji"));
        }
    }

    public void update() throws SQLException 
    {
        
    }

    public void delete() throws SQLException
    {
        
    }

    
    public void save() throws SQLException
    {
        
    }

    public void search() throws SQLException
    {
        
    }

}