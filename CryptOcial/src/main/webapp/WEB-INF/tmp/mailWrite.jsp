<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>


<title></title>
<script src="js/jquery-2.1.0.min.js"></script>
<script>

//    $(function() {
//        $("#sendMessage").click(function(){
//
//            var $recipient = $('input[name=recipient]').val();
//            var $theme = $('input[name=theme]').val();
//            var $content = $('textarea[name=content]').val();
//
//            console.log($recipient);
//            console.log($theme);
//            console.log($content);
//
//            if($content != null && $content != "" && $recipient != null && $recipient != ""){
//                var $sendMessage = $("#sendMessage");
//                $sendMessage.css("display", "none");
//
//                var $sendingMessage = $("#sendingMessage");
//                $sendingMessage.css("display","block");
//                $sendingMessage.css("background-image", "url(load.gif)");
//
//                var object = {};
//
//                object.recipient = $recipient;
//                object.theme = $theme;
//                object.content = $content;
//
//                $.ajax({
//                    url: "/message/writeForm",
//                    data: {
//                        json: JSON.stringify(object)
//                    },
//
//                    success: function(){
//                        var $resultBlock = $("#resultForm");
//                        var $questionsBlock = $("#messageForm");
//
//                        $resultBlock.css({display:"block"});
//                        $questionsBlock.css({display:"none"});
//                    },
//
//                    error: function(){
//                        console.log("error");
//                    }
//                });
//
//            } else {
//                console.log("some is empty");
//            }
//
//
//
//// width: 50px;   padding: 0 15px; background-size:cover; background-position: center;  height: 40px;
//
//
//
//        });
//    });
</script>
<style>

    .tsc_form_contact_light {
        width: 40%;
        text-align: left;
    }

    .tsc_form_contact_light .form-input {
        display: block;
        width: 100%;
        height: 5%;
        padding: 6px 10px;
        margin-bottom: 20px;

        font: 120% Calibri, Helvetica, Arial, sans-serif;
        color: #B03E40;
        background: #fff; border: 1px solid #ccc;
        outline: none;
        -moz-border-radius:    8px;
        -webkit-border-radius: 8px;
        border-radius:         8px;
        -moz-box-shadow:    inset 0 0 1px rgba(0, 0, 0, 0.3), 0 1px 0 rgba(255, 255, 255, 0.5);
        -webkit-box-shadow: inset 0 0 1px rgba(0, 0, 0, 0.3), 0 1px 0 rgba(255, 255, 255, 0.5);
        box-shadow:         inset 0 0 1px rgba(0, 0, 0, 0.3), 0 1px 0 rgba(255, 255, 255, 0.5);
        -webkit-background-clip: padding-box;
        background-clip:         padding-box;
        -moz-transition:    all 0.4s ease-in-out;
        -webkit-transition: all 0.4s ease-in-out;
        -o-transition:      all 0.4s ease-in-out;
        -ms-transition:     all 0.4s ease-in-out;
        transition:         all 0.4s ease-in-out;
    }


    .tsc_form_contact_light textarea.form-input {
        max-width: 100%;
        min-width: 100%;
        width: 100%;
        height: 30%;
        overflow: auto;
    }


    .tsc_form_contact_light .form-input:focus {
        border: 1px solid #7fbbf9;
        -moz-box-shadow:    inset 0 0 1px rgba(0, 0, 0, 0.3), 0 0 3px #7fbbf9;
        -webkit-box-shadow: inset 0 0 1px rgba(0, 0, 0, 0.3), 0 0 3px #7fbbf9;
        box-shadow:         inset 0 0 1px rgba(0, 0, 0, 0.3), 0 0 3px #7fbbf9;
    }

    .tsc_form_contact_light .form-input:-moz-ui-invalid {
        border: 1px solid #e00;
        -moz-box-shadow:    inset 0 0 1px rgba(0, 0, 0, 0.3), 0 0 3px #e00;
        -webkit-box-shadow: inset 0 0 1px rgba(0, 0, 0, 0.3), 0 0 3px #e00;
        box-shadow:         inset 0 0 1px rgba(0, 0, 0, 0.3), 0 0 3px #e00;
    }

    .tsc_form_contact_light .form-input.invalid {
        border: 1px solid #e00;
        -moz-box-shadow:    inset 0 0 1px rgba(0, 0, 0, 0.3), 0 0 3px #e00;
        -webkit-box-shadow: inset 0 0 1px rgba(0, 0, 0, 0.3), 0 0 3px #e00;
        box-shadow:         inset 0 0 1px rgba(0, 0, 0, 0.3), 0 0 3px #e00;
    }

    .tsc_form_contact_light .form-btn {
        float: right;
        width: 220px;
        padding: 0 15px;
        height: 30px;
        margin-top: -15px;
        font: bold 20px Calibri, Helvetica, Arial, sans-serif;
        text-align: center;
        color: #fff;
        text-shadow: 0 1px 0 rgba(0, 0, 0, 0.5);
        cursor: pointer;
        border: 1px solid #1972c4;
        background-color: #1d83e2;
        background-image: -webkit-gradient(linear, left top, left bottom, from(#77b5ee), to(#1972c4));
        /* Saf4+, Chrome */ background-image: -webkit-linear-gradient(top, #77b5ee, #1972c4);
        /* Chrome 10+, Saf5.1+, iOS 5+ */ background-image:    -moz-linear-gradient(top, #77b5ee, #1972c4);
        /* FF3.6 */ background-image:     -ms-linear-gradient(top, #77b5ee, #1972c4);
        /* IE10 */ background-image:      -o-linear-gradient(top, #77b5ee, #1972c4);
        /* Opera 11.10+ */ background-image:         linear-gradient(top, #77b5ee, #1972c4);
        -pie-background:          linear-gradient(top, #77b5ee, #1972c4);
        /* IE6-IE9 */ -moz-border-radius:    16px;
        -webkit-border-radius: 16px;
        border-radius:         16px;
        -moz-box-shadow:    inset 0 1px 0 rgba(255, 255, 255, 0.3), 0 1px 2px rgba(0, 0, 0, 0.5);
        -webkit-box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.3), 0 1px 2px rgba(0, 0, 0, 0.5);
        box-shadow:         inset 0 1px 0 rgba(255, 255, 255, 0.3), 0 1px 2px rgba(0, 0, 0, 0.5);
        -webkit-background-clip: padding-box;
        background-clip:         padding-box;
    }

    .tsc_form_contact_light .form-btn:active {
        border: 1px solid #77b5ee;
        background-color: #1972c4;
        background-image: -webkit-gradient(linear, left top, left bottom, from(#1972c4), to(#77b5ee));
        /* Saf4+, Chrome */ background-image: -webkit-linear-gradient(top, #1972c4, #77b5ee);
        /* Chrome 10+, Saf5.1+, iOS 5+ */ background-image:    -moz-linear-gradient(top, #1972c4, #77b5ee);
        /* FF3.6 */ background-image:     -ms-linear-gradient(top, #1972c4, #77b5ee);
        /* IE10 */ background-image:      -o-linear-gradient(top, #1972c4, #77b5ee);
        /* Opera 11.10+ */ background-image:         linear-gradient(top, #1972c4, #77b5ee);
        -pie-background:          linear-gradient(top, #1972c4, #77b5ee);
        /* IE6-IE9 */ -moz-box-shadow:    inset 0 0 5px rgba(0, 0, 0, 0.5), 0 1px 0 rgba(255, 255, 255, 0.5);
        -webkit-box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.5), 0 1px 0 rgba(255, 255, 255, 0.5);
        box-shadow:         inset 0 0 5px rgba(0, 0, 0, 0.5), 0 1px 0 rgba(255, 255, 255, 0.5);
    }

    .tsc_form_contact_light input[type=submit]::-moz-focus-inner {
        border: 0;
        padding: 0;
    }

    .tsc_form_contact_light.frame {
        padding: 30 30 30 30px;
        background-color: #ccc;
        background-image: -webkit-gradient(linear, left top, left bottom, from(#ededed), to(#b4b4b4));
        /* Saf4+, Chrome */ background-image: -webkit-linear-gradient(top, #f6f6f6, #d2d1d0);
        /* Chrome 10+, Saf5.1+, iOS 5+ */ background-image:    -moz-linear-gradient(top, #f6f6f6, #d2d1d0);
        /* FF3.6 */ background-image:     -ms-linear-gradient(top, #f6f6f6, #d2d1d0);
        /* IE10 */ background-image:      -o-linear-gradient(top, #f6f6f6, #d2d1d0);
        /* Opera 11.10+ */ background-image:         linear-gradient(top, #f6f6f6, #d2d1d0);
        -pie-background:          linear-gradient(top, #f6f6f6, #d2d1d0);
        /* IE6-IE9 */ -moz-border-radius:    8px;
        -webkit-border-radius: 8px;
        border-radius:         8px;
        -moz-box-shadow:    0 1px 2px rgba(0, 0, 0, 0.5), inset 0 0 1px rgba(255, 255, 255, 0.5);
        -webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, 0.5), inset 0 0 1px rgba(255, 255, 255, 0.5);
        box-shadow:         0 1px 2px rgba(0, 0, 0, 0.5), inset 0 0 1px rgba(255, 255, 255, 0.5);
    }

    .tsc_form_contact_light.tbar {
        padding: 0 20px 20px 20px;
        background-color: #eee;
        background-image: -webkit-gradient(linear, left top, left bottom, from(#f6f6f6), to(#d6d6d6));
        /* Saf4+, Chrome */ background-image: -webkit-linear-gradient(top, #f6f6f6, #d6d6d6);
        /* Chrome 10+, Saf5.1+, iOS 5+ */ background-image:    -moz-linear-gradient(top, #f6f6f6, #d6d6d6);
        /* FF3.6 */ background-image:     -ms-linear-gradient(top, #f6f6f6, #d6d6d6);
        /* IE10 */ background-image:      -o-linear-gradient(top, #f6f6f6, #d6d6d6);
        /* Opera 11.10+ */ background-image:         linear-gradient(top, #f6f6f6, #d6d6d6);
        -pie-background:          linear-gradient(top, #f6f6f6, #d6d6d6);
    }

    .tsc_form_contact_light.tbar h3 {
        font: normal 25px Calibri, Helvetica, Arial, sans-serif;
        color: #2060A0;
        text-shadow: 0 1px 1px rgba(255, 255, 255, 0.7);
        padding: 10 20px;
        margin: 0 -20px 20px -20px;
        background-color: #c9c9c9;
        background-image: -webkit-gradient(linear, left top, left bottom, from(#f6f6f6), to(#c9c9c9));
        /* Saf4+, Chrome */ background-image: -webkit-linear-gradient(top, #f6f6f6, #c9c9c9);
        /* Chrome 10+, Saf5.1+, iOS 5+ */ background-image:    -moz-linear-gradient(top, #f6f6f6, #c9c9c9);
        /* FF3.6 */ background-image:     -ms-linear-gradient(top, #f6f6f6, #c9c9c9);
        /* IE10 */ background-image:      -o-linear-gradient(top, #f6f6f6, #c9c9c9);
        /* Opera 11.10+ */ background-image:         linear-gradient(top, #f6f6f6, #c9c9c9);
        -pie-background:          linear-gradient(top, #f6f6f6, #c9c9c9);
        /* IE6-IE9 */ -moz-border-radius:    8px 8px 0 0;
        -webkit-border-radius: 8px 8px 0 0;
        border-radius:         8px 8px 0 0;
        -moz-border-radius:    8px 8px 0 0;
        -moz-box-shadow:    inset 0 1px 0 rgba(255, 255, 255, 0.5), 0 1px 1px rgba(0, 0, 0, 0.5);
        -webkit-box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.5), 0 1px 1px rgba(0, 0, 0, 0.5);
        box-shadow:         inset 0 1px 0 rgba(255, 255, 255, 0.5), 0 1px 1px rgba(0, 0, 0, 0.5);
    }


</style>
<script type="text/javascript">
    $(function(){
        $('input[placeholder], textarea[placeholder]').each(function(){
            var $placeInput = $(this);

            if( 'placeholder' in document.createElement('input') ) {
                var placeholder = true;
            }
            else {
                var placeholder = false;
                $placeInput.val( $placeInput.attr('placeholder') );
            }

            if( !placeholder ) {
                $placeInput.focusin(function(){
                    if( $placeInput.val() === $placeInput.attr('placeholder') ) {
                        $placeInput.val('');
                    }
                })
                        .focusout(function(){
                            if( $placeInput.val() === '' ) {
                                $placeInput.val( $placeInput.attr('placeholder') );
                            }
                        });
            }
        });
    });
</script>
</head>
<body>

<form action="/mail/write" method="POST" id="messageForm" class="tsc_form_contact_light frame tbar">
    <h3>New Message</h3>
    <input type="text" value="${userID}" id="recipient" name="recipient" class="form-input" placeholder="Recipient (required)" required />
    <input type="text" id="theme" name="theme" class="form-input" placeholder="Theme (optional)" />
    <textarea id="content" name="content" class="form-input"  placeholder="Message ('required')" required></textarea>
    <input class="form-btn" id="sendMessage" type="submit" value="Send Message"/>
    <div  style="display : none; float:right;  margin-top: -10px;" id="sendingMessage"><img src="load.gif"></div>
</form>

<div id="resultForm" style="display : none; background-image: url(load.gif)">Message sended</div>

</body>
</html>