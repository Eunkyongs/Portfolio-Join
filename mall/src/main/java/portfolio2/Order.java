package portfolio2;

import java.text.DecimalFormat;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Order {
	
	public Order() {
	}
	@PostMapping("/order")
	public String orders(@RequestParam("product_nm") String product_nm,
			@RequestParam("product_dtc") String product_dtc,
			@RequestParam("price") String price,
			@RequestParam("point") String point,
			@RequestParam("total") String total,
			@RequestParam("product_ea") String product_ea, 
			Model m) {
		
		m.addAttribute("product_nm",product_nm);
		m.addAttribute("product_dtc",product_dtc);
		DecimalFormat fr = new DecimalFormat("###,###");
		int price2 = Integer.parseInt(price);
		int total3 = Integer.parseInt(total);
		
		m.addAttribute("price",fr.format(price2));
		m.addAttribute("point",point);
		m.addAttribute("total",total); //실제 결제 금액
		m.addAttribute("total",fr.format(total3)); // 사용자가 보는 금액
		m.addAttribute("product_ea",product_ea);
		
		return "order";
	}
}
