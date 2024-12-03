import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VisaApplicationDAO {

    public void saveApplication(VisaApplication application) throws SQLException {
        String sql = "INSERT INTO applications (first_name, last_name, passport_number, visa_type, status, application_date) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, application.getFirstName());
            stmt.setString(2, application.getLastName());
            stmt.setString(3, application.getPassportNumber());
            stmt.setString(4, application.getVisaType());
            stmt.setString(5, application.getStatus());
            stmt.setDate(6, new Date(System.currentTimeMillis()));

            stmt.executeUpdate();
        }
    }

    // Implement remove and update status methods similarly
}
