<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>virement (jsp)</title>
<link href="../../css/styles.css" rel="stylesheet" />
</head>
<body>
    <h3>Nouveau virement</h3>
	<form:form action="doVirement" modelAttribute="virement" method="POST">
		<label class="simpleAlign">montant:</label> <form:input path="montant" />
		<form:errors path="montant" cssClass="error" />
		<br />
		<label class="simpleAlign">numCptDeb:</label> 
		<!-- <form:input path="numCptDeb" /> -->
		<form:select path="numCptDeb" >
			<form:options items="${listeComptes}" itemLabel="numero" itemValue="numero"/>
		</form:select>
		<br />
		<label class="simpleAlign">numCptCred:</label>
		<!--   <form:input path="numCptCred" /> -->
		<form:select path="numCptCred" >
			<form:options items="${listeComptes}" itemLabel="numero"  itemValue="numero"/>
		</form:select>
		<br />
		<label class="simpleAlign"></label>
		<input type="submit" value="effectuer virement" />
		<br />
	</form:form>
	<hr />
	<span class="highlight">${message}</span><br />
	<hr />
	<a href="../../index-site.html"> retour menu</a>
	<br />
</body>
</html>