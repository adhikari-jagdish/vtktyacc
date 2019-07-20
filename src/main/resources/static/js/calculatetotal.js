$(document).ready(function() {
    //this calculates values automatically
    $("#inputNoofAdult, #inputRateAdult").on("keydown keyup", function() {
        calculation();

    });
});

function calculation() {
            var noofPax = document.getElementById('inputNoofAdult').value;
            var rateperPax = document.getElementById('inputRateAdult').value;
            var amount = document.getElementById('inputAmount').value;
            var gst = document.getElementById('inputGst').value;
            var subtotal = document.getElementById('inputSubTotal').value;
            var nepalRemit = document.getElementById('inputNepalRemitCharges').value;
            var grandTotal = document.getElementById('inputGrandTotal');

			var amount = parseInt(noofPax) * parseInt(rateperPax);
			var gst = 0.05 * amount;
			var subtotal = amount + gst;

			var fiftyk = 50000;

			if(subtotal<fiftyk)
			var nepalRemit = 150;
			else
			var nepalRemit = 200;

			var grandTotal = subtotal + nepalRemit;


            if (!isNaN(grandTotal)) {
                document.getElementById('inputAmount').value = amount;
                document.getElementById('inputGst').value = gst;
                document.getElementById('inputSubTotal').value = subtotal;
                document.getElementById('inputNepalRemitCharges').value = nepalRemit;
                document.getElementById('inputGrandTotal').value = grandTotal;

            }
        }