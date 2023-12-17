package model.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.entity.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CountryRepository {

    private Logger logger = LoggerFactory.getLogger(CountryRepository.class);

    private Connection connection;

    public CountryRepository(Connection connection) {
        this.connection = connection;
    }

    public List<Country> getCountries() {

        List<Country> countries = new ArrayList<>();

        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from country");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                int population = resultSet.getInt("population");
                countries.add(new Country(id, title, population));
            }
            resultSet.close();
            statement.close();
            return countries;

        } catch (SQLException e) {
            logger.error("Произошла ошибка во время выполнения запрос 'getCountries()'");
            return null;
        }

    }

}
