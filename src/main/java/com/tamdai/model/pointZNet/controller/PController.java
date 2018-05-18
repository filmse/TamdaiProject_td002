package com.tamdai.model.pointZNet.controller;

import com.tamdai.model.pointZNet.entity.PUserEntity;
import com.tamdai.model.pointZNet.service.PService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/")
public class PController {

    @Autowired
    PService pService;

    @RequestMapping(value = "pUser/order", method = RequestMethod.POST)
    public PUserEntity pUserOrderCreate(@RequestBody PUserEntity pUserEntity, BindingResult bindingResult) {
        return pService.pUserOrderCreate(pUserEntity);
    }

    @RequestMapping(value = "oderList", method = RequestMethod.GET)
    public List<PUserEntity> oderList() {
        return pService.oderList();
    }

    @RequestMapping(value = "oderListById", method = RequestMethod.GET)
    public List<PUserEntity> oderListId(@RequestParam("userId") String id) {
        return pService.oderListId(id);
    }

    @RequestMapping(value = "getPayment/{id}", method = RequestMethod.GET)
    public PUserEntity getPayment(@PathVariable("id") Long id) {
        return pService.getPayment(id);
    }

    @RequestMapping(value = "getOrdersByStatus", method = RequestMethod.GET)
    public List<PUserEntity> getOrdersByStatus(@RequestParam("status") String status) {
        return pService.getOrdersByStatus(status);
    }

    @RequestMapping(value = "updatePayment/{id}", method = RequestMethod.PUT)
    public PUserEntity updatePayment(@PathVariable("id") Long id,
                                     @RequestParam("money") String money,
                                     @RequestParam("point") String point,
                                     @RequestParam("statementDate") String statementDate,
                                     @RequestParam("statementTime") String statementTime,
                                     @RequestParam("bankName") String bankName) {

        PUserEntity pUserEntity = pService.getPayment(id);
        String status2 = "2";

        pUserEntity.setMoney(money);
        pUserEntity.setPoint(point);
        pUserEntity.setStatus(status2);
        pUserEntity.setStatementDate(statementDate);
        pUserEntity.setStatementTime(statementTime);
        pUserEntity.setBankName(bankName);

        return pService.updatePayment(pUserEntity);
    }

    @RequestMapping(value = "confirmBankStatement/{id}", method = RequestMethod.PUT)
    public PUserEntity confirmBankStatement(@PathVariable("id") Long id,
                                            @RequestParam("userId") String userId,
                                            @RequestParam("point") String point,
                                            @RequestParam("status") String status) {

        PUserEntity pUserEntity = pService.getPayment(id);

        pUserEntity.setPoint(point);
        pUserEntity.setStatus(status);

        return pService.confirmBankStatement(pUserEntity);
    }

}
