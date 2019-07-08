$(document).ready(function(){

  // SUBMIT FORM
    $("form").submit(function(event) {
    // Prevent the form from submitting via the browser.
    event.preventDefault();

    var inputs = $(this).find('input');

      // prepare data from input-form
      var data = {
        firstname : $(inputs[0]).val(),
        lastname : $(inputs[1]).val()
      }

    ajaxPost(data);

      // reset input data
      $(inputs[0]).val("");
      $(inputs[1]).val("")
  });

    function ajaxPost(data){

      // DO POST
      $.ajax({
      type : "POST",
      contentType : "application/json",
      url : window.location + "api/customer/save",
      data : JSON.stringify(data),
      dataType: 'text',
      success : function(result) {
        $("#postResultMsg").html("<p style='background-color:#7FA7B0; color:white; padding:20px 20px 20px 20px'>" + result);
      },
      error : function(e) {
        alert("Error!")
      }
    });

    }

});