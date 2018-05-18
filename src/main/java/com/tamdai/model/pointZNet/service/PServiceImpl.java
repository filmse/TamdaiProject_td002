package com.tamdai.model.pointZNet.service;

import com.tamdai.model.pointZNet.Dao.PDao;
import com.tamdai.model.pointZNet.entity.PUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PServiceImpl implements PService {

    @Autowired
    PDao pDao;

    @Override
    public PUserEntity pUserOrderCreate(PUserEntity pUserEntity) {
        return pDao.pUserEntity(pUserEntity);
    }

    @Override
    public List<PUserEntity> oderList() {
        return pDao.oderList();
    }

    @Override
    public List<PUserEntity> oderListId(String id) {
        return pDao.oderListId(id);
    }

    @Override
    public PUserEntity getPayment(Long id) {
        return pDao.pService(id);
    }

    @Override
    public PUserEntity updatePayment(PUserEntity pUserEntity) {
        return pDao.updatePayment(pUserEntity);
    }

    @Override
    public List<PUserEntity> getOrdersByStatus(String status) {
        return pDao.getOrdersByStatus(status);
    }

    @Override
    public PUserEntity confirmBankStatement(PUserEntity pUserEntity) {
        return pDao.updatePayment(pUserEntity);
    }
}
