
function deleteStudentAsk(id) {
    if (confirm("确认删除?")) {
        location.href = "/admin/deleteStudent/" + id;
    }
}

function deleteUserAsk(id) {
    if (confirm("确认删除?")) {
        location.href = "/admin/deleteUser/" + id;
    }
}

function deleteTeacherAsk(id) {
    if (confirm("确认删除?")) {
        location.href = "/admin/deleteTeacher/" + id;
    }
}

function deleteCourseAsk(id) {
    if (confirm("确认删除?")) {
        location.href = "/admin/deleteCourse/" + id;
    }
}