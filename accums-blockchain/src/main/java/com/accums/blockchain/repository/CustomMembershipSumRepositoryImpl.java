package com.accums.blockchain.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.accums.blockchain.model.MembershipLedgerSummary;
@Repository
public class CustomMembershipSumRepositoryImpl implements CustomMembershipSumRepository {

	@Autowired
    MongoTemplate mongoTemplate;
	
	@Override
	public void updateMemberLedger(List<MembershipLedgerSummary> membershipLedgerSumList) {
		membershipLedgerSumList.forEach(memSum -> {
		Query query = new Query();
		query.addCriteria(Criteria.where("memberId").is(memSum.getMemberId()))
				.addCriteria(Criteria.where("memberName").is(memSum.getMemberName()));
		MembershipLedgerSummary membershipLedgerSummaryDb = mongoTemplate.findOne(query, MembershipLedgerSummary.class);
		if(membershipLedgerSummaryDb!=null) {
			membershipLedgerSummaryDb.setAmtRemaining(membershipLedgerSummaryDb.getLimit().subtract(memSum.getUtilizedAmt()));
			membershipLedgerSummaryDb.setUtilizedAmt(memSum.getUtilizedAmt());
			mongoTemplate.save(membershipLedgerSummaryDb);
		}else {
			memSum.setAmtRemaining(memSum.getLimit().subtract(memSum.getUtilizedAmt()));
			mongoTemplate.insert(memSum);
		
		}
		
	});
	}
}
