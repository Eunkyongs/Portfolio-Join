package portfolio2;

import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Cs {
	String sql;

	@Autowired
	BasicDataSource dataSource;
	
	@RequestMapping("/cs")
	public String page(HttpServletRequest req, Model m) throws Exception{
		String path =req.getServletContext().getRealPath("");
		
		String cate = req.getParameter("cate");
		Connection con = dataSource.getConnection();
		
		sql=null;
		
		if(cate != null && cate != "") {
			sql ="select * from FAQ_board where viewdata='Y' and category='"+cate+"'";
		}
		else {
			sql="select * from FAQ_board where viewdata='Y' limit 0,5";
		}
		
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		ArrayList<Map<String, String>> data = new ArrayList<Map<String,String>>();
		while(rs.next()) {
			Map<String, String> mp = new HashMap<String, String>();
			String msg = "";
			switch(rs.getString("category")) {
			case "1":
				msg="배송문의";
				break;
			case "2":
				msg="반품/교환문의";
				break;
			case "3":
				msg="상품문의";
				break;
			case "4":
				msg="쿠폰내역";
				break;
			case "5":
				msg="마일리지내역";
				break;
			default:
				msg="자주묻는질문";
				break;
			}
			
			mp.put("no", rs.getString("no"));
			mp.put("category", msg);
			mp.put("writer", rs.getString("writer"));
			mp.put("question", rs.getString("question"));
			mp.put("answer", rs.getString("answer"));
			mp.put("viewdata", rs.getString("viewdata"));
			mp.put("writedate", rs.getString("writedate"));
			data.add(mp);
		}
		m.addAttribute("data",data);
		con.close();
		return "cs";
	}
}
