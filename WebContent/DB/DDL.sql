
/* Drop Tables */

DROP TABLE cart CASCADE CONSTRAINTS;
DROP TABLE order_product CASCADE CONSTRAINTS;
DROP TABLE Prod_Info CASCADE CONSTRAINTS;
DROP TABLE qna CASCADE CONSTRAINTS;
DROP TABLE review CASCADE CONSTRAINTS;
DROP TABLE sale CASCADE CONSTRAINTS;
DROP TABLE product CASCADE CONSTRAINTS;
DROP TABLE category CASCADE CONSTRAINTS;
DROP TABLE common CASCADE CONSTRAINTS;
DROP TABLE refund_history CASCADE CONSTRAINTS;
DROP TABLE orders CASCADE CONSTRAINTS;
DROP TABLE point_history CASCADE CONSTRAINTS;
DROP TABLE member CASCADE CONSTRAINTS;




/* Create Tables */

CREATE TABLE cart
(
	cartnum number NOT NULL,
	pnum number NOT NULL,
	id varchar2(25) NOT NULL,
	EA number NOT NULL,
	PRIMARY KEY (cartnum)
);


CREATE TABLE category
(
	cnum number NOT NULL,
	type number,
	name varchar2(30),
	del_yn varchar2(2),
	PRIMARY KEY (cnum)
);


CREATE TABLE common
(
	type varchar2(40),
	conum number NOT NULL,
	name varchar2(50),
	val varchar2(30),
	PRIMARY KEY (conum)
);


CREATE TABLE member
(
	num number NOT NULL,
	id varchar2(25),
	pwd varchar2(25),
	name varchar2(30),
	rating number,
	email varchar2(40),
	birth varchar2(30),
	phone varchar2(30),
	-- 1:����
	-- 2:����
	gender number,
	addr varchar2(100),
	reg_date date,
	point number,
	-- y:����
	-- n:�̻���
	del_yn varchar2(2),
	del_date date,
	PRIMARY KEY (num)
);


CREATE TABLE orders
(
	onum number NOT NULL,
	num number NOT NULL,
	-- 1:��ǰ �غ�
	-- 2:��� �غ�
	-- 3:��� ��
	-- 4:��� �Ϸ�
	-- 5:���� �Ϸ�
	-- 6:�ֹ� ���
	status number,
	-- N:�̰���
	-- Y:����
	pay_yn varchar2(2),
	reg_date date,
	end_date date,
	id varchar2(25),
	price number,
	use_point number,
	sale_price number,
	-- ī�� ����
	-- ������
	pay_way number,
	addr varchar2(100),
	PRIMARY KEY (onum)
);


CREATE TABLE order_product
(
	opnum number NOT NULL,
	onum number NOT NULL,
	pnum number NOT NULL,
	pname varchar2(30),
	EA number,
	price number,
	PRIMARY KEY (opnum)
);


CREATE TABLE point_history
(
	ponum number NOT NULL,
	num number NOT NULL,
	id varchar2(35),
	use_vol number,
	reg_date date,
	del_yn varchar2(2),
	PRIMARY KEY (ponum)
);


CREATE TABLE product
(
	pnum number NOT NULL,
	cnum number NOT NULL,
	type number,
	name varchar2(70),
	price number,
	stock number,
	description varchar2(200),
	thumb_org varchar2(100),
	thumb_save varchar2(100),
	detail_org varchar2(100),
	detail_save varchar2(100),
	reg_date date,
	del_yn varchar2(2),
	PRIMARY KEY (pnum)
);


CREATE TABLE Prod_Info
(
	pnum number NOT NULL,
	unit varchar2(20),
	volume varchar2(30),
	origin varchar2(30),
	pack_type varchar2(40),
	shelf_life varchar2(50),
	guidance varchar2(50)
);


CREATE TABLE qna
(							
	pnum number NOT NULL,
	num number NOT NULL,
	qnum number,			/*qnum과 rnum 자꾸 에러뜸*/
	id varchar2(40),
	name varchar2(40),
	title varchar2(50),
	content varchar2(100),
	ref number,
	reg_date date,
	del_yn varchar2(2),
	locker varchar2(2)
);


CREATE TABLE refund_history
(
	num number NOT NULL,
	onum number NOT NULL,
	reason varchar2(100),
	reg_date date
);


CREATE TABLE review
(
	onum number NOT NULL,
	pnum number NOT NULL,
	num number NOT NULL,
	rnum number NOT NULL,	/*qnum과 rnum 자꾸 에러뜸*/
	id varchar2(40),
	name varchar2(40),
	title varchar2(50),
	content varchar2(100),
	reg_date date,
	orgfilename varchar2(150),
	savefilename varchar2(150),
	del_yn varchar2(2),
	PRIMARY KEY (rnum)
);


CREATE TABLE sale
(
	snum number NOT NULL,
	pnum number NOT NULL,
	name varchar2(50),
	percent number,
	start_date date,
	end_date date,
	del_yn varchar2(2),
	PRIMARY KEY (snum)
);



/* Create Foreign Keys */

ALTER TABLE product
	ADD FOREIGN KEY (cnum)
	REFERENCES category (cnum)
;


ALTER TABLE orders
	ADD FOREIGN KEY (num)
	REFERENCES member (num)
;


ALTER TABLE point_history
	ADD FOREIGN KEY (num)
	REFERENCES member (num)
;


ALTER TABLE qna
	ADD FOREIGN KEY (num)
	REFERENCES member (num)
;


ALTER TABLE refund_history
	ADD FOREIGN KEY (num)
	REFERENCES member (num)
;


ALTER TABLE review
	ADD FOREIGN KEY (num)
	REFERENCES member (num)
;


ALTER TABLE order_product
	ADD FOREIGN KEY (onum)
	REFERENCES orders (onum)
;


ALTER TABLE refund_history
	ADD FOREIGN KEY (onum)
	REFERENCES orders (onum)
;


ALTER TABLE review
	ADD FOREIGN KEY (onum)
	REFERENCES orders (onum)
;


ALTER TABLE cart
	ADD FOREIGN KEY (pnum)
	REFERENCES product (pnum)
;


ALTER TABLE order_product
	ADD FOREIGN KEY (pnum)
	REFERENCES product (pnum)
;


ALTER TABLE Prod_Info
	ADD FOREIGN KEY (pnum)
	REFERENCES product (pnum)
;


ALTER TABLE qna
	ADD FOREIGN KEY (pnum)
	REFERENCES product (pnum)
;


ALTER TABLE review
	ADD FOREIGN KEY (pnum)
	REFERENCES product (pnum)
;


ALTER TABLE sale
	ADD FOREIGN KEY (pnum)
	REFERENCES product (pnum)
;



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
COMMENT ON COLUMN orders.pay_yn IS 'N:�̰���
Y:����';
COMMENT ON COLUMN orders.pay_way IS 'ī�� ����
������';



