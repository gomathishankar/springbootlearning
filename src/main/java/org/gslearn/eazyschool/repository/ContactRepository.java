package org.gslearn.eazyschool.repository;

import org.gslearn.eazyschool.model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

    @Query("SELECT c FROM Contact c WHERE c.status = :status")
        //@Query(value = "SELECT * FROM contact_msg c WHERE c.status = :status",nativeQuery = true)
    Page<Contact> findByStatusWithQuery(@Param("status") String status, Pageable pageable);

    @Transactional
    @Modifying
    @Query("UPDATE Contact c SET c.status = ?1 WHERE c.contactId = ?2")
    int updateStatusById(String status, int id);

    Page<Contact> findOpenMsgs(@Param("status") String status, Pageable pageable);

    @Transactional
    @Modifying
    int updateMsgStatus(String status, int id);

    @Query(nativeQuery = true)
    Page<Contact> findOpenMsgsNative(@Param("status") String status, Pageable pageable);

    @Transactional
    @Modifying
    @Query(nativeQuery = true)
    int updateMsgStatusNative(String status, int id);

}
