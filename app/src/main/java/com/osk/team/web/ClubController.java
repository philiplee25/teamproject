package com.osk.team.web;

import com.osk.team.domain.Club;
import com.osk.team.domain.Member;
import com.osk.team.domain.Photo;
import com.osk.team.service.ClubService;
import com.osk.team.service.MemberService;
import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/club/")
public class ClubController {

    ClubService clubService;
    MemberService memberService;

    public ClubController(ClubService clubService, MemberService memberService) {
        this.clubService = clubService;
        this.memberService = memberService;
    }

    @RequestMapping("add")
    public String add(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String uploadDir = request.getServletContext().getRealPath("/upload");

        if (request.getMethod().equals("GET")) {
            return "/jsp/club/form.jsp";
        }

        List<Part> partList = new ArrayList<Part>();
        List<Photo> photos = new ArrayList<Photo>();

        Club c = new Club();
        c.setArrive(request.getParameter("arrive"));
        c.setStartDate(Date.valueOf(request.getParameter("startDate")));
        c.setEndDate(Date.valueOf(request.getParameter("endDate")));
        c.setTheme(request.getParameter("theme"));
        c.setTitle(request.getParameter("title"));
        c.setContent(request.getParameter("content"));
        c.setTotal(Integer.parseInt(request.getParameter("count")));

        if (request.getPart("photo1").getSize() > 0) {
            partList.add(request.getPart("photo1"));
        }
        if (request.getPart("photo2").getSize() > 0) {
            partList.add(request.getPart("photo2"));
        }
        if (request.getPart("photo3").getSize() > 0) {
            partList.add(request.getPart("photo3"));
        }

        for (int i = 0; i < partList.size(); i++) {
            if (partList.get(i).getSize() > 0) {
                photos.add(new Photo());

                String filename = UUID.randomUUID().toString();
                partList.get(i).write(uploadDir + "/" + filename);
                photos.get(i).setName(filename);

                Thumbnails.of(uploadDir + "/" + filename)
                        .size(254, 178)
                        .outputFormat("jpg")
                        .crop(Positions.CENTER)
                        .toFiles(new Rename() {
                            @Override
                            public String apply(String name, ThumbnailParameter param) {
                                return name + "_254x178";
                            }
                        });
            }
        }
        HttpServletRequest httpRequest = request;
        Member loginUser = (Member) httpRequest.getSession().getAttribute("loginUser");//회원번호로 받기
        c.setWriter(loginUser);

        clubService.add(c);
        int p_cno = clubService.getClubCno().getNo();//같은 p_cno 번호 셋팅
        for (int i = 0; i < photos.size(); i++) {//반복문을 돌려서 갯수만큼 db에 저장

            photos.get(i).setNo(p_cno);//등록된 게시글의 cno값을 출력해서 넣어줌 사짐cno값 넣어줌
            clubService.addWithPhoto(photos.get(i));//클럽 사진 등록 /사진 cno값과 이름값 함께 insert 해준다
        }
        return "redirect:list";
    }

    @RequestMapping("delete")
    public String delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int no = Integer.parseInt(request.getParameter("no"));

        Club oldClub = clubService.get(no);
        if (oldClub == null) {
            throw new Exception("해당 번호의 클럽이 없습니다.");
        }

        Member loginUser = (Member) request.getSession().getAttribute("loginUser");
        if (oldClub.getWriter().getNo() != loginUser.getNo() && loginUser.getPower() == 0) {
            throw new Exception("삭제 권한이 없습니다.");
        }

        clubService.delete(no);
        return "redirect:list";
    }

    @RequestMapping("deleteMember")
    public String deleteMember(HttpServletRequest request, HttpServletResponse response) throws Exception {

        int no = Integer.parseInt(request.getParameter("no"));

        clubService.deleteMember(no);
        return "redirect:list";
    }

    @RequestMapping("deleteMembers")
    public String deleteMembers(HttpServletRequest request, HttpServletResponse response) throws Exception {

        int no = Integer.parseInt(request.getParameter("no"));
        List<Member> clubM = clubService.getMembers(no);

        clubService.deleteMember(no);
        request.setAttribute("clubMembers", clubM);
        return "redirect:list";

    }

    @RequestMapping("detail")
    public String detail(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Member loginUser = (Member) request.getSession().getAttribute("loginUser");

        int no = Integer.parseInt(request.getParameter("no"));

        Club c = clubService.get(no);
        List<Member> clubM = clubService.getMembers(no);
        c.setNowTotal(clubM.size() + 1);//현재 참여 인원 저장

        request.setAttribute("club", c);
        request.setAttribute("members", memberService.list(null));
        request.setAttribute("clubMembers", clubM);
        request.setAttribute("size", c.getNowTotal());

        return "/jsp/club/detail.jsp";
    }

    @RequestMapping("join")
    public String join(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int no = Integer.parseInt(request.getParameter("no"));

        Member loginUser = (Member) request.getSession().getAttribute("loginUser");

        HashMap<String, Object> params = new HashMap<>();
        params.put("memberNo", loginUser.getNo());
        params.put("clubNo", no);

        clubService.addWithMember(params);
        return "redirect:list";
    }

    @RequestMapping("list")
    public String list(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Club> clubs = null;

        String arrive = request.getParameter("arrive");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String theme = request.getParameter("theme");

        if ((arrive != null && arrive.length() > 0) ||
                (startDate != null && startDate.length() > 0) ||
                (endDate != null && endDate.length() > 0) ||
                (theme != null && theme.length() > 0)) {
            clubs = clubService.search(arrive, startDate, endDate, theme);
        } else {
            clubs = clubService.list();
        }

        request.setAttribute("clubs", clubs);
        return "/jsp/club/list.jsp";
    }

    @RequestMapping("report1")
    public String report1(HttpServletRequest request, HttpServletResponse response) throws Exception {

        int no = Integer.parseInt(request.getParameter("no"));
        Club c = clubService.get(no);
        request.setAttribute("club", c);

        return "/jsp/club/report1.jsp";
    }

    @RequestMapping("report")
    public String report(HttpServletRequest request, HttpServletResponse response) throws Exception {

        int no = Integer.parseInt(request.getParameter("no"));
        int clubWriterNo = Integer.parseInt(request.getParameter("clubWriterNo"));
        String reason = request.getParameter("reason");//신고 사유
        int result = Integer.parseInt(request.getParameter("result"));

        HashMap<String, Object> params = new HashMap<>();
        params.put("memberNo", clubWriterNo);//클럽글 작성자 번호
        params.put("clubNo", no);
        params.put("reason", reason);
        params.put("result", result);


        clubService.addWithReport(params);//신고하는 글과 작성자 번호 보내기
        return "redirect:list";
    }

    @RequestMapping("reportList")
    public String reportList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Club> clubs = null;
        clubs = clubService.getReports();

        request.setAttribute("clubs", clubs);
        request.setAttribute("members", memberService.list(null));

        return "/jsp/club/reportList.jsp";
    }

    @RequestMapping("update")
    public String update(HttpServletRequest request, HttpServletResponse response) throws Exception {

        int no = Integer.parseInt(request.getParameter("no"));

        Club oldClub = clubService.get(no);

        if (oldClub == null) {
            throw new Exception("해당 번호의 클럽이 없습니다.");
        }

        Member loginUser = (Member) request.getSession().getAttribute("loginUser");
        if (oldClub.getWriter().getNo() != loginUser.getNo()) {
            throw new Exception("변경 권한이 없습니다!");
        }

        // 사용자에게서 변경할 데이터를 입력 받는다.
        Club c = new Club();
        c.setNo(oldClub.getNo());
        c.setTitle(request.getParameter("title"));
        c.setContent(request.getParameter("content"));

        clubService.update(c);

        return "redirect:list";
    }
}
