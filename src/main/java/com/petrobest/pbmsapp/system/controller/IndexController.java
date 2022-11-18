package com.petrobest.pbmsapp.system.controller;

import com.petrobest.pbmsapp.common.domain.ResponseBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;  //websocket推送客户端


    @RequestMapping("/login")
    public String index() {
        return "wel";
    }

    @RequestMapping(value = "/403")
    @ResponseBody
    public Object noAuth() {
        return ResponseBo.error("您没有权限访问。");
    }

    @GetMapping("/sendwsmsg")
    @ResponseBody
    public Object send() {
        simpMessagingTemplate.convertAndSendToUser("sessionId", "/duplicateLogin", "send a msg");
//        simpMessagingTemplate.convertAndSend("/topic/public","topic public");
        return ResponseBo.ok("发送成功");
    }


}
