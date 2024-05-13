package com.weather.demo.weatherapp.transformer;


import com.weather.demo.weatherapp.domain.CityWeather;
import com.weather.demo.weatherapp.entity.OpenWeatherResponseEntity;
import com.weather.demo.weatherapp.entity.WeatherEntity;
import com.weather.demo.weatherapp.entity.WeatherResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;


@Service
public class OpenWeatherTransformer {


    public CityWeather transformToDomain(OpenWeatherResponseEntity weather) {
        return CityWeather.builder()
                .weather(weather.getWeather()[0].getMain())
                .details(weather.getWeather()[0].getDesc())
                .build();
    }

    public WeatherResponse transformToEntity(final CityWeather cityWeather) {
        return WeatherResponse.builder()
                .details(cityWeather.getDetails())
                .weather(cityWeather.getWeather())
                .build();
    }
}
