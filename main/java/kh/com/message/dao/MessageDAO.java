package kh.com.message.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import config.SqlSessionConfig;
import kh.com.message.dto.MessageDTO;

public class MessageDAO {
	// 싱글턴 구현
	private static MessageDAO messageDAO;
	private MessageDAO() {}
	
	public static MessageDAO getInstance() {
		if(messageDAO == null) {
			messageDAO = new MessageDAO();
		}
		return messageDAO;
	}
	
	public List<MessageDTO> search(Map map) {
		try(SqlSession session = SqlSessionConfig.getSqlSession()) {
			return session.selectList("messageMapper.search", map);
		}
	}
	
	public int delete(String[] seqArr) {
		try(SqlSession session = SqlSessionConfig.getSqlSession()){
			Map<String, String[]> map = new HashMap<>();
			map.put("array", seqArr);
			return session.delete("messageMapper.deleteMsg", map);
		}
	}
	
	public List<MessageDTO> getMessage(String nickname) throws Exception{
		try(SqlSession session = SqlSessionConfig.getSqlSession()) {
			return session.selectList("messageMapper.getMessage", nickname);
		}
	}
	
	public int insert(String to_nickname, String from_nickname, String content) {
		try(SqlSession session = SqlSessionConfig.getSqlSession()){
			Map<String, String> map = new HashMap<>();
			map.put("to_nickname", to_nickname);
			map.put("from_nickname", from_nickname);
			map.put("content", content);
			
			return session.insert("messageMapper.insertMsg", map);	// mapper가 실제로 없어도 지금은 String값인지 아닌지만 보기 때문에 에러가 나지 않음에 주의하자.
			
		}
	}
	
}
