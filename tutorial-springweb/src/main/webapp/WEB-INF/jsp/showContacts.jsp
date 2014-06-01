<%@include file="taglib_includes.jsp"%>

<html>
<head>
   <title>
      <spring:message code="App.title"></spring:message>
   </title>

   <script src="js/contacts.js" type="text/javascript"></script>
</head>

<body style="font-family: Arial; font-size: smaller;">
<center>
   <form action="searchContacts.do" method="post">
      <table border="0" bordercolor="#006699" style="border-collapse: collapse; width: 500px;">
         <tbody>
         <tr>
            <td>Enter Contact Name</td>
            <td>
               <input name="name" type="text">
               <input type="submit" value="Search">
               <input onclick="javascript:go('saveContact.do');" type="button" value="New Contact">
            </td>
         </tr>
         </tbody>
      </table>
   </form>

   <c:if test="${empty SEARCH_CONTACTS_RESULTS_KEY}"></c:if>
   <c:if test="${! empty SEARCH_CONTACTS_RESULTS_KEY}">
      <c:forEach items="${SEARCH_CONTACTS_RESULTS_KEY}" var="contact"></c:forEach>
   </c:if>

   <table border="1" bordercolor="#006699" style="border-collapse: collapse; width: 500px;">
      <tbody>
      <tr bgcolor="lightblue">
         <th>Id</th>
         <th>Name</th>
         <th>Address</th>
         <th>Mobile</th>
         <th></th>
      </tr>

      <tr>
         <td colspan="4">No Results found</td>
      </tr>

      <tr>
         <td>
            <c:out value="${contact.id}"></c:out>
         </td>
         <td>
            <c:out value="${contact.name}"></c:out>
         </td>
         <td>
            <c:out value="${contact.address}"></c:out>
         </td>
         <td>
            <c:out value="${contact.mobile}"></c:out>
         </td>
         <td>
            <a href="updateContact.do?id=${contact.id}">Edit</a>
            <a href="javascript:deleteContact('deleteContact.do?id=${contact.id}');">Delete</a>
         </td>
      </tr>
      </tbody>
   </table>
</center>
</body>
</html>