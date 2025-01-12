package com.atguigu.mybatis_plus_demo.controller;

import com.atguigu.mybatis_plus_demo.entity.Car;
import com.atguigu.mybatis_plus_demo.entity.Store;
import com.atguigu.mybatis_plus_demo.mapper.StoreMapper;
import com.atguigu.mybatis_plus_demo.service.StoreService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;

@RestController
@RequestMapping("/store")
public class StoreController {
    @Autowired
    private StoreService storeService;
    @Resource
    private StoreMapper storeMapper;
//    搜索框
    @PostMapping("/serach")
    @ResponseBody
    public List<Store> getSerach(@RequestBody Map<String, Object> data) {
//        没搜出来
        String keywords = (String) data.get("serach");
        QueryWrapper<Store> queryWrapper = new QueryWrapper<>();
//        queryWrapper.like("sTitle", keywords);
        queryWrapper.or(i -> i.like("sTitle", keywords));
        queryWrapper.or(i -> i.like("sContent", keywords));
        List<Store> result = storeMapper.selectList(queryWrapper);
        return result;
    }
//    查
    @PostMapping("/listId")
    @ResponseBody
    public List<Store> getId(@RequestBody Map<String, Object> data) {
        int sid = (int) data.get("sid");
        Map<String,Object> map = new HashMap<>();
        map.put("sid", sid);
        return storeMapper.selectByMap(map);
    }
//    查某个用户所有的商品
    @PostMapping("/listUser")
    @ResponseBody
    public List<Store> getUser(@RequestBody Map<String, Object> data) {
        int suid = (int) data.get("suid");
        Map<String,Object> map = new HashMap<>();
        map.put("suid", suid);
        return storeMapper.selectByMap(map);
    }
//
    @PostMapping("/list")
    @ResponseBody
    public List<Store> getList() {
        List<Store> stores = storeMapper.selectList(new QueryWrapper<Store>().eq("isdelete",'0'));
        return stores;
    }
//    默认地点
    @PostMapping("/getLDB")
    @ResponseBody
    public List<Store> getLDB() {
        Map<String,Object> map = new HashMap<>();
        map.put("sid", 0);
        return storeMapper.selectByMap(map);
    }
//    范围
    @PostMapping("/listPrice")
    @ResponseBody
    public List<Store> getPrive(@RequestBody Map<String, Object> data) {
        String l = (String)data.get("l");
        String r = (String)data.get("r");
        if (l == null || l.equals("undefined") || l.isEmpty()) {
            l = "0";
        }
        QueryWrapper<Store> queryWrapper = new QueryWrapper<Store>();
//        queryWrapper.between("sprice",l,r);
        queryWrapper.between("sPrice", new BigDecimal(l), new BigDecimal(r));
        queryWrapper.eq("isdelete",'0');
        List<Store> stores = storeMapper.selectList(queryWrapper);
        return stores;
    }
//    增
    @PostMapping("/addStore")
    @ResponseBody
    public int addStore(@RequestBody Store data) {
        System.out.println(data);
        Store st = new Store();
        st.setSpic(data.getSpic());
        st.setStitle(data.getStitle());
        st.setScontent(data.getScontent());
        st.setSnum(data.getSnum());
        st.setSuid(data.getSuid());
        Timestamp now = new Timestamp(System.currentTimeMillis());
        st.setStime(now);
        st.setSpic(st.getSpic());
        st.setSprice(data.getSprice());
        st.setSlng(data.getSlng());
        st.setSlat(data.getSlat());
        st.setIsdelete('0');
        int result = storeMapper.insert(st);
        if(result == 1) {
//            查当前sid
            return st.getSid();
        }
        return 0;
    }

//    改
    @PostMapping("/updateStore")
    @ResponseBody
    public int updateStore(@RequestBody Store data) {
        System.out.println(data);
        Store st = new Store();
        st.setSid(data.getSid());
        st.setStitle(data.getStitle());
        st.setScontent(data.getScontent());
        st.setSnum(data.getSnum());
        st.setSuid(data.getSuid());
        Timestamp now = new Timestamp(System.currentTimeMillis());
        st.setStime(now);
        st.setSprice(data.getSprice());
        st.setSlng(data.getSlng());
        st.setSlat(data.getSlat());
        st.setIsdelete((char)data.getIsdelete());
        int result = storeMapper.updateById(st);
        return result;
    }
//  删除
    @PostMapping("/delStore")
    @ResponseBody
    public int delStore(@RequestBody Store data) {
        System.out.println(data);
        Store st = new Store();
        st.setSid(data.getSid());
        st.setStitle(data.getStitle());
        st.setScontent(data.getScontent());
        st.setSnum(data.getSnum());
        st.setSuid(data.getSuid());
        Timestamp now = new Timestamp(System.currentTimeMillis());
        st.setStime(now);
        st.setSprice(data.getSprice());
        st.setSlng(data.getSlng());
        st.setSlat(data.getSlat());
        st.setIsdelete('1');
        int result = storeMapper.updateById(st);
        return result;
    }
    //    上传图片
// 在 Spring Boot 中处理上传并保存到数据库的示例
    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());

            // 将文件内容转换为 Base64 编码
            String base64Image = Base64.getEncoder().encodeToString(file.getBytes());

            // 直接返回 Base64 编码的字符串
            return ResponseEntity.ok(base64Image);
//            // 获取文件名
//            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//            // 将文件内容转换为 Base64 编码
//            String base64Image = Base64.getEncoder().encodeToString(file.getBytes());
//
//            byte[] inputData = base64Image.getBytes("UTF-8");
//
//            // 使用 Deflater 进行压缩
////            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
////            Deflater deflater = new Deflater();
////            DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(outputStream, deflater);
////            deflaterOutputStream.write(inputData);
////            deflaterOutputStream.close();
//
//            // 获取压缩后的数据
////            byte[] compressedData = outputStream.toByteArray();
//
//            // 使用 Base64 编码
//            String base64EncodedData = java.util.Base64.getEncoder().encodeToString(inputData);
//
//            return ResponseEntity.ok(base64EncodedData);

        } catch (IOException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("文件上传失败");
        }
    }

}
