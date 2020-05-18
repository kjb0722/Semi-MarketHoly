package com.market.qna.dto;

import java.sql.Date;

public class QnaDto {
	private int pnum;
	private int num;
	private int qnum;
	private String id;
	private String name;		
	private String title;
	private String content;
	private int ref;
	private int lev;
	private int step;
	private Date regdate;
	private String del_yn;
	private String pwd;
	
	public QnaDto() {}

	public QnaDto(int pnum, int num, int qnum, String id, String name, String title, String content, int ref, int lev,
			int step, Date regdate, String del_yn, String pwd) {
		super();
		this.pnum = pnum;
		this.num = num;
		this.qnum = qnum;
		this.id = id;
		this.name = name;
		this.title = title;
		this.content = content;
		this.ref = ref;
		this.lev = lev;
		this.step = step;
		this.regdate = regdate;
		this.del_yn = del_yn;
		this.pwd = pwd;
	}

	public int getPnum() {
		return pnum;
	}

	public void setPnum(int pnum) {
		this.pnum = pnum;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getQnum() {
		return qnum;
	}

	public void setQnum(int qnum) {
		this.qnum = qnum;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

	public int getRef() {
		return ref;
	}

	public void setRef(int ref) {
		this.ref = ref;
	}

	public int getLev() {
		return lev;
	}

	public void setLev(int lev) {
		this.lev = lev;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public String getDel_yn() {
		return del_yn;
	}

	public void setDel_yn(String del_yn) {
		this.del_yn = del_yn;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	
	
	
}
