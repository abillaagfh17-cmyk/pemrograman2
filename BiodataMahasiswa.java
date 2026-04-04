import javax.swing.JOptionPane;

public class BiodataMahasiswa {
    public static void main(String[] args) {
        
        String nama = "Nama: Afghan Abilla Aghfirlah";
        String nim = "NIM: 231011403133";
        String jurusan = "Jurusan: Teknik Informatika";
        String alamat = "Alamat: Jakarta, Indonesia";
        String email = "Email: abillaagfh17@gmail.com";

        
        String biodata = String.format("%-8s: %s\n", "Nama", "Afghan Abilla Aghfirlah") +
                         String.format("%-8s: %s\n", "NIM", "231011403133") +
                         String.format("%-8s: %s\n", "Jurusan", "Teknik Informatika") +
                         String.format("%-8s: %s\n", "Alamat", "Jakarta, Indonesia") +
                         String.format("%-8s: %s", "Email", "abillaagfh17@gmail.com");

        
        JOptionPane.showMessageDialog(null, "<html><pre style='font-family: monospace; font-size: 12pt;'>" + biodata + "</pre></html>", "Biodata Mahasiswa", JOptionPane.INFORMATION_MESSAGE);
    }
}