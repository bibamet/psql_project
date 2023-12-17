package model;

import model.db.DBConnection;
import model.repository.CountryRepository;
import model.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;

public class DBManager {

    private static DBManager instance = null;
    private final CountryRepository countryRepository;
    private final UserRepository userRepository;
    private Logger logger = LoggerFactory.getLogger(DBManager.class);

    private DBManager() {
        Connection connection = DBConnection.getInstance().getConnection();
        userRepository = new UserRepository(connection);
        countryRepository = new CountryRepository(connection);
        logger.info("DBManager created");
    }

    public CountryRepository getCountryRepository() {
        return countryRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public static DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

}
