var table = $("#table").DataTable();

getAll();

$("#btnAddMovie").click(function (e) {
    e.preventDefault();
    var obj = {};
    obj.title = $("#movieName").val();
    obj.watched =  false;
    obj.movieID = $("#addID").val();
    $("#movieName").val("");
    $.ajax({
        url: "/api/movies/",
        type: "POST",
        data: JSON.stringify(obj),
        contentType: "application/json; charset=utf-8",
        success: function(result) {
            getAll();
            $("#txtFailAdd").html("");
        },
        error: function(jqXHR, status, thrownError) {
             var responseText = jQuery.parseJSON(jqXHR.responseText);
             for (var i=0;i<responseText.length;i++) {
                $("#txtFailAdd").append(responseText[i]+"<br>");
             }
        }
    });
});

function getAll() {
    $.get("/api/movies/", function (result) {
        table.clear();
        for (var i = 0; i < result.length; i++) {
            var watched = (result[i].watched) ? "Yes" : "No";
            table.row.add([ result[i].title,
                            watched,
                            "<a href=\"javascript:edit("+result[i].movieID+")\"><font color='#ff3385'><i class='fa fa-pencil' aria-hidden='true'></i></font></a>",
                            "<a href=\"javascript:del(" + result[i].movieID + ")\"><font color='#ff3385'><i class='fa fa-trash-o' aria-hidden='true'></i></font></a>"]);
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

function edit(id) {
    $.get({url:"/api/movies/"+id+"/", type:"GET"}).done( function(result) {
        var watched = result.watched;
        $("#editID").val(result.movieID);
        $("#editName").val(result.title);
        $('#editWatched option:contains(' +  watched + ')').prop({selected: true});
        $("#movieModal").modal("toggle");
    })
}

$("#btnUpdateMovie").click( function (e) {
    e.preventDefault();
    var obj = {};
    obj.title = $("#editName").val();
    obj.watched = $("#editWatched").val();
    obj.movieID = $("#editID").val();
    $.ajax({
            url: "/api/movies/",
            method:"PUT",
            data: JSON.stringify(obj),
            contentType: "application/json; charset=utf-8",
            success: function(result) {
                $("#movieModal").modal("toggle");
                $("#movieModal input").val("");
                getAll();
                $("#txtFailEdit").html("");
            },
            error: function(jqXHR, status, thrownError) {
                var responseText = jQuery.parseJSON(jqXHR.responseText);
                for (var i=0;i<responseText.length;i++) {
                $("#txtFailEdit").append(responseText[i]+"<br>");
                }
            }
    });
})
