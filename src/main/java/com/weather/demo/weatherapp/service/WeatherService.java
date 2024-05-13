package com.weather.demo.weatherapp.service;


import com.weather.demo.weatherapp.domain.CityCoordinates;
import com.weather.demo.weatherapp.domain.CityWeather;
import com.weather.demo.weatherapp.domain.WeatherRequestDetails;
import com.weather.demo.weatherapp.entity.WeatherResponse;
import com.weather.demo.weatherapp.provider.GeocodingProvider;
import com.weather.demo.weatherapp.provider.WeatherProvider;
import com.weather.demo.weatherapp.transformer.GeocodingCoordinatesTransformer;
import com.weather.demo.weatherapp.transformer.OpenWeatherTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private final WeatherProvider weatherProvider;
    private final GeocodingProvider geocodingProvider;
    private final GeocodingCoordinatesTransformer geocodingCoordinatesTransformer;
    private final OpenWeatherTransformer openWeatherTransformer;

    public WeatherResponse getWeather(WeatherRequestDetails weatherRequestDetails) throws Exception {
        final CityCoordinates cityCoordinates = geocodingCoordinatesTransformer.transformToDomain((geocodingProvider.getCoordinates(weatherRequestDetails)));

        final CityWeather cityWeather = openWeatherTransformer.transformToDomain(weatherProvider.getWeather((cityCoordinates)));

        return openWeatherTransformer.transformToEntity(cityWeather);
    }

}
