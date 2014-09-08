<%@include file="taglib_includes.jsp" %>

<html>
<head>
   <script src="js/contacts.js" type="text/javascript"></script>
   <title>
      <spring:message code="App.title"></spring:message>
   </title>
</head>

<body style="font-family: Arial; font-size:smaller;">

<table align="center" bgcolor="lightblue" border="1" bordercolor="#006699" height="500"
       style="border-collapse: collapse; width: 750px;">
   <tbody>
   <tr>
      <td align="center"><h3>Edit Contact Form</h3></td>
   </tr>
   <tr align="center" valign="top">
      <td align="center">

         <form:form action="saveContact.do" commandName="newContact" method="post">
             <table border="0" bordercolor="#006699" cellpadding="2" cellspacing="2"
                    style="border-collapse: collapse; width: 500px;">
                <tbody>
                <tr>
                   <td align="right" width="100">Name</td>
                   <td width="150">
                      <form:input path="name"></form:input></td>
                   <td align="left">
                   <form:errors cssstyle="color:red" path="name"></form:errors></td>
                </tr>
                <tr>
                   <td align="right" width="100">DOB</td>
                   <td>
                      <form:input path="dob"></form:input>
                   </td>
                   <td align="left">
                      <form:errors cssstyle="color:red" path="dob"></form:errors>
                   </td>
                </tr>
                <tr>
                   <td align="right" width="100">Gender</td>
                   <td>
                      <form:select path="gender">
                         <form:option label="Male" value="M"/>
                         <form:option label="Female" value="F"/>
                      </form:select>
                   </td>
                   <td></td>
                </tr>
                <tr>
                   <td align="right" width="100">Address</td>
                   <td>
                      <form:input path="address"></form:input>
                   </td>
                   <td align="left">
                      <form:errors cssstyle="color:red" path="address"></form:errors>
                   </td>
                </tr>
                <tr>
                   <td align="right" width="100">Email</td>
                   <td>
                      <form:input path="email"></form:input>
                   </td>
                   <td align="left">
                      <form:errors cssstyle="color:red" path="email"></form:errors>
                   </td>
                </tr>
                <tr>
                   <td align="right" width="100">Mobile</td>
                   <td>
                      <form:input path="mobile"></form:input>
                   </td>
                   <td align="left">
                      <form:errors cssstyle="color:red" path="mobile"></form:errors></td>
                   </tr>
                <tr>
                   <td align="center" colspan="3">
                      <input name="" type="submit" value="Save">
                      <input name="" type="reset" value="Reset">
                      <input onclick="javascript:go('viewAllContacts.do');" type="button" value="Back">
                   </td>
                </tr>
                </tbody>
             </table>
         </form:form>
      </td>
   </tr>
   </tbody>
</table>
</body>
</html>