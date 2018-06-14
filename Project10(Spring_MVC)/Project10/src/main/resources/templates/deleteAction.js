function DeleteAction(pictureId) {
    var xhttp = new XMLHttpRequest();
    xhttp.open("DELETE", "localhost:8080/gallery/${pictureId}", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send();
    var response = JSON.parse(xhttp.responseText);
}