package com.vtkty.vtktyacc.service.repository;

import com.vtkty.vtktyacc.service.model.Invoice;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InvoiceRepository extends MongoRepository<Invoice, String> {

   //Invoice findbyEmail(String email);
}
