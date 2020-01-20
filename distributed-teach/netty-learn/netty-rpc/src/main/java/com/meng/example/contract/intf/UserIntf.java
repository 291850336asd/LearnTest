package com.meng.example.contract.intf;

import com.meng.example.contract.model.Ad;
import com.meng.example.contract.model.UserInfo;

import java.util.List;

public interface UserIntf {

    public List<UserInfo> findById(int userId);

    public UserInfo find(int userId);

    public UserInfo[] findByIds(int[] userId);

    public List<Ad> findByUserinfos(List<UserInfo> userInfos);

}
