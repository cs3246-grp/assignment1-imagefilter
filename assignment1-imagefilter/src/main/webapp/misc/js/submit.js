function Validate()
{
	var image =document.getElementById("image").value;
	if(image!=''){
		var checkimg = image.toLowerCase();
		if (!checkimg.match(/(\.jpg|\.png|\.JPG|\.PNG|\.jpeg|\.JPEG)$/)){
			alert("Please enter  Image  File Extensions .jpg,.png,.jpeg");
			document.getElementById("image").focus();
			return false;
		}
	}
	return true;
}		

function readURL(input) {

    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            $('#preview')
                .attr('src', e.target.result)
                .width(400);
            };

        reader.readAsDataURL(input.files[0]);
    }
}

$("#attach").click(function () {
	alert('clicked!');
    $("#upload").trigger('click');
});

/*$('#upload').fileupload({
    dataType: 'json',
    done: function (e, data) {
     	alert('file uploaded');
    }
});*/
