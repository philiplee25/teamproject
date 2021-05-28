package com.osk.team.domain;

import java.sql.Date;

public class Hotplace {

  private int no; // 게시판 번호
  private String title; // 게시판 제목
  private String content; // 게시판 내용
  private Date date; // 작성일
  private String photo; // 사진
  private String latitude; // 위도
  private String longitude; // 경도

  @Override
  public String toString() {
    return "Hotplace [no=" + no + ", title=" + title + ", content=" + content + ", date=" + date
        + ", photo=" + photo + ", latitude=" + latitude + ", longitude=" + longitude + "]";
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
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

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }

  public String getLatitude() {
    return latitude;
  }

  public void setLatitude(String latitude) {
    this.latitude = latitude;
  }

  public String getLongitude() {
    return longitude;
  }

  public void setLongitude(String longitude) {
    this.longitude = longitude;
  }


}