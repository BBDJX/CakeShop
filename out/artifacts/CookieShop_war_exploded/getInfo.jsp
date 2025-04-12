<%--
  Created by IntelliJ IDEA.
  User: LR
  Date: 2025/1/2
  Time: 20:10
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page language="java" contentType="text/html;charset=UTF-8"--%>
<%--         pageEncoding="UTF-8" %>--%>
<%--<%@page import="java.sql.*" %>--%>
<%--<%@ page import="test.ConnDB" %>--%>
<%--<%--%>
<%--  // 假设你有一个数据库连接工具类ConnDB用于获取数据库连接，这里需要根据实际情况调整--%>
<%--  ConnDB connDB = new ConnDB();--%>
<%--  Connection conn = null;--%>
<%--  PreparedStatement pstmt = null;--%>
<%--  ResultSet rs = null;--%>
<%--  try {--%>
<%--    conn = connDB.getConnection();--%>
<%--    String sql = "select holiday_name, start_date, end_date, discount, description from holiday_activities";--%>
<%--    pstmt = conn.prepareStatement(sql);--%>
<%--    rs = pstmt.executeQuery();--%>
<%--%>--%>
<%--<ul>--%>
<%--  <%--%>
<%--    while (rs.next()) {--%>
<%--      String holidayName = rs.getString("holiday_name");--%>
<%--      String startDate = rs.getString("start_date");--%>
<%--      String endDate = rs.getString("end_date");--%>
<%--      int discount = rs.getInt("discount");--%>
<%--      String description = rs.getString("description");--%>
<%--  %>--%>
<%--  <li>--%>
<%--    <h3><%= holidayName %></h3>--%>
<%--    <p>活动日期：<%= startDate %> - <%= endDate %></p>--%>
<%--    <p>折扣：<%= discount %>%</p>--%>
<%--    <p><%= description %></p>--%>
<%--  </li>--%>
<%--  <%--%>
<%--    }--%>
<%--  %>--%>
<%--</ul>--%>
<%--<%--%>
<%--  } catch (SQLException e) {--%>
<%--    e.printStackTrace();--%>
<%--  } finally {--%>
<%--    // 关闭资源，确保释放数据库连接、语句对象和结果集对象--%>
<%--    try {--%>
<%--      if (rs!= null) {--%>
<%--        rs.close();--%>
<%--      }--%>
<%--      if (pstmt!= null) {--%>
<%--        pstmt.close();--%>
<%--      }--%>
<%--      if (conn!= null) {--%>
<%--        conn.close();--%>
<%--      }--%>
<%--    } catch (SQLException e) {--%>
<%--      e.printStackTrace();--%>
<%--    }--%>
<%--  }--%>
<%--%>--%>


<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@page import="java.sql.*" %>
<jsp:useBean id="conn" class="test.ConnDB" scope="page"/>

<%
  ResultSet rs = null;
  try {
    rs = conn.executeQuery("select holiday_name, start_date, end_date, discount, description from holiday_activities");
%>
<!DOCTYPE html>
<html>
<head>
  <title>公告信息</title>
  <meta charset="UTF-8">
  <style>
    /* 定义整体页面的背景图片样式 */
    body {
      background-image: url('https://png.pngtree.com/thumb_back/fw800/background/20190223/ourmid/pngtree-red-supermarket-dm-single-page-background-singleleafletsred-backgroundadvertising-dm-image_79135.jpg');
      background-size: cover;
      background-position: center;
      font-family: Arial, sans-serif;
      padding: 20px;
    }

    /* 定义公告列表的样式 */
    ul {
      list-style-type: none;
      padding: 0;
      margin: 0;
      background-color: rgba(255, 255, 255, 0.8); /* 设置半透明白色背景，使其文字更易读 */
      border-radius: 10px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
      padding: 20px;
    }

    /* 定义每个公告项li的样式 */
    li {
      margin-bottom: 20px;
      padding: 15px;
      border-bottom: 1px solid #ccc;
    }

    h2 {
      color: #e67e22; /* 标题颜色，可根据喜好调整 */
      margin-top: 0;
    }

    p {
      margin: 5px 0;
    }
  </style>
</head>
<body>

<%
  if (rs.next()) {
    out.print("<li>");
    out.print("<h2>" + rs.getString("holiday_name") + "</h2>");
    out.print("<p>活动日期：" + rs.getString("start_date") + " - " + rs.getString("end_date") + "</p>");
    out.print("<p>折扣：" + rs.getString("discount") + "%</p>");
    out.print("<p>描述：" + rs.getString("description") + "</p>");
    out.print("</li>");
  } else {
    out.print("<li>");
    out.print("<h2>美食优惠活动公告</h2>");
    out.print("<p>亲爱的吃货朋友们，节假日是享受美食的好时机，我们店内的各种零食、干货正在大促销，满足您的味蕾需求！无论是香浓的巧克力、甜蜜的糖果还是营养丰富的坚果，这里应有尽有。现在购买还可享受第二件半价的优惠，让您尽情品尝美味。</p>");
    out.print("<p>此外，我们还提供了特色小吃作为赠品，只要您的购买金额达到一定数额，就可以免费尝鲜。这种优惠不仅能让您在家慢慢享受美食，还能与家人朋友一起分享欢乐的时光。</p>");
    out.print("<p>快来囤货吧，让这个节假日充满美味与欢笑！</p>");
    out.print("</li>");
  }
%>
</ul>
<%
  } catch (SQLException e) {
    e.printStackTrace();
  } finally {
    // 关闭资源，确保释放结果集对象（假设conn内部会自行管理连接等其他资源关闭）
    try {
      if (rs!= null) {
        rs.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
%>
</body>
</html>

<%--<%@ page language="java" contentType="text/html;charset=UTF-8"--%>
<%--         pageEncoding="UTF-8" %>--%>
<%--<%@page import="java.sql.*" %>--%>
<%--<jsp:useBean id="connDB" class="test.ConnDB" scope="page"/>--%>

<%--<%--%>
<%--  Connection conn = null;--%>
<%--  PreparedStatement pstmt = null;--%>
<%--  ResultSet rs = null;--%>
<%--  try {--%>
<%--    conn = connDB.getConnection();--%>
<%--    String sql = "select holiday_name, start_date, end_date, discount, description from holiday_activities";--%>
<%--    pstmt = conn.prepareStatement(sql);--%>
<%--    rs = pstmt.executeQuery();--%>
<%--%>--%>
<%--<ul>--%>
<%--  <%--%>
<%--    while (rs.next()) {--%>
<%--      String holidayName = rs.getString("holiday_name");--%>
<%--      String startDate = rs.getString("start_date");--%>
<%--      String endDate = rs.getString("end_date");--%>
<%--      int discount = rs.getInt("discount");--%>
<%--      String description = rs.getString("description");--%>

<%--      // 使用out.print()拼接并输出HTML内容来展示公告信息--%>
<%--      out.print("<li>");--%>
<%--      out.print("<h3>" + holidayName + "</h3>");--%>
<%--      out.print("<p>活动日期：" + startDate + " - " + endDate + "</p>");--%>
<%--      out.print("<p>折扣：" + discount + "%</p>");--%>
<%--      out.print("<p>" + description + "</p>");--%>
<%--      out.print("</li>");--%>
<%--    }--%>

<%--    if (!rs.next()) {--%>
<%--      out.print("<li>暂无公告信息！</li>");--%>
<%--    }--%>
<%--  %>--%>
<%--</ul>--%>
<%--<%--%>
<%--  } catch (SQLException e) {--%>
<%--    e.printStackTrace();--%>
<%--  } finally {--%>
<%--    // 关闭资源，确保释放数据库连接、语句对象和结果集对象--%>
<%--    try {--%>
<%--      if (rs!= null) {--%>
<%--        rs.close();--%>
<%--      }--%>
<%--      if (pstmt!= null) {--%>
<%--        pstmt.close();--%>
<%--      }--%>
<%--      if (conn!= null) {--%>
<%--        conn.close();--%>
<%--      }--%>
<%--    } catch (SQLException e) {--%>
<%--      e.printStackTrace();--%>
<%--    }--%>
<%--  }--%>
<%--%>--%>

