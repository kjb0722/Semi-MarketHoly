
/* Drop Tables */

DROP TABLE order_product CASCADE CONSTRAINTS;
DROP TABLE refund_history CASCADE CONSTRAINTS;
DROP TABLE review CASCADE CONSTRAINTS;
DROP TABLE orders CASCADE CONSTRAINTS;
DROP TABLE point_history CASCADE CONSTRAINTS;
DROP TABLE qna CASCADE CONSTRAINTS;
DROP TABLE member CASCADE CONSTRAINTS;
DROP TABLE sale CASCADE CONSTRAINTS;
DROP TABLE product CASCADE CONSTRAINTS;
DROP TABLE cart CASCADE CONSTRAINTS;
DROP TABLE category CASCADE CONSTRAINTS;
DROP TABLE common CASCADE CONSTRAINTS;




/* Create Tables */

CREATE TABLE cart
(
	cartnum number primary key,
	id varchar2(25) NOT NULL,
	pnum number NOT NULL,
	EA number NOT NULL
);


CREATE TABLE category
(
	cnum number NOT NULL,
	type number,
	name varchar2(30),
	PRIMARY KEY (cnum)
);


CREATE TABLE common
(
	type varchar2(40) UNIQUE,
	conum number,
	name varchar2(50)
);


CREATE TABLE member
(
	num number primary key,
	id varchar2(25),
	pwd varchar2(25),
	name varchar2(30),
	rating number,
	email varchar2(40),
	birth varchar2(30),
	phone varchar2(30),
	gender number,
	addr varchar2(50),
	reg_date date,
	del_yn char,
	del_date date,
	point number,
	cartnum number references cart(cartnum)	
);



CREATE TABLE orders
(
	onum number NOT NULL,
	opnum number,
	status number,
	pay_yn number,
	reg_date date,
	end_date date,
	id varchar2(25),
	price number,
	num number references member(num),
	use_point number,
	sale_price number,
	pay_way number,
	PRIMARY KEY (onum)
);


CREATE TABLE order_product
(
	opnum number NOT NULL,
	pname varchar2(30),
	EA number,
	price number,
	onum number references orders(onum),
	pnum number references product(pnum),
	PRIMARY KEY (opnum)
);



CREATE TABLE point_history
(
	ponum number NOT NULL,
	id varchar2(35),
	use_vol number,
	reg_date date,
	del_yn char,
	num number references member(num),
	PRIMARY KEY (ponum)
);


CREATE TABLE product
(
	pnum number NOT NULL,
	cnum number references category(cnum),
    cartnum number references cart(cartnum),
	type number,
	name varchar2(40),
	reg_date date,
	del_yn char,
	price number,
	stock number,
	orgimg varchar2(50),
	saveimg varchar2(50),
	PRIMARY KEY (pnum)
);


CREATE TABLE qna
(
	rnum number,
	id varchar2(40),
	name varchar2(40),
	title varchar2(50),
	content varchar2(100),
	ref number,
	lev number,
	step number,
	regdate date,
	del_yn char,
	pnum number references product(pnum),
	num number references member(num)
);


CREATE TABLE refund_history
(
	reason varchar2(100),
	reg_date date,
	num number references member(num),
	onum number references orders(onum),
	UNIQUE (num, onum)
);


CREATE TABLE review
(
	rnum number,
	id varchar2(40),
	name varchar2(40),
	title varchar2(50),
	content varchar2(100),
	regdate date,
	del_yn char,
	pnum number references product(pnum),
	onum number references orders(onum),
	pwd varchar2(40),
	num number references member(num),
	CONSTRAINT primary UNIQUE (pnum, rnum)
);


CREATE TABLE sale
(
	snum number NOT NULL,
	pnum number references product(pnum),
	percent number,
	start_date date,
	end_date date,
	del_yn char,
	PRIMARY KEY (snum)
);



/* Comments */

COMMENT ON COLUMN member.gender IS '1:����
2:����';
COMMENT ON COLUMN member.del_yn IS 'y:����
n:�̻���';
COMMENT ON COLUMN orders.status IS '1:��ǰ �غ�
2:��� �غ�
3:��� ��
4:��� �Ϸ�
5:���� �Ϸ�
6:�ֹ� ���';
COMMENT ON COLUMN orders.pay_yn IS '1:�̰���
2:����';
COMMENT ON COLUMN orders.pay_way IS 'ī�� ����
������';