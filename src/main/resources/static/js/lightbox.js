$(function(){

    var imagesrc = [
        "/img/lightbox/geekdom-mentorship.png",
        "/img/lightbox/github-screenshot.png",
        "/img/lightbox/logo-rough.jpg",
        "/img/lightbox/wireframes.jpg",
        "/img/lightbox/trello1.png",
        "/img/lightbox/db1.jpg",
        "/img/lightbox/db2.jpg",
        "/img/lightbox/db3.jpg",
        "/img/lightbox/db4.jpg",
        "/img/lightbox/db5.jpg",
        "/img/lightbox/db6.jpg",
        "/img/lightbox/whiteboard1.jpg",
        "/img/lightbox/tech-palette.png",
        "/img/lightbox/G-and-X.jpg"
    ];

    $storage = $('.storage');
    $viewer = $('.viewer');
    $index = 0;
    $githubBtn = $('.githubBtn');
    console.log($githubBtn);


    for(var i = 0; i < imagesrc.length; i++){
        $storage.append("<div class=" + "image" + i + "></div>");
        $('.image'+i).append("<img src ="+imagesrc[i]+" />")
        $viewer.append("<img class='myslides' src=" + imagesrc[i] + " />");
    }

    $(".viewer .myslides:eq(0)").css("display","block");
    $(".storage img:eq(0)").addClass("active");

    $myslides = $('.myslides');
    $stimg = $('.storage img');

    $(document).on("keydown",()=>{
        $myslides.css("display","none");
        $stimg.removeClass("active")

        $index = show($index);

        if($index > imagesrc.length-1){
            $index = 0;
        } else if($index < 0){
            $index = imagesrc.length;
        }

        $(".myslides:eq(" + $index + ")").css("display","block");
        $(".storage img:eq("+ $index + ")").addClass("active")
    });

    function show($index){
        if(event.keyCode==39){
            $index++;
        }
        if(event.keyCode==37){
            $index--;
        }
        return $index;
    }
});