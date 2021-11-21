package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.mapper.StorageMapper;
import com.example.demo.model.Storage;
import com.example.demo.service.StorageService;
import org.springframework.stereotype.Service;

/**
 *  库存服务实现类
 */
@Service
public class StorageServiceImpl extends ServiceImpl<StorageMapper, Storage> implements StorageService {


}
