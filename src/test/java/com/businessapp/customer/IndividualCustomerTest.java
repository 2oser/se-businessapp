package com.businessapp.customer;

import com.businessapp.model.IndividualCustomer;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by uni on 20.10.17.
 */
public class IndividualCustomerTest {

    @Test
    public void testLastName(){
        IndividualCustomer ic = new IndividualCustomer();
        ic.setName("aa");
        String r = ic.getName();
        assertEquals(ic.getName(), r);

        ic.setName(null);
        String nWert = ic.getName();
        assertEquals(ic.getName(), nWert);

        ic.setName("");
        String leer = ic.getName();
        assertEquals(ic.getName(), leer);
    }


    @Test
    public void testFirstName(){
        IndividualCustomer ic = new IndividualCustomer();
        ic.setFirstName("aa");
        String r = ic.getName();
        assertEquals(ic.getName(), r);

        ic.setFirstName(null);
        String nWert = ic.getName();
        assertEquals(ic.getName(), nWert);

        ic.setFirstName("");
        String leer = ic.getName();
        assertEquals(ic.getName(), leer);
    }

    @Test
    public void testCustomerID(){
        IndividualCustomer ic = new IndividualCustomer();
        ic.setId("1234A");
        String r = ic.getId();
        assertEquals(ic.getId(), r);

        ic.setFirstName(null);
        String nWert = ic.getId();
        assertEquals(ic.getId(), nWert);

        ic.setFirstName("");
        String leer = ic.getId();
        assertEquals(ic.getId(), leer);
    }

    @Test
    public void testDate(){
        IndividualCustomer ic = new IndividualCustomer();
        ic.setCreated(new Date(2017, 10, 27));
        Date r = ic.getCreated();
        assertEquals(ic.getCreated(), r);

        ic.setCreated(null);
        Date nWert = ic.getCreated();
        assertEquals(ic.getCreated(), nWert);
    }

    /*
    @Test
    public void sameName() throws Exception {
        IndividualCustomer ic = new IndividualCustomer();
        String name = "Meyer";
        ic.setFirstName(name);
        assertEquals(ic.getFirstName(), name);
        assertThat(name==ic.getFirstName(), is(true));
    }

    @Test
    public void nullTest() throws Exception {
        IndividualCustomer n = new IndividualCustomer();
        String nWert = null;
        n.setFirstName(nWert);
        assertEquals(n.getFirstName(), nWert);
        assertThat(nWert==n.getFirstName(), is(true));

    }

    @Test
    public void emptyName(){
        IndividualCustomer e = new IndividualCustomer();
        String empty = "";
        e.setFirstName(empty);
        assertEquals(empty, e.getFirstName());
        assertThat(empty==e.getFirstName(), is(true));
    }

    @Test
    public void sameAdress(){
        IndividualCustomer c = new IndividualCustomer();
        String id = new String("2017");
        c.setId(id);
        assertThat(c.getId()==id, is(true));
    }
    */

}