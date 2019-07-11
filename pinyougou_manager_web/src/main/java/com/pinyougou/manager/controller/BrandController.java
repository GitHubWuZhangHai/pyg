package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.sellergoods.service.BrandService;
import entity.PageResult;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Steven
 * @version 1.0
 * @description com.pinyougou.manager.controller
 * @date 2019-7-9
 */
@RestController
@RequestMapping("brand")
public class BrandController {

    @Reference
    private BrandService brandService;

    @RequestMapping("findAll")
    public List<TbBrand> findAll(){
        return brandService.findAll();
    }

    @RequestMapping("findPage")
    public PageResult<TbBrand> findPage(Integer pageNo, Integer pageSize,@RequestBody TbBrand brand){
        return brandService.findPage(pageNo, pageSize,brand);
    }

    @RequestMapping(value = "add")
    public Result add(@RequestBody TbBrand brand){
        try {
            brandService.add(brand);
            return new Result(true,"新增品牌成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(false,"新增品牌失败");
    }

    @RequestMapping("getById")
    public TbBrand getById(Long id) {
        return brandService.getById(id);
    }

    @RequestMapping("update")
    public Result update(@RequestBody TbBrand brand) {
        try {
            brandService.update(brand);
            return new Result(true, "修改品牌成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(false, "修改品牌失败");
    }

    @RequestMapping("delete")
    public Result delete(Long[] ids){
        try {
            brandService.delete(ids);
            return new Result(true, "删除品牌成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(false, "删除品牌失败");
    }
}
