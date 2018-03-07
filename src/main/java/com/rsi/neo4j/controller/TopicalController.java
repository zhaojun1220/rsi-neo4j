package com.rsi.neo4j.controller;

import com.rsi.neo4j.service.TopicalService;
import com.rsi.util.JsonResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * http://localhost:5700/rest/database/coder/get?name=鱼暖暖
 */
@RestController
@RequestMapping("/rest/database/topical") //restful风格的api接口
public class TopicalController {

    @Autowired
    TopicalService topicalService;

//    @RequestMapping("/get")
//    public Coder GetCoderByName(@RequestParam(value="name") String name){
//        return coderRepositiory.findByName(name);
//    }

    @PostMapping("/save")
    public JsonResp createQuoteNode(@RequestBody String stdno) throws Exception{
        boolean result = topicalService.createQuoteNode();
        if(result) {
            return new JsonResp(200,"节点创建成功");
        }
        return new JsonResp(500,"节点创建失败！");
    }
}
