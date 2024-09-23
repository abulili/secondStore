package com.atguigu.mybatis_plus_demo.controller;

import com.atguigu.mybatis_plus_demo.entity.Comment;
import com.atguigu.mybatis_plus_demo.entity.Report;
import com.atguigu.mybatis_plus_demo.entity.ReportStoreCommentDTO;
import com.atguigu.mybatis_plus_demo.entity.Store;
import com.atguigu.mybatis_plus_demo.mapper.CommentMapper;
import com.atguigu.mybatis_plus_demo.mapper.ReportMapper;
import com.atguigu.mybatis_plus_demo.mapper.StarMapper;
import com.atguigu.mybatis_plus_demo.mapper.StoreMapper;
import com.atguigu.mybatis_plus_demo.service.ReportService;
import com.atguigu.mybatis_plus_demo.service.StarService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

@RestController
@RequestMapping("/reports")
public class ReportController {
    @Autowired
    private ReportService reportService;
    @Resource
    private ReportMapper reportMapper;
    @Resource
    private StoreMapper storeMapper;
    @Resource
    private CommentMapper commentMapper;

    //    查
    @PostMapping("/list")
    @ResponseBody
    public List<ReportStoreCommentDTO> getList() {
//        List<Report> reports = reportMapper.selectList(null);
//        Map<String,Object> map = new HashMap<>();
//        map.put("isdelete", '0');
//        return  reportMapper.selectByMap(map);
        List<Report> reports = reportMapper.selectList(new QueryWrapper<Report>().eq("isdeal",'0'));
        List<ReportStoreCommentDTO> result = new ArrayList<>();
        for(Report report: reports) {
            Store store = storeMapper.selectById(report.getSid());
            Comment comment = commentMapper.selectById(report.getCid());
            ReportStoreCommentDTO dto = new ReportStoreCommentDTO();
            dto.setReport(report);
            dto.setComment(comment);
            dto.setStore(store);
            result.add(dto);
        }
        return result;
    }

    //    增
    @PostMapping("/add")
    @ResponseBody
    public int add(@RequestBody Report data) {
        System.out.println(data);
        Report rt = new Report();
//        前端一定要置null
        rt.setIsdeal((char)data.getIsdeal());
        rt.setCid((int)data.getCid());
        rt.setSid((int)data.getSid());
        rt.setIsdeal(data.getIsdeal());
        int result = reportMapper.insert(rt);
        return result;
    }

    //    删
    @PostMapping("/del")
    @ResponseBody
    public int del(@RequestBody Report data) {
        System.out.println(data);
        Report rt = new Report();
        rt.setRid((int)data.getRid());
        rt.setIsdeal((char)data.getIsdeal());
        rt.setCid((int)data.getCid());
        rt.setSid((int)data.getSid());
        int result = reportMapper.updateById(rt);
        return result;
    }
}

