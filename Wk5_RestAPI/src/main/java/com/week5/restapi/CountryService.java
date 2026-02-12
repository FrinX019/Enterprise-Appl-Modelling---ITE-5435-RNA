package com.week5.restapi;

import java.util.Locale;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CountryService {

    private static final String RELOADLY_COUNTRY_URL = "https://topups.reloadly.com/countries/{countryCode}";

    private final RestTemplate restTemplate;

    public CountryService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Countries fetchCountry(String countryCode) {
        String normalizedCode = countryCode == null ? "" : countryCode.trim().toUpperCase(Locale.ROOT);
        return restTemplate.getForObject(RELOADLY_COUNTRY_URL, Countries.class, normalizedCode);
    }
}
