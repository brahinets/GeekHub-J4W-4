var test = new task10();

function task10(){
    var droper = this;

    this.init = function(){
        document.getElementById("avatarBlock").addEventListener("drop", droper.onDropListener, false);
    };

    this.onDropListener = function(e){
        e.stopPropagation();
        e.preventDefault();
        var files = e.dataTransfer.files;
        var reader = new FileReader();

        reader.onload = function(){
            droper.setImageToUpload(reader.result);
        };
        reader.readAsDataURL(files[0]);
    };

    this.setImageToUpload = function(imageToUpload){
        var base64RX = /data:image\/(png|bmp|jpeg|jpg|gif);base64,/g;

        if(imageToUpload != null && base64RX.test(imageToUpload)){
            document.getElementById("imagePreview").src = imageToUpload;
            droper.upload(imageToUpload.replace(base64RX, ""), "base64");
        }
    };

    this.upload = function(imageToUpload){
        console.log(imageToUpload)
        $("#avatar").val(imageToUpload);
    }
}