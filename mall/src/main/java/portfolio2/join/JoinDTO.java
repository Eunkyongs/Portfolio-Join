package portfolio2.join;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class JoinDTO {
	
	private String idx;
	private String userid;
	private String userpw;
	private String username;
	private String useremail;
	private String userphone;
	private String userzip;
	private String useraddr;
	private Date joindate;

}
