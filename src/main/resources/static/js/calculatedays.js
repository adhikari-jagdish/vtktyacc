$("#inputCheckIn, #inputCheckOut").datepicker({
    onSelect: function() {
                    var checkinDate = $('#inputCheckIn').val();
                     var checkoutDate = $('#inputCheckOut').val();

            $('#inputNoofDays').val(daydiff(parseDate(checkinDate), parseDate(checkoutDate)));



    }
});

function parseDate(str) {
    var mdy = str.split('/')
    return new Date(mdy[2], mdy[0]-1, mdy[1]);
}

function daydiff(first, second) {
    return (second-first)/(1000*60*60*24)
}