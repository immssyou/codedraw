// JavaScript source code

$('#vdo-wrapper').tubular({
    videoId: 'GEJLyW72bXU?rel=0&amp;controls=0&amp;showinfo=0',
    width: $(window).width(),
    wrapperZIndex: -100,
    repeat: false,
    start: 0,
});

$('document').ready(function () {
    $('#btn-wrapper').hide();

    setTimeout(function () {
        $('#pause').trigger('click');
        $('#btn-wrapper').fadeIn(1000);
    }, 25000);
});