package io.srmppn.HappyDorm.billing.query;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BillRepository extends ReactiveCrudRepository<Bill, UUID> {

}
