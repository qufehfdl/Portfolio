function search(content_subject) {
    var search = content_subject;
    $.ajax({
        url : '${root}/board/searchList',
        type: 'post',
        dataType : 'json',
        success:function(result){
        }
    })
}