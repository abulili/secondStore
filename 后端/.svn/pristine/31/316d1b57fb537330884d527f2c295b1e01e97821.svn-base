package com.atguigu.mybatis_plus_demo.controller;

import com.atguigu.mybatis_plus_demo.entity.Car;
import com.atguigu.mybatis_plus_demo.entity.CarStoreDTO;
import com.atguigu.mybatis_plus_demo.entity.Store;
import com.atguigu.mybatis_plus_demo.mapper.CarMapper;
import com.atguigu.mybatis_plus_demo.mapper.CarStoreMapper;
import com.atguigu.mybatis_plus_demo.mapper.StoreMapper;
import com.atguigu.mybatis_plus_demo.service.CarService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/car")
public class CarController{
    @Autowired
    private CarService carService;
    @Resource
    private CarMapper carMapper;

    private StoreMapper storeMapper;
    @Resource
    private CarStoreMapper carStoreMapper;
//    @GetMapping("/list")
//    @ResponseBody
//    public List<Car> index() {
//        return carService.list();
//    }
//    查询
    @PostMapping("/listId")
    @ResponseBody
    public List<CarStoreDTO> getid(@RequestBody Map<String, Object> data) {
//    public List<Car> getid(@RequestBody Map<String, Object> data) {
//        System.out.println(car.getUid());
//        return carService.getById(car.getUid());
//        return null;
//        Map<String,Object> map = new HashMap<>();
//        map.put("sid",sid);
//        return carMapper.selectByMap(map);
        int uid = (int) data.get("uid");
        char isdelete = '0';
//        Map<String,Object> map = new HashMap<>();
//        map.put("uid", uid);
//        map.put("isdelete",isdelete);
//        List<Car> cars = carMapper.selectByMap(map);
//        // 获取所有关联的 sid
//        List<Integer> sids = cars.stream().map(Car::getSid).collect(Collectors.toList());
//        QueryWrapper<Store> storeQueryWrapper = new QueryWrapper<>();
//        storeQueryWrapper.in("sid", sids);
////还要再创一个
//        List<Store> stores = storeMapper.selectList(storeQueryWrapper);
//        return carMapper.selectByMap(map);

//        return stores;//
        return carStoreMapper.selectCarStoreDTO(uid);
    }
//    添加
    @PostMapping("/addCar")
    @ResponseBody
    public int addCar(@RequestBody Car data) {
        System.out.println(data);
        Car car = new Car();
        car.setUid(data.getUid());
        car.setSid(data.getSid());
        car.setSnum(data.getSnum());
        car.setIsdelete('0');
        int result = carMapper.insert(car);
        return result;
    }

//    @PostMapping("/addCar")
//    @ResponseBody
//    public int addCar(@RequestParam("sid") int sid, @RequestParam("sNum") Integer sNum) {
//            System.out.println("sid: " + sid + ", sNum: " + sNum );
//            Car car = new Car();
//            car.setSid(sid);
//            car.setSnum(sNum);
//            car.setIsdelete('0');
//            int result = carMapper.insert(car);
//            return result;
//    }

//    修改
    @PostMapping("/updateCar")
    @ResponseBody
    public int updateCarNum(@RequestBody Car data) {
        System.out.println(data);
        Car car = new Car();
        car.setCid(data.getCid());
        car.setUid(data.getUid());
        car.setSid(data.getSid());
        car.setSnum(data.getSnum());
        car.setIsdelete(data.getIsdelete());
        int result = carMapper.updateById(car);
        return result;
    }
//    删除
    @PostMapping("/delCar")
    @ResponseBody
    public int updateCarDel(@RequestBody Car data) {
        Car car = new Car();
        car.setCid(data.getCid());
//        car.setUid(data.getUid());
//        car.setSid(data.getSid());
//        car.setSnum(data.getSnum());
        car.setIsdelete('1');
        int result = carMapper.updateById(car);
        return result;
    }
}
