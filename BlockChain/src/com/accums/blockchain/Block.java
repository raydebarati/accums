package com.accums.blockchain;
import java.util.Date;

import java.util.Date;

public class Block {

	public String hash;
	public String previousHash;
	private String data; //our data will be a simple message.
	private long timeStamp; //as number of milliseconds since 1/1/1970.
	private int nonce;
	private BlockData blockData;

	//Block Constructor.
	public Block(BlockData blockData,String data,String previousHash ) {
		this.data = data;
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime();
		this.blockData = blockData;
		this.hash = calculateHash(); //Making sure we do this after we set the other values.
	}

	//Calculate new hash based on blocks contents
	public String calculateHash() {
		String calculatedhash = StringUtil.applySha256(
				previousHash +
				Long.toString(timeStamp) +
				Integer.toString(nonce) +
				data + blockData.toString()
				);
		return calculatedhash;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public void mineBlock(int difficulty) {
		String target = new String(new char[difficulty]).replace('\0', '0'); //Create a string with difficulty * "0"
		while(!hash.substring( 0, difficulty).equals(target)) {
			nonce ++;
			hash = calculateHash();
		}
		System.out.println("Nonce =" +nonce);
		System.out.println("Block Mined!!! : " + hash);
	}
}
