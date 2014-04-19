/* prepare one row in message list,
 * create blocks for avatar, info and actions */
function prepareOneRowInMailList (message){
    var row;

    row =  '';
    row += '<div class="row listRow">';
    row += '    <div class="mini col-md-2 listItem listPhoto">';
    row += '        <a href="/user/' + message.sender +'"><img src="' + message.sender + '"/></a>';
    row += '    </div>';
    row += '    <div class="col-md-2 mailShort listItem">';
    row += '        <div>From : ' + message.sender + '</div>';
    row += '        <div>Date : ' + dateFormat(new Date(message.mDate), "hh:MM:ss TT, dS mmmm yyyy") /*date.toLocaleString()*/ + '</div>';
    row += '        <div>Theme : ' + message.theme + '</div>';
    row += '    </div>';
    row += '    <div class="col-md-2 listItem listAction">';
    row += '        <ul class="list-group">';
    row += '            <li class="list-group-item list-group-item-success read"><a href="/mail/read/' + message.id + '">Read</a></li>';
    row += '            <li class="list-group-item list-group-item-warning markitspam"><a href="/mail/markspam/' + message.id + '">Mark it spam</a></li>';
    row += '            <li class="list-group-item list-group-item-danger delete"><a href="/mail/delete/' + message.id + '">Delete</a></li>';
    row += '        </ul>';
    row += '    </div>';
    row += '</div>';
    row += '<hr>';

    return row;
}


/* get mail list from controller */
/*
function getMail(source){
    $.ajax({
        type: "GET",
        contentType: "application/json",
        dataType: "json",
        url: source,

        success: function(mailList) {
            var list = "";
            alert("len : "+ mailList.length);
            for (var i = 0; i < mailList.length; i++) {
                list += prepareOneRowInMailList(mailList[i]);
            }

            return list;
        },
        error: function(data){
            alert("error : ".concat(data));
        }

    });
}*/
