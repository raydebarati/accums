package com.accums.blockchain.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.accums.blockchain.model.MembershipLedgerSummary;
import com.accums.blockchain.repository.CustomMembershipSumRepository;
import com.accums.blockchain.repository.MembershipSumRespository;
import com.accums.blockchain.service.BlockChainService;
import com.accums.blockchain.simple.blocks.Block;
import com.google.gson.GsonBuilder;

@RestController
public class BlockChainController {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	public static List<Block> chain = new ArrayList<Block>();

	private static List<MembershipLedgerSummary> memSummaryList = new ArrayList<MembershipLedgerSummary>();

	public static int difficulty = 1;

	@Autowired
	private MembershipSumRespository membershipSumRespository;

	@Autowired
	private CustomMembershipSumRepository customMembershipSumRepository;

	@Autowired
	private BlockChainService blockChainService;

	@RequestMapping(value = "/getAllBlocks", method = RequestMethod.GET)
	public List<Block> getAllMembers() {
		LOG.info("Getting all users.");
		memSummaryList = membershipSumRespository.findAll();
		blockChainService.createBlocks(memSummaryList, chain);
		return chain;

	}

	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
	public Block saveOrUpdateMember(@RequestBody Block memberBlock) {
		Optional<Block> blockInChain = chain.stream()
				.filter(bl -> bl.getBlockNumber().equals(memberBlock.getBlockNumber())).findAny();
		if (blockInChain.isPresent()) {
			LOG.info("Save/Update member");
			customMembershipSumRepository.updateMemberLedger(memberBlock.getMemberLedgerList());
			memSummaryList = membershipSumRespository.findAll();
			blockInChain.get().setMemberLedgerList(memSummaryList);
			blockInChain.get().setValidChain(blockChainService.isChainValid(chain));
			System.out.println("\nBlockchain is Valid: " + blockChainService.isChainValid(chain));
			chain.get(chain.indexOf(blockInChain.get())).mineBlock(difficulty);
			if (chain.indexOf(blockInChain.get()) != 0) {
				System.out.println(chain.indexOf(blockInChain.get()));
				blockInChain.get().setPreviousHash(chain.get(chain.indexOf(blockInChain.get()) - 1).hash);
			}
		}
		return blockInChain.get();
	}

	@RequestMapping(value = "/mineBlocks", method = RequestMethod.GET)
	public List<Block> mineBlocks() {
		chain.clear();
		blockChainService.createBlocks(memSummaryList, chain);
		chain.forEach(block -> block.setValidChain(blockChainService.isChainValid(chain)));
		return chain;

	}

}
