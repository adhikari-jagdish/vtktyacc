package com.vtkty.vtktyacc.service.repository;

import com.vtkty.vtktyacc.service.model.BookingVoucher;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookingVoucherRepository extends MongoRepository<BookingVoucher, String> {
}
