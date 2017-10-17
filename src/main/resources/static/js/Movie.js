var table = $("#table").DataTable();

$("#btnAddMovie").click(function (e) {
    e.preventDefault();
    var obj = getObject();
    $("#movieName").val("");
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

function del(id) {
    $.confirm({
        title: "Confirm removal",
        content: "Are you sure you want to delete this movie?",
        buttons: {
            confirm: function () {
                $.ajax({url: "/api/movies/"+id+"/", type: "DELETE",
                    success: function() {
                        getAll();
                        $.alert("Movie is deleted.");
                    },
                    error: function(err) {
                    }
                });
            },
            cancel: function () {
            }
        }
    });
}

function getObject() {
    var obj = {};
    obj.title = $("#movieName").val();
    obj.watched =  false;
    obj.movieID = $("#id").val();
    return obj;
}

