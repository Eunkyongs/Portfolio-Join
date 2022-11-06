package portfolio2.notice;

import java.sql.Connection;
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
public class Notice {

	@Autowired
	BasicDataSource dataSource;

	@RequestMapping("/notice")
	public String n(HttpServletRequest req, Model m) throws Exception {
		String pgno = req.getParameter("p");
		
		int pageview = 5; // 한페이지당 보여지는 데이터 개수
		m.addAttribute("pageview",pageview);
		int startpage = 0; //시작 페이지 값
		double pagenumber = 1; //페이징 개수
		String pno = pgno;
		String ct = "select count(*) as ct from notice"; // 데이터의 총 개수 확인
		int total = 0;
		PreparedStatement psct = null;
		PreparedStatement ps = null;
		
		ArrayList<Map<String,String>> list = new ArrayList<Map<String,String>>();
		Connection con = dataSource.getConnection();
			psct=con.prepareStatement(ct);
			ResultSet rsct = psct.executeQuery();
			
			while(rsct.next()){
			total = rsct.getInt("ct");
			}
			m.addAttribute("total",total);
			if(pno==null || pno == ""){
				startpage = 0;
			}else{//페이지 2번부터 적용되는 사항
				startpage = (Integer.parseInt(pno)-1)*pageview;
			}
			
			//페이징 총 개수 파악
			if(total % pageview == 0){
				pagenumber=total/pageview;
			}else{
				pagenumber=(total/pageview)+1;
			}
			
			String sql = "select @rownum := @rownum+1 as rownum, notice.* from notice, (select @rownum := 0)=n order by top, rownum desc limit "+startpage+","+pageview;
			ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Map<String, String> mp = new HashMap<String, String>();
				mp.put("no",rs.getString("rownum"));
				mp.put("title",rs.getString("title"));
				mp.put("writer",rs.getString("writer"));
				mp.put("indate",rs.getString("indate").substring(0,10));
				mp.put("views",rs.getString("views"));
				mp.put("contents",rs.getString("contents"));
				mp.put("file",rs.getString("file"));
				list.add(mp);
			}
			m.addAttribute("notice",list);
			con.close();
		return "notice";
	}
}
