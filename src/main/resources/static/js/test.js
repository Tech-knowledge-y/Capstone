$(function(){

    var imagesrc = [
        "img/lightbox/db1.jpg",
        "img/lightbox/db2.jpg",
        "img/lightbox/db3.jpg",
        "img/lightbox/db4.jpg"
    ];

    $storage = $('.storage');
    $viewer = $('.viewer');
    $index = 0;

    for(var i = 0, i < imgsrc.length, i++){
        $storage.append("<div class=" + "image" + i + "></div>");
    }

});