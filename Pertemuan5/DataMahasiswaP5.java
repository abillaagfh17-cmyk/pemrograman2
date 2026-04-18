import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class DataMahasiswaP5 extends JFrame {
    private String nim;
    private String nama;
    private String kelas;
    private double nilai;

    private JTextField nimField;
    private JTextField namaField;
    private JTextField kelasField;
    private JTextField nilaiField;
    private DefaultTableModel tableModel;
    private JTable table;
    private final List<DataMahasiswaP5> daftarMahasiswa = new ArrayList<>();

    public DataMahasiswaP5() {
        initComponents();
    }

    public DataMahasiswaP5(String nim, String nama, String kelas, double nilai) {
        this.nim = nim;
        this.nama = nama;
        this.kelas = kelas;
        this.nilai = nilai;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public double getNilai() {
        return nilai;
    }

    public void setNilai(double nilai) {
        this.nilai = nilai;
    }

    private void initComponents() {
        setTitle("Sistem Data Mahasiswa");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(15, 15));
        getContentPane().setBackground(new Color(240, 247, 255));

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(30, 90, 150));
        headerPanel.setPreferredSize(new Dimension(750, 60));
        JLabel headerLabel = new JLabel("📋 MANAJEMEN DATA MAHASISWA");
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);

        // Input Panel
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBackground(new Color(255, 255, 255));
        inputPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(30, 90, 150), 2),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.anchor = GridBagConstraints.WEST;

        Font labelFont = new Font("Segoe UI", Font.BOLD, 12);
        Color labelColor = new Color(30, 90, 150);

        JLabel nimLabel = new JLabel("NIM:");
        nimLabel.setFont(labelFont);
        nimLabel.setForeground(labelColor);
        JLabel namaLabel = new JLabel("Nama:");
        namaLabel.setFont(labelFont);
        namaLabel.setForeground(labelColor);
        JLabel kelasLabel = new JLabel("Kelas:");
        kelasLabel.setFont(labelFont);
        kelasLabel.setForeground(labelColor);
        JLabel nilaiLabel = new JLabel("Nilai:");
        nilaiLabel.setFont(labelFont);
        nilaiLabel.setForeground(labelColor);

        nimField = createStyledTextField();
        namaField = createStyledTextField();
        kelasField = createStyledTextField();
        nilaiField = createStyledTextField();

        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(nimLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(nimField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(namaLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(namaField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(kelasLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(kelasField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        inputPanel.add(nilaiLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(nilaiField, gbc);

        JButton addButton = createStyledButton("➕ Input", new Color(46, 165, 67));
        JButton deleteButton = createStyledButton("🗑️ Hapus", new Color(220, 53, 69));
        JButton showButton = createStyledButton("👁️ Tampilkan", new Color(23, 162, 184));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.setBackground(new Color(255, 255, 255));
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(showButton);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(15, 10, 10, 10);
        inputPanel.add(buttonPanel, gbc);

        String[] kolom = {"NIM", "Nama", "Kelas", "Nilai"};
        tableModel = new DefaultTableModel(kolom, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        table.setRowHeight(25);
        table.setSelectionBackground(new Color(30, 90, 150));
        table.setSelectionForeground(Color.WHITE);
        table.getTableHeader().setBackground(new Color(30, 90, 150));
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(30, 90, 150), 2),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        scrollPane.getViewport().setBackground(Color.WHITE);

        add(headerPanel, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tambahMahasiswa();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hapusMahasiswa();
            }
        });

        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tampilkanData();
            }
        });
    }

    private JTextField createStyledTextField() {
        JTextField textField = new JTextField(15);
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        textField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        textField.setBackground(new Color(250, 250, 250));
        return textField;
    }

    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 11));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setOpaque(true);
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });
        return button;
    }

    private void tambahMahasiswa() {
        String nim = nimField.getText().trim();
        String nama = namaField.getText().trim();
        String kelas = kelasField.getText().trim();
        String nilaiText = nilaiField.getText().trim();

        if (nim.isEmpty() || nama.isEmpty() || kelas.isEmpty() || nilaiText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua field harus diisi.", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        double nilai;
        try {
            nilai = Double.parseDouble(nilaiText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Nilai harus berupa angka.", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        }

        DataMahasiswaP5 mahasiswa = new DataMahasiswaP5(nim, nama, kelas, nilai);
        daftarMahasiswa.add(mahasiswa);
        tableModel.addRow(new Object[]{nim, nama, kelas, nilai});
        clearInputs();
    }

    private void hapusMahasiswa() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Pilih data mahasiswa yang ingin dihapus.", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        daftarMahasiswa.remove(selectedRow);
        tableModel.removeRow(selectedRow);
    }

    private void tampilkanData() {
        refreshTable();
        if (daftarMahasiswa.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tidak ada data mahasiswa.", "Informasi", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        for (DataMahasiswaP5 m : daftarMahasiswa) {
            tableModel.addRow(new Object[]{m.getNim(), m.getNama(), m.getKelas(), m.getNilai()});
        }
    }

    private void clearInputs() {
        nimField.setText("");
        namaField.setText("");
        kelasField.setText("");
        nilaiField.setText("");
        nimField.requestFocus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DataMahasiswaP5().setVisible(true);
            }
        });
    }
}