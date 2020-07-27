package siru.md.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import siru.md.domain.ChartVo;

@Controller
@RequestMapping("chart")
public class ChartController {
	
	@RequestMapping("chart")
	public String showView() {
		return "chart/chart";
	}
	
	@PostMapping("chartData")
	public @ResponseBody List<ChartVo> chartData() {
		List<ChartVo> list = new ArrayList<ChartVo>();
		String items[] = {"애플", "MS", "아마존", "구글", "페이스북", "테슬라", "넷플릭스"};
		int numbers[] = {16800, 15800, 15700, 10600, 6893, 2907, 2155};
		for(int i=0; i<items.length; i++) {
			ChartVo vo = new ChartVo(items[i], numbers[i]);
			list.add(vo);
		}
		return list;
	}
	
	@PostMapping("chartDataRan")
	public @ResponseBody List<ChartVo> chartDataRan() {
		List<ChartVo> list = new ArrayList<ChartVo>();
		String items[] = {"애플", "MS", "아마존", "구글", "페이스북", "테슬라", "넷플릭스"};
		//int numbers[] = {16800, 15800, 15700, 10600, 6893, 2907, 2155};
		Random r = new Random();
		for(int i=0; i<items.length; i++) {
			ChartVo vo = new ChartVo(items[i], r.nextInt(100));
			list.add(vo);
		}
		return list;
	}
	
}
