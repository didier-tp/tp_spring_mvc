<html>
<head>
<title>calcul_tva (jsp)</title>
 <link href="../../css/styles.css" rel="stylesheet" />
</head>
<body>
	<form action="calculTva" method="GET">
		<label class="simpleAlign">taux de tva (en %):</label>
		<input type="text" name="taux" value="${taux}" />
		<!--
		 <select name="taux">
				<option>5</option>
				<option>10</option>
				<option>20</option>
		</select> 
		-->
		<br /> 
		<label class="simpleAlign">ht:</label> <input type="text" name="ht" value="${ht}" /> <br />
		<label class="simpleAlign"></label><input	type="submit" value="calculer tva et ttc" /> <br />
	</form>
	<hr/>
	<label class="simpleAlign">tva=</label><span class="highlight">${tva}</span><br />
	<label class="simpleAlign">ttc=</label><span class="highlight">${ttc}</span><br />
	<hr/>
	<a href="../../index-site.html"> retour menu</a> <br/>
</body>
</html>