package com.meng.example.server.imp;

import com.meng.example.contract.intf.UserIntf;
import com.meng.example.contract.model.Ad;
import com.meng.example.contract.model.UserInfo;

import java.util.ArrayList;
import java.util.List;

public class UserImp implements UserIntf {

    @Override
    public List<UserInfo> findById(int userId) {
        List<UserInfo> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(i + 1);
            userInfo.setUserName("老金" + (i + 1));
            userInfo.setSex(i + 1);
            List<Ad> ads = new ArrayList<>();
            Ad ad = new Ad();
            ad.setId(userInfo.getUserId());
            ad.setAddress(userInfo.getUserName());
            ads.add(ad);
            userInfo.setAds(ads);

            list.add(userInfo);
        }

        return list;
    }

    @Override
    public UserInfo find(int userId) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userId + 1);
        userInfo.setUserName("老金" + (userId + 1));
        userInfo.setSex(userId + 1);
        List<Ad> ads = new ArrayList<>();
        Ad ad = new Ad();
        ad.setId(userInfo.getUserId());
        ad.setAddress(userInfo.getUserName());
        ads.add(ad);
        userInfo.setAds(ads);

        return userInfo;
    }

    @Override
    public UserInfo[] findByIds(int[] userId) {
        List<UserInfo> list = findById(userId[0]);

        return list.toArray(new UserInfo[]{});
    }

    @Override
    public List<Ad> findByUserinfos(List<UserInfo> userInfos) {
        System.out.println("userInfos ： " + userInfos);
        List<Ad> ads = new ArrayList<>();
        Ad ad = new Ad();
        ad.setAddress("asdfs");
        ad.setId(2);
        ads.add(ad);

        return ads;
    }

}
