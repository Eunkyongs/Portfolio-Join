package portfolio2.join;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public class JoinServiceImpl implements JoinService {

	@Inject
	private SqlSessionTemplate session;
	
	private static String namespace="query";
	
	//※xml에서 사용하는id값이 중복 또는 오타가 발생할 경우 연결되지 않습니다.
	//session.selectList : 여러개의 데이터를 가져올 때 사용
	//session.selectOne : 1개의 데이터를 가져올 때 사용
	//selectMap : Object 데이터 입력을 사용함(배열형태일때 많이 사용)
	//session.insert :  입력파트
	//session.update :  수정파트
	//session.delete :  삭제파트
	
	@Override
	public JoinDTO selectId(String id) throws Exception {
		JoinDTO join = session.selectOne(namespace+".idcheck",id);
		return join;
	}
	
	@Override
	public String insert(JoinDTO joinVO) throws Exception {
		String sign = null;
		try {
		session.insert(namespace+".join",joinVO);
		sign ="ok";
		}catch(Exception e) {
			e.printStackTrace();
		}
		return sign;
	}



}
