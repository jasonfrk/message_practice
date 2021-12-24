package config;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/*
 * SqlSession 생성
 * 기존 JDBC getConnection 메서드를 사용하는 것과 비슷
 * -> 즉 SqlSession 객체를 생성해 반환받는 일이 DB에 접속해 세션을 만들어 반환받는 일과 동일
 * 
 * */

public class SqlSessionConfig {
	private static SqlSession session;
	
	// getSqlSession() 메서드를 호출하게 되면 기존에 DAO에서 this.getConnection() 하는 작업이랑 비슷하다고 생각하면 됨
	public static SqlSession getSqlSession() {
		try {
			// db관련 설정을 가지고 있는 mybatis-config.xml 파일을 먼저 읽어 옴
			String resource = "/mybatis-config.xml";
			InputStream stream = Resources.getResourceAsStream(resource);	// 설정파일을 읽어오는 작업
			
			// sqlSession 객체를 생성하기 위해서는 SqlSessionFactory 객체를 먼저 생성 -> SqlSessionFactoryBilder 먼저 생성
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
			SqlSessionFactory factory = builder.build(stream);
			session = factory.openSession(true);
			
			// openSession() 메서드에 주는 boolean 인자값
			// true -> auto commit 활성화
			// false -> auto commit 비활성화
		} catch(Exception e) {
			e.printStackTrace();
		}
		return session;
	}
}
