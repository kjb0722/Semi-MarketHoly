
/* Drop Tables */

DROP TABLE order_product CASCADE CONSTRAINTS;
DROP TABLE qna CASCADE CONSTRAINTS;
DROP TABLE review CASCADE CONSTRAINTS;
DROP TABLE sale CASCADE CONSTRAINTS;
DROP TABLE product CASCADE CONSTRAINTS;
DROP TABLE cart CASCADE CONSTRAINTS;
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
	id varchar2(25) NOT NULL,
	pnum number NOT NULL,
	EA number NOT NULL,
	PRIMARY KEY (cartnum)
);

DROP SEQUENCE seq_cart_num;
CREATE SEQUENCE seq_cart_num;

CREATE TABLE category
(
	cnum number NOT NULL,
	type number,
	name varchar2(30),
	PRIMARY KEY (cnum)
);

DROP SEQUENCE seq_category_cnum;
CREATE SEQUENCE seq_category_cnum;

CREATE TABLE common
(
	type varchar2(40) UNIQUE,
	conum number,
	name varchar2(50)
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
	addr varchar2(50),
	reg_date date,
	point number,
	-- y:����
	-- n:�̻���
	del_yn varchar2(2),
	del_date date,
	PRIMARY KEY (num)
);

DROP SEQUENCE seq_member_num;
CREATE SEQUENCE seq_member_num;

CREATE TABLE orders
(
	onum number NOT NULL,
	num number NOT NULL,
	opnum number,
	-- 1:��ǰ �غ�
	-- 2:��� �غ�
	-- 3:��� ��
	-- 4:��� �Ϸ�
	-- 5:���� �Ϸ�
	-- 6:�ֹ� ���
	status number,
	-- 1:�̰���
	-- 2:����
	pay_yn number,
	reg_date date,
	end_date date,
	id varchar2(25),
	price number,
	use_point number,
	sale_price number,
	-- ī�� ����
	-- ������
	pay_way number,
	PRIMARY KEY (onum)
);

DROP SEQUENCE seq_orders_onum;
CREATE SEQUENCE seq_orders_onum;

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

DROP SEQUENCE seq_order_product_opnum;
CREATE SEQUENCE seq_order_product_opnum;

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

DROP SEQUENCE seq_point_history_ponum;
CREATE SEQUENCE seq_point_history_ponum;

CREATE TABLE product
(
	pnum number NOT NULL,
	cnum number NOT NULL,
	cartnum number NOT NULL,
	name varchar2(40),
	reg_date date,
	price number,
	stock number,
	type number,
	thumb_org varchar2(100),
	thumb_save varchar2(100),
	description varchar2(200),
	detail_org varchar2(100),
	detail_save varchar2(100),
	del_yn varchar2(2),
	PRIMARY KEY (pnum)
);

DROP SEQUENCE seq_product_pnum;
CREATE SEQUENCE seq_product_pnum;

CREATE TABLE qna
(
	pnum number NOT NULL,
	num number NOT NULL,
	rnum number,
	id varchar2(40),
	name varchar2(40),
	title varchar2(50),
	content varchar2(100),
	ref number,
	lev number,
	step number,
	regdate date,
	del_yn varchar2(2),
	UNIQUE (pnum, num)
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
	rnum number,
	id varchar2(40),
	name varchar2(40),
	title varchar2(50),
	content varchar2(100),
	regdate date,
	del_yn varchar2(2),
	pwd varchar2(40),
	UNIQUE (onum, pnum, num)
);


CREATE TABLE sale
(
	snum number NOT NULL,
	pnum number NOT NULL,
	percent number,
	start_date date,
	end_date date,
	del_yn varchar2(2),
	PRIMARY KEY (snum)
);

DROP SEQUENCE seq_sale_snum;
CREATE SEQUENCE seq_sale_snum;

/* Create Foreign Keys */

ALTER TABLE product
	ADD FOREIGN KEY (cartnum)
	REFERENCES cart (cartnum)
;


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


ALTER TABLE order_product
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
COMMENT ON COLUMN orders.pay_yn IS '1:�̰���
2:����';
COMMENT ON COLUMN orders.pay_way IS 'ī�� ����
������';



