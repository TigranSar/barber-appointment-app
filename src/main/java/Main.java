import com.tigran.springcourse.dao.BarberRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        List fsd = jdbcTemplate.query("SELECT * FROM BARBERS WHERE id = 41",new BarberRowMapper());
    }
}
