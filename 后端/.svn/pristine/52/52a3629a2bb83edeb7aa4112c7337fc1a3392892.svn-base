package com.atguigu.mybatis_plus_demo.controller;

import com.atguigu.mybatis_plus_demo.entity.Store;
import com.atguigu.mybatis_plus_demo.entity.User;
import com.atguigu.mybatis_plus_demo.mapper.UserMapper;
import com.atguigu.mybatis_plus_demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UsreController {
    @Autowired
    private UserService userService;
    @Resource
    private UserMapper userMapper;

//    查
    @PostMapping("/listId")
    @ResponseBody
    public User getId(@RequestBody Map<String, Object> data) {
//        判断有没有这个人，用啥注册啊，直接账号密码吧暂时

//        取数据库里的盐值、真密码

//        通过手机号/邮箱/昵称进行登录  或者邮箱和手机号必须填一个（前端）
//        int uname = (int) data.get("uname");
//
////        解码
////        先要去查这个的盐值和密码是什么,数据库里存的密码和传过来的密码盐值对比
//        String pwd = (String) data.get("upwd");
//        String salt = UUID.randomUUID().toString().replace("-","");
//        String saltpwd = DigestUtils.md5DigestAsHex((salt + pwd).getBytes());
//        String finalPwd = salt + "$" + saltpwd;
//        if(StringUtils.hasLength(pwd) && StringUtils.hasLength(RealPwd) &&
//                finalPwd.length() == 65){
//            //1.得到盐值
//            String salt = RealPwd.split("$")[0];
//            //2.将明文密码和得到的盐值进行加密生成加密的密码
//            String confirmPassword = (String) data.get("upwd");
//            String salt = UUID.randomUUID().toString().replace("-","");
//            String saltpwd = DigestUtils.md5DigestAsHex((salt + pwd).getBytes());
//            //3.对比
//            if(confirmPassword.equals(RealPwd)) {
////                返回
//            }
//        }
//
//        Map<String,Object> map = new HashMap<>();
////        盐值怎么用来着？
////        map.put("sid", sid);
////        return userMapper.selectByMap(map);
        /**
         * "uname":"aaa",
         *  "upwd":"123"
         */
        String name = (String) data.get("uname");
        String inputPwd = (String) data.get("upwd");

        Map<String,Object> map = new HashMap<>();
        map.put("uname", name);
        List<User> res = userMapper.selectByMap(map);
        if(res.size() != 1) {
            return null;
        }
        User db = res.get(0);
        if(db.getIsdelete() == '1') {
            return null;
        }

        String dbPwd = db.getUpwd();
        String dbSalt = db.getSalt();
        String saltPassword = DigestUtils.md5DigestAsHex((dbSalt + inputPwd).getBytes());
        System.out.println(saltPassword);
        if(dbPwd.equals(saltPassword)) {
            return db;
        }
//        后期改成data.code == 200之类
        return null;
    }
//    查所有用户
    @PostMapping("/getAllUser")
    @ResponseBody
    public List<User> getAllUser() {
        Map<String,Object> map = new HashMap<>();
        map.put("isdelete", '0');
        return userMapper.selectByMap(map);
    }
//    查
    @PostMapping("/serachUid")
    @ResponseBody
    public List<User> serachUid(@RequestBody Map<String, Object> data) {
        int uid = (int)data.get("uid");
        Map<String,Object> map = new HashMap<>();
        map.put("uid", uid);
        return userMapper.selectByMap(map);
    }
    @PostMapping("/serachUser")
    @ResponseBody
    public List<User> serachUser(@RequestBody Map<String, Object> data) {
        String uname = (String)data.get("uname");
        Map<String,Object> map = new HashMap<>();
        map.put("uname", uname);
        return userMapper.selectByMap(map);
    }
    @PostMapping("/rePwd")
    @ResponseBody
    public Boolean rePwd(@RequestBody Map<String, Object> data) {
        String uname = (String)data.get("uname");
        String uphone = (String)data.get("uphone");
        String uemail = (String)data.get("uemail");
        Map<String,Object> map = new HashMap<>();
        map.put("uname", uname);
        if(uphone != "") {
            map.put("uphone", uphone);
        }
        if(uemail != "") {
            map.put("uemail", uemail);
        }
        List<User> users = userMapper.selectByMap(map);
        if(users.size() == 0) {
            return false;
        }
        User user = new User();
        User pre = users.get(0);
        user.setUid(pre.getUid());
        user.setUname(pre.getUname());
        user.setUavatar(pre.getUavatar());
        user.setUemail(pre.getUemail());
        user.setUphone(pre.getUphone());
        String pwd = "123456";
        String salt = UUID.randomUUID().toString().replace("-","");
        String saltpwd = DigestUtils.md5DigestAsHex((salt + pwd).getBytes());
        user.setSalt(salt);
        user.setUpwd(saltpwd);
        user.setIsreal(pre.getIsreal());
        user.setUkill(pre.getUkill());
        user.setUadmin(pre.getUadmin());
        user.setUinittime(pre.getUinittime());
        user.setIsdelete(pre.getIsdelete());
        int result = userMapper.updateById(user);
        if(result != 1) {
            return false;
        }
        return true;
    }
//    增
    @PostMapping("/addUser")
    @ResponseBody
    public int addUser(@RequestBody User data) {

        /**
         * "uname":"aaa",
         *  "upwd":"123"
         */
//        System.out.println(data);
        User user = new User();
        user.setUname(data.getUname());
        user.setUavatar(data.getUavatar());
        user.setUemail(data.getUemail());
        user.setUphone(data.getUphone());
        String pwd = data.getUpwd();
        String salt = UUID.randomUUID().toString().replace("-","");
        String saltpwd = DigestUtils.md5DigestAsHex((salt + pwd).getBytes());
        //
        System.out.println("RegPwd" + pwd);
        System.out.println("RegSalt" + salt);
        user.setSalt(salt);
        user.setUpwd(saltpwd);
        user.setIsreal('0');
        user.setUkill(0);
        user.setUadmin("普通用户");
        user.setUinittime(data.getUinittime());
        user.setIsdelete('0');
        int result = userMapper.insert(user);
        return result;
    }
//    改
    @PostMapping("/updateUser")
    @ResponseBody
    public int updateUser(@RequestBody User data) {
        User user = new User();
        Map<String,Object> map = new HashMap<>();
        map.put("uid", data.getUid());
        List<User> res = userMapper.selectByMap(map);
        User pre = res.get(0);
        user.setUid(pre.getUid());
        user.setUname(data.getUname());
        user.setUavatar(data.getUavatar());
        user.setUemail(data.getUemail());
        user.setUphone(data.getUphone());
        String pwd = data.getUpwd();//加密后
        if(!pre.getUpwd().equals(pwd)) {
            String salt = UUID.randomUUID().toString().replace("-","");
            String saltpwd = DigestUtils.md5DigestAsHex((salt + pwd).getBytes());
            user.setSalt(salt);
            user.setUpwd(saltpwd);
            System.out.println("修改密码");
        } else {
            user.setSalt(pre.getSalt());
            user.setUpwd(pre.getUpwd());
        }
        user.setIsreal(data.getIsreal());
        if(data.getUkill() == -1){
            user.setUkill(pre.getUkill() + 1);
        }
        else {
            user.setUkill(pre.getUkill());
        }
//        user.setUadmin(pre.getUadmin());
        user.setUadmin(data.getUadmin());
        user.setUinittime(data.getUinittime());
        user.setIsdelete(data.getIsdelete());
        int result = userMapper.updateById(user);
        return result;
    }
//    删
    @PostMapping("/delUser")
    @ResponseBody
    public int delUser(@RequestBody Map<String, Object> data) {
        User user = new User();
        Map<String,Object> map = new HashMap<>();
        map.put("uname", data.get("uname"));
        List<User> res = userMapper.selectByMap(map);
        if(res.size() != 0) {
            User pre = res.get(0);
            user.setUid(pre.getUid());
            user.setUname(pre.getUname());
            user.setUavatar(pre.getUavatar());
            user.setUemail(pre.getUemail());
            user.setUphone(pre.getUphone());
            user.setSalt(pre.getSalt());
            user.setUpwd(pre.getUpwd());
            user.setIsreal(pre.getIsreal());
            user.setUkill(pre.getUkill());
            user.setUadmin(pre.getUadmin());
            user.setUinittime(pre.getUinittime());
            user.setIsdelete('1');
            int result = userMapper.updateById(user);
            return result;
        }
        return 0;
    }
}
