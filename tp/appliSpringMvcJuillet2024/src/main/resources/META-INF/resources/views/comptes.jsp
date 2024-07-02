<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>comptes (jsp)</title>
 <link href="../../css/styles.css" rel="stylesheet" />
</head>
<body>
   <!-- 
	<span class="highlight">${message}</span><br />
    <hr/>
     -->
    <h3>Comptes du client numero ${numClient}</h3>
    <table border="1" >
		<tr><th>numero</th><th>label</th><th>solde</th></tr>
		<c:forEach var="c" items="${listeComptes}">
			<tr><td>${c.numero}</td><td>${c.label}</td><td>${c.solde}</td></tr>
		</c:forEach>
	</table>
	<hr/>
	<a href="toAddCompte"> ajouter nouveau compte (apres login)</a> <br/>
	<a href="../bank/toVirement"> effectuer un nouveau virement</a> <br/>
	<a href="../../index-site.html"> retour menu</a> <br/>
</body>
</html>