<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="game.js"></script>
<title>Insert title here</title>
</head>

<body>

	<p>${ sessionScope.user.username }</p>

	<div id="gameBoard">
	
		<input type="button" class="button" id="roll" name="roll" value="Roll">
		
		<div id="rollingDices">
		</div>
	
	</div>
	
	<div id="scoreGrid">
		
		<div >
		</div>
		
		<input type="button" class="button" id="fill" name="fill" value="Fill">
		
	</div>

</body>
</html>