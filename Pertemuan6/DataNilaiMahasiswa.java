import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

class Mahasiswa {
    private String nama;
    private String nim;
    private String kelas;
    private double nilai1;
    private double nilai2;
    private double rataRata;

    public Mahasiswa(String nama, String nim, String kelas, double nilai1, double nilai2) {
        this.nama = nama;
        this.nim = nim;
        this.kelas = kelas;
        this.nilai1 = nilai1;
        this.nilai2 = nilai2;
        this.rataRata = (nilai1 + nilai2) / 2;
    }

    public String getNama() { return nama; }
    public String getNim() { return nim; }
    public String getKelas() { return kelas; }
    public double getNilai1() { return nilai1; }
    public double getNilai2() { return nilai2; }
    public double getRataRata() { return rataRata; }

    @Override
    public String toString() {
        return nama + " (" + nim + ") - Rata-rata: " + rataRata;
    }
}

public class DataNilaiMahasiswa extends JFrame {
    private ArrayList<Mahasiswa> daftarMahasiswa = new ArrayList<>();
    private JTextField tfNama, tfNim, tfKelas, tfNilai1, tfNilai2, tfCariNim;
    private DefaultListModel<String> listModel;
    private JList<String> listMahasiswa;
    private JButton btnTambah, btnCari, btnHapus, btnTampilkan;

    public DataNilaiMahasiswa() {
        setTitle("Data Nilai Mahasiswa");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel untuk input
        JPanel panelInput = new JPanel();
        panelInput.setLayout(new GridLayout(7, 2));

        panelInput.add(new JLabel("Nama:"));
        tfNama = new JTextField();
        panelInput.add(tfNama);

        panelInput.add(new JLabel("NIM:"));
        tfNim = new JTextField();
        panelInput.add(tfNim);

        panelInput.add(new JLabel("Kelas:"));
        tfKelas = new JTextField();
        panelInput.add(tfKelas);

        panelInput.add(new JLabel("Nilai 1:"));
        tfNilai1 = new JTextField();
        panelInput.add(tfNilai1);

        panelInput.add(new JLabel("Nilai 2:"));
        tfNilai2 = new JTextField();
        panelInput.add(tfNilai2);

        panelInput.add(new JLabel("Cari/Hapus NIM:"));
        tfCariNim = new JTextField();
        panelInput.add(tfCariNim);

        add(panelInput, BorderLayout.NORTH);

        // Panel untuk tombol
        JPanel panelButton = new JPanel();
        btnTambah = new JButton("Tambah");
        btnCari = new JButton("Cari");
        btnHapus = new JButton("Hapus");
        btnTampilkan = new JButton("Tampilkan Semua");

        panelButton.add(btnTambah);
        panelButton.add(btnCari);
        panelButton.add(btnHapus);
        panelButton.add(btnTampilkan);

        add(panelButton, BorderLayout.CENTER);

        // List untuk menampilkan mahasiswa
        listModel = new DefaultListModel<>();
        listMahasiswa = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(listMahasiswa);
        add(scrollPane, BorderLayout.SOUTH);

        // Event listeners
        btnTambah.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tambahMahasiswa();
            }
        });

        btnCari.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cariMahasiswa();
            }
        });

        btnHapus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hapusMahasiswa();
            }
        });

        btnTampilkan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tampilkanSemua();
            }
        });
    }

    private void tambahMahasiswa() {
        try {
            String nama = tfNama.getText();
            String nim = tfNim.getText();
            String kelas = tfKelas.getText();
            double nilai1 = Double.parseDouble(tfNilai1.getText());
            double nilai2 = Double.parseDouble(tfNilai2.getText());

            Mahasiswa mhs = new Mahasiswa(nama, nim, kelas, nilai1, nilai2);
            daftarMahasiswa.add(mhs);
            JOptionPane.showMessageDialog(this, "Mahasiswa ditambahkan.");

            // Clear fields
            tfNama.setText("");
            tfNim.setText("");
            tfKelas.setText("");
            tfNilai1.setText("");
            tfNilai2.setText("");

            // Update list
            updateList();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error: Nilai harus angka.");
        }
    }

    private void cariMahasiswa() {
        String cariNim = tfCariNim.getText();
        boolean found = false;
        for (Mahasiswa m : daftarMahasiswa) {
            if (m.getNim().equals(cariNim)) {
                JOptionPane.showMessageDialog(this, "Ditemukan:\nNama: " + m.getNama() + "\nNIM: " + m.getNim() + "\nKelas: " + m.getKelas() + "\nNilai 1: " + m.getNilai1() + "\nNilai 2: " + m.getNilai2() + "\nRata-rata: " + m.getRataRata());
                found = true;
                break;
            }
        }
        if (!found) {
            JOptionPane.showMessageDialog(this, "Mahasiswa tidak ditemukan.");
        }
    }

    private void hapusMahasiswa() {
        String hapusNim = tfCariNim.getText();
        boolean removed = false;
        for (int i = 0; i < daftarMahasiswa.size(); i++) {
            if (daftarMahasiswa.get(i).getNim().equals(hapusNim)) {
                daftarMahasiswa.remove(i);
                JOptionPane.showMessageDialog(this, "Mahasiswa dihapus.");
                removed = true;
                break;
            }
        }
        if (!removed) {
            JOptionPane.showMessageDialog(this, "Mahasiswa tidak ditemukan.");
        }
        // Update list
        updateList();
    }

    private void tampilkanSemua() {
        updateList();
    }

    private void updateList() {
        listModel.clear();
        for (Mahasiswa m : daftarMahasiswa) {
            listModel.addElement(m.toString());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new DataNilaiMahasiswa().setVisible(true);
            }
        });
    }
}
