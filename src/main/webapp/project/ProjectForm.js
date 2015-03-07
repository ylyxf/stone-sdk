$(document).ready(function() {

	
	$("#dbType").on("change", function() {
		switch (this.value) {
		case "":
			$("#dbDriver").val("");
			$("#dbUrl").val("");
			break;
		case "Postgresql":
			$("#dbDriver").val("org.postgresql.Driver");
			$("#dbUrl").val("jdbc:postgresql://127.0.0.1:5432/stone-sdk");
			break;
		}
	});

});