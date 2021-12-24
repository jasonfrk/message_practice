package kh.com.message.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import config.SqlSessionConfig;
import kh.com.message.dto.MemDTO;
import kh.com.message.dto.MemberDTO;

public class MemberDAO {
	private static MemberDAO memberDAO;
	private MemberDAO() {}
	
	public static MemberDAO getInstance() {
		if(memberDAO == null) {
			memberDAO = new MemberDAO();
		}
		return memberDAO;
	}
	
	// 아이디로 멤버정보 가져오기
	public MemberDTO getMember(String id) {
		try(SqlSession session = SqlSessionConfig.getSqlSession();){
			return session.selectOne("memberMapper.getMember", id);
		}
	}
	
	// 회원가입을 위해 insert 메서드 생성
	public int insert(MemberDTO dto) {
		try(SqlSession session = SqlSessionConfig.getSqlSession()){
			return session.insert("memberMapper.insertMember", dto);
		}
	}
	
	// 아아디 중복확인 메서드 생성
	public boolean checkId(String id){
		// SqlSession을 통해 Connection을 얻어온다. 정확하게 Connection과 일치하는 것은 아님.
		try(SqlSession session = SqlSessionConfig.getSqlSession()){
			return session.selectOne("memberMapper.checkId", id);
		}
	}
	
	// 아이디 로그인 확인 및 세션추가
	public MemberDTO isLoginOk(String id, String pw) throws Exception {
		try(SqlSession session = SqlSessionConfig.getSqlSession();){
			Map<String, String> map = new HashMap<>();
			map.put("id", id);
			map.put("pw", pw);
			return session.selectOne("memberMapper.isLoginOk", map);
		}
	}
//	public MemDTO isLoginOk(String id, String pw) throws Exception {
//		try(SqlSession session = SqlSessionConfig.getSqlSession();){
//			Map<String, String> map = new HashMap<>();
//			map.put("id", id);
//			map.put("pw", pw);
//			return session.selectOne("memberMapper.isLoginOk", map);
//		}
//	}
	
	// 닉네임 반환
	public List<String> getNicknames(String nickname) throws Exception {
		try(SqlSession session = SqlSessionConfig.getSqlSession()){
			return session.selectList("memberMapper.getNicknames", nickname);
			
		}
	}
	
	// 정보 수정
	public int update(MemberDTO dto) {
		try(SqlSession session = SqlSessionConfig.getSqlSession()){
			return session.update("memberMapper.updateInfo", dto);
		}
	}
	
}
