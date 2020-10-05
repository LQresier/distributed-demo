package pers.lqresier.dis.demo.es.utils;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

/**
 * Created with IDEA
 *
 * @author qiujiajin
 * @date 2020/8/16 10:14
 * Description
 */
public class OptionalTest {
    private User user;

    @Before
    public void pre(){
        user=new User();
        Address address = new Address();
//        Country country = new Country();
//        country.setIsocode("First");
//        address.setCountry(country);
        user.setAddress(address);
    }

    @Test
    public void fun1(){
        String isocode = Optional.ofNullable(user).map(User::getAddress).map(Address::getCountry).map(Country::getIsocode).orElse("Second");
        System.out.println(isocode);
        System.out.println(user.getAddress().getCountry().getIsocode().toUpperCase());

    }
}
