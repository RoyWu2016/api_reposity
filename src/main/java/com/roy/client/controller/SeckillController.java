package com.roy.client.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.roy.publics.seckill.bean.SecKill;
import com.roy.publics.seckill.service.SecKillService;

@RestController
@RequestMapping("/seckill")
public class SeckillController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SecKillService secKillService;

	@RequestMapping(value = "/{secKillId}/detail", method = RequestMethod.GET)
	public ResponseEntity<String> detail(@PathVariable("secKillId") String secKillId) {
		logger.info(secKillId);
		long id = Long.parseLong(secKillId);
		SecKill secKill = secKillService.getById(id);
		logger.info(JSON.toJSONString(secKill));
		return new ResponseEntity<String>(JSON.toJSONString(secKill),HttpStatus.OK);
	}

}
