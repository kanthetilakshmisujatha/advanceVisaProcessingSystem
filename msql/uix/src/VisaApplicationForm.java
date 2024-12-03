import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class VisaApplicationForm extends JFrame {
    private JTextField nameField;
    private JTextField passportNumberField;
    private JTextField statusField;
    private JComboBox<String> visaTypeComboBox;
    private VisaApplicationDAO dao;
    private DefaultTableModel tableModel;

    public VisaApplicationForm() {
        dao = new VisaApplicationDAO();

        setTitle("Visa Application Manager");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creating labels and fields
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();

        JLabel passportNumberLabel = new JLabel("Passport Number:");
        passportNumberField = new JTextField();

        JLabel visaTypeLabel = new JLabel("Visa Type:");
        String[] visaTypes = {"Business", "Tourist", "Student"};
        visaTypeComboBox = new JComboBox<>(visaTypes);

        JLabel statusLabel = new JLabel("Status:");
        statusField = new JTextField();

        JButton addButton = new JButton("Add Application");
        addButton.addActionListener(new AddButtonListener());

        JButton removeButton = new JButton("Remove Application");
        // Add action listener for removeButton

        JButton updateStatusButton = new JButton("Update Status");
        // Add action listener for updateStatusButton

        // Setting up the table
        String[] columnNames = {"Name", "Passport Number", "Visa Type", "Status"};
        tableModel = new DefaultTableModel(columnNames, 0);
        JTable applicationTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(applicationTable);

        // Setting up the layout
        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(nameLabel)
                    .addComponent(passportNumberLabel)
                    .addComponent(visaTypeLabel)
                    .addComponent(statusLabel))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(nameField)
                    .addComponent(passportNumberField)
                    .addComponent(visaTypeComboBox)
                    .addComponent(statusField)
                    .addComponent(addButton)
                    .addComponent(removeButton)
                    .addComponent(updateStatusButton)
                    .addComponent(scrollPane))
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel)
                    .addComponent(nameField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(passportNumberLabel)
                    .addComponent(passportNumberField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(visaTypeLabel)
                    .addComponent(visaTypeComboBox))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(statusLabel)
                    .addComponent(statusField))
                .addComponent(addButton)
                .addComponent(removeButton)
                .addComponent(updateStatusButton)
                .addComponent(scrollPane)
        );

        add(panel);
    }

    private class AddButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            String passportNumber = passportNumberField.getText();
            String visaType = (String) visaTypeComboBox.getSelectedItem();
            String status = statusField.getText();

            // Assuming the name field contains both first and last name
            String[] nameParts = name.split(" ");
            String firstName = nameParts.length > 0 ? nameParts[0] : "";
            String lastName = nameParts.length > 1 ? nameParts[1] : "";

            VisaApplication application = new VisaApplication(firstName, lastName, passportNumber, visaType, status);

            try {
                dao.saveApplication(application);
                // Add the new application to the table
                tableModel.addRow(new Object[]{name, passportNumber, visaType, status});
                JOptionPane.showMessageDialog(VisaApplicationForm.this, "Application submitted successfully!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(VisaApplicationForm.this, "Error saving application: " + ex.getMessage());
            }
        }
    }

    // Main method to launch the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VisaApplicationForm form = new VisaApplicationForm();
            form.setVisible(true);
        });
    }
}
