/**
 * 
 */

let url = 'http://localhost:8080/yamsnew/game';

function getGameState(){
	
	fetch(url)
	.then(function(response) {
		if (response.ok){
			return response.json();
		}
	})
	.then(function(gamestate){
		
		let dices = gamestate.currentHand.dices;
		let currentDiceRoll = gamestate.currentDiceRoll;
		initHand(dices, currentDiceRoll);
		
	});
	
}

function initHand(dices, currentDiceRoll){
	
	let rollingDices = document.getElementById('rollingDices');
	
	for (let i in dices){
		
		let label = document.createElement("label");
		label.classList.add("diceContainer");
		
		let checkbox = document.createElement("input");
		checkbox.setAttribute("type", "checkbox"); 
		checkbox.setAttribute("name", "dices"); 
		checkbox.setAttribute("value", i+1);
		
		toggleDisabledDice(currentDiceRoll, checkbox);
		
		let span = document.createElement("span");
		span.classList.add("checkmark", dices[i].diceValue);
		
		rollingDices.appendChild(label);
		label.appendChild(checkbox);
		label.appendChild(span);
	}
	
}


function rollGame(){
	
	let dices = document.getElementsByName("dices");
	
	let dicesChecked = [];
	
	for (let i in dices){
		if (dices[i].checked){
			dicesChecked.push((parseInt(i)+1).toString()); //add 1 to be understood by the gameController
			dices[i].checked = false;
		}
	}
	
	let data = {
		roll: "",
		dices: dicesChecked
	};
	
	fetch(url, {
		method: "POST",
		headers: { 
	'Accept': 'application/json', 
	'Content-Type': 'application/json' 
	},
		body: JSON.stringify(data)
	})
	.then(function(response) {
		if (response.ok){
			return response.json();
		}
		else{
			printBlabla();
		}
	})
	.then(function(gamestate){
		
		let dices = gamestate.currentHand.dices;
		let currentDiceRoll = gamestate.currentDiceRoll;
		updateHand(dices, currentDiceRoll);
		
	})
	;
	
}

function updateHand(dices, currentDiceRoll){
	
	let checkmark = document.getElementsByClassName("checkmark");
	let checkbox  = document.getElementsByName("dices");
	
	for (let i in dices){
		
		toggleDisabledDice(currentDiceRoll, checkbox[i]);
		
		checkmark[i].classList.remove("ACE","TWO","THREE", "FOUR", "FIVE", "SIX")
		checkmark[i].classList.add(dices[i].diceValue);
		
	}
	
}

function toggleDisabledDice(currentDiceRoll, checkbox){
	
		if (currentDiceRoll == 0 || currentDiceRoll == 3){
			checkbox.setAttribute("disabled", "");
		}
		else{
			checkbox.removeAttribute("disabled");
		}
}


function printBlabla(){
	
	let grid = document.getElementById("grid");
	let p = document.createElement("p");
	p.innerText = "blabla";
	grid.appendChild(p);
	
}







