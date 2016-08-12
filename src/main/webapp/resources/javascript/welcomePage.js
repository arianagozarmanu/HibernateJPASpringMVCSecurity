//metodele onclik inafara

$(document).ready(function() {
	$('#delete-button-form').on("click",function(){
		
		var csrf = $("#csrf-input").val();
		
		$.ajax({
			url: 'welcome/delete2?id='+$("#hiddenProductId").val(),
			type: 'POST',
			dataType: 'json',
			headers:  {
			     'X-CSRF-Token': csrf
		    },
			success: function(data){
				if(data.isValid){
					$('#productMessage').hide();
					$('#displayAlert').html('<div class="alert alert-success"><strong>Success! </strong>'+data.message+'</div>');
					document.getElementById('myTable').deleteRow($("#hiddenProductTableId").val());
				}
				else {
					alert('Delete was not done!');
				}
			},
			error:  function(error) { console.log(error);}
		});

	});
});