
/* Drop Tables */

DROP TABLE cart CASCADE CONSTRAINTS;
DROP TABLE order_product CASCADE CONSTRAINTS;
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
	PRIMARY KEY (cnum)
);


CREATE TABLE common
(
	type varchar2(40),
	conum number,
	name varchar2(50),
	val varchar2(50)
);

CREATE TABLE member
(
	num number NOT NULL,
	id varchar2(25) NOT NULL,
	pwd varchar2(25) NOT NULL,
	name varchar2(30) NOT NULL,
	rating number,
	email varchar2(40) NOT NULL unique,
	birth varchar2(30),
	phone varchar2(30) NOT NULL,
	gender number,
	addr varchar2(100) NOT NULL,
	reg_date date,
	point number,
	del_yn varchar2(2),
	del_date date,
	PRIMARY KEY (num)
);

alter table member add unique(email);


CREATE TABLE orders
(
	onum number NOT NULL,
	num number NOT NULL,
	opnum number,
	-- 1:상품 준비
	-- 2:배송 준비
	-- 3:배송 중
	-- 4:배송 완료
	-- 5:구매 완료
	-- 6:주문 취소
	status number,
	-- 1:미결제
	-- 2:결제
	pay_yn number,
	reg_date date,
	end_date date,
	id varchar2(25),
	price number,
	use_point number,
	sale_price number,
	-- 카드 결제
	-- 무통장
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
	name varchar2(40),
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
	rnum number primary key,
	id varchar2(40),
	name varchar2(40),
	title varchar2(50),
	content varchar2(100),
	regdate date,
	orgfilename varchar2(150),		--전송된 파일명
	savefilename varchar2(150),		--저장된 파일명
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

DROP SEQUENCE seq_review_num;
CREATE SEQUENCE seq_review_num;

DROP SEQUENCE seq_cart_num;
CREATE SEQUENCE seq_cart_num;

DROP SEQUENCE seq_category_cnum_type;
CREATE SEQUENCE seq_category_cnum_type;

DROP SEQUENCE seq_common_conum;
CREATE SEQUENCE seq_common_conum;

DROP SEQUENCE seq_member_num;
CREATE SEQUENCE seq_member_num;

DROP SEQUENCE seq_orders_onum;
CREATE SEQUENCE seq_orders_onum;

DROP SEQUENCE seq_order_product_opnum;
CREATE SEQUENCE seq_order_product_opnum;

DROP SEQUENCE seq_point_history_ponum;
CREATE SEQUENCE seq_point_history_ponum;

DROP SEQUENCE seq_product_pnum;
CREATE SEQUENCE seq_product_pnum;

DROP SEQUENCE seq_sale_snum;
CREATE SEQUENCE seq_sale_snum;

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

COMMENT ON COLUMN member.gender IS '1:남자
2:여자';
COMMENT ON COLUMN member.del_yn IS 'y:삭제
n:미삭제';
COMMENT ON COLUMN orders.status IS '1:상품 준비
2:배송 준비
3:배송 중
4:배송 완료
5:구매 완료
6:주문 취소';
COMMENT ON COLUMN orders.pay_yn IS '1:미결제
2:결제';
COMMENT ON COLUMN orders.pay_way IS '카드 결제
무통장';



