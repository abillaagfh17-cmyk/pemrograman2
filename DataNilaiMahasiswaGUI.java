import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;

class Mahasiswa {
    String nim;
    String nama;
    double nilai;

    Mahasiswa(String nim, String nama, double nilai) {
        this.nim = nim;
        this.nama = nama;
        this.nilai = nilai;
    }

    String getGrade() {
        if (nilai >= 85) return "A";
        else if (nilai >= 70) return "B";
        else if (nilai >= 55) return "C";
        else if (nilai >= 40) return "D";
        else return "E";
    }
}

public class DataNilaiMahasiswaGUI extends JFrame implements ActionListener {
    private JTextField txtNim, txtNama, txtNilai, txtCari;
    private JTextArea txtOutput;
    private JButton btnTambah, btnTampilkan, btnRataRata, btnCari, btnHapus, btnRekap, btnClear;
    private List<Mahasiswa> daftar = new ArrayList<>();

    public DataNilaiMahasiswaGUI() {
        setTitle("Data Nilai Mahasiswa");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel untuk input
        JPanel panelInput = new JPanel(new GridLayout(5, 2, 5, 5));
        panelInput.setBorder(BorderFactory.createTitledBorder("Input Data"));

        panelInput.add(new JLabel("NIM:"));
        txtNim = new JTextField();
        panelInput.add(txtNim);

        panelInput.add(new JLabel("Nama:"));
        txtNama = new JTextField();
        panelInput.add(txtNama);

        panelInput.add(new JLabel("Nilai:"));
        txtNilai = new JTextField();
        panelInput.add(txtNilai);

        panelInput.add(new JLabel("Cari/Hapus NIM:"));
        txtCari = new JTextField();
        panelInput.add(txtCari);

        add(panelInput, BorderLayout.NORTH);

        // Panel untuk tombol
        JPanel panelButton = new JPanel(new GridLayout(2, 4, 5, 5));
        panelButton.setBorder(BorderFactory.createTitledBorder("Aksi"));

        btnTambah = new JButton("Tambah");
        btnTambah.addActionListener(this);
        panelButton.add(btnTambah);

        btnTampilkan = new JButton("Tampilkan Semua");
        btnTampilkan.addActionListener(this);
        panelButton.add(btnTampilkan);

        btnRataRata = new JButton("Rata-Rata");
        btnRataRata.addActionListener(this);
        panelButton.add(btnRataRata);

        btnCari = new JButton("Cari");
        btnCari.addActionListener(this);
        panelButton.add(btnCari);

        btnHapus = new JButton("Hapus");
        btnHapus.addActionListener(this);
        panelButton.add(btnHapus);

        btnRekap = new JButton("Rekap Grade");
        btnRekap.addActionListener(this);
        panelButton.add(btnRekap);

        btnClear = new JButton("Clear Output");
        btnClear.addActionListener(this);
        panelButton.add(btnClear);

        add(panelButton, BorderLayout.CENTER);

        // Text area untuk output
        txtOutput = new JTextArea();
        txtOutput.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtOutput);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Output"));
        add(scrollPane, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnTambah) {
            tambahDataMahasiswa();
        } else if (e.getSource() == btnTampilkan) {
            tampilkanSemuaNilai();
        } else if (e.getSource() == btnRataRata) {
            tampilkanRataRataNilai();
        } else if (e.getSource() == btnCari) {
            cariMahasiswa();
        } else if (e.getSource() == btnHapus) {
            hapusMahasiswa();
        } else if (e.getSource() == btnRekap) {
            rekapGrade();
        } else if (e.getSource() == btnClear) {
            txtOutput.setText("");
        }
    }

    private void tambahDataMahasiswa() {
        try {
            String nim = txtNim.getText().trim();
            String nama = txtNama.getText().trim();
            double nilai = Double.parseDouble(txtNilai.getText().trim());

            if (nim.isEmpty() || nama.isEmpty()) {
                JOptionPane.showMessageDialog(this, "NIM dan Nama tidak boleh kosong!");
                return;
            }

            daftar.add(new Mahasiswa(nim, nama, nilai));
            txtOutput.append("Data mahasiswa berhasil ditambahkan.\n");
            txtNim.setText("");
            txtNama.setText("");
            txtNilai.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Nilai harus berupa angka!");
        }
    }

    private void tampilkanSemuaNilai() {
        if (daftar.isEmpty()) {
            txtOutput.append("Belum ada data mahasiswa.\n");
            return;
        }
        txtOutput.append("\nDaftar Mahasiswa dan Nilai:\n");
        for (Mahasiswa m : daftar) {
            txtOutput.append(String.format("%s - %s - Nilai: %.2f - Grade: %s\n", m.nim, m.nama, m.nilai, m.getGrade()));
        }
    }

    private void tampilkanRataRataNilai() {
        if (daftar.isEmpty()) {
            txtOutput.append("Belum ada data mahasiswa.\n");
            return;
        }
        double total = 0;
        for (Mahasiswa m : daftar) {
            total += m.nilai;
        }
        double rata = total / daftar.size();
        txtOutput.append(String.format("Rata-rata nilai dalam kelas: %.2f\n", rata));
    }

    private void cariMahasiswa() {
        String nimCari = txtCari.getText().trim();
        if (nimCari.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Masukkan NIM yang dicari!");
            return;
        }
        for (Mahasiswa m : daftar) {
            if (m.nim.equalsIgnoreCase(nimCari)) {
                txtOutput.append(String.format("Ditemukan: %s - %s - Nilai: %.2f - Grade: %s\n", m.nim, m.nama, m.nilai, m.getGrade()));
                return;
            }
        }
        txtOutput.append("Mahasiswa dengan NIM tersebut tidak ditemukan.\n");
    }

    private void hapusMahasiswa() {
        String nimHapus = txtCari.getText().trim();
        if (nimHapus.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Masukkan NIM yang akan dihapus!");
            return;
        }
        Iterator<Mahasiswa> iterator = daftar.iterator();
        while (iterator.hasNext()) {
            Mahasiswa m = iterator.next();
            if (m.nim.equalsIgnoreCase(nimHapus)) {
                iterator.remove();
                txtOutput.append("Data mahasiswa berhasil dihapus.\n");
                txtCari.setText("");
                return;
            }
        }
        txtOutput.append("Mahasiswa dengan NIM tersebut tidak ditemukan.\n");
    }

    private void rekapGrade() {
        if (daftar.isEmpty()) {
            txtOutput.append("Belum ada data mahasiswa.\n");
            return;
        }
        Map<String, Integer> rekap = new LinkedHashMap<>();
        for (Mahasiswa m : daftar) {
            rekap.put(m.getGrade(), rekap.getOrDefault(m.getGrade(), 0) + 1);
        }
        txtOutput.append("\nRekap Grade:\n");
        rekap.forEach((grade, jumlah) -> txtOutput.append("Grade " + grade + ": " + jumlah + " mahasiswa\n"));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DataNilaiMahasiswaGUI());
    }
}