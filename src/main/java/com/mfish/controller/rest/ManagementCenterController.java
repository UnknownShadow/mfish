package com.mfish.controller.rest;


import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;


@Api(value = "管理中心", description = "管理中心")
@RestController
@RequestMapping("user/api/")
public class ManagementCenterController {

    Logger logger = LoggerFactory.getLogger(getClass());


    /*@Autowired
    GameUserDao gameUserDao;
    @Autowired
    AuthenticationDao authenticationDao;*/


    //微信授权所需参数
    @Value("${wechat.appid}")
    String APPID;
    @Value("${wechat.appsecret}")
    String APPSECRET;
    @Value("${wechat.backUrl}")
    String backUrl;
    @Value("${server.token}")
    String serverToken;



    /*@ApiOperation(value = "实名认证", notes = "实名认证",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = AuthenticationResp.class)
    @ResponseBody
    @RequestMapping(value = "authentication", method = RequestMethod.POST)
    public Ajax authentication(@RequestBody AuthenticationReq authenticationReq) throws Exception {
        logger.info("客户端请求数据  实名认证（/authentication）：{}", authenticationReq.toString());

        logger.info("接口：（user/api/authentication）服务端返回数据：{}", respBody);
        return new Ajax(respBody);
    }*/
}
