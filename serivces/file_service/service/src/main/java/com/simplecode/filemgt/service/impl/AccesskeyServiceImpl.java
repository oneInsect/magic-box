package com.simplecode.filemgt.service.impl;

import com.simplecode.filemgt.entity.Accesskey;
import com.simplecode.filemgt.mapper.AccesskeyMapper;
import com.simplecode.filemgt.service.AccesskeyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * categories 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-08-17
 */
@Service
public class AccesskeyServiceImpl extends ServiceImpl<AccesskeyMapper, Accesskey> implements AccesskeyService {

    @Override
    public List<Accesskey> getAccessKeyAll() {
        return null;
    }

    @Override
    public Accesskey genAK() {
        return null;
    }

    @Override
    public Boolean deleteAKById(String akId) {
        return null;
    }

    @Override
    public List<Accesskey> getAKByPage(String current, String limit) {
        return null;
    }
}
