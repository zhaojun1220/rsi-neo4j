package com.rsi.neo4j.service.impl;

import com.rsi.mysql.mapper.TopicalMapper;
import com.rsi.neo4j.nodeentity.Topical;
import com.rsi.neo4j.repository.TopicalRepository;
import com.rsi.neo4j.service.TopicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TopicalServiceImpl implements TopicalService {

    @Autowired
    private TopicalMapper topicalMapper;

    @Autowired
    private TopicalRepository topicalRepository;

    /**
     * 创建引用节点
     */
    public boolean createQuoteNode() {

        boolean success = true;
        try {
            // 取得应用题录信息
            Map<String, Object> params = new HashMap<>();
            params.put("stdno", 2848);
            List<Map<String, Object>> topicals = topicalMapper.findQuotes(params);

            // 先删除Topical节点，再创建
            topicalRepository.deleteAll();

            topicals.stream().forEach(topical -> {
                String stdno = topical.get("std_id") + "";

                // 创建题录节点
                topicalRepository.save(new Topical(stdno));

                // 建立引用关系
                Topical topicalNode = topicalRepository.findByStdNo(stdno);

                // 创建引用节点
                Object quote = topical.get("quote_no");
                if (quote != null) {
                    String[] quotes = quote.toString().split(",");
                    for (String str : quotes) {
                        Topical quoteTopical = new Topical(str);
                        // 保存引用节点
                        topicalRepository.save(quoteTopical);
                        // 创建题录到引用的关系
                        topicalNode.quoteWith(quoteTopical);
                    }
                }
                topicalRepository.save(topicalNode);
            });
        }catch (Exception e) {
            success = false;
            e.printStackTrace();
        }

        return success;
    }
}
