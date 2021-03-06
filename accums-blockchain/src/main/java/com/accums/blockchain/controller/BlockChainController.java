package com.accums.blockchain.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.accums.blockchain.constants.AccumConstants;
import com.accums.blockchain.model.MembershipLedgerSummary;
import com.accums.blockchain.repository.CustomMembershipSumRepository;
import com.accums.blockchain.repository.MembershipSumRespository;
import com.accums.blockchain.service.BlockChainService;
import com.accums.blockchain.simple.blocks.Block;

@RestController
public class BlockChainController {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	public static List<Block> chain = new ArrayList<Block>();

	private static List<MembershipLedgerSummary> memSummaryList = new ArrayList<MembershipLedgerSummary>();

	@Autowired
	private MembershipSumRespository membershipSumRespository;

	@Autowired
	private CustomMembershipSumRepository customMembershipSumRepository;

	@Autowired
	private BlockChainService blockChainService;

	@CrossOrigin
	@RequestMapping(value = "/getAllBlocks", method = RequestMethod.GET)
	public List<Block> getAllMembers() {
		LOG.info("Getting all users.");
		memSummaryList = membershipSumRespository.findAll();
		if (CollectionUtils.isEmpty(chain) && !CollectionUtils.isEmpty(memSummaryList)) {
			blockChainService.createBlocks(memSummaryList, chain);
		}
		return chain;

	}

	@CrossOrigin
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
			LOG.info("\nBlockchain is Valid: " + blockChainService.isChainValid(chain));
			chain.get(chain.indexOf(blockInChain.get())).mineBlock(AccumConstants.DIFFICULTY_LEVEL);
			if (chain.indexOf(blockInChain.get()) != 0) {
				blockInChain.get().setPreviousHash(chain.get(chain.indexOf(blockInChain.get()) - 1).hash);
			}
		}
		return blockInChain.get();
	}

	@CrossOrigin
	@RequestMapping(value = "/mineBlocks", method = RequestMethod.GET)
	public List<Block> mineBlocks() {
		chain.clear();
		blockChainService.createBlocks(memSummaryList, chain);
		chain.forEach(block -> block.setValidChain(blockChainService.isChainValid(chain)));
		return chain;

	}
	
	@CrossOrigin
	@RequestMapping(value = "/saveLedgerData", method = RequestMethod.POST)
	public void saveLedgerData(@RequestBody List<MembershipLedgerSummary> memLedgerList) {
		customMembershipSumRepository.updateMemberLedger(memLedgerList);
	}

}
