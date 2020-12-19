$(document).ready(function() {
	//mobile onkeypress validation

	$("#MOBILE").keypress(function(e) {
		var keyCode = e.keyCode || e.which;

		$("#lblError").html("");

		//Regex for Valid Characters i.e. Numbers.
		var regex = /^[0-9]+$/;

		//Validate TextBox value against the Regex.
		var isValid = regex.test(String.fromCharCode(keyCode));
		if (!isValid) {
			$("#lblErrorr").html("Only Numbers allowed.");
		}

		return isValid;
	});

	//NAME onkeypress validation

	$("#EMP_NAME").keypress(function(e) {
		var keyCode = e.keyCode || e.which;

		$("#lblError").html("");

		//Regex for Valid Characters i.e. Alphabets.
		var regex = /^[A-Za-z]+$/;

		//Validate TextBox value against the Regex.
		var isValid = regex.test(String.fromCharCode(keyCode));
		if (!isValid) {
			$("#lblError").html("Only Alphabets allowed.");
		}

		return isValid;
	});

$("#REG_DATE").keypress(function(e) {
		var keyCode = e.keyCode || e.which;

		$("#lblError").html("");

		//Regex for Valid Characters i.e. Alphabets.
		var regex =  /^[0-9]+$/;

		//Validate TextBox value against the Regex.
		var isValid = regex.test(String.fromCharCode(keyCode));
		if (!isValid) {
			$("#Date").html("Only Alphabets allowed.");
		}

		return isValid;
	});



	$(function() {
		// get the item 
		if (localStorage.getItem('edit') !== null) {
			$('#btn_Register').prop('hidden', true);
			$('#btn_delete').prop('hidden', true);
			localStorage.clear();
		}
	});


	$(function() {
		// get the item 
		if (localStorage.getItem('add') !== null) {
			$('#btn_update').prop('hidden', true);
			$('#btn_delete').prop('hidden', true);
			localStorage.clear();
		}
	});

	$(function() {
		// get the item 
		if (localStorage.getItem('delete') !== null) {
			$("#table_register").find("input,select").attr("disabled", "disabled");
			$('#btn_update,#btn_Register').prop('hidden', true);
			localStorage.clear();
		}
	});
});

function onEdit() {
	localStorage.setItem('edit', true);
}


function onAdd() {
	localStorage.setItem('add', true);
}

function onDelete() {
	localStorage.setItem('delete',true);
}

