package com.vtkty.vtktyacc.service.DAL;

import com.vtkty.vtktyacc.service.model.Address;
import com.vtkty.vtktyacc.service.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressDalImp {

    @Autowired
    AddressRepository addressRepository;

    public Address getByAgencyCode(String agencyCode){
        return addressRepository.findByAgencyCode(agencyCode);
    }

}
