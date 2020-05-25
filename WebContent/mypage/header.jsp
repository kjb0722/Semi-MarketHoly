<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>



<style>
	#aa ul li{list-style-type: none; display:inline; margin-left: 40px;}

</style>

<div id="snb" class="snb_my">
	<h2 class="tit_snb">&nbsp&nbsp&nbsp 마이컬리</h2>
	<div id ="aa">
		<ul id =list1 class="list_menu">
			<li><a href="${cp }/mypage/mypageOrderList.do">주문내역</a></li>	
			<li><a href="${cp }/mypage/mypageReview.do">내가 작성한 상품후기</a></li>	
			<li><a href="${cp }/mypage/mypageQna.do">내가 작성한 qna</a></li>	
			<li><a href="${cp }/member/startChangeInfo.do">개인정보수정</a></li>		
			<li><a href="${cp }/mypage/mypageShowPoint.do">포인트</a></li>	
		</ul>
	</div>
</div>



