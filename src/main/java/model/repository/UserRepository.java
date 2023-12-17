package model.repository;

import model.entity.Country;
import model.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private final Logger logger = LoggerFactory.getLogger(UserRepository.class);
    private Connection connection;

    public UserRepository(Connection connection) {
        this.connection = connection;
    }

    public List<User> getUsers() {

        List<User> users = new ArrayList<>();

        Statement statement = null;
        try {

            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from users");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String fullName = resultSet.getString("fullName");
                int country_id = resultSet.getInt("country_id");

                users.add(new User(id, username, fullName, country_id));
            }

            resultSet.close();
            statement.close();

            return users;

        } catch (SQLException e) {
            logger.error("Произошла ошибка во время выполнения запроса 'getUsers()'");
            return null;
        }
    }

    public List<User> getUsersAndCountries() {

        List<User> users = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL_QUERY = "select u.id, u.username, u.\"fullName\", u.country_id, c.title, c.population from users u join country c on u.country_id = c.id";
            ResultSet resultSet = statement.executeQuery(SQL_QUERY);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String fullName = resultSet.getString("fullName");
                int country_id = resultSet.getInt("country_id");

                String title = resultSet.getString("title");
                int population = resultSet.getInt("population");

                User user = new User(id, username, fullName, country_id);
                Country country = new Country(id, title, population);
                user.setCountry(country);

                users.add(user);
            }
            resultSet.close();
            statement.close();
            return users;
        } catch (SQLException e) {
            logger.error("Произошла ошибка во время выполнения запроса 'getUsersAndCountries()'");
            return null;
        }
    }

}
