-- 회원
DROP TABLE IF EXISTS member RESTRICT;

-- 핫플레이스
DROP TABLE IF EXISTS hotplace RESTRICT;

-- 신고게시판
DROP TABLE IF EXISTS b_report RESTRICT;

-- 할인정보
DROP TABLE IF EXISTS discount RESTRICT;

-- 여행클럽
DROP TABLE IF EXISTS club RESTRICT;

-- 게시글
DROP TABLE IF EXISTS board RESTRICT;

-- 게시판 댓글
DROP TABLE IF EXISTS b_reply RESTRICT;

-- QnA게시판
DROP TABLE IF EXISTS m_qna RESTRICT;

-- 탈퇴회원정보
DROP TABLE IF EXISTS m_delete RESTRICT;

-- FAQ게시판
DROP TABLE IF EXISTS faq RESTRICT;

-- 여행참여자
DROP TABLE IF EXISTS c_member RESTRICT;

-- 게시판분류
DROP TABLE IF EXISTS b_type RESTRICT;

-- 게시판사진
DROP TABLE IF EXISTS b_photo RESTRICT;

-- 여행클럽신고
DROP TABLE IF EXISTS c_report RESTRICT;

-- 클럽사진
DROP TABLE IF EXISTS c_photo RESTRICT;

-- 회원
CREATE TABLE member (
                        mno     INTEGER      NOT NULL COMMENT '회원번호', -- 회원번호
                        mname   VARCHAR(50)  NOT NULL COMMENT '이름', -- 이름
                        mpwd    VARCHAR(100) NOT NULL COMMENT '암호', -- 암호
                        memail  VARCHAR(40)  NOT NULL COMMENT '이메일', -- 이메일
                        mbirth  DATE         NOT NULL COMMENT '생년월일', -- 생년월일
                        mtel    INTEGER      NOT NULL COMMENT '전화번호', -- 전화번호
                        mgender INTEGER      NOT NULL COMMENT '성별', -- 성별
                        mphoto  VARCHAR(255) NULL     COMMENT '사진', -- 사진
                        mstatus INTEGER      NOT NULL COMMENT '탈퇴여부', -- 탈퇴여부
                        mpow    INTEGER      NOT NULL COMMENT '관리자권한', -- 관리자권한
                        mcnt    INTEGER      NULL     COMMENT '제제횟수' -- 제제횟수
)
    COMMENT '회원';

-- 회원
ALTER TABLE member
    ADD CONSTRAINT PK_member -- 회원 기본키
        PRIMARY KEY (
                     mno -- 회원번호
            );

-- 회원
ALTER TABLE member
    ADD CONSTRAINT CK_member -- 회원 체크 제약
        CHECK (mgender = 1 or mgender = 0
            );

-- 회원
ALTER TABLE member
    ADD CONSTRAINT CK_member2 -- 회원 체크 제약2
        CHECK (mstatus = 1 or mstatus = 0);

ALTER TABLE member
    MODIFY COLUMN mno INTEGER NOT NULL AUTO_INCREMENT COMMENT '회원번호';

-- 핫플레이스
CREATE TABLE hotplace (
                          hno      INTEGER      NOT NULL COMMENT '게시판 번호', -- 게시판 번호
                          htitle   VARCHAR(255) NOT NULL COMMENT '게시판 제목', -- 게시판 제목
                          hcontent LONGTEXT     NOT NULL COMMENT '게시판 내용', -- 게시판 내용
                          hdate    TIMESTAMP    NOT NULL DEFAULT current_timestamp COMMENT '등록 날짜', -- 등록 날짜
                          hcount   INTEGER      NOT NULL COMMENT '조회수', -- 조회수
                          haddr    VARCHAR(255) NOT NULL COMMENT '주소', -- 주소
                          hphoto   VARCHAR(255) NULL     COMMENT '사진' -- 사진
)
    COMMENT '핫플레이스';

-- 핫플레이스
ALTER TABLE hotplace
    ADD CONSTRAINT PK_hotplace -- 핫플레이스 기본키
        PRIMARY KEY (
                     hno -- 게시판 번호
            );

ALTER TABLE hotplace
    MODIFY COLUMN hno INTEGER NOT NULL AUTO_INCREMENT COMMENT '게시판 번호';

-- 신고게시판
CREATE TABLE b_report (
                          b_nno     INTEGER  NOT NULL COMMENT '신고게시판번호', -- 신고게시판번호
                          bno       INTEGER  NOT NULL COMMENT '게시판id', -- 게시판id
                          mno       INTEGER  NOT NULL COMMENT '회원번호', -- 회원번호
                          b_rreason LONGTEXT NOT NULL COMMENT '신고 사유', -- 신고 사유
                          b_rresult INTEGER  NULL     COMMENT '처리결과' -- 처리결과
)
    COMMENT '신고게시판';

-- 신고게시판
ALTER TABLE b_report
    ADD CONSTRAINT PK_b_report -- 신고게시판 기본키
        PRIMARY KEY (
                     b_nno -- 신고게시판번호
            );

-- 신고게시판
ALTER TABLE b_report
    ADD CONSTRAINT CK_b_report -- 신고게시판 체크 제약
        CHECK (b_rresult = 1 or b_rresult = 0);

ALTER TABLE b_report
    MODIFY COLUMN b_nno INTEGER NOT NULL AUTO_INCREMENT COMMENT '신고게시판번호';

-- 할인정보
CREATE TABLE discount (
                          dno      INTEGER      NOT NULL COMMENT '게시판 번호', -- 게시판 번호
                          dtitle   VARCHAR(255) NOT NULL COMMENT '게시판 제목', -- 게시판 제목
                          dcontent LONGTEXT     NOT NULL COMMENT '게시판 내용', -- 게시판 내용
                          ddate    TIMESTAMP    NOT NULL DEFAULT current_timestamp COMMENT '등록 날짜', -- 등록 날짜
                          dcount   INTEGER      NOT NULL COMMENT '조회수', -- 조회수
                          dphoto   VARCHAR(255) NULL     COMMENT '사진' -- 사진
)
    COMMENT '할인정보';

-- 할인정보
ALTER TABLE discount
    ADD CONSTRAINT PK_discount -- 할인정보 기본키
        PRIMARY KEY (
                     dno -- 게시판 번호
            );

ALTER TABLE discount
    MODIFY COLUMN dno INTEGER NOT NULL AUTO_INCREMENT COMMENT '게시판 번호';

-- 여행클럽
CREATE TABLE club (
                      cno      INTEGER      NOT NULL COMMENT '클럽게시판번호', -- 클럽게시판번호
                      mno      INTEGER      NOT NULL COMMENT '회원번호', -- 회원번호
                      carrive  VARCHAR(10)  NOT NULL COMMENT '도착지', -- 도착지
                      ctheme   VARCHAR(10)  NOT NULL COMMENT '테마', -- 테마
                      ctitle   VARCHAR(255) NOT NULL COMMENT '제목', -- 제목
                      ccontent LONGTEXT     NOT NULL COMMENT '내용', -- 내용
                      csdt     DATE         NOT NULL COMMENT '출발날짜', -- 출발날짜
                      cedt     DATE         NOT NULL COMMENT '도착날짜', -- 도착날짜
                      ctotal   INTEGER      NOT NULL COMMENT '최대인원수' -- 최대인원수
)
    COMMENT '여행클럽';

-- 여행클럽
ALTER TABLE club
    ADD CONSTRAINT PK_club -- 여행클럽 기본키
        PRIMARY KEY (
                     cno -- 클럽게시판번호
            );

ALTER TABLE club
    MODIFY COLUMN cno INTEGER NOT NULL AUTO_INCREMENT COMMENT '클럽게시판번호';

-- 게시글
CREATE TABLE board (
                       bno      INTEGER      NOT NULL COMMENT '게시판id', -- 게시판id
                       b_tno    INTEGER      NOT NULL COMMENT '게시판분류번호', -- 게시판분류번호
                       mno      INTEGER      NOT NULL COMMENT '회원번호', -- 회원번호
                       btitle   VARCHAR(255) NOT NULL COMMENT '게시판 제목', -- 게시판 제목
                       bcontent LONGTEXT     NOT NULL COMMENT '게시판내용', -- 게시판내용
                       bcount   INTEGER      NOT NULL COMMENT '조회수', -- 조회수
                       bdate    DATETIME     NOT NULL DEFAULT now() COMMENT '작성일' -- 작성일
)
    COMMENT '게시글';

-- 게시글
ALTER TABLE board
    ADD CONSTRAINT PK_board -- 게시글 기본키
        PRIMARY KEY (
                     bno -- 게시판id
            );

ALTER TABLE board
    MODIFY COLUMN bno INTEGER NOT NULL AUTO_INCREMENT COMMENT '게시판id';

-- 게시판 댓글
CREATE TABLE b_reply (
                         b_rno      INTEGER  NOT NULL COMMENT '댓글 번호', -- 댓글 번호
                         mno        INTEGER  NOT NULL COMMENT '회원번호', -- 회원번호
                         bno        INTEGER  NOT NULL COMMENT '게시판id', -- 게시판id
                         b_rdate    DATETIME NOT NULL DEFAULT now() COMMENT '작성일자', -- 작성일자
                         b_rcontent LONGTEXT NOT NULL COMMENT '댓글 내용' -- 댓글 내용
)
    COMMENT '게시판 댓글';

-- 게시판 댓글
ALTER TABLE b_reply
    ADD CONSTRAINT PK_b_reply -- 게시판 댓글 기본키
        PRIMARY KEY (
                     b_rno -- 댓글 번호
            );

ALTER TABLE b_reply
    MODIFY COLUMN b_rno INTEGER NOT NULL AUTO_INCREMENT COMMENT '댓글 번호';

-- QnA게시판
CREATE TABLE m_qna (
                       m_qno      INTEGER      NOT NULL COMMENT 'QnA번호', -- QnA번호
                       mno        INTEGER      NOT NULL COMMENT '회원번호', -- 회원번호
                       m_qdate    TIMESTAMP    NOT NULL COMMENT '작성일', -- 작성일
                       m_qtitle   VARCHAR(255) NOT NULL COMMENT '제목', -- 제목
                       m_qcontent LONGTEXT     NOT NULL COMMENT '내용', -- 내용
                       a_qcontent LONGTEXT     NULL     COMMENT '답변내용', -- 답변내용
                       a_qdate    TIMESTAMP    NOT NULL DEFAULT current_timestamp COMMENT '답변일' -- 답변일
)
    COMMENT 'QnA게시판';

-- QnA게시판
ALTER TABLE m_qna
    ADD CONSTRAINT PK_m_qna -- QnA게시판 기본키
        PRIMARY KEY (
                     m_qno -- QnA번호
            );

ALTER TABLE m_qna
    MODIFY COLUMN m_qno INTEGER NOT NULL AUTO_INCREMENT COMMENT 'QnA번호';

-- 탈퇴회원정보
CREATE TABLE m_delete (
                          m_dno     INTEGER  NOT NULL COMMENT '탈퇴번호', -- 탈퇴번호
                          mno       INTEGER  NOT NULL COMMENT '회원번호', -- 회원번호
                          m_dreason LONGTEXT NOT NULL COMMENT '탈퇴사유', -- 탈퇴사유
                          m_ddate   DATE     NOT NULL COMMENT '탈퇴일' -- 탈퇴일
)
    COMMENT '탈퇴회원정보';

-- 탈퇴회원정보
ALTER TABLE m_delete
    ADD CONSTRAINT PK_m_delete -- 탈퇴회원정보 기본키
        PRIMARY KEY (
                     m_dno -- 탈퇴번호
            );

ALTER TABLE m_delete
    MODIFY COLUMN m_dno INTEGER NOT NULL AUTO_INCREMENT COMMENT '탈퇴번호';

-- FAQ게시판
CREATE TABLE faq (
                     fno      INTEGER      NOT NULL COMMENT 'FAQ번호', -- FAQ번호
                     ftitle   VARCHAR(255) NOT NULL COMMENT '제목', -- 제목
                     fdate    TIMESTAMP    NOT NULL DEFAULT current_timestamp COMMENT '작성일', -- 작성일
                     fcontent LONGTEXT     NOT NULL COMMENT '내용' -- 내용
)
    COMMENT 'FAQ게시판';

-- FAQ게시판
ALTER TABLE faq
    ADD CONSTRAINT PK_faq -- FAQ게시판 기본키
        PRIMARY KEY (
                     fno -- FAQ번호
            );

ALTER TABLE faq
    MODIFY COLUMN fno INTEGER NOT NULL AUTO_INCREMENT COMMENT 'FAQ번호';

-- 여행참여자
CREATE TABLE c_member (
                          mno     INTEGER NOT NULL COMMENT '회원번호', -- 회원번호
                          cno     INTEGER NOT NULL COMMENT '클럽게시판번호', -- 클럽게시판번호
                          c_mdate DATE    NULL     COMMENT '등록일' -- 등록일
)
    COMMENT '여행참여자';

-- 여행참여자
ALTER TABLE c_member
    ADD CONSTRAINT PK_c_member -- 여행참여자 기본키
        PRIMARY KEY (
                     mno, -- 회원번호
                     cno  -- 클럽게시판번호
            );

-- 게시판분류
CREATE TABLE b_type (
                        b_tno    INTEGER      NOT NULL COMMENT '게시판분류번호', -- 게시판분류번호
                        b_ttitle VARCHAR(255) NOT NULL COMMENT '게시판이름' -- 게시판이름
)
    COMMENT '게시판분류';

-- 게시판분류
ALTER TABLE b_type
    ADD CONSTRAINT PK_b_type -- 게시판분류 기본키
        PRIMARY KEY (
                     b_tno -- 게시판분류번호
            );

-- 게시판분류
ALTER TABLE b_type
    ADD CONSTRAINT CK_b_type -- 게시판분류 체크 제약
        CHECK (b_tno = 1 or b_tno = 2 or b_tno = 3);

-- 게시판사진
CREATE TABLE b_photo (
                         b_pno  INTEGER      NOT NULL COMMENT '게시판사진번호', -- 게시판사진번호
                         bphoto VARCHAR(255) NOT NULL COMMENT '사진', -- 사진
                         bno    INTEGER      NOT NULL COMMENT '게시판id' -- 게시판id
)
    COMMENT '게시판사진';

-- 게시판사진
ALTER TABLE b_photo
    ADD CONSTRAINT PK_b_photo -- 게시판사진 기본키
        PRIMARY KEY (
                     b_pno -- 게시판사진번호
            );

ALTER TABLE b_photo
    MODIFY COLUMN b_pno INTEGER NOT NULL AUTO_INCREMENT COMMENT '게시판사진번호';

-- 여행클럽신고
CREATE TABLE c_report (
                          cno       INTEGER   NOT NULL COMMENT '클럽게시판번호', -- 클럽게시판번호
                          mno       INTEGER   NOT NULL COMMENT '회원번호', -- 회원번호
                          c_rreason LONGTEXT  NOT NULL COMMENT '신고사유', -- 신고사유
                          c_rresult INTEGER   NOT NULL COMMENT '처리결과', -- 처리결과
                          c_rdate   TIMESTAMP NOT NULL COMMENT '신고일자' -- 신고일자
)
    COMMENT '여행클럽신고';

-- 여행클럽신고
ALTER TABLE c_report
    ADD CONSTRAINT PK_c_report -- 여행클럽신고 기본키
        PRIMARY KEY (
                     cno, -- 클럽게시판번호
                     mno  -- 회원번호
            );

-- 여행클럽신고
ALTER TABLE c_report
    ADD CONSTRAINT CK_c_report -- 여행클럽신고 체크 제약
        CHECK (c_rresult = 1 or c_rresult = 0);

-- 클럽사진
CREATE TABLE c_photo (
                         c_pno INTEGER      NOT NULL COMMENT '클럽사진번호', -- 클럽사진번호
                         cno   INTEGER      NOT NULL COMMENT '클럽게시판번호', -- 클럽게시판번호
                         c_pho VARCHAR(255) NULL     COMMENT '사진' -- 사진
)
    COMMENT '클럽사진';

-- 클럽사진
ALTER TABLE c_photo
    ADD CONSTRAINT PK_c_photo -- 클럽사진 기본키
        PRIMARY KEY (
                     c_pno -- 클럽사진번호
            );

ALTER TABLE c_photo
    MODIFY COLUMN c_pno INTEGER NOT NULL AUTO_INCREMENT COMMENT '클럽사진번호';

-- 신고게시판
ALTER TABLE b_report
    ADD CONSTRAINT FK_board_TO_b_report -- 게시글 -> 신고게시판
        FOREIGN KEY (
                     bno -- 게시판id
            )
            REFERENCES board ( -- 게시글
                              bno -- 게시판id
                );

-- 신고게시판
ALTER TABLE b_report
    ADD CONSTRAINT FK_member_TO_b_report -- 회원 -> 신고게시판
        FOREIGN KEY (
                     mno -- 회원번호
            )
            REFERENCES member ( -- 회원
                               mno -- 회원번호
                );

-- 여행클럽
ALTER TABLE club
    ADD CONSTRAINT FK_member_TO_club -- 회원 -> 여행클럽
        FOREIGN KEY (
                     mno -- 회원번호
            )
            REFERENCES member ( -- 회원
                               mno -- 회원번호
                );


-- 게시글
ALTER TABLE board
    ADD CONSTRAINT FK_member_TO_board -- 회원 -> 게시글
        FOREIGN KEY (
                     mno -- 회원번호
            )
            REFERENCES member ( -- 회원
                               mno -- 회원번호
                );

-- 게시글
ALTER TABLE board
    ADD CONSTRAINT FK_b_type_TO_board -- 게시판분류 -> 게시글
        FOREIGN KEY (
                     b_tno -- 게시판분류번호
            )
            REFERENCES b_type ( -- 게시판분류
                               b_tno -- 게시판분류번호
                );

-- 게시판 댓글
ALTER TABLE b_reply
    ADD CONSTRAINT FK_member_TO_b_reply -- 회원 -> 게시판 댓글
        FOREIGN KEY (
                     mno -- 회원번호
            )
            REFERENCES member ( -- 회원
                               mno -- 회원번호
                );

-- 게시판 댓글
ALTER TABLE b_reply
    ADD CONSTRAINT FK_board_TO_b_reply -- 게시글 -> 게시판 댓글
        FOREIGN KEY (
                     bno -- 게시판id
            )
            REFERENCES board ( -- 게시글
                              bno -- 게시판id
                );

-- QnA게시판
ALTER TABLE m_qna
    ADD CONSTRAINT FK_member_TO_m_qna -- 회원 -> QnA게시판
        FOREIGN KEY (
                     mno -- 회원번호
            )
            REFERENCES member ( -- 회원
                               mno -- 회원번호
                );

-- 탈퇴회원정보
ALTER TABLE m_delete
    ADD CONSTRAINT FK_member_TO_m_delete -- 회원 -> 탈퇴회원정보
        FOREIGN KEY (
                     mno -- 회원번호
            )
            REFERENCES member ( -- 회원
                               mno -- 회원번호
                );

-- 여행참여자
ALTER TABLE c_member
    ADD CONSTRAINT FK_member_TO_c_member -- 회원 -> 여행참여자
        FOREIGN KEY (
                     mno -- 회원번호
            )
            REFERENCES member ( -- 회원
                               mno -- 회원번호
                );

alter table c_member
    drop constraint FK_member_TO_c_member;

ALTER TABLE c_member
    ADD CONSTRAINT FK_club_TO_c_member -- 여행클럽 -> 여행참여자
        FOREIGN KEY (
                     cno -- 클럽게시판번호
            )
            REFERENCES club ( -- 여행클럽
                             cno -- 클럽게시판번호
                );

-- 여행참여자
alter table c_member
    drop constraint FK_club_TO_c_member;

-- 게시판사진
ALTER TABLE b_photo
    ADD CONSTRAINT FK_board_TO_b_photo -- 게시글 -> 게시판사진
        FOREIGN KEY (
                     bno -- 게시판id
            )
            REFERENCES board ( -- 게시글
                              bno -- 게시판id
                );

-- 여행클럽신고
ALTER TABLE c_report
    ADD CONSTRAINT FK_club_TO_c_report -- 여행클럽 -> 여행클럽신고
        FOREIGN KEY (
                     cno -- 클럽게시판번호
            )
            REFERENCES club ( -- 여행클럽
                             cno -- 클럽게시판번호
                );

-- 여행클럽신고
ALTER TABLE c_report
    ADD CONSTRAINT FK_member_TO_c_report -- 회원 -> 여행클럽신고
        FOREIGN KEY (
                     mno -- 회원번호
            )
            REFERENCES member ( -- 회원
                               mno -- 회원번호
                );

-- 클럽사진
ALTER TABLE c_photo
    ADD CONSTRAINT FK_club_TO_c_photo -- 여행클럽 -> 클럽사진
        FOREIGN KEY (
                     cno -- 클럽게시판번호
            )
            REFERENCES club ( -- 여행클럽
                             cno -- 클럽게시판번호
                );




alter table c_photo drop constraint FK_club_TO_c_photo;

alter table c_photo add constraint FK_club_TO_c_photo
    foreign key (cno) references club (cno)
        on delete cascade
        on update cascade;


alter table c_member add constraint FK_member_TO_c_member
    foreign key (mno) references member (mno)
        on delete cascade
        on update cascade;

alter table c_member drop constraint FK_member_TO_c_member;



alter table c_member
    add constraint FK_club_TO_c_member
        foreign key (cno) references club (cno)
            on delete cascade
            on update cascade;

alter table c_member drop constraint FK_club_TO_c_member;


alter table c_report drop constraint FK_club_TO_c_report;

alter table c_report add constraint FK_club_TO_c_report
    foreign key (cno) references club (cno)
        on delete cascade
        on update cascade;


alter table c_report drop constraint FK_member_TO_c_report;

alter table c_report add constraint FK_member_TO_c_report
    foreign key (mno) references member (mno)
        on delete cascade
        on update cascade;

select * from information_schema.table_constraints;