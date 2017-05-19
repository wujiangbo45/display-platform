package com.mapbar.display.service;

import com.alibaba.fastjson.JSONObject;
import com.mapbar.display.command.TdsInCommand;
import com.mapbar.display.command.TdsOutCommand;
import com.mapbar.display.repository.HyCarRepository;
import com.mapbar.display.result.CommonResult;
import com.mapbar.display.result.ReturnCode;
import com.mapbar.display.util.HttpUtil;
import com.mapbar.display.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by yinsihua on 2017/5/2.
 */
@Service
public class TdsService {
    private static final Logger logger = LoggerFactory.getLogger(TdsService.class);

    @Value("${tdsInUrl}")
    private String tdsInUrl;

    @Value("${tdsOutUrl}")
    private String tdsOutUrl;
    @Autowired
    HyCarRepository hyCarRepository;

    /**
     * TDS入库
     * @param command
     * @return
     */
    public CommonResult tdsIn (TdsInCommand command) {
        CommonResult result = new CommonResult();
        result.fillResult(ReturnCode.OK);
        logger.info("tdsIn--------start--------");
        try {
            String jsonString = JsonUtil.toJson(command);
            String param = HttpUtil.mapToParams(JsonUtil.toMap(jsonString));
            JSONObject resutlJson =  HttpUtil.getHttpResult(tdsInUrl+ param, "");
            // 有响应结果
            if (resutlJson != null && resutlJson.size() > 0) {
                int code = resutlJson.getInteger("Code");
                if (code == 400) {
                    result.fillResult(ReturnCode.CLIENT_ERROR);
                    result.setErrmsg(resutlJson.getString("Errmsg"));
                } else if (code == 500) {
                    result.fillResult(ReturnCode.SERVER_ERROR);
                    result.setErrmsg(resutlJson.getString("Errmsg"));
                }
            }
        } catch (Exception e) {
            result.fillResult(ReturnCode.SERVER_ERROR);
            logger.error("调用TDS入库接口异常：" + e.getMessage());
        }
        logger.info("tdsIn--------end--------");
        return result;
    }

    /**
     * TDS出库
     * @param command
     * @return
     */
    public CommonResult tdsOut (TdsOutCommand command) {
        logger.info("tdsOut--------start--------");
        CommonResult result = new CommonResult();
        result.fillResult(ReturnCode.OK);
        try {
            String jsonString = JsonUtil.toJson(command);
            String param = HttpUtil.mapToParams(JsonUtil.toMap(jsonString));
            JSONObject resutlJson =  HttpUtil.getHttpResult(tdsOutUrl+ param, "");
            // 有响应结果
            if (resutlJson != null && resutlJson.size() > 0) {
                int code = resutlJson.getInteger("Code");
                if (code == 400) {
                    result.fillResult(ReturnCode.CLIENT_ERROR);
                    result.setErrmsg(resutlJson.getString("Errmsg"));
                } else if (code == 500) {
                    result.fillResult(ReturnCode.SERVER_ERROR);
                    result.setErrmsg(resutlJson.getString("Errmsg"));
                }
            }
        } catch (Exception e) {
            result.fillResult(ReturnCode.SERVER_ERROR);
            logger.error("调用TDS出库接口异常：" + e.getMessage());
        }
        logger.info("tdsOut--------end--------");
        return result;
    }

    public void test(){
        System.out.println(hyCarRepository.findByCarId(1L));
    }
}
