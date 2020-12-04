function updateCourse() {
    var collegeID = $('#course_college').val();
    document.getElementById("course_teacher").options.length = 0;
    jQuery.ajax({
        dataType: "json",
        type: "GET",
        url: "/admin/updateCourse/query",
        data: {collegeID: collegeID},
        success: function (data) {
            var arr = data.teachers;
            for (var i = 0; i < data.teachers.length; i++) {
                var id = arr[i].userID;
                var name = arr[i].userName;
                $('#course_teacher').append('<option value=' + id + '>' + name + '</option>')
            }
        }, error: function (data) {
            alert(查询错误);
        }
    });
}
