package com.weather.demo.weatherapp.transformer;

import com.weather.demo.weatherapp.domain.CityCoordinates;
import com.weather.demo.weatherapp.entity.GeocodingCoordinatesEntity;
import org.springframework.stereotype.Service;

@Service
public class GeocodingCoordinatesTransformer {

    public CityCoordinates transformToDomain(final GeocodingCoordinatesEntity geocodingCoordinatesEntity){
        return CityCoordinates.builder()
                .latitude(geocodingCoordinatesEntity.getLatitude())
                .longitude(geocodingCoordinatesEntity.getLongitude())
                .build();
    }
}
