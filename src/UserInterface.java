import java.sql.SQLOutput;
import java.util.Locale;
import java.util.Scanner;

public class UserInterface {


    public static void tampilkanmenu() {
        System.out.println("===============");
        System.out.println("| PILIH MENU : |");
        System.out.println("===============");
        System.out.println(" [C] : Create |");
        System.out.println(" [R] : Read   |");
        System.out.println(" [U] : update |");
        System.out.println(" [D] : Delete |");
        System.out.println(" [X] : Exit   |");
        System.out.println("===============");
    }


    public static void main(String[] args) {
        database db = new database();
        System.out.println("SELAMAT DATANG DI APLIKASI CRUD TEXT DATABASE");
        while (true){
            tampilkanmenu();
            Scanner sc = new Scanner(System.in);
            System.out.println("Pilih :");
            String pilihan = sc.nextLine();

            pilihan = pilihan.toUpperCase();
            switch (pilihan){
                case "C":
                    System.out.println("ANDA MEMILIH MENU CREATE");
                    System.out.println("----------------------------");
                    System.out.println("INPUT DATA BARU");
                    System.out.print("NIM      :");
                    String nim = sc.nextLine();
                    System.out.print("NAMA     :");
                    String nama = sc.nextLine();
                    System.out.print("ALAMAT   :");
                    String alamat = sc.nextLine();
                    System.out.print("SEMESTER :");
                    int semester = sc.nextInt();
                    System.out.print("SKS       :");
                    int sks = sc.nextInt();
                    System.out.print("IPK     :");
                    Double ipk = sc.nextDouble();
                    sc.nextLine();
                    System.out.println("------------------------------");
                    boolean status = db.insert(nim, nama, alamat, semester,sks, ipk);

                    if (status == true) {
                        System.out.println("DATA BARU BERHASIL DITAMBAHKAN");
                    }
                    else{
                        System.out.println("NIM "+nim +" SUDAH ADA DI DATABASE");
                        System.out.println("GAGAL MENAMBHAN DATA BARU");

                    }
                    System.out.println("------------------------------------");
                    break;
                case "R":
                    System.out.println("ANDA MEMILIH MENU READ");
                    db.view();
                    break;
                case "U":
                    System.out.println("INFO : ANDA MEMILIH MENU UPDATE");
                    db.view();
                    System.out.println("INPUT KEY (NIM MAHASISWA YANG AKAN DIUPDATE):");
                    String key = sc.next();
                    int index = db.search(key);

                    if (index >= 0){
                        System.out.println("ANDA AKAN UPDATE DATA " + db.getData().get(index));
                        System.out.println("----------------------------------");
                        System.out.println("INPUT DATA BARU");
                        System.out.print("NIM      :");
                        nim = sc.next();
                        System.out.print("NAMA     :");
                        nama = sc.next();
                        System.out.print("ALAMAT   :");
                        alamat = sc.next();
                        System.out.print("SEMESTER :");
                        semester = sc.nextInt();
                        System.out.print("SKS      :");
                        sks = sc.nextInt();
                        System.out.print("IPK      :");
                        ipk = sc.nextDouble();
                        sc.nextLine();
                        System.out.println("----------------------------------");
                        status = db.update(index,nim,nama,alamat,semester,sks,ipk);

                        if (status == true){
                            System.out.println("DATA BERHASIL DI PERBAHARUI");

                        }else{
                            System.err.println("GAGAL MEMPERBAHARUI DATA");
                        }
                        System.out.println("------------------------------------");

                    }else{
                        System.err.println("MAHASISWA DENGAN NIM :" + key + "TIDAK ADA DI DATABASE");
                    }
                    break;
                case "D":
                    System.out.println("INFO : ANDA MEMILIH MENU DELETE");
                    db.view();
                    System.out.println("Input key (Nim Mahasiswa yang akan dihapus : ");
                    key = sc.next();
                    index = db.search(key);
                    if (index >= 0){
                        System.out.println("APAKAH ANDA YAKIN ANDA AKAN MENGHAPUS DATA "+db.getData().get(index)+"? Y/N");
                        System.out.print("Pilih : ");
                        pilihan = sc.next();
                        if(pilihan.equalsIgnoreCase("Y")){
                            status = db.delete(index);
                            if(status==true){
                                System.out.println("DATA BERHASIL DIHAPUS");

                            }else {
                                System.err.println("GAGAL MENGHAPUS DATA");
                            }
                        }

                    }else {
                        System.err.println("Mahasiswa dengan NIM: "+key+" tidak ada di DataBase");
                    }
                    break;

                case "X":
                    System.out.println("INFO : ANDA MEMILIH MENU EXIT");
                    System.out.println("APAKAH ANDA YAKIN KELUAR DARI APLIKASI INI");
                    System.out.print("PILIH :");
                    pilihan = sc.next();
                    if (pilihan.equalsIgnoreCase("Y")){
                        System.exit(0);
                    }
                    break;
            }
        }
    }
}