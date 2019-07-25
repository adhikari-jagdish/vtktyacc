package com.vtkty.vtktyacc.service.repository;

import com.vtkty.vtktyacc.service.model.Address;
import com.vtkty.vtktyacc.service.model.Invoice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends MongoRepository<Address, String> {

   Address findByAgencyCode(String agencyCode);
}
