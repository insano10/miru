function getThings() {

    $.get( "stats", function( data ) {
        $( ".result" ).html( data );
    });
}

setInterval(function () { getThings() }, 1000);