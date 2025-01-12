package jsonplaceholder.api.data;
import jsonplaceholder.api.models.users.Address;
import jsonplaceholder.api.models.users.Company;
import jsonplaceholder.api.models.users.Geo;
import jsonplaceholder.api.models.users.Users;

public class UserBaseData extends DataGeneratorBase {

    public Users createUser(){

        Users user= new Users();
        Address address=new Address();
        address.setStreet(faker().address().streetName());
        address.setSuite(faker().address().streetAddressNumber());
        address.setCity(faker().address().city());
        address.setZipcode(faker().address().zipCode());

        Geo geo=new Geo();
        geo.setLat(String.valueOf(random().nextDouble() * 180.0 - 90.0));
        geo.setLng(String.valueOf(random().nextDouble() * 360.0 - 180.0));

        address.setGeo(geo);

        Company company=new Company();
        company.setName(faker().company().name());
        company.setCatchPhrase(faker().commerce().promotionCode());
        company.setBs(faker().job().field());


        user.setId(faker().number().numberBetween(1,150));
        user.setName(faker().name().username());
        user.setEmail(faker().internet().emailAddress());
        user.setAddress(address);
        user.setPhone(faker().phoneNumber().phoneNumber());
        user.setWebsite(faker().internet().url());
        user.setCompany(company);
        return user;
    }
}
