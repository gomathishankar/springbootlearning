package org.gslearn.eazyschool.repository;

import org.gslearn.eazyschool.model.Holiday;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HolidayRepository extends CrudRepository<Holiday, String> {

    // For the H2 and Direct Spring JDBC
//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public HolidayRepository(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public List<Holiday> findListOfHolidays() {
//        String sql = "SELECT * FROM holidays";
//        var rowMapper = new BeanPropertyRowMapper<Holiday>(Holiday.class);
//        return jdbcTemplate.query(sql, rowMapper);
//    }

}
