document.getElementById("searchField").onsearch = function() {search()};
document.getElementById("searchbtn").onclick = function() {search()};
function search() {
	var x = document.getElementById("searchField");
	if(x.value != "")
	{
		document.getElementById("searchkey").innerHTML = "You are searching for: " + x.value;
	}
	//window.alert(5 + 6);
}