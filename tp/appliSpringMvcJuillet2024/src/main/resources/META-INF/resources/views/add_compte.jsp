<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>add_compte (jsp)</title>
<link href="../../css/styles.css" rel="stylesheet" />
</head>
<body>
    <h3>Ajout de compte pour le client ${numClient}</h3>
	<form:form action="doAddCompte" modelAttribute="compte" method="POST">
		<label class="simpleAlign">label:</label> <form:input path="label" />
		<form:errors path="label" cssClass="error" />
		<br />
		<label class="simpleAlign">solde:</label> <form:input path="solde" />
		<form:errors path="solde" cssClass="error" />
		<br />
		<label class="simpleAlign"></label>
		<input type="submit" value="ajouter compte" />
		<br />
	</form:form>
	<hr />
	<span class="highlight">${message}</span><br />
	<hr />
	<a href="../../index-site.html"> retour menu</a>
	<br />
</body>
</html>