"use strict";

// <![CDATA[
$( document ).ready(function() {
    $('#search').keyup(function () {
        var filter = $(this).val();

        $('.card').each(function () {
            if ($(this).find('.card-title').text().search(new RegExp(filter, 'i')) < 0 ) {
                $(this).parent().hide();
            } else {
                $(this).parent().show();
            }
        })
    });
});
// ]]>