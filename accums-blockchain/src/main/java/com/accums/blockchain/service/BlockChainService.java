package com.accums.blockchain.service;

import java.util.List;

import com.accums.blockchain.model.MembershipLedgerSummary;
import com.accums.blockchain.simple.blocks.Block;

public interface BlockChainService {



	void createBlocks(List<MembershipLedgerSummary> memSumList, List<Block> chain);
	
	Boolean isChainValid(List<Block> blockchain);
}
