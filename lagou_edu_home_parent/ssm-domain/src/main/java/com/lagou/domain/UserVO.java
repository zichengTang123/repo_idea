package com.lagou.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class UserVO {

    private Integer currentPage;
    private Integer pageSize;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endCreateTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startCreateTime;
    private String username;

    @Override
    public String toString() {
        return "UserVO{" +
                "currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", endCreateTime=" + endCreateTime +
                ", startCreateTime=" + startCreateTime +
                ", username='" + username + '\'' +
                '}';
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Date getEndCreateTime() {
        return endCreateTime;
    }

    public void setEndCreateTime(Date endCreateTime) {
        this.endCreateTime = endCreateTime;
    }

    public Date getStartCreateTime() {
        return startCreateTime;
    }

    public void setStartCreateTime(Date startCreateTime) {
        this.startCreateTime = startCreateTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
