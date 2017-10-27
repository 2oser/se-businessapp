package com.businessapp.customer;

import com.businessapp.model.IndividualCustomer;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by uni on 20.10.17.
 */
public class IndividualCustomerTest {
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

}