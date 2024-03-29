<%@ page import="java.util.List, java.io.File" %>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title> Home </title>
    <%@ include file="head.jsp"%>
    <%@ page pageEncoding="utf-8" %>
  </head>
  <body>
    <% Object pseudo = session.getAttribute("pseudo"); %>
    <%@ include file="header.jsp" %>
    <div class="row" id="menu1">
      <div class="col-md-10">
        <h1> Modification du Profil </h1>
        <form method="POST" action="../servlet/UploadImage" enctype="multipart/form-data"  >
          <div class="form-group">
            <%
              if (new File(request.getServletContext().getRealPath("/") +
                "img/" + pseudo + "Avatar.png").exists()){ %>
                <img class="avatar" src="../img/${sessionScope.pseudo}Avatar.png">
            <% } else{ %>
                <img class="avatar" src="../img/defaultAvatar.png">
            <% } %>
          </div>
          <div class="form-group">
            <input type="file" name="file" id="file" />
          </div>
          <input type="submit" value="Upload" name="upload" class="btn btn-primary btn-lg">
        </form>
      </div>
      <%@ include file="listeContacts.jsp" %>
    </div> <!-- Fin de row -->
    <%@ include file="../footer.html"%>
  </body>
</html>
