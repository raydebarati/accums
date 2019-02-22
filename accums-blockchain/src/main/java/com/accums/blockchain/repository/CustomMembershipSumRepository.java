package com.accums.blockchain.repository;

import java.util.List;

import com.accums.blockchain.model.MembershipLedgerSummary;

public interface CustomMembershipSumRepository {

	void updateMemberLedger(List<MembershipLedgerSummary> membershipLedgerSumList);
}
