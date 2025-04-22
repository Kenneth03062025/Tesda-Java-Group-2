package com.app.controller;

import com.app.model.Cashering;
import com.app.model.Response;
import com.app.model.Stocks;
import com.app.model.dto.CasheringItemResponse;
import com.app.service.implementations.CasheringServiceImpl;

public class CasheringController {

    static CasheringServiceImpl cImpl = new CasheringServiceImpl();

    public static Response<Cashering> createCashering(){
        Response<Cashering> res;
        res = cImpl.createCashering();
//        System.out.println(res.getStatus());
        return res;
    }

    public static Response<Cashering> openCashering(String casheringNumber){
        Response<Cashering> res;
        res = cImpl.openCashering(casheringNumber);
        return res;
    }

    public static Response<Cashering> closeCashering(String casheringNumber){
        Response<Cashering> res;
        res = cImpl.closeCashering(casheringNumber);
        return res;
    }

    public static CasheringItemResponse getCreatedCashering(){
        CasheringItemResponse res = cImpl.getOpenCashering();
        System.out.println(res.getCashering().getOperationNumber());
        return res;
    }


}
