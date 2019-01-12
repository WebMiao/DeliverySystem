<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="com.dv.bean.Sellers,com.dv.bean.Users"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div class="headerwrapper">
	<div id="header" class="container">
		<div class="logo"> 
			<a href="#"><img src="./image/LOGO.png" alt="logo" width="50" height="50"></a> 
			<a href="#"><img src="./image/Title.png" alt="title" width="200" height="50"></a> 
		</div> <!--end of Logo-->
        <nav>
            <ul id="navigations">
				<% if(session.getAttribute("sellerinfo")!=null){ 
					Sellers seller = (Sellers)session.getAttribute("sellerinfo");%>
                <li><a href="/DeliverySystem/idcheck?username=<%=seller.getName()%>&password=<%=seller.getPassword() %>">Welcome,<%= seller.getName() %>!</a></li>
                <li><a href = "/DeliverySystem/toSellControl">My Restaurant</a></li>
				<%} %>
            </ul>
        </nav>
      </div> <!--end of header-->
</div> <!-- end of headerwrapper-->

	<div style="margin:150px auto; padding:40px; font-size:1.2em; border-radius:10px; border:3px #ff8e22 solid;
		width:400px; background-color:#FFFFFB; text-align:center"><b>Fail to Take Order!</b></div>
</body>
</html>