import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class database {
    private ArrayList<mahasiswa> data = new ArrayList<>();
    private String filename = "src/data.csv";
    private Path path = Path.of(filename);

    public database() {
        open();

    }

    public ArrayList<mahasiswa> getData() {
        return data;
    }

    public void open(){
        try {
            List<String> lines = Files.readAllLines(path);
            data = new ArrayList<>();
            for (int i = 1; i < lines.size(); i++) {
                String Line = lines.get(i);
                String[] Element = Line.split(",");
                String nim = Element[0];
                String nama = Element[1];
                String alamat = Element[2];
                int semester = Integer.parseInt(Element[3]);
                int sks = Integer.parseInt(Element[4]);
                double ipk = Double.parseDouble(Element[5]);
                mahasiswa mhs = new mahasiswa(nim, nama, alamat, semester, sks, ipk);
                data.add(mhs);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);

        }

    }
    public void save() {
        StringBuilder sb = new StringBuilder();
        sb.append("NIM, NAMA, ALAMAT, SEMESTER, SKS, IPK");
        if (!data.isEmpty()) {
            for (int i = 0; i < data.size(); i++) {
                mahasiswa mhs = data.get(i);
                String Line = mhs.getNim() + "," + mhs.getNama() + "," + mhs.getAlamat() + "," + mhs.getSemester() + "," + mhs.getSks()+ "," + mhs.getIpk() + "\n";
                sb.append(Line);
            }
        }
        try {
            Files.writeString(path,sb.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);

        }
    }
    public void view(){
        System.out.println("===================================================================================");
        System.out.printf("|%-8.8s |", "NIM");
        System.out.printf("%-20.20s |", "NAMA");
        System.out.printf("%-20.20s|", "ALAMAT");
        System.out.printf("%8.8s |", "SEMESTER");
        System.out.printf("%3.3s |", "SKS");
        System.out.printf("%4.4s |%n", "IPK");
        System.out.println("-----------------------------------------------------------------------------------");

        for (mahasiswa mhs : data){
            System.out.printf("|%-8.8s |", mhs.getNim());
            System.out.printf("%-20.20s |", mhs.getNama());
            System.out.printf("%-20.20s|", mhs.getAlamat());
            System.out.printf("%8.8s |", mhs.getSemester());
            System.out.printf("%3.3s |", mhs.getSks());
            System.out.printf("%4.4s |%n", mhs.getIpk());
            System.out.println();
        }
        System.out.println("----------------------------------------------------------------------------------");

    }
    boolean insert (String nim, String nama, String alamat, int semester, int sks, double ipk){
        // return false;
        boolean status = true;

        //cek primary key
        if (!data.isEmpty()){
            for (int i = 0; i < data.size(); i++) {
                if (data.get(i).getNim().equalsIgnoreCase(nim)){
                    status = false;
                    break;
                }

            }
        }
        if (status == true){
            mahasiswa mhs = new mahasiswa(nim, nama, alamat, semester, sks, ipk);
            data.add(mhs);
            save();
        }
        return status;
    }
    public int search (String nim){
        int index = -1;
        if (!data.isEmpty()){
            for (int i = 0; i < data.size(); i++){
                if (data.get(i).getNim().equalsIgnoreCase(nim)){
                    index = i;
                    break;
                }

            }
        }
        return index;
    }
    public boolean update(int index, String nim, String nama, String alamat, int semester, int sks, double ipk){
        boolean status = false;
        if (!data.isEmpty()){
            if (index >= 0 && index < data.size()){
                mahasiswa mhs = new mahasiswa(nim, nama, alamat, semester, sks, ipk);
                data.set(index, mhs);
                save();
                status = true;
            }
        }
        return status;
    }
    public boolean delete(int index){
        boolean status = false;
        if (!data.isEmpty()){
            data.remove(index);
            save();
            status = true;
        }
        return status;

    }
}