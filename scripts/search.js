document.getElementById("searchField").onsearch = function() {search()};
document.getElementById("searchbtn").onclick = function() {search()};
function search() {
	var x = document.getElementById("searchField");
	document.getElementById("searchkey").innerHTML = "You are searching for: " + x.value;
}