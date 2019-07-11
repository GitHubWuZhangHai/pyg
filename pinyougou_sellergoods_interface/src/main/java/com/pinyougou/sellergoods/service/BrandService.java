package com.pinyougou.sellergoods.service;

import com.pinyougou.pojo.TbBrand;
import entity.PageResult;

import java.util.List;

/**
 * 品牌业务逻辑接口
 * @author Steven
 *
 */
public interface BrandService {
   /**
    * 查询所有品牌
    * @return
    */
   public List<TbBrand> findAll();

   /**
    * 分页查询
    * @param pageNum
    * @param pageSize
    * @return
    */
   public PageResult<TbBrand> findPage(Integer pageNum,Integer pageSize,TbBrand brand);

   /**
    * 新增品牌
    * @param brand
    */
   public void add(TbBrand brand);

   /**
    * 跟据id查询数据
    * @param id
    * @return
    */
   public TbBrand getById(Long id);

   /**
    * 修改品牌
    * @param brand
    */
   public void update(TbBrand brand);

   /**
    * 批量删除
    * @param ids
    */
   public void delete(Long[] ids);

}
