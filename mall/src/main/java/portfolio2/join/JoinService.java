package portfolio2.join;

public interface JoinService {
	
	public String insert(JoinDTO joinVO) throws Exception;
	
	public JoinDTO selectId(String id) throws Exception;
		
}
