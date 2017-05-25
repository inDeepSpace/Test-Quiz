package cn.com.wnkd;

import java.util.ArrayList;
import java.util.List;

public class QuizBean {
     private  String title;
     private  String content;
     private  int id;
     private List<Chioce> choices;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<Chioce> getChoices() {
		if (null == this.choices) {
			this.choices = new ArrayList<Chioce>();
		}
		return choices;
	}
	public void setChoices(List<Chioce> choices) {
		this.choices = choices;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
