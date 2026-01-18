package GUI;

import airmain.dao.DAO;
import airmain.model.Passenger;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Passengers extends JFrame {

    private DAO dao = new DAO();

    private JTextField txtName;
    private JTextField txtPassport;
    private JTable table;
    private DefaultTableModel model;

    public Passengers() {
        setTitle("Passengers");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // ================= Title =================
        JLabel title = new JLabel("Passengers", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        add(title, BorderLayout.NORTH);

        // ================= Center Panel =================
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        // ---- Name Panel ----
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        namePanel.add(new JLabel("Name:"));
        txtName = new JTextField(20);
        namePanel.add(txtName);
        centerPanel.add(namePanel);

        // ---- Passport Panel ----
        JPanel passportPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        passportPanel.add(new JLabel("Passport Number:"));
        txtPassport = new JTextField(20);
        passportPanel.add(txtPassport);
        centerPanel.add(passportPanel);

        // ---- Table ----
        model = new DefaultTableModel();
        model.addColumn("Name");
        model.addColumn("Passport Number");

        table = new JTable(model);
        table.setRowHeight(25);
        table.setShowGrid(true);
        table.setGridColor(Color.BLACK);

        JScrollPane sp = new JScrollPane(table);
        sp.setPreferredSize(new Dimension(600, 200));

        JPanel tablePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        tablePanel.add(sp);
        centerPanel.add(tablePanel);

        add(centerPanel, BorderLayout.CENTER);

        // ================= Buttons =================
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));

        JButton btnAdd = new JButton("Add");
        JButton btnUpdate = new JButton("Update");
        JButton btnDelete = new JButton("Delete");
        JButton btnView = new JButton("View");

        btnAdd.setBackground(Color.GREEN);
        btnUpdate.setBackground(Color.CYAN);
        btnDelete.setBackground(Color.ORANGE);

        buttons.add(btnAdd);
        buttons.add(btnUpdate);
        buttons.add(btnDelete);
        buttons.add(btnView);

        add(buttons, BorderLayout.SOUTH);

        // ================= Actions =================
        btnAdd.addActionListener(e -> addPassenger());
        btnUpdate.addActionListener(e -> updatePassenger());
        btnDelete.addActionListener(e -> deletePassenger());
        btnView.addActionListener(e -> loadTable());

        loadTable();
    }

    // ================= Methods =================
    private void addPassenger() {
        String name = txtName.getText().trim();
        String passport = txtPassport.getText().trim();

        if (name.isEmpty() || passport.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Fill all fields");
            return;
        }

        dao.addPassenger(new Passenger(name, passport));
        clear();
        loadTable();
    }

    private void updatePassenger() {
        int row = table.getSelectedRow();
        if (row == -1) return;

        String passport = model.getValueAt(row, 1).toString();
        String newName = JOptionPane.showInputDialog(this, "New name:");

        if (newName != null && !newName.trim().isEmpty()) {
            dao.updatePassenger(new Passenger(newName.trim(), passport));
            dao.updatePassengerQueue(new Passenger(newName.trim(), passport));
            loadTable();
        }
    }

    private void deletePassenger() {
        int row = table.getSelectedRow();
        if (row == -1) return;

        String passport = model.getValueAt(row, 1).toString();
        dao.removePassengerFromQueueAndDB(passport);
        loadTable();
    }

    private void loadTable() {
        model.setRowCount(0);
        for (Passenger p : dao.getPassengerQueue()) {
            model.addRow(new Object[]{p.getName(), p.getPassportNumber()});
        }
    }

    private void clear() {
        txtName.setText("");
        txtPassport.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Passengers().setVisible(true));
    }
}