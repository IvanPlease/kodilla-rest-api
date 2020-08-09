package com.crud.tasks.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class CompanyConfig {
    @Value("${info.company.name}")
    private String companyName;
    @Value("${info.company.email}")
    private String companyEmail;
    @Value("${info.app.administrator.address.street}")
    private String appAddressStreet;
    @Value("${info.app.administrator.address.number}")
    private String appAddressNumber;
}
