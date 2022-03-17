package com.app.rests;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.News;

@RestController
public class NewsController {

	
		@GetMapping("/listnews")
		public List<News> listnews(){
			ArrayList<News> news = new ArrayList<>();
			News n = new News();
			n.setNewsId(new Long(1));
			n.setContent("Content Test");
			n.setCreatedTime(new Date());
			n.setShortContent("shortContent");
			news.add(n);
			return news;
		}
}
