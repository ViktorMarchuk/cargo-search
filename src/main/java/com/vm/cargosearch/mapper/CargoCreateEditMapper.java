package com.vm.cargosearch.mapper;

import com.vm.cargosearch.database.entity.*;
import com.vm.cargosearch.database.repository.CityRepository;
import com.vm.cargosearch.database.repository.ContactRepository;
import com.vm.cargosearch.database.repository.CountryRepository;
import com.vm.cargosearch.database.repository.KindOfTransportRepository;
import com.vm.cargosearch.dto.CargoCreateEditDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CargoCreateEditMapper implements Mapper<CargoCreateEditDto, Cargo> {
    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;
    private final KindOfTransportRepository transportRepository;
    private final ContactRepository contactRepository;

    @Override
    public Cargo map(CargoCreateEditDto fromObject, Cargo toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    @Override
    public Cargo map(CargoCreateEditDto object) {
        Cargo cargo = new Cargo();
        copy(object, cargo);
        return cargo;
    }

    private void copy(CargoCreateEditDto object, Cargo cargo) {
        if (object.getId() != null) {
            cargo.setId(object.getId());
        }
        if (object.getLoadDate() != null) {
            cargo.setLoadDate(object.getLoadDate());
        }
        if (object.getCountryLoad() != null) {
            cargo.setCountryLoad(getCountry(object.getCountryLoad().getId()));
        }
        if (object.getCityLoad() != null) {
            cargo.setCityLoad(getCity(object.getCityLoad().getId()));
        }
        if (object.getCountryUnload() != null) {
            cargo.setCountryUnload(getCountry(object.getCountryUnload().getId()));
        }
        if (object.getCityUnload() != null) {
            cargo.setCityUnload(getCity(object.getCityUnload().getId()));
        }
        if (object.getKindOfTransport() != null) {
            cargo.setKindOfTransport(getTransport(object.getKindOfTransport().getId()));
        }
        if (object.getNameOfLoad() != null) {
            cargo.setNameOfLoad(object.getNameOfLoad());
        }
        if (object.getPrice() != null) {
            cargo.setPrice(object.getPrice());
        }
        if (object.getContact() != null) {
            cargo.setContact(getContact(object.getContact().getId()));
        }
    }

    private KindOfTransport getTransport(Integer transportId) {
        return Optional.ofNullable(transportId)
                .flatMap(transportRepository::findById)
                .orElse(null);
    }

    private City getCity(Integer cityId) {
        return Optional.ofNullable(cityId)
                .flatMap(cityRepository::findById)
                .orElse(null);
    }

    private Country getCountry(Integer countryId) {
        return Optional.ofNullable(countryId)
                .flatMap(countryRepository::findById)
                .orElse(null);
    }

    private Contact getContact(Integer contactId) {
        return Optional.ofNullable(contactId)
                .flatMap(contactRepository::findById)
                .orElse(null);
    }
}
