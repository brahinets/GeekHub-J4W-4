$(function calculator() {
    $('#actions').find('[type="button"]').click(function () {
        var value = $(this).attr('value');

        $.get('./calculator.form', {button: value}).done(function (result) {
            $('#result').val(result);
            $('#operand').val(0);
        });
    });


    $('#digits').find('[type="button"]').click(function () {
        var value = $(this).attr('value');

        $.get('./calculator.form', {button: value}).done(function (operand) {
            $('#operand').val(operand);
        });
    });
});
