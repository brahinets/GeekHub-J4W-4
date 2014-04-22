/* prepare one row in message list,
 * create blocks for avatar, info and actions */
function prepareOneRowInMailList (message, type){
    var row;
    row =  '';

    if(message.isRead) {
        row += '<div class="row listRow" id="message'+ message.id +'">';
    } else {
        row += '<div class="row listRow notReadMessage" id="message'+ message.id +'">';
    }
    row += '    <div class="mini col-md-2 listItem listPhoto">';
    if(message.user != null && message.user.avatar != null) {
        row += '        <a href="/user/' + message.sender +'"><img src="data:image/png;base64,' + message.user.avatar + '"/></a>';
    } else {
        row += '        <a href="/user/' + message.sender +'"><img src="/res/img/noAvatar.png"/></a>';
    }
    row += '    </div>';
    row += '    <div class="col-md-2 mailShort listItem">';
    row += '        <div>From : ' + message.sender + '</div>';
    row += '        <div>To : ' + message.recipient + '</div>';
    row += '        <div>Date : ' + dateFormat(new Date(message.mDate), "hh:MM:ss TT, dS mmmm yyyy") /*date.toLocaleString()*/ + '</div>';
    row += '        <div>Theme : ' + message.theme + '</div>';
    row += '    </div>';
    row += '    <div class="col-md-2 listItem listAction">';
    row += '        <ul class="list-group">';
    row += '            <li class="list-group-item list-group-item-success read"><a href="/mail/read/' + message.id + '">Read</a></li>';
    row += '            <li class="list-group-item list-group-item-warning markitspam"><span class="action" onclick="messageSpam(' + message.id + ', true)">Mark it spam</span></li>';
    row += '            <li class="list-group-item list-group-item-danger delete"><span class="action" onclick="messageDelete(' + message.id + ','+ type +')" >Delete</span></li>';
    row += '        </ul>';
    row += '    </div>';
    row += '</div>';

    return row;
}