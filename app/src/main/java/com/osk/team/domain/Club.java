package com.osk.team.domain;

import java.sql.Date;
import java.util.List;

public class Club {
    /*
    1.맴버번호 배열로 가져와야되는거 아닌지?
    2.현재인원도 출력되게!(방장은 이미들어왔으니 현재인원에 1 포함하고 다른 사람이 클럽참여하면 +1되게!)
    3.사진-멤버 저장/불러오기 구현하기
    4.클럽 상세보기에서 사진이 이상하게 출력
    5.클럽생성시에 사진 넣으면 NPE뜸


    --------------------------
    org.apache.ibatis.exceptions.PersistenceException:
### Error updating database.  Cause: org.apache.ibatis.reflection.ReflectionException: There is no getter for property named 'club' in 'class com.osk.team.domain.Club'
### The error may exist in com/osk/team/mapper/ClubMapper.xml
### The error may involve com.osk.team.dao.ClubDao.insert-Inline
### The error occurred while setting parameters
### SQL: insert into club(cno, mno, carrive, ctheme, ctitle, ccontent, csdt, cedt, ctotal)     values (?, ?, ?, ?,             ?, ?, ?, ?,             ?);     insert into c_photo(c_pno,cno,c_pho)     values (?,?,?);
### Cause: org.apache.ibatis.reflection.ReflectionException: There is no getter for property named 'club' in 'class com.osk.team.domain.Club'
	at org.apache.ibatis.exceptions.ExceptionFactory.wrapException(ExceptionFactory.java:30)
	at org.apache.ibatis.session.defaults.DefaultSqlSession.update(DefaultSqlSession.java:199)
	at org.apache.ibatis.session.defaults.DefaultSqlSession.insert(DefaultSqlSession.java:184)
	at com.osk.mybatis.DaoWorker.invoke(DaoWorker.java:35)
	at com.sun.proxy.$Proxy11.insert(Unknown Source)
	at com.osk.team.service.impl.DefaultClubService.add(DefaultClubService.java:27)
	at com.osk.team.web.ClubAddHandler.doPost(ClubAddHandler.java:138)
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:652)
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:733)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:227)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)
	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)
	at com.osk.team.web.filter.RequestLogFilter.doFilter(RequestLogFilter.java:20)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)
	at com.osk.team.web.filter.LoginCheckFilter.doFilter(LoginCheckFilter.java:29)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)
	at com.osk.team.web.filter.CharacterEncodingFilter.doFilter(CharacterEncodingFilter.java:37)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)
	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:202)
	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:97)
	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:542)
	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:143)
	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:92)
	at org.apache.catalina.valves.AbstractAccessLogValve.invoke(AbstractAccessLogValve.java:687)
	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:78)
	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:357)
	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:374)
	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:65)
	at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:893)
	at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1707)
	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)
	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1128)
	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628)
	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
	at java.base/java.lang.Thread.run(Thread.java:829)
Caused by: org.apache.ibatis.reflection.ReflectionException: There is no getter for property named 'club' in 'class com.osk.team.domain.Club'
	at org.apache.ibatis.reflection.Reflector.getGetInvoker(Reflector.java:374)
	at org.apache.ibatis.reflection.MetaClass.getGetInvoker(MetaClass.java:164)
	at org.apache.ibatis.reflection.wrapper.BeanWrapper.getBeanProperty(BeanWrapper.java:162)
	at org.apache.ibatis.reflection.wrapper.BeanWrapper.get(BeanWrapper.java:49)
	at org.apache.ibatis.reflection.MetaObject.getValue(MetaObject.java:122)
	at org.apache.ibatis.reflection.MetaObject.metaObjectForProperty(MetaObject.java:145)
	at org.apache.ibatis.reflection.MetaObject.getValue(MetaObject.java:115)
	at org.apache.ibatis.scripting.defaults.DefaultParameterHandler.setParameters(DefaultParameterHandler.java:79)
	at org.apache.ibatis.executor.statement.PreparedStatementHandler.parameterize(PreparedStatementHandler.java:94)
	at org.apache.ibatis.executor.statement.RoutingStatementHandler.parameterize(RoutingStatementHandler.java:64)
	at org.apache.ibatis.executor.SimpleExecutor.prepareStatement(SimpleExecutor.java:88)
	at org.apache.ibatis.executor.SimpleExecutor.doUpdate(SimpleExecutor.java:49)
	at org.apache.ibatis.executor.BaseExecutor.update(BaseExecutor.java:117)
	at org.apache.ibatis.executor.CachingExecutor.update(CachingExecutor.java:76)
	at org.apache.ibatis.session.defaults.DefaultSqlSession.update(DefaultSqlSession.java:197)
	... 38 more
     */

    private int no;//클럽번호
    private int memberNo;//멤버번호--------------여기 배열로 가져와야되는거 아닌지?

    private Member writer;//작성자
    private List<Member> members;//현재 인원

    private String arrive;//도착지
    private String theme;//테마
    private String title;//제목
    private String content;//내용
    private Date startDate;//가는날
    private Date endDate;//오는날
    private int total;//인원수
    private String photo1;//사진
    private String photo2;//사진
    private String photo3;//사진

    @Override
    public String toString() {
        return "Club{" +
                "no=" + no +
                ", memberNo=" + memberNo +
                ", writer=" + writer +
                ", members=" + members +
                ", arrive='" + arrive + '\'' +
                ", theme='" + theme + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", total=" + total +
                ", photo1='" + photo1 + '\'' +
                ", photo2='" + photo2 + '\'' +
                ", photo3='" + photo3 + '\'' +
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

    public String getPhoto1() {
        return photo1;
    }

    public void setPhoto1(String photo1) {
        this.photo1 = photo1;
    }

    public String getPhoto2() {
        return photo2;
    }

    public void setPhoto2(String photo2) {
        this.photo2 = photo2;
    }

    public String getPhoto3() {
        return photo3;
    }

    public void setPhoto3(String photo3) {
        this.photo3 = photo3;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(int memberNo) {
        this.memberNo = memberNo;
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
