package org.example.controller;

import ch.qos.logback.core.util.ReentryGuard;
import lombok.extern.slf4j.Slf4j;
import org.example.anno.Log;
import org.example.pojo.Dept;
import org.example.pojo.Result;
import org.example.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {

    //private static final Logger log = LoggerFactory.getLogger(DeptController.class);

    @Autowired
    private DeptService deptService;

    //查询所有部门数据
    //@RequestMapping(value="/depts",method = RequestMethod.GET)
    @GetMapping
    public Result getDepts() {
        //System.out.println("查询全部部门数据");
        log.info("查询全部部门数据");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    //删除部门
    //方式一：使用HttpServletRequest获取参数
    /*@DeleteMapping("/depts")
    public Result delete(HttpServletRequest request) {
        String idStr = request.getParameter("id");
        int id = Integer.parseInt(idStr);
        System.out.println("删除部门数据：" + id);
        return Result.success();
    }*/
    //方式二：使用@RequestParam获取参数
    //注意：一旦声明了@RequestParam注解，前端必须传递该参数，否则会报错（默认required 为 true）
    /*@DeleteMapping("/depts")
    public Result delete(@RequestParam(value = "id", required = false) Integer id) {
        System.out.println("删除部门数据：" + id);
        return Result.success();
    }*/
    //方式三：省略@RequestParam注解（前端传递的请求参数名与服务器端方法参数名一致）
    @DeleteMapping
    @Log
    public Result delete(Integer id) {
        //System.out.println("删除部门数据：" + id);
        //log.info("删除部门数据：" + id);
        log.info("删除部门数据：{}", id);
        deptService.deleteById(id);
        return Result.success();
    }

    //添加部门
    @PostMapping
    @Log
    public Result add(@RequestBody Dept dept) {
        //System.out.println("添加部门数据：" + dept);
        log.info("添加部门数据：{}", dept);
        deptService.addDept(dept);
        return Result.success();
    }

    //根据id查询部门
    //@PathVariable注解：将路径变量的值绑定到方法参数上
    //如果路径变量名与方法参数名一致，@PathVariable注解可以省略
    @GetMapping("/{id}")
    //public Result findById(@PathVariable("id") Integer id) {
    public Result findById(@PathVariable Integer id) {
        //System.out.println("根据id查询部门数据：" + id);
        log.info("根据id查询部门数据：{}", id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    //修改部门
    @PutMapping
    @Log
    public Result update(@RequestBody Dept dept) {
        //System.out.println("修改部门数据：" + dept);
        log.info("修改部门数据：{}", dept);
        deptService.update(dept);
        return Result.success();
    }
}
