var table = $("#table").DataTable();

$("#btnAddMovie").click(function (e) {
    e.preventDefault();
    var obj = getObject();
    $.ajax({
        url: "/api/movies/",
        type: "POST",
        data: JSON.stringify(obj),
        contentType: "application/json; charset=utf-8",
        success: function(result) {
            getAll();
        },
        error: function() { }
    });
});

function getAll() {
    $.get("/api/movies/", function (result) {
        table.clear();
        for (var i = 0; i < result.length; i++) {
            table.row.add([ result[i].title,
                            result[i].watched,
                            "<a href=\"javascript:del(" + result[i].movieID + ")\"><font color='#ff3385'><i class='fa fa-trash-o' aria-hidden='true'></i></font></a>",
                            "<a href=\"javascript:edit("+result[i].movieID+")\"><font color='#ff3385'><i class='fa fa-pencil' aria-hidden='true'></i></font></a>"]);
        }
        table.draw();
    });
}

function getObject() {
    var obj = {};
    obj.title = $("#movieName").val();
    obj.watched =  false;
    obj.movieID = $("#id").val();
    return obj;
}

