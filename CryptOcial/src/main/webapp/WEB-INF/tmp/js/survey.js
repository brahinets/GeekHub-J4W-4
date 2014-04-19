var survey = new Survey();

function Survey(){
    this.init = function(){
        $("#submitSurvey").click(function(){
            var object = {};

            object.name = $('input[name=name]').val();
            object.age = $('input[name=age]').val();
            object.country = $('input[name=country]').val();
            object.fromKnow = $('input[name=fromKnow]:checked').val();
            object.isLiked = $('input[name=isLiked]').is(":checked");
            object.wishes = $('textarea[name=wishes]').val();
            object.money = document.getElementById("money").value;

            $.ajax({
                url: "survey.form",
                data: {
                    json: JSON.stringify(object)
                },

                success: function(){
                    var $resultBlock = $("#resultBlock");
                    var $questionsBlock = $("#questionsBlock");

                    $resultBlock.css({display:"block"});
                    $questionsBlock.css({display:"none"});
                },

                error: function(){
                    console.log("error");
                }
            });
        });
    }
}