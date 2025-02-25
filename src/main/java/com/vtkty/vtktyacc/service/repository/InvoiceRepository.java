package com.vtkty.vtktyacc.service.repository;

import com.vtkty.vtktyacc.service.model.Invoice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends MongoRepository<Invoice,String>, InvoiceRepositoryCustom {


}
