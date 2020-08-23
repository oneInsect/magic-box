package com.simplecode.filemgt.service;

import com.simplecode.filemgt.entity.Accesskey;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * categories 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-08-17
 */
public interface AccesskeyService extends IService<Accesskey> {

    List<Accesskey> getAccessKeyAll();

    Accesskey genAK();

    Boolean deleteAKById(String akId);

    List<Accesskey> getAKByPage(String current, String limit);
}
