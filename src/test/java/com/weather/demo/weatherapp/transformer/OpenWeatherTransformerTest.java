package com.weather.demo.weatherapp.transformer;

import com.weather.demo.weatherapp.domain.CityWeather;
import com.weather.demo.weatherapp.entity.OpenWeatherResponseEntity;
import com.weather.demo.weatherapp.entity.WeatherEntity;
import com.weather.demo.weatherapp.entity.WeatherResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OpenWeatherTransformerTest {

    private OpenWeatherTransformer underTest;

    @BeforeEach
    public void setUp(){
        underTest = new OpenWeatherTransformer();
    }

    @Test
    void test_should_transform_open_weather_entity_to_domain() {

        WeatherEntity weatherEntity = WeatherEntity.builder()
                .desc("rain")
                .main("really rain")
                .build();

        final WeatherEntity[] weatherEntityArray = {weatherEntity};

        OpenWeatherResponseEntity entity = OpenWeatherResponseEntity.builder()
                .weather(weatherEntityArray)
                .build();

        CityWeather cityWeather = underTest.transformToDomain(entity);

        assertEquals(entity.getWeather()[0].getMain(),cityWeather.getWeather());
        assertEquals(entity.getWeather()[0].getDesc(),cityWeather.getDetails());

    }

    @Test
    void test_should_transform_city_weather_domain_to_entity() {

        CityWeather cityWeather = CityWeather.builder()
                .details("details")
                .weather("weather")
                .build();

        WeatherResponse weatherResponse = underTest.transformToEntity(cityWeather);

        assertEquals(weatherResponse.getWeather(),cityWeather.getWeather());
        assertEquals(weatherResponse.getDetails(),cityWeather.getDetails());

    }
}