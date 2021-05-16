package com.osk.team.web;

import java.io.InputStream;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import com.osk.mybatis.MybatisDaoFactory;
import com.osk.mybatis.SqlSessionFactoryProxy;
import com.osk.mybatis.TransactionManager;
import com.osk.team.dao.BoardDao;
import com.osk.team.dao.ClubDao;
import com.osk.team.dao.DiscountDao;
import com.osk.team.dao.FaqDao;
import com.osk.team.dao.HotplaceDao;
import com.osk.team.dao.MemberDao;
import com.osk.team.dao.QnaDao;
import com.osk.team.service.BoardService;
import com.osk.team.service.ClubService;
import com.osk.team.service.DiscountService;
import com.osk.team.service.FaqService;
import com.osk.team.service.HotplaceService;
import com.osk.team.service.MemberService;
import com.osk.team.service.QnaService;
import com.osk.team.service.impl.DefaultBoardService;
import com.osk.team.service.impl.DefaultClubService;
import com.osk.team.service.impl.DefaultDiscountService;
import com.osk.team.service.impl.DefaultFaqService;
import com.osk.team.service.impl.DefaultHotplaceService;
import com.osk.team.service.impl.DefaultMemberService;
import com.osk.team.service.impl.DefaultQnaService;

@SuppressWarnings("serial")
public class AppInitHandler extends HttpServlet {

  @Override
  public void init() throws ServletException {

    try {
      // 1) Mybatis 관련 객체 준비
      InputStream mybatisConfigStream = Resources.getResourceAsStream(
          this.getInitParameter("mybatis-config"));
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
      ServletContext servletContext = this.getServletContext();

      servletContext.setAttribute("boardService", boardService);
      servletContext.setAttribute("clubService", clubService);
      servletContext.setAttribute("hotplaceService", hotplaceService);
      servletContext.setAttribute("memberService", memberService);
      servletContext.setAttribute("qnaService", qnaService);
      servletContext.setAttribute("faqService", faqService);
      servletContext.setAttribute("discountService", discountService);

      System.out.println("AppInitHandler: 의존 객체를 모두 준비하였습니다.");

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
