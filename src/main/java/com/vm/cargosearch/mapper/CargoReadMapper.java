package com.vm.cargosearch.mapper;

import com.vm.cargosearch.database.entity.Cargo;
import com.vm.cargosearch.database.entity.Contact;
import com.vm.cargosearch.dto.*;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;


@RequiredArgsConstructor
@Component
public class CargoReadMapper implements Mapper<Cargo, CargoReadDto> {
    private final CityReadMapper cityReadMapper;
    private final CountryReadMapper countryReadMapper;
    private final KindOfTransportReadMapper kindOfTransportReadMapper;
    private final ContactReadMapper contactReadMapper;


    @Override
    public CargoReadDto map(Cargo object) {

        CountryReadDto countryLoad = Optional.ofNullable(object.getCountryLoad())
                .map(countryReadMapper::map)
                .orElse(null);
        CityReadDto cityLoad = Optional.ofNullable(object.getCityLoad())
                .map(cityReadMapper::map)
                .orElse(null);
        CountryReadDto countryUnload = Optional.ofNullable(object.getCountryUnload())
                .map(countryReadMapper::map)
                .orElse(null);
        CityReadDto cityUnload = Optional.ofNullable(object.getCityUnload())
                .map(cityReadMapper::map)
                .orElse(null);
        KindOfTransportReadDto transport = Optional.ofNullable(object.getKindOfTransport())
                .map(kindOfTransportReadMapper::map)
                .orElse(null);
        Integer price = object.getPrice();
        Integer priceValue = 0;
        if (price == null) {
            price = priceValue;
        }
        ContactReadDto contactReadDto = Optional.ofNullable(object.getContact())
                .map(contactReadMapper::map)
                .orElse(null);

        return new CargoReadDto(object.getId(),
                object.getLoadDate(),
                countryLoad,
                cityLoad,
                countryUnload,
                cityUnload,
                transport,
                object.getNameOfLoad(),
                price,
                contactReadDto
        );
    }
}
