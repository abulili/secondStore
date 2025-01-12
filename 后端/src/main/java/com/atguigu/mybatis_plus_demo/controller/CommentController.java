package com.atguigu.mybatis_plus_demo.controller;

import com.atguigu.mybatis_plus_demo.entity.*;
import com.atguigu.mybatis_plus_demo.mapper.CommentMapper;
import com.atguigu.mybatis_plus_demo.mapper.StoreMapper;
import com.atguigu.mybatis_plus_demo.mapper.UserMapper;
import com.atguigu.mybatis_plus_demo.service.CommentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Resource
    private CommentMapper commentMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private StoreMapper storeMapper;
//    查
    @PostMapping("/list")
    @ResponseBody
    public List<Comment> getList() {
        List<Comment> comments = commentMapper.selectList(new QueryWrapper<Comment>().eq("isdelete", '0'));
        return comments;
    }
//    卖家
    @PostMapping("/listUser")
    @ResponseBody
    public List<Comment> getUser(@RequestBody Map<String, Object> data) {
        int uid = (int)data.get("uid");
        Map<String,Object> map = new HashMap<>();
        map.put("uid", uid);
        return commentMapper.selectByMap(map);
    }
//    用户
    @PostMapping("/listSuid")
    @ResponseBody
    public List<CommentStoreDTO> getUser2(@RequestBody Map<String, Object> data) {
//        int suid = (int)data.get("suid");
//        Map<String,Object> map = new HashMap<>();
//        map.put("suid", suid);
//        map.put("isdelete", '0');
//        return commentMapper.selectByMap(map);
        int suid = (int)data.get("suid");
        List<Comment> comments = commentMapper.selectList(new QueryWrapper<Comment>().
                eq("suid", suid)
                .eq("isdelete", 0));

        List<CommentStoreDTO> result = new ArrayList<>();
        for (Comment comment : comments) {
            Store store = storeMapper.selectById(comment.getSid());

            CommentStoreDTO dto = new CommentStoreDTO();
            dto.setComment(comment);
            dto.setStore(store);

            result.add(dto);
        }
        return result;
    }
//    商品
    @PostMapping("/listStore")
    @ResponseBody
    public List<Comment> getStore(@RequestBody Map<String, Object> data) {
        int sid = (int)data.get("sid");
        Map<String,Object> map = new HashMap<>();
        map.put("sid", sid);
        return commentMapper.selectByMap(map);
    }
//    评论和商品一起查

    @PostMapping("/CommentUser")
    @ResponseBody
    public List<CommentUserDTO> getCommentsWithUserBySid(@RequestBody Map<String, Object> data) {
        int sid = (int) data.get("sid");
        List<Comment> comments = commentMapper.selectList(new QueryWrapper<Comment>().eq("sid", sid));

        List<CommentUserDTO> result = new ArrayList<>();
        for (Comment comment : comments) {
            User user = userMapper.selectById(comment.getSuid());

            CommentUserDTO dto = new CommentUserDTO();
            dto.setComment(comment);
            dto.setUser(user);

            result.add(dto);
        }
        return result;
    }
//    增

    /**
     *  "sid":1,
     *     "uid":1,
     *     "suid":3,
     *     "ccontent":"11111"
     * @param data
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public int addComment(@RequestBody Comment data) {
        System.out.println(data);
        Comment ct = new Comment();
        ct.setSid(data.getSid());
        ct.setUid(data.getUid());
        ct.setSuid(data.getSuid());
        ct.setCcontent(data.getCcontent());
        Timestamp now = new Timestamp(System.currentTimeMillis());
        ct.setCtime(now);
        ct.setIsdelete('0');
        int result = commentMapper.insert(ct);
        return result;
    }
    //    删
    @PostMapping("/del")
    @ResponseBody
    public int updateComment(@RequestBody Comment data) {
//        完全靠cid的值进行删除
        System.out.println(data);
        Comment ct = new Comment();
        ct.setCid(data.getCid());
        ct.setSid(data.getSid());
        ct.setUid(data.getUid());
        ct.setSuid(data.getSuid());
        ct.setCcontent(data.getCcontent());
        ct.setCtime(data.getCtime());
        ct.setIsdelete('1');
        int result = commentMapper.updateById(ct);
        return result;
    }
}
