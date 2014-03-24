function deleteFilm(id) {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        dataType: "json",
        url: "/film/delete/"+id,

        success: function(id) {
            var $row = $("#film" + id);
            $row.css("display", "none");
        },
        error: function(){
            alert("error");
        }
    });
}

function getListOfFilms() {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        dataType: "json",
        url: "/film/list",

        success: function(films) {
            var filmsList = $("#filmsList");
            var row;

            filmsList.append('<table style="table, th, td { border: solid black 1px; }">');
            filmsList.append('<tr>');
            filmsList.append("<td>id</td>");
            filmsList.append("<td>Film Name</td>");
            filmsList.append("<td>Year</td>");
            filmsList.append("<td># of actors</td>");
            filmsList.append("<td colspan='2'>Actions</td>");
            filmsList.append("</tr>");

            for (var i = 0; i < films.length; i++) {
                row = "<tr id=\"film" + films[i].id + "\"  display=block>";
                row += "<td>" + films[i].id + "</td>";
                row += "<td>" + films[i].name + "</td>";
                row += "<td>" + films[i].year + "</td>";
                row += "<td>" + films[i].actorsCount + "</td>";
                row += "<td>" + "<button onclick=window.open(\"/film/edit/" + films[i].id + "\")>" + "Edit</button>" + "</td>";
                row += "<td>" + "<button onclick=deleteFilm("+ films[i].id +")>" + "Delete</button>" + "</td>";
                row += "</tr>";
                filmsList.append(row);
            }

            filmsList.append('</table>');
        },
        error: function(data){
            alert("error : ".concat(data));
        }
    });
}