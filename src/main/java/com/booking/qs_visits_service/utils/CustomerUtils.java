package com.booking.qs_visits_service.utils;

import com.booking.qs_visits_service.domain.Customer;
import com.booking.qs_visits_service.dtos.visits.CustomerDto;

public class CustomerUtils {

    public static Customer createCustomer(CustomerDto dto) {
        var customer = new Customer();
        customer.setEmail(dto.getEmail());
        customer.setPhone(dto.getPhone());
        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());

        return customer;
    }
}
