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
import com.roy.publics.utils.ProtoStuffUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/seckill")
@Api(tags = { "Controller" }, description = "Seckill")
public class SeckillController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SecKillService secKillService;

	@RequestMapping(value = "/{secKillId}/detail", method = RequestMethod.GET)
	@ApiOperation(value = "Get detail API", response = String.class)
	public ResponseEntity<String> detail(
			@ApiParam(value = "id for seckill like : 1004") @PathVariable("secKillId") String secKillId) throws Exception {
		logger.info(secKillId);
		long id = Long.parseLong(secKillId);
		byte[] secKill = null;
		try {
			secKill = secKillService.getById(id);
		} catch (Exception e) {
			logger.error(e.toString());
			return new ResponseEntity<String>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		logger.info(JSON.toJSONString(ProtoStuffUtil.deserializer(secKill, SecKill.class)));
		return new ResponseEntity<String>(JSON.toJSONString(ProtoStuffUtil.deserializer(secKill, SecKill.class)),
				HttpStatus.OK);
	}

}
