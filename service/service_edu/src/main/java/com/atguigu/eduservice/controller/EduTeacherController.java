package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.entity.query.TeacherQuery;
import com.atguigu.eduservice.service.EduTeacherService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-02-08
 */
@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
@CrossOrigin//跨域问题
public class EduTeacherController {
    //查询讲师表中的所有数据
    @Autowired
    private EduTeacherService teacherService;
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAll")
    public R findAllTeacher() {
        //service的方法查询所有操作
        List<EduTeacher> list = teacherService.list(null);
        return R.ok().data("items",list);
    }

    //删除讲师的功能
    @ApiOperation(value = "根据id删除一个讲师")
    @DeleteMapping("/{id}")
    public R removeTeacher(
            @ApiParam(name = "id",value = "讲师ID",required = true)
            @PathVariable("id")String id) {
        boolean flag = teacherService.removeById(id);
        if (flag){
            return R.ok();
        }else {
            return R.error();
        }
    }
    //分页查询
    @ApiOperation(value = "分页查询")
    @GetMapping("/pageTeacher/{current}/{limit}")
    public R pageListTeacher(@PathVariable("current")long current,@PathVariable("limit")long limit){
        Page<EduTeacher> teacherPage=new Page<>(current,limit);

        try {
            int i=10/0;
        }catch (Exception e){
            throw new GuliException(20001,"出现自定义异常");
        }
        teacherService.page(teacherPage,null);
        long total = teacherPage.getTotal();
        List<EduTeacher> records = teacherPage.getRecords();
        return R.ok().data("total",total).data("records",records);
    }
    //条件查询
    @ApiOperation(value = "条件查询讲师")
    @PostMapping("/pageTeacherCondition/{current}/{limit}")
    public R pageListTeacherCondition(@PathVariable("current")long current, @PathVariable("limit")long limit,@RequestBody(required = false) TeacherQuery teacherQuery){
        Page<EduTeacher> teacherPage=new Page<>(current,limit);
        QueryWrapper<EduTeacher> wrapper=new QueryWrapper<>();
        String name=teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        if (!StringUtils.isEmpty(name)){
            wrapper.like("name",name);
        }
        if (!StringUtils.isEmpty(level)){
            wrapper.eq("level",level);
        }
        if (!StringUtils.isEmpty(begin)){
            wrapper.ge("gmt_create",begin);
        }
        if (!StringUtils.isEmpty(end)){
            wrapper.le("gmt_modified",end);
        }
        //排序
        wrapper.orderByDesc("gmt_create");
        teacherService.page(teacherPage,wrapper);
        long total = teacherPage.getTotal();
        List<EduTeacher> records = teacherPage.getRecords();
        return R.ok().data("total",total).data("records",records);
    }
    @ApiOperation(value = "添加讲师")
    @PostMapping("/addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean flag=teacherService.save(eduTeacher);
        if (flag){
            return R.ok();
        }else {
            return R.error();
        }
    }
    @ApiOperation(value = "通过id查询讲师")
    @GetMapping("/selectByTeacherId/{id}")
    public R delete(@ApiParam(name = "id",value = "讲师ID",required = true)
            @PathVariable String id){
        EduTeacher teacher=teacherService.getById(id);
        return R.ok().data("itme",teacher);
    }

    @ApiOperation(value = "修改讲师")
    @PostMapping("/update")
    public R update(@RequestBody EduTeacher eduTeacher){
       boolean flag = teacherService.updateById(eduTeacher);
        if (flag){
            return R.ok();
        }else {
            return R.error();
        }
    }
}

