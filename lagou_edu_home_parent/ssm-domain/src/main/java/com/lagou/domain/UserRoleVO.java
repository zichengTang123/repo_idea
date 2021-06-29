package com.lagou.domain;

import java.util.List;

public class UserRoleVO {

    /*"userId": 4,
	"roleIdList": [4,5,6]*/

    private Integer userId;
    private List<Integer> roleIdList;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<Integer> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<Integer> roleIdList) {
        this.roleIdList = roleIdList;
    }

    @Override
    public String toString() {
        return "UserRoleVO{" +
                "userId=" + userId +
                ", roleIdList=" + roleIdList +
                '}';
    }
}
