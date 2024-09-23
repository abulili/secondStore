package com.atguigu.mybatis_plus_demo;

import com.atguigu.mybatis_plus_demo.entity.Car;
import com.atguigu.mybatis_plus_demo.mapper.CarMapper;
import com.atguigu.mybatis_plus_demo.service.CarService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.security.Key;
import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.List;
import java.util.Random;

@SpringBootTest
public class testa {
    @Resource
    private CarMapper carMapper;
    @Autowired
    private CarService carService;
    @Test
    void testDB() {
//    jwt ogHyKS2vsRAdZdPdGfFvVbsNNalPzyoP46cpZe8YRZ4=
//        Instant now = Instant.now();
        Date now = new Date(System.currentTimeMillis() + 60000); // 当前时间 + 1分钟
        byte[] secrect = Base64.getDecoder().decode("ogHyKS2vsRAdZdPdGfFvVbsNNalPzyoP46cpZe8YRZ4=");

        // 创建JWT
        String jwt = Jwts.builder()
                .setSubject("a")
                .setAudience("bbb")
                .setIssuedAt(now)
                .setExpiration(now) // 设置过期时间
                .signWith(Keys.hmacShaKeyFor(secrect)) // 签名密钥
                .compact();

        // 解析JWT
        Claims claims = Jwts.parser()
                .setSigningKey(Keys.hmacShaKeyFor(secrect))
                .parseClaimsJws(jwt)
                .getBody();
        System.out.println(jwt);
        Jws<Claims> result = Jwts.parser()
                .requireAudience("bbb")
                .setAllowedClockSkewSeconds(60005)
                .setSigningKey(Keys.hmacShaKeyFor(secrect))
                .parseClaimsJws(jwt);
//        header={alg=HS256},body={sub=a, aud=bbb, iat=1709993320, exp=1709993320},signature=fFGIFMjNtyJ_mKGLwzgYJUlHJ745K3U5N8tm298ppp0
        System.out.println(result);
//        Car car = new Car();
//        car.setUid(3);
//        car.setSid(2);
//        car.setSNum(9);
//        car.setIsDelete('0');
//        int result = carMapper.updateById(car);
        /**
         * https://www.oz6.cn/articles/55
         * ok
         */
//        List<Car> cars = carMapper.selectList(null);
//        cars.forEach(System.out::println);
        /**
         * ok
         */
//        Car car = new Car();
//        car.setIsDelete('0');
//        car.setSid(1);
//        car.setSNum(1);
//        int result = carMapper.insert(car);
//        System.out.println(result > 0 ? "添加成功！" : "添加失败！");
        /**
         * ok
         */
//        Map<String,Object> map = new HashMap<>();
//        map.put("uid",1);
//        int result = carMapper.deleteByMap(map);

        /**
         * ok  但是主键名最好重新弄为
         * Long id,这里已经改了数据库的名字
         */
//        Car car = new Car();
//        car.setSid(1);
//        car.setUid(1);
//        car.setSNum(1);
//        car.setIsDelete('0');
//        int res = carMapper.updateById(car);
        /**
         * ok
         */
//        Car car = carMapper.selectById(1);
//        System.out.println(car);
        /**
         * ok 我也不知道改了什么，能用了
         */
//        long count = carService.count();
//        System.out.println("总记录数：" + count);

        /**
         * ok 必须手动指定主键值 查了数量之后接着就行了
         */
//        List<Car> list = new ArrayList<>();
//        for (int i = 3; i <= 10; i++) {
//            Car car = new Car();
//            car.setSNum(1);
//            car.setIsDelete('0');
//            car.setSid(2);
//            list.add(car);
//        }
//        boolean b = carService.saveBatch(list);
//        System.out.println(b ? "添加成功！" : "添加失败！");

//        Car car = carService.getById(1);
//        System.out.println(car);
    }

}
