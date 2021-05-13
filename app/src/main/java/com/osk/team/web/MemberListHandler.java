package com.osk.team.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import com.osk.team.domain.Member;
import com.osk.team.service.MemberService;

@WebServlet("/member/list")
public class MemberListHandler implements Servlet {

    @Override
    public void service(ServletRequest request, ServletResponse response)
            throws ServletException, IOException {

        MemberService memberService = (MemberService) request.getServletContext().getAttribute("memberService");

        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("[회원 목록]");

        out.println("회원번호 입력:");
        int no = Integer.parseInt(request.getParameter("no"));

        try {
//            Member m = memberService.get(no);

//            for (Member m : list) {
//                out.printf("%d, %s, %s, %d, %s, %s \n",
//                        m.getMno(),
//                        m.getMname(),
//                        m.getMemail(),
//                        m.getMtel(),
//                        m.getMbirth(),
//                        m.getMgender());
//
//                out.println("<meta http-equiv='Refresh' content='1;url=list'>");
//                out.println("</head>");
//                out.println("<body>");
//                out.println("<h1>회원 목록</h1>");
//                out.println("<p>회원목록입니다 > </p>");
//            }

//            out.printf("%d, %s, %s, %d, %s, %s \n",
//                        m.getNo(),
//                        m.getName()
//                        m.getMemail(),
//                        m.getMtel(),
//                        m.getMbirth(),
//                        m.getMgender()
//                        );

                out.println("<meta http-equiv='Refresh' content='1;url=list'>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>회원 목록</h1>");
                out.println("<p>회원목록입니다 > </p>");

        } catch (Exception e) {
            StringWriter strWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(strWriter);
            e.printStackTrace(printWriter);
            out.println(strWriter.toString());

            out.println("</head>");
            out.println("<body>");
            out.println("<h1>회원목록 불러오기 오류!</h1>");
            out.printf("<pre>%s</pre>\n", strWriter.toString());
            out.println("<p><a href='list'>회원 목록</a></p>");
        }

        out.println("</body>");
        out.println("</html>");

    }

    @Override
    public void init(ServletConfig config) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public String getServletInfo() {
        return null;
    }
}