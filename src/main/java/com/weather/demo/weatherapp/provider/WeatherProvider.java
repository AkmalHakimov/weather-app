package com.weather.demo.weatherapp.provider;

import com.weather.demo.weatherapp.domain.CityCoordinates;
import com.weather.demo.weatherapp.domain.CityWeather;
import com.weather.demo.weatherapp.entity.OpenWeatherResponseEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class WeatherProvider {

    @Value("${weather.url}")
    String weatherUrl;

    @Value("${api.key}")
    String apiKey;

    public OpenWeatherResponseEntity getWeather(CityCoordinates cityCoordinates) throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        final ResponseEntity<OpenWeatherResponseEntity> responseEntity;

        HttpEntity<String> requestEntity = new HttpEntity<>(null,null);

        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(weatherUrl)
                .queryParam("lon",cityCoordinates.getLongitude())
                .queryParam("lat",cityCoordinates.getLatitude())
                .queryParam("appid",apiKey)
                .build();

        try {
           responseEntity = restTemplate
                   .exchange(uriComponents.toString(),
                           HttpMethod.GET,
                           requestEntity,
                           OpenWeatherResponseEntity.class);
        }catch (HttpStatusCodeException e){
            throw new Exception(e.getMessage());

        }

        return responseEntity.getBody();
    }
}
