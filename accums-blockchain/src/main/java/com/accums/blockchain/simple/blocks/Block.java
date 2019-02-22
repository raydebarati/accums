package com.accums.blockchain.simple.blocks;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.accums.blockchain.model.MembershipLedgerSummary;



public class Block {
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	public Integer blockNumber;
	public String hash;
	public String previousHash;
	private long timeStamp; //as number of milliseconds since 1/1/1970.
	private int nonce;
	private List<MembershipLedgerSummary> memberLedgerList;
	private Boolean validChain = true;
	
	

	public Boolean getValidChain() {
		return validChain;
	}

	public void setValidChain(Boolean validChain) {
		this.validChain = validChain;
	}

	//Block Constructor.
	public Block(Integer blockNumber,List<MembershipLedgerSummary> memberLedgerList,String previousHash) {
		this.blockNumber = blockNumber;
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime();
		this.memberLedgerList = memberLedgerList;
		this.hash = calculateHash(); //Making sure we do this after we set the other values.
	}

	//Calculate new hash based on blocks contents
	public String calculateHash() {
		String calculatedhash = SHA256.applySha256(
				previousHash +
				Long.toString(timeStamp) +
				Integer.toString(nonce) +
				memberLedgerList.toString()
				);
		return calculatedhash;
	}



	public void mineBlock(int difficulty) {
		String target = new String(new char[difficulty]).replace('\0', '0'); //Create a string with difficulty * "0"
		while(!hash.substring( 0, difficulty).equals(target)) {
			this.nonce ++;
			this.hash = calculateHash();
		}
		LOG.info("Nonce =" +nonce);
		LOG.info("Block Mined!!! : " + hash);
	}

	@Override
	public String toString() {
		return "Block [blockNumber=" + blockNumber + ", hash=" + hash + ", previousHash=" + previousHash
				+ ", timeStamp=" + timeStamp + ", nonce=" + nonce + ", memberLedgerList=" + memberLedgerList + "]";
	}

	public Integer getBlockNumber() {
		return blockNumber;
	}

	public void setBlockNumber(Integer blockNumber) {
		this.blockNumber = blockNumber;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getPreviousHash() {
		return previousHash;
	}

	public void setPreviousHash(String previousHash) {
		this.previousHash = previousHash;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public int getNonce() {
		return nonce;
	}

	public void setNonce(int nonce) {
		this.nonce = nonce;
	}

	public List<MembershipLedgerSummary> getMemberLedgerList() {
		return memberLedgerList;
	}

	public void setMemberLedgerList(List<MembershipLedgerSummary> memberLedgerList) {
		this.memberLedgerList = memberLedgerList;
	}
	
	
	
}
