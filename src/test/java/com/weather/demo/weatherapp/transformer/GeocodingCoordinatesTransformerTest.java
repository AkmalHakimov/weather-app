package com.weather.demo.weatherapp.transformer;

import com.weather.demo.weatherapp.domain.CityCoordinates;
import com.weather.demo.weatherapp.entity.GeocodingCoordinatesEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class GeocodingCoordinatesTransformerTest {

    private GeocodingCoordinatesTransformer transformer;

    @BeforeEach
    public void setUp(){
        transformer = new GeocodingCoordinatesTransformer();
    }

    @Test
    void test_should_transform_geocoding_coordinates_to_main() {
        final GeocodingCoordinatesEntity entity = GeocodingCoordinatesEntity.builder()
                .longitude("12.94")
                .latitude("-10.04")
                .build();

        CityCoordinates cityCoordinates = transformer.transformToDomain(entity);

        assertEquals(entity.getLatitude(),cityCoordinates.getLatitude());
        assertEquals(entity.getLongitude(),cityCoordinates.getLongitude());
    }
}