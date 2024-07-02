<html>
<head>
<title>clientLogin (jsp)</title>
 <link href="../../css/styles.css" rel="stylesheet" />
</head>
<body>
	<form action="clientLogin" method="GET">
		<label class="simpleAlign">numClient:</label>
		<input type="text" name="numClient" value="${numClient}" />
		<br /> 
		<label class="simpleAlign">temp password:</label> 
		<input type="password" name="tempPassword" value="${tempPassword}" /> (ex: pwd)<br />
		<label class="simpleAlign"></label>
		<input	type="submit" value="login (client banque)" /> <br />
	</form>
	<hr/>
	<span class="highlight">${message}</span><br />
    <hr/>
    <h3>Informations "Client"</h3>
     <label class="simpleAlign">numero:</label> <span class="highlight">${client.numero}</span><br />
    <label class="simpleAlign">prenom:</label> <span class="highlight">${client.prenom}</span><br />
    <label class="simpleAlign">nom:</label> <span class="highlight">${client.nom}</span><br />
    <label class="simpleAlign">email:</label> <span class="highlight">${client.email}</span><br />
    <label class="simpleAlign">adresse:</label> <span class="highlight">${client.adresse}</span><br />
	<hr/>
	<a href="comptesDuClient"> comptesDuClient (apres login)</a> <br/>
	<a href="toAddCompte"> ajouter nouveau compte (apres login)</a> <br/>
	<hr/>
	<a href="../../index-site.html"> retour menu</a> <br/>
</body>
</html>