package com.vtkty.vtktyacc.service.repository;

import com.vtkty.vtktyacc.service.model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InvoiceRepositoryImpl implements InvoiceRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Invoice maxId() {
        final Query query = new Query()
                .limit(1)
                .with(new Sort(Sort.Direction.DESC, "invoiceNo"));

        return mongoTemplate.findOne(query, Invoice.class);
    }


   /* public Long updateinvoiceId(String key) {
        Query query = new Query(Criteria.where("_id").is(key));

        Update update = new Update();
        update.inc("seq", 1);

        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true);

        SequenceId seqId = mongoOperation.findAndModify(query, update, options, SequenceId.class);

        return seqId.getSeq();
    }*/
}
