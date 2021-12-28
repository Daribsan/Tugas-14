import java.sql.*;
import com.mysql.cj.protocol.Resultset;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TerimaGaji extends Gaji
{
    Integer totalGaji, jmlAbsen; 
    static Connection conn;
	String link = "jdbc:mysql://localhost:3306/karyawan";  
    Scanner input = new Scanner(System.in);

    @Override
    public void Potongan()
    {
        this.jmlAbsen = 30-jmlHadir;
        this.potongan = jmlAbsen*200000;
    }

    @Override
    public void TotalGaji()
    {
        this.totalGaji = this.gajiPokok - this.potongan;
    }


    @Override
    public void save() throws SQLException 
    {
        try 
        {
            System.out.println("Masukkan data karyawan");
            NamaPegawai();
            NoPegawai();
            Jabatan();
            GajiPokok();
            JumlahHariMasuk();
            Potongan();
            TotalGaji();
    
            String sql = "INSERT INTO tugas (Nama, Nomor, Jabatan, JumlHadir, TotalGaji) VALUES ('"+nama+"','"+nomor+"','"+jabatan+"','"+jmlHadir+"','"+totalGaji+"')";
            conn = DriverManager.getConnection(link,"root","");
            Statement statement = conn.createStatement();
            statement.execute(sql);
            System.out.println("Berhasil input data!!");
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            //System.out.println("Error masukkan nomor jabatan dengan benar");
            System.err.println("Error masukkan nomor jabatan dengan benar");
        }

        catch (IndexOutOfBoundsException e)
        {
            //System.out.println("Masukkan rentang jumlah hadir 1-30");
            System.err.println("Masukkan rentang jumlah hadir 1-30");
        }

        catch (InputMismatchException e)
        {
            System.err.println("Input pilihan dengan angka saja");
        }
        
    }

    @Override
    public void delete() throws SQLException
    {
        view();
        try
        {
            System.out.println("Hapus data karyawan");
            System.out.print("Masukkan nomor pegawai yang akan dihapus : ");
            String noPegawai = input.nextLine();

            String sql = "DELETE FROM tugas WHERE nomor = "+ noPegawai;

            conn = DriverManager.getConnection(link,"root","");
	        Statement statement = conn.createStatement();

            if(statement.executeUpdate(sql) > 0)
            {
	            System.out.println("Berhasil menghapus data pegawai (Nomor "+noPegawai+")");
	        }
        }

        catch(SQLException e)
        {
	        System.out.println("Terjadi kesalahan dalam menghapus data");
	    }
        catch(Exception e)
        {
            System.out.println("masukan data yang benar");
        }
    }

    @Override
    public void update() throws SQLException
    {
        view();
        try
        {
            System.out.print("Masukkan nomor pegawai hendak diubah: ");
            String noPegawai = input.nextLine();

            String sql = "SELECT * FROM tugas WHERE nomor = " +noPegawai;
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            if (result.next())
            {
                System.out.print("Nama baru ["+result.getString("nama")+"]\t: ");
                String namaPegawai = input.nextLine();

                sql = "UPDATE tugas SET nama='"+namaPegawai+"' WHERE nomor='"+noPegawai+"'";

                if(statement.executeUpdate(sql) > 0)
                {
                    System.out.println("Berhasil memperbaharui data pegawai (Nomor "+noPegawai+")");
                }
            }
            statement.close();
        }

            catch (SQLException e) 
            {
                System.err.println("Terjadi kesalahan dalam mengedit data");
                System.err.println(e.getMessage());
            }
        
    }
        
    

    @Override
    public void search() throws SQLException
    {
        System.out.print("Masukkan Nama Pegawai yang ingin dilihat : ");    
		String keyword = input.nextLine();
		
		String sql = "SELECT * FROM tugas WHERE nama LIKE '%"+keyword+"%'";
		conn = DriverManager.getConnection(link,"root","");
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql); 

        while (result.next())
        {
            System.out.print("\nNo pegawai\t\t: ");
            System.out.print(result.getString("Nomor"));
            System.out.print("\nNama pegawai\t\t: ");
            System.out.print(result.getString("Nama"));
            System.out.print("\nJabatan\t\t\t: ");
            System.out.print(result.getString("Jabatan"));
            System.out.print("\nKehadiran\t\t: ");
            System.out.print(result.getInt("JumlHadir"));
            System.out.print("\nTotal gaji\t\t: ");
            System.out.println(result.getInt("TotalGaji"));
        }
        
    }

    public void Tampilkan()
    {
        System.out.println("\nInformasi Penerimaan Gaji");
        System.out.println("Nama pegawai    : "+this.nama.toUpperCase()); //method toUpperCase()
        System.out.println("Karakter nama   : "+this.nama.length()+" karakter"); //method lenght()
        System.out.println("Nomor Pegawai   : "+this.nomor);
        System.out.print("Jabatan         : "+this.jabatan);
        //PrintJabatan();
        System.out.println("Jumlah kehadiran: "+this.jmlHadir+" hari");
        System.out.println("Potongan gaji   : Rp"+this.potongan);
        System.out.println("Total gaji      : Rp"+this.totalGaji+"\n");
    }
}