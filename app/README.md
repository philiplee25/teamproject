//TODO

1.관리자권한에서 현재 회원리스트와 회원번호로 회원을 검색할 수 있는 기능 ui추가




2.sql no value차이
<update id="update" parameterType="qna">
    update qna
    set m_qtitle = #{m_qtitle},
        m_qcontent = #{m_qcontent}
    where m_qno = #{no}
  </update>

  <delete id="delete" parameterType="int">
    delete from qna
    where m_qno = #{value}
  </delete>

3.sql문 mapper or/and 클럽
<if test="value != null">
where
b.title like concat('%', #{value},'%')
and b.content like concat('%', #{value},'%')
and m.name like concat('%', #{value},'%')
</if>




4.멤버 디테일/리스트 다시 만들기 
현재 리스트에 멤버정보 하나만 넘어오는 디테일만 구현되어 있음!

5.클럽에도 작성자콜론 만들어야할 듯
