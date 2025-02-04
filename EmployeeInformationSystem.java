import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class EmployeeInformationSystem {

    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField eidField, nameField, surnameField, ageField;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                EmployeeInformationSystem window = new EmployeeInformationSystem();
                window.frame.setVisible(true);
            } catch (Exception e) {
            }
        });
    }

    public EmployeeInformationSystem() {
        initialize();
    }

    @SuppressWarnings("unused")
    private void initialize() {
        frame = new JFrame("The Employee Information System");
        frame.setBounds(100, 100, 800, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel titleLabel = new JLabel("The Employee Information System");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        titleLabel.setBounds(250, 10, 400, 30);
        frame.getContentPane().add(titleLabel);

        JLabel lblEID = new JLabel("EID:");
        lblEID.setBounds(30, 60, 80, 25);
        frame.getContentPane().add(lblEID);

        eidField = new JTextField();
        eidField.setBounds(120, 60, 150, 25);
        frame.getContentPane().add(eidField);

        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(30, 100, 80, 25);
        frame.getContentPane().add(lblName);

        nameField = new JTextField();
        nameField.setBounds(120, 100, 150, 25);
        frame.getContentPane().add(nameField);

        JLabel lblSurname = new JLabel("Surname:");
        lblSurname.setBounds(30, 140, 80, 25);
        frame.getContentPane().add(lblSurname);

        surnameField = new JTextField();
        surnameField.setBounds(120, 140, 150, 25);
        frame.getContentPane().add(surnameField);

        JLabel lblAge = new JLabel("Age:");
        lblAge.setBounds(30, 180, 80, 25);
        frame.getContentPane().add(lblAge);

        ageField = new JTextField();
        ageField.setBounds(120, 180, 150, 25);
        frame.getContentPane().add(ageField);

        // Table setup
        tableModel = new DefaultTableModel(new Object[]{"EID", "Name", "Surname", "Age"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(300, 60, 460, 300);
        frame.getContentPane().add(scrollPane);

        // Buttons
        JButton btnInsert = new JButton("Insert");
        btnInsert.setBounds(30, 220, 80, 25);
         btnInsert.setBackground(Color.decode("#0000FF"));
        frame.getContentPane().add(btnInsert);

        JButton btnSave = new JButton("Save");
        btnSave.setBounds(120, 220, 80, 25);
        btnSave.setBackground(Color.decode("#008080"));
        frame.getContentPane().add(btnSave);

        JButton btnDelete = new JButton("Delete");
        btnDelete.setBounds(210, 220, 80, 25);
         btnDelete.setBackground(Color.decode("#FF0000"));
        frame.getContentPane().add(btnDelete);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.setBackground(Color.decode("#808000"));
        btnUpdate.setBounds(30, 260, 80, 25);
        frame.getContentPane().add(btnUpdate);

        // Action Listeners
        btnInsert.addActionListener(e -> insertData());
        btnDelete.addActionListener(e -> deleteData());
        btnUpdate.addActionListener(e -> updateData());
    }

    private void insertData() {
        String eid = eidField.getText();
        String name = nameField.getText();
        String surname = surnameField.getText();
        String age = ageField.getText();

        if (!eid.isEmpty() && !name.isEmpty() && !surname.isEmpty() && !age.isEmpty()) {
            tableModel.addRow(new Object[]{eid, name, surname, age});
            clearFields();
        } else {
            JOptionPane.showMessageDialog(frame, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteData() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            tableModel.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a row to delete", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateData() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            tableModel.setValueAt(eidField.getText(), selectedRow, 0);
            tableModel.setValueAt(nameField.getText(), selectedRow, 1);
            tableModel.setValueAt(surnameField.getText(), selectedRow, 2);
            tableModel.setValueAt(ageField.getText(), selectedRow, 3);
            clearFields();
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a row to update", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        eidField.setText("");
        nameField.setText("");
        surnameField.setText("");
        ageField.setText("");
    }
}
