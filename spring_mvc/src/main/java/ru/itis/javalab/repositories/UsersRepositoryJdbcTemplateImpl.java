package ru.itis.javalab.repositories;

import org.springframework.context.annotation.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.itis.javalab.models.User;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Profile("master")
@Repository
public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    //language=SQL
    private static final String SQL_SELECT_BY_ID = "select * from users where id = ?";

    //language=SQL
    private static final String SQL_SELECT_ALL_WITH_PAGES = "select * from users order by id limit :limit offset :offset ;";

    //language=SQL
    private static final String SQL_SELECT_ALL = "select * from users";

    //language=SQL
    private static final String SQL_INSERT_USER = "insert into users(first_name," +
            " last_name, confirm_code, email, password, state) values (?, ?, ?, ?, ?, ?)";

    //language=SQL
    private static final String SQL_SELECT_BY_AGE = "select * from users where age = ?";

    private RowMapper<User> userRowMapper = (row, i) -> User.builder()
            .id(row.getLong("id"))
            .firstName(row.getString("first_name"))
            .lastName(row.getString("last_name"))
            .confirmCode(row.getString("confirm_code"))
            .email(row.getString("email"))
            .state(User.State.valueOf(row.getString("state")))
            .build();

    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, userRowMapper);
    }

    @Override
    public List<User> findAll(int page, int size) {
        Map<String, Object> params = new HashMap<>();
        params.put("limit", size);
        params.put("offset", page * size);
        return namedParameterJdbcTemplate.query(SQL_SELECT_ALL_WITH_PAGES, params, userRowMapper);
    }

    @Override
    public Optional<User> findById(Long id) {
        User user;
        try {
            user = jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, userRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            user = null;
        }

        return Optional.ofNullable(user);

    }

    @Override
    public void save(User entity) {
        jdbcTemplate.update(SQL_INSERT_USER, entity.getFirstName(), entity.getLastName()
        , entity.getConfirmCode(), entity.getEmail(), entity.getPassword(), entity.getState().toString());
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void delete(User entity) {

    }
}
