package org.gslearn.eazyschool.repository;

import org.gslearn.eazyschool.model.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Integer> {
//    private JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public void ContactRepository(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public int saveMessage(Contact contact) {
//        String sql = "INSERT INTO contact_msg (name,mobile_num,email,subject,message,status,created_at,created_by) VALUES (?,?,?,?,?,?,?,?)";
//        return jdbcTemplate.update(sql, contact.getName(), contact.getMobileNum(), contact.getEmail(), contact.getSubject()
//                , contact.getMessage(), contact.getStatus(), contact.getCreatedAt(), contact.getCreatedBy());
//    }
//
//    public List<Contact> findMsgWithStatus(String status) {
//        String sql = "SELECT * FROM contact_msg WHERE status = ?";
//        return jdbcTemplate.query(sql, new PreparedStatementSetter() {
//            public void setValues(PreparedStatement preparedStatement) throws SQLException {
//                preparedStatement.setString(1, status);
//            }
//        }, new ContactRowMapper());
//    }
//
//    public int updateMsgStatus(int contactId, String status, String updatedBy) {
//        String sql = "UPDATE contact_msg SET status = ?, updated_by = ?, updated_at = ? WHERE CONTACT_ID = ?";
//        return jdbcTemplate.update(sql, new PreparedStatementSetter() {
//            public void setValues(PreparedStatement preparedStatement) throws SQLException {
//                preparedStatement.setString(1, status);
//                preparedStatement.setString(2, updatedBy);
//                preparedStatement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
//                preparedStatement.setInt(4, contactId);
//            }
//        });
//    }

    List<Contact> findByStatus(String status);

}
