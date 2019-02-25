package com.accums.blockchain.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.accums.blockchain.constants.AccumConstants;
import com.accums.blockchain.model.MembershipLedgerSummary;
import com.accums.blockchain.simple.blocks.Block;
@Service
public class BlockChainServiceImpl implements BlockChainService{
	
	
	
	private final Logger LOG = LoggerFactory.getLogger(getClass());

	@Override
	public void createBlocks(List<MembershipLedgerSummary> memSumList,List<Block> chain) {
		
		for(int i=0;i<AccumConstants.BLOCK_SIZE;i++) {
			if(CollectionUtils.isEmpty(chain)){
				//Genesis block create
				chain.add(new Block(1, memSumList, "0"));
				chain.get(0).mineBlock(AccumConstants.DIFFICULTY_LEVEL);
			}else {
				chain.add(new Block(i+1, memSumList, chain.get(chain.size()-1).hash));
				chain.get(i).mineBlock(AccumConstants.DIFFICULTY_LEVEL);
			}
		}
		
	}
	
	public Boolean isChainValid(List<Block> blockchain) {
		Block currentBlock;
		Block previousBlock;
		String hashTarget = new String(new char[AccumConstants.DIFFICULTY_LEVEL]).replace('\0', '0');

		//loop through blockchain to check hashes:
		for(int i=1; i < blockchain.size(); i++) {
			currentBlock = blockchain.get(i);
			previousBlock = blockchain.get(i-1);
			//compare registered hash and calculated hash:
			if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
				LOG.info("Current Hashes not equal");
				return false;
			}
			//compare previous hash and registered previous hash
			if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
				LOG.info("Previous Hashes not equal");
				return false;
			}
			//check if hash is solved
			if(!currentBlock.hash.substring( 0, AccumConstants.DIFFICULTY_LEVEL).equals(hashTarget)) {
				LOG.info("This block hasn't been mined");
				return false;
			}
		}
		return true;
	}
}
