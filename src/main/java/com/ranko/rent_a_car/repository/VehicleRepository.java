package com.ranko.rent_a_car.repository;

import com.ranko.rent_a_car.model.Vehicle;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

	Collection<Vehicle> findByCarModel(String carModel) throws DataAccessException;

//	Vehicle findById(Long id) throws DataAccessException;

}
