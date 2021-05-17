package com.osk.team.domain;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Discount {
  private int no;//게시판 번호
  private String title;//게시판 제목
  private String content;//게시판 내용
  private Member writer; // 작성자=관리자
  private Date date;//작성일
  private int count;//조회수
  private String photo;//사진

  // 날짜 값을 특정 포맷의 문자열로 만들어 리턴한다.
  public String getdate2() {
    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
  }

  @Override
  public String toString() {
    return "Discount [no=" + no + ", title=" + title + ", content=" + content + ", writer=" + writer
        + ", date=" + date + ", count=" + count + ", photo=" + photo + "]";
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


  public Member getWriter() {
    return writer;
  }


  public void setWriter(Member writer) {
    this.writer = writer;
  }


  public Date getDate() {
    return date;
  }


  public void setDate(Date date) {
    this.date = date;
  }


  public int getCount() {
    return count;
  }


  public void setCount(int count) {
    this.count = count;
  }


  public String getPhoto() {
    return photo;
  }


  public void setPhoto(String photo) {
    this.photo = photo;
  }

}
