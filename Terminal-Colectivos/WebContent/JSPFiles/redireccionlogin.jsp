<% String txtusuario = (String) session.getAttribute("usuario"); %> 

<% String txtestado = (String) session.getAttribute("estado"); %>

<% if(txtusuario==null) request.getRequestDispatcher("login.jsp").forward(request, response); %>
<% if(txtestado==null || !txtestado.equals("activo")) request.getRequestDispatcher("login.jsp").forward(request, response); %>