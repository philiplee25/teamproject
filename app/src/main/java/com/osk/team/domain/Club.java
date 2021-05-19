package com.osk.team.domain;

import java.sql.Date;
import java.util.List;

public class Club {
    /*
    2.현재인원도 출력되게!(방장은 이미들어왔으니 현재인원에 1 포함하고 다른 사람이 클럽참여하면 +1되게!)
    3.멤버 저장/불러오기 구현하기
     */

    private int no;//클럽번호

    private Member writer;//작성자
    private List<Member> members;//현재 클럽에 소속된 멤버들

    private String arrive;//도착지
    private String theme;//테마
    private String title;//제목
    private String content;//내용
    private Date startDate;//가는날
    private Date endDate;//오는날
    private int total;//인원수
    private List<Object> photos;//사진

    @Override
    public String toString() {
        return "Club{" +
                "no=" + no +
                ", writer=" + writer +
                ", members=" + members +
                ", arrive='" + arrive + '\'' +
                ", theme='" + theme + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", total=" + total +
                ", photos=" + photos +
                '}';
    }

    public Member getWriter() {
        return writer;
    }

    public void setWriter(Member writer) {
        this.writer = writer;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public List<Object> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Object> photos) {
        this.photos = photos;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getArrive() {
        return arrive;
    }

    public void setArrive(String arrive) {
        this.arrive = arrive;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
