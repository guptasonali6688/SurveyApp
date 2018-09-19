<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.zycus.service.*" %>
<%@ page import="com.zycus.entity.*" %> 
<%@ page import="com.zycus.*" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<script src="../jquery-3.3.1.min.js"></script>
<script>
$(document).ready(function(){
	console.log("dfsdf");
	
	$(".btn").click(function(){
		var id = $(this).attr("id").split("_")[0];
		var amount = $("#"+id+"_amount").val();

		if (amount == "")
		{
			alert("Please enter Amount.");
		}
		else
		{
			var form = document.createElement("form");
			form.method="POST";
			form.action = "/myApp3/payment.do";
			
			var ele1 = document.createElement("input");
			ele1.setAttribute("type", "text");
			ele1.setAttribute("name", "amount");
			ele1.setAttribute("value", amount);
			
			var ele2 = document.createElement("input");
			ele2.setAttribute("type", "text");
			ele2.setAttribute("name", "facId");
			ele2.setAttribute("value", id);
			
			form.appendChild(ele1);
			form.appendChild(ele2);
			form.style.display = "none";
			
			$("body").append(form);
			form.submit();
		}
	})
})
</script>
<body>
<% 

String id=session.getAttribute("userID").toString();
SurveyServiceImpl surveyserviceimpl= new SurveyServiceImpl();
List<Survey> survey=surveyserviceimpl.surveyList(Integer.parseInt(id));

Iterator<Survey> it= survey.iterator();

%>

<div id="menu">
				<ul>
					<li class="active"><a href="Login.html" accesskey="1" title="">Home page</a></li>
					<li><a href="Response.html" accesskey="2" title="">Your Response</a></li>
					<li><a href="#" accesskey="3" title="">Contact Us</a></li>
				</ul>
			</div>

<div id="wrapper">
	<div id="three-column" class="container">
		<div class="title">
			<h2>YOUR FACILITIES</h2>			
			<span class="byline">Easy LifeStyle</span>
		 <form>
					<br><br><br>
					<table align="center" border="2">
   					<tr>
        
        			<td>SurveyId</td>
        			<td>SurveyName</td>
        			<td>SurveyStatus</td>
        			
        
   					</tr>
   					<%for (Survey f:survey){
	   				%>
     
          			 <tr><td><%= f.getSid() %></td>
        
         			 <td><%= f.getSname()%></td>
           			<td><%= f.getSstatus()%></td>
          			
           			
					<td><input type="button" value="Attempt" name="submit" id="<%=f.getSid()%>_submit" class="btn"/></td></tr>

  					 <%
  					 }   
   
  					 %>
  					 
  					 
   					</table>
   					
   					<a  class ="btn" href="login.html"><button class ="btn"  type="button" >Back</button></a>
  
   					</form>
		</div>

</div>
</div>

</body>
</html>