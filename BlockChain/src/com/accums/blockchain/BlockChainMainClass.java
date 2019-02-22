package com.accums.blockchain;

import java.util.ArrayList;

import com.google.gson.GsonBuilder;

public class BlockChainMainClass {

	public static ArrayList<Block> blockchain = new ArrayList<Block>();
	public static int difficulty = 5;

	public static void main(String[] args) {
		//add our blocks to the blockchain ArrayList:
		BlockData bdata1= new BlockData("1234","188",5,2,"Crocin");
		blockchain.add(new Block(bdata1,"Hi im the first block", "0"));
		System.out.println("Trying to Mine block 1... ");
		blockchain.get(0).mineBlock(difficulty);

		BlockData bdata2= new BlockData("1234","188",6,1,"Crocin");
		blockchain.add(new Block(bdata2,"Yo im the second block",blockchain.get(blockchain.size()-1).hash));
		System.out.println("Trying to Mine block 2... ");
		blockchain.get(1).mineBlock(difficulty);

		BlockData bdata3= new BlockData("1234","188",7,0,"Crocin");
		blockchain.add(new Block(bdata3,"Hey im the third block",blockchain.get(blockchain.size()-1).hash));
		System.out.println("Trying to Mine block 3... ");
		blockchain.get(2).mineBlock(difficulty);

		Block secondBlockdata = blockchain.get(2);
		secondBlockdata.setData("Changing second block data"); // this will turn the box as red
		//blockchain.get(1).mineBlock(difficulty);
		blockchain.remove(secondBlockdata);
		System.out.println("\nBlockchain is Valid: " + isChainValid());


		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
	//	System.out.println("\nThe block chain: ");
		//System.out.println(blockchainJson);
	}

	public static Boolean isChainValid() {
		Block currentBlock;
		Block previousBlock;
		String hashTarget = new String(new char[difficulty]).replace('\0', '0');

		//loop through blockchain to check hashes:
		for(int i=1; i < blockchain.size(); i++) {
			currentBlock = blockchain.get(i);
			previousBlock = blockchain.get(i-1);
			//compare registered hash and calculated hash:
			if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
				System.out.println("Current Hashes not equal");
				return false;
			}
			//compare previous hash and registered previous hash
			if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
				System.out.println("Previous Hashes not equal");
				return false;
			}
			//check if hash is solved
			if(!currentBlock.hash.substring( 0, difficulty).equals(hashTarget)) {
				System.out.println("This block hasn't been mined");
				return false;
			}
		}
		return true;
	}
}