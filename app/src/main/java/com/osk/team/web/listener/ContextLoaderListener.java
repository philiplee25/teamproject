package com.osk.team.web.listener;

import java.io.InputStream;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.osk.team.dao.*;
import com.osk.team.service.*;
import com.osk.team.service.impl.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import com.osk.mybatis.MybatisDaoFactory;
import com.osk.mybatis.SqlSessionFactoryProxy;
import com.osk.mybatis.TransactionManager;

// 웹 애플리케이션을 시작하거나 종료할 때 서버로부터 보고를 받는다.
// 즉 톰캣 서버가 웹 애플리케이션을 시작하거나 종료하면 리스너 규칙에 따라 메서드를 호출한다는 뜻이다.
// 
public class ContextLoaderListener implements ServletContextListener {
  @Override
  public void contextInitialized(ServletContextEvent sce) {
    try {
      ServletContext servletContext = sce.getServletContext();

      // 1) Mybatis 관련 객체 준비
      InputStream mybatisConfigStream = Resources.getResourceAsStream(
          servletContext.getInitParameter("mybatis-config"));
      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(mybatisConfigStream);
      SqlSessionFactoryProxy sqlSessionFactoryProxy = new SqlSessionFactoryProxy(sqlSessionFactory);

      // 2) DAO 관련 객체 준비
      MybatisDaoFactory daoFactory = new MybatisDaoFactory(sqlSessionFactoryProxy);
      BoardDao boardDao = daoFactory.createDao(BoardDao.class);
      ClubDao clubDao = daoFactory.createDao(ClubDao.class);
      HotplaceDao hotplaceDao = daoFactory.createDao(HotplaceDao.class);
      MemberDao memberDao = daoFactory.createDao(MemberDao.class);
      QnaDao qnaDao = daoFactory.createDao(QnaDao.class);
      FaqDao faqDao = daoFactory.createDao(FaqDao.class);
      DiscountDao discountDao = daoFactory.createDao(DiscountDao.class);

      // 3) 서비스 관련 객체 준비
      TransactionManager txManager = new TransactionManager(sqlSessionFactoryProxy);

      BoardService boardService = new DefaultBoardService(boardDao);
      ClubService clubService = new DefaultClubService(txManager, clubDao);
      HotplaceService hotplaceService = new DefaultHotplaceService(hotplaceDao);
      MemberService memberService = new DefaultMemberService(memberDao);
      QnaService qnaService = new DefaultQnaService(qnaDao);
      FaqService faqService = new DefaultFaqService(faqDao);
      DiscountService discountService = new DefaultDiscountService(discountDao);

      // 4) 서비스 객체를 ServletContext 보관소에 저장한다.
      servletContext.setAttribute("boardService", boardService);
      servletContext.setAttribute("clubService", clubService);
      servletContext.setAttribute("hotplaceService", hotplaceService);
      servletContext.setAttribute("memberService", memberService);
      servletContext.setAttribute("qnaService", qnaService);
      servletContext.setAttribute("faqService", faqService);
      servletContext.setAttribute("discountService", discountService);

      System.out.println("ContextLoaderListener: 의존 객체를 모두 준비하였습니다.");

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
