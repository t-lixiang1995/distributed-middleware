package com.pcitc.sharding.service.impl;

import com.pcitc.sharding.mapper.ProvinceMapper;
import com.pcitc.sharding.model.Province;
import com.pcitc.sharding.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProvinceServiceImpl implements ProvinceService {
    @Autowired
    private ProvinceMapper provinceMapper;

    @Override
    public void save(Province province) {
        provinceMapper.save(province);
    }
}
