import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PraktekGUI extends JFrame {

    JLabel lbl1, lbl2, lbl3;
    JTextField txt1, txt2, txtHasil;
    JButton btnTambah, btnHapus, btnExit;

    public PraktekGUI() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("Praktek GUI Pemrograman II");
        setSize(450, 300);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(240, 248, 255)); // Alice Blue background

        // Label
        lbl1 = new JLabel("Angka Pertama");
        lbl1.setBounds(30, 30, 120, 25);
        lbl1.setFont(new Font("Arial", Font.BOLD, 14));
        lbl1.setForeground(new Color(0, 102, 204));
        add(lbl1);

        lbl2 = new JLabel("Angka Kedua");
        lbl2.setBounds(30, 70, 120, 25);
        lbl2.setFont(new Font("Arial", Font.BOLD, 14));
        lbl2.setForeground(new Color(0, 102, 204));
        add(lbl2);

        lbl3 = new JLabel("Hasil");
        lbl3.setBounds(30, 110, 120, 25);
        lbl3.setFont(new Font("Arial", Font.BOLD, 14));
        lbl3.setForeground(new Color(0, 102, 204));
        add(lbl3);

        // TextField
        txt1 = new JTextField();
        txt1.setBounds(150, 30, 200, 30);
        txt1.setFont(new Font("Arial", Font.PLAIN, 14));
        txt1.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 2));
        add(txt1);

        txt2 = new JTextField();
        txt2.setBounds(150, 70, 200, 30);
        txt2.setFont(new Font("Arial", Font.PLAIN, 14));
        txt2.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 2));
        add(txt2);

        txtHasil = new JTextField();
        txtHasil.setBounds(150, 110, 200, 30);
        txtHasil.setFont(new Font("Arial", Font.BOLD, 16));
        txtHasil.setEditable(false);
        txtHasil.setBackground(new Color(255, 255, 204)); // Light yellow
        txtHasil.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 2));
        add(txtHasil);

        // Button
        btnTambah = new JButton("Tambah");
        btnTambah.setBounds(50, 170, 100, 40);
        btnTambah.setFont(new Font("Arial", Font.BOLD, 14));
        btnTambah.setBackground(new Color(34, 139, 34));
        btnTambah.setForeground(Color.WHITE);
        btnTambah.setFocusPainted(false);
        add(btnTambah);

        btnHapus = new JButton("Hapus");
        btnHapus.setBounds(175, 170, 100, 40);
        btnHapus.setFont(new Font("Arial", Font.BOLD, 14));
        btnHapus.setBackground(new Color(255, 69, 0));
        btnHapus.setForeground(Color.WHITE);
        btnHapus.setFocusPainted(false);
        add(btnHapus);

        btnExit = new JButton("Exit");
        btnExit.setBounds(300, 170, 100, 40);
        btnExit.setFont(new Font("Arial", Font.BOLD, 14));
        btnExit.setBackground(new Color(220, 20, 60));
        btnExit.setForeground(Color.WHITE);
        btnExit.setFocusPainted(false);
        add(btnExit);

        // Event Tambah
        btnTambah.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int a = Integer.parseInt(txt1.getText());
                int b = Integer.parseInt(txt2.getText());
                int hasil = a + b;
                txtHasil.setText(String.valueOf(hasil));
            }
        });

        // Event Hapus
        btnHapus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txt1.setText("");
                txt2.setText("");
                txtHasil.setText("");
            }
        });

        // Event Exit
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new PraktekGUI();
    }
}