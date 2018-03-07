package com.rsi.neo4j.controller;

import com.rsi.neo4j.nodeentity.Coder;
import com.rsi.neo4j.repository.CoderRepositiory;
import com.rsi.util.JsonResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * http://localhost:5700/rest/database/coder/get?name=鱼暖暖
 */
@RestController
@RequestMapping("/rest/database/coder") //restful风格的api接口
public class CoderController {

    @Autowired
    CoderRepositiory coderRepositiory;

    @RequestMapping("/get")
    public Coder GetCoderByName(@RequestParam(value="name") String name){
        return coderRepositiory.findByName(name);
    }

    @PostMapping("/save")
    public JsonResp Create(@RequestBody Coder coder) throws Exception{
        Coder result = coderRepositiory.save(coder);
        if(result != null) {
            return new JsonResp(200,result.getName() + "节点创建成功");
        }
        return new JsonResp(500,coder.getName()+"节点创建失败！");
    }
}
