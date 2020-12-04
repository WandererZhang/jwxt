function addCourseAsk(courseID,studentID) {
    if (confirm("确认选课?")) {
        location.href = "/student/addCourse?courseID=" + courseID + '&studentID=' + studentID;
    }
}

function deleteCourseAsk(courseID,studentID) {
    if (confirm("确认退课?")) {
        location.href = "/student/deleteCourse?courseID=" + courseID + '&studentID=' + studentID;
    }
}