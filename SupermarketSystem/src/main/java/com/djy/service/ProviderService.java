package com.djy.service;


import com.djy.pojo.Provider;

import java.util.HashMap;
import java.util.List;

public interface ProviderService {
    /**
     * 增加供应商
     *
     * @param provider
     * @return
     */
    public boolean add(Provider provider);


    /**
     * 通过供应商名称、编码获取供应商列表-模糊查询-providerList
     *
     * @param proName
     * @return
     */
    public HashMap<String,Object> getProviderList(String proName, String proCode , int pageIndex, int pageSize);

    /**
     * 通过proId删除Provider
     *
     * @param delId
     * @return
     */
    public int deleteProviderById(int delId);


    /**
     * 通过proId获取Provider
     *
     * @param id
     * @return
     */
    public Provider getProviderById(int id);

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    public boolean modifyById(Provider provider);

}
