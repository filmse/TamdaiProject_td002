package com.tamdai.model.pointZNet.Dao;

import com.tamdai.model.pointZNet.entity.PUserEntity;
import com.tamdai.model.pointZNet.repository.PUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PDaoImpl implements PDao {

    @Autowired
    PUserRepository pUserRepository;

    @Override
    public PUserEntity pUserEntity(PUserEntity pUserEntity) {
        return pUserRepository.save(pUserEntity);
    }

    @Override
    public List<PUserEntity> oderList() {
        return pUserRepository.findAll();
    }

    @Override
    public List<PUserEntity> oderListId(String id) {
        return pUserRepository.findPUserEntityByUserId(id);
    }

    @Override
    public PUserEntity pService(Long id) {
        return pUserRepository.findPUserEntityById(id);
    }

    @Override
    public PUserEntity updatePayment(PUserEntity pUserEntity) {
        return pUserRepository.save(pUserEntity);
    }

    @Override
    public List<PUserEntity> getOrdersByStatus(String status) {
        return pUserRepository.findPUserEntityByStatus(status);
    }
}
