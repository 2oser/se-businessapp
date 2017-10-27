package com.businessapp;

import com.businessapp.customer.IndividualCustomerTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


/**
 * Created by uni on 27.10.17.
 */

@RunWith(Suite.class)
@SuiteClasses({
        AppTest.class,
        IndividualCustomerTest.class
        })


public class TestSuite {
}
