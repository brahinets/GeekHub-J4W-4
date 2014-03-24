function deleteActor(id) {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        dataType: "json",
        url: "/actor/delete/"+id,

        success: function(id) {
            var $row = $("#actor" + id);
            $row.css("display", "none");
        },
        error: function(){
            alert("error");
        }
    });
}

function getListOfActors() {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        dataType: "json",
        url: "/actor/list",

        success: function(actors) {
            var actorsList = $("#actorsList");
            var row;

            actorsList.append('<table style="table, th, td { border: solid black 1px; }">');
            actorsList.append('<tr>');
            actorsList.append("<td>id</td>");
            actorsList.append("<td>First Name</td>");
            actorsList.append("<td>Second Name</td>");
            actorsList.append("<td>Birth date</td>");
            actorsList.append("<td>Films count</td>");
            actorsList.append("<td colspan='2'>Actions</td>");
            actorsList.append("</tr>");

            for (var i = 0; i < actors.length; i++) {
                row = "<tr id=\"actor" + actors[i].id + "\"  display=block>";
                row += "<td>" + actors[i].id + "</td>";
                row += "<td>" + actors[i].firstName + "</td>";
                row += "<td>" + actors[i].secondName + "</td>";
                row += "<td>" + actors[i].birthDate + "</td>";
                row += "<td>" + actors[i].filmsCount + "</td>";
                row += "<td>" + "<button onclick=window.open(\"/actor/edit/" + actors[i].id + "\")>" + "Edit</button>" + "</td>";
                row += "<td>" + "<button onclick=deleteActor("+ actors[i].id +")>" + "Delete</button>" + "</td>";
                row += "</tr>";
                actorsList.append(row);
            }

            actorsList.append('</table>');
        },
        error: function(data){
            alert("error : ".concat(data));
        }
    });
}