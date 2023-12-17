import model.DBManager;
import model.entity.Country;
import model.entity.User;
import model.repository.CountryRepository;
import model.repository.UserRepository;

public class psql_project {
    public static void main(String[] args) {

//        CountryRepository countryRepository = DBManager.getInstance().getCountryRepository();
//        for (Country country :
//                countryRepository.getCountries()) {
//            System.out.println(country);
//        }
//
        UserRepository userRepository = DBManager.getInstance().getUserRepository();
//        for (User user :
//                userRepository.getUsers()) {
//            System.out.println(user);
//        }

        for (User user :
                userRepository.getUsersAndCountries()) {
            System.out.println(user);
        }

    }
}
