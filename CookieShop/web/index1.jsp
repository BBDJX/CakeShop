  <%--
    Created by IntelliJ IDEA.
    User: LR
    Date: 2025/1/2
    Time: 19:59
    To change this template use File | Settings | File Templates.
  --%>
  <%@ page contentType="text/html;charset=UTF-8" language="java" %>
  <html>
  <head>
    <title>节日活动公告</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link type="text/css" rel="stylesheet" href="css/bootstrap.css">
    <link type="text/css" rel="stylesheet" href="css/style.css">
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/simpleCart.min.js"></script>

    <script type="text/javascript"
            src="${pageContext.request.contextPath}/jquery-3.6.0.js"></script>
    <script language="JavaScript">
      function getInfo() {
        $.get("http://localhost:8080/getInfo.jsp?nocache=" + new Date().getTime(), function (data) {
          $("#showInfo").html(data);
        });
      }

      $(document).ready(function () {
        getInfo();//调用getInfo()方法获取公告信息
        window.setInterval("getInfo()", 600000);
      })
    </script>
  </head>
  <body>

  <!--header-->
  <jsp:include page="header.jsp">
    <jsp:param name="flag" value="17"></jsp:param>
  </jsp:include>
  <!--//header-->

  <!--cart-items-->
  <div class="cart-items">
    <div class="container">


      <h2 style="color: gold; font-size: 28px; font-weight: bold; background-image: url('https://png.pngtree.com/thumb_back/fw800/background/20190223/ourmid/pngtree-red-supermarket-dm-single-page-background-singleleafletsred-backgroundadvertising-dm-image_79135.jpg'); background-size: cover; background-position: center; padding: 20px; text-align: center;">节日活动盛大来袭</h2>

      <table class="table table-bordered table-hover">


        <section>
          <marquee id="showInfo" direction="up" scrollamount="3">
          </marquee>
        </section>

      </table>


    </div>
  </div>
  <!--//cart-items-->


  <!--footer-->
  <jsp:include page="footer.jsp"></jsp:include>
  <!--//footer-->
  </body>
  </html>
