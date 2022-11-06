package portfolio2.join;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JoinController {

	@Inject
	JoinService joinService;
	
	//회원가입화면 로드
	@RequestMapping("/join")
	public String memberjoin() {
		return "member";
	}

	//회원가입 처리
	@RequestMapping("/joinok")
	public String joinok(JoinDTO joinVO, Model m) throws Exception {
			String call = joinService.insert(joinVO);
			if (call == "ok") {
				m.addAttribute("msg",call);
			} else {
				m.addAttribute("msg","error");
			}
			return "joinok";
	}
}
