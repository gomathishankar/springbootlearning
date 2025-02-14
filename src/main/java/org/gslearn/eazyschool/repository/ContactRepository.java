package org.gslearn.eazyschool.repository;

import org.gslearn.eazyschool.model.Contact;
import org.gslearn.eazyschool.rowmapper.ContactRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ContactRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void ContactRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int saveMessage(Contact contact) {
        String sql="INSERT INTO CONTACT_MSG (NAME,MOBILE_NUM,EMAIL,SUBJECT,MESSAGE,STATUS,CREATED_AT,CREATED_BY) VALUES (?,?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql,contact.getName(),contact.getMobileNum(),contact.getEmail(),contact.getSubject()
        ,contact.getMessage(),contact.getStatus(),contact.getCreatedAt(),contact.getCreatedBy());
    }

    public List<Contact> findMsgWithStatus(String status) {
        String sql = "SELECT * FROM CONTACT_MSG WHERE STATUS = ?";
        return jdbcTemplate.query(sql,new PreparedStatementSetter() {
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, status);
            }
        },new ContactRowMapper());
    }
}
