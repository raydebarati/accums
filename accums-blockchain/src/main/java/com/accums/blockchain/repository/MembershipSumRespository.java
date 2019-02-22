package com.accums.blockchain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.accums.blockchain.model.MembershipLedgerSummary;
@Repository
public interface MembershipSumRespository extends MongoRepository<MembershipLedgerSummary, String> {

}
