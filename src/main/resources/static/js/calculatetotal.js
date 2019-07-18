 function calculateTotal() {
        var rate = document.getElementById("ticketPrice").value;
        var noofPax = document.getElementById("noOfTickets").value;
        var discount = document.getElementById("ticketDiscount").value;
        var totalInput = document.getElementById("totalPriceJS");

        //do all the calculations here
        var total = price * quantity
        if (discount) total -= discount;

        totalInput.innerHTML = total
    }
