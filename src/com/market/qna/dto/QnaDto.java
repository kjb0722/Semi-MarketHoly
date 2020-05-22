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
	private Date reg_date;
	private String del_yn;
	private String locker;
	private int level;
	private int rnum2;
	
	public QnaDto() {}



	public QnaDto(int pnum, int num, int qnum, String id, String name, String title, String content, int ref,
			Date reg_date, String del_yn, String locker, int level, int rnum2) {
		super();
		this.pnum = pnum;
		this.num = num;
		this.qnum = qnum;
		this.id = id;
		this.name = name;
		this.title = title;
		this.content = content;
		this.ref = ref;
		this.reg_date = reg_date;
		this.del_yn = del_yn;
		this.locker = locker;
		this.level = level;
		this.rnum2= rnum2;
	}



	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
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

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	public String getDel_yn() {
		return del_yn;
	}

	public void setDel_yn(String del_yn) {
		this.del_yn = del_yn;
	}

	public String getLocker() {
		return locker;
	}

	public void setLocker(String locker) {
		this.locker = locker;
	}



	public int getRnum2() {
		return rnum2;
	}



	public void setRnum2(int rnum2) {
		this.rnum2 = rnum2;
	}




	
	
	
}
